package course.ps2

import lib.test.orElse
import lib.test.selfNamedPassTo
import lib.test.shouldBe
import lib.test.withTimeLimit

/**
 * запусти, чтобы протестировать функцию [payInAYearBisection]
 */
fun main() = (sequenceOf(
        PayInAYearCase(320000.0, 0.2, "Lowest Payment: 29157.1"),
        PayInAYearCase(999999.0, 0.18, "Lowest Payment: 90325.03")
) + bisectionCases.shuffled().asSequence().take(10)).forEach { (args, expected) ->
    args selfNamedPassTo { payInAYearBisection(balance, annualInterestRate) } withTimeLimit 10 shouldBe { actual ->
        val expectedRange = (expected.payment() - 0.02).round(2)..(expected.payment() + 0.02).round(2)
        (actual.replace("^.*\\.".toRegex(), "").length <= 2 && actual.payment() in expectedRange) orElse
                "Lowest Payment in $expectedRange"
    }
}

private fun String.payment() = removePrefix("Lowest Payment: ").toDouble()
val bisectionCases = listOf(
        PayInAYearCase(320000.0, 0.05, "Lowest Payment: 27280.73"),
        PayInAYearCase(320000.0, 0.1, "Lowest Payment: 27900.58"),
        PayInAYearCase(320000.0, 0.15, "Lowest Payment: 28526.08"),
        PayInAYearCase(320000.0, 0.25, "Lowest Payment: 29793.45"),
        PayInAYearCase(320000.0, 0.3, "Lowest Payment: 30435.0"),
        PayInAYearCase(320000.0, 0.35, "Lowest Payment: 31081.61"),
        PayInAYearCase(320000.0, 0.4, "Lowest Payment: 31733.11"),
        PayInAYearCase(320000.0, 0.45, "Lowest Payment: 32389.34"),
        PayInAYearCase(320000.0, 0.5, "Lowest Payment: 33050.15"),
        PayInAYearCase(500000.0, 0.05, "Lowest Payment: 42626.13"),
        PayInAYearCase(500000.0, 0.1, "Lowest Payment: 43594.65"),
        PayInAYearCase(500000.0, 0.15, "Lowest Payment: 44572.0"),
        PayInAYearCase(500000.0, 0.2, "Lowest Payment: 45557.95"),
        PayInAYearCase(500000.0, 0.25, "Lowest Payment: 46552.27"),
        PayInAYearCase(500000.0, 0.3, "Lowest Payment: 47554.69"),
        PayInAYearCase(500000.0, 0.35, "Lowest Payment: 48565.02"),
        PayInAYearCase(500000.0, 0.4, "Lowest Payment: 49582.97"),
        PayInAYearCase(500000.0, 0.45, "Lowest Payment: 50608.33"),
        PayInAYearCase(500000.0, 0.5, "Lowest Payment: 51640.85"),
        PayInAYearCase(999999.0, 0.05, "Lowest Payment: 85252.17"),
        PayInAYearCase(999999.0, 0.1, "Lowest Payment: 87189.23"),
        PayInAYearCase(999999.0, 0.15, "Lowest Payment: 89143.92"),
        PayInAYearCase(999999.0, 0.2, "Lowest Payment: 91115.82"),
        PayInAYearCase(999999.0, 0.25, "Lowest Payment: 93104.44"),
        PayInAYearCase(999999.0, 0.3, "Lowest Payment: 95109.3"),
        PayInAYearCase(999999.0, 0.35, "Lowest Payment: 97129.94"),
        PayInAYearCase(999999.0, 0.4, "Lowest Payment: 99165.86"),
        PayInAYearCase(999999.0, 0.45, "Lowest Payment: 101216.58"),
        PayInAYearCase(999999.0, 0.5, "Lowest Payment: 103281.6")
)