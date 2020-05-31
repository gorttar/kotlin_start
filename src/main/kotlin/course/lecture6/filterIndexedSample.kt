package course.lecture6

fun main() {
    val source = listOf(1, 2, 3, 4)
    val filterIndexedViaFor: MutableList<Int> = mutableListOf()
    fun condition(index: Int, x: Int) = index < 3 && x != 2
    // цикл for эквивалентный filterIndexed
    var index = 0
    for (x in source) if (condition(index++, x)) filterIndexedViaFor.add(x)
    println("Результат filterIndexed для списка $source с помощью for: $filterIndexedViaFor")
    val filterIndexed = source.filterIndexed(::condition)
    println("Результат filterIndexed для списка $source: $filterIndexed")

    // как примерно реализована функция filterIndexed
    fun <T> Iterable<T>.myFilterIndexed(predicate: (index: Int, T) -> Boolean): List<T> {
        val result: MutableList<T> = mutableListOf()
        var idx = 0
        for (t in this) if (predicate(idx++, t)) result.add(t)
        return result
    }

    val myFilterIndexed = source.myFilterIndexed(::condition)
    println("Результат myFilterIndexed для списка $source: $myFilterIndexed")
}