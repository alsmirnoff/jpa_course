package com.learning.persistence_context.jpa_methods;

import com.learning.persistence_context.entity.Teacher;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class MergeEx3 {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course")) {
            
            EntityManager entityManager = factory.createEntityManager();

            EntityTransaction transaction = entityManager.getTransaction();
            
            try{
                transaction.begin();

                // Teacher teacher = new Teacher("User", "Testov", "JPA", false);
                // Teacher mergedTeacher = entityManager.merge(teacher);

                // ===================================================

                // Teacher teacher = entityManager.find(Teacher.class, 7);
                // entityManager.remove(teacher);

                // ===================================================

                Teacher teacher = new Teacher("User", "Testov", "JPA", false);
                teacher.setId(100L);
                Teacher mergedTeacher = entityManager.merge(teacher);

                transaction.commit();
            } catch (Exception e) {
                if(transaction != null && transaction.isActive()){
                    System.out.println("!ROLLBACK!");
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        }
    }
}
