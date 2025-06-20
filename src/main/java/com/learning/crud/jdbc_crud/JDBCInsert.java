package com.learning.crud.jdbc_crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.learning.crud.entity.Student;

public class JDBCInsert {

    static final String DB_URL = "jdbc:mysql://localhost:3306/test_db";
    static final String USER = "jpauser";
    static final String PWD = "jpapwd";

    public static void main(String[] args) {

        Student student = new Student("Chanel", "King", 9.1);
        try(Connection connection = DriverManager.getConnection(DB_URL, USER, PWD)){
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO students(name, surname, avg_grade) VALUES (?, ?, ?)")) {
                statement.setString(1, student.getName());
                statement.setString(2, student.getSurname());
                statement.setDouble(3, student.getAvgGrade());
                statement.executeUpdate();
                statement.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

    }

}
