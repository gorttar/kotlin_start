package course.ps3

/**
 * Второй напишите функцию [getGuessedWord]. Требования к функции описаны в её документации.
 *
 * запусти, чтобы протестировать функцию [getGuessedWord]
 */
fun main() {

}

object GetGuessedWordSample {
    @JvmStatic
    fun main(args: Array<String>) {
        /**
         * пример использования функции [getGuessedWord]
         */
        println(getGuessedWord("apple", setOf('e', 'i', 'k', 'p', 'r', 's')))/*должно вывести _ pp_ e*/
    }
}