package course.ps3

object GetGuessedWordSample {
    @JvmStatic
    fun main(args: Array<String>) {
        /**
         * пример использования функции [getGuessedWord]
         */
        println(getGuessedWord("apple", setOf('e', 'i', 'k', 'p', 'r', 's')))/*должно вывести _ pp_ e*/
    }
}