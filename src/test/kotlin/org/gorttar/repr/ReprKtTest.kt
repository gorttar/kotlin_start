package org.gorttar.repr

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.gorttar.test.dynamicTests
import org.junit.jupiter.api.TestFactory

/**
 * @author Andrey Antipov (gorttar@gmail.com) (2019-09-19)
 */
class ReprKtTest {
    @TestFactory
    fun `repr should provide correct values`() = dynamicTests(
        Case(null.exact, "null"),
        Case(1.exact, "1"),
        Case(1.2.exact, "1.2"),
        Case('x'.exact, "'x'"),
        Case("foo".exact, "\"foo\""),
        Case(
            listOf(null, 1, 1.2, 'x', "foo", listOf('y', "bar")).exact,
            "[null, 1, 1.2, 'x', \"foo\", ['y', \"bar\"]]"
        ),
        Case(
            mapOf(1 to null, 'x' to 1.2, "foo" to mapOf("bar" to 'y')).exact,
            "{1=null, 'x'=1.2, \"foo\"={\"bar\"='y'}}"
        ),
        Case(mutableListOf<Any>().apply { add(this) }.exact, "[(cycle Collection #1)]"),
        Case(
            mutableMapOf<Any, Any>().also { it[it] = it }.exact,
            "{(cycle Map #1)=(cycle Map #1)}"
        ),
        Case(
            mutableListOf<Any>().apply { add(listOf(this)) }.exact("[(mutualReferenceList)]"),
            "[[(cycle Collection #1)]]"
        ),
        Case(
            mutableMapOf<Any, Any>()
                .apply {
                    listOf(this)
                        .let { mapOf(it to it) }
                        .let { this[it] = it }
                }
                .exact("{(mutualReferenceMap)}"),
            "{{[(cycle Map #1)]=(cycle Collection #3)}=(cycle Map #2)}"),
        Case(("foo" to 'x').exact, "Pair(\"foo\", 'x')"),
        Case(Triple("foo", 'x', listOf('y')).exact, "Triple(\"foo\", 'x', ['y'])"),
        Case(
            mutableListOf<Any>().let { it to it }.also { it.first.add(it) }.exact("((mutualReferencePair))"),
            "Pair([(cycle Pair #1)], (cycle Collection #2))"
        ),
        Case(
            mutableListOf<Any>().let { Triple(it, it, it) }.also { it.first.add(it) }
                .exact("((mutualReferenceTriple))"),
            "Triple([(cycle Triple #1)], (cycle Collection #2), (cycle Collection #2))"
        )
    ) {
        assertThat(o().repr).isEqualTo(expected)
    }

    private data class Case(val o: () -> Any?, val expected: String)
}

private fun <T> T.exact(toString: String): () -> T = { this }.let {
    object : () -> T by it {
        override fun toString() = toString
    }
}

private val <T> T.exact: () -> T get() = exact(toString())