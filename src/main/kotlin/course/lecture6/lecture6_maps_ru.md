## Функции как объекты, карты (словари, ассоциативные массивы)
### Функции как объекты
* функции в Котлин являются [объектами первого класса](https://ru.wikipedia.org/wiki/%D0%9E%D0%B1%D1%8A%D0%B5%D0%BA%D1%82_%D0%BF%D0%B5%D1%80%D0%B2%D0%BE%D0%B3%D0%BE_%D0%BA%D0%BB%D0%B0%D1%81%D1%81%D0%B0)
  * у них есть тип
  * они могут быть элементами какой-нибудь структуры данных, например списка
  * могут быть аргументами других функций
  * могут быть возвращены из функции, как результат
  * могут быть созданы во время исполнения программы
* использование функций в качестве аргумента другой функции имеет много применений в Котлин, особенно для коллекций (map, filter, takeWhile, forEach, reduce, fold и т.д.)
  * функции, принимающие другие в качестве аргумента и/или возвращающие, как результат, называются функциями высших порядков (ФВП), по-английски higher order functions (HOF)
#### Пример: [transform и applyEachTo](transform.kt)
```kotlin
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
```
### Общие операции для String, Iterable (List, Set, Range), Sequence
* размер
  * `"".length`
  * `listOf(1).size`
* `map` - создаёт новый объект, элементы которого - результаты применения `transform` к элементам исходного объекта
  * `fun <T, R> Iterable<T>.map(transform: (T) -> R): List<R>`
  * `fun <R> CharSequence.map(transform: (Char) -> R): List<R>`
  * `fun <T, R> Sequence<T>.map(transform: (T) -> R): Sequence<R> // обрати внимание, что результат не List, а Sequence`
* `mapIndexed` - создаёт новый объект, элементы которого - результаты применения `transform` к элементам исходного объекта и их индексам
  * `fun <R> CharSequence.mapIndexed(transform: (index: Int, Char) -> R): List<R>`
  * `fun <T, R> Iterable<T>.mapIndexed(transform: (index: Int, T) -> R): List<R>`
  * `fun <T, R> Sequence<T>.mapIndexed(transform: (index: Int, T) -> R): Sequence<R> // обрати внимание, что результат не List, а Sequence`
* `forEach` - для всех элементов объекта выполняет функцию `action`. Похоже на `for`
  * `fun CharSequence.forEach(action: (Char) -> Unit): Unit`
  * `fun <T> Iterable<T>.forEach(action: (T) -> Unit): Unit`
  * `fun <T> Sequence<T>.forEach(action: (T) -> Unit): Unit`
* `forEachIndexed` - для всех элементов объекта и их индексов выполняет функцию `action`.
  * `fun CharSequence.forEachIndexed(action: (index: Int, Char) -> Unit): Unit`
  * `fun <T> Iterable<T>.forEachIndexed(action: (index: Int, T) -> Unit): Unit`
  * `fun <T> Sequence<T>.forEachIndexed(action: (index: Int, T) -> Unit): Unit`
* `take` - создаёт новый объект, в который помещает первые `n` элементов исходного. Если элементов в исходном меньше - помещает все 
  * `fun String.take(n: Int): String`
  * `fun <T> Iterable<T>.take(n: Int): List<T>`
  * `fun <T> Sequence<T>.take(n: Int): Sequence<T>`
* `takeLast` - создаёт новый объект, в который помещает последние `n` элементов исходного. Не для Sequence
* `drop` - создаёт новый объект, в который помещает все элементы исходного кроме первых `n`. Если элементов в исходном меньше - пустой объект
* `dropLast` - создаёт новый объект, в который помещает все элементы исходного кроме последних `n`. не для Sequence
* `takeWhile` - создаёт новый объект, в который помещает элементы исходного пока для них выполняется `predicate`.
  * `fun String.takeWhile(predicate: (Char) -> Boolean): String`
  * `fun <T> Iterable<T>.takeWhile(predicate: (T) -> Boolean): List<T>`
  * `fun <T> Sequence<T>.takeWhile(predicate: (T) -> Boolean): Sequence<T> // обрати внимание, что результат не List, а Sequence`
* `takeUnless` - создаёт новый объект, в который помещает элементы исходного пока для них не выполняется `predicate`.
* `takeLastWhile` - создаёт новый объект, в который помещает элементы исходного с конца пока для них выполняется `predicate`. Не для Sequence
* `dropWhile` - создаёт новый объект, в который помещает элементы исходного, начиная с первого, для которого не выполняется `predicate`.
* `dropLastWhile` - не для Sequence
* `filter` - создаёт новый объект, из элементов сходного для которых выполняется `predicate`.
  * `fun String.filter(predicate: (Char) -> Boolean): String`
  * `fun <T> Iterable<T>.filter(predicate: (T) -> Boolean): List<T>`
  * `fun <T> Sequence<T>.filter(predicate: (T) -> Boolean): Sequence<T> // обрати внимание, что результат не List, а Sequence`
* дописать остальные
## Карты (Map)
