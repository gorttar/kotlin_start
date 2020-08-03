package course.ps3

data class Case<E>(val secretWord: String, val lettersGuessed: Set<Char>, val expected: E)