package org.gorttar.test.io

import org.gorttar.test.io.IOScenario.Companion.runIOTest

fun main() {
    runIOTest({
        expectLine("Start")
        sendLine("errHello")
        expectErrLine("ErrRead: errHello")
        sendLine("hello")
        expectLine(
                """Read: hello
                |End
                """.trimMargin()
        )
    }) {
        println("Start")
        System.err.println("ErrRead: ${readLine()}")
        println("Read: ${readLine()}")
        println("End")
    }
}

