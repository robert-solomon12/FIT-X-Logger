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

    //variable assigned as 'employee_data' which holds an observable lists of 'employeemodel' objects
    val employee_data = SortedFilteredList(items = getEmployees().asObservable())

    init {
        logger.info { "Launching FIT-X-LOGGER TornadoFX UI App" }
    }


    //addition or insertion method invoked by the respected add button click which calls for this method which passes the object and calls for the addition query call in the Data access object class
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

        //insertion query call located in the data access object class to query the database to insert a new record to the database.
        dao.addEmployee(employM)
        employee_data += employM
    }
    private fun getEmployees() : List<employeeModel> = EmployeeDao().readEmployee()

    //update method invoked by the respected button click which calls on for the update query call in the Data access object class
    fun updateEmp(oldemployeeModel: employeeModel, new_fName: String, new_sName: String, new_dateOfB: String, new_email: String, new_ssNumber: Int, new_nationality: String, new_jobTitle: String) {
        val newEmployee = employeeModel(new_fName, new_sName, new_dateOfB, new_email, new_ssNumber, new_nationality, new_jobTitle)
        val dao = EmployeeDao()

        //update query call located in the data access object class to query the database for an update on the selected employee based on his/her ssNumber and replace the entire selected record
        dao.updateEmployee(oldemployeeModel.ssNumber, newEmployee)
        with(employee_data){
            remove(oldemployeeModel)
            add(newEmployee)

        }
    }

    //deletion method invoked by the respected button click which calls on for the deletion query call in the deletion query in the Data access object class
    fun deleteEmp(employeeModel: employeeModel) {
        val dao = EmployeeDao()

        //deletion query call located in the data access object class to query the database for the removal of the selected employee based on his/her ssNumber and remove the entire selected record
        dao.deleteEmployee(employeeModel.ssNumber)
        employee_data.remove(employeeModel)
        print("Deleted Employee!")
    }
}