package lib.control

typealias ManagedValue<T> = Pair<() -> T, (T) -> Unit>

fun <T> ManagedValue<T>.get(): T = first()

fun <T> ManagedValue<T>.set(t: T): Unit = second(t)

infix fun <T> (() -> T).setBy(setter: (T) -> Unit): ManagedValue<T> = to(setter)

fun <T> managedBy(getter: () -> T, setter: (T) -> Unit): ManagedValue<T> = getter to setter

inline fun <T, R> ManagedValue<T>.on(t: T, block: (oldT: T) -> R): R = get().let {
    try {
        set(t)
        block(it)
    } finally {
        set(it)
    }
}