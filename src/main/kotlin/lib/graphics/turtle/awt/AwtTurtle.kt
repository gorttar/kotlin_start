package lib.graphics.turtle.awt

import lib.graphics.turtle.Turtle
import java.awt.Component
import java.awt.Graphics2D
import java.awt.GraphicsEnvironment
import javax.swing.JFrame

object AwtTurtle {
    /**
     * Create a window (AWT [JFrame]) and a [Turtle] bound to it.
     * By default, window gets size and title set,
     * is displayed immediately at the screen center,
     * and causes program termination upon closing.
     */
    fun create(): Pair<Turtle, JFrame> {
        val frame = JFrame().also {
            it.title = "Turtle"
            it.setSize(512, 512)
            it.isVisible = true

            it.defaultCloseOperation = JFrame.EXIT_ON_CLOSE

            val screens = GraphicsEnvironment.getLocalGraphicsEnvironment().screenDevices
            val screenRect = screens.first().defaultConfiguration.bounds
            val frameSize = it.size
            it.setLocation(
                    screenRect.x + (screenRect.width - frameSize.width) / 2,
                    screenRect.y + (screenRect.height - frameSize.height) / 2
            )
        }
        return turtle(frame) to frame
    }

    /**
     * [Turtle] interface over AWT [Component]
     */
    fun turtle(component: Component): Turtle {
        // Every Graphics in runtime is actually Graphics2D
        val graphics = component.graphics as Graphics2D
        val size = component.size
        return Turtle(
            x = size.width * 0.5f,
            y = size.height * 0.5f,
            core = GraphicsTurtleCore(graphics, size)
        )
    }
}
