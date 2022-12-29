package com.luv2code.hibernate.demo.main;

import java.util.List;

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

////		1-insert object to database
//			Student student = new Student("Saed", "Amer", "Saed@gmail");
//			saveToDatabase(student);
//			
////		2-retrieve object from database			
//			retrieveFromDatabase( student.getId());
//
////		2-retrieve objects from database			
//			String query ="from Student";
//			String query ="from Student where lastName='Osama'";
//			String query ="from Student where lastName='Amer' or firstName='Amer' ";
//			String query ="from Student where email like 'A%' ";
//			Query(query,"retrieved");
//			
////		3-Update object to database						
//			Student student = new Student("Saed", "Amer", "Saed@gmail");
//			updateFromDatabase(4,student);
//			
////		3-Update objects to database						
//			String query ="update Student set lastName='Ahmed' where id=5";
//			Query(query,"updated");
//			
////		4-delete object to database						
			deleteFromDatabase(2);
//			
////		4-delete object from database						
//			String query ="delete Student where id=3";
//			Query(query,"deleted");
			

		} finally {
			session=null;
			factory=null;
		}

	}

	public static void saveToDatabase(Student student) {
		
		 session = factory.getCurrentSession();
		// 1-start transaction
		session.beginTransaction();
		System.out.println("1- Transaction begin");

		// 2-save object
		session.save(student);
		System.out.println("2- Student Inserted");

		// 3-commit transaction
		session.getTransaction().commit();
		System.out.println("3- Commit Transaction");
	}

	public static void retrieveFromDatabase(int id) {

		session = factory.getCurrentSession();
		// 1-start transaction
		session.beginTransaction();
		System.out.println("\n\n1- Transaction begin");

		// 2-retrieve object with id
		Student student = session.get(Student.class, id);
		System.out.println(student);
		System.out.println("2- Student Retrieved");

		// 3-commit transaction
		session.getTransaction().commit();
		System.out.println("3- Commit Transaction");
	}
	
	public static void updateFromDatabase(int id,Student s) {

		session = factory.getCurrentSession();
		// 1-start transaction
		session.beginTransaction();
		System.out.println("\n\n1- Transaction begin");

		// 2-update object with id
		Student student = session.get(Student.class, id);
		student.setFirstName(s.getFirstName());
		student.setLastName(s.getLastName());
		student.setEmail(s.getEmail());

		System.out.println(student);
		System.out.println("2- Student updated ");

		// 3-commit transaction
		session.getTransaction().commit();
		System.out.println("3- Commit Transaction");
	}

	
	public static void deleteFromDatabase(int id) {

		session = factory.getCurrentSession();
		// 1-start transaction
		session.beginTransaction();
		System.out.println("\n\n1- Transaction begin");

		// 2-update object with id
		Student student = session.get(Student.class, id);
		session.delete(student);
		System.out.println("2- Student deleted ");

		// 3-commit transaction
		session.getTransaction().commit();
		System.out.println("3- Commit Transaction");
	}
	
	public static void retrieveQuery(String query) {

		session = factory.getCurrentSession();
		// 1-start transaction
		session.beginTransaction();
		System.out.println("\n\n1- Transaction begin");

		// 2-retrieve object with id
		List<Student> students = session.createQuery(query).list();
		//students.forEach(x->System.out.println(x));
		students.forEach(System.out::println);
		System.out.println("2- Student Retrieved");

		// 3-commit transaction
		session.getTransaction().commit();
		System.out.println("3- Commit Transaction");
	}
	
	
	public static void Query(String query,String message) {

		session = factory.getCurrentSession();
		// 1-start transaction
		session.beginTransaction();
		System.out.println("\n\n1- Transaction begin");

		// 2-retrieve object with id
		session.createQuery(query).executeUpdate();
		System.out.println("2- Student "+message);

		// 3-commit transaction
		session.getTransaction().commit();
		System.out.println("3- Commit Transaction");
	}
	


}
