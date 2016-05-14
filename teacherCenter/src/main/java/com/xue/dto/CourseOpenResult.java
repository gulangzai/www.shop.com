package com.xue.dto;

import java.util.List;

import com.xue.model.Shop;
import com.xue.model.StudentInfo;

public class CourseOpenResult {
	
	private List<Shop> shops = null;
	
	private StudentInfo studentInfo = null;

    
	
	public List<Shop> getShops() {
		return shops;
	}

	public void setShops(List<Shop> shops) {
		this.shops = shops;
	}

	public StudentInfo getStudentInfo() {
		return studentInfo;
	}

	public void setStudentInfo(StudentInfo studentInfo) {
		this.studentInfo = studentInfo;
	}
	
	
}
