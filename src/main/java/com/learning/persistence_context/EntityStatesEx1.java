package com.learning.persistence_context;

import com.learning.persistence_context.entity.Teacher;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class EntityStatesEx1 {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
            EntityManager entityManager = factory.createEntityManager()) {
            
            EntityTransaction transaction = entityManager.getTransaction();
            
            try{
                transaction.begin();

                Teacher teacher = new Teacher("Alessandro", "Lozano", "CS", true);

                System.out.println("Create entity: " + entityManager.contains(teacher));

                entityManager.persist(teacher);

                System.out.println("After persist: " + entityManager.contains(teacher));

                transaction.commit();

                System.out.println("After commit: " + entityManager.contains(teacher));
            } catch (Exception e) {
                if(transaction != null && transaction.isActive()){
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        }
    }
}
