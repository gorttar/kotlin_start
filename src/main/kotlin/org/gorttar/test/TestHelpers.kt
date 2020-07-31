package org.gorttar.test

import org.gorttar.control.S
import org.gorttar.control.Try
import org.gorttar.control.Try.Companion.Do
import org.gorttar.control.Try.Companion.failure
import org.gorttar.control.Try.Companion.handle
import org.gorttar.control.Try.Companion.map
import org.gorttar.control.Try.Companion.success
import org.gorttar.helpers.alphabet
import org.gorttar.output.green
import org.gorttar.output.red
import org.gorttar.repr.repr
import org.gorttar.test.NamedCase.Companion.namedAs
import org.gorttar.test.NamedCaseWithBody.Companion.passTo
import java.util.concurrent.Callable
import java.util.concurrent.ForkJoinPool
import java.util.concurrent.TimeUnit.MILLISECONDS
import java.util.concurrent.TimeoutException
import kotlin.random.Random

fun printSuccessLn(message: Any?) = println(message.repr.green)
fun printFailLn(message: Any?) = println(message.repr.red)

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