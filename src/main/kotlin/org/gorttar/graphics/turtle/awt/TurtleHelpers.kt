package org.gorttar.graphics.turtle.awt

import org.gorttar.graphics.turtle.Turtle
import org.gorttar.graphics.turtle.TurtleState
import java.awt.*
import java.awt.image.BufferedImage
import java.time.Duration
import javax.swing.JFrame

typealias Window = JFrame

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

/** buffered [Turtle] over [Container] */
fun Container.turtle(): Turtle = BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB).let { image ->
    val imageCanvas = object : Canvas() {
        override fun paint(g: Graphics) {
            log("paint started")
            g.drawImage(image, 0, 0, this)
            log("paint finished")
        }
    }.also {
        add(it)
        it.size = size
    }

    image.graphics.fillRect(0, 0, size.width, size.height)

    val state = TurtleState(x = size.width * 0.5, y = size.height * 0.5)
    Turtle(
        core = RepaintCoreDecorator(
            delegate = GraphicsTurtleCore(image.graphics, size, state),
            minInterval = Duration.ofMillis(25),
            doRepaint = { EventQueue.invokeAndWait(imageCanvas::repaint) }
        ),
        state = state
    )
}
