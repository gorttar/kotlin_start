package course.lecture8

fun main() {
    runCatching {
        print("Введите числитель: ")
        val a = readLine()!!.toInt()
        print("Введите знаменатель: ")
        val b = readLine()!!.toInt()
        println(a / b)
    }.onSuccess { println("Успешно") }.onFailure { println("Неверный ввод") }.getOrThrow()
    println("До свидания")
}