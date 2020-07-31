package course.ps2

class PayInAYearArgs(val balance: Double, val annualInterestRate: Double) {
    @Suppress("MemberVisibilityCanBePrivate")
    val str = "Test: balance=$balance, annualInterestRate=$annualInterestRate"

    override fun toString(): String = str
}

@Suppress("MemberVisibilityCanBePrivate")
class PayInAYearCase(balance: Double, annualInterestRate: Double, val expected: String) {
    val args: PayInAYearArgs = PayInAYearArgs(balance, annualInterestRate)

    @Suppress("unused")
    val repr = "PayInAYearCase($balance, $annualInterestRate, \"$expected\")"

    operator fun component1(): PayInAYearArgs = args
    operator fun component2(): String = expected
}