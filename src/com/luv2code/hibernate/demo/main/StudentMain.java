package com.luv2code.hibernate.demo.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;



public class StudentMain {

	public static void main(String[] args) {

		SessionFactory factory= new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory(); 
		
		Session session =factory.getCurrentSession();
		
		try {
			//create object
			System.out.println("1- Creating new student Objects");
			Student student = new Student("Ahmed","San","amer@gmail");
			
			 //start transaction
			session.beginTransaction();
			System.out.println("2- Transaction begin");
			
			// save object
			session.save(student);
			System.out.println("3- Saving The Student");
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("4- Commit Transaction");

		}finally{
			factory.close();
			session.close();
		}
		
	}
	
	//Session Factory
	// 1- this is the person that reads the hibernate config file
	// 2- gets a connection to the database
	// 3- create a session objects
	// 4- its a heavy weight object 
	// 5- you only create it once in your application and reuse it over and over
	
	
	//Session
	// 1- is just a wrapper around JDBC connection to the database
	// 2- main object for saving and retrieving objects to the db
	// 5- short lived object   
	// 4- you use it then throw it away and go for another session

}
