package com.quiz.category;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.quiz.admin.AdminDashboard;
import com.quiz.connection.*;

public class CreateCategory {
	CreateConnection cc =new CreateConnection();
	Connection con=cc.getConnect();
	Category cat =new Category();
	
	//create table 
	 
	 public void createCategory() {
	  try {
	   DatabaseMetaData db = con.getMetaData();
	    ResultSet table = db.getTables(null, null, "Quizcategory", new String[] { "TABLE" });
	    if (table.next()) {
	     System.out.println("Table Already exist!");

	    } else {
	     String query = "create table Quizcategory(catid int not null primary key auto_increment,category varchar(255))";
	     Statement s = con.createStatement();
	     if(s.execute(query)==false)
	      System.out.println("Table created successfully");
	    }
	   } catch (Exception e) {
	    System.out.println(e);
	   }
	  }

	//insert records into table
	
	public void insertCategory(String category) {
		try {
			createCategory();
			String query1 ="insert into Quizcategory(category) values(?)";
			PreparedStatement ps=con.prepareStatement(query1);
			cat.setCategory(category);
			ps.setString(1, cat.getCategory());
			if(ps.execute()==false) {
				System.out.println("Category inserted Successfully!");
				AdminDashboard.selectOption();
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	 // delete specific category
	 public void deleteCategory(String category) {
	   try {
	    String query = "delete * from Quizcategory where category=?";
	    PreparedStatement ps = con.prepareStatement(query);
	    cat.setCategory(category);
	    ps.setString(1, cat.getCategory());
	    if(ps.execute()==false)
	     System.out.println("delete record successfully");
	    AdminDashboard.selectOption();
	   } catch (Exception e) {
	    System.out.println(e);
	   }
	  }

	  // update category wrt id.
	 public void updateCategory(String category, int id) {
	   try {
	    String query = "update Quizcategory set category=? wherecatid=?";
	    PreparedStatement ps = con.prepareStatement(query);
	    cat.setCategory(category);
	    cat.setCatid(id);
	    ps.setString(1, cat.getCategory());
	    ps.setInt(2, cat.getCatid());
	    if(ps.execute()==false)
	     System.out.println("update record successfully");
	    AdminDashboard.selectOption();
	   } catch (Exception e) {
	    System.out.println(e);
	   }
	  }

	  // Select categories from database
	 public void selectCategory() {
	   try {
	    String query = "select * from Quizcategory ";
	    Statement s = con.createStatement();
	    ResultSet rs = s.executeQuery(query);
	    while (rs.next()) {
	     System.out.println(rs.getString(2));
	    }
	   } catch (Exception e) {
	    System.out.println(e);
	   }
	  }
	

}
