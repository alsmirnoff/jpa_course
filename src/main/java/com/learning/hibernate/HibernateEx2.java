package com.learning.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.learning.hibernate.entity.Student;

public class HibernateEx2 {
    public static void main(String[] args) {
        try(SessionFactory factory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Student.class)
                    .buildSessionFactory();
            Session session = factory.getCurrentSession()){

            Transaction transaction = session.getTransaction();

            try{
                transaction.begin();

                // Student student = session.find(Student.class, 2);
                // System.out.println(student);

                // ===================================================

            // First Level Cache check
                // Student student1 = session.find(Student.class, 2);
                // Student student2 = session.find(Student.class, 2);

                // ===================================================

            // null if no such student
                // Student student = session.find(Student.class, 3);
                // System.out.println(student);

                // ===================================================

            // find not valid without transaction
                Student student = session.find(Student.class, 1);
                System.out.println(student);

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
