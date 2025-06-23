package com.learning.relationships.one_to_many;

import com.learning.relationships.one_to_many.entity.University;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class LoadingTypesEx {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
            EntityManager entityManager = factory.createEntityManager();) {

            try{
                University university = entityManager.find(University.class, 1);
                System.out.println("University INFO:");
                System.out.println(university);

                university.getStudents().size();

                entityManager.close();

                System.out.println("Students INFO:");
                System.out.println(university.getStudents());
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
