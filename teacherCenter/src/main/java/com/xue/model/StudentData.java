/*
 * StudentData.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2015-12-28 Created
 */
package com.xue.model;

import java.util.Date;

/**
 * 
 * 
 * @author Mr.Ruan
 * @version 1.0 2015-12-28
 */
public class StudentData {
    private Integer studentDataId;

    /**
    /*真实姓名
    */
    private String realName;

    private String nickName;

    /**
    /*性别、1，男；2，女；
    */
    private Integer gender;

    /**
    /*QQ号
    */
    private Integer qqNumber;

    /**
    /*出生日期
    */
    private Date birthDate;

    /**
    /*省份
    */
    private String provinces;

    /**
    /*市
    */
    private String city;

    /**
    /*区
    */
    private String districts;

    /**
    /*签名
    */
    private String signature;

    /**
    /*教育程度
    */
    private String education;

    /**
    /* 学校
    */
    private String school;

    /**
    /*专业
    */
    private String major;

    /**
    /*公司名称
    */
    private String companyName;

    /**
    /*职位
    */
    private String position;

    /**
    /*头像路径
    */
    private String headImg;

    /**
    /*证书A
    */
    private String certificateImgA;

    /**
    /*证书B
    */
    private String certificateImgB;

    /**
    /*证书C
    */
    private String certificateImgC;

    /**
    /*证书D
    */
    private String certificateImgD;

    public Integer getStudentDataId() {
        return studentDataId;
    }

    public void setStudentDataId(Integer studentDataId) {
        this.studentDataId = studentDataId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getQqNumber() {
        return qqNumber;
    }

    public void setQqNumber(Integer qqNumber) {
        this.qqNumber = qqNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getProvinces() {
        return provinces;
    }

    public void setProvinces(String provinces) {
        this.provinces = provinces == null ? null : provinces.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getDistricts() {
        return districts;
    }

    public void setDistricts(String districts) {
        this.districts = districts == null ? null : districts.trim();
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature == null ? null : signature.trim();
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg == null ? null : headImg.trim();
    }

    public String getCertificateImgA() {
        return certificateImgA;
    }

    public void setCertificateImgA(String certificateImgA) {
        this.certificateImgA = certificateImgA == null ? null : certificateImgA.trim();
    }

    public String getCertificateImgB() {
        return certificateImgB;
    }

    public void setCertificateImgB(String certificateImgB) {
        this.certificateImgB = certificateImgB == null ? null : certificateImgB.trim();
    }

    public String getCertificateImgC() {
        return certificateImgC;
    }

    public void setCertificateImgC(String certificateImgC) {
        this.certificateImgC = certificateImgC == null ? null : certificateImgC.trim();
    }

    public String getCertificateImgD() {
        return certificateImgD;
    }

    public void setCertificateImgD(String certificateImgD) {
        this.certificateImgD = certificateImgD == null ? null : certificateImgD.trim();
    }
}