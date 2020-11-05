package models

interface employeeStore {
    fun findAll(): List<employeeModel>
    fun findOne(id: Int): employeeModel?
    fun create(employee: employeeModel)
    fun update(employee: employeeModel)
    fun delete(employee: employeeModel)

}