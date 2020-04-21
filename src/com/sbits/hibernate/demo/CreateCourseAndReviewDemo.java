package com.sbits.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.sbits.demo.entity.Course;
import com.sbits.demo.entity.Instructor;
import com.sbits.demo.entity.InstructorDetail;
import com.sbits.demo.entity.Review;


public class CreateCourseAndReviewDemo {

	public static void main(String[] args) {
		
		
		
		//create sessionFactory
		SessionFactory factory=new Configuration().configure()
				           .addAnnotatedClass(InstructorDetail.class)
				           .addAnnotatedClass(Instructor.class)
				           .addAnnotatedClass(Course.class)
				           .addAnnotatedClass(Review.class)
				           .buildSessionFactory();
		
		//create session
		Session session=factory.getCurrentSession();
		
		try {
			
			//start a transaction
			session.beginTransaction();
			
		
			Course tempCourse=new Course();
			
			tempCourse.addReview(new Review("review4"));
			tempCourse.addReview(new Review("review5"));
			tempCourse.addReview(new Review("review6"));
			
			
			System.out.println("saving the course");
			System.out.println(tempCourse);
			System.out.println(tempCourse.getReviews());
			
			session.save(tempCourse);
			
			//commit the transaction
			session.getTransaction().commit();
			
		    
		}
		finally {
			session.close();
			factory.close();
		}
	}

}
