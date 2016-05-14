package com.xue.dto;

public class OpenCourseRecodeQuery {

	private int currentPage;
	
	private int startNum = 0;
	
	String userId = null;
	
	String studentId = null;
	
	String studentName = null;
	
	String mobile = null;

	
	public OpenCourseRecodeQuery(int currentPage) {
		// TODO Auto-generated constructor stub
		this.currentPage = currentPage;
		startNum=(currentPage-1)*5;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getStartNum() {
		return startNum;
	}

	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}

	public void setStudentId(String studentId) {
		// TODO Auto-generated method stub
		this.studentId=(studentId==null)?null:studentId;
	} 
	
	public String getStudentId() {
		return studentId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		// TODO Auto-generated method stub
		this.mobile=mobile;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	} 
}
