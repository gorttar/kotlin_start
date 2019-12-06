package lib.graphics.turtle.awt

import lib.graphics.turtle.TurtleCore
import lib.graphics.turtle.TurtleStateView
import java.awt.BasicStroke
import java.awt.Color
import java.awt.Dimension
import java.awt.EventQueue.invokeAndWait
import java.awt.Graphics2D
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

/**
 * [TurtleCore] interface over AWT [Graphics2D]
 */
class GraphicsTurtleCore(
    graphics: Graphics2D,
    private val size: Dimension,
    private val stateView: TurtleStateView
) : TurtleCore {
    private val graphics = object {
        // Force all interactions with Graphics2D to happen in  UI thread
        operator fun invoke(block: Graphics2D.() -> Unit): Unit = invokeAndWait {
            graphics.block()
        }
    }

    private val turtleFigure = listOf(0 to 0, -5 to 10, 15 to 0, -5 to -10)
        .let { it + it.first() }
        .map { (x, y) -> x.toDouble() to y.toDouble() }
        .asSequence()

    override fun clear(): Unit = graphics { clearRect(0, 0, size.width, size.height) }

    override fun line(x1: Double, y1: Double, x2: Double, y2: Double): Unit =
        graphics { drawLine(x1.roundToInt(), y1.roundToInt(), x2.roundToInt(), y2.roundToInt()) }

    override fun pen(width: Float): Unit =
        graphics { stroke = BasicStroke(width, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND) }

    override fun showTurtle() = stateView.let {
        if (it.isVisible) graphics {
            val oldStroke = stroke
            stroke = BasicStroke(2f)
            setXORMode(Color.WHITE)

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

val Pos.x get() = first
val Pos.y get() = second
operator fun Pos.plus(o: Pos): Pos = x + o.x to y + o.y