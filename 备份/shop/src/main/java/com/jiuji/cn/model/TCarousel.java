package com.jiuji.cn.model;
/**
 * 轮播
 * @author liyi
 *
 */
public class TCarousel {
	
	private String FId;
	
	private String FUrl; 
	
	private String FAddress;
	 
	TCarousel(){}

	public String getFId() {
		return FId;
	}

	public void setFId(String fId) {
		FId = fId;
	}

	public String getFUrl() {
		return FUrl;
	}

	public void setFUrl(String fUrl) {
		FUrl = fUrl;
	}

	public String getFAddress() {
		return FAddress;
	}

	public void setFAddress(String fAddress) {
		FAddress = fAddress;
	} 
}
