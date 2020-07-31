package org.gorttar.graphics.turtle

import org.gorttar.graphics.turtle.awt.createSquareWindow
import org.gorttar.graphics.turtle.awt.turtle
import kotlin.math.pow
import kotlin.math.sqrt

fun main() {
    val width = 486.0
    val height = width * 2 * sqrt(3.0) / 3
    createSquareWindow().turtle().apply {
//        ht()
        pu() // поднимаем перо (хвост)
        bk(width / 2) lt 90 fd height / 4 rt 90 // делаем отступ так, чтобы фигура была по центру
        pd() // опускаем перо (хвост)
        (0..5).forEach { kochFlake(it) }
        st()
    }
}

private fun Turtle.kochFlake(n: Int, width: Double = 486.0) {
    a = width / 3.0.pow(n)
    repeat(3) { f(n).r() } // рисуем три стороны снежинки Коха
}

private const val angle = 120

private var a = 2.0

private fun Turtle.l() = lt(angle / 2)
private fun Turtle.r() = rt(angle)
private fun Turtle.f(n: Int): Turtle =
    if (n > 0) (n - 1).let { f(it).l().f(it).r().f(it).l().f(it) }
    else fd(a)