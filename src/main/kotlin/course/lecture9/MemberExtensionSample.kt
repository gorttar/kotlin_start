package course.lecture9

import java.util.*
import kotlin.math.abs
import kotlin.math.min
import kotlin.math.sign

class Asset(val currency: Currency, val whole: Int, val cents: Int = 0) {
    init {
        require(whole * cents >= 0) { "Целая($whole) и дробная($cents) части валюты имеют разные знаки" }
        require(cents in -99..99) { "Дробная($cents) часть валюты за пределами интервала -99..99" }
    }

    override fun toString(): String = "${
        "-".takeIf { min(whole.sign, cents.sign) < 0 } ?: ""
    }${abs(whole)}.${abs(cents) / 10}${abs(cents) % 10} $currency"
}

val USD: Currency = Currency.getInstance("USD")
val RUB: Currency = Currency.getInstance("RUB")
val EUR: Currency = Currency.getInstance("EUR")

class CurrencyConverter {
    private val rates = mutableMapOf<Currency, Double>().also {
        it[USD] = 1.0
    }

    operator fun set(dividend: Currency, divisor: Currency, rate: Double) {
        check(rate > 0) { "Попытка присвоить курсу $dividend/$divisor не положительное значение $rate" }
        check(dividend != divisor) { "Попытка задать курс валюты $dividend по отношению к себе" }
        val divisorRate = rates[divisor]
        val dividendRate = rates[dividend]
        when {
            dividend == USD -> rates[divisor] = 1 / rate
            divisor == USD -> rates[dividend] = rate
            divisorRate != null -> rates[dividend] = rate * divisorRate
            dividendRate != null -> rates[divisor] = dividendRate / rate
            else -> error("Попытка присвоить значение курсу $dividend/$divisor при неизвестных курсах $dividend и $divisor")
        }
    }

    operator fun Currency.div(divisor: Currency): Double = rates[this]!! / rates[divisor]!!

    operator fun Double.times(currency: Currency): Asset =
        Asset(currency.also { rates[it]!! }, toInt(), (this * 100).toInt() % 100)

    operator fun Int.times(currency: Currency): Asset = toDouble() * currency

    infix fun Asset.convertTo(toCurrency: Currency): Asset =
        ((whole * 100 + cents) * (currency / toCurrency) / 100).times(toCurrency)
}

fun main(): Unit = CurrencyConverter().run {
    this[USD, RUB] = 77.03
    this[EUR, RUB] = 90.72
    println("Курс доллара к рублю: ${USD / RUB}")
    println("Курс евро к рублю: ${EUR / RUB}")
    println("Курс евро к доллару: ${EUR / USD}")
    val oneEUR = 1 * EUR
    println("$oneEUR = ${oneEUR convertTo RUB} = ${oneEUR convertTo USD}")
}