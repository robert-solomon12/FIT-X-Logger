package controllers

import main.models.employeeJSONStore
import models.employeeModel
import mu.KotlinLogging
import tornadofx.*
import views.AddEmployeeUI
import views.ListEmployeeUI
import views.MenuUI


//Employee UI class extracted from the Controller class
class employeeControllerUI : Controller() {

    val employees = employeeJSONStore()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching FIT-X-LOGGER TornadoFX UI App" }
    }
    fun add(_fName : String, _sName : String, _dateOfB : String, _email : String, _nationality : String, _jobTitle : String){

        var aEmployee = employeeModel(fName = _fName, sName = _sName, dateOfB = _dateOfB, email = _email, nationality = _nationality, jobTitle = _jobTitle)
        employees.create(aEmployee)
        logger.info("Employee Added")
    }

    fun loadListScreen() {
        runLater {
            find(MenuUI::class).replaceWith(ListEmployeeUI::class, sizeToScene = true, centerOnScreen = true)
        }
        employees.logAll()
    }

    fun loadAddScreen() {
        runLater {
            find(MenuUI::class).replaceWith(AddEmployeeUI::class, sizeToScene = true, centerOnScreen = true)
        }
    }
    fun closeAdd() {
        runLater {
            find(AddEmployeeUI::class).replaceWith(MenuUI::class, sizeToScene = true, centerOnScreen = true)
        }
    }
    fun closeList() {
        runLater {
            find(ListEmployeeUI::class).replaceWith(MenuUI::class, sizeToScene = true, centerOnScreen = true)
        }
    }
}
