package lib.graphics.turtle.awt

import lib.graphics.turtle.Turtle
import java.awt.Component
import java.awt.Graphics2D
import java.awt.GraphicsEnvironment
import javax.swing.JFrame

object AwtTurtle {
    /**
     * Create a window (AWT [JFrame]).
     * By default, window gets size and title set,
     * is displayed immediately at the screen center,
     * and causes program termination upon closing.
     */
    fun initWindow(): JFrame = JFrame().apply {
        title = "Turtle"
        val side = 650
        setSize(side, side)
        isVisible = true

        defaultCloseOperation = JFrame.EXIT_ON_CLOSE

        val screenRect = GraphicsEnvironment.getLocalGraphicsEnvironment().screenDevices.first()
            .defaultConfiguration.bounds
        val frameSize = size
        setLocation(
            screenRect.x + (screenRect.width - frameSize.width) / 2,
            screenRect.y + (screenRect.height - frameSize.height) / 2
        )
    }

    /**
     * [Turtle] interface over AWT [Component]
     */
    fun Component.turtle(): Turtle {
        // Every Graphics in runtime is actually Graphics2D
        val graphics = graphics as Graphics2D
        val size = size
        return Turtle(
            x = size.width * 0.5f,
            y = size.height * 0.5f,
            core = GraphicsTurtleCore(graphics, size)
        )
    }
}
