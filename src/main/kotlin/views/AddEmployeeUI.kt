package views

import controllers.employeeControllerUIDB
import javafx.beans.property.SimpleStringProperty
import javafx.stage.StageStyle
import tornadofx.*
import controllers.PopupDialog
import javafx.application.Platform
import javafx.beans.property.SimpleIntegerProperty
import javafx.scene.paint.Color
import javafx.scene.text.FontWeight


class AddEmployeeUI : View("New Employee") {
    private val fName = SimpleStringProperty()
    private val sName = SimpleStringProperty()
    private val dateOfB = SimpleStringProperty()

    //    val _dateOfB = SimpleObjectProperty<LocalDate>()
    private val email = SimpleStringProperty()
    private val ssNumber = SimpleIntegerProperty()
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
                field("Date of Birth:") {
                    textfield(dateOfB)
                }
                field("Email:") {
                    textfield(email)
                }
                field("Social Security No.:") {
                    textfield(ssNumber)
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
                                ssNumber.value,
                                nationality.value,
                                jobTitle.value,
                            )
                        }.ui {
                            fName.value = ""; sName.value = ""; dateOfB.value = ""; email.value =
                            ""; ssNumber.value; nationality.value = ""; jobTitle.value = "";
                            //search for PopupDialog class and set message to 'Employee Added'
                            find<PopupDialog>(params = mapOf("message" to "New Employee Added!")).openModal(stageStyle = StageStyle.UTILITY)
                        }
                    }
                }
                button("Exit") {
                    style {
                        fontWeight = FontWeight.EXTRA_BOLD
                        borderColor += box(
                            top = Color.RED,
                            right = Color.DARKRED,
                            left = Color.DARKRED,
                            bottom = Color.DARKRED
                        )
                    }
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
}

