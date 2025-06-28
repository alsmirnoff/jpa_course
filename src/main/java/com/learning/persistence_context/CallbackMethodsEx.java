package com.learning.persistence_context;

import com.learning.persistence_context.entity.Teacher;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class CallbackMethodsEx {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
            EntityManager entityManager = factory.createEntityManager();) {

            EntityTransaction transaction = entityManager.getTransaction();
            
            try{
                transaction.begin();

            // PERSIST

                // Teacher teacher1 = new Teacher("User", "Testov", "Java", false);
                // entityManager.persist(teacher1);

                // teacher1.setSubject("SQL");

                // ===================================================

            // REMOVE

                Teacher teacher1 = entityManager.find(Teacher.class, 7);
                entityManager.remove(teacher1);

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
