package views

import main.models.employeeJSONStore
import models.employeeModel

class employeeView {

    fun menu() : Int {
        var option : Int
        var input: String? = null
        println("Welcome to Fit-X Logger Menu " +
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

    //functioncall to list existing employees in the datastore
    fun listEmployees(employees: employeeJSONStore) {
        println("List All Employees")
        println()
        employees.logAll()
        println()
    }

    //function call to list employees after user has inputted data <-- this method is invoked when the user chooses the option to search
    fun showEmployees(employee : employeeModel) {
        if(employee != null)
            println("Employee Details: [ $employee ]")
        else
            println("Employee Not Found...")
    }

    fun addEmployeeData(employee : employeeModel) : Boolean {

        print("Enter your first name : ")
        employee.fName = readLine()!!
        print("Enter your Surname : ")
        employee.sName = readLine()!!
        print("Enter your Date of Birth: ")
        employee.dateOfB = readLine()!!
        print("Enter your Email: ")
        employee.email = readLine()!!
        print("Enter your Nationality: ")
        employee.nationality = readLine()!!
        print("Enter your Job Title: ")
        employee.jobTitle = readLine()!!

        return employee.fName.isNotEmpty() && employee.sName.isNotEmpty() && employee.dateOfB.isNotEmpty() && employee.email.isNotEmpty() && employee.nationality.isNotEmpty() && employee.jobTitle.isNotEmpty()
    }

    fun updateEmployeeData(employee : employeeModel) : Boolean {

        var tempfName : String?
        var tempsName : String?
        var tempdateOfB : String?
        var tempEmail : String?
        var tempNationality : String?
        var tempjobTitle : String?

        if(employee != null) {
            print("Update your First Name to replace [ " + employee.fName + " ] : ")
            tempfName = readLine()!!
            print("Update your Surname to replace [ " + employee.sName + " ] : ")
            tempsName = readLine()!!
            print("Update your Date of Birth to replace [ " + employee.dateOfB + " ] : ")
            tempdateOfB = readLine()!!
            print("Update your Email to replace [ " + employee.email + " ] : ")
            tempEmail = readLine()!!
            print("Update your Nationality to replace [ " + employee.nationality + " ] : ")
            tempNationality = readLine()!!
            print("Update your Job Title to replace [ " + employee.jobTitle + " ] : ")
            tempjobTitle = readLine()!!

            if (!tempfName.isNullOrEmpty() && !tempsName.isNullOrEmpty() && !tempdateOfB.isNullOrEmpty() && !tempEmail.isNullOrEmpty() && !tempNationality.isNullOrEmpty() && !tempjobTitle.isNullOrEmpty()) {
                employee.fName = tempfName
                employee.sName = tempsName
                employee.dateOfB = tempdateOfB
                employee.email = tempEmail
                employee.nationality = tempNationality
                employee.jobTitle = tempjobTitle
                return true
            }
        }
        return false
    }

    fun getId() : Int {
        var strId : String? // String to hold user input
        var searchId : Int // Long to hold converted id

        print("Enter Employee Id to Search/Update/Delete : ")
        strId = readLine()!!
        searchId = if (strId.toIntOrNull() != null && !strId.isEmpty())
            strId.toInt()
        else
            -9
        return searchId
    }
}