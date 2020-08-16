package course.lecture8

fun main() {
    var n: Int
    while (true) try {
        n = readLine()!!.toInt()
        break // из цикла выходим только если дошли сюда без исключений
    } catch (e: NumberFormatException) {
        println("Введено не целое число; попробуйте ещё раз") // печатаем сообщение только если поймали исключение
    }
    println("Введено число $n")
}