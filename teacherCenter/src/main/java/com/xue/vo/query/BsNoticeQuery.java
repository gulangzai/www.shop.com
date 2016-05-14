package com.xue.vo.query;

import javacommon.base.BaseQuery;

import com.xue.dto.PageBean;

public class BsNoticeQuery extends BaseQuery{
	
	public int currentPage=1;
	
	public String majorId;
	
	public String keyword;
	
	public String headColor;
	
	public String intention;
	
	private String mobile;
	
	private String studentId;
	
	private String studentName;
	
	public int startNum;


	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
 
	public String getMajorId() {
		return majorId;
	}
	public void setMajorId(String majorId) {
		this.majorId = majorId;
	}
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getHeadColor() {
		return headColor;
	}

	public void setHeadColor(String headColor) {
		this.headColor = headColor;
	}
	public int getStartNum() {
		return startNum;
	}
	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}

	public String getIntention() {
		// TODO Auto-generated method stub
		return intention;
	}

	public void setIntention(String intention) {
		this.intention = intention;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	} 
	
	
}
