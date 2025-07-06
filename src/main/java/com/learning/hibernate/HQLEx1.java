package com.learning.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.learning.hibernate.entity.Student;

public class HQLEx1 {
    public static void main(String[] args) {
        try(SessionFactory factory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Student.class)
                    .buildSessionFactory();
            Session session = factory.getCurrentSession()){

            Transaction transaction = session.getTransaction();

            try{
                transaction.begin();

            // All Students
            // select * from students;
                // Query<Student> query = session.createQuery("from Student", Student.class);
                // List<Student> students = query.getResultList();
                // for (Student st : students) {
                //     System.out.println(st);
                // }

                // ===================================================

            // All Students where name with 'l' or 'L' and avgGrade > 8
                // List<Student> students = session.createQuery("FROM Student s WHERE s.name LIKE '%l%' AND s.avgGrade > 8", Student.class)
                //             .getResultList();
                // for (Student st : students) {
                //     System.out.println(st);
                // }

            // PARAMETRIZED All Students where name with 'l' or 'L' and avgGrade > 8 
                // Query<Student> query = session.createQuery("FROM Student s WHERE s.name LIKE :letter AND s.avgGrade > :grade", Student.class);
                // query.setParameter("letter", "%l%");
                // query.setParameter("grade", 8.0);
                // List<Student> students = query.getResultList();

                // for (Student st : students) {
                //     System.out.println(st);
                // }

            // UPDATE 
                // session.createMutationQuery("UPDATE Student s set s.avgGrade = 10.0 where length(s.name) = 5").executeUpdate();

            // DELETE
                session.createMutationQuery("DELETE Student s WHERE s.avgGrade < 9").executeUpdate();
                
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
