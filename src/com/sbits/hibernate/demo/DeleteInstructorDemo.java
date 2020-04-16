package com.sbits.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.sbits.demo.entity.Instructor;
import com.sbits.demo.entity.InstructorDetail;


public class DeleteInstructorDemo {

	public static void main(String[] args) {
		
		
		
		//create sessionFactory
		SessionFactory factory=new Configuration().configure("hibernate.cfg.xml")
				           .addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Instructor.class).buildSessionFactory();
		
		//create session
		Session session=factory.getCurrentSession();
		
		try {
			
			
			
			//start a transaction
			session.beginTransaction();
			
			//get the instructor detail object 
			int theId=3; 
			
			
			// Instructor
			InstructorDetail tempInstructorDetail=session.get(InstructorDetail.class,theId);
			System.out.println("tempInstructorDetail:"+tempInstructorDetail); // if id does not exist ,returns null
			
			//print the associated instructor
			System.out.println("the instructor "+tempInstructorDetail.getInstructor());// get() over null will give nullPointerException
			
			//now lets delete the instructor detail
			System.out.println("lets delete the instructor detail"+tempInstructorDetail);
			
			//deleted object would be re-saved by cascade  so to break the cascade bidirectional link the single instructuralDetail object
			tempInstructorDetail.getInstructor().setInstructorDetail(null);
			
			session.delete(tempInstructorDetail); // it will delete only instructorDetail not the instructor object as remove type cascade is not applied
			
			
			//commit the transaction
			session.getTransaction().commit();
			
		    System.out.println("done");
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			// handle connection leak issue
			session.close();// if an id which does not exist entered , error will come with connection leakage , to overcome  that error need to close the session
			factory.close();
		}
	}

}
