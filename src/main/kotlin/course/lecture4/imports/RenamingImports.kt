package course.lecture4.imports

import course.lecture4.imports.circle.Circle.area as cArea
import course.lecture4.imports.circle.Circle.circumference as cCircumference
import course.lecture4.imports.circle.Circle.pi as cPi
import course.lecture4.imports.circle.area as pArea
import course.lecture4.imports.circle.circumference as pCircumference
import course.lecture4.imports.circle.pi as pPi

private const val pi = 3.14

fun main() {
    println(pi)
    println(pPi)
    println(pArea(3.0))
    println(pCircumference(3.0))
    println(cPi)
    println(cArea(3.0))
    println(cCircumference(3.0))
}