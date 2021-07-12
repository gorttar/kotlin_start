package course

import course.SolutionsLanguage.JAVA
import course.SolutionsLanguage.KOTLIN

enum class SolutionsLanguage {
    KOTLIN, JAVA
}

val currentSolutionLanguage: SolutionsLanguage = JAVA

fun <T : Function<*>> languageDependent(kotlinCase: T, javaCase: T): T = when (currentSolutionLanguage) {
    KOTLIN -> kotlinCase
    JAVA -> javaCase
}