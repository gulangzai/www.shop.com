package com.xue.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class CourseMajorShop extends CourseMajor{
	 @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date validity;

 	/**
    /*科目名称
    */
    private String subjectName;
	    
    /**
    /*班级名称
    */
    private String classesName;
	 
    /**
     * 科目ID
     */
    private String subjectId;
    
    
    
	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public Date getValidity() {
		return validity;
	}

	public void setValidity(Date validity) {
		this.validity = validity;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getClassesName() {
		return classesName;
	}

	public void setClassesName(String classesName) {
		this.classesName = classesName;
	}
	   
	   
	   
}
