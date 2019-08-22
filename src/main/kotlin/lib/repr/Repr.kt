package lib.repr

val Any?.repr
    get() = when (this) {
        is String -> "\"$this\""
        is Char -> "'$this'"
        else -> "$this"
    }