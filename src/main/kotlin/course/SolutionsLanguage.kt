package course

import course.SolutionsLanguage.JAVA
import course.SolutionsLanguage.KOTLIN

enum class SolutionsLanguage {
    KOTLIN, JAVA
}

val currentSolutionLanguage: SolutionsLanguage = JAVA

inline fun <T : Function<*>, R> languageDependent(kotlin: T, java: T, block: (T) -> R): R = block(
    when (currentSolutionLanguage) {
        KOTLIN -> kotlin
        JAVA -> java
    }
)