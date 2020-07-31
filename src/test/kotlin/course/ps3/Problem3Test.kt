package course.ps3

import org.gorttar.test.NamedCase.Companion.namedAs
import org.gorttar.test.NamedCaseWithBody.Companion.passTo
import org.gorttar.test.randomAlphabetPartition
import org.gorttar.test.shouldBeEqualTo

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