package main.controllers

import main.models.employeeJSONStore
import models.employeeModel
import views.employeeView
import mu.KotlinLogging

class employeeController {

   // val employees = employeeMemStore()
    val employees = employeeJSONStore() //referencing the declaration here to use JSON Persistance implemented in employeeJSONStore class
    val employeeView = employeeView()
    val logger = KotlinLogging.logger {}


    init {
        logger.info { "Launching Fit-X Logger Console App" }
        println("Fit-X-Logger Kotlin App Version 1.0")
    }

    fun start(){

    var input: Int

    do {
        input = menu()
        when(input) {
            1 -> add()
            2 -> update()
            3 -> list()
            4 -> search()
            5 -> delete()
            6 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != 6)
    logger.info { "Shutting Down Fit-X-Logger Console App" }
}


    fun menu(): Int {
        return employeeView.menu()
    }

    fun add() {
        var aEmployee = employeeModel()

        if (employeeView.addEmployeeData(aEmployee))
            employees.create(aEmployee)
        else
            logger.info("Employee Not Added")
    }

    fun list() {
        employeeView.listEmployees(employees)
    }

    fun update() {

        employeeView.listEmployees(employees)
        var searchId = employeeView.getId()
        val aEmployee = search(searchId)

        if (aEmployee != null) {
            if (employeeView.updateEmployeeData(aEmployee)) {
                employees.update(aEmployee)
                employeeView.showEmployees(aEmployee)
                logger.info("Employee Updated : [ $aEmployee ]")
            } else
                logger.info("Employee Not Updated")
        } else
            println("Employee Not Updated...")
    }

    fun search() {
        val aEmployee = search(employeeView.getId())!!
        employeeView.showEmployees(aEmployee)
    }

    fun delete() {
        employeeView.listEmployees(employees)
        var searchId = employeeView.getId()
        val aEmployee = search(searchId)

        if(aEmployee != null) {
            employees.delete(aEmployee)
            println("Employee Deleted...")
            employeeView.listEmployees(employees)
        }
        else
            println("Employee Not Deleted...")
    }

    fun search(id: Long): employeeModel? {
        var foundEmployee = employees.findOne(id)
        return foundEmployee
    }
}
