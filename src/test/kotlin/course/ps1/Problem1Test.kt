package course.ps1

import lib.repr.repr
import lib.test.NamedCase.Companion.namedAs
import lib.test.NamedCaseWithBody.Companion.passTo
import lib.test.shouldBeEqualTo
import kotlin.random.Random

/**
 * запусти, чтобы протестировать функцию [numberOfVowels]
 */
fun main() = testCases.forEach { (s, expected) ->
    s namedAs "Test for ${s.repr}" passTo { numberOfVowels(s) } shouldBeEqualTo expected
}

private val vowels = "aeiou".toList()
private val consonants = ('a'..'z').filter { it !in vowels }
private val testCases = sequenceOf(
        "azcbobobegghakl" to 5,
        "abba" to 2,
        "foo" to 2,
        "kotlin" to 2,
        "padavan" to 3,
        "" to 0
) +
        (1..10)
                .map {
                    val numberOfVowels = Random.nextInt(10)
                    (
                            (1..numberOfVowels)
                                    .map { vowels.random() } +
                                    (1..Random.nextInt(10))
                                            .map { consonants.random() }
                            )
                            .shuffled()
                            .joinToString("") to numberOfVowels
                }