package com.xue.dto;

import java.util.Date;
import java.util.List;

import com.xue.model.PaperInfo;
import com.xue.model.Shop;
import com.xue.model.ShopLiveInfo;
import com.xue.model.StudentInfo;

public class StudentAndShop{
	
	private String studentId;
	private String studentName;
	private String mobile; 
	private String createTime;
	
	/*
	 * ��ͨ����Ŀ
	 * */
	public String shopName;
	
	/*
	 * ��ͨ��ʱ��
	 * */ 
	
	
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
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
}
