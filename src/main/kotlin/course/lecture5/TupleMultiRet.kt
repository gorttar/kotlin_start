package course.lecture5

import lib.output.boldGreen
import lib.output.magenta
import lib.repr.repr

fun main() {
    val (quotient, remainder) = quotientRemainder(x, y)
    action(quotient to remainder)
}

fun quotientRemainder(x: Int, y: Int): Pair<Int, Int> = x / y to x % y

const val x: Int = 5
const val y: Int = 2

val action: (Pair<Int, Int>) -> Unit = { (quotient, remainder) ->
    println("${x.repr.magenta} делённое нацело на ${y.repr.magenta} = ${quotient.repr.boldGreen}")
    println("Остаток от деления ${x.repr.magenta} на ${y.repr.magenta} = ${remainder.repr.boldGreen}")
}