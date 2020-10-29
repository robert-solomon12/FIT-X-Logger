//package models
//
//
//import mu.KotlinLogging
//
//private val logger = KotlinLogging.logger {}
//var lastId = 0L
//
//internal fun getId(): Long {
//    return lastId++
//}
//
//class employeeMemStore : employeeStore {
//
//    val employees = ArrayList<employeeModel>()
//
//    override fun findAll(): List<employeeModel> {
//        return employees
//    }
//
//    override fun findOne(id: Long) : employeeModel? {
//        var foundEmployee: employeeModel? = employees.find { p -> p.id == id }
//        return foundEmployee
//    }
//
//    override fun create(employee: employeeModel) {
//        employee.id = getId()
//        employees.add(employee)
//        logAll()
//    }
//
//    override fun update(employee: employeeModel) {
//        var foundEmployee = findOne(employee.id)
//        if (foundEmployee != null) {
//            foundEmployee.fName = employee.fName
//            foundEmployee.sName = employee.sName
//            foundEmployee.dateOfB = employee.dateOfB
//            foundEmployee.jobTitle = employee.jobTitle
//        }
//    }
//
//    internal fun logAll() {
//        employees.forEach { logger.info("${it}") }
//    }
//
//
//}