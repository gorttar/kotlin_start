package course.lecture4

fun main() {
    fun multIter(a: Int, b: Int): Int {
        var result = 0
        for (i in (1..b)) result += a
        return result
    }
    println(multIter(37, 41))

    fun mult(a: Int, b: Int): Int = if (b == 1) a else a + mult(a, b - 1)
    println(mult(37, 41))
}