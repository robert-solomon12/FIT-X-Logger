package main

import models.employeeMemStore
import models.employeeModel
import views.employeeView
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

val employees = employeeMemStore()
val employeeView = employeeView()

    fun main(args: Array<String>) {
        logger.info { "Launching Fit-X-Logger Console App" }
        println("Fit-X-Logger Kotlin App Version 1.0")

        var input: Int

        do {
            input = employeeView.menu()
            when(input) {
                1 -> addEmployee()
                2 -> updateEmployee()
                3 -> employeeView.listEmployees(employees)
                4 -> searchEmployee()
                5 -> println("Exiting App")
                else -> println("Invalid Option")
            }
            println()
        } while (input != 4)
        logger.info { "Shutting Down Fit-X-Logger Console App" }
    }


fun addEmployee(){
    var aEmployee = employeeModel()

    if (employeeView.addEmployeeData(aEmployee))
        employees.create(aEmployee)
    else
        logger.info("Employee Not Added")
}

fun updateEmployee() {
    println("Update Employee")
    println()
    employeeView.listEmployees(employees)
    var searchId = employeeView.getId()
    val aEmployee = search(searchId)
    var tempfName : String?
    var tempsName : String?
    var tempdateOfB : String?
    var tempjobTitle : String?

    if(aEmployee != null) {
      if (employeeView.updateEmployeeData(aEmployee)){
          employees.update(aEmployee)
            logger.info("Employee Updated : [ $aEmployee ]")
        }
        else
            logger.info("Placemark Not Updated")
    }
    else
        println("Employee Not Updated...")
}

fun searchEmployee() {
    val aEmployee = search(employeeView.getId())!!
    employeeView.showEmployees(aEmployee)
}

fun search(id: Long) : employeeModel? {
    var foundEmployee = employees.findOne(id)
    return foundEmployee
}




//fun updateEmployee() {
//    println("Update Placemark")
//    println()
//    listEmployees()
//    var searchId = getId()
//    val aEmployee = search(searchId)
//
//    if(aEmployee != null) {
//        // Ask the user for new details here
//    }
//    else
//        println("Employee Not Updated...")
//}


//fun bmi() {
//    val  height = 1.7 // meters
//}




