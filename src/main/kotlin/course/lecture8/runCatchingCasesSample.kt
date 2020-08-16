package course.lecture8

fun main() {
    runCatching {
        print("Введите числитель: ")
        val a = readLine()!!.toInt()
        print("Введите знаменатель: ")
        val b = readLine()!!.toInt()
        println(a / b)
    }.fold({ println("Успешно") }) {
        when (it) {
            is NumberFormatException -> println("Не могу преобразовать в число")
            is ArithmeticException -> println("Не могу поделить на 0")
            else -> println("Что-то пошло совсем не так")
        }
    }
}