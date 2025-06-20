package com.learning.crud.jpa_crud;

import com.learning.crud.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Find_ex {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
            EntityManager entityManager = factory.createEntityManager();) {
            
            Student student = null;

            try{
                student = entityManager.find(Student.class, 5);
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println(student);
        }
    }
}
