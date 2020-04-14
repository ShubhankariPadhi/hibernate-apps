package com.sbits.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.sbits.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {
		
		
		
		//create sessionFactory
		SessionFactory factory=new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory();
		
		//create session
		Session session=factory.getCurrentSession();
		
		try {
			
			
			//start a transaction
			session.beginTransaction();
			
			//query students
			List<Student> theStudents=session.createQuery("from Student").list();
			
			
			//display students
			displayStudents(theStudents);
			
			
			
			//query students :last name="v"
			theStudents=session.createQuery("from Student s where 	s.lname='v'").list();
			
			//display thestudents 
			System.out.println("\n\n studentwho have last name 'v'");
			displayStudents(theStudents);
			
			
			//query students where lname='v' or fname='sizu'
			theStudents=session.createQuery("from Student s where s.lname='v' or s.fname='sizu'").list();
			
			System.out.println("\n\n students who have last name 'k' or first name 'sizu'");
			displayStudents(theStudents);
			
			
			//query students where students like '@gmail.com'
			theStudents=session.createQuery("from Student s where s.email LIKE '%gmail.com'").getResultList();
			
			System.out.println("\n\n students who have email end with @gmail.com");
			displayStudents(theStudents);
			
			
			//commit the transaction
			session.getTransaction().commit();
			
		    System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	}

	private static void displayStudents(List<Student> theStudents) {
		for(Student tempStudents:theStudents)
		{
			System.out.println(tempStudents);
		}
	}
	
}
