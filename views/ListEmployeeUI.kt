package views

import controllers.employeeControllerUIDB
import javafx.scene.control.SelectionMode
import models.employeeModel

import tornadofx.*


class ListEmployeeUI : View("Existing Employees") {

    private val data: employeeControllerUIDB by inject()
//    var selectedEmployee = employeeModel()

    override val root = tableview(data.employee_data) {
        setPrefSize(1000.0, 600.0)

            readonlyColumn("FIRST_NAME", employeeModel::fName).fixedWidth(141)
            readonlyColumn("SURNAME", employeeModel::sName).fixedWidth(141)
            readonlyColumn("DATE_OF_BIRTH", employeeModel::dateOfB).fixedWidth(141)
            readonlyColumn("EMAIL", employeeModel::email).fixedWidth(141)
            readonlyColumn("SS_Number", employeeModel::ssNumber).fixedWidth(141)
            readonlyColumn("NATIONALITY", employeeModel::nationality).fixedWidth(141)
            readonlyColumn("JOB_TITLE", employeeModel::jobTitle)
//            readonlyColumn("Action", employeeModel::id)
            //Smart Resize policy to resize the columns
            columnResizePolicy = SmartResize.POLICY
        }


//    button("Delete")
//    {
//        useMaxWidth = true
//        action {
//            runAsyncWithProgress {
//                placemarkUIController.closeList()
//            }
//        }
//    }
    }


