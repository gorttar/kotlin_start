package lib.graphics.turtle

import lib.graphics.turtle.awt.AwtTurtle

/**
 * @author Andrey Antipov (gorttar@gmail.com) (2019-11-24)
 */
fun main() {
    val (turtle, _) = AwtTurtle.create()
    turtle pw 3 fd 50 rt 90 fd 50 rt 90 fd 50 rt 90 fd 50
}