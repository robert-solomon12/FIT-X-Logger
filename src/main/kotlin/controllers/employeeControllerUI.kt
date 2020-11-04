package controllers

import javafx.collections.ObservableList
import main.models.employeeJSONStore
import models.employeeModel
import mu.KotlinLogging
import tornadofx.*
import views.AddEmployeeUI
import views.ListEmployeeUI
import views.MenuUI
import views.WelcomeUI


//Employee UI class extracted from the Controller class
class employeeControllerUI : Controller() {

    //    val empM = ArrayList<employeeModel>()
    val employees = employeeJSONStore()
    val logger = KotlinLogging.logger {}
//    val empC = employeeController()


    init {
        logger.info { "Launching FIT-X-LOGGER TornadoFX UI App" }
    }

    fun add(_fName: String, _sName: String, _dateOfB: String, _email: String, _nationality: String, _jobTitle: String) {

        var aEmployee = employeeModel(
            fName = _fName,
            sName = _sName,
            dateOfB = _dateOfB,
            email = _email,
            nationality = _nationality,
            jobTitle = _jobTitle
        )
        employees.create(aEmployee)

//        aEmployee.id = empM.size.toLong()
        logger.info("Employee Added")
    }

    fun deleteEmployee(employeeModel: employeeModel) {
        //printing the employee model twice //before and after
        println(employees.findAll())
        employees.delete(employeeModel)
        print(employees.findAll())
        //printing the employee model twice //before and after
        println(employeeModel)
    }

    fun getTData(): ObservableList<employeeModel> {

        val tableContent = employees.findAll()
        val data = tableContent.observable()
        return data
    }

//    fun getEmployeeList():ObservableList<employeeModel>{
//        return employees.findAll()
//
//    }


    fun loadMenuUI() {
        runLater {
            find(WelcomeUI::class).replaceWith(MenuUI::class, sizeToScene = true, centerOnScreen = true)
        }
    }
    fun loadAddScreen() {
        runLater {
            find(MenuUI::class).replaceWith(AddEmployeeUI::class, sizeToScene = true, centerOnScreen = true)
        }
    }
    fun loadListScreen() {
        runLater {
            find(MenuUI::class).replaceWith(ListEmployeeUI::class, sizeToScene = true, centerOnScreen = true)
        }
        employees.logAll()
    }


    fun closeMenu() {
        runLater {
            find(MenuUI::class).replaceWith(WelcomeUI::class, sizeToScene = true, centerOnScreen = true)
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