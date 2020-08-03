package course.ps1

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.gorttar.test.dynamicTests
import org.junit.jupiter.api.TestFactory
import kotlin.random.Random

class Problem2Test {
    /**
     * запусти, чтобы протестировать функцию [numberOfBobs]
     */
    @Suppress("SpellCheckingInspection")
    @TestFactory
    fun numberOfBobsTest() = (sequenceOf(
        Case("azcbobobegghakl", 2),
        Case("bob", 1),
        Case("foo", 0),
        Case("bobkbob", 2),
        Case("", 0)
    ) + (1..10).asSequence().map { (1..Random.nextInt(5)).asSequence() }.map {
        it.map { Random.nextInt(1, 5) }.map { numberOfBobs -> numberOfBobs.bobs to numberOfBobs }
    }.map { bobsWithAmount ->
        Case(
            (bobsWithAmount.map { (s) -> s } + (1..Random.nextInt(10, 20)).map { nonBOLetters.random() }).toList()
                .shuffled()
                .joinToString(""),
            bobsWithAmount.map { (_, x) -> x }.sum()
        )
    }).dynamicTests { assertThat(numberOfBobs(s)).isEqualTo(expected) }
}

private val Int.bobs: String get() = "b${"ob".repeat(this)}"
private val nonBOLetters = ('a'..'z').filter { it !in "bo" }.map { it.toString() }