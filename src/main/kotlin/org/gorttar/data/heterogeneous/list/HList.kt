@file:Suppress("NOTHING_TO_INLINE")

package org.gorttar.data.heterogeneous.list

import org.gorttar.data.eq

/**
 * left sub listed heterogeneous list.
 * Left sub listed means that
 * *    values are stored in [HCons.tail] (right part) while consecutive sub lists in [HCons.head] (left part)
 * *    It's more convenient from usage perspective than right sub listed heterogeneous list
 * *    because operators and infix functions in Kotlin are grouped from left to right.
 * *    For example a + b + c + d actually means ((a + b) + c) + d
 * *    so left sub listing can eliminate brackets in list construction expressions
 * Heterogeneous means that list preserves types of its elements in full type signature
 * [L] - full type of list
 */
sealed class HList<out L : HList<L>>

private val HList<*>.ts
    get() = generateSequence(this) {
        when (it) {
            is HCons<*, *> -> it.head
            is HNil -> null
        }
    }.filterIsInstance<HCons<*, *>>().map { it.tail }

/**
 * Empty [HList]
 */
object HNil : HList<HNil>() {
    override fun toString(): String = "$hListTypeName[]"
}

/**
 * [HList] node constructor
 * [head] - consecutive sub list
 * [tail] - node value
 */
class HCons<out L : HList<L>, out A>(val head: L, val tail: A) : HList<HCons<L, A>>() {
    override fun equals(other: Any?): Boolean = this === other || other is HCons<*, *> && ts.eq(other.ts)
    override fun hashCode(): Int = ts.fold(0) { hash, x -> 31 * hash + x.hashCode() }
    override fun toString(): String = "$hListTypeName[${head.ts.fold(tail.toString()) { str, x -> "$x, $str" }}]"
}

fun <L : HList<L>, A> HCons<L, *>.copy(tail: A): HCons<L, A> = HCons(head, tail)

fun <L : HList<L>, A> HCons<*, A>.copy(head: L): HCons<L, A> = HCons(head, tail)

fun <L : HList<L>, A> HCons<L, A>.copy(): HCons<L, A> = this

/**
 * creates [HList] by appending [a] to [this]
 */
inline operator fun <L : HList<L>, A> L.plus(a: A): HCons<L, A> = HCons(this, a)

/**
 * creates [HList2] by appending [this] to [HNil] and then appending [b] to result
 */
@Suppress("FunctionName")
inline fun <A, B> A.`+`(b: B): HList2<A, B> = HCons(HNil, this) + b

private val hListTypeName by lazy { HList::class.simpleName?.intern() }