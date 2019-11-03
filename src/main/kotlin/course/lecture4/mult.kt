package course.lecture4

fun main() {
    fun mult(a: Int, b: Int): Int =
            if (b == 1) {
                a
            } else {
                val prevMult = mult(a, b - 1)
                a + prevMult
            }
    println("Простая рекурсия: mult(37, 41) = ${mult(37, 41)}")

    fun multTail(a: Int, b: Int): Int {
        tailrec fun mult(a: Int, b: Int, acc: Int = 0): Int =
                if (b == 0) {
                    acc
                } else {
                    val prevMult = acc + a
                    mult(a, b - 1, prevMult)
                }
        return mult(a, b)
    }
    println("Хвостовая рекурсия: multTail(37, 41) = ${multTail(37, 41)}")

    fun multHOF(a: Int, b: Int): Int = (1..b).map { a }.sum()
    println("Функции высшего порядка: multHOF(37, 41) = ${multHOF(37, 41)}")


    fun multIter(a: Int, b: Int): Int {
        var result = 0
        for (i in (1..b)) result += a
        return result
    }
    println("Итерирование: multIter(37, 41) = ${multIter(37, 41)}")
}