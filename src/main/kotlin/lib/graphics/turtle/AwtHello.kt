package lib.graphics.turtle

import lib.graphics.turtle.awt.AwtTurtle.initAndCreateTurtle
import javax.swing.JFrame

/**
 * @author Andrey Antipov (gorttar@gmail.com) (2019-11-24)
 */
fun main() {
    val turtle = JFrame().initAndCreateTurtle()
    turtle pw 3 fd 50 rt 90 fd 50 rt 90 fd 50 rt 90 fd 50
}