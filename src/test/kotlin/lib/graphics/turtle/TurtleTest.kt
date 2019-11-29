package lib.graphics.turtle

import org.testng.Assert.*
import org.testng.annotations.Test
import kotlin.math.roundToInt

class TurtleTest {
    data class Line(
            val w: Float,
            val x1: Int,
            val y1: Int,
            val x2: Int,
            val y2: Int
    )

    private class TestCore: Turtle.Core {
        val lines = mutableListOf<Line>()
        private var penWidth = 1f

        override fun clear() {
            lines.clear()
        }

        override fun line(x1: Float, y1: Float, x2: Float, y2: Float) {
            lines += Line(penWidth, x1.roundToInt(), y1.roundToInt(), x2.roundToInt(), y2.roundToInt())
        }

        override fun pen(width: Float) {
            penWidth = width
        }
    }

    @Test
    fun `Square 100x100`() {
        val core = TestCore()
        Turtle(core) fd 100 rt 90 fd 100 rt 90 fd 100 rt 90 fd 100
        assertEquals(
                listOf(
                        Line(1f, 0, 0, 100, 0),
                        Line(1f, 100, 0, 100, 100),
                        Line(1f, 100, 100, 0, 100),
                        Line(1f, 0, 100, 0, 0)
                ),
                core.lines
        )
    }

    @Test
    fun `Rhombus 50x50 3px thick rotated 45 deg`() {
        val core = TestCore()
        Turtle(core) pw 3 lt 45 bk 50 lt 90 bk 50 lt 90 bk 50 lt 90 bk 50
        assertEquals(
                listOf(
                        Line(3f, 0, 0, -35, 35),
                        Line(3f, -35, 35, 0, 71),
                        Line(3f, 0, 71, 35, 35),
                        Line(3f, 35, 35, 0, 0)
                ),
                core.lines
        )
    }

    @Test
    fun `Cross 20x20`() {
        val core = TestCore()
        Turtle(core)
                .fd(10)
                .pu().bk(10).rt(90).pd()
                .fd(10)
                .pu().bk(10).rt(90).pd()
                .fd(10)
                .pu().bk(10).rt(90).pd()
                .fd(10)

        assertEquals(
                listOf(
                        Line(1f, 0, 0, 10, 0),
                        Line(1f, 0, 0, 0, 10),
                        Line(1f, 0, 0, -10, 0),
                        Line(1f, 0, 0, 0, -10)
                ),
                core.lines
        )
    }
}
