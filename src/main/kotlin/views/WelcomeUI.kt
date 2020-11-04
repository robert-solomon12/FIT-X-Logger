package views

import controllers.employeeControllerUI
import javafx.application.Platform
import javafx.geometry.Orientation
import tornadofx.*




class WelcomeUI : View("Hello and Welcome") {

    val empUIController: employeeControllerUI by inject()

    override val root = form {
        setPrefSize(400.0, 200.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            text("")

            button("Enter") {
                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        empUIController.loadMenuUI()
                    }
                }
            }
            button("Exit") {

//                isDefaultButton = true
                useMaxWidth = true
                action {

                    //RUNSYNC Loading feature
                    runAsyncWithProgress {
                        Platform.exit();
                        System.exit(0);
                    }
                }
            }
        }
    }
}
