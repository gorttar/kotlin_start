package course.lecture4

fun main() {
    /**
     * Спецификация функции:
     * Ввод [i] положительное целое [Int]
     * @return true если [i] чётное, иначе false
     */
    fun/*ключевое слово*/ isEven/*имя*/(i: Int/*параметры*/): Boolean/*тип результата*/ {
        println("hi")
        return/*ключевое слово*/ i % 2 == 0/*выражение, значение которого вычисляется и возвращается из функции*/
    }/*тело функции - блок кода, ограниченный фигурными скобками*/

    println(
            "isEven(3) = ${
            isEven(3)/*вызов функции*/
            }"
    )
}