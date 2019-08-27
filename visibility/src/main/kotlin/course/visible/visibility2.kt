package course.visible

import course.visible.Visible.intern
import course.visible.Visible.pub

/**
 * не получится импортировать, так как переменная priv приватна, то есть доступна
 * только в объекте course.visible.Visible
 */
//import course.visible.Visible.priv

fun main() {
    println(pub)
    println(intern)
//    println(priv)
}