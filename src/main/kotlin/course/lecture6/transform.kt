package course.lecture6

import kotlin.math.abs
import kotlin.math.absoluteValue
import kotlin.reflect.KCallable

/**
 * трансформирует список [this] в новый список по следующему правилу:
 * для каждого элемента t, исходного списка, помещает элемент равный transformer(t) в результирующий список
 */
fun <T, R> Iterable<T>.transform(transformer: (T) -> R): List<R> {
    val result = arrayListOf<R>()
    for (t in this) result += transformer(t)
    return result
}

/**
 * на основании списка функций [this] формирует новый список по следующему правилу:
 * для каждой функции f, исходного списка, помещает элемент равный f(t) в результирующий список
 */
fun <T, R> Iterable<(T) -> R>.applyEachTo(t: T): List<R> {
    val result = arrayListOf<R>()
    for (f in this) result += f(t)
    return result
}

private fun toInt(x: Double): Int = x.toInt()

private fun fact(x: Double): Int = (1..x.toInt().absoluteValue).fold(1) { acc, x -> acc * x }

private fun fib(x: Double): Int = generateSequence(1 to 1) { (acc, prevAcc) -> acc + prevAcc to acc }
    .drop(x.toInt().absoluteValue)
    .first()
    .second

fun main() {
    val xs: List<Double> = listOf(1.0, -2.0, 3.4, 5.7)
    val fs: List<(Double) -> Any> = listOf(::abs, ::toInt, ::fact, ::fib)
    val fNames = fs.map { it.refName }.toString()

    println(
        """
        Исходный список: xs = $xs
        Список функций: fs = $fNames
        
        Результаты работы ФВП transform:
        """.trimIndent()
    )

    for (f in fs) println("xs.transform(${f.refName}) = ${xs.transform(f)}")

    println("\nРезультаты работы ФВП applyEachTo:")

    for (x in xs) println("${fNames}.applyEachTo($x) = ${fs.applyEachTo(x)}")
}

private val Function<*>.refName: String get() = "::${(this as? KCallable<*>)?.name ?: toString()}"