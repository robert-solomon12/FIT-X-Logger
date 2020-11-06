package controllers

import java.sql.Connection
import java.sql.DriverManager

class Database {

    var connection: Connection

    /*
      * Connection String containing 2 parameter to establish connectivity to my Database SQL Lite SERVER (URL, db name)
      */
    init {
            Class.forName("org.sqlite.JDBC")
            connection =
                DriverManager.getConnection("jdbc:sqlite:C:\\Users\\rober\\OneDrive\\Desktop\\Github\\FIT-X-Logger-2.1\\src\\FitX.db")
        Thread.sleep(3000)
        print("Successfully connected to Fit-X-Database!\n")
        }
    }