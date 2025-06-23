package com.learning.relationships.one_to_many;

import java.sql.Date;

import com.learning.relationships.one_to_many.entity.Student;
import com.learning.relationships.one_to_many.entity.University;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class OneToManyBi {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
            EntityManager entityManager = factory.createEntityManager();) {
            
            EntityTransaction transaction = entityManager.getTransaction();

            try{
                transaction.begin();

            // PERSIST

                // University university = new University("MIT", Date.valueOf("1861-04-10"));
                // Student student1 = new Student("Isaac", "Clarke", 9.8);
                // Student student2 = new Student("Serena", "Nielsen", 7.2);
                // Student student3 = new Student("Chanel", "King", 7.2);
                // Student student4 = new Student("Roy", "Harper", 7.0);

                
                // university.addStudentToUniversity(student1);
                // university.addStudentToUniversity(student2);
                // university.addStudentToUniversity(student3);
                // university.addStudentToUniversity(student4);

                // entityManager.persist(university);

                // ===================================================

                // Student student3 = new Student("Roy", "Harper", 7.9);
                // Student student4 = new Student("Eric", "Scott", 7.4);
                // University university = new University("Oxford", Date.valueOf("1200-09-01"));

                // university.addStudentToUniversity(student3);
                // university.addStudentToUniversity(student4);

                // entityManager.persist(student3);

                // ===================================================

            // FIND

                University university = entityManager.find(University.class, 1);
                System.out.println(university);
                System.out.println(university.getStudents());

                // ===================================================

                // Student student = entityManager.find(Student.class, 2);
                // System.out.println(student);
                // System.out.println(student.getUniversity());
                // System.out.println(student.getUniversity().getStudents());

                // ===================================================

            // REMOVE

                // Student student = entityManager.find(Student.class, 1);
                // entityManager.remove(student);

                // ===================================================

                // Student student = entityManager.find(Student.class, 4);
                // entityManager.remove(student);
                
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
