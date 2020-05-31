package course.lecture6

fun main() {
    val source = listOf(1, 2, 3)
    fun task(x: Int) = println('a' + x)
    // цикл for эквивалентный forEach
    println("Результат for цикла для списка $source:")
    for (x in source) task(x)
    println("\nРезультат forEach для списка $source:")
    source.forEach(::task)

    // как примерно реализована функция forEach
    fun <T> Iterable<T>.myForEach(action: (T) -> Unit) {
        for (t in this) action(t)
    }

    println("\nРезультат myForEach для списка $source:")
    source.myForEach(::task)
}