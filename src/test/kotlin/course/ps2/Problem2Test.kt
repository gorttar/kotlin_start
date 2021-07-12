package course.ps2

import assertk.assertThat
import assertk.assertions.isEqualTo
import course.currentSolutionLanguage
import course.languageDependent
import org.gorttar.test.dynamicTests
import org.junit.jupiter.api.TestFactory

private val solution = languageDependent(::payInAYearExhaustive, PayInAYearExhaustive::payInAYearExhaustive)

class Problem2Test {
    /**
     * запусти, чтобы протестировать функцию [payInAYearExhaustive] или [PayInAYearExhaustive.payInAYearExhaustive]
     * в зависимости от языка, присвоенного константе [currentSolutionLanguage]
     */
    @TestFactory
    fun payInAYearExhaustiveTest() = (sequenceOf(
        Case(3329.0, 0.2, "Lowest Payment: 310"),
        Case(3926.0, 0.2, "Lowest Payment: 360"),
        Case(4773.0, 0.2, "Lowest Payment: 440")
    ) + exhaustiveCases.shuffled().asSequence().take(10)).dynamicTests {
        assertThat(solution(balance, annualInterestRate)).isEqualTo(expected)
    }
}

val exhaustiveCases = listOf(
    Case(3329.0, 0.05, "Lowest Payment: 290"),
    Case(3329.0, 0.1, "Lowest Payment: 300"),
    Case(3329.0, 0.15, "Lowest Payment: 300"),
    Case(3329.0, 0.25, "Lowest Payment: 310"),
    Case(3329.0, 0.3, "Lowest Payment: 320"),
    Case(3329.0, 0.35, "Lowest Payment: 330"),
    Case(3329.0, 0.4, "Lowest Payment: 340"),
    Case(3329.0, 0.45, "Lowest Payment: 340"),
    Case(3329.0, 0.5, "Lowest Payment: 350"),
    Case(3926.0, 0.05, "Lowest Payment: 340"),
    Case(3926.0, 0.1, "Lowest Payment: 350"),
    Case(3926.0, 0.15, "Lowest Payment: 350"),
    Case(3926.0, 0.25, "Lowest Payment: 370"),
    Case(3926.0, 0.3, "Lowest Payment: 380"),
    Case(3926.0, 0.35, "Lowest Payment: 390"),
    Case(3926.0, 0.4, "Lowest Payment: 390"),
    Case(3926.0, 0.45, "Lowest Payment: 400"),
    Case(3926.0, 0.5, "Lowest Payment: 410"),
    Case(4773.0, 0.05, "Lowest Payment: 410"),
    Case(4773.0, 0.1, "Lowest Payment: 420"),
    Case(4773.0, 0.15, "Lowest Payment: 430"),
    Case(4773.0, 0.25, "Lowest Payment: 450"),
    Case(4773.0, 0.3, "Lowest Payment: 460"),
    Case(4773.0, 0.35, "Lowest Payment: 470"),
    Case(4773.0, 0.4, "Lowest Payment: 480"),
    Case(4773.0, 0.45, "Lowest Payment: 490"),
    Case(4773.0, 0.5, "Lowest Payment: 500")
)