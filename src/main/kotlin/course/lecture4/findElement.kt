package course.lecture4

fun main() {
    fun <T: Comparable<T>> hasElemRecur(t: T, ts: List<T>): Boolean = when {
        ts.isEmpty() -> false
        ts.size == 1 -> ts.first() == t
        else -> {
            val half = ts.size / 2
            if (ts[half] > t) hasElemRecur(t, ts.take(half))
            else hasElemRecur(t, ts.drop(half))
        }
    }

    println(hasElemRecur(1, listOf(1, 2, 3, 5, 7, 8, 9, 15)))
}