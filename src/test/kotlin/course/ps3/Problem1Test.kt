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

class Problem1Test {
    /**
     * Прочтите введение intro_ru.md перед тем, как приступать к этой задаче. Мы начнём написание игры виселица с написания
     * 3 простых функций, которые помогут нам в написании самой игры.
     * Первой напишите функцию [isWordGuessed]. Требования к функции описаны в её документации.
     *
     * запусти, чтобы протестировать функцию [isWordGuessed] или [Hangman.isWordGuessed]
     * в зависимости от языка, присвоенного константе [currentSolutionLanguage]
     */
    @TestFactory
    fun isWordGuessedTest() = languageDependent(
        ::isWordGuessed,
        Hangman::isWordGuessed
    ) { isWordGuessed ->
        (sequenceOf(
            Case("apple", setOf('e', 'i', 'k', 'p', 'r', 's'), false),
            Case("apple", setOf('a', 'e', 'i', 'l', 'p', 's'), true)
        ) + (1..10).asSequence().map {
            nextBoolean()
                .let { expected ->
                    randomAlphabetPartition()
                        .let { (xs, ys) ->
                            if (expected) Case(
                                (1..nextInt(11)).map { xs.random() }.joinToString(""),
                                xs.toSet(),
                                true
                            )
                            else Case(
                                ((1..nextInt(6)).map { xs.random() } + (0..nextInt(5)).map { ys.random() }).joinToString(
                                    ""
                                ),
                                xs.toSet(),
                                false
                            )
                        }
                }
        }).dynamicTests { assertThat(isWordGuessed(secretWord, lettersGuessed)).isEqualTo(expected) }
    }
}