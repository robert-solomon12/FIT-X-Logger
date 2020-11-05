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

    fun addEmp(_fName: String, _sName: String, _dateOfB: String, _email: String, _nationality: String, _jobTitle: String) {

        val employM = employeeModel(id = 0, _fName, _sName, _dateOfB, _email, _nationality, _jobTitle)
        val dao = EmployeeDao()
        dao.addEmployee(employM)
    }
}