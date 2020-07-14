package course.lecture7.debug5

fun <T> isPal(x: MutableList<T>): Boolean {
    val temp = x
    temp.reverse()
    println("$temp, $x") // <-- отладочная печать промежуточного значения
    return temp == x
}

fun silly(n: Int) {
    val result: MutableList<String> = mutableListOf()
    for (i in 1..n) {
        print("Введите элемент: ")
        val elem = readLine()!!
        result.add(elem)
    }
    println(if (isPal(result)) "Да" else "Нет")
}

fun main() {
    print("Введите длину: ")
    silly(readLine()!!.toInt())
}