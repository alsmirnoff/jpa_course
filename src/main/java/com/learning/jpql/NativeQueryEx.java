package com.learning.jpql;

import java.util.List;

import com.learning.jpql.entity.Student;
import com.learning.jpql.entity.University;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class NativeQueryEx {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
            EntityManager entityManager = factory.createEntityManager()) {
            
            EntityTransaction transaction = entityManager.getTransaction();
            
            try{
                
                // Query query = entityManager.createNativeQuery("SELECT * FROM students", Student.class);
                // List<Student> students = query.getResultList();

                // ===================================================

                // Query query = entityManager.createNativeQuery("SELECT * FROM students" + 
                //             " WHERE avg_grade > 8 AND length(name) = 5", Student.class);
                // List<Student> students = query.getResultList();

                // ===================================================

                // Query query = entityManager.createNativeQuery("SELECT * FROM students" + 
                //             " WHERE avg_grade > :grade AND length(name) = :length", Student.class);
                // query.setParameter("grade", 8);
                // query.setParameter("length", 5);
                // List<Student> students = query.getResultList();

                // ===================================================

                Query query = entityManager.createNativeQuery("SELECT * FROM students" + 
                            " WHERE avg_grade > ?1 AND length(name) = ?2", Student.class);
                query.setParameter(1, 8);
                query.setParameter(2, 5);
                List<Student> students = query.getResultList();

                System.out.println(students);

            } catch (Exception e) {
                if(transaction != null && transaction.isActive()){
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        }
    }
}
