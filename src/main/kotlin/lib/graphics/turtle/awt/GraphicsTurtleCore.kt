package lib.graphics.turtle.awt

import lib.graphics.turtle.TurtleCore
import java.awt.BasicStroke
import java.awt.Color
import java.awt.Dimension
import java.awt.EventQueue.invokeAndWait
import java.awt.Graphics2D
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

/**
 * [TurtleCore] interface over AWT [Graphics2D]
 */
class GraphicsTurtleCore(
    graphics: Graphics2D,
    private val size: Dimension
) : TurtleCore {
    private val graphics = object {
        // Force all interactions with Graphics2D to happen in  UI thread
        operator fun invoke(block: Graphics2D.() -> Unit): Unit = invokeAndWait {
            graphics.block()
        }
    }

    private var isVisible = true

    override fun clear(): Unit = graphics { clearRect(0, 0, size.width, size.height) }

    override fun line(x1: Double, y1: Double, x2: Double, y2: Double): Unit =
        graphics { drawLine(x1.roundToInt(), y1.roundToInt(), x2.roundToInt(), y2.roundToInt()) }

    override fun pen(width: Float): Unit =
        graphics { stroke = BasicStroke(width, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND) }

    override fun turtle(x: Double, y: Double, angle: Double) {
        if (isVisible) {
            graphics {
                val oldStroke = stroke
                stroke = turtle.stroke
                setXORMode(Color.WHITE)

                turtle.draw(this, x to y, angle)

                setPaintMode()
                stroke = oldStroke
            }
        }
    }

    private val turtle = object {
        private val zero = 0.0 to 0.0
        private val poly = listOf(-5 to 10, 15 to 0, -5 to -10)
        val stroke = BasicStroke(2f)

        fun draw(graphics: Graphics2D, pos: Pos, angle: Double) {
            val r = angle * PI / 180
            val s = sin(r)
            val c = cos(r)
            val last = poly.fold(zero) { p0, p1 ->
                val (x1, y1) = p1
                val x2 = x1 * c - y1 * s
                val y2 = x1 * s + y1 * c
                (x2 to y2).also { graphics.line(pos, p0, it) }
            }
            graphics.line(pos, last, zero)
        }

        private fun Graphics2D.line(base: Pos, from: Pos, to: Pos) {
            val (fromX, fromY) = base + from
            val (toX, toY) = base + to
            drawLine(
                fromX.roundToInt(),
                fromY.roundToInt(),
                toX.roundToInt(),
                toY.roundToInt()
            )
        }
    }
}

typealias Pos = Pair<Double, Double>

val Pos.x get() = first
val Pos.y get() = second
operator fun Pos.plus(o: Pos): Pos = x + o.x to y + o.y