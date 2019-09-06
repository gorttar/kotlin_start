package course.ps3

object GetAvailableLettersSample {
    @JvmStatic
    fun main(args: Array<String>) {
        /**
         * пример использования функции [getAvailableLetters]
         */
        println(getAvailableLetters(setOf('e', 'i', 'k', 'p', 'r', 's')))/*должно вывести abcdfghjlmnoqtuvwxyz*/
    }
}