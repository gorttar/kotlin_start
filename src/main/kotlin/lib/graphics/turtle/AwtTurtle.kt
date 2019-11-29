package lib.graphics.turtle

import java.awt.BasicStroke
import java.awt.Component
import java.awt.Graphics2D
import java.awt.GraphicsEnvironment
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.JFrame
import kotlin.math.roundToInt
import kotlin.system.exitProcess

/**
 * [Turtle] interface over AWT [Component]
 */
class AwtTurtle<T: Component>(val component: T) {
    companion object {
        /**
         * Create a window (AWT [JFrame]) and a [Turtle] bound to it.
         * By default, window gets size and title set,
         * is displayed immediately at the screen center,
         * and causes program termination upon closing.
         * This can be customized by providing [init] code block(s).
         */
        fun create(
            vararg init: JFrame.() -> Unit = arrayOf(basicInit, centerToScreen, exitOnClose, weirdDelay)
        ): AwtTurtle<JFrame> {
            val frame = JFrame()
            init.forEach { it(frame) }
            return AwtTurtle(frame)
        }

        val basicInit = { it: JFrame ->
            it.title = "Turtle"
            it.setSize(512, 512)
            it.isVisible = true
        }

        val centerToScreen = { it: JFrame ->
            val screens = GraphicsEnvironment.getLocalGraphicsEnvironment().screenDevices
            val screenRect = screens.first().defaultConfiguration.bounds
            val frameSize = it.size
            it.setLocation(
                    screenRect.x + (screenRect.width - frameSize.width) / 2,
                    screenRect.y + (screenRect.height - frameSize.height) / 2
            )
        }

        val exitOnClose = { it: JFrame ->
            it.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
            it.addWindowListener(object : WindowAdapter() {
                override fun windowClosing(e: WindowEvent) {
                    if (e.id == WindowEvent.WINDOW_CLOSING) {
                        exitProcess(0)
                    }
                }
            })
        }

        val weirdDelay = { _: JFrame ->
            //??? give UI thread time to init window?
            Thread.sleep(0)
        }
    }

    /**
     * [Turtle] bound to the [component]
     */
    val turtle = Turtle(
            x = component.width * 0.5f,
            y = component.height * 0.5f,
            core = object: Turtle.Core {
                // Every Graphics in runtime is actually Graphics2D
                private val graphics = component.graphics as Graphics2D

                override fun clear() {
                    graphics.clearRect(0, 0, component.width, component.height)
                }

                override fun line(x1: Float, y1: Float, x2: Float, y2: Float) {
                    graphics.drawLine(x1.roundToInt(), y1.roundToInt(), x2.roundToInt(), y2.roundToInt())
                }

                override fun pen(width: Float) {
                    graphics.stroke = BasicStroke(width)
                }
            }
    )
}
