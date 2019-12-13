package lib.graphics.turtle.awt

import lib.graphics.turtle.Turtle
import lib.graphics.turtle.TurtleState
import lib.graphics.turtle.awt.CanvasTurtle.turtle
import java.awt.Canvas
import java.awt.Container
import java.awt.EventQueue.invokeAndWait
import java.awt.Graphics
import java.awt.image.BufferedImage
import java.time.Duration
import kotlin.math.pow
import kotlin.math.sqrt

object CanvasTurtle {
    /** buffered [Turtle] over [Container] */
    fun Container.turtle(): Turtle = BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB).let { image ->
        val imageCanvas = object : Canvas() {
            override fun paint(g: Graphics) {
                log("paint started")
                g.drawImage(image, 0, 0, this)
                log("paint finished")
            }
        }.also {
            add(it)
            it.size = size
        }

        image.graphics.fillRect(0, 0, size.width, size.height)

        val state = TurtleState(x = size.width * 0.5, y = size.height * 0.5)
        Turtle(
            core = RepaintCoreDecorator(
                delegate = GraphicsTurtleCore(image.graphics, size, state),
                minInterval = Duration.ofMillis(25),
                doRepaint = { invokeAndWait(imageCanvas::repaint) }
            ),
            state = state
        )
    }
}

fun main() {
    log("main started")

    AwtTurtle.createSquareWindow().turtle().apply {
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
