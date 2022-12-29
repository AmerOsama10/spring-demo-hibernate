package com.luv2code.hibernate.demo.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

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

public class StudentMain {
	 static SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();
	 static Session session ;

	public static void main(String[] args) {

		try {

			Student student = new Student("Amer", "Osama", "Amer@gmail");

			saveToDatabase(student);
			retrieveFromDatabase( student.getId());

		} finally {
			factory.close();
			session.close();
		}

	}

	public static void saveToDatabase(Student student) {
		
		 session = factory.getCurrentSession();
		// 1-start transaction
		session.beginTransaction();
		System.out.println("1- Transaction begin");

		// 2-save object
		session.save(student);
		System.out.println("2- Saving The Student");

		// 3-commit transaction
		session.getTransaction().commit();
		System.out.println("3- Commit Transaction");
	}

	public static void retrieveFromDatabase(int id) {

		Session session = factory.getCurrentSession();
		// 1-start transaction
		session.beginTransaction();
		System.out.println("\n\n1- Transaction begin");

		// 2-retrieve object with id
		Student student = session.get(Student.class, id);
		System.out.println(student);
		System.out.println("2- Retrieve the Student");

		// 3-commit transaction
		session.getTransaction().commit();
		System.out.println("3- Commit Transaction");
	}
}
