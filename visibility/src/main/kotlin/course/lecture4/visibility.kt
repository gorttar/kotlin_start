val pub = "Переменная pub доступна везде"
internal val intern = "Переменная intern доступна только в модуле visibility"
private val priv = "Переменная priv доступна только в файле visibility/src/main/kotlin/course/lecture4/visibility.kt"

fun main() {
    println(pub)
    println(intern)
    println(priv)
}