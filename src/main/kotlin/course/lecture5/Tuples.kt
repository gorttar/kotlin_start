package course.lecture5

import org.gorttar.output.boldGreen
import org.gorttar.output.magenta
import org.gorttar.output.withIndent
import org.gorttar.repr.repr

fun main() {
    val pair: Pair<Int, String> = 1 to "foo"
    println("Для пары = ${pair.repr.boldGreen}")
    withIndent {
        println("${"first".magenta}  = ${pair.first.repr.boldGreen}")
        println("${"second".magenta} = ${pair.second.repr.boldGreen}")
        println("Деконструкция пары")
        withIndent {
            val (num, str) = pair
            println("${"num".magenta} = ${num.repr.boldGreen}")
            println("${"str".magenta} = ${str.repr.boldGreen}")
        }
    }

    println()
    val triple: Triple<Int, String, Double> = Triple(2, "bar", 3.0)
    println("Для тройки = ${triple.repr.boldGreen}")
    withIndent {
        println("${"first".magenta}  = ${triple.first.repr.boldGreen}")
        println("${"second".magenta} = ${triple.second.repr.boldGreen}")
        println("${"third".magenta}  = ${triple.third.repr.boldGreen}")
        println("Деконструкция тройки")
        withIndent {
            val (num, str, dbl) = triple
            println("${"num".magenta} = ${num.repr.boldGreen}")
            println("${"str".magenta} = ${str.repr.boldGreen}")
            println("${"dbl".magenta} = ${dbl.repr.boldGreen}")
        }
    }

    println()
    val quadruple = Quadruple(4, "baz", 5.0, '6')
    println("Для четвёрки = ${quadruple.repr.boldGreen}")
    withIndent {
        println("${"first".magenta}  = ${quadruple.first.repr.boldGreen}")
        println("${"second".magenta} = ${quadruple.second.repr.boldGreen}")
        println("${"third".magenta}  = ${quadruple.third.repr.boldGreen}")
        println("${"forth".magenta}  = ${quadruple.forth.repr.boldGreen}")
        println("Деконструкция четвёрки")
        withIndent {
            val (num, str, dbl, chr) = quadruple
            println("${"num".magenta} = ${num.repr.boldGreen}")
            println("${"str".magenta} = ${str.repr.boldGreen}")
            println("${"dbl".magenta} = ${dbl.repr.boldGreen}")
            println("${"chr".magenta} = ${chr.repr.boldGreen}")
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