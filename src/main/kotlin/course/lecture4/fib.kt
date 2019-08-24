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

    fun fibTail(x: Int): Int {
        tailrec fun fib(x: Int, acc: Int = 1, prevAcc: Int = 1): Int =
                if (x == 0) prevAcc
                else fib(x - 1, acc + prevAcc, acc)
        return fib(x)
    }

    println(fibTail(10))

    fun fibIter(x: Int): Int {
        var fib = 1
        var prevFib = 1
        for (i in 1..x) {
            val tmp = fib
            fib += prevFib
            prevFib = tmp
        }
        return prevFib
    }

    println(fibIter(10))
}