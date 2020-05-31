package course.lecture6

fun main() {
    val source = listOf(1, 2, 3)
    val mapIndexedViaFor: MutableList<Char> = mutableListOf()
    fun transform(index: Int, x: Int): Char = 'a' + x + index
    // цикл for эквивалентный функций mapIndexed
    var index = 0
    for (x in source) mapIndexedViaFor += transform(index++, x)
    println("Результат mapIndexed трансформации списка $source с помощью for: $mapIndexedViaFor")
    val mapIndexed = source.mapIndexed(::transform)
    println("Результат mapIndexed трансформации списка $source: $mapIndexed")

    // как примерно реализована функция mapIndexed
    fun <T, R> Iterable<T>.myMapIndexed(mapper: (index: Int, T) -> R): List<R> {
        val result: MutableList<R> = mutableListOf()
        var idx = 0
        for (t in this) result += mapper(idx++, t)
        return result
    }

    val myMapIndexed = source.myMapIndexed(::transform)
    println("Результат mapIndexed трансформации списка $source: $myMapIndexed")
}