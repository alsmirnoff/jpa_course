package com.learning.persistence_context;

import com.learning.persistence_context.entity.Teacher;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class AutomaticDirtyCheckingEx1 {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
            EntityManager entityManager = factory.createEntityManager()) {
            
            EntityTransaction transaction = entityManager.getTransaction();
            
            try{
                // transaction.begin();

                // Teacher teacher = entityManager.find(Teacher.class, 3);
                // teacher.setSubject("Biology");

                // Teacher teacher = entityManager.find(Teacher.class, 3);
                // teacher.setSubject("CS");
                // teacher.setSubject("Math");
                // teacher.setIsProfessor(true);

                Teacher teacher = entityManager.find(Teacher.class, 4);
                teacher.setSubject("CS");

                // transaction.commit();
            } catch (Exception e) {
                if(transaction != null && transaction.isActive()){
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        }
    }
}
