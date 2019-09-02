package lib.test

import lib.test.IOScenario.IOScenarioBuilder.Companion.ioScenario

fun main() {
    ioScenario {
        name = "IO Test"
        sleep = 100
        expectLine("Start")
        sendLine("hello")
        expectLine("Read: hello")
        expectLine("End")
    }() {
        println("Start")
        println("Read: ${readLine()}")
        println("End")
    }
    println("Test finished")
}

