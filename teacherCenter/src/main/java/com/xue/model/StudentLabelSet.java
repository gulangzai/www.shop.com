/*
 * StudentInfo.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2015-09-10 Created
 */
package com.xue.model;

import java.sql.Timestamp;

/**
 * 
 * 
 * @author zhangxy
 * @version 1.0 2015-12-01
 */
public class StudentLabelSet {
    private Integer id;

    /**
    /*学员id
    */
    private String studentId;

    /**
    /*标签id
    */
    private Integer labelId;

    public Integer getLabelId() {
		return labelId;
	}

	public void setLabelId(Integer labelId) {
		this.labelId = labelId;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId == null ? null : studentId.trim();
	}
	
}