package com.ccthanking.business.jkjc.vo;
import com.ccthanking.framework.base.BaseVO;
import java.util.Date;

public class JcCxDataVO extends BaseVO{

	public JcCxDataVO(){
		this.addField("CX_DATA_UID",OP_STRING|this.TP_PK);//
		this.addField("PRJ_POINTS_UID",OP_STRING);//
		this.addField("REPORT_CODE",OP_STRING);//报告编号
		this.addField("REPORT_DATE",OP_DATE);//报告日期
		this.addField("DEEP",OP_STRING);//深度（米）
		this.addField("HORIZONTAL_VALUE",OP_STRING);//水平监测值
		this.addField("HORIZONTAL_VALUE_DIFF",OP_STRING);//本次水平变化值
		this.addField("CREATED_DATE",OP_DATE);//
		this.setFieldDateFormat("REPORT_DATE","yyyy-MM-dd HH:mm:ss");
		this.setFieldDateFormat("CREATED_DATE","yyyy-MM-dd HH:mm:ss");
		this.setVOTableName("JC_CX_DATA");
	}

	public void setCx_data_uid(String cx_data_uid){
		this.setInternal("CX_DATA_UID",cx_data_uid);
	}
	public String getCx_data_uid(){
		return (String)this.getInternal("CX_DATA_UID");
	}
	public void setPrj_points_uid(String prj_points_uid){
		this.setInternal("PRJ_POINTS_UID",prj_points_uid);
	}
	public String getPrj_points_uid(){
		return (String)this.getInternal("PRJ_POINTS_UID");
	}
	public void setReport_code(String report_code){
		this.setInternal("REPORT_CODE",report_code);
	}
	public String getReport_code(){
		return (String)this.getInternal("REPORT_CODE");
	}
	public void setReport_date(Date report_date){
		this.setInternal("REPORT_DATE",report_date);
	}
	public Date getReport_date(){
		return (Date)this.getInternal("REPORT_DATE");
	}
	public void setDeep(String deep){
		this.setInternal("DEEP",deep);
	}
	public String getDeep(){
		return (String)this.getInternal("DEEP");
	}
	public void setHorizontal_value(String horizontal_value){
		this.setInternal("HORIZONTAL_VALUE",horizontal_value);
	}
	public String getHorizontal_value(){
		return (String)this.getInternal("HORIZONTAL_VALUE");
	}
	public void setHorizontal_value_diff(String horizontal_value_diff){
		this.setInternal("HORIZONTAL_VALUE_DIFF",horizontal_value_diff);
	}
	public String getHorizontal_value_diff(){
		return (String)this.getInternal("HORIZONTAL_VALUE_DIFF");
	}
	public void setCreated_date(Date created_date){
		this.setInternal("CREATED_DATE",created_date);
	}
	public Date getCreated_date(){
		return (Date)this.getInternal("CREATED_DATE");
	}
}