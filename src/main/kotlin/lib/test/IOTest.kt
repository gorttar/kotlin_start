package lib.test

import lib.control.Try
import lib.control.Try.Companion.Do
import lib.control.Try.Companion.flatMap
import lib.control.Try.Companion.handle
import lib.control.Try.Companion.map
import lib.control.Try.Failure
import lib.control.managedBy
import lib.control.on
import lib.repr.repr
import lib.test.IOStep.Expect
import lib.test.IOStep.Send
import java.io.InputStream
import java.io.PipedInputStream
import java.io.PipedOutputStream
import java.io.PrintStream
import java.lang.Thread.sleep
import kotlin.math.max

typealias IOPipe = Pair<InputStream, PrintStream>

fun pipe(): IOPipe = with(PipedInputStream()) { this to PrintStream(PipedOutputStream(this)) }

fun PrintStream.putLine(message: Any?) {
    println(message)
    flush()
}

fun InputStream.readLine(): String? = managedBy(System::`in`, System::setIn).on(this) { kotlin.io.readLine() }

fun InputStream.tryReadLine(): Try<String?> = Do `try` { readLine() }

sealed class IOStep {
    class Send(val message: Any?) : IOStep() {
        override fun toString() = "Send($message)"
    }

    class Expect(val assertion: Assertion<String?>) : IOStep()
}

class IOScenario private constructor(private val name: String, private val steps: List<IOStep>, private val sleep: Long)
    : (() -> Unit) -> Unit {
    override operator fun invoke(block: () -> Unit) = this[block] map
            { result: TestResult ->
                result map
                        { printSuccessLn("    $name passed") } handle
                        { printFailLn("!!!$name failed: ${it.message}") }
            } handle
            { printFailLn("!!!$name failed with error: $it") }

    operator fun get(block: () -> Unit): Try<TestResult> {
        val (mainIn, toMainOut) = pipe()
        val (threadIn, toThreadOut) = pipe()

        return managedBy(System::out, System::setOut).on(toMainOut) { oldOut ->
            managedBy(System::`in`, System::setIn).on(threadIn) {
                with(oldOut) {
                    with(mainIn) {
                        Do `try` {
                            with(Thread(block)) {
                                var t: Throwable? = null
                                uncaughtExceptionHandler = Thread.UncaughtExceptionHandler { _, e -> t = e }
                                start()

                                val result: TestResult? = steps.asSequence()
                                        .flatMap { step ->
                                            when (step) {
                                                is Send -> step
                                                        .message
                                                        .also { println("-> $it") }
                                                        .also { toThreadOut.putLine(it) }
                                                        .let { emptySequence<TestResult>() }
                                                is Expect -> sleep(sleep)
                                                        .let { toMainOut.flush() }
                                                        .let { tryReadLine() } map
                                                        {
                                                            println("<- $it")
                                                            sequenceOf(step.assertion(it))
                                                        } handle
                                                        { sequenceOf("Expected some output but nothing found".fail) }
                                            }
                                        }
                                        .firstOrNull { it is Failure }

                                join()
                                toMainOut.flush()
                                sleep(sleep)
                                result ?: t?.also { throw it }

                                (result ?: pass) flatMap
                                        {
                                            tryReadLine() map
                                                    { "Unexpected line ${it.repr} from output".fail } handle
                                                    { pass }
                                        }
                            }
                        }
                    }
                }
            }
        }
    }

    class IOScenarioBuilder private constructor() {
        private val steps: MutableList<IOStep> = mutableListOf()
        var name: String = "IO scenario"
        var sleep: Long = 100
            set(value) {
                field = max(value, 0)
            }

        private fun build(): IOScenario = IOScenario(name, steps, sleep)
        fun sendLine(message: Any?) = steps.plusAssign(Send(message))
        fun expect(assertion: Assertion<String?>) = steps.plusAssign(Expect(assertion))
        fun expectLine(expected: String?) =
                expect { if (it == expected) pass else "Expected ${expected.repr} but found ${it.repr}".fail }

        companion object {
            fun ioScenario(builder: IOScenarioBuilder.() -> Unit): IOScenario =
                    IOScenarioBuilder().apply(builder).build()
        }
    }
}