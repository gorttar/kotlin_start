package course.ps2

import org.gorttar.test.FAIL
import kotlin.math.pow
import kotlin.math.roundToLong

/**
 * Напишите программу расчета баланса кредитной карты после года использования, для случая, когда
 * клиент вносит только минимальные ежемесячные платежи, используя следующие переменные и их значения:
 * [balance]            сумма долга по кредитной карте
 * [annualInterestRate] годовая ставка в виде доли
 * *    (если ставка 18%, то [annualInterestRate] будет равно 0.18)
 * [monthlyPaymentRate] доля минимального ежемесячного платежа
 * *    (если процент минимального платежа 2%, то [monthlyPaymentRate] будет равно 0.02)
 *
 * Для каждого месяца необходимо рассчитать сумму ежемесячного платежа и оставшегося долга. Результат функции - строка,
 * содержащая остаток на счету на конец 12 месяца. Обратите внимание, что у суммы долга и месячного платежа не бывает
 * дробных копеек, поэтому количество знаков после запятой в ответе не должно превышать 2,
 * результат должен выглядеть так:
 * *    "Remaining balance: 813.41"
 * а не так:
 * *    "Remaining balance: 813.4141998135"
 * Для округления до нужного числа знаков после запятой можно воспользоваться функцией [round].
 * Например 1.2345.round(1) даст 1.2
 *
 * Ответ должен содержать одно число, оставшийся баланс карты на конец года в формате:
 * *    "Remaining balance: 4784.0"
 *
 * Необходимая для расчета математика:
 * *    Месячная ставка = (годовая ставка) / 12.0
 * *    Минимальный ежемесячный платеж = (доля минимального ежемесячного платежа) * (предыдущая сумма долга)
 * *    Остаток долга = (предыдущая сумма долга) - (минимальный ежемесячный платеж)
 * *    Сумма долга = (остаток долга) + (месячная ставка * остаток долга)
 *
 * Ниже прилагаются примеры тестов.
 * *    Тест 1
 * *        Входные параметры:
 * *            [balance] = 42.0
 * *            [annualInterestRate] = 0.2
 * *            [monthlyPaymentRate] = 0.04
 * *        Ожидаемый результат: "Remaining balance: 31.38"
 * *        Примечания: чтобы быть уверенным в правильности вычислений, ниже приведена таблица сумм долга по месяцам.
 * *            Month 1 Remaining balance: 40.99
 * *            Month 2 Remaining balance: 40.01
 * *            Month 3 Remaining balance: 39.05
 * *            Month 4 Remaining balance: 38.11
 * *            Month 5 Remaining balance: 37.2
 * *            Month 6 Remaining balance: 36.31
 * *            Month 7 Remaining balance: 35.44
 * *            Month 8 Remaining balance: 34.59
 * *            Month 9 Remaining balance: 33.76
 * *            Month 10 Remaining balance: 32.95
 * *            Month 11 Remaining balance: 32.16
 * *            Month 12 Remaining balance: 31.38
 *
 * *    Тест 2
 * *        Входные параметры:
 * *            [balance] = 484.0
 * *            [annualInterestRate] = 0.2
 * *            [monthlyPaymentRate] = 0.04
 * *        Ожидаемый результат: "Remaining balance: 361.61"
 *
 * Для проверки задания запускаешь main из файла src/test/kotlin/course/ps2/Problem1Test.kt и смотришь вывод.
 * Он должен быть зелёным, если всё верно
 */
fun payMinimalAYear(balance: Double, annualInterestRate: Double, monthlyPaymentRate: Double): String {
    FAIL
}

fun Double.round(decimals: Int): Double = 10.0.pow(decimals).let {
    times(it).roundToLong() / it
}