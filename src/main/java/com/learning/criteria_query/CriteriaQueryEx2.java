package com.learning.criteria_query;

import java.util.List;

import com.learning.criteria_query.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class CriteriaQueryEx2 {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
            EntityManager entityManager = factory.createEntityManager()) {
            
            EntityTransaction transaction = entityManager.getTransaction();
            
            try{                
                // // JPQL: select s.name from Student s;

                // // 1 Creation of Criteria Builder
                // CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

                // // 2 Creation of Criteria Query
                // CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);

                // // 3 Root creation
                // Root<Student> root = criteriaQuery.from(Student.class); // from Student s

                // // 4 Adding root to Criteria Query
                // criteriaQuery.select(root.get("name")); // select s.name from Student s

                // // 5 Query creation
                // TypedQuery<String> query = entityManager.createQuery(criteriaQuery);
                // List<String> names = query.getResultList();
                // System.out.println(names);

                // ===================================================

                // JPQL: select s from Student s where s.avgGrade >= 7.5;

                // 1 Creation of Criteria Builder
                CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

                // 2 Creation of Criteria Query
                CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);

                // 3 Root creation
                Root<Student> root = criteriaQuery.from(Student.class); // from Student s

                // 3.1 Condition creation
                Predicate predicate = criteriaBuilder.greaterThanOrEqualTo(root.get("avgGrade"), 7.5);
                
                // 3.2 Adding condition to Criteria Query
                criteriaQuery.where(predicate);

                // 4 Adding root to Criteria Query
                criteriaQuery.select(root); // select s from Student s where s.avgGrade >= 7.5;

                // 5 Query creation
                TypedQuery<Student> query = entityManager.createQuery(criteriaQuery);
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
