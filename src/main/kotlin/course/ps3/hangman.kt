package course.ps3

import lib.test.FAIL

/**
 * Игра виселица
 */

/**
 * -----------------------------------
 * Вспомогательный код
 * Не обязательно понимать, как именно он работает, но важно понимать, как его использовать,
 * поэтому обазательно внимательно прочесть документацию к функциям
 */

const val WORDLIST_FILENAME = "words.txt"

/**
 * @return список возможных слов. Слова - это строки в нижнем регистре
 *
 * Функции может потребоваться время, чтобы загрузить весь список
 */
fun loadWords(): List<String> = println("Загружаю слова из файла $WORDLIST_FILENAME ...")
        .let {
            @Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
            Unit.javaClass.classLoader.getResource(WORDLIST_FILENAME)
                    .readText().split(" ").map { it.trim().toLowerCase() }
        }
        .apply { println("   загружено $size слов") }

/**
 * [wordList] - список возможных слов
 * @return случайное слово из списка
 */
fun chooseWord(wordList: List<String>): String = wordList.random()

/**
 * конец вспомогательного кода
 * -----------------------------------
 */

val wordList = loadWords()

/**
 * [secretWord] - слово, которое пытается угадать игрок
 * [lettersGuessed] - множество букв, которые уже вводил игрок на текущий момент
 * @return true, если все буквы из [secretWord] есть в [lettersGuessed] иначе false
 */
fun isWordGuessed(secretWord: String, lettersGuessed: Set<Char>): Boolean {
    FAIL
}

/**
 * [secretWord] - слово, которое пытается угадать игрок
 * [lettersGuessed] - множество букв, которые уже вводил игрок на текущий момент
 * @return строку, состоящую из угаданных букв м подчёркиваний '_' на местах не угаданных букв
 */
fun getGuessedWord(secretWord: String, lettersGuessed: Set<Char>): String {
    FAIL
}

/**
 * [lettersGuessed] - множество букв, которые уже вводил игрок на текущий момент
 * @return строку, состоящую из букв, которые игрок ещё не вводил
 */
fun getAvailableLetters(lettersGuessed: Set<Char>): String {
    FAIL
}

/**
 * [secretWord] - слово, которое пытается угадать игрок
 *
 * Запускает игру виселица
 *
 * В начале игры выводит на экран, сколько букв в слове [secretWord]
 *
 * В каждом раунде просит пользователя ввести одну букву
 *
 * Сразу после ввода буквы выводит на экран информацию о том, есть эта буква или нет в [secretWord]
 *
 * Также в каждом раунде выводит частично отгаданное [secretWord] и буквы, которые пользователь ещё не вводил
 *
 * Подробнее в описании задачи
 */
fun hangman(secretWord: String) {
    FAIL
}

/**
 * после написания функции [hangman] можно запустить [main] и поиграть
 *
 * (подсказка: на время тестирования лучше выбрать не случайное слово в качестве secretWord)
 */
fun main() {
    val secretWord = chooseWord(wordList)
    hangman(secretWord)
}