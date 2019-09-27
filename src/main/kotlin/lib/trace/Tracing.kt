package lib.trace

import lib.output.boldGreen
import lib.output.magenta
import lib.repr.repr
import java.lang.reflect.Method
import java.lang.reflect.Proxy.newProxyInstance
import kotlin.sequences.filter as sFilter
import kotlin.sequences.forEach as sForEach
import kotlin.sequences.map as sMap
import kotlin.sequences.takeWhile as sTakeWhile

inline fun <reified T> T.traced(toString: String): T =
        newProxyInstance(T::class.java.classLoader, arrayOf(T::class.java)) { _, method, args: Array<Any?>? ->
            val name = method.name
            val res = when {
                args != null -> traceInvocation(toString, method, args)
                name == "toString" -> toString
                else -> traceInvocation(toString, method)
            }
            res
        } as T

inline fun <reified T : Sequence<*>> T.traced(toString: String): T =
        newProxyInstance(T::class.java.classLoader, arrayOf(T::class.java)) { _, method, args: Array<Any?>? ->
            method.name.let { name ->
                when {
                    args != null -> traceInvocation(toString, method, args)
                    name == "toString" -> toString
                    name == "iterator" -> traceIteratorInvocation(toString, method)
                    else -> traceInvocation(toString, method)
                }
            }
        } as T

fun <P1, R> traced(toString: String, f: (P1) -> R) = object : (P1) -> R by f {
    override fun toString() = toString
}

fun <T, R> Sequence<T>.map(transform: (T) -> R): Sequence<R> = sMap(transform.trace("map"))
fun <T> Sequence<T>.filter(predicate: (T) -> Boolean): Sequence<T> = sFilter(predicate.trace("filter"))
fun <T> Sequence<T>.takeWhile(predicate: (T) -> Boolean): Sequence<T> = sTakeWhile(predicate.trace("takeWhile"))
fun <T> Sequence<T>.forEach(action: (T) -> Unit): Unit = sForEach { t: T ->
    trace(
            { "${t.repr.boldGreen} -> forEach ${action.toString().magenta}" },
            { action(t) },
            { "" })
}

/**
 * can't be private since used in inline fun
 */
fun Any?.traceInvocation(toString: String, method: Method): Any? = method.name.let { name ->
    trace(
            { "-> ${"$toString.$name()".magenta}" },
            { method(this) },
            { res -> "${res.repr.boldGreen} <- ${"$toString.$name()".magenta}" })
}

/**
 * can't be private since used in inline fun
 */
fun Any?.traceInvocation(toString: String, method: Method, args: Array<Any?>): Any? = method.name.let { name ->
    trace(
            { "-> ${"$toString.$name(${args.joinToString { arg -> arg.repr }})".magenta}" },
            { method(this, *args) },
            { res -> "${res.repr.boldGreen} <- ${"$toString.$name(${args.joinToString { arg -> arg.repr }})".magenta}" })
}

/**
 * can't be private since used in inline fun
 */
fun <T : Sequence<*>> T.traceIteratorInvocation(toString: String, method: Method): Iterator<*> = trace(
        { "-> ${"$toString.iterator()".magenta}" },
        { (method(this) as Iterator<*>).traced("$toString::Iterator") },
        { "${it.repr.boldGreen} <- ${"$toString.iterator()".magenta}" })

private var indent: Int = 1
private fun println(message: Any?) = kotlin.io.println("${"\t".repeat(indent)}$message")
private inline fun <T> trace(beforeMessage: () -> Any?, invocation: () -> T, afterMessage: (T) -> Any?): T =
        println(beforeMessage())
                .also { indent++ }
                .let { invocation() }
                .also { indent-- }
                .also { println(afterMessage(it)) }

private fun <T, R> ((T) -> R).trace(hofName: String): (T) -> R = { t ->
    trace(
            { "${t.repr.boldGreen} -> $hofName ${toString().magenta}" },
            { this(t) },
            { res -> "${res.repr.boldGreen} <- $hofName ${toString().magenta}" })
}