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

public class JPQLEx5 {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
            EntityManager entityManager = factory.createEntityManager()) {
            
            EntityTransaction transaction = entityManager.getTransaction();
            
            try{
                transaction.begin();

                System.out.println("First SELECT");
                Student student1 = entityManager.find(Student.class, 3);
                Student student2 = entityManager.find(Student.class, 3);

                System.out.println("Second SELECT");
                Student student3 = entityManager.createQuery("SELECT s FROM Student s WHERE s.id = 3", Student.class)
                        .getSingleResult();


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
