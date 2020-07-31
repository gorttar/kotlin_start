package course.ps3

import org.gorttar.repr.repr
import org.gorttar.test.orElse
import org.gorttar.test.randomAlphabetPartition
import org.gorttar.test.selfNamedPassTo
import org.gorttar.test.shouldBe
import kotlin.random.Random.Default.nextBoolean
import kotlin.random.Random.Default.nextInt

/**
 * Второй напишите функцию [getGuessedWord]. Требования к функции описаны в её документации.
 *
 * запусти, чтобы протестировать функцию [getGuessedWord]
 */
fun main() = (sequenceOf(
        TestCase("apple", setOf('e', 'i', 'k', 'p', 'r', 's'), "_ pp_ e"),
        TestCase("apple", setOf('a', 'e', 'i', 'l', 'p', 's'), "apple")
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
                TestCase(word, xs.toSet(), masked)
            }
}).forEach { (args, expected) ->
    args selfNamedPassTo { getGuessedWord(secretWord, lettersGuessed) } shouldBe { actual ->
        (actual.replace(" ", "") == expected.replace(" ", "")) orElse expected.repr
    }
}