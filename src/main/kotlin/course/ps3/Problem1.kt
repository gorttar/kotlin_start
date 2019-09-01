package course.ps3

import lib.repr.repr
import lib.test.passTo
import lib.test.randomAlphabetPartition
import lib.test.shouldBeEqualTo
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
    args passTo { isWordGuessed(secretWord, lettersGuessed) } shouldBeEqualTo expected
}

object IsWordGuessedSample {
    @JvmStatic
    fun main(args: Array<String>) {
        /**
         * пример использования функции [isWordGuessed]
         */
        println(isWordGuessed("apple", setOf('e', 'i', 'k', 'p', 'r', 's')))/*должно вывести false*/
    }
}

class TestArgs(val secretWord: String, val lettersGuessed: Set<Char>) {
    override fun toString(): String = "Test: secretWord=\"$secretWord\", lettersGuessed=$lettersGuessed"
}

class TestCase<E>(secretWord: String, lettersGuessed: Set<Char>, val expected: E) {
    val args: TestArgs = TestArgs(secretWord, lettersGuessed)
    @Suppress("unused")
    val repr by lazy { "TestCase(\"$secretWord\", setOf(${lettersGuessed.joinToString()}), ${expected.repr})" }

    operator fun component1(): TestArgs = args
    operator fun component2(): E = expected
}