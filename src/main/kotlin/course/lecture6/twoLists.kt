package course.lecture6

fun getGrade(name: String, names: List<String>, grades: List<String>): String {
    val index = names.indexOf(name)
    return grades[index]
}
