package course.ps1

import assertk.assertThat
import assertk.assertions.isEqualTo
import course.SolutionsLanguage.JAVA
import course.SolutionsLanguage.KOTLIN
import course.currentSolutionLanguage
import org.gorttar.test.dynamicTests
import org.junit.jupiter.api.TestFactory
import kotlin.random.Random

private val solution = when (currentSolutionLanguage) {
    KOTLIN -> ::numberOfVowels
    JAVA -> NumberOfVowels::numberOfVowels
}

class Problem1Test {
    /**
     * запусти, чтобы протестировать функцию [numberOfVowels] или [NumberOfVowels.numberOfVowels]
     * в зависимости от языка, присвоенного константе [currentSolutionLanguage]
     */
    @Suppress("SpellCheckingInspection")
    @TestFactory
    fun numberOfVowelsTest() = (sequenceOf(
        Case("azcbobobegghakl", 5),
        Case("abba", 2),
        Case("foo", 2),
        Case("kotlin", 2),
        Case("padavan", 3),
        Case("", 0)
    ) + (1..10).asSequence().map { Random.nextInt(10) }.map {
        Case(
            ((1..it).map { vowels.random() } + (1..Random.nextInt(10)).map { consonants.random() })
                .shuffled().joinToString(""),
            it
        )
    }).dynamicTests { assertThat(solution(s)).isEqualTo(expected) }

}

@Suppress("SpellCheckingInspection")
private val vowels = "aeiou".toList()
private val consonants = ('a'..'z').filter { it !in vowels }