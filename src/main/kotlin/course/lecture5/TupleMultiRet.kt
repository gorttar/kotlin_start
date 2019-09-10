package course.lecture5

import lib.output.boldGreen
import lib.test.get

fun main() {
    val (quotient, remainder) = quotientRemainder(x, y)
    action(quotient to remainder)
}

fun quotientRemainder(x: Int, y: Int): Pair<Int, Int> = x / y to x % y

const val x: Int = 5
const val y: Int = 2

val action: (Pair<Int, Int>) -> Unit = { (quotient, remainder) ->
    println("${boldGreen[x]} делённое нацело на ${boldGreen[y]} = ${boldGreen[quotient]}")
    println("Остаток от деления ${boldGreen[x]} на ${boldGreen[y]} = ${boldGreen[remainder]}")
}