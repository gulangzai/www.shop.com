package com.jiuji.cn.model; 

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TClassappend generated by hbm2java
 */
@Entity
@Table(name = "t_classappend", catalog = "shop")
public class TClassappend implements java.io.Serializable {

	private int FId;
	private Integer FClsId;
	private String FDisplayName;
	private String FFieldName;
	private Integer FValueLength;
	private String FValueType;
	private String FInputType;
	private String FDefaultValue;
	private Byte FIsRequired;
	private Byte FIsShow;
	private Integer FOrders;

	public TClassappend() {
	}

	public TClassappend(int FId) {
		this.FId = FId;
	}

	public TClassappend(int FId, Integer FClsId, String FDisplayName, String FFieldName, Integer FValueLength,
			String FValueType, String FInputType, String FDefaultValue, Byte FIsRequired, Byte FIsShow,
			Integer FOrders) {
		this.FId = FId;
		this.FClsId = FClsId;
		this.FDisplayName = FDisplayName;
		this.FFieldName = FFieldName;
		this.FValueLength = FValueLength;
		this.FValueType = FValueType;
		this.FInputType = FInputType;
		this.FDefaultValue = FDefaultValue;
		this.FIsRequired = FIsRequired;
		this.FIsShow = FIsShow;
		this.FOrders = FOrders;
	}

	@Id

	@Column(name = "F_Id", unique = true, nullable = false)
	public int getFId() {
		return this.FId;
	}

	public void setFId(int FId) {
		this.FId = FId;
	}

	@Column(name = "F_ClsId")
	public Integer getFClsId() {
		return this.FClsId;
	}

	public void setFClsId(Integer FClsId) {
		this.FClsId = FClsId;
	}

	@Column(name = "F_DisplayName", length = 55)
	public String getFDisplayName() {
		return this.FDisplayName;
	}

	public void setFDisplayName(String FDisplayName) {
		this.FDisplayName = FDisplayName;
	}

	@Column(name = "F_FieldName", length = 55)
	public String getFFieldName() {
		return this.FFieldName;
	}

	public void setFFieldName(String FFieldName) {
		this.FFieldName = FFieldName;
	}

	@Column(name = "F_ValueLength")
	public Integer getFValueLength() {
		return this.FValueLength;
	}

	public void setFValueLength(Integer FValueLength) {
		this.FValueLength = FValueLength;
	}

	@Column(name = "F_ValueType", length = 55)
	public String getFValueType() {
		return this.FValueType;
	}

	public void setFValueType(String FValueType) {
		this.FValueType = FValueType;
	}

	@Column(name = "F_InputType", length = 55)
	public String getFInputType() {
		return this.FInputType;
	}

	public void setFInputType(String FInputType) {
		this.FInputType = FInputType;
	}

	@Column(name = "F_DefaultValue", length = 55)
	public String getFDefaultValue() {
		return this.FDefaultValue;
	}

	public void setFDefaultValue(String FDefaultValue) {
		this.FDefaultValue = FDefaultValue;
	}

	@Column(name = "F_IsRequired")
	public Byte getFIsRequired() {
		return this.FIsRequired;
	}

	public void setFIsRequired(Byte FIsRequired) {
		this.FIsRequired = FIsRequired;
	}

	@Column(name = "F_isShow")
	public Byte getFIsShow() {
		return this.FIsShow;
	}

	public void setFIsShow(Byte FIsShow) {
		this.FIsShow = FIsShow;
	}

	@Column(name = "F_Orders")
	public Integer getFOrders() {
		return this.FOrders;
	}

	public void setFOrders(Integer FOrders) {
		this.FOrders = FOrders;
	}

}
