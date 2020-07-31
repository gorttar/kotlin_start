package org.gorttar.graphics.turtle.awt

import org.gorttar.graphics.turtle.TurtleCore
import java.time.Duration
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Triggers [doRepaint] after wrapped [delegate] operations.
 * Caps [doRepaint] calls frequency to once in [minInterval].
 */
class RepaintCoreDecorator(
    private val delegate: TurtleCore,
    private val minInterval: Duration,
    private val doRepaint: () -> Unit
) : TurtleCore {
    private val timer = Executors.newSingleThreadScheduledExecutor()
    private val paintScheduled = AtomicBoolean(false)
    private val paintTask = Runnable {
        doRepaint()
        paintScheduled.set(false)
    }

    private fun repaintIfNecessary() {
        if (paintScheduled.compareAndSet(false, true)) {
            timer.schedule(paintTask, minInterval.toMillis(), TimeUnit.MILLISECONDS)
        }
    }

    override fun clear() {
        delegate.clear()
        repaintIfNecessary()
    }

    override fun line(x1: Double, y1: Double, x2: Double, y2: Double) {
        delegate.line(x1, y1, x2, y2)
        repaintIfNecessary()
    }

    override fun pen(width: Float) {
        delegate.pen(width)
    }

    override fun showTurtle() {
        delegate.showTurtle()
        repaintIfNecessary()
    }
}
