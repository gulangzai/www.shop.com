package com.xue.model;

/**
 * @Description:学员分数
 * @author:Administrator
 * @time:2015年10月20日 下午3:59:51
 */
public class StudentScore<T>{
	
	private String name ;
	
	private T data;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	
}

