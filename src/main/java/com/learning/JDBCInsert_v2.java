package com.learning;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCInsert_v2 {

    static final String DB_URL = "jdbc:mysql://localhost:3306/test_db";
    static final String USER = "jpauser";
    static final String PWD = "jpapwd";

    public static void main(String[] args) {

        Student student = new Student("Leo", "Farrel", 8.4);
        try(Connection connection = DriverManager.getConnection(DB_URL, USER, PWD)){
            try(Statement statement = connection.createStatement();) {
            //    String sqlQuery = "INSERT INTO students(name, surname, avg_grade) VALUES ('" + 
            //                     student.getName() + "', '" + 
            //                     student.getSurname() + "', "+ 
            //                     student.getAvgGrade() + ")";
               // "INSERT INTO students(name, surname, avg_grade) VALUES ('Leo', 'Farrel', 8.4)"
               String sqlQuery = "INSERT INTO students(name, surname, avg_grade) VALUES ('Julia', 'Dean', 8.7)";
               statement.executeUpdate(sqlQuery);
            } catch(SQLException e) {
                e.printStackTrace();
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

    }

}
