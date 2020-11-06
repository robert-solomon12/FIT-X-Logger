package controllers

import models.EmployeeDao
import models.employeeModel
import mu.KotlinLogging
import tornadofx.Controller
import tornadofx.SortedFilteredList
import tornadofx.asObservable

//import views.ListEmployeeUI

class employeeControllerUIDB : Controller() {

    val logger = KotlinLogging.logger {}
//    val empC = employeeController()

    val employee_data = SortedFilteredList(items = getEmployees().asObservable())

    init {
        logger.info { "Launching FIT-X-LOGGER TornadoFX UI App" }
    }

    fun addEmp(
        fName: String,
        sName: String,
        dateOfB: String,
        email: String,
        ssNumber: Int,
        nationality: String,
        jobTitle: String
    ) {
        val employM = employeeModel(fName,sName, dateOfB, email, ssNumber, nationality, jobTitle)
        val dao = EmployeeDao()
        dao.addEmployee(employM)
        employee_data += employM
    }
    private fun getEmployees() : List<employeeModel> = EmployeeDao().readEmployee()

    fun updateEmp(oldemployeeModel: employeeModel, new_fName: String, new_sName: String, new_dateOfB: String, new_email: String, new_ssNumber: Int, new_nationality: String, new_jobTitle: String) {
        val newEmployee = employeeModel(new_fName, new_sName, new_dateOfB, new_email, new_ssNumber, new_nationality, new_jobTitle)
        val dao = EmployeeDao()
        dao.updateEmployee(oldemployeeModel.ssNumber, newEmployee)
        with(employee_data){
            remove(oldemployeeModel)
            add(newEmployee)

        }
    }
}