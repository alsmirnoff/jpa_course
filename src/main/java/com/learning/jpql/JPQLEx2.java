package com.learning.jpql;

import java.util.List;

import com.learning.jpql.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class JPQLEx2 {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
            EntityManager entityManager = factory.createEntityManager()) {
            
            EntityTransaction transaction = entityManager.getTransaction();
            
            try{
                transaction.begin();

            // All Students with 'a' in name
                // List<Student> students = entityManager.createQuery("select s from Student s" + 
                //                 " where s.name LIKE '%a%'", Student.class).getResultList();

                // ===================================================

            // All Students with 'a' or 'A' in name
                // List<Student> students = entityManager.createQuery("select s from Student s" + 
                //                 " where lower(s.name) LIKE '%a%'", Student.class).getResultList();

                // ===================================================

            // All Students without avg grade info
                // List<Student> students = entityManager.createQuery("select s from Student s" + 
                //                 " where avgGrade IS NULL", Student.class).getResultList();

                // ===================================================

            // All Students without avg grade info
                // List<Student> students = entityManager.createQuery("select s from Student s" + 
                //                 " where avgGrade IS NULL", Student.class).getResultList();

                // ===================================================

            // All Students with 'l' in name and avg grade > 8.5
                // List<Student> students = entityManager.createQuery("select s from Student s" + 
                //                 " where s.name LIKE '%l%' AND s.avgGrade > 8.5", Student.class).getResultList();

                // ===================================================

            // All Students names
                // List<String> names = entityManager.createQuery("select s.name from Student s", String.class).getResultList();

                // ===================================================

            // All Students names and avg grades
                // List<Object[]> studentsInfo = entityManager.createQuery("select s.name, s.avgGrade from Student s", Object[].class).getResultList();
                // // Object[0] - name
                // // Object[1] - avgGrade

                // for (Object[] info : studentsInfo) {
                //     System.out.println(info[0] + " --> " + info[1]);
                // }

                // ===================================================

            // MAX avg grade
                // Query query = entityManager.createQuery("select max(s.avgGrade) from Student s");
                // double maxGrade = (double) query.getSingleResult();
                // System.out.println("MAX avg grade = " + maxGrade);

                // ===================================================

            // Average of avg grade
                Query query = entityManager.createQuery("select avg(s.avgGrade) from Student s");
                double avgGrade = (double) query.getSingleResult();
                System.out.println("Average of avg grade = " + avgGrade);


                // System.out.println(studentsInfo);


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
