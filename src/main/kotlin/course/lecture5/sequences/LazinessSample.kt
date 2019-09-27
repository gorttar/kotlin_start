package course.lecture5.sequences

import lib.output.boldGreen
import lib.output.magenta
import lib.repr.repr
import lib.trace.filter
import lib.trace.forEach
import lib.trace.map
import lib.trace.takeWhile
import lib.trace.traced

fun main() {
    println("Создаю первую последовательность")
    val seq1 = sequenceOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).traced("Seq1")
    println("Создана ${seq1.repr.boldGreen}\n")

    println("Создаю вторую последовательность")
    val seq2 = seq1.map(traced<Int, Int>("{ it - 1 }") { it - 1 }).traced("Seq2")
    println("Создана ${seq2.repr.boldGreen}\n")

    println("Создаю третью последовательность")
    val seq3 = seq2.filter(traced("{ it % 2 == 0 }") { it % 2 == 0 }).traced("Seq3")
    println("Создана ${seq3.repr.boldGreen}\n")

    println("Создаю четвёртую последовательность")
    val seq4 = seq3.takeWhile(traced("{ it <= 6 }") { it <= 6 }).traced("Seq4")
    println("Создана ${seq4.repr.boldGreen}\n")

    println("Запускаю цикл по элементам последовательности $seq4")
    seq4.forEach(traced("{ println(...) }")
    { println("Элемент ${it.repr.boldGreen} получен из ${seq4.repr.magenta}") })
    println("Цикл завершён")
}

