package course.ps1

import assertk.assertThat
import assertk.assertions.isEqualTo
import course.currentSolutionLanguage
import course.languageDependent
import org.gorttar.test.dynamicTests
import org.junit.jupiter.api.TestFactory
import kotlin.random.Random

class Problem3Test {
    /**
     * запусти, чтобы протестировать функцию [longestAlphabetic] или [LongestAlphabetic.longestAlphabetic]
     * в зависимости от языка, присвоенного константе [currentSolutionLanguage]
     */
    @Suppress("SpellCheckingInspection")
    @TestFactory
    fun longestAlphabeticTest() = languageDependent(
        ::longestAlphabetic,
        LongestAlphabetic::longestAlphabetic
    ) { longestAlphabetic ->
        (sequenceOf(
            Case("", ""),
            Case("azcbobobegghakl", "beggh"),
            Case("abcbcd", "abc")
        ) + (1..10).map {
            (1..Random.nextInt(2, 10)).map {
                (sequenceOf('a', 'z') + (1..Random.nextInt(9)).map { letters.random() }).sorted().joinToString("")
            }.let { Case(it.joinToString(""), it.maxByOrNull { s -> s.length }!!) }
        }).dynamicTests { assertThat(longestAlphabetic(s)).isEqualTo(expected) }
    }

    private data class Case(val s: String, val expected: String)
}

private val letters = ('a'..'z')