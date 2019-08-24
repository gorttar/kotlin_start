package course.lecture4

fun main() {
    var count = 0
    fun towers(n: Int, from: Any?, to: Any?, spare: Any?): Unit =
            if (n == 1) println("Перемещаем диск с $from на $to").apply { count++ }
            else {
                towers(n - 1, from, spare, to)
                towers(1, from, to, spare)
                towers(n - 1, spare, to, from)
            }
    towers(5, 1, 2, 3)
    println("Решено за $count перемещений")
}