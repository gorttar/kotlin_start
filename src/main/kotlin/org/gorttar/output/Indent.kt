package org.gorttar.output

import org.gorttar.trace.impl.traceIndent
import java.util.concurrent.ConcurrentHashMap
import kotlin.math.max

class Indent private constructor(val n: Int) {
    private val prefix = "\t".repeat(n)
    fun println(message: Any?) = kotlin.io.println("$prefix$message")
    inline fun withIndent(
            traceIndentSetter: Indent.() -> Unit = { traceIndent = inc() },
            block: Indent.() -> Unit
    ) = inc().apply(traceIndentSetter).block()

    companion object {
        private val indentsCache: MutableMap<Int, Indent> = (0..5).asSequence()
                .map(::Indent)
                .map { it.n to it }
                .toMap(ConcurrentHashMap(16))

        fun indent(n: Int): Indent = indentsCache.computeIfAbsent(max(n, 0), ::Indent)
    }
}

fun indent(n: Int): Indent = Indent.indent(n)
operator fun Indent.inc(): Indent = indent(n + 1)
operator fun Indent.dec(): Indent = indent(n - 1)
inline fun withIndent(block: Indent.() -> Unit) = indent(0).withIndent(block = block)