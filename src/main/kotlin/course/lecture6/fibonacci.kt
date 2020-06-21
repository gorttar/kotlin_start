package course.lecture6

import java.math.BigInteger
import kotlin.system.measureTimeMillis

fun fib(n: Int): BigInteger = when (n) {
    1 -> BigInteger.ONE
    2 -> 2.toBigInteger()
    else -> fib(n - 1) + fib(n - 2)
}

fun fibEfficient(n: Int): BigInteger {
    fun fibMemo(n: Int, map: MutableMap<Int, BigInteger>): BigInteger = map[n] ?: when (n) {
        1 -> BigInteger.ONE
        2 -> 2.toBigInteger()
        else -> fibMemo(n - 1, map) + fibMemo(n - 2, map)
    }.also { map[n] = it }

    return fibMemo(n, mutableMapOf())
}

var numFibCalls = 0

fun fibTracked(n: Int): BigInteger = when (n) {
    1 -> BigInteger.ONE
    2 -> 2.toBigInteger()
    else -> fibTracked(n - 1) + fibTracked(n - 2)
}.also { numFibCalls++ }

var numFibEffCalls = 0

fun fibEffTracked(n: Int): BigInteger {
    fun fibMemo(n: Int, map: MutableMap<Int, BigInteger>): BigInteger = (map[n] ?: when (n) {
        1 -> BigInteger.ONE
        2 -> 2.toBigInteger()
        else -> fibMemo(n - 1, map) + fibMemo(n - 2, map)
    }.also { map[n] = it }).also { numFibEffCalls++ }

    return fibMemo(n, mutableMapOf())
}

fun main() {
    println("Вычисления fib заняли ${measureTimeMillis { println(fib(40)) } / 1000} с.")
    println("Вычисления fibEfficient заняли ${measureTimeMillis { println(fibEfficient(50)) } / 1000} с.")
    println(
        "Вычисления fib заняли ${measureTimeMillis { println(fibTracked(40)) } / 1000} с и $numFibCalls вызовов."
    )
    println(
        "Вычисления fibEffTracked заняли ${measureTimeMillis { println(fibEffTracked(50)) } / 1000} с и " +
            "$numFibEffCalls вызовов."
    )
}