package views

import controllers.employeeControllerUI
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import tornadofx.*

class AddEmployeeUI : View("FIT-X-LOGGER Add New Employee") {
    val model = ViewModel()
    val _fName = model.bind { SimpleStringProperty() }
    val _sName = model.bind { SimpleStringProperty() }
    val _dateOfB = model.bind { SimpleStringProperty() }
//    val _dateOfB = SimpleObjectProperty<LocalDate>()
    val _email = model.bind { SimpleStringProperty() }
    val _nationality = model.bind { SimpleStringProperty() }
    val _jobTitle = model.bind { SimpleStringProperty() }
    val empUIController: employeeControllerUI by inject()

    override val root = form {
        setPrefSize(600.0, 200.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            field("First Name") {
                textfield(_fName).required()
            }
            field("Surname") {
                textfield(_sName).required()
            }
//            datepicker(_dateOfB){
//                value = LocalDate.now()
//            }
            field("Date of Birth") {
                textfield(_dateOfB).required()
            }
            field("Email") {
                textfield(_email).required()
            }
            field("Nationality") {
                textfield(_nationality).required()
            }
            field("Job Title") {
                textfield(_jobTitle).required()
            }
            button("Add") {
                enableWhen(model.valid)
                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        empUIController.add(_fName.value,_sName.value,_dateOfB.value,_email.value,_nationality.value,_jobTitle.value)

                    }
                }
            }
            button("Back") {
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        empUIController.closeAdd()
                    }
                }
            }
        }
    }

    override fun onDock() {
        _fName.value = ""
        _sName.value = ""
        _dateOfB.value = ""
        _email.value = ""
        _nationality.value = ""
        _jobTitle.value = ""
        model.clearDecorators()
    }
}
