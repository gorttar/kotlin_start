package org.gorttar.trace.impl

import org.gorttar.output.boldBlue
import org.gorttar.output.boldGreen
import org.gorttar.output.magenta
import org.gorttar.output.withIndent
import org.gorttar.repr.IdEqKey
import org.gorttar.repr.repr
import org.mockito.Mockito.mock
import java.lang.reflect.Method
import java.lang.reflect.Proxy.newProxyInstance
import kotlin.reflect.KClass

fun <T : Any> trace(t: T, tKClass: KClass<T>, toString: String): T = tKClass.java.run {
    if (isInterface) cast(newProxyInstance(classLoader, arrayOf(this)) { _, method, args ->
        method.handleInvocation(t, args, toString)
    })
    else mock(this) { it.method.handleInvocation(t, it.arguments, toString) }
}.also { TracedToNoTrace[it] = t }

fun <P1, R> ((P1) -> R).trace(hofName: String, traceResult: Boolean = true): (P1) -> R = { p1: P1 ->
    trace(
            { "-> ${traceName(hofName, p1)}" },
            { this(p1) },
            traceResult(hofName, traceResult, p1))
}.also { TracedToNoTrace[it] = this }

fun <P1, P2, R> ((P1, P2) -> R).trace(hofName: String, traceResult: Boolean = true): (P1, P2) -> R = { p1: P1, p2: P2 ->
    trace(
            { "-> ${traceName(hofName, p1, p2)}" },
            { this(p1, p2) },
            traceResult(hofName, traceResult, p1, p2))
}.also { TracedToNoTrace[it] = this }

object TracedToNoTrace {
    private val tracedToNoTraceStorage: MutableMap<IdEqKey, Any> = hashMapOf()
    fun <T : Any> raw(traced: T) = tracedToNoTraceStorage[IdEqKey(traced)]
    operator fun <T : Any> set(traced: T, nt: T) = tracedToNoTraceStorage.set(IdEqKey(traced), nt)
    inline operator fun <reified T : Any> get(traced: T): T = generateSequence(traced) { raw(it) as? T }
            .last()
            .also { if (it !== raw(traced)) set(traced, it) }
}

private fun <T : Any> Method.handleInvocation(t: T, args: Array<Any?>?, toString: String): Any? = when {
    args != null -> traceInvocation(t, this, toString, args)
    name == "toString" -> toString
    name == "iterator" -> traceIteratorInvocation(t, this, toString)
    else -> traceInvocation(t, this, toString, args)
}

private fun <R> Function<R>.traceResult(hofName: String, traceResult: Boolean, vararg args: Any?): (R) -> String =
        if (traceResult) { r -> "${r.repr.boldGreen} <- ${traceName(hofName, *args)}" }
        else { _ -> "" }

private fun <T> trace(beforeMessage: () -> Any?, invocation: () -> T, afterMessage: (T) -> Any?): T =
    withIndent {
        println(beforeMessage())
        withIndent(block = invocation)
    }.also { withIndent { println(afterMessage(it)) } }

private fun traceInvocation(o: Any?, method: Method, toString: String, args: Array<Any?>?): Any? = trace(
        { "-> ${method.traceName(toString, args)}" },
        { args?.let { method(o, *args) } ?: method(o) },
        { "${it.repr.boldGreen} <- ${method.traceName(toString, args)}" })

private fun <T> traceIteratorInvocation(t: T, method: Method, toString: String): Iterator<*> = trace(
        { "-> ${method.traceName(toString)}" },
        { trace((method(t) as Iterator<*>), Iterator::class, "$toString::Iterator") },
        { "${it.repr.boldGreen} <- ${method.traceName(toString)}" })

private fun Method.traceName(toString: String, args: Array<Any?>? = null) =
        "${toString.magenta}${(".$name(${args?.joinToString { arg -> arg.repr } ?: ""})").boldBlue}"

private fun <R> Function<R>.traceName(hofName: String, vararg args: Any?) =
        "$hofName ${"$this(${args.joinToString { arg -> arg.repr }})".boldBlue}"