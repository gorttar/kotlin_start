package course.visible

import course.visible.VisObj.iprintln
import course.visible.VisObj.privUnavailable
import course.visible.VisObj.protUnavailable

private const val internUnavailable = "-Внутреннее поле intern не доступно за пределом модуля"

private object VisNotModuleObj : Visible() {
    override operator fun invoke() {
        println("Мы в объекте, являющемся наследником класса course.visible.Visible")
        println("Мы в другом модуле, нежели класс course.visible.Visible (модуль visible)")
        iprintln(pub)
        iprintln(internUnavailable)
        iprintln(prot)
        iprintln(privUnavailable)
    }
}

private object NotVisNotModuleObj : () -> Unit by {
    with(Visible()) {
        println("Мы в другом модуле, нежели класс course.visible.Visible (модуль visible)")
        iprintln(pub)
        iprintln(internUnavailable)
        iprintln(protUnavailable)
        iprintln(privUnavailable)
    }
}

fun main() = sequenceOf(Visible(), VisObj, NotVisObj, VisNotModuleObj, NotVisNotModuleObj).forEach {
    it()
    println()
}