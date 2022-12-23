package com.quiz.result;

import java.sql.Date;

public class Result {
	private int userId;
	private int catId;
	private int marks;
	private String grade;
	private Date date;
	
	public String toString() {
		return "Result [userId=" + userId + ", catId=" + catId + ", marks=" + marks + ", grade=" + grade + ", date="
				+ date + "]";
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getCatId() {
		return catId;
	}
	public void setCatId(int catId) {
		this.catId = catId;
	}
	public int getMarks() {
		return marks;
	}
	public void setMarks(int marks) {
		this.marks = marks;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}



}
