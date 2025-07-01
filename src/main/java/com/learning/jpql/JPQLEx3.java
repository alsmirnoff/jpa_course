package com.learning.jpql;

import java.util.List;

import com.learning.jpql.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class JPQLEx3 {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
            EntityManager entityManager = factory.createEntityManager()) {
            
            EntityTransaction transaction = entityManager.getTransaction();
            
            try{
                transaction.begin();

            // All Students with 'l' in name and avg grade > 8.5
                // TypedQuery<Student> query = entityManager.createQuery("select s from Student s" + 
                //                 " where s.name LIKE ?1 AND s.avgGrade > ?2", Student.class);

                // query.setParameter(1, "%l%");
                // query.setParameter(2, 8.5);

                // List<Student> students = query.getResultList();

                // ===================================================

            // All Students with 'l' in name and avg grade > 8.5
                // TypedQuery<Student> query = entityManager.createQuery("select s from Student s" + 
                //                 " where s.name LIKE :letter AND s.avgGrade > :grade", Student.class);

                // query.setParameter("letter", "%l%");
                // query.setParameter("grade", 8.5);

                // List<Student> students = query.getResultList();

                // ===================================================

            // UPDATE
                // Query query = entityManager.createQuery("update Student s" + 
                //             " set avgGrade = 7.0 where length(surname) > 6");

                // query.executeUpdate();

                // ===================================================

            // DELETE
                Query query = entityManager.createQuery("delete Student s" + 
                            " where s.avgGrade < 7.5 OR s.avgGrade IS NULL");

                query.executeUpdate();

                // ===================================================

                // System.out.println(students);
                
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
