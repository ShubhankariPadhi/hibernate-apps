package com.sbits.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.sbits.demo.entity.Student;

public class DeleteStudentDemo {

	public static void main(String[] args) {
		
		
		
		//create sessionFactory
		SessionFactory factory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory();
		
		//create session
		Session session=factory.getCurrentSession();
		
		try {
			
			int studentId=123;
			//get a new session 
			session=factory.getCurrentSession();
			session.beginTransaction();
			
			//retrieve student based on the id: primary key
			System.out.println("\n getting student with studentId  "+ studentId );
			
			Student myStudent=session.get(Student.class,studentId);
			 
			//delete the student 1st approach
			//System.out.println("deleting student"+myStudent);
			//session.delete(myStudent);
			
			
			//delete the student id=124  second approach
			System.out.println("\n\n Deleting studet id=125 ");
			session.createQuery("delete from Student where id=125 ").executeUpdate();
			
			
			//commit the transaction
			session.getTransaction().commit();
			
			
				
		}
		finally {
			factory.close();
		}
	}

}
