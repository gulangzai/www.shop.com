package com.xue.vo.query;

import java.util.Date;

import javacommon.base.BaseQuery;

import org.apache.ibatis.type.IntegerTypeHandler;

public class StudentExamInfoQuery extends BaseQuery{
    /**
	 * 
	 */
	private static final long serialVersionUID = -4770983334826893026L;

	private String examId;

    private String examSubject;

    private Date examBeginTime;

    private Date examEndTime;

    private String examTotalTime;

    private String examResult;

    private Integer questNum;

    private Integer rightNum;

    private Float score;

    private Integer allScore;

    private Integer ranking;

    private Integer beat;

    private Integer status;

    private Integer paperId;
    
    private String studentId;
    
    private Integer sourceType;//数据来源类型（1.月月考，2全国模考赛，3在线模考，4……）
	
    
    public Integer getSourceType() {
		return sourceType;
	}

	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public Integer getPaperId() {
		return paperId;
	}

	public void setPaperId(Integer paperId) {
		this.paperId = paperId;
	}

	public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId == null ? null : examId.trim();
    }

    public String getExamSubject() {
        return examSubject;
    }

    public void setExamSubject(String examSubject) {
        this.examSubject = examSubject == null ? null : examSubject.trim();
    }

    public Date getExamBeginTime() {
        return examBeginTime;
    }

    public void setExamBeginTime(Date examBeginTime) {
        this.examBeginTime = examBeginTime;
    }

    public Date getExamEndTime() {
        return examEndTime;
    }

    public void setExamEndTime(Date examEndTime) {
        this.examEndTime = examEndTime;
    }

    public String getExamTotalTime() {
        return examTotalTime;
    }

    public void setExamTotalTime(String examTotalTime) {
        this.examTotalTime = examTotalTime == null ? null : examTotalTime.trim();
    }

    public String getExamResult() {
        return examResult;
    }

    public void setExamResult(String examResult) {
        this.examResult = examResult == null ? null : examResult.trim();
    }

    public Integer getQuestNum() {
        return questNum;
    }

    public void setQuestNum(Integer questNum) {
        this.questNum = questNum;
    }

    public Integer getRightNum() {
        return rightNum;
    }

    public void setRightNum(Integer rightNum) {
        this.rightNum = rightNum;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public Integer getAllScore() {
        return allScore;
    }

    public void setAllScore(Integer allScore) {
        this.allScore = allScore;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public Integer getBeat() {
        return beat;
    }

    public void setBeat(Integer beat) {
        this.beat = beat;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}