/*
 * ZUsers4.java
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
public class ZUsers4 {
    /**
    /*ID,主键
    */
    private Integer id;

    /**
    /*账号
    */
    private String name;

    /**
    /*密码
    */
    private String pwd;

    /**
    /*金额
    */
    private Float monSum;

    /**
    /*权限
    */
    private Integer quanx;

    /**
    /*折扣
    */
    private Float zhekou;

    /**
    /*手机号
    */
    private String tel1;

    /**
    /*创建时间
    */
    private Date fTime;

    /**
    /*最后登录时间
    */
    private Date lTime;

    /**
    /*管理员姓名
    */
    private String username;

    /**
    /*机构姓名
    */
    private String cjName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public Float getMonSum() {
        return monSum;
    }

    public void setMonSum(Float monSum) {
        this.monSum = monSum;
    }

    public Integer getQuanx() {
        return quanx;
    }

    public void setQuanx(Integer quanx) {
        this.quanx = quanx;
    }

    public Float getZhekou() {
        return zhekou;
    }

    public void setZhekou(Float zhekou) {
        this.zhekou = zhekou;
    }

    public String getTel1() {
        return tel1;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1 == null ? null : tel1.trim();
    }

    public Date getfTime() {
        return fTime;
    }

    public void setfTime(Date fTime) {
        this.fTime = fTime;
    }

    public Date getlTime() {
        return lTime;
    }

    public void setlTime(Date lTime) {
        this.lTime = lTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getCjName() {
        return cjName;
    }

    public void setCjName(String cjName) {
        this.cjName = cjName == null ? null : cjName.trim();
    }
}