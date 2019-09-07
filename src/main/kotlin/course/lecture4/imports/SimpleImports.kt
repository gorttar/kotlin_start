package course.lecture4.imports

import course.lecture4.imports.circle.Circle
import course.lecture4.imports.circle.area
import course.lecture4.imports.circle.circumference
import course.lecture4.imports.circle.pi

private const val pi = 3.14

fun main() {
    println(course.lecture4.imports.pi)
    println(pi)
    println(area(3.0))
    println(circumference(3.0))
    println(Circle.pi)
    println(Circle.area(3.0))
    println(Circle.circumference(3.0))
}