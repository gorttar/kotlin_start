package lib.graphics.turtle.awt

import java.time.LocalDateTime

fun log(msg: String): Unit {
    val thread = Thread.currentThread()
    val now = LocalDateTime.now()
    println("$now $thread $msg")
}