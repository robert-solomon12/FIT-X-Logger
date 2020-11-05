package models

import controllers.Database

class EmployeeDao {

    fun addEmployee(employeeModel: employeeModel){
        val conn = Database().connection
        val preparedStatement = conn.prepareStatement("INSERT INTO employees(id, fName, sName, dateOB, email, nationality, jobTitle) VALUES(?, ?)")
        preparedStatement.setInt(1, employeeModel.id)
        preparedStatement.setString(2, employeeModel.fName)
        preparedStatement.setString(3, employeeModel.sName)
        preparedStatement.setString(4, employeeModel.dateOfB)
        preparedStatement.setString(5, employeeModel.email)
        preparedStatement.setString(6, employeeModel.nationality)
        preparedStatement.setString(7, employeeModel.jobTitle)
        preparedStatement.executeUpdate()
        ("successfully inserted record to database!")
        preparedStatement.close()
        conn.close()
    }
}