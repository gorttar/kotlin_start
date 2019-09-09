package course.lecture4.imports.circle

import lib.test.bold
import lib.test.green
import kotlin.reflect.KProperty

private val style = green + bold
private operator fun <T> (() -> T).getValue(thisRef: Any?, property: KProperty<*>) = this()

val pi: Double by {
    println("Была использована переменная ${style("course.lecture4.imports.circle.pi")}")
    3.14159
}

fun area(radius: Double): Double = println("Была вызвана функция ${style("course.lecture4.imports.circle.area")}")
        .let { pi * radius * radius }

fun circumference(radius: Double): Double =
        println("Была вызвана функция ${style("course.lecture4.imports.circle.circumference")}")
                .let { 2 * pi * radius }

object Circle {
    val pi: Double by {
        println("Была использована переменная ${style("course.lecture4.imports.circle.Circle.pi")}")
        3.14159265
    }

    fun area(radius: Double): Double =
            println("Была вызвана функция ${style("course.lecture4.imports.circle.Circle.area")}")
                    .let { pi * radius * radius }

    fun circumference(radius: Double): Double =
            println("Была вызвана функция ${style("course.lecture4.imports.circle.Circle.circumference")}")
                    .let { 2 * pi * radius }
}