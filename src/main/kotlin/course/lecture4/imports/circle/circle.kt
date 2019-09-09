package course.lecture4.imports.circle

import lib.test.bold
import lib.test.green

private val style = green + bold

val pi: Double
    get() {
        println("Была использована переменная ${style("course.lecture4.imports.circle.pi")}")
        return 3.14159
    }

fun area(radius: Double): Double {
    println("Была вызвана функция ${style("course.lecture4.imports.circle.area")}")
    return pi * radius * radius
}

fun circumference(radius: Double): Double {
    println("Была вызвана функция ${style("course.lecture4.imports.circle.circumference")}")
    return 2 * pi * radius
}

object Circle {
    val pi: Double
        get() {
            println("Была использована переменная ${style("course.lecture4.imports.circle.Circle.pi")}")
            return 3.14159265
        }

    fun area(radius: Double): Double {
        println("Была вызвана функция ${style("course.lecture4.imports.circle.Circle.area")}")
        return pi * radius * radius
    }

    fun circumference(radius: Double): Double {
        println("Была вызвана функция ${style("course.lecture4.imports.circle.Circle.circumference")}")
        return 2 * pi * radius
    }
}