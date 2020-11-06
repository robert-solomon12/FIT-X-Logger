package views

import controllers.PopupDialog
import controllers.employeeControllerUIDB
import javafx.application.Platform
import javafx.beans.property.SimpleObjectProperty

import javafx.stage.StageStyle
import models.employeeModel

import tornadofx.*


class ListEmployeeUI : View("Existing Employees") {

    private val data: employeeControllerUIDB by inject()
    private val comboboxObject = SimpleObjectProperty<employeeModel>()


    override val root = vbox {

        setPrefSize(800.0, 600.0)

        /*
        tableview which holds an observablelist 'employee_data' in my tableview
         */
        tableview(data.employee_data) {
            readonlyColumn("FIRST_NAME", employeeModel::fName).fixedWidth(141)
            readonlyColumn("SURNAME", employeeModel::sName).fixedWidth(141)
            readonlyColumn("DATE_OF_BIRTH", employeeModel::dateOfB).fixedWidth(141)
            readonlyColumn("EMAIL", employeeModel::email).fixedWidth(141)
            readonlyColumn("SS_Number", employeeModel::ssNumber).fixedWidth(141)
            readonlyColumn("NATIONALITY", employeeModel::nationality).fixedWidth(141)
            readonlyColumn("JOB_TITLE", employeeModel::jobTitle)


            //Smart Resize policy to resize the columns
            columnResizePolicy = SmartResize.POLICY
        }
        combobox<employeeModel>(comboboxObject) {
            items = data.employee_data
            cellFormat {
                text = this.item.ssNumber.toString()
            }
        }

        /* on action delete button which calls for the delete method in my controller class to remove the selected record based by the ssNumber of the existing
         record on combobox dropdown and removes the reflects the changes in the database.
         */
        button("Delete")
        {
            useMaxWidth = true
            action {
                runAsyncWithProgress {

                    data.deleteEmp(comboboxObject.get())
                    comboboxObject.value = null

                    //this method is an interesting feature which I wanted to try, this post a popup to the user when this method call is invoked
                    //acknow
                    find<PopupDialog>(params = mapOf("message" to "Employee Deleted!")).openModal(stageStyle = StageStyle.UTILITY)
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


