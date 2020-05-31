package course.lecture6

fun main() {
    val nameToGrade = mutableMapOf("Ana" to "B", "John" to "A+", "Denise" to "A", "Katy" to "A")
    println("Начальное состояние карты $nameToGrade")

    // добавление записи в карту
    nameToGrade["Alex"] = "A-"
    println("После добавления записи $nameToGrade")

    // изменение записи
    nameToGrade["Alex"] = "C+"
    println("После изменения записи $nameToGrade")

    // удаление записи
    nameToGrade.remove("Alex")
    println("После удаления записи $nameToGrade")
}
