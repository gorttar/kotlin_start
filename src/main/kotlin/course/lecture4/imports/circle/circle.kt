package course.lecture4.imports.circle

import lib.output.boldGreen
import kotlin.reflect.KProperty

private operator fun <T> (() -> T).getValue(thisRef: Any?, property: KProperty<*>) = this()

val pi: Double by {
    println("Была использована переменная ${boldGreen("course.lecture4.imports.circle.pi")}")
    3.14159
}

fun area(radius: Double): Double = println("Была вызвана функция ${boldGreen("course.lecture4.imports.circle.area")}")
        .let { pi * radius * radius }

fun circumference(radius: Double): Double =
        println("Была вызвана функция ${boldGreen("course.lecture4.imports.circle.circumference")}")
                .let { 2 * pi * radius }

object Circle {
    val pi: Double by {
        println("Была использована переменная ${boldGreen("course.lecture4.imports.circle.Circle.pi")}")
        3.14159265
    }

    fun area(radius: Double): Double =
            println("Была вызвана функция ${boldGreen("course.lecture4.imports.circle.Circle.area")}")
                    .let { pi * radius * radius }

    fun circumference(radius: Double): Double =
            println("Была вызвана функция ${boldGreen("course.lecture4.imports.circle.Circle.circumference")}")
                    .let { 2 * pi * radius }
}