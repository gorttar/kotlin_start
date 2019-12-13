package lib.graphics.turtle.awt

import lib.graphics.turtle.Turtle
import lib.graphics.turtle.TurtleState
import java.awt.Component
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

    /** [Turtle] over [Component] */
    fun Component.turtle(): Turtle = TurtleState(x = size.width * 0.5, y = size.height * 0.5).let { state ->
        Turtle(
            core = GraphicsTurtleCore(graphics, size, state),
            state = state
        )
    }
}
