package course.lecture9

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
    println(oneHalf)
    println(twoThirds)
    println(oneHalf + twoThirds)
    println(twoThirds - oneHalf)
    println((oneHalf + twoThirds).toDouble())
}