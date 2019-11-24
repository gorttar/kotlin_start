package lib.graphics.turtle

import java.awt.Frame
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import java.awt.event.WindowEvent.WINDOW_CLOSING
import java.util.concurrent.TimeUnit
import kotlin.system.exitProcess

/**
 * @author Andrey Antipov (gorttar@gmail.com) (2019-11-24)
 */
fun main() {
    val window = Frame("Hello world").apply {
        addWindowListener(object : WindowAdapter() {
            override fun windowClosing(e: WindowEvent) {
                println(e.id)
                if (e.id == WINDOW_CLOSING) exitProcess(0)
            }
        })
        setSize(300, 300)
    }
    window.isVisible = true
    TimeUnit.SECONDS.sleep(1)
    window.title = "Hello awt"
}