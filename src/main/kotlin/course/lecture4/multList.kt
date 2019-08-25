package course.lecture4

fun main() {
    val testList = listOf(1, 3, 5, 7, 9)

    fun multList(xs: List<Int>): Int =
            if (xs.size == 1) xs[0]
            else xs[0] * multList(xs.drop(1))

    println("Простая рекурсия: multList($testList) = ${multList(testList)}")

    fun multListTail(xs: List<Int>): Int {
        tailrec fun multList(xs: List<Int>, acc: Int = 1): Int =
                if (xs.isEmpty()) acc
                else multList(xs.drop(1), acc * xs[0])
        return multList(xs)
    }

    println("Хвостовая рекурсия: multListTail($testList) = ${multListTail(testList)}")

    fun multListHOF(xs: List<Int>): Int = xs.fold(1) { acc, x -> acc * x }

    println("Функции высшего порядка: multListHOF($testList) = ${multListHOF(testList)}")

    fun multListIter(xs: List<Int>): Int {
        var prod = 1
        for (x in xs) prod *= x
        return prod
    }

    println("Итерирование: multListIter($testList) = ${multListIter(testList)}")
}