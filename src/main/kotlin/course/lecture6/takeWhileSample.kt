package course.lecture6

fun main() {
    val source = listOf(1, 2, 3)
    val takeWhileViaFor: MutableList<Int> = mutableListOf()
    fun condition(x: Int) = x != 2
    // цикл for эквивалентный takeWhile
    for (x in source) {
        if (!condition(x)) break
        takeWhileViaFor += x
    }
    println("Результат takeWhile для списка $source с помощью for: $takeWhileViaFor")
    val takeWhile = source.takeWhile(::condition)
    println("Результат takeWhile для списка $source: $takeWhile")

    // как примерно реализована функция takeWhile
    fun <T> Iterable<T>.myTakeWhile(predicate: (T) -> Boolean): List<T> {
        val result: MutableList<T> = mutableListOf()
        for (t in this) {
            if (!predicate(t)) break
            result += t
        }
        return result
    }

    val myTakeWhile = source.myTakeWhile(::condition)
    println("Результат myTakeWhile для списка $source: $myTakeWhile")
}