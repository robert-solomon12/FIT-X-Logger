package models

import controllers.Database
import java.sql.Timestamp

class EmployeeDao {

    //addition query call to db to insert record (in this case based on the employee model) to db
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

    //fetch query method call to db to fetch where existing records are stored
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

    //update query method call to db
    fun updateEmployee(ssNumber: Int, employeeModel: employeeModel): employeeModel {

        val conn = Database().connection
        var pmfName = ""
        val newfName = ", fName = ? "
        var pmsName = ""
        val newsName = ", sName = ? "
        var pmdateOfB = ""
        val newdateOfB = ", dateOfB = ? "
        var pmemail = ""
        val newemail = ", email = ? "
        var pmnationality = ""
        val newnationality = ", nationality = ? "
        var pmjobTitle = ""
        val newjobTitle = ", jobTitle = ? "
        var optIndex1 = 1
        var optIndex2 = 2
        var optIndex3 = 3
        var optIndex4 = 4
        var optIndex5 = 5
        var optIndex6 = 6

        if (employeeModel.fName.isNotEmpty() && employeeModel.sName.isNotEmpty() && employeeModel.dateOfB.isNotEmpty() && employeeModel.email.isNotEmpty() && employeeModel.nationality.isNotEmpty() && employeeModel.jobTitle.isNotEmpty())
            pmfName = newfName
            pmsName = newsName
            pmdateOfB = newdateOfB
            pmemail = newemail
            pmnationality = newnationality
            pmjobTitle = newjobTitle

        val preparedStatement = conn.prepareStatement("UPDATE employees SET fName = ? $pmfName, sName = ? $pmsName, dateOfB = ? $pmdateOfB, email = ? $pmemail, nationality = ? $pmnationality, jobTitle = ? $pmjobTitle WHERE ssNumber = ?")
        preparedStatement.setString(1,employeeModel.fName)
        preparedStatement.setString(2,employeeModel.sName)
        preparedStatement.setString(3,employeeModel.dateOfB)
        preparedStatement.setString(4,employeeModel.email)
        preparedStatement.setString(5,employeeModel.nationality)
        preparedStatement.setString(6,employeeModel.jobTitle)


        if (pmfName.isNotEmpty() && pmsName.isNotEmpty() && pmdateOfB.isNotEmpty() && pmemail.isNotEmpty() && pmnationality.isNotEmpty() && pmjobTitle.isNotEmpty()){
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
        Thread.sleep(2000)
        print("Successfully updated record in database!\n")
        preparedStatement.close()
        conn.close()
        return employeeModel
    }

    //deletion query method called to db to remove the selected record based by ssNumber which is an Integer
    fun deleteEmployee(ssNumber: Int) {
        val timestamp = Timestamp(System.currentTimeMillis())
        val conn = Database().connection
        val preparedStatement = conn.prepareStatement("UPDATE employees SET id = ? WHERE ssNumber = ?")
        preparedStatement.setString(1, timestamp.toString())
        preparedStatement.setString(2, ssNumber.toString())
        preparedStatement.executeUpdate()
        Thread.sleep(2000)
        print("Employee has been successfully removed from our database!\n")
        preparedStatement.close()
        conn.close()
    }
}