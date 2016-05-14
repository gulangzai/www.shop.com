/*
 * Shop.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2015-10-14 Created
 */
package com.xue.vo.query;

import java.util.Date;

/**
 * 存储商品(课程)主要数据
 * 
 * @author Mr.Ruan
 * @version 1.0 2015-10-14
 */
public class ShopQuery {
    /**
    /*商品id
    */
    private String shopId;

    /**
    /*商品名称
    */
    private String shopName;

    /**
    /*商品描述
    */
    private String shopDesc;

    /**
    /*商品数量
    */
    private Integer shopNum;

    /**
    /*商品价格
    */
    private Float shopPrice;

    /**
    /*商品状态（0：下架 1：上架）
    */
    private Integer shopState;

    /**
    /*商品类型(0:电子书 1：实体书 2：课件 3：直播)
    */
    private String shopType;
    
    private String shopTime;

    /**
    /*商品图片
    */
    private String shopPic;

    /**
    /*有效期
    */
    private Date validity;

    /**
    /*商品折扣(默认为0，无折扣)
    */
    private Float discount;

    /**
    /*商品优先级；顺序
    */
    private Integer orderBy;

    /**
    /*支付方式状态：001:快钱 002：支付宝 003:易宝
    */
    private String payWay;

    /**
    /*支付来源：0:网银 1：学习中心 2：分校 3：网盟  4：淘宝
    */
    private String paySource;

    /**
    /*是否删除（0，未删除；1，已删除）
    */
    private Integer isDel;

    /**
    /*购买次数
    */
    private Integer payNumber;

    /**
    /*预计学习时长（输入N天或N小时）
    */
    private String studyTime;

    /**
    /*专业id
    */
    private String majorId;

    /**
    /*专业名称
    */
    private String majorName;

    /**
    /*科目id
    */
    private String subjectId;

    /**
    /*科目名称
    */
    private String subjectName;

    /**
    /*班id
    */
    private String classesId;

    /**
    /*班名称
    */
    private String classesName;

    /**
    /*班顺序。用于展示排序
    */
    private Integer classesOrder;

    private String description;
    
    private String studentId;
    
    
    
    private  int startNum = 0;
    public ShopQuery(){}
    
    public ShopQuery(int currentPageNum){
    	startNum = (currentPageNum-1)*5;
    }
    public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId == null ? null : shopId.trim();
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public String getShopDesc() {
        return shopDesc;
    }

    public void setShopDesc(String shopDesc) {
        this.shopDesc = shopDesc == null ? null : shopDesc.trim();
    }

    public Integer getShopNum() {
        return shopNum;
    }

    public void setShopNum(Integer shopNum) {
        this.shopNum = shopNum;
    }

    public Float getShopPrice() {
        return shopPrice;
    }

    public void setShopPrice(Float shopPrice) {
        this.shopPrice = shopPrice;
    }

    public Integer getShopState() {
        return shopState;
    }

    public void setShopState(Integer shopState) {
        this.shopState = shopState;
    }

    public String getShopType() {
        return shopType;
    }

    public void setShopType(String shopType) {
        this.shopType = shopType == null ? null : shopType.trim();
    }

    public String getShopPic() {
        return shopPic;
    }

    public void setShopPic(String shopPic) {
        this.shopPic = shopPic == null ? null : shopPic.trim();
    }

    public Date getValidity() {
        return validity;
    }

    public void setValidity(Date validity) {
        this.validity = validity;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Integer getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Integer orderBy) {
        this.orderBy = orderBy;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay == null ? null : payWay.trim();
    }

    public String getPaySource() {
        return paySource;
    }

    public void setPaySource(String paySource) {
        this.paySource = paySource == null ? null : paySource.trim();
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Integer getPayNumber() {
        return payNumber;
    }

    public void setPayNumber(Integer payNumber) {
        this.payNumber = payNumber;
    }

    public String getStudyTime() {
        return studyTime;
    }

    public void setStudyTime(String studyTime) {
        this.studyTime = studyTime == null ? null : studyTime.trim();
    }

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId == null ? null : majorId.trim();
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName == null ? null : majorName.trim();
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId == null ? null : subjectId.trim();
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName == null ? null : subjectName.trim();
    }

    public String getClassesId() {
        return classesId;
    }

    public void setClassesId(String classesId) {
        this.classesId = classesId == null ? null : classesId.trim();
    }

    public String getClassesName() {
        return classesName;
    }

    public void setClassesName(String classesName) {
        this.classesName = classesName == null ? null : classesName.trim();
    }

    public Integer getClassesOrder() {
        return classesOrder;
    }

    public void setClassesOrder(Integer classesOrder) {
        this.classesOrder = classesOrder;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

	public void setShopTime(String shopTime) {
		// TODO Auto-generated method stub
		this.shopTime = shopTime; 
	}

	public String getShopTime() {
		return shopTime;
	}
	public int getStartNum() {
		return startNum;
	}
	public void setStartNum(int startNum) {
		this.startNum = startNum;
	} 
}