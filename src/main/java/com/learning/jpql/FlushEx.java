package com.learning.jpql;

import java.util.List;

import com.learning.jpql.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class FlushEx {
public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
            EntityManager entityManager = factory.createEntityManager()) {
            
            EntityTransaction transaction = entityManager.getTransaction();
            
            try{
                transaction.begin();

                // Student student = entityManager.find(Student.class, 1);
                // student.setAvgGrade(8.0);

                // entityManager.flush();

                // ===================================================

                Student student = entityManager.find(Student.class, 1);
                student.setAvgGrade(9.0);

                Double avgGrade = entityManager.createQuery("SELECT s.avgGrade FROM Student s WHERE s.id = 1", Double.class).getSingleResult();

                System.out.println(avgGrade);

                transaction.commit();
            } catch (Exception e) {
                if(transaction != null && transaction.isActive()){
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        }
    }
}
