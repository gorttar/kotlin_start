package course.lecture7.debug1

fun <T> isPal(x: MutableList<T>): Boolean {
    val temp = x
    temp.reverse()
    return temp == x
}

fun silly(n: Int) {
    lateinit var result: MutableList<String>
    for (i in 1..n) {
        result = mutableListOf()
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