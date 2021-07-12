package course.ps3

import course.languageDependent

object GetGuessedWordSample {
    @JvmStatic
    fun main(args: Array<String>): Unit = languageDependent(
        ::getGuessedWord,
        Hangman::getGuessedWord
    ) { getGuessedWord ->
        /**
         * пример использования функции [getGuessedWord]
         */
        println(getGuessedWord("apple", setOf('e', 'i', 'k', 'p', 'r', 's')))/*должно вывести _ pp_ e*/
    }
}