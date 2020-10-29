package main

import main.controllers.employeeController
//import models.employeeMemStore
import models.employeeModel
import views.employeeView
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

//val employees = employeeMemStore()
val employeeView = employeeView()
val controllers = employeeController()



    fun main(args: Array<String>) {
        logger.info { "Launching FIT-X LOGGER Console App" }
        println("Placemark Kotlin App Version 4.0")

        var input: Int

        do {
            input = employeeView.menu()
            when (input) {
                1 -> controllers.add()
                2 -> controllers.update()
                3 -> controllers.list()
                4 -> controllers.search()
                -1 -> println("Exiting App")
                else -> println("Invalid Option")
            }
            println()
        } while (input != -1)
        logger.info { "Shutting Down Placemark Console App" }
    }


