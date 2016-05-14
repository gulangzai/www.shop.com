/*
 * ZLog4.java
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
public class ZLog4 {
    private Integer id;

    private String name;

    private String eventstr;

    private String ipstr;

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

    public String getEventstr() {
        return eventstr;
    }

    public void setEventstr(String eventstr) {
        this.eventstr = eventstr == null ? null : eventstr.trim();
    }

    public String getIpstr() {
        return ipstr;
    }

    public void setIpstr(String ipstr) {
        this.ipstr = ipstr == null ? null : ipstr.trim();
    }
}