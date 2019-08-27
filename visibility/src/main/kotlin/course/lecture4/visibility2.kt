package course.lecture4

import intern
import pub

/**
 * не получится импортировать, так как переменная priv приватна, то есть доступна
 * только в файле visibility/src/main/kotlin/course/lecture4/visibility.kt
 */
//import priv

fun main() {
    println(pub)
    println(intern)
}