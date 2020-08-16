package course.lecture8

import kotlin.properties.Delegates

fun main() {
    var n: Int by Delegates.notNull()
    while (true) if (
        runCatching { n = readLine()!!.toInt() }.onFailure {
            println("Введено не целое число; попробуйте ещё раз")
        }.isSuccess
    ) break
    println("Введено число $n")
}