package models

interface employeeStore {
    fun findAll(): List<employeeModel>
    fun findOne(id: Long): employeeModel?
    fun create(employee: employeeModel)
    fun update(employee: employeeModel)
}