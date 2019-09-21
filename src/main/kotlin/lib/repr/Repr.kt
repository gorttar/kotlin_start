package lib.repr

val Any?.repr: String get() = repr(ProcessedCompounds())

private fun Any?.repr(processedCompounds: ProcessedCompounds): String = when (this) {
    null -> "null"
    is String -> "\"$this\""
    is Char -> "'$this'"
    is Collection<*> -> repr("[", "]", this, processedCompounds)
    is Map<*, *> -> repr("{", "}", entries, processedCompounds) { (k, v) -> "${k.repr(this)}=${v.repr(this)}" }
    is Pair<*, *> -> repr("(", ")", this.toList(), processedCompounds)
    is Triple<*, *, *> -> repr("(", ")", this.toList(), processedCompounds)
    else -> "$this"
}

private inline fun <E> Any.repr(
        prefix: String,
        postfix: String,
        asCollection: Collection<E>,
        processedCompounds: ProcessedCompounds,
        crossinline transform: ProcessedCompounds.(E) -> String = { it.repr(this) }): String = when (val k = K(this)) {
    in processedCompounds -> {
        "(cycle ${when (this) {
            is Collection<*> -> "Collection"
            is Map<*, *> -> "Map"
            is Pair<*, *> -> "Pair"
            is Triple<*, *, *> -> "Triple"
            else -> "compound"
        }} #${processedCompounds[k]})"
    }
    else -> {
        processedCompounds.add(k)
        asCollection.joinToString(", ", prefix, postfix) { processedCompounds.transform(it) }
    }
}

private class K(private val obj: Any) {
    override fun equals(other: Any?): Boolean = other is K && obj === other.obj
    override fun hashCode(): Int = System.identityHashCode(obj)
}

private class ProcessedCompounds {
    private val counter = generateSequence(1) { it + 1 }.iterator()
    private val processedCompounds: MutableMap<K, Int> = mutableMapOf()
    operator fun contains(k: K) = k in processedCompounds
    operator fun contains(obj: Any) = K(obj) in this
    fun add(k: K) = processedCompounds.computeIfAbsent(k) { counter.next() }
    operator fun get(k: K) = processedCompounds[k]
    operator fun get(obj: Any) = get(K(obj))
}