package views

import main.models.employeeJSONStore
import models.employeeModel

class employeeView {

    fun menu() : Int {
        var option : Int
        var input: String? = null
        println("Welcome to Fit-X Logger Menu" +
                "Please choose from the following options: ")
        println(" 1. Add Employee")
        println(" 2. Update Employee")
        println(" 3. List Existing Employees")
        println(" 4. Search Employees")
        println(" 5. Delete Employees")
        println(" 6. Exit")
        println()
        print("Choose option: ")
        input = readLine()!!
        option = if (input.toIntOrNull() != null && !input.isEmpty())
            input.toInt()
        else
            -9
        return option
    }

    fun listEmployees(employees: employeeJSONStore) {
        println("List All Employees")
        println()
        employees.logAll()
        println()
    }

    fun showEmployees(employee : employeeModel) {
        if(employee != null)
            println("Employee Details [ $employee ]")
        else
            println("Employee Not Found...")
    }

    fun addEmployeeData(employee : employeeModel) : Boolean {

        print("Enter your first name : ")
        employee.fName = readLine()!!
        print("Enter your second name : ")
        employee.sName = readLine()!!
        print("Enter your Date of Birth: ")
        employee.dateOfB = readLine()!!
        print("Enter your Job Title: ")
        employee.jobTitle = readLine()!!
//        println("You've entered the following information: \nFirst Name: " + employee.fName + ", \nSecond Name: " + employee.sName + ", \nDate of Birth: " + employee.dateOfB + ", \nJob Title: " + employee.jobTitle + "")

        return employee.fName.isNotEmpty() && employee.sName.isNotEmpty() && employee.dateOfB.isNotEmpty() && employee.jobTitle.isNotEmpty()
    }

    fun updateEmployeeData(employee : employeeModel) : Boolean {

        var tempfName : String?
        var tempsName : String?
        var tempdateOfB : String?
        var tempjobTitle : String?

        if(employee != null) {
            print("Enter a new First Name for [ " + employee.fName + " ] : ")
            tempfName = readLine()!!
            print("Enter a new Second Name for [ " + employee.sName + " ] : ")
            tempsName = readLine()!!
            print("Enter your new Date of Birth for [ " + employee.dateOfB + " ] : ")
            tempdateOfB = readLine()!!
            print("Enter your new Job Title for [ " + employee.jobTitle + " ] : ")
            tempjobTitle = readLine()!!

            if (!tempfName.isNullOrEmpty() && !tempsName.isNullOrEmpty() && !tempdateOfB.isNullOrEmpty() && !tempjobTitle.isNullOrEmpty()) {
                employee.fName = tempfName
                employee.sName = tempsName
                employee.dateOfB = tempdateOfB
                employee.jobTitle = tempjobTitle
                return true
            }
        }
        return false
    }

    fun getId() : Long {
        var strId : String? // String to hold user input
        var searchId : Long // Long to hold converted id
        print("Enter Employee Id to Search/Update/Delete : ")
        strId = readLine()!!
        searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
            strId.toLong()
        else
            -9
        return searchId
    }
}