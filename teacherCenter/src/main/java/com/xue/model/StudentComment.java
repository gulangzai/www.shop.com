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
public class StudentComment {
    private Integer id;

    /**
    /*学员id
    */
    private String studentId;

    /**
    /*支持情况：1，支持，2，不支持
    */
    private Integer commentType;
    
    /**
    /*我的回答id
    */
    private Integer answerId;

	public Integer getAnswerId() {
		return answerId;
	}

	public void setAnswerId(Integer answerId) {
		this.answerId = answerId;
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

	public Integer getCommentType() {
		return commentType;
	}

	public void setCommentType(Integer commentType) {
		this.commentType = commentType;
	}
	
	
}