package lib.trace

import lib.output.magenta
import lib.trace.impl.TracedToNoTrace
import lib.trace.impl.trace
import kotlin.collections.filter as iFilter
import kotlin.collections.forEach as iForEach
import kotlin.collections.map as iMap
import kotlin.collections.takeWhile as iTakeWhile
import kotlin.sequences.filter as sFilter
import kotlin.sequences.forEach as sForEach
import kotlin.sequences.map as sMap
import kotlin.sequences.reduce as sReduce
import kotlin.sequences.takeWhile as sTakeWhile

inline fun <reified T : Any> T.trace(toString: String): T = trace(this, T::class, toString)
inline val <reified T : Any> T.unTraced: T get() = TracedToNoTrace[this]

fun <P1, R> trace(toString: String, f: (P1) -> R) = object : (P1) -> R by f {
    override fun toString() = toString
}

fun <P1, P2, R> trace(toString: String, f: (P1, P2) -> R) = object : (P1, P2) -> R by f {
    override fun toString() = toString
}

fun <T> listOf(vararg ts: T): List<T> = kotlin.collections.listOf(*ts).let { it.trace("List$it") }

fun <T, R> Iterable<T>.map(transform: (T) -> R): List<R> = iMap(transform.trace("map")).let { it.trace("List$it") }
fun <T> Iterable<T>.filter(predicate: (T) -> Boolean): List<T> =
        iFilter(predicate.trace("filter")).let { it.trace("List$it") }

fun <T> Iterable<T>.takeWhile(predicate: (T) -> Boolean): List<T> =
        iTakeWhile(predicate.trace("takeWhile")).let { it.trace("List$it") }

fun <T> Iterable<T>.forEach(action: (T) -> Unit): Unit = iForEach(action.trace("forEach", false))

fun <T> sequenceOf(vararg ts: T): Sequence<T> =
        kotlin.sequences.sequenceOf(*ts).trace(ts.joinToString(prefix = "Seq[", postfix = "]"))

fun <T, R> Sequence<T>.map(transform: (T) -> R): Sequence<R> =
        sMap(transform.trace("${"$this".magenta}.map")).trace("$this.map $transform")

fun <T> Sequence<T>.filter(predicate: (T) -> Boolean): Sequence<T> =
        sFilter(predicate.trace("${"$this".magenta}.filter")).trace("$this.filter $predicate")

fun <T> Sequence<T>.takeWhile(predicate: (T) -> Boolean): Sequence<T> =
        sTakeWhile(predicate.trace("${"$this".magenta}.takeWhile")).trace("$this.takeWhile $predicate")

fun <T> Sequence<T>.forEach(action: (T) -> Unit): Unit = sForEach(action.trace("forEach", false))
fun <S, T : S> Sequence<T>.reduce(operation: (acc: S, T) -> S): S = sReduce(operation.trace("reduce"))