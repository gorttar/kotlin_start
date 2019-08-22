package course.ps1

import lib.repr.repr
import lib.test.FAIL
import lib.test.NamedCase.Companion.namedAs
import lib.test.NamedCaseWithBody.Companion.passTo
import lib.test.shouldBeEqualTo
import kotlin.random.Random

/**
 * Assume [s] is a string of lower case characters.
 * Write a [numberOfBobs] function that returns the number of times the string "bob" occurs in s.
 * For example, if s = 'azcbobobegghakl', then your [numberOfBobs] should return 2
 *
 * Параметром функции является строка [s], состоящая из строчных букв английского алфавита
 * Нужно написать функцию [numberOfBobs] которая считает, сколько раз строка "bob" встречается в [s].
 * Пример: если [s] = 'azcbobobegghakl', функция [numberOfBobs] должна вернуть 2
 *
 * Для проверки задания запускаешь [main] и смотришь вывод. Он должен быть зелёным, если всё верно
 */
fun numberOfBobs(s: String): Int {
    FAIL
}

/**
 * запусти, чтобы протестировать функцию [numberOfBobs]
 */
fun main() = (
        sequenceOf(
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
        )
    .forEach { (s, expected) ->
        s namedAs "Test for ${s.repr}" passTo { numberOfBobs(s) } shouldBeEqualTo expected
    }

private val Int.bobs: String get() = "b${"ob".repeat(this)}"
private val nonBOLetters = ('a'..'z').filter { it !in "bo" }.map { it.toString() }