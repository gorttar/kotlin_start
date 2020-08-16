package course.lecture8

fun main() {
    print("Введите натуральное число: ")
    val n = readLine()!!.toInt()
    check(n > 0) { "Ожидалось натуральное число, но введено $n" } // IllegalStateException
}