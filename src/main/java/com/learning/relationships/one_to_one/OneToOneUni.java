package com.learning.relationships.one_to_one;

import com.learning.relationships.one_to_one.entity.Passport;
import com.learning.relationships.one_to_one.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class OneToOneUni {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
            EntityManager entityManager = factory.createEntityManager();) {
            
            EntityTransaction transaction = entityManager.getTransaction();

            try{
                transaction.begin();

                // PERSIST

                // ===================================================

                // Student student1 = new Student("Chanel", "King", 9.1);
                // Passport passport1 = new Passport("chanel.king@gmail.com", 174, "blue");

                // entityManager.persist(passport1);
                // entityManager.persist(student1);

                // ===================================================

                // Student student1 = new Student("Leo", "Farrel", 8.4);
                // Passport passport1 = new Passport("leo.farrel@yahoo.com", 178, "black");

                // student1.setPassport(passport1);

                // entityManager.persist(passport1);
                // entityManager.persist(student1);

                // ===================================================

                // Student student1 = new Student("Julia", "Dean", 8.7);
                // Passport passport1 = new Passport("julia.dean@gmail.com", 168, "green");

                // student1.setPassport(passport1);

                // entityManager.persist(student1);
                // entityManager.persist(passport1);

                // ===================================================

                // Student student1 = new Student("Serene", "Nielsen", 7.2);
                // Passport passport1 = new Passport("serena.nielsen@gmail.com", 172, "brown");

                // student1.setPassport(passport1);

                // // entityManager.persist(passport1);
                // entityManager.persist(student1);

                // ===================================================

                // Student student1 = new Student("Isaac", "Clarke", 9.8);
                // Passport passport1 = new Passport("isaac.clarke@yahoo.com", 183, "blue");

                // student1.setPassport(passport1);

                // // entityManager.persist(passport1);
                // entityManager.persist(student1);

                // ===================================================

                // FIND

                // Student student = entityManager.find(Student.class, 100);
                // System.out.println(student);
                // System.out.println(student.getPassport());

                // ===================================================
                
                // REMOVE

                // Student student = entityManager.find(Student.class, 2);
                // entityManager.remove(student);

                // ===================================================

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
