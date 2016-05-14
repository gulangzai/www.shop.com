/*
 * BaseSncreater.java
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
public class BaseSncreater {
    private Integer id;

    /**
    /*名称
    */
    private String name;

    /**
    /*代码
    */
    private String code;

    /**
    /*前缀
    */
    private String qz;

    private Short qyrq;

    private String rqgs;

    private Integer dqxh;

    private Short sfmrgx;

    private Short ws;

    /**
    /*更新时间
    */
    private String udate;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getQz() {
        return qz;
    }

    public void setQz(String qz) {
        this.qz = qz == null ? null : qz.trim();
    }

    public Short getQyrq() {
        return qyrq;
    }

    public void setQyrq(Short qyrq) {
        this.qyrq = qyrq;
    }

    public String getRqgs() {
        return rqgs;
    }

    public void setRqgs(String rqgs) {
        this.rqgs = rqgs == null ? null : rqgs.trim();
    }

    public Integer getDqxh() {
        return dqxh;
    }

    public void setDqxh(Integer dqxh) {
        this.dqxh = dqxh;
    }

    public Short getSfmrgx() {
        return sfmrgx;
    }

    public void setSfmrgx(Short sfmrgx) {
        this.sfmrgx = sfmrgx;
    }

    public Short getWs() {
        return ws;
    }

    public void setWs(Short ws) {
        this.ws = ws;
    }

    public String getUdate() {
        return udate;
    }

    public void setUdate(String udate) {
        this.udate = udate == null ? null : udate.trim();
    }
}