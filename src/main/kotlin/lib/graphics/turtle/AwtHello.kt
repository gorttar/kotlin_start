package lib.graphics.turtle

import lib.graphics.turtle.awt.AwtTurtle.initWindow
import lib.graphics.turtle.awt.AwtTurtle.turtle
import kotlin.math.pow
import kotlin.math.sqrt

fun main() {
    val n = 5
    val width = 3.0.pow(n) * a
    val height = width * 2 * sqrt(3.0) / 3
    initWindow().turtle()
        .pu() // поднимаем перо (хвост)
        .bk(width / 2).lt(90).fd(height / 4).rt(90) // делаем отступ так, чтобы фигура была по центру
        .pd() // опускаем перо (хвост)
        .run { repeat(3) { f(n).r() } } // рисуем три стороны снежинки Коха
}

private const val angle = 120

private var a = 2

private fun Turtle.l() = lt(angle / 2)
private fun Turtle.r() = rt(angle)
private fun Turtle.f(n: Int): Turtle =
    if (n > 0) (n - 1).let { f(it).l().f(it).r().f(it).l().f(it) }
    else fd(a)