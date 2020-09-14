package course.lecture9

import kotlin.random.Random
import kotlin.random.nextInt

abstract class Animal(val age: Int, val name: String) {
    abstract fun speak()
    override fun toString(): String = "Animal(age=$age, name=$name)"
}

class Cat(age: Int, name: String) : Animal(age, name) {
    override fun speak(): Unit = meow()
    override fun toString(): String = "Cat(age=$age, name=$name)"
    fun meow(): Unit = println("мяу")
    fun animalToString(): String = super.toString()
}

class Rabbit(age: Int, name: String, val parent1: Rabbit? = null, val parent2: Rabbit? = null) : Animal(age, name) {
    val rid: Int = tag++

    override fun speak(): Unit = meep()
    override fun toString(): String = "Rabbit(age=$age, name=$name)"
    fun meep(): Unit = println("meep")

    override fun equals(other: Any?): Boolean = this === other ||
        other is Rabbit && other.javaClass == Rabbit::class.java && (
        parent1?.rid == other.parent1?.rid && parent2?.rid == other.parent2?.rid ||
            parent1?.rid == other.parent2?.rid && parent2?.rid == other.parent1?.rid
        )

    override fun hashCode(): Int = parent1?.rid.hashCode() * 31 + parent2?.rid.hashCode()

    companion object {
        private var tag = 1
    }
}

open class Person(name: String, age: Int) : Animal(age, name) {
    private val friends: MutableSet<String> = mutableSetOf()
    override fun speak(): Unit = hello()
    override fun toString(): String = "Person(name=$name, age=$age, friends=$friends)"
    open fun hello(): Unit = println("привет")
    fun getFriends(): List<String> = friends.toList()
    fun addFriend(name: String) {
        friends += name
    }

    fun ageDiff(other: Person): Unit = (age - other.age).let {
        when (it) {
            in Int.MIN_VALUE until 0 -> "$name на ${-it} лет моложе, чем ${other.name}"
            in 1..Int.MAX_VALUE -> "$name на $it лет старше, чем ${other.name}"
            else -> "$name и ${other.name} - ровесники"
        }
    }.let(::println)
}

class Student(name: String, age: Int, var major: String? = null) : Person(name, age) {
    override fun hello(): Unit = when (Random.nextInt(1..4)) {
        1 -> "мне надо делать домашнее задание"
        2 -> "мне надо спать"
        3 -> "хочу есть"
        else -> "смотрю телевизор"
    }.let(::println)

    override fun toString(): String = "Student(name=$name, age=$age, major=$major)"
}