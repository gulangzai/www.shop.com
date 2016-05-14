/*
 * StudentInfoDetail.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2015-12-28 Created
 */
package com.xue.model;

/**
 * 学员详情
 * 
 * @author Mr.Ruan
 * @version 1.0 2015-12-28
 */
public class StudentInfoDetail{
	
    private Long id;

    /**
    /*真实名字
    */
    private String realName;

    /**
    /*昵称
    */
    private String nickName;

    /**
    /*性别（1：男 2：女3 ： 保密）
    */
    private Integer sex;

    /**
    /*手机
    */
    private String mobilePhone;

    /**
    /*邮箱
    */
    private String email;

    /**
    /*QQ
    */
    private String qq;

    /**
    /*个人签名
    */
    private String idiograph;

    /**
    /*教育程度
    */
    private Integer education;

    /**
    /*学校
    */
    private String school;

    /**
    /*你的专业
    */
    private String major;

    /**
    /*公司
    */
    private String company;

    /**
    /*职位
    */
    private String job;

    /**
    /*城市
    */
    private String city;

    private String birthday;

    private String studentId;
    
    private int courseProperty = 0;
    
    private int studentSource = 0;
    
    private int intention = 0;
    
    private String intentionRamark = null;
    
    /*  报名意向*/
    private String applyIntention = null;
    
    /* 报名班级*/
    private String applyClasses = null;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
 
    public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }
 
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public String getIdiograph() {
        return idiograph;
    }

    public void setIdiograph(String idiograph) {
        this.idiograph = idiograph == null ? null : idiograph.trim();
    }

    public Integer getEducation() {
        return education;
    }

    public void setEducation(Integer education) {
        this.education = education;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school == null ? null : school.trim();
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major == null ? null : major.trim();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job == null ? null : job.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday == null ? null : birthday.trim();
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId == null ? null : studentId.trim();
    }

	public int getCourseProperty() {
		return courseProperty;
	}

	public void setCourseProperty(int courseProperty) {
		this.courseProperty = courseProperty;
	}

	public int getStudentSource() {
		return studentSource;
	}

	public void setStudentSource(int studentSource) {
		this.studentSource = studentSource;
	}

	public int getIntention() {
		return intention;
	}

	public void setIntention(int intention) {
		this.intention = intention;
	}

	public String getIntentionRamark() {
		return intentionRamark;
	}

	public void setIntentionRamark(String intentionRamark) {
		this.intentionRamark = intentionRamark;
	}

	public String getApplyIntention() {
		return applyIntention;
	}

	public void setApplyIntention(String applyIntention) {
		this.applyIntention = applyIntention;
	}

	public String getApplyClasses() {
		return applyClasses;
	}

	public void setApplyClasses(String applyClasses) {
		this.applyClasses = applyClasses;
	} 
}