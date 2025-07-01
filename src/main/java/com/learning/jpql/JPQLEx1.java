package com.learning.jpql;

import java.util.List;

import com.learning.jpql.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class JPQLEx1 {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
            EntityManager entityManager = factory.createEntityManager()) {
            
            EntityTransaction transaction = entityManager.getTransaction();
            
            try{
                transaction.begin();

            // All Students
            // select * from students;
                // Query query = entityManager.createQuery("select s from Student s");
                // List<Student> students = query.getResultList();

                // TypedQuery<Student> query = entityManager.createQuery("select s from Student s", Student.class);
                // List<Student> students = query.getResultList();

                // List<Student> students = entityManager.createQuery("select s from Student s").getResultList();

                // List<Student> students = entityManager.createQuery("from Student").getResultList();

                // ===================================================

            // All Students with name Leo
                // List<Student> students = entityManager.createQuery("select s from Student s" + 
                //                 " where s.name = 'Leo'").getResultList();

                // ===================================================

            // All Students with avg grade > 8.5
                // List<Student> students = entityManager.createQuery("select s from Student s" + 
                //                 " where s.avgGrade > 8.5", Student.class).getResultList();

                // ===================================================

            // All Students with avg grade between 7 and 8
                List<Student> students = entityManager.createQuery("select s from Student s" + 
                                " where s.avgGrade between 7 and 8", Student.class).getResultList();

                System.out.println(students);


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
