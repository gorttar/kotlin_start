package course.lecture4.imports.circle

import lib.output.BLK
import lib.output.G

val pi: Double
    get() {
        println("Была использована переменная$G course.lecture4.imports.circle.pi$BLK")
        return 3.14159
    }

fun area(radius: Double): Double {
    println("Была вызвана функция$G course.lecture4.imports.circle.area$BLK")
    return pi * radius * radius
}

fun circumference(radius: Double): Double {
    println("Была вызвана функция$G course.lecture4.imports.circle.circumference$BLK")
    return 2 * pi * radius
}

object Circle {
    val pi: Double
        get() {
            println("Была использована переменная$G course.lecture4.imports.circle.Circle.pi$BLK")
            return 3.14159265
        }

    fun area(radius: Double): Double {
        println("Была вызвана функция$G course.lecture4.imports.circle.Circle.area$BLK")
        return pi * radius * radius
    }

    fun circumference(radius: Double): Double {
        println("Была вызвана функция$G course.lecture4.imports.circle.Circle.circumference$BLK")
        return 2 * pi * radius
    }
}