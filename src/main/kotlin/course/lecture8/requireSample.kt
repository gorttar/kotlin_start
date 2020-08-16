package course.lecture8

fun main() {
    print("Введите натуральное число: ")
    val n = readLine()!!.toInt()
    require(n > 0) { "Ожидалось натуральное число, но введено $n" } // IllegalArgumentException
}