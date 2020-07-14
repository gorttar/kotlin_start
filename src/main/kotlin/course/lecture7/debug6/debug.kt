package course.lecture7.debug6

fun <T> isPal(x: MutableList<T>): Boolean {
    val temp = x
    println("перед reverse $temp, $x") // <-- отладочная печать промежуточного значения
    temp.reverse()
    println("после reverse $temp, $x") // <-- отладочная печать промежуточного значения
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