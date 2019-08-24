package course.lecture4

fun main() {
    fun mult(a: Int, b: Int): Int = if (b == 1) a else a + mult(a, b - 1)
    println(mult(37, 41))

    fun multTail(a: Int, b: Int): Int {
        tailrec fun mult(a: Int, b: Int, acc: Int = 0): Int = if (b == 0) acc else mult(a, b - 1, acc + a)
        return mult(a, b)
    }
    println(multTail(37, 41))

    fun multIter(a: Int, b: Int): Int {
        var result = 0
        for (i in (1..b)) result += a
        return result
    }
    println(multIter(37, 41))
}