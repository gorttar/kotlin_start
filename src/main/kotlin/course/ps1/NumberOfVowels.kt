package course.ps1

import lib.repr.repr
import lib.test.FAIL
import lib.test.NamedCase.Companion.namedAs
import lib.test.NamedCaseWithBody.Companion.passTo
import lib.test.shouldBeEqualTo
import kotlin.random.Random

/**
 * Assume [s] is a string of lower case characters.
 * Write a [numberOfVowels] function that counts up the number of vowels contained in the string s.
 * Valid vowels are: 'a', 'e', 'i', 'o', and 'u'.
 * For example, if [s] = "azcbobobegghakl", your [numberOfVowels] should return 5
 *
 * Параметром функции является строка [s], состоящая из строчных букв английского алфавита
 * Нужно написать функцию [numberOfVowels], которая считает количество гласных букв в строке
 * Гласными в английском языке являются буквы 'a', 'e', 'i', 'o' и 'u'.
 * Пример: если [s] = "azcbobobegghakl", функция [numberOfVowels] должна вернуть 5
 *
 * Для проверки задания запускаешь [main] и смотришь вывод. Он должен быть зелёным, если всё верно
 */
fun numberOfVowels(s: String): Int {
    FAIL
}

/**
 * запусти, чтобы протестировать функцию [numberOfVowels]
 */
fun main() = (
        sequenceOf(
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
        )
    .forEach { (s, expected) ->
        s namedAs "Test for ${s.repr}" passTo { numberOfVowels(s) } shouldBeEqualTo expected
    }

private val vowels = "aeiou".toList()
private val consonants = ('a'..'z').filter { it !in vowels }
