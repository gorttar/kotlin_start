package course.lecture4

fun main() {
    fun g(x: Int): Int {
        fun h() {
            val x = "abc"
            println("В области видимости h(): x = $x")
        }
        val x = x + 1
        println("В области видимости g(x): x = $x")
        h()
        return x
    }

    val x = 3
    val z = g(x)
    println("В области видимости main(): x = $x")
}