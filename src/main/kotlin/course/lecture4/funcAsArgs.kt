package course.lecture4

fun main() {
    fun fa(): String {
        println("Внутри fa")
        return "это вернулось из fa"
    }

    fun <Y> fb(y: Y): Y {
        println("Внутри fb")
        return y
    }

    fun <R> fc(z: () -> R): R {
        println("Внутри fc")
        return z()
    }

    println("вызов fa:")
    println(
            fa()/*вызов fa без параметров*/
    )
    println("\nвызов fb:")
    println(
            5 + fb(2)/*вызов fb с одним параметром*/
    )
    println("\nвызов fc:")
    println(
            fc(::fa)/*вызов fc с одним параметром - другой функцией*/
    )
}