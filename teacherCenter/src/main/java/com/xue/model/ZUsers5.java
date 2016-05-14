/*
 * ZUsers5.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2015-12-28 Created
 */
package com.xue.model;

/**
 * 
 * 
 * @author Mr.Ruan
 * @version 1.0 2015-12-28
 */
public class ZUsers5 {
    /**
    /*ID,主见
    */
    private Integer id;

    private String name;

    private String pwd;

    /**
    /*zonge
    */
    private Float monSum;

    /**
    /*quanxian
    */
    private Integer quanx;

    private String tel1;

    private Integer zt;

    private String peNme;

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

    public String getTel1() {
        return tel1;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1 == null ? null : tel1.trim();
    }

    public Integer getZt() {
        return zt;
    }

    public void setZt(Integer zt) {
        this.zt = zt;
    }

    public String getPeNme() {
        return peNme;
    }

    public void setPeNme(String peNme) {
        this.peNme = peNme == null ? null : peNme.trim();
    }
}