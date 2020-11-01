package views

import controllers.employeeControllerUI
import javafx.application.Platform
import javafx.geometry.Orientation
import models.employeeModel
import tornadofx.*

class MenuUI : View("FIT-X-LOGGER Menu") {

    val empUIController: employeeControllerUI by inject()

    override val root = form {
        setPrefSize(400.0, 200.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            text("")
            button("Add Employee") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        empUIController.loadAddScreen()
                    }
                }
            }
            text("")
            button("List Existing Employees") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        empUIController.loadListScreen()
                    }
                }
            }
            text("")
            button("Exit") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        Platform.exit();
                        System.exit(0);
                    }
                }
            }
        }

    }
}
