package com.quiz.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class CreateConnection {
	Connection con=null;
	
	public Connection getConnect() {
		try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
		     con=DriverManager.getConnection("jdbc:mysql://localhost:3306/quizproject","root","vrushali@123");
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return con;
	}

}
