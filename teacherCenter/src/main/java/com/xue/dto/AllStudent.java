package com.xue.dto;

import com.xue.model.StudentInfo;
import com.xue.model.StudentInfoDetail;

public class AllStudent extends StudentInfoDetail {
	
	//����¼ʱ��
	private String lastTime;
	
	//��һ��ѧϰʱ��
	private String firstDuration;
	
	//������ȷ��
	private String rightRate;
	
	//ѧԱ����
	private String studentAttr;
	  
	private String coursePropertyLabel = null;
	
	private String studentSourceLabel = null;
	
	private String intentionLabel = null;
	
	private String studentName;
	
	private String mobile;
	
	private String lastLoginTime;
	

	public String getLastTime() {
		return lastTime;
	}

	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}
 
	public String getFirstDuration() {
		return firstDuration;
	}

	public void setFirstDuration(String firstDuration) {
		this.firstDuration = firstDuration;
	}

	public String getRightRate() {
		return rightRate;
	}

	public void setRightRate(String rightRate) {
		this.rightRate = rightRate;
	}

	public String getStudentAttr() {
		return studentAttr;
	}

	public void setStudentAttr(String studentAttr) {
		this.studentAttr = studentAttr;
	}

	 

	public String getCoursePropertyLabel() {
		return coursePropertyLabel;
	}

	public void setCoursePropertyLabel(String coursePropertyLabel) {
		this.coursePropertyLabel = coursePropertyLabel;
	}

	public String getStudentSourceLabel() {
		return studentSourceLabel;
	}

	public void setStudentSourceLabel(String studentSourceLabel) {
		this.studentSourceLabel = studentSourceLabel;
	}

	public String getIntentionLabel() {
		return intentionLabel;
	}

	public void setIntentionLabel(String intentionLabel) {
		this.intentionLabel = intentionLabel;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	} 
  
}
