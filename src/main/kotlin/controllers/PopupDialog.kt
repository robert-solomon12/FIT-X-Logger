package controllers

import styles.Styles
import tornadofx.Stylesheet.Companion.label
import tornadofx.View

import javax.swing.text.Element
import tornadofx.*

class PopupDialog : View() {

    val message: String by param()
    override val root = vbox {
        label(message){
            addClass(Styles.heading)
        }

    }
}