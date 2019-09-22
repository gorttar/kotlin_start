package lib.output

import java.util.concurrent.ConcurrentHashMap
import kotlin.math.max

class Indent(val n: Int) {
    private val prefix = "\t".repeat(n)
    fun println(message: Any?) = kotlin.io.println(prefix + message)
    inline fun withIndent(block: Indent.() -> Unit) = indent(n + 1).block()
}

private val indentsCache: MutableMap<Int, Indent> = (1..5).asSequence()
        .map(::Indent)
        .map { it.n to it }
        .toMap(ConcurrentHashMap(16))

fun indent(n: Int): Indent = indentsCache.computeIfAbsent(max(n, 0), ::Indent)
inline fun withIndent(block: Indent.() -> Unit) = indent(1).block()