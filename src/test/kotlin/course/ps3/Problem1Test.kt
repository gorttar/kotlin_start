package course.ps3

import org.gorttar.test.randomAlphabetPartition
import org.gorttar.test.selfNamedPassTo
import org.gorttar.test.shouldBeEqualTo
import kotlin.random.Random.Default.nextBoolean
import kotlin.random.Random.Default.nextInt

/**
 * Прочтите введение intro_ru.md перед тем, как приступать к этой задаче. Мы начнём написание игры виселица с написания
 * 3 простых функций, которые помогут нам в написании самой игры.
 * Первой напишите функцию [isWordGuessed]. Требования к функции описаны в её документации.
 *
 * запусти, чтобы протестировать функцию [isWordGuessed]
 */
fun main() = (sequenceOf(
        TestCase("apple", setOf('e', 'i', 'k', 'p', 'r', 's'), false),
        TestCase("apple", setOf('a', 'e', 'i', 'l', 'p', 's'), true)
) + (1..10).asSequence().map {
    nextBoolean()
            .let { expected ->
                randomAlphabetPartition()
                        .let { (xs, ys) ->
                            if (expected) TestCase(
                                    (1..nextInt(11)).map { xs.random() }.joinToString(""),
                                    xs.toSet(),
                                    true)
                            else TestCase(
                                    ((1..nextInt(6)).map { xs.random() } +
                                            (0..nextInt(5)).map { ys.random() })
                                            .joinToString(""),
                                    xs.toSet(),
                                    false)
                        }
            }
}).forEach { (args, expected) ->
    args selfNamedPassTo { isWordGuessed(secretWord, lettersGuessed) } shouldBeEqualTo expected
}