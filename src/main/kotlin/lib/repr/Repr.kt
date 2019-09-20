package lib.repr

val Any?.repr: String get() = repr(ProcessedCompounds())

private fun Any?.repr(processedCompounds: ProcessedCompounds): String = when (this) {
    is String -> "\"$this\""
    is Char -> "'$this'"
    is Collection<*> -> this.repr("[", "]", this, processedCompounds) { it.repr(this) }
    is Map<*, *> -> this.repr("{", "}", entries, processedCompounds) { (k, v) -> "${k.repr(this)}=${v.repr(this)}" }
    else -> "$this"
}

private fun <T : Any, E> T.repr(
        prefix: String,
        postfix: String,
        asCollection: Collection<E>,
        processedCompounds: ProcessedCompounds,
        transform: ProcessedCompounds.(E) -> String = { it.toString() }): String = when (val k = K(this)) {
    in processedCompounds -> {
        "(cycle ${when (this) {
            is Collection<*> -> "Collection #${processedCompounds[k]}"
            is Map<*, *> -> "Map #${processedCompounds[k]}"
            else -> "compound #${processedCompounds[k]}"
        }})"
    }
    else -> {
        processedCompounds.add(k)
        asCollection.joinToString(", ", prefix, postfix, transform = { processedCompounds.transform(it) })
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