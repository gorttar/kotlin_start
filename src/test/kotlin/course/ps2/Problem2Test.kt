package course.ps2

import lib.test.selfNamedPassTo
import lib.test.shouldBeEqualTo

/**
 * запусти, чтобы протестировать функцию [payInAYearExhaustive]
 */
fun main() = (sequenceOf(
        PayInAYearCase(3329.0, 0.2, "Lowest Payment: 310"),
        PayInAYearCase(3926.0, 0.2, "Lowest Payment: 360"),
        PayInAYearCase(4773.0, 0.2, "Lowest Payment: 440")
) + exhaustiveCases.shuffled().asSequence().take(10)).forEach { (args, expected) ->
    args selfNamedPassTo { payInAYearExhaustive(balance, annualInterestRate) } shouldBeEqualTo expected
}

val exhaustiveCases = listOf(
        PayInAYearCase(3329.0, 0.05, "Lowest Payment: 290"),
        PayInAYearCase(3329.0, 0.1, "Lowest Payment: 300"),
        PayInAYearCase(3329.0, 0.15, "Lowest Payment: 300"),
        PayInAYearCase(3329.0, 0.25, "Lowest Payment: 310"),
        PayInAYearCase(3329.0, 0.3, "Lowest Payment: 320"),
        PayInAYearCase(3329.0, 0.35, "Lowest Payment: 330"),
        PayInAYearCase(3329.0, 0.4, "Lowest Payment: 340"),
        PayInAYearCase(3329.0, 0.45, "Lowest Payment: 340"),
        PayInAYearCase(3329.0, 0.5, "Lowest Payment: 350"),
        PayInAYearCase(3926.0, 0.05, "Lowest Payment: 340"),
        PayInAYearCase(3926.0, 0.1, "Lowest Payment: 350"),
        PayInAYearCase(3926.0, 0.15, "Lowest Payment: 350"),
        PayInAYearCase(3926.0, 0.25, "Lowest Payment: 370"),
        PayInAYearCase(3926.0, 0.3, "Lowest Payment: 380"),
        PayInAYearCase(3926.0, 0.35, "Lowest Payment: 390"),
        PayInAYearCase(3926.0, 0.4, "Lowest Payment: 390"),
        PayInAYearCase(3926.0, 0.45, "Lowest Payment: 400"),
        PayInAYearCase(3926.0, 0.5, "Lowest Payment: 410"),
        PayInAYearCase(4773.0, 0.05, "Lowest Payment: 410"),
        PayInAYearCase(4773.0, 0.1, "Lowest Payment: 420"),
        PayInAYearCase(4773.0, 0.15, "Lowest Payment: 430"),
        PayInAYearCase(4773.0, 0.25, "Lowest Payment: 450"),
        PayInAYearCase(4773.0, 0.3, "Lowest Payment: 460"),
        PayInAYearCase(4773.0, 0.35, "Lowest Payment: 470"),
        PayInAYearCase(4773.0, 0.4, "Lowest Payment: 480"),
        PayInAYearCase(4773.0, 0.45, "Lowest Payment: 490"),
        PayInAYearCase(4773.0, 0.5, "Lowest Payment: 500")
)