package course.lecture6

fun main() {
    val source = listOf(1, 2, 3)
    val filterViaFor: MutableList<Int> = mutableListOf()
    fun condition(x: Int) = x != 2
    // цикл for эквивалентный filter
    for (x in source) if (condition(x)) filterViaFor.add(x)
    println("Результат filter для списка $source с помощью for: $filterViaFor")
    val filter = source.filter(::condition)
    println("Результат filter для списка $source: $filter")

    // как примерно реализована функция filter
    fun <T> Iterable<T>.myFilter(predicate: (T) -> Boolean): List<T> {
        val result: MutableList<T> = mutableListOf()
        for (t in this) if (predicate(t)) result.add(t)
        return result
    }

    val myFilter = source.myFilter(::condition)
    println("Результат myFilter для списка $source: $myFilter")
}