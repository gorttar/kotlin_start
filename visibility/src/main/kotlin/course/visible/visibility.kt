package course.visible

import course.visible.VisObj.iprintln
import course.visible.VisObj.privUnavailable
import course.visible.VisObj.protUnavailable

open class Visible : () -> Unit {
    val pub = "+Поле pub доступно везде"
    internal val intern = "+Поле intern доступно только в модуле visibility"
    protected val prot = "+Поле prot доступно для класса и его наследников"
    private val priv = "+Поле priv доступно только в классе course.visible.Visible"

    override fun invoke() {
        println("Мы в объекте класса course.visible.Visible")
        iprintln(pub)
        iprintln(intern)
        iprintln(prot)
        iprintln(priv)
    }
}

object VisObj : Visible() {
    const val protUnavailable = "-Защищенное поле prot не доступно не наследникам класса course.visible.Visible"
    const val privUnavailable = "-Приватное поле priv не доступно за пределами класса course.visible.Visible"

    override operator fun invoke() {
        println("Мы в объекте, являющемся наследником класса course.visible.Visible")
        println("Мы в том же модуле, что и класс course.visible.Visible (модуль visible)")
        iprintln(pub)
        iprintln(intern)
        iprintln(prot)
        iprintln(privUnavailable)
    }

    fun iprintln(message: Any?, indent: Int = 1) = println("${"\t".repeat(indent)}$message")
}

object NotVisObj : () -> Unit by {
    with(Visible()) {
        println("Мы в том же модуле, что и класс course.visible.Visible (модуль visible)")
        iprintln(pub)
        iprintln(intern)
        iprintln(protUnavailable)
        iprintln(privUnavailable)
    }
}