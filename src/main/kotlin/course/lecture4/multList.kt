package course.lecture4

fun main() {
    fun multListRecur(xs: List<Int>): Int =
            if (xs.size == 1) xs[0]
            else xs[0] * multListRecur(xs.drop(1))

    println(multListRecur(listOf(1, 3, 5, 7, 9)))
}