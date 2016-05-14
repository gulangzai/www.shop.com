package com.fh.entity.information;

public class TClass {
	int fClsId;
	String fClsName;
	int fStatus;
	int fOrder;
	int fPClsId;
	public TClass(int i, String fClsName) {
		// TODO Auto-generated constructor stub
		this.fClsId = i;
		this.fClsName = fClsName;
	}
	public int getfClsId() {
		return fClsId;
	}
	public void setfClsId(int fClsId) {
		this.fClsId = fClsId;
	}
	public String getfClsName() {
		return fClsName;
	}
	public void setfClsName(String fClsName) {
		this.fClsName = fClsName;
	}
	public int getfStatus() {
		return fStatus;
	}
	public void setfStatus(int fStatus) {
		this.fStatus = fStatus;
	}
	public int getfOrder() {
		return fOrder;
	}
	public void setfOrder(int fOrder) {
		this.fOrder = fOrder;
	}
	public int getfPClsId() {
		return fPClsId;
	}
	public void setfPClsId(int fPClsId) {
		this.fPClsId = fPClsId;
	}
	
	
}
