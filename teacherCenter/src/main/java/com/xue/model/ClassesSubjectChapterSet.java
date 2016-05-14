/*
 * ClassesSubjectChapterSet.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2015-12-28 Created
 */
package com.xue.model;

/**
 * 班型科目章关联表
 * 
 * @author Mr.Ruan
 * @version 1.0 2015-12-28
 */
public class ClassesSubjectChapterSet {
    /**
    /*班型id
    */
    private String classesId;

    /**
    /*科目id
    */
    private String subjectId;

    /**
    /*章id
    */
    private String chapterId;

    public String getClassesId() {
        return classesId;
    }

    public void setClassesId(String classesId) {
        this.classesId = classesId == null ? null : classesId.trim();
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId == null ? null : subjectId.trim();
    }

    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId == null ? null : chapterId.trim();
    }
}