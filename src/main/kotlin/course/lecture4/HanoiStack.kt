package course.lecture4

import course.lecture4.Tower.Companion.printTowers
import course.lecture4.Tower.Companion.tower
import java.util.Stack

fun main() {
    val hanoiSize = 5
    val from = tower("1", hanoiSize)
    val to = tower("2")
    val spare = tower("3")
    var count = 0

    fun towers(n: Int, f: Tower, t: Tower, s: Tower): Unit =
            if (n == 1) {
                count++
                println("Перемещаем диск с ${f.name} на ${t.name}")
                f.moveTo(t)
                printTowers(hanoiSize, from, to, spare)
                println()
            } else {
                towers(n - 1, f, s, t)
                towers(1, f, t, s)
                towers(n - 1, s, t, f)
            }

    printTowers(hanoiSize, from, to, spare)
    println()
    towers(hanoiSize, from, to, spare)
    println("Решено за $count перемещений")
}

class Tower private constructor(val name: String, private val stack: Stack<Int>) {
    fun moveTo(to: Tower): Int =
            if (to.stack.empty() || to.stack.peek() > stack.peek()) to.stack.push(stack.pop())
            else throw IllegalStateException()

    override fun toString(): String = "Tower($stack)"

    companion object {
        fun tower(name: String = "", n: Int = 0): Tower =
                if (n < 0) throw IllegalArgumentException("") else Tower(name, (n downTo 1).toCollection(Stack()))

        fun printTowers(hanoiSize: Int, vararg towers: Tower) = with(towers.asSequence()
                .map { it.stack }
                .map {
                    (it.asSequence().map(Int::toString) + generateSequence { "·" })
                            .take(hanoiSize)
                            .toList()
                            .reversed()
                }
                .toList()) {
            drop(1)
                    .fold(get(0).map { sequenceOf(it) }) { table, column ->
                        table.zip(column) { row, cell -> row + cell }
                    }
                    .map { it.joinToString(" ") }
                    .forEach(::println)
        }
    }
}