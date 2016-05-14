package com.lanbao.cn.db.single;

import java.util.ArrayList;
import java.util.List;

public class Table {
	public Table(){}
	//表名
	private String name=null; 
	//表描述
	private String desc = null;
	
	private List fields = new ArrayList();

	public List getFields() {
		return fields;
	}

	public void setFields(List fields) {
		this.fields = fields;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
