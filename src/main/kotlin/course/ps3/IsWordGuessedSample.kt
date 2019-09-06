package course.ps3

object IsWordGuessedSample {
    @JvmStatic
    fun main(args: Array<String>) {
        /**
         * пример использования функции [isWordGuessed]
         */
        println(isWordGuessed("apple", setOf('e', 'i', 'k', 'p', 'r', 's')))/*должно вывести false*/
    }
}