package com.learning.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.learning.hibernate.entity.Student;

public class HibernateEx3 {
    public static void main(String[] args) {
        try(SessionFactory factory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Student.class)
                    .buildSessionFactory();
            Session session = factory.getCurrentSession()){

            Transaction transaction = session.getTransaction();

            try{
                transaction.begin();

                Student student = session.find(Student.class, 2);
                student.setAvgGrade(9.0);
                System.out.println("Average grade was updated");

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
