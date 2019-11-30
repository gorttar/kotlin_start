package lib.graphics.turtle

import lib.graphics.turtle.Turtle.Core
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

/**
 * Provides [basic set](https://www.tutorialspoint.com/logo/logo_turtle.htm )
 * of [Turtle graphics](https://en.wikipedia.org/wiki/Turtle_graphics) commands
 * over wrapped [Core] instance
 */
class Turtle(
    private val core: Core,
    private var x: Float = 0f,
    private var y: Float = 0f,
    private var angle: Float = 0f,
    private var isDown: Boolean = true
) {
    /**
     * Set of core operations that other [Turtle] operations are translated to
     */
    interface Core {
        fun clear()
        fun line(x1: Float, y1: Float, x2: Float, y2: Float)
        fun pen(width: Float)
        fun turtle(x: Float, y: Float, angle: Float, isVisible: Boolean)
    }

    /**
     * Move forward by number of pixels specified by [distance]
     */
    infix fun fd(distance: Number): Turtle {
        show(false)

        val d = distance.toFloat()
        val r = (angle * PI / 180).toFloat()
        val nx = x + cos(r) * d
        val ny = y + sin(r) * d
        if (isDown) {
            core.line(x, y, nx, ny)
        }
        x = nx
        y = ny

        show(true)

        return this
    }

    /**
     * Move backward by number of pixels specified by [distance]
     */
    infix fun bk(distance: Number) = fd(-distance.toFloat())

    /**
     * Rotate turtle right by [degrees]
     */
    infix fun rt(degrees: Number): Turtle {
        show(false)
        angle += degrees.toFloat()
        angle %= 360
        show(true)
        return this
    }

    /**
     * Rotate turtle left by [degrees]
     */
    infix fun lt(degrees: Number) = rt(-degrees.toFloat())

    /**
     * Set width of line drawn by turtle
     */
    infix fun pw(penWidth: Number): Turtle {
        core.pen(penWidth.toFloat())
        return this
    }

    /**
     * Pen up, i. e. move but not draw
     */
    fun pu(): Turtle {
        isDown = false
        return this
    }

    /**
     * Pen down, i. e. draw when moving
     */
    fun pd(): Turtle {
        isDown = true
        return this
    }

    /**
     * Clear screen
     */
    fun cs(): Turtle {
        core.clear()
        show(true)
        return this
    }

    private fun show(isVisible: Boolean) = core.turtle(x, y, angle, isVisible)
}
