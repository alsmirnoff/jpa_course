package com.learning.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.learning.hibernate.entity.Student;
import com.learning.hibernate.entity.University;

public class HQLEx2 {
    public static void main(String[] args) {
        try(SessionFactory factory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Student.class)
                    .addAnnotatedClass(University.class)
                    .buildSessionFactory();
            Session session = factory.getCurrentSession()){

            Transaction transaction = session.getTransaction();

            try{
                transaction.begin();

            // All Students
            // select * from students and universities;
                Query<Object[]> query = session.createQuery("from University u JOIN u.students s ", Object[].class);
                List<Object[]> students = query.getResultList();
                for (Object[] info : students) {
                    System.out.println(info[0] + " ---> " + info[1]);
                }

                // ===================================================

                
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
