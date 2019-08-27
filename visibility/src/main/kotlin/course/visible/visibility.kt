package course.visible

object Visible {
    val pub = "Переменная pub доступна везде"
    internal val intern = "Переменная intern доступна только в модуле visibility"
    private val priv = "Переменная priv доступна только в объекте course.visible.Visible"

    @JvmStatic
    fun main(args: Array<String>) {
        println(pub)
        println(intern)
        println(priv)
    }
}