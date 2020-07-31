package course.ps1

import org.gorttar.repr.repr
import org.gorttar.test.NamedCase.Companion.namedAs
import org.gorttar.test.NamedCaseWithBody.Companion.passTo
import org.gorttar.test.shouldBeEqualTo
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