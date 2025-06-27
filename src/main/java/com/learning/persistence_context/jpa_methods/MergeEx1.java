package com.learning.persistence_context.jpa_methods;

import com.learning.persistence_context.entity.Teacher;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class MergeEx1 {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course")) {
            
            EntityManager entityManager = factory.createEntityManager();

            EntityTransaction transaction = entityManager.getTransaction();
            
            try{
                transaction.begin();

                Teacher teacher = new Teacher("Vera", "Walton", "Geography", true);
                entityManager.persist(teacher);
                
                transaction.commit();
                entityManager.close();

                // ===================================================

                teacher.setSubject("Math");

                entityManager = factory.createEntityManager();
                transaction = entityManager.getTransaction();

                transaction.begin();

                // entityManager.persist(teacher);
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
