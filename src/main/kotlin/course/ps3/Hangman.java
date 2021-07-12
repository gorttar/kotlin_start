package course.ps3;

import org.gorttar.helpers.StringsKt;

import java.util.Set;

import static course.ps3.HangmanKt.chooseWord;
import static course.ps3.HangmanKt.getWordList;
import static org.gorttar.test.HelpersKt.fail;

/**
 * Игра виселица
 * для реализации можно воспользоваться вспомогательными функциями и константами:
 * {@link HangmanKt#WORDLIST_FILENAME}
 * {@link HangmanKt#loadWords}
 * {@link HangmanKt#chooseWord}
 * {@link HangmanKt#getWordList}
 */
public class Hangman {
    /**
     * В первой задаче напишите функцию, которая принимает на вход два параметра:
     *
     * @param secretWord     - слово, которое пытается угадать игрок. Все буквы строчные
     * @param lettersGuessed - множество букв, которые уже вводил игрок на текущий момент. Все буквы строчные
     * @return true, если все буквы из secretWord есть в lettersGuessed, иначе false
     * <p>
     * Пример использования {@link IsWordGuessedSample#main}
     * <p>
     * Для проверки задания запускаешь main из файла src/test/kotlin/course/ps3/Problem1Test.kt и смотришь вывод.
     * Он должен быть зелёным, если всё верно
     */
    public static boolean isWordGuessed(String secretWord, Set<Character> lettersGuessed) {
        return fail();
    }

    /**
     * Во второй задаче напишите функцию, которая принимает на вход два параметра:
     *
     * @param secretWord     - слово, которое пытается угадать игрок. Все буквы строчные
     * @param lettersGuessed - множество букв, которые уже вводил игрок на текущий момент. Все буквы строчные
     * @return строку, состоящую из угаданных букв м подчёркиваний '_' на местах не угаданных букв
     * <p>
     * Для удобства игрока стоит после подчёркивания вставлять минимум один пробел, чтобы пользователю было проще понять,
     * сколько не отгаданных букв в слове (сравните читабельность ____ с _ _ _ _ ). Очень важно учитывать удобство
     * использования при разработке программ. Если пользователю будет тяжело пользоваться программой, он не будет этого
     * делать!
     * <p>
     * Тест не учитывает пробелы, так что их расстановка остаётся на ваше усмотрение, но всё же стоит учитывать удобство
     * использования.
     * <p>
     * Пример использования смотри {@link GetGuessedWordSample#main}
     * <p>
     * Для проверки задания запускаешь main из файла src/test/kotlin/course/ps3/Problem2Test.kt и смотришь вывод.
     * Он должен быть зелёным, если всё верно
     */
    public static String getGuessedWord(String secretWord, Set<Character> lettersGuessed) {
        return fail();
    }

    /**
     * В третьей задаче напишите функцию, которая принимает на вход параметр:
     *
     * @param lettersGuessed - множество букв, которые уже вводил игрок на текущий момент. Все буквы строчные
     * @return строку, состоящую из букв, которые игрок ещё не вводил в алфавитном порядке
     * <p>
     * для решения вы можете использовать строку {@link StringsKt#getAlphabet()},
     * состоящую из всех строчных букв в алфавитном порядке
     * <p>
     * Пример использования смотри {@link GetAvailableLettersSample#main}
     * <p>
     * Для проверки задания запускаешь main из файла src/test/kotlin/course/ps3/Problem3Test.kt и смотришь вывод.
     * Он должен быть зелёным, если всё верно
     */
    public static String getAvailableLetters(Set<Character> lettersGuessed) {
        return fail();
    }

    /**
     * Запускает игру виселица
     * В начале игры выводит на экран, сколько букв в слове [secretWord]
     * В каждом раунде просит пользователя ввести одну букву
     * Сразу после ввода буквы выводит на экран информацию о том, есть эта буква или нет в [secretWord]
     * Также в каждом раунде выводит частично отгаданное [secretWord] и буквы, которые пользователь ещё не вводил
     * Подробнее в описании задачи
     *
     * @param secretWord - слово, которое пытается угадать игрок
     */
    public static void hangman(String secretWord) {
        fail();
    }

    /**
     * после написания функции {@link #hangman} можно запустить и поиграть
     * (подсказка: на время тестирования лучше выбрать не случайное слово в качестве secretWord)
     */
    public static void main(String[] args) {
        String secretWord = chooseWord(getWordList());
        hangman(secretWord);
    }
}
