package org.gorttar.graphics.turtle.awt

import java.time.LocalDateTime

fun log(msg: String) {
    val thread = Thread.currentThread()
    val now = LocalDateTime.now()
    println("$now $thread $msg")
}