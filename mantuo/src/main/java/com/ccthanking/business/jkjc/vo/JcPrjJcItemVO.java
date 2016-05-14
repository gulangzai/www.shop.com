package com.ccthanking.business.jkjc.vo;
import com.ccthanking.framework.base.BaseVO;
import java.util.Date;

public class JcPrjJcItemVO extends BaseVO{

	public JcPrjJcItemVO(){
		this.addField("JC_PRJ_ITEM_UID",OP_STRING|this.TP_PK);//
		this.addField("PROJECT_UID",OP_STRING);//
		this.addField("JC_OBJECT_UID",OP_STRING);//
		this.addField("JC_TYPE_UID",OP_STRING);//
		this.addField("JC_NAME",OP_STRING);//监测项目名称
		this.addField("SHORT_NAME",OP_STRING);//监测项目短名称
		this.addField("ITEM_TYPE",OP_STRING);//监测项目类型：基坑、降水、视频、门禁等
		this.addField("UNIT",OP_STRING);//监测数据的单位，如mm、Kn等
		this.addField("PRE_CODE",OP_STRING);//监测项目前缀
		this.addField("ICON_FILE",OP_STRING);//图标
		this.addField("SINGLE_WARN",OP_STRING);//单次预警值
		this.addField("TOTAL_WARN1",OP_STRING);//累计值（正方向变化值）
		this.addField("TOTAL_WARN2",OP_STRING);//累计值（负方向变化值）
		this.addField("UPPER_LINE",OP_STRING);//上包络线
		this.addField("LOWER_LINE",OP_STRING);//下包络线
		this.addField("REPORT_UID",OP_STRING);//
		this.setVOTableName("JC_PRJ_JC_ITEM");
	}

	public void setJc_prj_item_uid(String jc_prj_item_uid){
		this.setInternal("JC_PRJ_ITEM_UID",jc_prj_item_uid);
	}
	public String getJc_prj_item_uid(){
		return (String)this.getInternal("JC_PRJ_ITEM_UID");
	}
	public void setProject_uid(String project_uid){
		this.setInternal("PROJECT_UID",project_uid);
	}
	public String getProject_uid(){
		return (String)this.getInternal("PROJECT_UID");
	}
	public void setJc_object_uid(String jc_object_uid){
		this.setInternal("JC_OBJECT_UID",jc_object_uid);
	}
	public String getJc_object_uid(){
		return (String)this.getInternal("JC_OBJECT_UID");
	}
	public void setJc_type_uid(String jc_type_uid){
		this.setInternal("JC_TYPE_UID",jc_type_uid);
	}
	public String getJc_type_uid(){
		return (String)this.getInternal("JC_TYPE_UID");
	}
	public void setJc_name(String jc_name){
		this.setInternal("JC_NAME",jc_name);
	}
	public String getJc_name(){
		return (String)this.getInternal("JC_NAME");
	}
	public void setShort_name(String short_name){
		this.setInternal("SHORT_NAME",short_name);
	}
	public String getShort_name(){
		return (String)this.getInternal("SHORT_NAME");
	}
	public void setItem_type(String item_type){
		this.setInternal("ITEM_TYPE",item_type);
	}
	public String getItem_type(){
		return (String)this.getInternal("ITEM_TYPE");
	}
	public void setUnit(String unit){
		this.setInternal("UNIT",unit);
	}
	public String getUnit(){
		return (String)this.getInternal("UNIT");
	}
	public void setPre_code(String pre_code){
		this.setInternal("PRE_CODE",pre_code);
	}
	public String getPre_code(){
		return (String)this.getInternal("PRE_CODE");
	}
	public void setIcon_file(String icon_file){
		this.setInternal("ICON_FILE",icon_file);
	}
	public String getIcon_file(){
		return (String)this.getInternal("ICON_FILE");
	}
	public void setSingle_warn(String single_warn){
		this.setInternal("SINGLE_WARN",single_warn);
	}
	public String getSingle_warn(){
		return (String)this.getInternal("SINGLE_WARN");
	}
	public void setTotal_warn1(String total_warn1){
		this.setInternal("TOTAL_WARN1",total_warn1);
	}
	public String getTotal_warn1(){
		return (String)this.getInternal("TOTAL_WARN1");
	}
	public void setTotal_warn2(String total_warn2){
		this.setInternal("TOTAL_WARN2",total_warn2);
	}
	public String getTotal_warn2(){
		return (String)this.getInternal("TOTAL_WARN2");
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
	public void setReport_uid(String report_uid){
		this.setInternal("REPORT_UID",report_uid);
	}
	public String getReport_uid(){
		return (String)this.getInternal("REPORT_UID");
	}
}