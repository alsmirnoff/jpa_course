package com.learning.inheritance_mapping;

import java.util.List;

import com.learning.inheritance_mapping.entity.Driver;
import com.learning.inheritance_mapping.entity.Employee;
import com.learning.inheritance_mapping.entity.Teacher;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class InheritanceMappingEx {
public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
            EntityManager entityManager = factory.createEntityManager()) {
            
            EntityTransaction transaction = entityManager.getTransaction();
            
            try{
                transaction.begin();

                // Teacher teacher = new Teacher("Alessandro", 2500, 8d, "CS", true);
                // Driver driver = new Driver("Peter", 2300, 15d, 'B', "BMW");

                // entityManager.persist(teacher);
                // entityManager.persist(driver);

                // ===================================================

                // Teacher teacher = new Teacher("Rio", 200, 3d, "Biology", false);
                // Driver driver = new Driver("Michael", 2800, 25d, 'C', "Mercedes");

                // entityManager.persist(teacher);
                // entityManager.persist(driver);

                // ===================================================

                // TypedQuery<Employee> query = entityManager.createQuery("SELECT emp FROM Employee emp", Employee.class);
                // List<Employee> employees = query.getResultList();
                // System.out.println(employees);

                // ===================================================

                TypedQuery<Driver> query = entityManager.createQuery("SELECT dr FROM Driver dr", Driver.class);
                List<Driver> drivers = query.getResultList();
                System.out.println(drivers);

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
