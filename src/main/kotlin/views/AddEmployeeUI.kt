package views

import controllers.employeeControllerUIDB
import javafx.beans.property.SimpleStringProperty
import javafx.stage.StageStyle
import tornadofx.*
import controllers.PopupDialog

class AddEmployeeUI : View("New Employee") {
    private val fName = SimpleStringProperty()
    private val sName = SimpleStringProperty()
    private val dateOfB = SimpleStringProperty()

    //    val _dateOfB = SimpleObjectProperty<LocalDate>()
    private val email = SimpleStringProperty()
    private val nationality = SimpleStringProperty()
    private val jobTitle = SimpleStringProperty()

    //    val empUIController: employeeControllerUI by inject()
    val empuidb: employeeControllerUIDB by inject()

    override val root = vbox {
        form {
            fieldset {
                field("First Name:") {
                    textfield(fName)
                }
                field("Surname:") {
                    textfield(sName)
                }
//            datepicker(_dateOfB){
//                value = LocalDate.now()
//            }
                field("Date of Birth:") {
                    textfield(dateOfB)
                }
                field("Email:") {
                    textfield(email)
                }
                field("Nationality:") {
                    textfield(nationality)
                }
                field("Job Title:") {
                    textfield(jobTitle)
                }
                button("Add") {
                    action {
                        runAsyncWithProgress {
                            empuidb.addEmp(
                                fName.value,
                                sName.value,
                                dateOfB.value,
                                email.value,
                                nationality.value,
                                jobTitle.value)
                        }.ui {
                            fName.value = ""; sName.value = ""; dateOfB.value = ""; email.value =
                            ""; nationality.value =
                            ""; jobTitle.value = ""
                            find<PopupDialog>(params = mapOf("message" to "New Employee Added")).openModal(stageStyle = StageStyle.UTILITY)
                        }
                    }
                }
            }
        }
    }
}
//            button("Back") {
//                useMaxWidth = true
//                action {
//                    runAsyncWithProgress {
//                        empUIController.closeAdd()
//                    }
//                }
//            }

