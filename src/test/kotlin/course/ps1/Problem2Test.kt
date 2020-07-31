package course.ps1

import org.gorttar.repr.repr
import org.gorttar.test.NamedCase.Companion.namedAs
import org.gorttar.test.NamedCaseWithBody.Companion.passTo
import org.gorttar.test.shouldBeEqualTo
import kotlin.random.Random

/**
 * запусти, чтобы протестировать функцию [numberOfBobs]
 */
fun main() = testCases.forEach { (s, expected) ->
    s namedAs "Test for ${s.repr}" passTo { numberOfBobs(s) } shouldBeEqualTo expected
}

private val Int.bobs: String get() = "b${"ob".repeat(this)}"
private val nonBOLetters = ('a'..'z').filter { it !in "bo" }.map { it.toString() }
private val testCases = sequenceOf(
        "azcbobobegghakl" to 2,
        "bob" to 1,
        "foo" to 0,
        "bobkbob" to 2,
        "" to 0
) +
        (1..10)
                .map {
                    val bobsWithAmount = (1..Random.nextInt(5))
                            .map {
                                val numberOfBobs = Random.nextInt(1, 5)
                                numberOfBobs.bobs to numberOfBobs
                            }
                    val numberOfBobs = bobsWithAmount.map { it.second }.sum()
                    (bobsWithAmount.map { it.first } + (1..Random.nextInt(10, 20)).map { nonBOLetters.random() })
                            .shuffled()
                            .joinToString("") to numberOfBobs
                }