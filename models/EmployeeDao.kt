package models

import controllers.Database

class EmployeeDao {

    fun addEmployee(employeeModel: employeeModel) {
        val conn = Database().connection
        val preparedStatement =
            conn.prepareStatement("INSERT INTO employees(fName, sName, dateOfB, email, ssNumber, nationality, jobTitle) VALUES(?, ?, ?, ?, ?, ?, ?)")
        preparedStatement.setString(1, employeeModel.fName)
        preparedStatement.setString(2, employeeModel.sName)
        preparedStatement.setString(3, employeeModel.dateOfB)
        preparedStatement.setString(4, employeeModel.email)
        preparedStatement.setInt(5, employeeModel.ssNumber)
        preparedStatement.setString(6, employeeModel.nationality)
        preparedStatement.setString(7, employeeModel.jobTitle)
        preparedStatement.executeUpdate()
        Thread.sleep(2000)
        print("Successfully inserted record to database!\n")
        preparedStatement.close()
        conn.close()
    }


    fun readEmployee(): List<employeeModel> {
        val conn = Database().connection
        val resultSet = conn.createStatement().executeQuery("SELECT * FROM employees WHERE id IS NOT NULL")
        val employeeList = ArrayList<employeeModel>()
//        print("I'm being accessed!! --> testing\n")
        while (resultSet.next()) {
//            print("me too -> testing\n")
            val fName = resultSet.getString("fName")
            val sName = resultSet.getString("sName")
            val dateOfB = resultSet.getString("dateOfB")
            val email = resultSet.getString("email")
            val ssNumber = resultSet.getInt("ssNumber")
            val nationality = resultSet.getString("nationality")
            val jobTitle = resultSet.getString("jobTitle")
            employeeList += employeeModel(fName, sName, dateOfB, email, ssNumber, nationality, jobTitle)
            Thread.sleep(2000)
            print("Reading from database, please wait........\n")
        }
        resultSet.close()
        conn.close()
        return employeeList
    }

    fun updateEmployee(ssNumber: Int, employeeModel: employeeModel): employeeModel {
        val conn = Database().connection
        var pm_fName = ""
        val new_fName = ", fName = ? "
        var pm_sName = ""
        val new_sName = ", sName = ? "
        var pm_dateOfB = ""
        val new_dateOfB = ", dateOfB = ? "
        var pm_email = ""
        val new_email = ", email = ? "
        var pm_nationality = ""
        val new_nationality = ", nationality = ? "
        var pm_jobTitle = ""
        val new_jobTitle = ", jobTitle = ? "
        var optIndex1 = 1
        var optIndex2 = 2
        var optIndex3 = 3
        var optIndex4 = 4
        var optIndex5 = 5
        var optIndex6 = 6


        if (employeeModel.fName.isNotEmpty() && employeeModel.sName.isNotEmpty() && employeeModel.dateOfB.isNotEmpty() && employeeModel.email.isNotEmpty() && employeeModel.nationality.isNotEmpty() && employeeModel.jobTitle.isNotEmpty())
            pm_fName = new_fName
            pm_sName = new_sName
            pm_dateOfB = new_dateOfB
            pm_email = new_email
            pm_nationality = new_nationality
            pm_jobTitle = new_jobTitle

        val preparedStatement = conn.prepareStatement("UPDATE employees SET fName = ? $pm_fName, sName = ? $pm_sName, dateOfB = ? $pm_dateOfB, email = ? $pm_email, nationality = ? $pm_nationality, jobTitle = ? $pm_jobTitle WHERE ssNumber = ?")
        preparedStatement.setString(1,employeeModel.fName)
        preparedStatement.setString(2,employeeModel.sName)
        preparedStatement.setString(3,employeeModel.dateOfB)
        preparedStatement.setString(4,employeeModel.email)
        preparedStatement.setString(5,employeeModel.nationality)
        preparedStatement.setString(6,employeeModel.jobTitle)

        if (pm_fName.isNotEmpty() && pm_sName.isNotEmpty() && pm_dateOfB.isNotEmpty() && pm_email.isNotEmpty() && pm_nationality.isNotEmpty() && pm_jobTitle.isNotEmpty()){
            preparedStatement.setString(optIndex1, employeeModel.fName)
            preparedStatement.setString(optIndex2, employeeModel.sName)
            preparedStatement.setString(optIndex3, employeeModel.dateOfB)
            preparedStatement.setString(optIndex4, employeeModel.email)
            preparedStatement.setString(optIndex5, employeeModel.nationality)
            preparedStatement.setString(optIndex6, employeeModel.jobTitle)
            optIndex1 = optIndex1.inc()
            optIndex2 = optIndex2.inc()
            optIndex3 = optIndex3.inc()
            optIndex4 = optIndex4.inc()
            optIndex5 = optIndex5.inc()
            optIndex6 = optIndex6.inc()
        }
        preparedStatement.setString(optIndex1, employeeModel.fName)
        preparedStatement.setString(optIndex2, employeeModel.sName)
        preparedStatement.setString(optIndex3, employeeModel.dateOfB)
        preparedStatement.setString(optIndex4, employeeModel.email)
        preparedStatement.setString(optIndex5, employeeModel.nationality)
        preparedStatement.setString(optIndex6, employeeModel.jobTitle)
        preparedStatement.executeUpdate()
//        Thread.sleep(2000)
        print("Successfully updated record in database!\n")
        preparedStatement.close()
        conn.close()
        return employeeModel
    }
}
        //fName: String, sName: String, dateOfB: String, email: String, ssNumber: Int, nationality: String, jobTitle: String) {