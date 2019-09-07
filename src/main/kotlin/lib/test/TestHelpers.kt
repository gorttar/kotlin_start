package lib.test

import lib.control.S
import lib.control.Try
import lib.control.Try.Companion.Do
import lib.control.Try.Companion.failure
import lib.control.Try.Companion.handle
import lib.control.Try.Companion.map
import lib.control.Try.Companion.success
import lib.helpers.alphabet
import lib.output.BLK
import lib.output.G
import lib.output.R
import lib.repr.repr
import lib.test.NamedCase.Companion.namedAs
import lib.test.NamedCaseWithBody.Companion.passTo
import java.util.concurrent.Callable
import java.util.concurrent.ForkJoinPool
import java.util.concurrent.TimeUnit.MILLISECONDS
import java.util.concurrent.TimeoutException
import kotlin.random.Random

fun printSuccessLn(message: Any?) = println("$G$message$BLK")
fun printFailLn(message: Any?) = println("$R$message$BLK")

class NamedCase<Case> private constructor(val case: Case, val name: Any) {
    companion object {
        infix fun <Case> Case.namedAs(name: Any): NamedCase<Case> = NamedCase(this, name)
    }
}

typealias TestResult = Try<Unit>

val pass: TestResult = Unit.success()
val String.fail: TestResult get() = AssertionError(this).failure()

typealias Assertion<R> = (actual: R) -> TestResult

infix fun Boolean.orElse(message: String): TestResult = if (this) pass else message.fail

class NamedCaseWithBody<Case, R> private constructor(
        private val case: Case,
        private val name: Any,
        private val body: Case.() -> R
) : (Assertion<R>) -> Unit {
    override operator fun invoke(assertion: Assertion<R>) =
            Do `try`
                    { body(case) } map
                    { r ->
                        assertion(r) map
                                { printSuccessLn("    $name passed") } handle
                                { printFailLn("!!!$name failed: expected ${it.message} but found ${r.repr}") }
                    } handle
                    { printFailLn("!!!$name failed with error: $it") }

    infix fun withTimeLimit(limit: Double): NamedCaseWithBody<Case, R> = NamedCaseWithBody(case, name) {
        try {
            ForkJoinPool()
                    .submit(Callable(S `try` { body() }))
                    .get((limit * 1000).toLong(), MILLISECONDS) map
                    { it } handle
                    { throw it }
        } catch (e: TimeoutException) {
            throw TimeoutException("Task should be finished in $limit seconds")
        }
    }

    companion object {
        infix fun <Case, R> NamedCase<Case>.passTo(body: Case.() -> R): NamedCaseWithBody<Case, R> =
                NamedCaseWithBody(case, name, body)
    }
}

infix fun <Case, R> Case.selfNamedPassTo(body: Case.() -> R): NamedCaseWithBody<Case, R> = namedAs("$this") passTo body

infix fun <Case, R> NamedCaseWithBody<Case, R>.withTimeLimit(limit: Int) = withTimeLimit(limit.toDouble())

infix fun <Case, R> NamedCaseWithBody<Case, R>.shouldBeEqualTo(expected: R) =
        shouldBe { actual -> (actual == expected) orElse expected.repr }

infix fun <Case, R> NamedCaseWithBody<Case, R>.shouldBe(assertion: Assertion<R>) = this(assertion)

val FAIL: Nothing inline get() = TODO("Реши меня")

fun randomAlphabetPartition(): Pair<String, String> = generateSequence { alphabet.partition { Random.nextBoolean() } }
        .first { (xs, ys) -> xs.isNotEmpty() && ys.isNotEmpty() }