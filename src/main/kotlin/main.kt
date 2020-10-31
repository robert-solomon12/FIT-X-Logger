package main

import main.controllers.employeeController
//import models.employeeMemStore
import models.employeeModel
import views.employeeView
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

val controllers = employeeController()



    fun main(args: Array<String>) {
        println("Placemark Kotlin App Version 4.0")
               controllers.start()
            }


