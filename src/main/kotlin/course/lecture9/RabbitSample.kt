package course.lecture9

fun main() {
    val peter = Rabbit(2, "Peter")
    val hopsy = Rabbit(3, "Hopsy")
    val cotton = Rabbit(1, "Cottontail", peter, hopsy)
    println(cotton) // выведет "Rabbit(age=1, name=Cottontail)"
    println(cotton.parent1) // выведет "Rabbit(age=2, name=Peter)"
    val mopsy = Rabbit(0, "Mopsy", hopsy, peter)
    println(mopsy == cotton) // выведет true, так как у cotton и mopsy одни и те же родители
}