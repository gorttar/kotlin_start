package course.ps3

import lib.repr.repr

class TestArgs(val secretWord: String, val lettersGuessed: Set<Char>) {
    override fun toString(): String = "Test: secretWord=${secretWord.repr}, lettersGuessed=${lettersGuessed.repr}"
}

@Suppress("MemberVisibilityCanBePrivate")
class TestCase<E>(secretWord: String, lettersGuessed: Set<Char>, val expected: E) {
    val args: TestArgs = TestArgs(secretWord, lettersGuessed)
    @Suppress("unused")
    val repr by lazy {
        "TestCase(${secretWord.repr}, setOf(${lettersGuessed.map { it.repr }.joinToString()}), ${expected.repr})"
    }

    operator fun component1(): TestArgs = args
    operator fun component2(): E = expected
}