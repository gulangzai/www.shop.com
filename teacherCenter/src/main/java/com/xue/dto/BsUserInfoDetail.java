package com.xue.dto;

import java.util.Date;

import com.xue.model.BsUserInfo;

public class BsUserInfoDetail extends BsUserInfo{
	private String userId;

	    /**
	    /*真实姓名
	    */
	    private String realName;

	    /**
	    /*移动电话
	    */
	    private String mobile;

	    /**
	    /*座机
	    */
	    private String telephone;

	    /**
	    /*年龄
	    */
	    private Integer age;

	    /**
	    /*性别（1，男，2，女。）
	    */
	    private Integer gender;

	    /**
	    /*注册创建时间
	    */
	    private Date createTime;
	    
	    /* 账户余额*/
	    private int balance;
	    
	    /*机构名称*/
	    private String jigouName = null;
	    
	    /**
	    /*预留字段A
	    */
	    private String obligateFieldA;

	    private String obligateFieldB;

	    private String obligateFieldC;

	    private String obligateFieldD;

	    private String obligateFieldE;
	    

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public String getRealName() {
			return realName;
		}

		public void setRealName(String realName) {
			this.realName = realName;
		}

		public String getMobile() {
			return mobile;
		}

		public void setMobile(String mobile) {
			this.mobile = mobile;
		}

		public String getTelephone() {
			return telephone;
		}

		public void setTelephone(String telephone) {
			this.telephone = telephone;
		}

		public Integer getAge() {
			return age;
		}

		public void setAge(Integer age) {
			this.age = age;
		}

		public Integer getGender() {
			return gender;
		}

		public void setGender(Integer gender) {
			this.gender = gender;
		}

		public Date getCreateTime() {
			return createTime;
		}

		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}

		public int getBalance() {
			return balance;
		}

		public void setBalance(int balance) {
			this.balance = balance;
		}

		public String getJigouName() {
			return jigouName;
		}

		public void setJigouName(String jigouName) {
			this.jigouName = jigouName;
		}

		public String getObligateFieldA() {
			return obligateFieldA;
		}

		public void setObligateFieldA(String obligateFieldA) {
			this.obligateFieldA = obligateFieldA;
		}

		public String getObligateFieldB() {
			return obligateFieldB;
		}

		public void setObligateFieldB(String obligateFieldB) {
			this.obligateFieldB = obligateFieldB;
		}

		public String getObligateFieldC() {
			return obligateFieldC;
		}

		public void setObligateFieldC(String obligateFieldC) {
			this.obligateFieldC = obligateFieldC;
		}

		public String getObligateFieldD() {
			return obligateFieldD;
		}

		public void setObligateFieldD(String obligateFieldD) {
			this.obligateFieldD = obligateFieldD;
		}

		public String getObligateFieldE() {
			return obligateFieldE;
		}

		public void setObligateFieldE(String obligateFieldE) {
			this.obligateFieldE = obligateFieldE;
		}
	    
	    
}
