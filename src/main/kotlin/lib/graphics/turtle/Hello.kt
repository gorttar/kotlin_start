package lib.graphics.turtle

import javafx.scene.Scene
import javafx.scene.paint.Color
import javafx.stage.Stage
import tornadofx.*

// привет

class MainView : View() {
    override val root = group { label("Hello World") }
}

private const val windowSize: Double = 1000.0

class TornadoApp : App(MainView::class) {
    override fun start(stage: Stage) {
        stage.initStage()
        super.start(stage)
    }

    override fun createPrimaryScene(view: UIComponent): Scene = super.createPrimaryScene(view).apply {
        fill = Color.valueOf("#EDEDED")
    }

    private fun Stage.initStage() {
        minHeight = windowSize
        minWidth = windowSize
        maxHeight = windowSize
        maxWidth = windowSize
    }
}
