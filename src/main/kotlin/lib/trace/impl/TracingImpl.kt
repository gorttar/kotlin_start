package lib.trace.impl

import lib.output.boldGreen
import lib.output.magenta
import lib.repr.repr
import lib.trace.trace
import org.mockito.Mockito.mock
import java.lang.reflect.Method
import java.lang.reflect.Proxy.newProxyInstance
import kotlin.reflect.KClass

fun <T : Any> trace(t: T, tKClass: KClass<T>, toString: String): T = with(tKClass.java) {
    if (isInterface) cast(newProxyInstance(classLoader, arrayOf(this)) { _, method, args ->
        method.handleInvocation(t, args, toString)
    })
    else mock(this) { it.method.handleInvocation(t, it.arguments, toString) }
}

fun <P1, R> ((P1) -> R).trace(hofName: String, traceResult: Boolean = true): (P1) -> R = rTracer(
        hofName,
        traceResult
).let { after ->
    { p1 ->
        trace(
                { "${p1.repr.boldGreen} -> $hofName ${toString().magenta}" },
                { this(p1) },
                after)
    }
}

fun <P1, P2, R> ((P1, P2) -> R).trace(hofName: String, traceResult: Boolean = true): (P1, P2) -> R = rTracer(
        hofName,
        traceResult
).let { after ->
    { p1, p2 ->
        trace(
                { "${"${p1.repr}, ${p2.repr}".boldGreen} -> $hofName ${toString().magenta}" },
                { this(p1, p2) },
                after)
    }
}

private fun <T : Any> Method.handleInvocation(t: T, args: Array<Any?>?, toString: String): Any? = when {
    args != null -> traceInvocation(t, this, args, toString)
    name == "toString" -> toString
    name == "iterator" -> traceIteratorInvocation(t, this, toString)
    else -> traceInvocation(t, this, toString)
}

private fun <R> Function<R>.rTracer(hofName: String, traceResult: Boolean): (R) -> String =
        if (traceResult) { r -> "${r.repr.boldGreen} <- $hofName ${toString().magenta}" }
        else { _ -> "" }

private fun <T> trace(beforeMessage: () -> Any?, invocation: () -> T, afterMessage: (T) -> Any?): T =
        println(beforeMessage())
                .also { indent++ }
                .let { invocation() }
                .also { indent-- }
                .also { println(afterMessage(it)) }

private fun traceInvocation(o: Any?, method: Method, toString: String): Any? = method.name.let { name ->
    trace(
            { "-> ${"$toString.$name()".magenta}" },
            { method(o) },
            { res -> "${res.repr.boldGreen} <- ${"$toString.$name()".magenta}" })
}

private fun traceInvocation(o: Any?, method: Method, args: Array<Any?>, toString: String): Any? = method.name.let { name ->
    trace(
            { "-> ${"$toString.$name(${args.joinToString { arg -> arg.repr }})".magenta}" },
            { method(o, *args) },
            { res -> "${res.repr.boldGreen} <- ${"$toString.$name(${args.joinToString { arg -> arg.repr }})".magenta}" })
}

private fun <T> traceIteratorInvocation(t: T, method: Method, toString: String): Iterator<*> = trace(
        { "-> ${"$toString.iterator()".magenta}" },
        { (method(t) as Iterator<*>).trace("$toString::Iterator") },
        { "${it.repr.boldGreen} <- ${"$toString.iterator()".magenta}" })

private var indent: Int = 1
private fun println(message: Any?) = kotlin.io.println("${"\t".repeat(indent)}$message")