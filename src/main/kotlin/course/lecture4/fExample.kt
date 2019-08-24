package course.lecture4

fun main() {
    fun f(x: Int/*формальный параметр*/): Int {
        val x = x + 1
        println("Внутри f(x): x = $x")
        return x
    }

    val x = 3
    val z = f(x/*значние, подставляемое в параметр*/) /*присвоение переменной z результата вызова f*/
}