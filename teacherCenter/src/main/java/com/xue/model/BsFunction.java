/*
 * BsFunction.java
 * Copyright(C) 2015-2018 学尔森
 * All rights reserved.
 * ------------------------------------------------
 * 2015-12-28 Created
 */
package com.xue.model;

/**
 * 
 * 
 * @author Mr.Ruan
 * @version 1.0 2015-12-28
 */
public class BsFunction {
    /**
    /*功能（权限）id
    */
    private Integer functionId;

    /**
    /*功能（菜单）名称
    */
    private String functionName;

    /**
    /*功能（菜单）描述
    */
    private String functionDesc;

    /**
    /*调用方法
    */
    private String invokingMethod;

    /**
    /*创建时间
    */
    private String createTime;

    /**
    /*创建人id
    */
    private String createStudentId;

    /**
    /*是否删除(0未删除,1已删除)
    */
    private Integer isDel;

    /**
    /*删除人id
    */
    private String delStudentId;

    public Integer getFunctionId() {
        return functionId;
    }

    public void setFunctionId(Integer functionId) {
        this.functionId = functionId;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName == null ? null : functionName.trim();
    }

    public String getFunctionDesc() {
        return functionDesc;
    }

    public void setFunctionDesc(String functionDesc) {
        this.functionDesc = functionDesc == null ? null : functionDesc.trim();
    }

    public String getInvokingMethod() {
        return invokingMethod;
    }

    public void setInvokingMethod(String invokingMethod) {
        this.invokingMethod = invokingMethod == null ? null : invokingMethod.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getCreateStudentId() {
        return createStudentId;
    }

    public void setCreateStudentId(String createStudentId) {
        this.createStudentId = createStudentId == null ? null : createStudentId.trim();
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public String getDelStudentId() {
        return delStudentId;
    }

    public void setDelStudentId(String delStudentId) {
        this.delStudentId = delStudentId == null ? null : delStudentId.trim();
    }
}