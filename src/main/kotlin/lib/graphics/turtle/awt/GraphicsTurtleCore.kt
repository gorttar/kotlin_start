package lib.graphics.turtle.awt

import lib.graphics.turtle.Turtle
import java.awt.BasicStroke
import java.awt.Dimension
import java.awt.EventQueue
import java.awt.Graphics2D
import kotlin.math.roundToInt

/**
 * [Turtle.Core] interface over AWT [Graphics2D]
 */
class GraphicsTurtleCore(
    graphics: Graphics2D,
    private val size: Dimension
): Turtle.Core {
    private val graphics = object {
        // Force all interactions with Graphics2D to happen in  UI thread
        operator fun invoke(block: Graphics2D.() -> Unit) {
            EventQueue.invokeAndWait {
                graphics.block()
            }
        }
    }

    override fun clear() {
        graphics { clearRect(0, 0, size.width, size.height) }
    }

    override fun line(x1: Float, y1: Float, x2: Float, y2: Float) {
        graphics { drawLine(x1.roundToInt(), y1.roundToInt(), x2.roundToInt(), y2.roundToInt()) }
    }

    override fun pen(width: Float) {
        graphics { stroke = BasicStroke(width) }
    }
}
