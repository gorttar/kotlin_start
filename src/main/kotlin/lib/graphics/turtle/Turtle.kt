package lib.graphics.turtle

import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

/**
 * Provides [basic set](https://www.tutorialspoint.com/logo/logo_turtle.htm )
 * of [Turtle graphics](https://en.wikipedia.org/wiki/Turtle_graphics) commands
 * over wrapped [TurtleCore] instance
 */
class Turtle(
    private val core: TurtleCore,
    private var x: Double = 0.0,
    private var y: Double = 0.0,
    private var angle: Double = 0.0,
    private var isDown: Boolean = true
) {

    /**
     * Move forward by number of pixels specified by [distance]
     */
    infix fun fd(distance: Number): Turtle {
        show()

        val d = distance.toDouble()
        val r = angle * PI / 180
        val nx = x + cos(r) * d
        val ny = y + sin(r) * d
        if (isDown) {
            core.line(x, y, nx, ny)
        }
        x = nx
        y = ny

        show()

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
        show()
        angle += degrees.toFloat()
        angle %= 360
        show()
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
        show()
        return this
    }

    private fun show() = core.turtle(x, y, angle)
}

/**
 * Set of core operations that other [Turtle] operations are translated to
 */
interface TurtleCore {
    fun clear()
    fun line(x1: Double, y1: Double, x2: Double, y2: Double)
    fun pen(width: Float)
    fun turtle(x: Double, y: Double, angle: Double)
}