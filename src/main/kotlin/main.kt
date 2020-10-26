import jdk.nashorn.internal.objects.NativeString.search
import model.employeeModel
import mu.KotlinLogging
import sun.management.GcInfoCompositeData.getId

private val logger = KotlinLogging.logger {}

//var employee = employeeModel()
val employees = ArrayList<employeeModel>()
var employee = employeeModel()

    fun main(args: Array<String>) {
        logger.info { "Launching Fit-X-Logger Console App" }
        println("Fit-X-Logger Kotlin App Version 1.0")

        var input: Int

        do {
            input = menu()
            when(input) {
                1 -> addEmployee()
                2 -> updateEmployee()
                3 -> listEmployees()
                4 -> searchEmployee()
                5 -> println("Exiting App")
                else -> println("Invalid Option")
            }
            println()
        } while (input != 4)
        logger.info { "Shutting Down Fit-X-Logger Console App" }
    }

fun menu() : Int {
    var option : Int
    var input: String? = null
    println("Welcome to Fit-X Logger " +
            "Please choose from the following options: ")
    println(" 1. Add Employee")
    println(" 2. Update Employee")
    println(" 3. List Existing Employees")
    println(" 4. Exit")
    println()
    print("Choose option: ")
    input = readLine()!!
    option = if (input.toIntOrNull() != null && !input.isEmpty())
        input.toInt()
    else
        -9
    return option
}

fun addEmployee() {

    println("Add Employee")
    println()
    print("Enter your first name : ")
    employee.fName = readLine()!!
    print("Enter your second name : ")
    employee.sName = readLine()!!
    print("Enter your Date of Birth: ")
    employee.dateOfB = readLine()!!
    print("Enter your Job Title: ")
    employee.jobTitle = readLine()!!
    println("You've entered the following information: First Name: " + employee.fName + ", Second Name: " + employee.sName + ", Date of Birth: " + employee.dateOfB + ", Job Title: " + employee.jobTitle + "")

    if (employee.fName.isNotEmpty() && employee.sName.isNotEmpty() && employee.dateOfB.isNotEmpty() && employee.jobTitle.isNotEmpty()) {
        employees.add(employee.copy())
        logger.info("Employee Added : [ $employee ]")
    }
    else
        logger.info("Employee Not Added")
}


fun updateEmployee() {
    println("Update Employee")
    println()
    listEmployees()
    var searchId = getId()
    val employee = search(searchId)
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
            println(
                "You updated our system with the following information: [ " + employee.fName + " ] for first name, " +
                             "[ " + employee.sName + " ] for surname, " +
                             "[ " + employee.dateOfB + " ] for date of birth, "+
                             "[ " + employee.jobTitle + " ] for job title, ")
            logger.info("Employee Updated : [ $employee ]")
        }
        else
            logger.info("Placemark Not Updated")
    }
    else
        println("Employee Not Updated...")
}



fun listEmployees() {
    println("Listing all Employees")
    println()
    employees.forEach { logger.info("${it}") }
}

fun searchEmployee() {

    var searchId = getId()
    val employee = search(searchId)

    if(employee != null)
        println("Employee Details [ $employee ]")
    else
        println("Employee Not Found...")
}

fun getId() : Long {
    var strId : String? // String to hold user input
    var searchId : Long // Long to hold converted id
    print("Enter id to Search/Update : ")
    strId = readLine()!!
    searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
        strId.toLong()
    else
        -9
    return searchId
}


fun search(id: Long) : employeeModel? {
    var foundEmployee: employeeModel? = employees.find { p -> p.id == id }
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




