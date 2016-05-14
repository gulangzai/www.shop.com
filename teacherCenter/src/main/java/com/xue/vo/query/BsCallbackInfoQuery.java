package com.xue.vo.query;

import com.xue.model.BsCallbackInfo;

public class BsCallbackInfoQuery extends BsCallbackInfo{

	public int currentPage=1;
	
	public int startNum;
	
	private int pageNumber = 0;
	
	private int pageSize = 0;
	
	public BsCallbackInfoQuery() {
		// TODO Auto-generated constructor stub
	}
	 
	
	public BsCallbackInfoQuery(String teacherId, String studentId) {
		// TODO Auto-generated constructor stub
		this.setTeacherId(teacherId);
		this.setStudentId(studentId);
	}

	

	public int getPageSize() {
		return pageSize;
	}
  
	public void setPageSize(int pageSize) {
		this.startNum=(pageNumber-1)*5;
		this.pageSize = pageSize;
	} 
	
	public int getPageNumber() {
		return pageNumber;
	} 
	public void setPageNumber(int pageNumber) {
		// TODO Auto-generated method stub
		this.pageNumber = pageNumber;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getStartNum() {
		return startNum;
	}

	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}
	
	
  
}
