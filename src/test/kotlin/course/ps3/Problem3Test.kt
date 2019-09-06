package course.ps3

import lib.test.NamedCase.Companion.namedAs
import lib.test.NamedCaseWithBody.Companion.passTo
import lib.test.randomAlphabetPartition
import lib.test.shouldBeEqualTo

/**
 * Третьей напишите функцию [getAvailableLetters]. Требования к функции описаны в её документации.
 *
 * запусти, чтобы протестировать функцию [getAvailableLetters]
 */
fun main() = (sequenceOf(
        setOf('e', 'i', 'k', 'p', 'r', 's') to "abcdfghjlmnoqtuvwxyz"
) + (1..10).asSequence().map {
    randomAlphabetPartition().let { (xs, ys) -> ys.toSet() to xs }
}).forEach { (arg, expected) ->
    arg namedAs "Test for $arg" passTo { getAvailableLetters(arg) } shouldBeEqualTo expected
}