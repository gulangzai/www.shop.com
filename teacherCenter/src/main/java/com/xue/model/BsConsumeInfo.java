package com.xue.model;

import java.util.Date;

public class BsConsumeInfo {
	private String id;
	//消耗资金
	private int consumeMoney;
	
	private String shopId;
	
	/*开课专业id*/
	private String majorId;
	
	private String majorName;
	
	/*开课时间*/
	private Date createTime;
	
	/*状态*/
	private int isState;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getConsumeMoney() {
		return consumeMoney;
	}

	public void setConsumeMoney(int consumeMoney) {
		this.consumeMoney = consumeMoney;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getMajorId() {
		return majorId;
	}

	public void setMajorId(String majorId) {
		this.majorId = majorId;
	}

	public String getMajorName() {
		return majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getIsState() {
		return isState;
	}

	public void setIsState(int isState) {
		this.isState = isState;
	} 
}
