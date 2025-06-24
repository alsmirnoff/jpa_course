package com.learning.persistence_context;

import com.learning.persistence_context.entity.Teacher;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class FirstLevelCacheEx {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course")) {
            
            EntityManager entityManager = factory.createEntityManager();

            EntityTransaction transaction = entityManager.getTransaction();
            
            try{
                // transaction.begin();

                Teacher teacher1 = entityManager.find(Teacher.class, 3);

                entityManager.close();

                // transaction.commit();

                // transaction.begin();

                entityManager = factory.createEntityManager();

                Teacher teacher2 = entityManager.find(Teacher.class, 3);                

                // transaction.commit();

                entityManager.close();
                
            } catch (Exception e) {
                if(transaction != null && transaction.isActive()){
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        }
    }
}
