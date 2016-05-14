package com.xue.model;

import java.util.Date;

public class MajorExamTime {
    private String examTimeId;

    private String subjectId;

    private String majorId;

    private Date examTime;

    private Date createTime;

    private Integer isDel;

    public String getExamTimeId() {
        return examTimeId;
    }

    public void setExamTimeId(String examTimeId) {
        this.examTimeId = examTimeId == null ? null : examTimeId.trim();
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId == null ? null : subjectId.trim();
    }

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId == null ? null : majorId.trim();
    }

    public Date getExamTime() {
        return examTime;
    }

    public void setExamTime(Date examTime) {
        this.examTime = examTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
}