package controllers

import java.sql.Connection
import java.sql.DriverManager

class Database {

    var connection: Connection
    init {
        Class.forName("org.sqlite.JDBC")
        connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\rober\\OneDrive\\Desktop\\Github\\FIT-X-Logger-2.1\\src\\FitX.db")
        print("Successfully connected to Fit-X-Database!")
    }
}