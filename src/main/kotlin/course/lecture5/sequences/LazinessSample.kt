package course.lecture5.sequences

import lib.output.boldGreen
import lib.output.magenta
import lib.repr.repr
import lib.trace.filter
import lib.trace.forEach
import lib.trace.map
import lib.trace.takeWhile
import lib.trace.trace

fun main() {
    println("Создаю первую последовательность")
    val seq1 = sequenceOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).trace("Seq1")
    println("Создана ${seq1.repr.magenta}\n")

    println("Создаю вторую последовательность")
    val seq2 = seq1.map(trace("{ x -> x - 1 }") { x: Int -> x - 1 }).trace("Seq2")
    println("Создана ${seq2.repr.magenta}\n")

    println("Создаю третью последовательность")
    val seq3 = seq2.filter(trace("{ x -> x % 2 == 0 }") { x -> x % 2 == 0 }).trace("Seq3")
    println("Создана ${seq3.repr.magenta}\n")

    println("Создаю четвёртую последовательность")
    val seq4 = seq3.takeWhile(trace("{ x -> x <= 6 }") { x -> x <= 6 }).trace("Seq4")
    println("Создана ${seq4.repr.magenta}\n")

    println("Запускаю цикл по элементам последовательности $seq4")
    seq4.forEach(trace("{ println(...) }")
    { println("Элемент ${it.repr.boldGreen} получен из ${seq4.repr.magenta}") })
    println("Цикл завершён")
}

