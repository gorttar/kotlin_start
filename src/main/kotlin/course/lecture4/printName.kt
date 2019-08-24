package course.lecture4

fun main() {
    fun printName(firstName: String, lastName: String, reverse: Boolean) =
            if (reverse) println("$lastName, $firstName") else println("$firstName, $lastName")

    printName("Андрей", "Антипов", false)
    printName("Андрей", "Антипов", reverse = false)
    printName("Андрей", lastName = "Антипов", reverse = false)
    printName(reverse = false, lastName = "Антипов", firstName = "Андрей")
}