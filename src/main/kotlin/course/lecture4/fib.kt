package course.lecture4

fun main() {
    /**
     * [x] - целое [Int] >= 0
     * @return [x]-е число Фибоначчи
     */
    fun fib(x: Int): Int =
            if (x == 0 || x == 1) 1
            else fib(x - 1) + fib(x - 2)

    println(fib(10))
}