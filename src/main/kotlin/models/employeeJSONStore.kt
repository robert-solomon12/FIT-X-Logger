package main.models

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import main.helpers.exists
import main.helpers.read
import main.helpers.write
import models.employeeModel
import models.employeeStore
import mu.KotlinLogging
import java.util.*

private val logger = KotlinLogging.logger {}

val JSON_FILE = "employeesData.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<employeeModel>>() {}.type

//val empM = ArrayList<employeeModel>()

fun generateRandomId(): Long {
    return Random().nextLong()
}

class employeeJSONStore : employeeStore {

    var employees = mutableListOf<employeeModel>()

    init {
        if (exists(JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<employeeModel> {
        return employees
    }

    override fun findOne(id: Long) : employeeModel? {
        var foundEmployee: employeeModel? = employees.find { p -> p.id == id }
        return foundEmployee
    }

    override fun create(employee: employeeModel) {

        employee.id = generateRandomId() //empM.size.toLong()
        employees.add(employee)
        serialize()
    }

    override fun update(employee: employeeModel) {
        var foundEmployee = findOne(employee.id)
        if (foundEmployee != null) {
            foundEmployee.fName = employee.fName
            foundEmployee.sName = employee.sName
            foundEmployee.dateOfB = employee.dateOfB
            foundEmployee.email = employee.email
            foundEmployee.nationality = employee.nationality
            foundEmployee.jobTitle = employee.jobTitle
        }
        serialize()
    }

    override fun delete(employee: employeeModel) {
        employees.remove(employee)
        serialize()
        deserialize()
    }

    internal fun logAll() {
        employees.forEach { logger.info("${it}") }
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(employees, listType)
        write(JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(JSON_FILE)
        employees = Gson().fromJson(jsonString, listType)
    }
}