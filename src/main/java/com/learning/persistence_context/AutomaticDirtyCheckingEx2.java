package com.learning.persistence_context;

import com.learning.persistence_context.entity.Teacher;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class AutomaticDirtyCheckingEx2 {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course")) {
            
            EntityManager entityManager = factory.createEntityManager();

            EntityTransaction transaction = entityManager.getTransaction();
            
            try{
                Teacher teacher = entityManager.find(Teacher.class, 4);

                entityManager.close();

                entityManager = factory.createEntityManager();
                transaction = entityManager.getTransaction();

                transaction.begin();

                teacher.setSubject("Economics");
                
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
