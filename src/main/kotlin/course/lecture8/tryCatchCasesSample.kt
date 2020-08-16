package course.lecture8

fun main() {
    try {
        print("Введите числитель: ")
        val a = readLine()!!.toInt()
        print("Введите знаменатель: ")
        val b = readLine()!!.toInt()
        println(a / b)
        println("Успешно")
    } catch (e: NumberFormatException) {
        println("Не могу преобразовать в число")
    } catch (e: ArithmeticException) {
        println("Не могу поделить на 0")
    } catch (e: Throwable) {
        println("Что-то пошло совсем не так")
    }
}