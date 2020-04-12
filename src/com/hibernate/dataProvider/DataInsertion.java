package com.hibernate.dataProvider;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class DataInsertion {

	public static void main(String[] args) {
		
		new DataInsertion().insertInfo();
			}
	
	public void insertInfo()
	{
			Configuration con=new Configuration();
			con.configure("hibernate.cfg.xml");
			SessionFactory SF=con.buildSessionFactory();
			Session session=SF.openSession();
			
			DataProvider provider=new DataProvider();
			provider.setUser_id(121);
			provider.setUser_name("subhankari");
			provider.setUser_address("kld");
			
			Transaction TR=session.beginTransaction();
		
			//saving the obj of the pojo class
			session.save(provider);

			System.out.println("object saved successfully");
			TR.commit();
	        session.close();
	SF.close();
	}


}
