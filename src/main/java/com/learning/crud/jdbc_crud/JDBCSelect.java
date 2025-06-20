package com.learning.crud.jdbc_crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.learning.crud.entity.Student;

public class JDBCSelect {

    static final String DB_URL = "jdbc:mysql://localhost:3306/test_db";
    static final String USER = "jpauser";
    static final String PWD = "jpapwd";

    public static void main(String[] args) {

        try(Connection connection = DriverManager.getConnection(DB_URL, USER, PWD);
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM students where avg_grade > ?")){
            statement.setDouble(1, 9.0);
            try(ResultSet resultSet = statement.executeQuery()){
                List<Student> students = new ArrayList<>();
                while(resultSet.next()){
                    Student student = new Student();
                    student.setId(resultSet.getLong("id"));
                    student.setName(resultSet.getString("name"));
                    student.setSurname(resultSet.getString("surname"));
                    student.setAvgGrade(resultSet.getDouble("avg_grade"));
                    students.add(student);
                }
                System.out.println(students);
            } catch(Exception e) {
                e.printStackTrace();
            }
            
        } catch(SQLException e) {
            e.printStackTrace();
        }

    }

}
