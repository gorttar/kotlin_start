package course.lecture6

fun main() {
    val nameToGrade = mapOf("Ana" to "B", "John" to "A+", "Denise" to "A", "Katy" to "A")
    println("Размер карты $nameToGrade: ${nameToGrade.size}")
    println("По ключу \"Ana\" в карте $nameToGrade лежит значение ${nameToGrade["Ana"]}")
    println("По ключу \"Alex\" в карте $nameToGrade лежит значение ${nameToGrade["Alex"]}")
    println("По ключу \"Ana\" в карте $nameToGrade лежит значение ${nameToGrade.getValue("Ana")}")
    try {
        println("По ключу \"Alex\" в карте $nameToGrade лежит значение ${nameToGrade.getValue("Alex")}")
    } catch (e: RuntimeException) {
        println("Выполнение nameToGrade.getValue(\"Alex\") привело к ошибке '${e.message}'")
    }
    println("По ключу \"Ana\" в карте $nameToGrade лежит значение ${nameToGrade.getOrDefault("Ana", "absent")}")
    println("По ключу \"Alex\" в карте $nameToGrade лежит значение ${nameToGrade.getOrDefault("Alex", "absent")}")
    println("Проверка, что ключ \"Ana\" есть в карте $nameToGrade даст ${"Ana" in nameToGrade}")
    println("Проверка, что ключ \"Alex\" есть в карте $nameToGrade даст ${"Alex" in nameToGrade}")
}
