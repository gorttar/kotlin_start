package course.lecture5.iterables

import lib.output.boldGreen
import lib.output.magenta
import lib.repr.repr
import lib.trace.filter
import lib.trace.map
import lib.trace.takeWhile
import lib.trace.trace
import lib.trace.forEach as tForEach

fun main() {
    println("Создаю перввый список")
    val list1 = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).apply {
        println("Создан ${"List1".magenta} = ${repr.boldGreen}\n")
    }.trace("List1")

    println("Создаю второй список")
    val list2 = list1.map(trace("{ x -> x - 1 }") { x: Int -> x - 1 }).apply {
        println("Создан ${"List2".magenta} = ${repr.boldGreen}\n")
    }.trace("List2")

    println("Создаю третий список")
    val list3 = list2.filter(trace("{ x -> x % 2 == 0 }") { x -> x % 2 == 0 }).apply {
        println("Создан ${"List3".magenta} = ${repr.boldGreen}\n")
    }.trace("List3")

    println("Создаю четвёртый список")
    val list4 = list3.takeWhile(trace("{ x -> x <= 6 }") { x -> x <= 6 }).apply {
        println("Создан ${"List4".magenta} = ${repr.boldGreen}\n")
    }.trace("List4")

    println("Запускаю цикл по элементам списка List4")
    list4.tForEach(trace("{ println(...) }")
    { println("Элемент ${it.repr.boldGreen} получен из ${"List4".magenta}") })
    println("Цикл завершён")
}

