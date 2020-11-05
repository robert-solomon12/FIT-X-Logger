package controllers

import models.EmployeeDao
import models.employeeModel
import mu.KotlinLogging
import tornadofx.Controller
//import views.ListEmployeeUI

class employeeControllerUIDB : Controller() {

    val logger = KotlinLogging.logger {}
//    val empC = employeeController()

    init {
        logger.info { "Launching FIT-X-LOGGER TornadoFX UI App" }
    }

    fun addEmp(fName: String, sName: String, dateOfB: String, email: String, nationality: String, jobTitle: String) {
        val employM = employeeModel(fName, sName, dateOfB, email, nationality, jobTitle)
        val dao = EmployeeDao()
        dao.addEmployee(employM)
    }
}