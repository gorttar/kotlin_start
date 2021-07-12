package course.ps3

import course.languageDependent

object IsWordGuessedSample {
    @JvmStatic
    fun main(args: Array<String>): Unit = languageDependent(
        ::isWordGuessed,
        Hangman::isWordGuessed
    ) { isWordGuessed ->
        /**
         * пример использования функции [isWordGuessed]
         */
        println(isWordGuessed("apple", setOf('e', 'i', 'k', 'p', 'r', 's')))/*должно вывести false*/
    }
}