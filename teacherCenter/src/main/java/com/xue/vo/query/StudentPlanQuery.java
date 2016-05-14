/*
 * StudentPlan.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2015-09-10 Created
 */
package com.xue.vo.query;

/**
 * 学习计划模板数据
 * 
 * @author Mr.Ruan
 * @version 1.0 2015-09-10
 */
public class StudentPlanQuery {
    private Integer planId;

    /**
    /*模板名称
    */
    private String planName;

    /**
    /*每日学习时长
    */
    private Integer dayTime;

    /**
    /*开始时间
    */
    private String startDate;

    /**
    /*结束时间
    */
    private String endDate;

    /**
    /*总天数
    */
    private Integer totalDay;

    /**
    /*视频总数
    */
    private Integer videoTotal;

    /**
    /*计划创建时间
    */
    private String createDate;

    /**
    /*视频的总时间
    */
    private Integer totalVideoTime;

    private String studentId;

    private String majorId;
    
    private String detailId;

    /**
    /*是否是免费课程计划（0或者null：是 ，1：否）
    */
    private Integer isfree;
    
    
    
    public Integer getIsfree() {
		return isfree;
	}

	public void setIsfree(Integer isfree) {
		this.isfree = isfree;
	}

	public String getDetailId() {
		return detailId;
	}

	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}

	public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName == null ? null : planName.trim();
    }

    public Integer getDayTime() {
        return dayTime;
    }

    public void setDayTime(Integer dayTime) {
        this.dayTime = dayTime;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate == null ? null : startDate.trim();
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate == null ? null : endDate.trim();
    }

    public Integer getTotalDay() {
        return totalDay;
    }

    public void setTotalDay(Integer totalDay) {
        this.totalDay = totalDay;
    }

    public Integer getVideoTotal() {
        return videoTotal;
    }

    public void setVideoTotal(Integer videoTotal) {
        this.videoTotal = videoTotal;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate == null ? null : createDate.trim();
    }

    public Integer getTotalVideoTime() {
        return totalVideoTime;
    }

    public void setTotalVideoTime(Integer totalVideoTime) {
        this.totalVideoTime = totalVideoTime;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId == null ? null : studentId.trim();
    }

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId == null ? null : majorId.trim();
    }
}