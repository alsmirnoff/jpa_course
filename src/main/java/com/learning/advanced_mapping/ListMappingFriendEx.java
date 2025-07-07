package com.learning.advanced_mapping;

import java.util.ArrayList;
import java.util.List;

import com.learning.advanced_mapping.entity.Employee;
import com.learning.advanced_mapping.entity.Friend;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class ListMappingFriendEx {
    public static void main(String[] args) {
        try (EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
            EntityManager entityManager = factory.createEntityManager()) {
            
            EntityTransaction transaction = entityManager.getTransaction();
            
            try{
                transaction.begin();

                // List<Friend> friendsList = new ArrayList<>();
                // Friend friend1 = new Friend("Chanel", "King", 22);
                // Friend friend2 = new Friend("Leo", "Farrel", 24);
                // Friend friend3 = new Friend("Julia", "Dean", 23);
                // friendsList.add(friend1);
                // friendsList.add(friend2);
                // friendsList.add(friend3);

                // Employee employee = new Employee("Michael", 4000, 15d, friendsList);
                // entityManager.persist(employee);

                // ===================================================

                Employee emp = entityManager.find(Employee.class, 1);
                System.out.println(emp);

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
