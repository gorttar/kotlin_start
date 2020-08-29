# Объектно-ориентированное программирование
До этого момента мы, в основном, создавали новые действия (глаголы) в Котлине: функции, лямбды, блоки кода и т.п.
Типы данных (существительные) при этом использовались из стандартной библиотеки.
В этой лекции будет рассказано, как создавать свои типы данных.

## Объекты
* в Котлине есть разные типы данных: 
```kotlin
1234
3.14159
"Hello"
listOf(1, 5, 7, 11, 13)
mapOf(
    "CA" to "California",
    "MA" to "Massachusetts"
)
```
* каждое значение - объект, а у каждого объекта есть:
  * тип
    * `1234` имеет тип `Int`
    * `"Hello"` имеет тип `String`
  * внутренние данные (примитивные и составные)
  * свойства
  * функции для работы с ним (методы)
## Объектно-ориентированное программирование (ООП)
* объекты - абстрактные данные, которые объединяют в себе:
  * внутреннее представление (приватные свойства)
  * интерфейс взаимодействия с объектом через публичные свойства и методы, которые определяют поведение, но скрывают реализацию
* есть возможность создавать объекты
* объекты, на которые не ссылается ни одна переменная, доступная из `main` функции, уничтожаются сборщиком мусора
## Стандартные объекты
* многие типы объектов вшиты в Котлин или определены в стандартной библиотеке
  * целые числа: `Byte, Short, Int, Long, BigInteger`
  * дробные числа с плавающей запятой: `Float, Double, BigDecimal`
  * символы `Char`
  * строки `String`
  * перечислимые (`Iterable`, `Collection`, `Set`, `List`), последовательности `Sequence`
  * "бросаемые", исключения и ошибки (`Throwable`, `Error`, `Exception` и их наследники)
  * родитель всех объектов `Any`
* программисту хотелось бы иметь возможность определять собственные типы объектов
## Создание и использование собственных объектов с помощью классов
* обозначим разницу между созданием класса и его использованием
* создание класса подразумевает
  * именование класса
  * декларирование его аттрибутов (свойств, методов)
  * например написание собственной реализации класса `List`
* использование класса подразумевает
  * создание новых объектов - экземпляров класса
  * выполнение операций над ними
  * например
```kotlin
val l = listOf(1, 2)
l.size
```
## Преимущества ООП
* объединение данных и операций над ними
* разработка по принципу: "разделяй и властвуй"
  * можно реализовывать и тестировать поведение каждого класса отдельно
  * позволяет писать модульный код, снижать сложность кода
* классы облегчают повторное использование кода
  * большинство библиотек, наряду с функциями, объявляют новые классы
  * у каждого класса свой контекст, поэтому нет коллизий в именовании функций
  * наследование позволяет наследникам уточнять и расширять поведение родительского класса
## Объявление класса
```kotlin
class Coordinate(val x: Double, val y: Double) : Any() { // аттрибуты класса объявляются здесь
}
```
Здесь
* `class` - ключевое слово, говорящее, что мы объявляем класс
* `Coordinate` - имя класса
* `val x: Int, val y: Int` - аттрибуты (свойства), объявленные в конструкторе класса
* ` : Any()` - родительский класс (в случае, если это `Any`, то можно не писать)
* `fun distance ...` - аттрибут (метод), объявленный в теле класса
## Что такое аттрибуты класса?
* свойства и методы, принадлежащие классу
  * свойства (аттрибуты - данные)
    * другие объекты, из которых состоит класс
    * например координата состоит из двух целых чисел
  * методы (аттрибуты - функции)
    * методы - функции, которые работают с объектами данного класса
    * например можно объявить функцию расстояния между двумя координатами, но она имеет сымысл только для координат
## Объявление способа создания объектов класса (конструкторы)
* чтобы создавать объекты класса надо объявить конструктор
```kotlin
class Coordinate(val x: Double, val y: Double) : Any() { // аттрибуты класса объявляются здесь
    constructor(x: Int, y: Int) : this(x.toDouble(), y.toDouble())
}
```
* в классе `Coordinate` объявлен основной конструктор, который инициализирует свойства `x` и `y`
* также в классе объявлен вторичный конструктор `constructor(x: Int, y: Int): this(x.toDouble(), y.toDouble())`, который вызывает основной (`: this(x.toDouble(), y.toDouble())`)
* для основного конструктора слово `constructor` можно не писать
## Создание объекта класса
```kotlin
fun main() {
    // создаёт новый объект класса Coordinate, передавая во вторичный конструктор аргументы 3 и 4
    val c = Coordinate(3, 4)
    val origin = Coordinate(0, 0)
    println(c.x) // аттрибуты объекта c доступны через точку
    println(origin.x)
}
```
* аттрибуты - данные объекта называются свойствами объекта 
* `c` указывает на контекст объекта
  * в рамках этого контекста мы присваиваем значения свойствам
  * `c.x` читается как "получить значение контекста `c` и найти в нём значение, присвоенное свойству `x`", то есть значение `x` данного конкретного объекта
## Что такое метод?
* функция, которая работает с объектами данного класса
* точка используется для доступа к любым аттрибутам объекта
  * свойствам объекта
  * методам объекта
  * для доступа к свойствам объекта изнутри его можно использовать ключевое слово `this`, оно означает ссылку на себя
## Объявление метода
```kotlin
class Coordinate(val x: Double, val y: Double) : Any() { // аттрибуты класса объявляются здесь
    // this ссылается на сам объект, other на другой объект
    fun distance(other: Coordinate): Double =
        sqrt((other.x - this.x).pow(2) + (other.y - this.y).pow(2))
}
```
## Как вызывать метод
```kotlin
fun main() {
    val c = Coordinate(3, 4)
    val origin = Coordinate(0, 0)
    // с - объект, для которого вызывается метод (приёмник или receiver)
    // distance - имя метода
    // origin - параметр метода
    println(c.distance(origin))
}
```
## Строковое представление объекта
```kotlin
fun main() {
    val c = Coordinate(3, 4)
    println(c)
}
```
Выведет `course.lecture9.Coordinate@8efb846`
* представление по умолчанию не информативно
* чтобы его поменять, надо переопределить метод `toString`
* при выполнении `println` Котлин вызывает для переданного объекта `toString` и печатает результат
* при этом программист сам определяет, как будет выглядеть строковое представление объекта, например можно сделать, чтобы координаты выводились в треугольных скобках
## Переопределение строкового представления
```kotlin
class Coordinate(val x: Double, val y: Double) : Any() { // аттрибуты класса объявляются здесь
    override fun toString(): String = "<$x, $y>"
}

fun main() {
    val c = Coordinate(3, 4)
    println(c)
}
```
Пример выше выведет `<3.0, 4.0>`
## Получение информации о типе объекта
* можно запросить информацию о типе объекта: код ниже выведет `class course.lecture9.Coordinate`
```kotlin
fun main() {
    val c = Coordinate(3, 4)
    println(c::class)
}
```
* можно использовать ключевое слово `is`, чтобы проверить, является ли объект объектом класса: код ниже выведет `true`
```kotlin
fun main() {
    val c = Coordinate(3, 4)
    println(c is Coordinate)
}
```
## Перегрузка операторов
* можно определить свое поведение для операторов `+`, `-`, `*`, `/` и других. Детально [тут](https://kotlinlang.ru/docs/reference/operator-overloading.html)
* операторы переопределяются следующим образом:
  * `operator fun plus(other: Coordinate)` -> `c + other`
  * `operator fun minus(other: Coordinate)` -> `c - other`
  * `operator fun times(other: Coordinate)` -> `c * other`
  * `operator fun div(other: Coordinate)` -> `c / other`
  * и другие, подробнее [тут](https://kotlinlang.ru/docs/reference/operator-overloading.html)
## Пример: натуральные дроби
* надо создать новый класс, определяющий натуральные дроби
* свойства класса - два целых числа:
  * numerator - числитель
  * denominator - знаменатель
* методы класса:
  * строковое представление
  * сложить, вычесть
  * преобразовать в `Double`
## Начальное объявление:
```kotlin
class Fraction(val numerator: Int, val denominator: Int) {
    override fun toString(): String = "$numerator / $denominator"
}

fun main() {
    val oneHalf = Fraction(1, 2)
    val twoThirds = Fraction(2, 3)
    println(oneHalf)
    println(twoThirds)
}
```
Выведет
```
1 / 2
2 / 3
```
## Добавляем операторы
```kotlin
class Fraction(val numerator: Int, val denominator: Int) {
    override fun toString(): String = "$numerator / $denominator"
    operator fun plus(other: Fraction): Fraction =
        Fraction(numerator * other.denominator + other.numerator * denominator, denominator * other.denominator)

    operator fun minus(other: Fraction): Fraction =
        Fraction(numerator * other.denominator - other.numerator * denominator, denominator * other.denominator)
}

fun main() {
    val oneHalf = Fraction(1, 2)
    val twoThirds = Fraction(2, 3)
    println(oneHalf + twoThirds)
    println(twoThirds - oneHalf)
}
```
Выведет
```
7 / 6
1 / 6
```
## Добавляем конвертацию в `Double`
```kotlin
class Fraction(val numerator: Int, val denominator: Int) {
    override fun toString(): String = "$numerator / $denominator"
    operator fun plus(other: Fraction): Fraction =
        Fraction(numerator * other.denominator + other.numerator * denominator, denominator * other.denominator)

    operator fun minus(other: Fraction): Fraction =
        Fraction(numerator * other.denominator - other.numerator * denominator, denominator * other.denominator)

    fun toDouble(): Double = numerator / denominator.toDouble()
}

fun main() {
    val oneHalf = Fraction(1, 2)
    val twoThirds = Fraction(2, 3)
    println((oneHalf + twoThirds).toDouble())
}
```
Выведет
```
1.1666666666666667
```
