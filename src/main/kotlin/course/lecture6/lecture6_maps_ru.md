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
* `map` - создаёт новый объект, элементы которого - результаты применения `transform` к элементам исходного объекта. Пример [тут](mapFunctionSample.kt)
  * `fun <T, R> Iterable<T>.map(transform: (T) -> R): List<R>`
  * `fun <R> CharSequence.map(transform: (Char) -> R): List<R>`
  * `fun <T, R> Sequence<T>.map(transform: (T) -> R): Sequence<R> // обрати внимание, что результат не List, а Sequence`
* `mapIndexed` - создаёт новый объект, элементы которого - результаты применения `transform` к элементам исходного объекта и их индексам. Пример [тут](mapIndexedFunctionSample.kt)
  * `fun <R> CharSequence.mapIndexed(transform: (index: Int, Char) -> R): List<R>`
  * `fun <T, R> Iterable<T>.mapIndexed(transform: (index: Int, T) -> R): List<R>`
  * `fun <T, R> Sequence<T>.mapIndexed(transform: (index: Int, T) -> R): Sequence<R> // обрати внимание, что результат не List, а Sequence`
* `forEach` - для всех элементов объекта выполняет функцию `action`. Похоже на `for`. Пример [тут](forEachSample.kt)
  * `fun CharSequence.forEach(action: (Char) -> Unit): Unit`
  * `fun <T> Iterable<T>.forEach(action: (T) -> Unit): Unit`
  * `fun <T> Sequence<T>.forEach(action: (T) -> Unit): Unit`
* `forEachIndexed` - для всех элементов объекта и их индексов выполняет функцию `action`. Пример [тут](forEachIndexedSample.kt)
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
* `takeWhile` - создаёт новый объект, в который помещает элементы исходного пока для них выполняется `predicate`. Пример [тут](takeWhileSample.kt)
  * `fun String.takeWhile(predicate: (Char) -> Boolean): String`
  * `fun <T> Iterable<T>.takeWhile(predicate: (T) -> Boolean): List<T>`
  * `fun <T> Sequence<T>.takeWhile(predicate: (T) -> Boolean): Sequence<T> // обрати внимание, что результат не List, а Sequence`
* `takeLastWhile` - создаёт новый объект, в который помещает элементы исходного с конца пока для них выполняется `predicate`. Не для Sequence
* `dropWhile` - создаёт новый объект, в который помещает элементы исходного, начиная с первого, для которого не выполняется `predicate`.
* `dropLastWhile` - не для Sequence
* `filter` - создаёт новый объект, из элементов сходного для которых выполняется `predicate`. Пример [тут](filterSample.kt)
  * `fun String.filter(predicate: (Char) -> Boolean): String`
  * `fun <T> Iterable<T>.filter(predicate: (T) -> Boolean): List<T>`
  * `fun <T> Sequence<T>.filter(predicate: (T) -> Boolean): Sequence<T> // обрати внимание, что результат не List, а Sequence`
* `filterInedxed` - создаёт новый объект, из элементов сходного для которых выполняется `predicate`. Пример [тут](filterIndexedSample.kt)
  * `fun String.filter(predicate: (index:Int, Char) -> Boolean): String`
  * `fun <T> Iterable<T>.filter(predicate: (index:Int, T) -> Boolean): List<T>`
  * `fun <T> Sequence<T>.filter(predicate: (index:Int, T) -> Boolean): Sequence<T> // обрати внимание, что результат не List, а Sequence`
* `flatten` - из вложенного объекта (список списков, последовательность последовательностей) создаёт новый объект, "сплющивая" один уровень вложенности (конкатенируя списки/последовательности из исходного). Пример [тут](flattenSample.kt)
  * `fun <T> Iterable<Iterable<T>>.flatten(): List<T>`
  * `fun <T> Sequence<Sequence<T>>.flatten(): Sequence<T> // обрати внимание, что результат не List, а Sequence`
* `flatMap` - создаёт новый объект, конкатенируя результаты применения `transform` к элементам исходного объекта. Пример [тут](flatMapSample.kt)
  * `fun <R> CharSequence.flatMap(transform: (Char) -> Iterable<R>): List<R>`
  * `fun <T, R> Iterable<T>.flatMap(transform: (T) -> Iterable<R>): List<R>`
  * `fun <T, R> Sequence<T>.flatMap(transform: (T) -> Sequence<R>): Sequence<R> // обрати внимание, что результат не List, а Sequence`
## Карты (Map)
### Зачем нужны карты?
Допустим, нам надо хранить следующую информацию о студентах:  
`name: String`  
`grade: String // у американцев оценки буквенные от A+ до F-`  
* можно хранить эту информацию в двух списках:  
names = [**"Ana"**, "John", "Denise", "Katy"]  
grade = [**"B"**, "A+", "A", "A"]  
* каждый список должен иметь одинаковый размер
* информация по одному студенту хранится во всех списках под одним и тем же индексом
* разные индексы обозначают разных студентов
#### Как получать и обновлять информацию о студенте из такого хранилища?
```kotlin
fun getGrade(name: String, names: List<String>, grades: List<String>): String {
    val index = names.indexOf(name)
    return grades[index]
}
```
* сложно, особенно если списков станет больше
* нужно поддерживать много списков и передавать их параметрами
* нужно не забыть обновить информацию во всех списках при изменениях
#### Более удобно использовать карты
* есть возможность получать элементы по произвольному индексу (не обязательно числу)
* можно обойтись одной структурой вместо двух списков  
**Список:**

Индекс - целое число, начиная с нуля подряд|Элемент
---|---
0|Элемент 1
1|Элемент 2
2|Элемент 3
...|...
**Карта:**

Ключ - произвольный объект, не обязательно число|Значение
---|---
Ключ 1|Значение 1
Ключ 2|Значение 2
Ключ 3|Значение 3
...|...
#### Операции с картами. Пример [тут](mapOperations.kt)
`val nameToGrade = mapOf("Ana" to "B", "John" to "A+", "Denise" to "A", "Katy" to "A")`
* размер  
`nameToGrade.size`
* получение значения по ключу  
`nameToGrade["Ana"] => "B"`  
`nameToGrade["Alex"] => null`
* форсированное получение значения по ключу  
`nameToGrade.getValue("Ana") => "B"`  
`nameToGrade.getValue("Alex") => упадёт с ошибкой: "Key Alex is missing in the map."`
* получение значения по ключу или значения по умолчанию  
`nameToGrade.getOrDefault("Ana", "absent") => "B"`  
`nameToGrade.getOrDefault("Alex", "absent") => "absent"`
* проверка, что ключ есть в карте  
`"Ana" in nameToGrade => true`  
`"Alex" in nameToGrade => false`
#### Операции с изменяемыми картами. Пример [тут](mutableMapOperations.kt)
`val nameToGrade = mutableMapOf("Ana" to "B", "John" to "A+", "Denise" to "A", "Katy" to "A")`
* добавление/изменение записи  
`nameToGrade["Alex"] = "A-"`
* удаление записи  
`nameToGrade.remove("Alex")`
#### Свойства карт
* значения
  * любого типа
  * могут дублироваться в пределах карты
  * могут быть списками, массивами и даже другими картами
* ключи
  * должны быть уникальными (не должно быть двух равных)
  * желательно, чтобы они были неизменяемыми, если изменяемые, то изменения не должны менять результаты **equals**/**hashCode**
  * желательно не использовать числа с плавающей запятой (Folat, Double, BigDecimal), так как их сложно проверять на равенство (два числа могут отличаться очень мало, но при этом не быть равными)
  * обычно не сохраняют порядок добавления ключей (исключение Linked*Map)
#### Мемоизация на примере чисел Фибоначчи
Неэффективное решение "в лоб"
```kotlin
fun fib(n: Int): Int = when(n) {
    1 -> 1
    2 -> 2
    else -> fib(n - 1) + fib(n - 2)
}
```
* два базовых случая
* два рекурсивных вызова
* работает медленно, так как с ростом n на 1 количество рекурсивных вызовов растёт примерно вдвое. Например для n = 40 вычисления уже занимают несколько секунд, а для 50 ответа вообще не дождёшься
* проблема в том, что для одного и того же n значение вычисляется много раз
* может быть как-то запоминать уже вычисленные значения?

Фибоначчи с запоминанием в карте (мемоизация)
```kotlin
fun fibEfficient(n: Int): BigInteger {
    fun fibMemo(n: Int, map: MutableMap<Int, BigInteger>): BigInteger = map[n] ?: when (n) {
        1 -> BigInteger.ONE
        2 -> 2.toBigInteger()
        else -> fibMemo(n - 1, map) + fibMemo(n - 2, map)
    }.also { map[n] = it }
    return fibMemo(n, mutableMapOf())
}
```
* сначала смотрим, есть ли уже вычисленное значение для данного n в карте
* добавляем значения в словарь по мере вычислений
#### Глобальные переменные
* могут быть опасными в использовании
  * доступны везде и неявно передаются между вызвами функций
  * позволяют сторонние изменения, которые влияют на вычисления
* но при этом могут быть полезны для трекинга происходящего в функции
* пример: подсчёт количества вызовов при вычислении рекурсивных функций
```kotlin
var numFibCalls = 0

fun fibTracked(n: Int): BigInteger = when (n) {
    1 -> BigInteger.ONE
    2 -> 2.toBigInteger()
    else -> fibTracked(n - 1) + fibTracked(n - 2)
}.also { numFibCalls++ }

var numFibEffCalls = 0

fun fibEffTracked(n: Int): BigInteger {
    fun fibMemo(n: Int, map: MutableMap<Int, BigInteger>): BigInteger = (map[n] ?: when (n) {
        1 -> BigInteger.ONE
        2 -> 2.toBigInteger()
        else -> fibMemo(n - 1, map) + fibMemo(n - 2, map)
    }.also { map[n] = it }).also { numFibEffCalls++ }

    return fibMemo(n, mutableMapOf())
}
```
