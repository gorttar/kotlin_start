package course.lecture9

data class User(val name: String, val age: Int)

data class Human(val name: String) {
    var age: Int = 0
}

fun main() {
    val human1 = Human("Андрей")
    val human2 = Human("Андрей")
    human1.age = 10
    human2.age = 20
    println(human1)                     // выведет "Human(name=Андрей)"
    println(human1 == human2)           // выведет "true"

    val andrey = User(name = "Андрей", age = 1)
    val olderAndrey = andrey.copy(age = 2)
    println(andrey)                     // выведет "User(name=Андрей, age=1)"
    println(olderAndrey)                // выведет "User(name=Андрей, age=2)"

    val ivan = User("Иван", 30)
    val (name, age) = ivan              // мульти-декларация
    println("$name, возраст $age лет")  // выведет "Иван, возраст 30 лет"
}