package org.gorttar.graphics.turtle.awt

import org.gorttar.graphics.turtle.TurtleCore
import org.gorttar.graphics.turtle.TurtleStateView
import java.awt.*
import java.awt.EventQueue.invokeAndWait
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

/**
 * [TurtleCore] interface over AWT [Graphics2D]
 */
class GraphicsTurtleCore(
    graphics: Graphics,
    private val size: Dimension,
    private val stateView: TurtleStateView
) : TurtleCore {
    // Every Graphics in runtime is actually Graphics2D
    private val graphics = graphics as Graphics2D

    // Force all interactions with Graphics2D to happen in  UI thread
    private operator fun Graphics2D.invoke(block: Graphics2D.() -> Unit) = invokeAndWait { block() }

    private val turtleFigure = listOf(0 to 0, -5 to 10, 15 to 0, -5 to -10)
        .let { it + it.first() }
        .map { (x, y) -> x.toDouble() to y.toDouble() }
        .asSequence()

    override fun clear(): Unit = graphics { clearRect(0, 0, size.width, size.height) }

    override fun line(x1: Double, y1: Double, x2: Double, y2: Double): Unit = graphics {
        color.let {
            color = Color.BLACK
            drawLine(x1.roundToInt(), y1.roundToInt(), x2.roundToInt(), y2.roundToInt())
            color = it
        }
    }

    override fun pen(width: Float): Unit =
        graphics { stroke = BasicStroke(width, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND) }

    override fun showTurtle(): Unit = stateView.let {
        if (it.isVisible) graphics {
            val oldStroke = stroke
            stroke = BasicStroke(2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)
            setXORMode(Color.BLACK)

            drawFigure()

            setPaintMode()
            stroke = oldStroke
        }
    }

    private fun Graphics2D.drawFigure() {
        val phi = stateView.phi
        val base = stateView.x to stateView.y
        val s = sin(phi)
        val c = cos(phi)
        turtleFigure
            .map { (x, y) -> base + (x * c - y * s to x * s + y * c) }
            .zipWithNext()
            .forEach { (from, to) ->
                drawLine(
                    from.x.roundToInt(),
                    from.y.roundToInt(),
                    to.x.roundToInt(),
                    to.y.roundToInt()
                )
            }
    }
}

typealias Pos = Pair<Double, Double>

val Pos.x: Double get() = first
val Pos.y: Double get() = second
operator fun Pos.plus(o: Pos): Pos = x + o.x to y + o.y