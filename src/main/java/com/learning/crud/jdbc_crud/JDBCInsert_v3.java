package com.learning.crud.jdbc_crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.learning.crud.entity.Student;

public class JDBCInsert_v3 {

    static final String DB_URL = "jdbc:mysql://localhost:3306/test_db";
    static final String USER = "jpauser";
    static final String PWD = "jpapwd";

    public static void main(String[] args) {

        Student student = new Student("Isaac", "Clarke", 9.8);
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PWD);
            PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO students(name, surname, avg_grade) VALUES (?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, student.getName());
            statement.setString(2, student.getSurname());
            statement.setDouble(3, student.getAvgGrade());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Failed to add student to DB");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    student.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Failed to create student ID");
                }
            }

            System.out.println(student);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
