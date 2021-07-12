package course.ps3

import course.languageDependent

object GetAvailableLettersSample {
    @JvmStatic
    fun main(args: Array<String>): Unit = languageDependent(
        ::getAvailableLetters,
        Hangman::getAvailableLetters
    ) { getAvailableLetters ->
        /**
         * пример использования функции [getAvailableLetters]
         */
        println(getAvailableLetters(setOf('e', 'i', 'k', 'p', 'r', 's')))/*должно вывести abcdfghjlmnoqtuvwxyz*/
    }
}