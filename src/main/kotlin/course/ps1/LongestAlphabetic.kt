package course.ps1

import lib.repr.repr
import lib.test.FAIL
import lib.test.NamedCase.Companion.namedAs
import lib.test.NamedCaseWithBody.Companion.passTo
import lib.test.shouldBeEqualTo
import kotlin.random.Random

/**
 * Assume [s] is a string of lower case characters.
 * Write a [longestAlphabetic] function that returns the longest substring of s in which the letters occur in alphabetical order.
 * For example, if s = "azcbobobegghakl", then your function should return "beggh"
 *
 * In the case of ties, return the first substring.
 * For example, if s = "abcbcd", then your function should return "abc"
 *
 * Note: This problem may be challenging.
 * We encourage you to work smart.
 * If you've spent more than a few hours on this problem,
 * we suggest that you move on to a different part of the course.
 * If you have time, come back to this problem after you've had a break and cleared your head.
 *
 *
 * Параметром функции является строка [s], состоящая из строчных букв английского алфавита
 * Нужно написать функцию [longestAlphabetic] которая возвращает самую длинную подстроку из [s]
 * буквы в которой идут в алфавитном порядке.
 * Пример: если [s] = "azcbobobegghakl", функция [longestAlphabetic] должна вернуть "beggh"
 *
 * Если таких последовательностей несколько, надо вернуть первую найденную.
 * Пример: если [s] = "abcbcd", функция [longestAlphabetic] должна вернуть "abc"
 *
 * Заметка: Эта задача достаточно сложная.
 * Призываю тебя хорошенько над ней подумать, угадайкой её не решить.
 * Если ты потратишь больше нескольких часов на неё,
 * переключись на что-нибудь другое.
 * Когда отдохнёшь и снова появятся идеи, вернись к ней и попробуй решить ещё раз.
 *
 * Для проверки задания запускаешь [main] и смотришь вывод. Он должен быть зелёным, если всё верно
 */
fun longestAlphabetic(s: String): String {
    FAIL
}

/**
 * запусти, чтобы протестировать функцию [longestAlphabetic]
 */
fun main() = (
        sequenceOf(
            "" to "",
            "azcbobobegghakl" to "beggh",
            "abcbcd" to "abc"
        ) +
                (1..10).map { randomTestCase() }
        )
    .forEach { (s, expected) ->
        s namedAs "Test for ${s.repr}" passTo { longestAlphabetic(s) } shouldBeEqualTo expected
    }

private fun randomTestCase(): Pair<String, String> = with((1..Random.nextInt(2, 10)).map { alphabeticOrdered() }) {
    val longestPart = maxBy { it.length }!!
    joinToString("") to longestPart
}

private fun alphabeticOrdered(): String = (sequenceOf('a', 'z') + (1..Random.nextInt(9)).map { letters.random() })
    .sorted()
    .joinToString("")

private val letters = ('a'..'z').toList()