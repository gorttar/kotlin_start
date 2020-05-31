package course.lecture6

fun main() {
    val source = listOf(1, 2, 3)
    fun task(index: Int, x: Int) = println('a' + x + index)
    // цикл for эквивалентный forEachIndexed
    println("Результат for цикла для списка $source:")
    var index = 0
    for (x in source) task(index++, x)
    println("\nРезультат forEachIndexed для списка $source:")
    source.forEachIndexed(::task)

    // как примерно реализована функция forEachIndexed
    fun <T> Iterable<T>.myForEachIndexed(action: (index: Int, T) -> Unit) {
        var idx = 0
        for (t in this) action(idx++, t)
    }

    println("\nРезультат myForEachIndexed для списка $source:")
    source.myForEachIndexed(::task)
}