package styles


import tornadofx.Stylesheet
import tornadofx.Stylesheet.Companion.label
import tornadofx.box

import tornadofx.cssclass
import tornadofx.px

class Styles : Stylesheet() {
    companion object {
        val heading by cssclass()
    }

    init {
        label and heading {
            padding = box(700.px)
            fontSize = 70.px
            fontWeight = javafx.scene.text.FontWeight.BOLD
        }
    }
}