package views

import controllers.PopupDialog
import controllers.employeeControllerUIDB
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.scene.Parent
import javafx.scene.layout.VBox
import javafx.stage.StageStyle
import models.employeeModel
import tornadofx.*

class UpdateUI : View("Update Employee Info") {

    private val comboboxObject = SimpleObjectProperty<employeeModel>()
    private val new_fName = SimpleStringProperty()
    private val new_sName = SimpleStringProperty()
    private val new_dateOfB = SimpleStringProperty()
    //    val new_dateOfB = SimpleObjectProperty<LocalDate>()
    private val new_email = SimpleStringProperty()
    private val new_ssNumber = SimpleIntegerProperty()
    private val new_nationality = SimpleStringProperty()
    private val new_jobTitle = SimpleStringProperty()
    private val empuidb: employeeControllerUIDB by inject()

    override val root = vbox {
        form {
            combobox<employeeModel>(comboboxObject, values = empuidb.employee_data.observable()) {
                cellFormat {
                    text = this.item.ssNumber.toString()
                }
            }
            fieldset {
                field("First Name:") {
                    textfield(new_fName)
                }
                field("Surname:") {
                    textfield(new_sName)
                }
//            datepicker(_dateOfB){
//                value = LocalDate.now()
//            }
                field("Date of Birth:") {
                    textfield(new_dateOfB)
                }
                field("Email:") {
                    textfield(new_email)
                }
                field("Social Security No.:") {
                    textfield(new_ssNumber)
                }
                field("Nationality:") {
                    textfield(new_nationality)
                }
                field("Job Title:") {
                    textfield(new_jobTitle)
                }
                    button("Update")

                    {
                useMaxWidth = true
                action {
                        runAsyncWithProgress {
                          empuidb.updateEmp(
                              comboboxObject.get(),
                              new_fName.value,
                              new_sName.value,
                              new_dateOfB.value,
                              new_email.value,
                              new_ssNumber.value,
                              new_nationality.value,
                              new_jobTitle.value
                          )
                            new_fName.value = ""; new_sName.value =""; new_dateOfB.value =""; new_email.value =""; new_ssNumber.value; new_nationality.value =""; new_jobTitle.value =""; comboboxObject.value = null
                            find<PopupDialog>(params = mapOf("message" to "New Employee Updated!")).openModal(stageStyle = StageStyle.UTILITY)

                        }
                    }
                }
            }
        }
    }
}