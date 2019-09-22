package course.lecture5.iterables

import lib.output.boldGreen
import lib.output.magenta
import lib.output.withIndent
import lib.repr.repr

fun main() {
    /**пустой список [Int]*/
    val list1: List<Int> = emptyList()
    println("Пустой список: ${list1.repr.boldGreen}")

    /**список [Int]*/
    val list2: List<Int> = listOf(1, 2, 5)
    println("Список целых чисел: ${list2.repr.boldGreen}")

    /**список-то список, но вот чего?*/
    val list3 = listOf(2, 'a', 4, true)
    println("Список чего-то: ${list3.repr.boldGreen}")

    val l = listOf(2, 1, 3)
    println("\nОперации со списком ${"l".magenta} = ${l.repr.boldGreen}:")
    withIndent {
        println("Длина списка: ${"l.size".magenta} = ${l.size.repr.boldGreen}")
        println("Элемент списка с индексом 0: ${"l[0]".magenta} или ${"l.get(0)".magenta} = ${l[0].repr.boldGreen}")
        println("Элемент списка с индексом 2, увеличенный на 1: ${"l[2] + 1".magenta} = ${(l[2] + 1).repr.boldGreen}")
        val (i0, i1, i2) = l
        println("Деконструкция списка: ${"(i0, i1, i2)".magenta} = ${"($i0, $i1, $i2)".boldGreen}")
    }
}