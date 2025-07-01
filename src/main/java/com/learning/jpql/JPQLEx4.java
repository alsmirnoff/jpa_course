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

public class JPQLEx4 {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
            EntityManager entityManager = factory.createEntityManager()) {
            
            EntityTransaction transaction = entityManager.getTransaction();
            
            try{
                transaction.begin();

            // Select universities without students
                // TypedQuery<University> query = entityManager.createQuery("select u from University u where u.students IS EMPTY", University.class);
                // List<University> universities = query.getResultList();

                // ===================================================

            // Select universities with one student
                // TypedQuery<University> query = entityManager.createQuery("select u from University u where size(u.students) = 1", University.class);
                // List<University> universities = query.getResultList();

                // ===================================================

            // Sort universities by count of students DESC
                // TypedQuery<University> query = entityManager.createQuery("SELECT u FROM University u ORDER BY size(u.students) DESC", University.class);
                // List<University> universities = query.getResultList();

                // ===================================================

            // CROSS JOIN
                // TypedQuery<Object[]> query = entityManager.createQuery("SELECT u,s FROM University u, Student s", Object[].class);
                // List<Object[]> results = query.getResultList();

                // for (Object[] result : results) {
                //     System.out.println(result[0] + " ----> " + result[1]);
                // }

                // ===================================================

            // JOIN
                TypedQuery<Object[]> query = entityManager.createQuery("SELECT u,s FROM University u JOIN u.students s", Object[].class);
                List<Object[]> results = query.getResultList();

                for (Object[] result : results) {
                    System.out.println(result[0] + " ----> " + result[1]);
                }

                // ===================================================

                // System.out.println(results);

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
