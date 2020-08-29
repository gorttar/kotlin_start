package course.lecture9

import kotlin.math.pow
import kotlin.math.sqrt

class Coordinate(val x: Double, val y: Double) : Any() { // аттрибуты класса объявляются здесь
    constructor(x: Int, y: Int) : this(x.toDouble(), y.toDouble())

    // this ссылается на сам объект, other на другой объект
    fun distance(other: Coordinate): Double =
        sqrt((other.x - this.x).pow(2) + (other.y - this.y).pow(2))

    // ключевое слово override говорит о том, что мы переопределяем метод, существующий у родителя (Any)
    override fun toString(): String = "<$x, $y>"
}

fun main() {
    // создаёт новый объект класса Coordinate, передавая во вторичный конструктор аргументы 3 и 4
    val c = Coordinate(3, 4)
    val origin = Coordinate(0, 0)
    println(c.x) // аттрибуты объекта c доступны через точку
    println(origin.x)
    // с - объект, для которого вызывается метод (приёмник или receiver)
    // distance - имя метода
    // origin - параметр метода
    println(c.distance(origin))
    println(c)
    println(c::class)
    println(c is Coordinate)
}