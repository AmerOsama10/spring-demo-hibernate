package com.luv2code.hibernatedemo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;


public class TestJdbc {

	public static void main(String[] args) {

		String url = "jdbc:mysql://localhost:3306/sys";
		String user ="hbstudent";
		String pass ="hbstudent";

		try {
			System.out.println("Connection to database  "+url);
			Connection con = DriverManager.getConnection(url, user, pass);
			System.out.println("Connection successfully");
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

}
