package views

import controllers.employeeControllerUI
import models.employeeModel
import tornadofx.*

class ListEmployeeUI : View("FIT-X-LOGGER Existing Employees") {
    val empUIController: employeeControllerUI by inject()
    val tableContent = empUIController.employees.findAll()
    val data = tableContent.observable()


    override val root = vbox {
        setPrefSize(800.0, 700.0)
        tableview(data) {
            column("ID", employeeModel::id)
            column("FIRST_NAME", employeeModel::fName)
            column("SURNAME", employeeModel::sName)
            column("DATE_OF_BIRTH", employeeModel::dateOfB)
            column("EMAIL", employeeModel::email)
            column("NATIONALITY", employeeModel::nationality)
            column("JOB_TITLE", employeeModel::jobTitle)

            //Smart Resize policy to resize the columns
            columnResizePolicy = SmartResize.POLICY
        }
        button("Back") {
            useMaxWidth = true
            action {
                runAsyncWithProgress {
                    empUIController.closeList()
                }
            }
        }
    }
}
