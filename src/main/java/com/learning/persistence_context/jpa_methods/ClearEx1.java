package com.learning.persistence_context.jpa_methods;

import com.learning.persistence_context.entity.Teacher;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class ClearEx1 {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
            EntityManager entityManager = factory.createEntityManager()) {
            
            EntityTransaction transaction = entityManager.getTransaction();
            
            try{
                transaction.begin();

                Teacher teacher1 = entityManager.find(Teacher.class, 3);
                Teacher teacher2 = entityManager.find(Teacher.class, 4);
                System.out.println("teacher is in persistence context: " + entityManager.contains(teacher1));
                System.out.println("teacher is in persistence context: " + entityManager.contains(teacher2));

                entityManager.clear();

                System.out.println("teacher is in persistence context: " + entityManager.contains(teacher1));
                System.out.println("teacher is in persistence context: " + entityManager.contains(teacher2));
                
                teacher1.setIsProfessor(true);
                teacher2.setIsProfessor(true);

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
