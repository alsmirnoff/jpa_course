package com.learning.jpql;

import java.util.List;

import com.learning.jpql.entity.Student;
import com.learning.jpql.entity.University;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class NamedQueryEx {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
            EntityManager entityManager = factory.createEntityManager()) {
            
            EntityTransaction transaction = entityManager.getTransaction();
            
            try{
                
                // TypedQuery<University> query = entityManager.createNamedQuery("University.allUniversitiesLessOrEqualTo2", University.class);
                // List<University> universities = query.getResultList();
                // System.out.println(universities);

                TypedQuery<Student> query = entityManager.createNamedQuery("University.studentsWithAvgGradeBetween", Student.class);
                query.setParameter("from", 6);
                query.setParameter("to", 8);
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
