package course.lecture5.iterables

import lib.output.boldGreen
import lib.output.magenta
import lib.repr.repr
import lib.trace.filter
import lib.trace.listOf
import lib.trace.map
import lib.trace.takeWhile
import lib.trace.trace
import lib.trace.forEach as tForEach

fun main() {
    println("Создаю перввый список")
    val list1 = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    println("Создан ${"$list1".magenta}\n")

    println("Создаю второй список")
    val list2 = list1.map(trace("{ x -> x - 1 }") { x: Int -> x - 1 })
    println("Создан ${"$list2".magenta}\n")

    println("Создаю третий список")
    val list3 = list2.filter(trace("{ x -> x % 2 == 0 }") { x -> x % 2 == 0 })
    println("Создан ${"$list3".magenta}\n")

    println("Создаю четвёртый список")
    val list4 = list3.takeWhile(trace("{ x -> x <= 6 }") { x -> x <= 6 })
    println("Создан ${"$list4".magenta}\n")

    println("Запускаю цикл по элементам списка ${"$list4".magenta}")
    list4.tForEach(trace("{ x -> println(...) }")
    { x -> println("Элемент ${x.repr.boldGreen} получен из ${"$list4".magenta}") })
    println("Цикл завершён")
}