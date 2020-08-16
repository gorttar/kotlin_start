package course.lecture8

fun main() {
    try {
        print("Введите числитель: ")
        val a = readLine()!!.toInt()
        print("Введите знаменатель: ")
        val b = readLine()!!.toInt()
        println(a / b)
        println("Успешно")
    } catch (e: Throwable) {
        println("Неверный ввод")
        throw e
    } finally {
        println("В любом случае, спасибо")
    }
    println("До свидания")
}