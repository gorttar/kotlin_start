package lib.repr

import org.testng.Assert.assertEquals
import org.testng.annotations.DataProvider
import org.testng.annotations.Test

/**
 * @author Andrey Antipov (gorttar@gmail.com) (2019-09-19)
 */
class ReprKtTest {
    @DataProvider
    fun `data for repr testing`() = arrayOf(
            arrayOf("null reference", null.exact, "null"),
            arrayOf("Int number", 1.exact, "1"),
            arrayOf("Double number", 1.2.exact, "1.2"),
            arrayOf("Char", 'x'.exact, "'x'"),
            arrayOf("String", "foo".exact, "\"foo\""),
            arrayOf(
                    "List of different objects",
                    listOf(null, 1, 1.2, 'x', "foo", listOf('y', "bar")).exact,
                    "[null, 1, 1.2, 'x', \"foo\", ['y', \"bar\"]]"),
            arrayOf(
                    "Map of different objects",
                    mapOf(1 to null, 'x' to 1.2, "foo" to mapOf("bar" to 'y')).exact,
                    "{1=null, 'x'=1.2, \"foo\"={\"bar\"='y'}}"),
            arrayOf("self reference list", mutableListOf<Any>().apply { add(this) }.exact, "[(cycle Collection #1)]"),
            arrayOf(
                    "self reference map",
                    mutableMapOf<Any, Any>().also { it[it] = it }.exact,
                    "{(cycle Map #1)=(cycle Map #1)}"),
            arrayOf(
                    "mutual reference list",
                    mutableListOf<Any>().apply { add(listOf(this)) }.exact("[(mutualRefList)]"),
                    "[[(cycle Collection #1)]]"),
            arrayOf(
                    "mutual reference map",
                    mutableMapOf<Any, Any>()
                            .apply {
                                listOf(this)
                                        .let { mapOf(it to it) }
                                        .let { this[it] = it }
                            }
                            .exact("{(mutualRefMap)}"),
                    "{{[(cycle Map #1)]=(cycle Collection #3)}=(cycle Map #2)}"),
            arrayOf("Simple pair", ("foo" to 'x').exact, "Pair(\"foo\", 'x')"),
            arrayOf("Simple triple", Triple("foo", 'x', listOf('y')).exact, "Triple(\"foo\", 'x', ['y'])"),
            arrayOf(
                    "mutual reference pair",
                    mutableListOf<Any>().let { it to it }.also { it.first.add(it) }.exact("((mutualRefPair))"),
                    "Pair([(cycle Pair #1)], (cycle Collection #2))"),
            arrayOf(
                    "mutual reference triple",
                    mutableListOf<Any>().let { Triple(it, it, it) }.also { it.first.add(it) }.exact("((mutualRefTriple))"),
                    "Triple([(cycle Triple #1)], (cycle Collection #2), (cycle Collection #2))"))

    @Test(dataProvider = "data for repr testing")
    fun `repr should provide correct values`(case: String, o: () -> Any?, expected: String) =
            assertEquals(o().repr, expected, "Case [$case] failed")
}

private fun <T> T.exact(toString: String): () -> T = { this }.let {
    object : () -> T by it {
        override fun toString() = toString
    }
}

private val <T> T.exact: () -> T get() = exact(toString())