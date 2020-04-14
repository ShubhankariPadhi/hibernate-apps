package com.sbits.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.sbits.demo.entity.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {
		
		
		
		//create sessionFactory
		SessionFactory factory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory();
		
		//create session
		Session session=factory.getCurrentSession();
		
		try {
			
			int studentId=124;
			//get a new session 
			session=factory.getCurrentSession();
			session.beginTransaction();
			
			//retrieve student based on the id: primary key
			System.out.println("\n getting student with studentId  "+ studentId );
			
			Student myStudent=session.get(Student.class,studentId);
			 
			System.out.println("Updating firstname");
			myStudent.setFname("nobita");
			
			//commit the transaction
			session.getTransaction().commit();
			
			
			//bulk updation
			
			session=factory.getCurrentSession();
			session.beginTransaction();
			
			//udating email for all 
			System.out.println("\n\nupdating email of students  's@gmail.com'");
			session.createQuery("update Student set email='s@gmail.com' ").executeUpdate();
			
			//commit the transaction
			session.getTransaction().commit();
			
		}
		finally {
			factory.close();
		}
	}

}
