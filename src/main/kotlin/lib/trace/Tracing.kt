package lib.trace

import lib.trace.impl.trace
import kotlin.collections.filter as iFilter
import kotlin.collections.forEach as iForEach
import kotlin.collections.map as iMap
import kotlin.collections.takeWhile as iTakeWhile
import kotlin.sequences.filter as sFilter
import kotlin.sequences.forEach as sForEach
import kotlin.sequences.map as sMap
import kotlin.sequences.takeWhile as sTakeWhile

inline fun <reified T : Any> T.trace(toString: String): T = trace(this, T::class, toString)

fun <P1, R> trace(toString: String, f: (P1) -> R) = object : (P1) -> R by f {
    override fun toString() = toString
}

fun <T, R> Sequence<T>.map(transform: (T) -> R): Sequence<R> = sMap(transform.trace("map"))
fun <T> Sequence<T>.filter(predicate: (T) -> Boolean): Sequence<T> = sFilter(predicate.trace("filter"))
fun <T> Sequence<T>.takeWhile(predicate: (T) -> Boolean): Sequence<T> = sTakeWhile(predicate.trace("takeWhile"))
fun <T> Sequence<T>.forEach(action: (T) -> Unit): Unit = sForEach(action.trace("forEach", false))