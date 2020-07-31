package org.gorttar.data

infix fun Sequence<*>.eq(ySequence: Sequence<*>): Boolean {
    val xs = iterator()
    val ys = ySequence.iterator()
    while (xs.hasNext() || ys.hasNext()) {
        if (!xs.hasNext() || !ys.hasNext() || xs.next() != ys.next()) {
            return false
        }
    }
    return true
}