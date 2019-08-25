package course.lecture4

fun main() {
    /**
     * [x] - целое [Int] >= 0
     * @return [x]-е число Фибоначчи
     */
    fun fib(x: Int): Int =
            if (x == 0 || x == 1) 1
            else fib(x - 1) + fib(x - 2)

    println("Простая рекурсия: fib(10) = ${fib(10)}")

    fun fibTail(x: Int): Int {
        tailrec fun fib(x: Int, acc: Int = 1, prevAcc: Int = 1): Int =
                if (x == 0) prevAcc
                else fib(x - 1, acc + prevAcc, acc)
        return fib(x)
    }

    println("Хвостовая рекурсия: fibTail(10) = ${fibTail(10)}")

    fun fibHOF(x: Int): Int = generateSequence(1 to 1) { (acc, prevAcc) -> acc + prevAcc to acc }
            .drop(x)
            .first()
            .second

    println("Функции высшего порядка: fibHOF(10) = ${fibHOF(10)}")

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

    println("Итерирование: fibIter(10) = ${fibIter(10)}")
    println(fibIter(10))
}