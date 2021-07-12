package course.ps3

import assertk.assertThat
import assertk.assertions.isEqualTo
import course.currentSolutionLanguage
import course.languageDependent
import org.gorttar.test.dynamicTests
import org.gorttar.test.randomAlphabetPartition
import org.junit.jupiter.api.TestFactory
import kotlin.random.Random.Default.nextBoolean
import kotlin.random.Random.Default.nextInt

class Problem2Test {
    /**
     * Второй напишите функцию [getGuessedWord]. Требования к функции описаны в её документации.
     *
     * запусти, чтобы протестировать функцию [getGuessedWord] или [Hangman.getGuessedWord]
     * в зависимости от языка, присвоенного константе [currentSolutionLanguage]
     */
    @TestFactory
    fun getGuessedWordTest() = languageDependent(
        ::getGuessedWord,
        Hangman::getGuessedWord
    ) { getGuessedWord ->
        (sequenceOf(
            Case("apple", setOf('e', 'i', 'k', 'p', 'r', 's'), "_ pp_ e"),
            Case("apple", setOf('a', 'e', 'i', 'l', 'p', 's'), "apple")
        ) + (1..10).asSequence().map {
            randomAlphabetPartition()
                .let { (xs, ys) ->
                    val fold = (1..nextInt(11))
                        .map {
                            if (nextBoolean()) xs.random().let { it to it }
                            else ys.random().let { it to "_ " }
                        }
                        .fold("" to "") { (wAcc, mAcc), (w, m) -> "$wAcc$w" to "$mAcc$m" }
                    val (word, masked) = fold
                    Case(word, xs.toSet(), masked)
                }
        }).dynamicTests {
            assertThat(getGuessedWord(secretWord, lettersGuessed).replace(" ", "")).isEqualTo(expected.replace(" ", ""))
        }
    }
}