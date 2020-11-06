package views

import controllers.PopupDialog
import controllers.employeeControllerUIDB
import javafx.application.Platform
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.stage.StageStyle
import models.employeeModel
import tornadofx.*
import java.time.LocalDate

class UpdateUI : View("Update Employee Info") {

    private val comboboxObject = SimpleObjectProperty<employeeModel>()
    private val newfName = SimpleStringProperty()
    private val newsName = SimpleStringProperty()
    private val newdateOfB = SimpleStringProperty()
    private val newemail = SimpleStringProperty()
    private val newssNumber = SimpleIntegerProperty()
    private val newnationality = SimpleStringProperty()
    private val newjobTitle = SimpleStringProperty()
    private val empuidb: employeeControllerUIDB by inject()

    override val root = vbox {
        form {
            //combobox which takes my the employeeModel setting which re and returns an observable list that is by the original list in the list in my combobox dropdown
            combobox<employeeModel>(comboboxObject, values = empuidb.employee_data.asObservable()) {
                cellFormat {
                    text = this.item.ssNumber.toString()
                }
            }
            fieldset {
                field("First Name:") {
                    textfield(newfName)
                }
                field("Surname:") {
                    textfield(newsName)
                }
                field("Date of Birth:") {
                    textfield(newdateOfB)
                }
                field("Email:") {
                    textfield(newemail)
                }
                field("Social Security No.:") {
                    textfield(newssNumber)
                }
                field("Nationality:") {
                    textfield(newnationality)
                }
                field("Job Title:") {
                    textfield(newjobTitle)
                }

                //on action button which updates the selected record based of the ssNumber (social security number) and calls on my update method to query my database and reflect the relevant changes there.
                button("Update")

                {
                    useMaxWidth = true
                    action {
                        runAsyncWithProgress {
                            empuidb.updateEmp(
                                comboboxObject.get(),
                                newfName.value,
                                newsName.value,
                                newdateOfB.value,
                                newemail.value,
                                newssNumber.value,
                                newnationality.value,
                                newjobTitle.value
                            )
                            newfName.value = ""; newsName.value = ""; newdateOfB.value; newemail.value =
                            ""; newssNumber.value; newnationality.value = ""; newjobTitle.value =
                            ""; comboboxObject.value = null
                            find<PopupDialog>(params = mapOf("message" to "New Employee Updated!")).openModal(
                                stageStyle = StageStyle.UTILITY
                            )
                        }
                    }
                }
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
}

