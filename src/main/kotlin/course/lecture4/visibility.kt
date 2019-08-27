package course.lecture4

import pub

/**
 * не получится импортировать, так как переменная intern внутренняя, то есть доступна
 * только в модуле visibility
 */
//import intern

/**
 * не получится импортировать, так как переменная priv приватна, то есть доступна
 * только в файле visibility/src/main/kotlin/course/lecture4/visibility.kt
 */
//import priv

fun main() {
    println(pub)
}