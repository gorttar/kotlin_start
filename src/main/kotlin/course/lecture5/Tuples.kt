package course.lecture5

import lib.output.boldGreen
import lib.output.withIndent
import lib.test.get

fun main() {
    val pair: Pair<Int, String> = 1 to "foo"
    println("Для пары = ${boldGreen[pair]}")
    withIndent {
        println("first = ${boldGreen[pair.first]}")
        println("second = ${boldGreen[pair.second]}")
        println("Деконструкция пары")
        withIndent {
            val (num, str) = pair
            println("num = ${boldGreen[num]}")
            println("str = ${boldGreen[str]}")
        }
    }

    println()
    val triple: Triple<Int, String, Double> = Triple(2, "bar", 3.0)
    println("Для тройки = ${boldGreen[triple]}")
    withIndent {
        println("first = ${boldGreen[triple.first]}")
        println("second = ${boldGreen[triple.second]}")
        println("third = ${boldGreen[triple.third]}")
        println("Деконструкция тройки")
        withIndent {
            val (num, str, dbl) = triple
            println("num = ${boldGreen[num]}")
            println("str = ${boldGreen[str]}")
            println("dbl = ${boldGreen[dbl]}")
        }
    }

    println()
    val quadruple = Quadruple(4, "baz", 5.0, '6')
    println("Для четвёрки = ${boldGreen[quadruple]}")
    withIndent {
        println("first = ${boldGreen[quadruple.first]}")
        println("second = ${boldGreen[quadruple.second]}")
        println("third = ${boldGreen[quadruple.third]}")
        println("forth = ${boldGreen[quadruple.forth]}")
        println("Деконструкция четвёрки")
        withIndent {
            val (num, str, dbl, chr) = quadruple
            println("num = ${boldGreen[num]}")
            println("str = ${boldGreen[str]}")
            println("dbl = ${boldGreen[dbl]}")
            println("dbl = ${boldGreen[chr]}")
        }
    }
}

data class Quadruple<out A, out B, out C, out D>(
        val first: A,
        val second: B,
        val third: C,
        val forth: D
) {
    override fun toString(): String = "($first, $second, $third, $forth)"
}