/*
 * Tojoin.java
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
public class Tojoin {
    private String uid;

    private String uname;

    private String phone;

    private String mail;

    private String yjconstructor;

    private Integer erjconstructor;

    private Integer yjfirecontrol;

    private Integer erjfirecontrol;

    private String createdata;

    /**
    /*选择的省
    */
    private String sheng;

    /**
    /*选择的市
    */
    private String shi;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname == null ? null : uname.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail == null ? null : mail.trim();
    }

    public String getYjconstructor() {
        return yjconstructor;
    }

    public void setYjconstructor(String yjconstructor) {
        this.yjconstructor = yjconstructor == null ? null : yjconstructor.trim();
    }

    public Integer getErjconstructor() {
        return erjconstructor;
    }

    public void setErjconstructor(Integer erjconstructor) {
        this.erjconstructor = erjconstructor;
    }

    public Integer getYjfirecontrol() {
        return yjfirecontrol;
    }

    public void setYjfirecontrol(Integer yjfirecontrol) {
        this.yjfirecontrol = yjfirecontrol;
    }

    public Integer getErjfirecontrol() {
        return erjfirecontrol;
    }

    public void setErjfirecontrol(Integer erjfirecontrol) {
        this.erjfirecontrol = erjfirecontrol;
    }

    public String getCreatedata() {
        return createdata;
    }

    public void setCreatedata(String createdata) {
        this.createdata = createdata == null ? null : createdata.trim();
    }

    public String getSheng() {
        return sheng;
    }

    public void setSheng(String sheng) {
        this.sheng = sheng == null ? null : sheng.trim();
    }

    public String getShi() {
        return shi;
    }

    public void setShi(String shi) {
        this.shi = shi == null ? null : shi.trim();
    }
}