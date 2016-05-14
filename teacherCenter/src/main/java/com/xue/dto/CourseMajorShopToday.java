package com.xue.dto;

import com.xue.model.CourseMajorShop;

public class CourseMajorShopToday extends CourseMajorShop{

	private Integer state = 0;
	
	private String shopId;

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getShopId() {
		// TODO Auto-generated method stub
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	
    
}
