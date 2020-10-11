package course.lecture9

/** библиотека "Докладчик"*/
interface Speaker {
    fun say() // абстрактный метод в интерфейсе не требует ключевого слова abstract
    fun speak(): Unit = println(speech) // открытый метод может быть определён в интерфейсе
    val speech: String // абстрактное свойство в интерфейсе не требует ключевого слова abstract
//    val story = "story" // а вот инициализированное свойство - уже нет
}

fun performSpeech(speaker: Speaker) {
    println("А сейчас выступит $speaker с речью ${speaker.speech}")
    speaker.say()
    speaker.speak()
}
/** конец библиотеки "Докладчик"*/

/** библиотека "Охотник"*/
interface Hunter {
    val name: String // абстрактное свойство в интерфейсе не требует ключевого слова abstract
    fun hunt() = println("$name охотится") // открытый метод может быть определён в интерфейсе
    fun speak(): Unit = println(name) // открытый метод может быть определён в интерфейсе
}

fun forestHunt(hunter: Hunter) {
    println("В лесу охотится $hunter по имени ${hunter.name}")
    hunter.hunt()
}
/** конец библиотеки "Охотник"*/

/** класс [Dog] является [Animal], а так же [Speaker] и [Hunter]*/
class Dog(age: Int, name: String) : Animal(age, name), Speaker, Hunter {
    /** определяем абстрактный метод из интерфейса [Speaker] */
    override fun say(): Unit = speak()

    /** определяем абстрактное свойство из интерфейса [Speaker] */
    override val speech: String = "гав"

    /** определяем абстрактный метод из класса [Animal]
     * после super в <> явно указываем, из какого родителя вызывать реализацию,
     * так как он реализован и в [Speaker.speak], и в [Hunter.speak]
     */
    override fun speak() {
        super<Speaker>.speak()
        super<Hunter>.speak()
    }
    /** поле [Hunter.name] определять не нужно, так как мы унаследовали его от [Animal]
     * к тому же его нельзя переопределить, так как оно не open в [Animal]
     */
}

fun main(): Unit = Dog(2, "Шарик").let {
    performSpeech(it)
    forestHunt(it)
}