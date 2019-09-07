package course.lecture4.imports

private const val pi = 3.14

fun main() {
    println(pi)
    println(course.lecture4.imports.circle.pi)
    println(course.lecture4.imports.circle.area(3.0))
    println(course.lecture4.imports.circle.circumference(3.0))
    println(course.lecture4.imports.circle.Circle.pi)
    println(course.lecture4.imports.circle.Circle.area(3.0))
    println(course.lecture4.imports.circle.Circle.circumference(3.0))
}