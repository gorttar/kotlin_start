package course.ps2

import assertk.assertThat
import assertk.assertions.isBetween
import assertk.assertions.isLessThan
import course.currentSolutionLanguage
import course.languageDependent
import org.gorttar.test.dynamicTests
import org.junit.jupiter.api.TestFactory
import org.junit.jupiter.api.assertTimeoutPreemptively
import java.time.Duration.ofSeconds

private val solution = languageDependent(::payInAYearBisection, PayInAYearBisection::payInAYearBisection)

class Problem3Test {
    /**
     * запусти, чтобы протестировать функцию [payInAYearBisection] или [PayInAYearBisection.payInAYearBisection]
     * в зависимости от языка, присвоенного константе [currentSolutionLanguage]
     */
    @TestFactory
    fun payInAYearBisectionTest() = (sequenceOf(
        Case(320000.0, 0.2, "Lowest Payment: 29157.1"),
        Case(999999.0, 0.18, "Lowest Payment: 90325.03")
    ) + bisectionCases.shuffled().asSequence().take(10)).dynamicTests {
        assertTimeoutPreemptively(ofSeconds(1)) {
            assertThat(solution(balance, annualInterestRate)).given { actual ->
                assertThat((actual.replace("^.*\\.".toRegex(), "").length)).isLessThan(2)
                assertThat(actual.payment()).isBetween(expected.payment() - 0.02, expected.payment() + 0.02)
            }
        }
    }
}

private fun String.payment() = removePrefix("Lowest Payment: ").toDouble()
val bisectionCases = listOf(
    Case(320000.0, 0.05, "Lowest Payment: 27280.73"),
    Case(320000.0, 0.1, "Lowest Payment: 27900.58"),
    Case(320000.0, 0.15, "Lowest Payment: 28526.08"),
    Case(320000.0, 0.25, "Lowest Payment: 29793.45"),
    Case(320000.0, 0.3, "Lowest Payment: 30435.0"),
    Case(320000.0, 0.35, "Lowest Payment: 31081.61"),
    Case(320000.0, 0.4, "Lowest Payment: 31733.11"),
    Case(320000.0, 0.45, "Lowest Payment: 32389.34"),
    Case(320000.0, 0.5, "Lowest Payment: 33050.15"),
    Case(500000.0, 0.05, "Lowest Payment: 42626.13"),
    Case(500000.0, 0.1, "Lowest Payment: 43594.65"),
    Case(500000.0, 0.15, "Lowest Payment: 44572.0"),
    Case(500000.0, 0.2, "Lowest Payment: 45557.95"),
    Case(500000.0, 0.25, "Lowest Payment: 46552.27"),
    Case(500000.0, 0.3, "Lowest Payment: 47554.69"),
    Case(500000.0, 0.35, "Lowest Payment: 48565.02"),
    Case(500000.0, 0.4, "Lowest Payment: 49582.97"),
    Case(500000.0, 0.45, "Lowest Payment: 50608.33"),
    Case(500000.0, 0.5, "Lowest Payment: 51640.85"),
    Case(999999.0, 0.05, "Lowest Payment: 85252.17"),
    Case(999999.0, 0.1, "Lowest Payment: 87189.23"),
    Case(999999.0, 0.15, "Lowest Payment: 89143.92"),
    Case(999999.0, 0.2, "Lowest Payment: 91115.82"),
    Case(999999.0, 0.25, "Lowest Payment: 93104.44"),
    Case(999999.0, 0.3, "Lowest Payment: 95109.3"),
    Case(999999.0, 0.35, "Lowest Payment: 97129.94"),
    Case(999999.0, 0.4, "Lowest Payment: 99165.86"),
    Case(999999.0, 0.45, "Lowest Payment: 101216.58"),
    Case(999999.0, 0.5, "Lowest Payment: 103281.6")
)