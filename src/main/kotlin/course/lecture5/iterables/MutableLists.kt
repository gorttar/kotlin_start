package course.lecture5.iterables

import lib.output.boldGreen
import lib.output.magenta
import lib.output.withIndent
import lib.repr.repr

fun main() {
    val l = mutableListOf(2, 1, 3)
    println("\nОперации с изменяемым списком ${"l".magenta} = ${l.repr.boldGreen}:")
    withIndent {
        l[1] = 5
        println("Изменение элемента с индексом 1 (${"l[1] = 5".magenta}):                          ${"l".magenta} = ${
        l.repr.boldGreen
        }")
        l.add(5)
        println("Добавление элемента (${"l.add(5)".magenta}):                                      ${"l".magenta} = ${
        l.repr.boldGreen
        }")
        l += 7
        println("Добавление элемента (${"l += 7".magenta}):                                        ${"l".magenta} = ${
        l.repr.boldGreen
        }")
        l.addAll(listOf(8, 9, 10))
        println("Добавление элементов из другого списка (${"l.addAll(listOf(8, 9, 10))".magenta}): ${"l".magenta} = ${
        l.repr.boldGreen
        }")
        l += listOf(11, 12, 13)
        println("Добавление элементов из другого списка (${"l += listOf(11, 12, 13)".magenta}):    ${"l".magenta} = ${
        l.repr.boldGreen
        }")
        println("Удаление элемента с индексом 10 (элемент = ${l.removeAt(10).repr.magenta}):                      ${"l".magenta} = ${
        l.repr.boldGreen
        }")
    }
}