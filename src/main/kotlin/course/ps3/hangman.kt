package course.ps3

import org.gorttar.helpers.alphabet
import org.gorttar.test.FAIL

/**
 * Игра виселица
 */

/**
 * -----------------------------------
 * Вспомогательный код
 * Не обязательно понимать, как именно он работает, но важно понимать, как его использовать,
 * поэтому обязательно внимательно прочесть документацию к функциям
 */

const val WORDLIST_FILENAME = "words.txt"

/**
 * @return список возможных слов. Слова - это строки в нижнем регистре
 *
 * Функции может потребоваться время, чтобы загрузить весь список
 */
fun loadWords(): List<String> = println("Загружаю слова из файла $WORDLIST_FILENAME ...")
    .let {
        Unit.javaClass.classLoader.getResource(WORDLIST_FILENAME)!!.readText().split(" ")
            .map { it.trim().toLowerCase() }
    }
    .apply { println("загружено $size слов") }

/**
 * [wordList] - список возможных слов
 * @return случайное слово из списка
 */
fun chooseWord(wordList: List<String>): String = wordList.random()

/**
 * конец вспомогательного кода
 * -----------------------------------
 */

val wordList by lazy(::loadWords)

/**
 * В первой задаче напишите функцию [isWordGuessed], которая принимает на вход два параметра:
 * [secretWord] - слово, которое пытается угадать игрок. Все буквы строчные
 * [lettersGuessed] - множество букв, которые уже вводил игрок на текущий момент. Все буквы строчные
 * @return true, если все буквы из [secretWord] есть в [lettersGuessed] иначе false
 *
 * Пример использования смотри [тут][IsWordGuessedSample.main]
 *
 * Для проверки задания запускаешь main из файла src/test/kotlin/course/ps3/Problem1Test.kt и смотришь вывод.
 * Он должен быть зелёным, если всё верно
 */
fun isWordGuessed(secretWord: String, lettersGuessed: Set<Char>): Boolean {
    FAIL
}

/**
 * Во второй задаче напишите функцию [getGuessedWord], которая принимает на вход два параметра:
 * [secretWord] - слово, которое пытается угадать игрок. Все буквы строчные
 * [lettersGuessed] - множество букв, которые уже вводил игрок на текущий момент. Все буквы строчные
 * @return строку, состоящую из угаданных букв м подчёркиваний '_' на местах не угаданных букв
 *
 * Для удобства игрока стоит после подчёркивания вставлять минимум один пробел, чтобы пользователю было проще понять,
 * сколько не отгаданных букв в слове (сравните читабельность ____ с _ _ _ _ ). Очень важно учитывать удобство
 * использования при разработке программ. Если пользователю будет тяжело пользоваться программой, он не будет этого
 * делать!
 *
 * Тест не учитывает пробелы, так что их расстановка остаётся на ваше усмотрение, но всё же стоит учитывать удобство
 * использования.
 *
 * Пример использования смотри [тут][GetGuessedWordSample.main]
 *
 * Для проверки задания запускаешь main из файла src/test/kotlin/course/ps3/Problem2Test.kt и смотришь вывод.
 * Он должен быть зелёным, если всё верно
 */
fun getGuessedWord(secretWord: String, lettersGuessed: Set<Char>): String {
    FAIL
}

/**
 * В третьей задаче напишите функцию [getAvailableLetters], которая принимает на вход параметр:
 * [lettersGuessed] - множество букв, которые уже вводил игрок на текущий момент. Все буквы строчные
 * @return строку, состоящую из букв, которые игрок ещё не вводил в алфавитном порядке
 *
 * для решения вы можете использовать строку [alphabet], состоящую из всех строчных букв в алфавитном порядке
 *
 * Пример использования смотри [тут][GetAvailableLettersSample.main]
 *
 * Для проверки задания запускаешь main из файла src/test/kotlin/course/ps3/Problem3Test.kt и смотришь вывод.
 * Он должен быть зелёным, если всё верно
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