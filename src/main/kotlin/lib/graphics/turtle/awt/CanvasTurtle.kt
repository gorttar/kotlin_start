package lib.graphics.turtle.awt

import lib.graphics.turtle.Turtle
import lib.graphics.turtle.TurtleState
import java.awt.Canvas
import java.awt.Dimension
import java.awt.EventQueue.invokeAndWait
import java.awt.Graphics
import java.awt.image.BufferedImage
import java.time.Duration
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * AWT component with [Turtle] interface and buffering
 * (i. e. what's drawn by turtle is not lost upon GUI refresh)
 */
class CanvasTurtle(size: Dimension) : Canvas() {
    private val buffer = BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB).apply {
        graphics.fillRect(0, 0, size.width, size.height)
    }
    val turtle = TurtleState(x = size.width * 0.5, y = size.height * 0.5).let { state ->
        Turtle(
            core = RepaintHelper(
                delegate = GraphicsTurtleCore(buffer.graphics, size, state),
                minInterval = Duration.ofMillis(25),
                doRepaint = { invokeAndWait { repaint() } }
            ),
            state = state
        )
    }

    init {
        setSize(size)
    }

    override fun paint(g: Graphics) {
        log("paint started")
        g.drawImage(buffer, 0, 0, this)
        log("paint finished")
    }
}

fun main() {
    log("main started")

    AwtTurtle.createSquareWindow().run {
        CanvasTurtle(size).also { add(it) }
    }.turtle.apply {
        val width = 486.0
        val height = width * 2 * sqrt(3.0) / 3
        pu() // поднимаем перо (хвост)
        bk(width / 2) lt 90 fd height / 4 rt 90 // делаем отступ так, чтобы фигура была по центру
        pd() // опускаем перо (хвост)
        (0..5).forEach { n ->
            a = width / 3.0.pow(n)
            repeat(3) { f(n).r() } // рисуем три стороны снежинки Коха
        }
    }

    log("main finished")
}

private const val angle = 120

private var a = 2.0

private fun Turtle.l() = lt(angle / 2)
private fun Turtle.r() = rt(angle)
private fun Turtle.f(n: Int): Turtle =
        if (n > 0) (n - 1).let { f(it).l().f(it).r().f(it).l().f(it) }
        else fd(a)
