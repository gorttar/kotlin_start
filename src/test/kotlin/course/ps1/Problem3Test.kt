package course.ps1

import lib.repr.repr
import lib.test.NamedCase.Companion.namedAs
import lib.test.NamedCaseWithBody.Companion.passTo
import lib.test.shouldBeEqualTo
import kotlin.random.Random

/**
 * запусти, чтобы протестировать функцию [longestAlphabetic]
 */
fun main() = testCases.forEach { (s, expected) ->
    s namedAs "Test for ${s.repr}" passTo { longestAlphabetic(s) } shouldBeEqualTo expected
}

private fun randomTestCase(): Pair<String, String> = with((1..Random.nextInt(2, 10)).map { alphabeticOrdered() }) {
    val longestPart = maxBy { it.length }!!
    joinToString("") to longestPart
}

private fun alphabeticOrdered(): String = (sequenceOf('a', 'z') + (1..Random.nextInt(9)).map { letters.random() })
        .sorted()
        .joinToString("")

private val letters = ('a'..'z').toList()
private val testCases = sequenceOf(
        "" to "",
        "azcbobobegghakl" to "beggh",
        "abcbcd" to "abc"
) +
        (1..10).map { randomTestCase() }