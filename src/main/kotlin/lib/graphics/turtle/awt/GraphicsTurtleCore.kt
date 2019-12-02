package lib.graphics.turtle.awt

import lib.graphics.turtle.Turtle
import java.awt.*
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

/**
 * [Turtle.Core] interface over AWT [Graphics2D]
 */
class GraphicsTurtleCore(
    graphics: Graphics2D,
    private val size: Dimension
) : Turtle.Core {
    private val graphics = object {
        // Force all interactions with Graphics2D to happen in  UI thread
        operator fun invoke(block: Graphics2D.() -> Unit) {
            EventQueue.invokeAndWait {
                graphics.block()
            }
        }
    }

    private var isVisible = false

    override fun clear() {
        graphics { clearRect(0, 0, size.width, size.height) }
    }

    override fun line(x1: Float, y1: Float, x2: Float, y2: Float) {
        graphics { drawLine(x1.roundToInt(), y1.roundToInt(), x2.roundToInt(), y2.roundToInt()) }
    }

    override fun pen(width: Float) {
        graphics { stroke = BasicStroke(width, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND) }
    }

    override fun turtle(x: Float, y: Float, angle: Float, isVisible: Boolean) {
        if (this.isVisible != isVisible) {
            graphics {
                val oldStroke = stroke
                stroke = turtle.stroke
                setXORMode(Color.WHITE)

                turtle.draw(this, x to y, angle)

                setPaintMode()
                stroke = oldStroke
            }
            this.isVisible = isVisible
        }
    }

    private val turtle = object {
        private val zero = 0.0 to 0.0
        private val poly = listOf(-5 to 10, 15 to 0, -5 to -10)
        val stroke = BasicStroke(2f)

        fun draw(graphics: Graphics2D, pos: Pair<Float, Float>, angle: Float) {
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

        private fun Graphics2D.line(base: Pair<Float, Float>, from: Pair<Double, Double>, to: Pair<Double, Double>) {
            drawLine(
                (base.first + from.first).roundToInt(),
                (base.second + from.second).roundToInt(),
                (base.first + to.first).roundToInt(),
                (base.second + to.second).roundToInt()
            )
        }
    }
}
