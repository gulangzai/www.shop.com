/*
 * BsCallbackInfo.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2015-12-28 Created
 */
package com.xue.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javacommon.util.DateUtils;

/**
 * 学员回访记录信息
 * 
 * @author Mr.Ruan
 * @version 1.0 2015-12-28
 */
public class BsCallbackInfo {
    private Integer id;

    /**
    /*回访记录内容
    */
    private String callbackContent;

    /**
    /*创建时间
    */
    private Date createTime;
    
    private String createTimeLabel;

    /**
    /*删除时间
    */
    private Date updateTime;

    /**
    /*回访老师id
    */
    private String teacherId;

    private String studentId;
    /**
    /*状态
    */
    private Integer statu;
    
    public BsCallbackInfo(){}

    public BsCallbackInfo(String userId, String studentId) {
		// TODO Auto-generated constructor stub
    	this.teacherId = userId;
    	this.studentId= studentId;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCallbackContent() {
        return callbackContent;
    }

    public void setCallbackContent(String callbackContent) {
        this.callbackContent = callbackContent == null ? null : callbackContent.trim();
    }

    public Date getCreateTime() { 
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
 
    public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
	 
	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public Integer getStatu() {
        return statu;
    }

    public void setStatu(Integer statu) {
        this.statu = statu;
    }

	public String getCreateTimeLabel() {
		System.out.println("createTime:"+createTime);
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			createTimeLabel =df.format(createTime);  
		return createTimeLabel;
	}

	public void setCreateTimeLabel(String createTimeLabel) {
		this.createTimeLabel = createTimeLabel;
	}
    
    
}