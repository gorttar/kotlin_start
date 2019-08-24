package course.lecture4

fun main() {
    fun factorial(n: Int): Int = if (n == 1) 1 else n * factorial(n - 1)
    println("""
            Вычислите внимательно:
            230 - 220 * 0.5
            Вы не поверите, но ответ 5!
            Если что, 5! = ${factorial(5)}
            
        """.trimIndent())

    fun factorialTail(n: Int): Int {
        tailrec fun factorial(n: Int, acc: Int = 1): Int = if (n == 1) acc else factorial(n - 1, acc * n)
        return factorial(n)
    }

    println(factorialTail(5))
    println()

    fun factorialIter(n: Int): Int {
        var prod = 1
        for (i in (1..n)) prod *= i
        return prod
    }

    println(factorialIter(5))
}