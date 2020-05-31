package course.lecture6

fun main() {
    val source = listOf(1, 2, 3).map { 'a'..'a' + it }
    val flattenViaFor: MutableList<Char> = mutableListOf()
    // цикл for эквивалентный функции flatten
    for (x in source) flattenViaFor.addAll(x)
    println("Результат flatten трансформации списка $source с помощью for: $flattenViaFor")
    val flatten = source.flatten()
    println("Результат flatten трансформации списка $source: $flatten")

    // как примерно реализована функция flatten
    fun <T> Iterable<Iterable<T>>.myFlatten(): List<T> {
        val result: MutableList<T> = mutableListOf()
        for (t in this) result.addAll(t)
        return result
    }

    val myFlatten = source.myFlatten()
    println("Результат myFlatten трансформации списка $source: $myFlatten")
}