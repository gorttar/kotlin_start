package lib.output

import com.github.ajalt.mordant.AnsiCode
import com.github.ajalt.mordant.TermColors
import com.github.ajalt.mordant.TermColors.Level.TRUECOLOR

private val termColors = TermColors(TRUECOLOR)
private val boldGreen: AnsiCode = termColors.green + termColors.bold
private val boldBlue: AnsiCode = termColors.blue + termColors.bold

val String.green: String get() = termColors.green(this)
val String.red: String get() = termColors.red(this)
val String.boldGreen: String get() = boldGreen(this)
val String.boldBlue: String get() = boldBlue(this)
val String.magenta: String get() = termColors.magenta(this)