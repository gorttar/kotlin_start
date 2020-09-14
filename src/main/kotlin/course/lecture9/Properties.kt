package course.lecture9

object Sample1 {
    class Book(name: String) {
        var name = name
            get() = field
            set(value) {
                field = value
            }
    }
}

object Sample2Before {
    class Book(var name: String)
}

object Sample2After {
    class Book(var title: String) {
        var name
            get() = title
            set(value) {
                title = value
            }
    }
}