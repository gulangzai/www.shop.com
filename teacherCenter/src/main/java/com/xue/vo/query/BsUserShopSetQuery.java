package com.xue.vo.query;

import com.bokecc.util.DateUtil;

import javacommon.base.BaseQuery;
import javacommon.util.DbUtil;

/*
 * 老师开通学员商品表
 * */
public class BsUserShopSetQuery extends BaseQuery{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1650297173598766543L;
	private String id;
	private String userId;
	private String shopId;
	private String stuId;  
	
	/*开通时间*/
	private String createTime;
	private int statu;
	/**
    /*专业ID
    */
    private String majorId;

    /**
    /*专业名称
    */
    private String majorName;
    
    private String classesId;

    private String classesName;

    private Float shopPrice;
    
    
	public String getClassesId() {
		return classesId;
	}

	public void setClassesId(String classesId) {
		this.classesId = classesId;
	}

	public String getClassesName() {
		return classesName;
	}

	public void setClassesName(String classesName) {
		this.classesName = classesName;
	}

	public Float getShopPrice() {
		return shopPrice;
	}

	public void setShopPrice(Float shopPrice) {
		this.shopPrice = shopPrice;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public String getStuId() {
		return stuId;
	}
	public void setStuId(String stuId) {
		this.stuId = stuId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public int getStatu() {
		return statu;
	}

	public void setStatu(int statu) {
		this.statu = statu;
	} 
	
	
}
