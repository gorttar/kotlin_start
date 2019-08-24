package course.lecture4

fun main() {
    fun printName(firstName: String, lastName: String, reverse: Boolean = false) =
            if (reverse) println("$lastName, $firstName") else println("$firstName, $lastName")

    printName("Андрей", "Антипов")
    printName("Андрей", "Антипов", true)
}