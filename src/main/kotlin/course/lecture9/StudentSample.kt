package course.lecture9

fun main() {
    val fred = Student("Fred", 21, "Course VI")
    println(fred) // выведет "Student(name=Fred, age=21, major=Course VI)"
    repeat(4) { fred.speak() } // выведет 4 случайных сообщения
}