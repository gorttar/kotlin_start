package org.gorttar.test.io

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.gorttar.test.io.OperationType.*
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.currentStackTrace

typealias IOAssertion = (IOOperationChunk) -> Unit

class IOScenario private constructor() {
    private val ioAssertions: MutableList<IOAssertion> = mutableListOf()
    private val inputBuilder = StringBuilder()
    fun sendLine(line: String): Unit = "$line\n".let {
        inputBuilder.append(it)
        expect(IN) { actual, message -> assertThat(actual, message).isEqualTo(it) }
    }

    fun expect(assertion: (actual: String, message: String) -> Unit) = expect(OUT, assertion)
    fun expectString(expected: String) = expect(stringEqualsAssertion(expected))
    fun expectLine(expected: String) = expectString("$expected\n")

    fun expectErr(assertion: (actual: String, message: String) -> Unit) = expect(ERR, assertion)
    fun expectErrString(expected: String) = expectErr(stringEqualsAssertion(expected))
    fun expectErrLine(expected: String) = expectErrString("$expected\n")

    private fun stringEqualsAssertion(expected: String): (String, String) -> Unit = { actual, message ->
        assertThat(actual, message).isEqualTo(expected.replace("\n", cr))
    }

    private fun expect(expectedType: OperationType, assertion: (actual: String, message: String) -> Unit) =
            "at scenario ${currentStackTrace().first { it.className != this::class.qualifiedName }}".let { message ->
                ioAssertions.add { (type, content) ->
                    assertThat(type, message).isEqualTo(expectedType)
                    assertion(content, message)
                }
                Unit
            }

    companion object {
        private val cr = ByteArrayOutputStream().use {
            PrintStream(it).println()
            it.toString()
        }

        fun runIOTest(scenario: IOScenario.() -> Unit, block: () -> Unit) {
            IOScenario().apply {
                scenario()
                val chunks = recordIO("$inputBuilder", block)
                assertThat(chunks.size).isEqualTo(ioAssertions.size)
                chunks.zip(ioAssertions) { chunk, assertion -> assertion(chunk) }
            }
        }
    }
}