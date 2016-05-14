/*
 * NoteBook.java
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
public class NoteBook {
    /**
    /*笔记本id
    */
    private Integer notebookid;

    /**
    /*所属人的id
    */
    private String studentid;

    /**
    /*所记录的知识点id
    */
    private Integer pointid;

    /**
    /*记录内容
    */
    private String notebookcontent;

    /**
    /*是否公开
    */
    private Integer isopen;

    /**
    /*记录时间
    */
    private String createdate;

    /**
    /*是否删除
    */
    private Integer isdel;

    public Integer getNotebookid() {
        return notebookid;
    }

    public void setNotebookid(Integer notebookid) {
        this.notebookid = notebookid;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid == null ? null : studentid.trim();
    }

    public Integer getPointid() {
        return pointid;
    }

    public void setPointid(Integer pointid) {
        this.pointid = pointid;
    }

    public String getNotebookcontent() {
        return notebookcontent;
    }

    public void setNotebookcontent(String notebookcontent) {
        this.notebookcontent = notebookcontent == null ? null : notebookcontent.trim();
    }

    public Integer getIsopen() {
        return isopen;
    }

    public void setIsopen(Integer isopen) {
        this.isopen = isopen;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate == null ? null : createdate.trim();
    }

    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }
}