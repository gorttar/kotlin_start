package lib.control

import lib.control.Try.Companion.Do
import lib.control.Try.Companion.flatMap
import lib.control.Try.Companion.lift
import lib.repr.repr

/**
 * interface which requires custom [equals] and [hashCode] implementations
 */
interface RequireEquals {
    override operator fun equals(other: Any?): Boolean
    override fun hashCode(): Int
}

/**
 * interface which requires custom [toString] implementation
 */
interface RequireToString {
    override fun toString(): String
}

sealed class Try<out V> : RequireEquals, RequireToString {
    data class Success<out V>(val v: V) : Try<V>() {
        override fun toString() = "Success(${v.repr})"
    }

    data class Failure(val t: Throwable) : Try<Nothing>() {
        override fun toString() = "Failure($t)"
    }

    companion object {
        fun <V> V.success(): Try<V> = Success(this)
        fun <V> Throwable.failure(): Try<V> = Failure(this)

        object Do {
            inline infix fun <V> `try`(s: () -> V): Try<V> = try {
                s().success()
            } catch (t: Throwable) {
                t.failure()
            }
        }

        inline fun <V, R> Try<V>.escape(handler: Failure.(Throwable) -> R, after: Success<V>.(V) -> R): R = when (this) {
            is Success -> after(v)
            is Failure -> handler(t)
        }

        inline infix fun <P1, R> Try<P1>.flatMap(after: (P1) -> Try<R>): Try<R> = escape({ this }) {
            try {
                after(v)
            } catch (t: Throwable) {
                t.failure()
            }
        }

        inline infix fun <P1, R> Try<P1>.map(after: (P1) -> R): Try<R> = flatMap { after(it).success() }
        inline infix fun <R> Try<R>.handle(handler: (Throwable) -> R): R = escape({ handler(t) }) { v }

        fun <V> lift(v: V) = v.success()
        fun <V> lift(t: Throwable) = t.failure<V>()
        inline fun <R> lift(crossinline s: () -> R): TryS<R> = { Do `try` s }

        inline fun <P1, R> lift(crossinline f: (P1) -> R): (Try<P1>) -> Try<R> = { it map f }
        inline fun <P1, P2, R> lift(crossinline f: (P1, P2) -> R): (Try<P1>, Try<P2>) -> Try<R> =
                { tp1, tp2 -> tp1 flatMap { p1 -> tp2 map { p2 -> f(p1, p2) } } }

        inline fun <P1, P2, P3, R> lift(crossinline f: (P1, P2, P3) -> R): (Try<P1>, Try<P2>, Try<P3>) -> Try<R> =
                { tp1, tp2, tp3 -> tp1 flatMap { p1 -> tp2 flatMap { p2 -> tp3 map { p3 -> f(p1, p2, p3) } } } }
    }
}

typealias TryS<T> = () -> Try<T>

object S {
    inline infix fun <R> `try`(crossinline s: () -> R): TryS<R> = { Do `try` s }
}

inline infix fun <V, R> TryS<V>.flatMap(crossinline after: (V) -> Try<R>): TryS<R> = { this() flatMap after }
inline infix fun <V, R> TryS<V>.map(crossinline after: (V) -> R): TryS<R> = flatMap { lift(after(it)) }
