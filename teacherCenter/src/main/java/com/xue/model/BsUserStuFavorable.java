package com.xue.model;

import java.util.Date;

/*
 * 学员优惠记录信息表
 * */
public class BsUserStuFavorable {
	
	public int id;
	
	public String userId;
	
	public String studentId;
	
	public int favorableBalance;
	
	public Date createTime;
	
	public int statu;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
 
	public int getFavorableBalance() {
		return favorableBalance;
	}

	public void setFavorableBalance(int favorableBalance) {
		this.favorableBalance = favorableBalance;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getStatu() {
		return statu;
	}

	public void setStatu(int statu) {
		this.statu = statu;
	}
}
