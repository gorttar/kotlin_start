package course.lecture6

fun main() {
    val source = listOf(1, 2, 3)
    val flatMapViaFor: MutableList<Char> = mutableListOf()
    fun transform(x: Int) = 'a'..'a' + x
    // цикл for эквивалентный функции flatMap
    for (x in source) flatMapViaFor.addAll(transform(x))
    println("Результат flatMap трансформации списка $source с помощью for: $flatMapViaFor")
    val flatMap = source.flatMap(::transform)
    println("Результат flatMap трансформации списка $source: $flatMap")

    // как примерно реализована функция flatMap
    fun <T, R> Iterable<T>.myFlatMap(mapper: (T) -> Iterable<R>): List<R> {
        val result: MutableList<R> = mutableListOf()
        for (t in this) result.addAll(mapper(t))
        return result
    }

    val myFlatMap = source.myFlatMap(::transform)
    println("Результат myFlatMap трансформации списка $source: $myFlatMap")
}