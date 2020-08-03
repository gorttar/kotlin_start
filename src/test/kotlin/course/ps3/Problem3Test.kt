package course.ps3

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.gorttar.test.dynamicTests
import org.gorttar.test.randomAlphabetPartition
import org.junit.jupiter.api.TestFactory

class Problem3Test {
    /**
     * Третьей напишите функцию [getAvailableLetters]. Требования к функции описаны в её документации.
     *
     * запусти, чтобы протестировать функцию [getAvailableLetters]
     */
    @TestFactory
    fun getAvailableLettersTest() = (sequenceOf(
        Case(setOf('e', 'i', 'k', 'p', 'r', 's'), "abcdfghjlmnoqtuvwxyz")
    ) + (1..10).asSequence().map {
        randomAlphabetPartition().let { (xs, ys) -> Case(ys.toSet(), xs) }
    }).dynamicTests { assertThat(getAvailableLetters(lettersGuessed)).isEqualTo(expected) }

    private data class Case(val lettersGuessed: Set<Char>, val expected: String)
}