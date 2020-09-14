package course.lecture9

fun main() {
    val eric = Person("Eric", 45)
    val john = Person("John", 55)
    eric.speak() // выведет "привет"
    eric.ageDiff(john) // выведет "Eric на 10 лет моложе, чем John"
    john.ageDiff(eric) // выведет "John на 10 лет старше, чем Eric"
}