package lib.graphics.turtle.awt

import lib.graphics.turtle.Turtle
import java.awt.Component
import java.awt.Graphics2D
import java.awt.GraphicsEnvironment
import javax.swing.JFrame

typealias Window = JFrame

object AwtTurtle {
    /**
     * Create a [Window] which has
     * *    [JFrame.width] = [JFrame.height] = [side]
     * *    [JFrame.title] = [name]
     * *    is displayed immediately at the first screen center
     * *    causes program termination upon closing
     */
    fun createSquareWindow(side: Int = 650, name: String = "Turtle"): Window = Window().apply {
        title = name
        setSize(side, side)
        isVisible = true

        defaultCloseOperation = Window.EXIT_ON_CLOSE

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
            x = size.width * 0.5,
            y = size.height * 0.5,
            core = GraphicsTurtleCore(graphics, size)
        )
    }
}
