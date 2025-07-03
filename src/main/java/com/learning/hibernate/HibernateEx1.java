package com.learning.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.learning.hibernate.entity.Student;

public class HibernateEx1 {
    public static void main(String[] args) {
        try(SessionFactory factory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Student.class)
                    .buildSessionFactory();
            Session session = factory.getCurrentSession()){

            Transaction transaction = session.getTransaction();

            try{
                // Student student = new Student("Chanel", "King", 9.1);
                // transaction.begin();

                // System.out.println("Create entity: " + session.contains(student));

                // session.persist(student);

                // System.out.println("After persist: " + session.contains(student));

                // transaction.commit();

                Student student = new Student("Leo", "Farrel", 8.4);
                transaction.begin();
                System.out.println(student);
                session.persist(student);
                System.out.println(student);

                Thread.sleep(15000);

                transaction.commit();
            } catch(Exception e){
                if(transaction != null && transaction.isActive()){
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        }
    }
}
