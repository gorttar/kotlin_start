package course.lecture7.debug4

fun <T> isPal(x: MutableList<T>): Boolean {
    val temp = x
    temp.reverse()
    return temp == x
}

fun silly(n: Int) {
    val result: MutableList<String> = mutableListOf() // <-- перенесли инициализацию за пределы цикла
    for (i in 1..n) {
        print("Введите элемент: ")
        val elem = readLine()!!
        result.add(elem)
        println(result) // <-- отладочная печать промежуточного значения
    }
    println(if (isPal(result)) "Да" else "Нет")
}

fun main() {
    print("Введите длину: ")
    silly(readLine()!!.toInt())
}