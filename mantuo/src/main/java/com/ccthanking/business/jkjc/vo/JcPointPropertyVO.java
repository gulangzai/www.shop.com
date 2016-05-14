package com.ccthanking.business.jkjc.vo;
import com.ccthanking.framework.base.BaseVO;
import java.util.Date;

public class JcPointPropertyVO extends BaseVO{

	public JcPointPropertyVO(){
		this.addField("POINT_PROPERTY_UID",OP_STRING|this.TP_PK);//
		this.addField("PRJ_POINTS_UID",OP_STRING);//
		this.addField("SINGLE_WARN",OP_STRING);//单次值
		this.addField("TOTAL_WARN",OP_STRING);//累计值
		this.addField("UPPER_LINE",OP_STRING);//上包络线
		this.addField("LOWER_LINE",OP_STRING);//下包络线
		this.addField("CREATED_DATE",OP_DATE);//
		this.setFieldDateFormat("CREATED_DATE","yyyy-MM-dd HH:mm:ss");
		this.setVOTableName("JC_POINT_PROPERTY");
	}

	public void setPoint_property_uid(String point_property_uid){
		this.setInternal("POINT_PROPERTY_UID",point_property_uid);
	}
	public String getPoint_property_uid(){
		return (String)this.getInternal("POINT_PROPERTY_UID");
	}
	public void setPrj_points_uid(String prj_points_uid){
		this.setInternal("PRJ_POINTS_UID",prj_points_uid);
	}
	public String getPrj_points_uid(){
		return (String)this.getInternal("PRJ_POINTS_UID");
	}
	public void setSingle_warn(String single_warn){
		this.setInternal("SINGLE_WARN",single_warn);
	}
	public String getSingle_warn(){
		return (String)this.getInternal("SINGLE_WARN");
	}
	public void setTotal_warn(String total_warn){
		this.setInternal("TOTAL_WARN",total_warn);
	}
	public String getTotal_warn(){
		return (String)this.getInternal("TOTAL_WARN");
	}
	public void setUpper_line(String upper_line){
		this.setInternal("UPPER_LINE",upper_line);
	}
	public String getUpper_line(){
		return (String)this.getInternal("UPPER_LINE");
	}
	public void setLower_line(String lower_line){
		this.setInternal("LOWER_LINE",lower_line);
	}
	public String getLower_line(){
		return (String)this.getInternal("LOWER_LINE");
	}
	public void setCreated_date(Date created_date){
		this.setInternal("CREATED_DATE",created_date);
	}
	public Date getCreated_date(){
		return (Date)this.getInternal("CREATED_DATE");
	}
}