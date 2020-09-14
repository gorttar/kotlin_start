package course.lecture9

class IntSet {
    private val vals = mutableListOf<Int>()

    fun insert(e: Int) {
        if (e !in vals) vals += e
    }

    operator fun contains(e: Int): Boolean = e in vals

    fun remove(e: Int) {
        if (!vals.remove(e)) throw NoSuchElementException("$e not found")
    }

    override fun toString(): String = vals.sorted().joinToString(prefix = "{", postfix = "}")
}

fun main() {
    val s = IntSet()
    println(s)

    with(s) {
        insert(3)
        insert(4)
        insert(3)
    }
    println(s)

    println(3 in s)
    println(6 in s)

    with(s) {
        remove(3)
        insert(6)
    }
    println(s)

    s.remove(3)
}