package lib.trace.impl

import lib.output.boldGreen
import lib.output.magenta
import lib.repr.repr
import lib.trace.trace
import java.lang.reflect.Method
import java.lang.reflect.Proxy.newProxyInstance
import kotlin.reflect.KClass

fun <T : Any> trace(t: T, iClass: KClass<T>, toString: String): T = with(iClass) {
    require(isInstance(t)) { "Traced instance of type ${t::class.qualifiedName} should implement $qualifiedName" }
    @Suppress("UNCHECKED_CAST")
    newProxyInstance(java.classLoader, arrayOf(java)) { _, method, args ->
        with(method) {
            when {
                args != null -> traceInvocation(t, method, args, toString)
                name == "toString" -> toString
                name == "iterator" -> traceIteratorInvocation(t, method, toString)
                else -> traceInvocation(t, method, toString)
            }
        }
    } as T
}

fun <T, R> ((T) -> R).trace(hofName: String, traceResult: Boolean = true): (T) -> R = (
        if (traceResult) { r: R -> "${r.repr.boldGreen} <- $hofName ${toString().magenta}" }
        else { _ -> "" }
        ).let { after ->
    { t ->
        trace(
                { "${t.repr.boldGreen} -> $hofName ${toString().magenta}" },
                { this(t) },
                after)
    }
}

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