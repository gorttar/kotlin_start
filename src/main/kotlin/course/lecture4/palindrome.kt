package course.lecture4

fun main() {
    fun isPalindrome(s: String): Boolean {
        fun letters(s: String): String = s.toLowerCase().filter { c -> c in 'a'..'z' }
        fun isPal(s: String): Boolean =
                if (s.length <= 1) true
                else s.first() == s.last() && isPal(s.drop(1).dropLast(1))

        return isPal(letters(s))
    }

    println("")
    println("Является ли \"eve\" палиндромом?")
    println(isPalindrome("eve"))

    println("")
    println("Является ли \"able was I ere I saw Elba\" палиндромом?")
    println(isPalindrome("Able was I, ere I saw Elba"))
}