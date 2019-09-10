package lib.output

import com.github.ajalt.mordant.AnsiCode
import com.github.ajalt.mordant.AnsiColorCode
import com.github.ajalt.mordant.TermColors
import com.github.ajalt.mordant.TermColors.Level.TRUECOLOR

private val termColors = TermColors(TRUECOLOR)
val green: AnsiColorCode = termColors.green
val red: AnsiColorCode = termColors.red
val bold: AnsiCode = termColors.bold
val boldGreen: AnsiCode = green + bold