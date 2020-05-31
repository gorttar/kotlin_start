package course.lecture6

fun main() {
    val source = listOf(1, 2, 3)
    val mapViaFor: MutableList<Iterable<Char>> = mutableListOf()
    fun transform(x: Int) = 'a'..'a' + x
    // цикл for эквивалентный функции map
    for (x in source) mapViaFor.add(transform(x))
    println("Результат map трансформации списка $source с помощью for: $mapViaFor")
    val map = source.map(::transform)
    println("Результат map трансформации списка $source: $map")

    // как примерно реализована функция map
    fun <T, R> Iterable<T>.myMap(mapper: (T) -> R): List<R> {
        val result: MutableList<R> = mutableListOf()
        for (t in this) result.add(mapper(t))
        return result
    }

    val myMap = source.myMap(::transform)
    println("Результат myMap трансформации списка $source: $myMap")
}