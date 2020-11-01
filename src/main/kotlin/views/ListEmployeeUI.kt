package views

import controllers.employeeControllerUI
import models.employeeModel
import tornadofx.*

class ListEmployeeUI : View("FIT-X-LOGGER Existing Employees") {
    val empUIController: employeeControllerUI by inject()
    val tableContent = empUIController.employees.findAll()
    val data = tableContent.observable()


    override val root = vbox {
        setPrefSize(600.0, 200.0)
        tableview(data) {
            readonlyColumn("ID", employeeModel::id)
            readonlyColumn("FIRST_NAME", employeeModel::fName)
            readonlyColumn("SURNAME", employeeModel::sName)
            readonlyColumn("DATE_OF_BIRTH", employeeModel::dateOfB)
            readonlyColumn("EMAIL", employeeModel::email)
            readonlyColumn("NATIONALITY", employeeModel::nationality)
            readonlyColumn("JOB_TITLE", employeeModel::jobTitle)
        }
        button("Close") {
            useMaxWidth = true
            action {
                runAsyncWithProgress {
                    empUIController.closeList()
                }
            }
        }
    }
}
