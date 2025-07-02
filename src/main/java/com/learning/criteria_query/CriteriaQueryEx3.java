package com.learning.criteria_query;

import java.util.List;

import com.learning.criteria_query.entity.Student;
import com.learning.criteria_query.entity.University;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;

public class CriteriaQueryEx3 {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
            EntityManager entityManager = factory.createEntityManager()) {
            
            EntityTransaction transaction = entityManager.getTransaction();
            
            try{                
                // // JPQL: select s.name, s.avgGrade from Student s;

                // // 1 Creation of Criteria Builder
                // CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

                // // 2 Creation of Criteria Query
                // CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);

                // // 3 Root creation
                // Root<Student> root = criteriaQuery.from(Student.class); // from Student s

                // // 4 Adding root to Criteria Query
                // //criteriaQuery.multiselect(root.get("name"), root.get("avgGrade")); // select s.name, s.avgGrade from Student s
                // criteriaQuery.select(criteriaBuilder.array(root.get("name"), root.get("avgGrade")));

                // // 5 Query creation
                // TypedQuery<Object[]> query = entityManager.createQuery(criteriaQuery);
                // List<Object[]> studentInfo = query.getResultList();

                // for (Object[] info : studentInfo) {
                //     System.out.println(info[0] + " " + info[1]);
                // }

                // ===================================================

                // JPQL: select u, s from University u join Student u.students s;

                // 1 Creation of Criteria Builder
                CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

                // 2 Creation of Criteria Query
                CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);

                // 3 Root creation
                Root<University> root = criteriaQuery.from(University.class); // from University u

                // 3.1 JOIN
                Join<University, Student> join = root.join("students");

                // 4 Adding root to Criteria Query
                criteriaQuery.select(criteriaBuilder.array(root, join)); // select u, s from University u join Student u.students s;

                // 5 Query creation
                TypedQuery<Object[]> query = entityManager.createQuery(criteriaQuery);
                List<Object[]> students = query.getResultList();

                for (Object[] student : students) {
                    System.out.println(student[0] + " ---> " + student[1]);
                }
                

            } catch (Exception e) {
                if(transaction != null && transaction.isActive()){
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        }
    }
}
