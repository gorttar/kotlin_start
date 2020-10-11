package course.lecture9

/** преобразование элементов [xs] из [Int] в [Double] */
fun toDoubles(xs: Iterable<Int>): Iterable<Double> = xs.map(Int::toDouble)

/** произведение элементов перечислимого [xs] */
fun product(xs: Iterable<Double>): Double = xs.fold(1.0, Double::times)

/** преобразование элементов [this] из [Int] в [Double], объявленное как функция-расширение */
fun Iterable<Int>.toDoublesExt(): Iterable<Double> = this.map(Int::toDouble)

/** произведение элементов перечислимого [this], объявленное как функция-расширение */
fun Iterable<Double>.productExt(): Double = this.fold(1.0, Double::times)

/** преобразование элементов [this] из [Int] в [Double], объявленное как свойство-расширение */
val Iterable<Int>.toDoublesExt: Iterable<Double> get() = this.map(Int::toDouble)

/** произведение элементов перечислимого [this], объявленное как свойство-расширение */
val Iterable<Double>.productExt: Double get() = this.fold(1.0, Double::times)

fun main() {
    val xs = (1..5)

    /** без расширений у нас получается плохо читаемая конструкция со вложенными скобками и обратным порядком функций,
     * если читать слева направо:
     * читаем product, toDoubles, при этом сначала выполняется toDoubles, а затем product
     */
    val product = product(toDoubles(xs))
    println(product)

    /** с функциями-расширениями из выражения исчезли вложенные скобки и порядок вызовов совпадает с порядком
     * чтения слева направо
     */
    val productExtFun = xs.toDoublesExt().productExt()
    println(productExtFun)

    /** со свойствами-расширениями из выражения исчезли вложенные скобки и порядок вызовов совпадает с порядком
     * чтения слева направо
     */
    val productExtVal = xs.toDoublesExt.productExt
    println(productExtVal)
}