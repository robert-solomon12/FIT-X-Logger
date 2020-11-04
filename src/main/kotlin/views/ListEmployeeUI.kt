package views

import controllers.employeeControllerUI
import javafx.collections.ObservableList
import javafx.scene.control.SelectionMode
import models.employeeModel
import tornadofx.*


class ListEmployeeUI : View("FIT-X-LOGGER Existing Employees") {

    val empUIController: employeeControllerUI by inject()

    var data = empUIController.getTData()
    var selectedEmployee = employeeModel()


    override val root = vbox {
        setPrefSize(1000.0, 600.0)
        //var onLeftClick
        tableview(data) {

            //on Double click
            onDoubleClick {
            }
            onUserSelect {
                if (this.selectedItem != null) {
                    selectedEmployee = this.selectedItem!!

//                    println(this.selectedItem)
                }
//                println(this.selectedItem!!)
            }

            selectionModel.selectionMode = SelectionMode.SINGLE
            readonlyColumn("ID", employeeModel::id).fixedWidth(180)
            readonlyColumn("FIRST_NAME", employeeModel::fName).fixedWidth(120)
            readonlyColumn("SURNAME", employeeModel::sName).fixedWidth(120)
            readonlyColumn("DATE_OF_BIRTH", employeeModel::dateOfB).fixedWidth(130)
            readonlyColumn("EMAIL", employeeModel::email).fixedWidth(180)
            readonlyColumn("NATIONALITY", employeeModel::nationality).fixedWidth(120)
            readonlyColumn("JOB_TITLE", employeeModel::jobTitle)
//            readonlyColumn("Action", employeeModel::id)

            //Smart Resize policy to resize the columns
            columnResizePolicy = SmartResize.POLICY
        }

        //on Double Click on Selection in Listview, action is performed to delete
        button("Delete") {
            useMaxWidth = true
            action {
                runAsyncWithProgress {
                    empUIController.deleteEmployee(selectedEmployee)

                    //refreshing the tableview after item is deleted
                    data.setAll(empUIController.getTData())
                }
            }
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

