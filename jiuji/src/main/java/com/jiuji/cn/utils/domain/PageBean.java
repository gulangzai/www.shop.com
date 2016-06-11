package com.jiuji.cn.utils.domain;

import java.util.List;

public class PageBean {
	
	public int currentPage=1;
	
	public int counts = 0;
	
	public int pageSize = 8;
	
	public List list = null;
	
	
	
	public int getCurrentPage() {
		return currentPage;
	}



	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}



	public int getCounts() {
		return counts;
	}



	public void setCounts(int counts) {
		this.counts = counts;
	}
 
	public int getPageSize() {
		return pageSize;
	}



	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}



	public List getList() {
		return list;
	}



	public void setList(List list) {
		this.list = list;
	}



	public PageBean(int currentPage,int counts,List list){
		this.currentPage = currentPage;
		this.counts = counts;
		this.list=list;
	}
 

	public PageBean(List list) {
		// TODO Auto-generated constructor stub
		this.list=list;
	}

   
 
	public PageBean(int pageNum, int pageSize, int intValue, List list) {
		// TODO Auto-generated constructor stub
		this(pageNum,intValue,list);
		this.pageSize = pageSize; 
	}
}
