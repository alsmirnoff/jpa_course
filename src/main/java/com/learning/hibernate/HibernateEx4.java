package com.learning.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.learning.hibernate.entity.Student;

public class HibernateEx4 {
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
                session.remove(student);

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
