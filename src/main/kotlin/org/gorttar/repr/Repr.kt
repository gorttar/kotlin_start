package org.gorttar.repr

import kotlin.reflect.KVisibility.PUBLIC
import kotlin.reflect.full.memberFunctions

val Any?.repr: String get() = repr(ProcessedCompounds())

private val componentNamePattern = "^component[0-9]+$".toRegex()

private fun Any?.repr(processedCompounds: ProcessedCompounds): String = when (this) {
    null -> "null"
    is String -> "\"$this\""
    is Char -> "'$this'"
    is Number, is Boolean -> "$this"
    is Collection<*> -> repr("[", "]", this, processedCompounds)
    is Map<*, *> -> repr("{", "}", entries, processedCompounds) { (k, v) -> "${k.repr(this)}=${v.repr(this)}" }
    is Pair<*, *> -> repr("Pair(", ")", this.toList(), processedCompounds)
    is Triple<*, *, *> -> repr("Triple(", ")", this.toList(), processedCompounds)
    else -> {
        val kClass = this::class
        val asCollection = kClass.memberFunctions.asSequence()
                .filter { it.name.matches(componentNamePattern) }
                .sortedBy { it.name.replace("component", "").toInt() }
                .filter { it.visibility == PUBLIC }
                .filterIsInstance<Function1<Any, Any?>>()
                .map { it(this) }
                .toList()
        if (asCollection.isNotEmpty()) repr("${kClass.simpleName}(", ")", asCollection, processedCompounds)
        else "$this"
    }
}

private inline fun <E> Any.repr(
        prefix: String,
        postfix: String,
        asCollection: Collection<E>,
        processedCompounds: ProcessedCompounds,
        crossinline transform: ProcessedCompounds.(E) -> String = { it.repr(this) }): String = when (val k = IdEqKey(this)) {
    in processedCompounds -> "(cycle ${when (this) {
        is Collection<*> -> "Collection"
        is Map<*, *> -> "Map"
        is Pair<*, *> -> "Pair"
        is Triple<*, *, *> -> "Triple"
        else -> "${this::class.simpleName}"
    }} #${processedCompounds[k]})"
    else -> {
        processedCompounds.add(k)
        asCollection.joinToString(", ", prefix, postfix) { processedCompounds.transform(it) }
    }
}

class IdEqKey(private val obj: Any) {
    override fun equals(other: Any?): Boolean = other is IdEqKey && obj === other.obj
    override fun hashCode(): Int = System.identityHashCode(obj)
}

private class ProcessedCompounds {
    private val counter = generateSequence(1) { it + 1 }.iterator()
    private val processedCompounds: MutableMap<IdEqKey, Int> = mutableMapOf()
    operator fun contains(k: IdEqKey) = k in processedCompounds
    operator fun contains(obj: Any) = IdEqKey(obj) in this
    fun add(k: IdEqKey) = processedCompounds.computeIfAbsent(k) { counter.next() }
    operator fun get(k: IdEqKey) = processedCompounds[k]
    operator fun get(obj: Any) = get(IdEqKey(obj))
}