package com.ccthanking.business.jkjc.vo;
import com.ccthanking.framework.base.BaseVO;
import java.util.Date;

public class JcJcDataVO extends BaseVO{

	public JcJcDataVO(){
		this.addField("JC_DATA_UID",OP_STRING|this.TP_PK);//
		this.addField("PRJ_POINTS_UID",OP_STRING);//
		this.addField("REPORT_CODE",OP_STRING);//报告编号
		this.addField("REPORT_DATE",OP_DATE);//报告日期
		this.addField("VERTICAL_VALUE",OP_STRING);//垂直监测值，如沉降值（mm）、轴力（KN）、深度（m）
		this.addField("HORIZONTAL_VALUE",OP_STRING);//水平监测值
		this.addField("PUMP_STATUS",OP_STRING);//水泵运行状态
		this.addField("WATER_YIELD",OP_STRING);//出水量（立方米/秒）
		this.addField("ELECTRICITY",OP_STRING);//耗电（KW）
		this.addField("DESCRIPTION",OP_STRING);//文字说明
		this.addField("CREATED_DATE",OP_DATE);//
		this.setFieldDateFormat("REPORT_DATE","yyyy-MM-dd HH:mm:ss");
		this.setFieldDateFormat("CREATED_DATE","yyyy-MM-dd HH:mm:ss");
		this.setVOTableName("JC_JC_DATA");
	}

	public void setJc_data_uid(String jc_data_uid){
		this.setInternal("JC_DATA_UID",jc_data_uid);
	}
	public String getJc_data_uid(){
		return (String)this.getInternal("JC_DATA_UID");
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
	public void setVertical_value(String vertical_value){
		this.setInternal("VERTICAL_VALUE",vertical_value);
	}
	public String getVertical_value(){
		return (String)this.getInternal("VERTICAL_VALUE");
	}
	public void setHorizontal_value(String horizontal_value){
		this.setInternal("HORIZONTAL_VALUE",horizontal_value);
	}
	public String getHorizontal_value(){
		return (String)this.getInternal("HORIZONTAL_VALUE");
	}
	public void setPump_status(String pump_status){
		this.setInternal("PUMP_STATUS",pump_status);
	}
	public String getPump_status(){
		return (String)this.getInternal("PUMP_STATUS");
	}
	public void setWater_yield(String water_yield){
		this.setInternal("WATER_YIELD",water_yield);
	}
	public String getWater_yield(){
		return (String)this.getInternal("WATER_YIELD");
	}
	public void setElectricity(String electricity){
		this.setInternal("ELECTRICITY",electricity);
	}
	public String getElectricity(){
		return (String)this.getInternal("ELECTRICITY");
	}
	public void setDescription(String description){
		this.setInternal("DESCRIPTION",description);
	}
	public String getDescription(){
		return (String)this.getInternal("DESCRIPTION");
	}
	public void setCreated_date(Date created_date){
		this.setInternal("CREATED_DATE",created_date);
	}
	public Date getCreated_date(){
		return (Date)this.getInternal("CREATED_DATE");
	}
}