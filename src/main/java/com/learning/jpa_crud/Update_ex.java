package com.learning.jpa_crud;

import com.learning.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Update_ex {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
            EntityManager entityManager = factory.createEntityManager()) {
            EntityTransaction transaction = entityManager.getTransaction();
            Student student = null;

            try{
                transaction.begin();
                
                // student = entityManager.find(Student.class, 5);
                // student.setAvgGrade(8.2);

                student =  entityManager.find(Student.class, 2);
                student.setAvgGrade(9.0);

                transaction.commit();
            } catch(Exception e){
                if (transaction != null && transaction.isActive()) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
            System.out.println(student);
        }
    }
}
