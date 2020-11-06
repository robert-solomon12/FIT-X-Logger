package views

import javafx.scene.control.TabPane
import tornadofx.View
import tornadofx.tabpane

class UIHeader : View() {

    //Tabpane View
    override val root = tabpane {
        tabClosingPolicy = TabPane.TabClosingPolicy.UNAVAILABLE
        tab<AddEmployeeUI>()
        tab<ListEmployeeUI>()
        tab<UpdateUI>()
    }
}