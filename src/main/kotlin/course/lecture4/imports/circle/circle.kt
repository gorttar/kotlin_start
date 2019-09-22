package course.lecture4.imports.circle

import lib.output.boldGreen
import kotlin.reflect.KProperty

private operator fun <T> (() -> T).getValue(thisRef: Any?, property: KProperty<*>) = this()

val pi: Double by {
    println("Была использована переменная ${"course.lecture4.imports.circle.pi".boldGreen}")
    3.14159
}

fun area(radius: Double): Double = println("Была вызвана функция ${"course.lecture4.imports.circle.area".boldGreen}")
        .let { pi * radius * radius }

fun circumference(radius: Double): Double =
        println("Была вызвана функция ${"course.lecture4.imports.circle.circumference".boldGreen}")
                .let { 2 * pi * radius }

object Circle {
    val pi: Double by {
        println("Была использована переменная ${"course.lecture4.imports.circle.Circle.pi".boldGreen}")
        3.14159265
    }

    fun area(radius: Double): Double =
            println("Была вызвана функция ${"course.lecture4.imports.circle.Circle.area".boldGreen}")
                    .let { pi * radius * radius }

    fun circumference(radius: Double): Double =
            println("Была вызвана функция ${"course.lecture4.imports.circle.Circle.circumference".boldGreen}")
                    .let { 2 * pi * radius }
}