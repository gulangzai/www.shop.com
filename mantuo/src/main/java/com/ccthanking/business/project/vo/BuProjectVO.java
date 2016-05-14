package com.ccthanking.business.project.vo;
import com.ccthanking.framework.base.BaseVO;
import java.util.Date;

public class BuProjectVO extends BaseVO{

	public BuProjectVO(){
		this.addField("PROJECT_UID",OP_STRING|this.TP_PK);//
		this.addField("PROJECT_NAME",OP_STRING);//项目名称
		this.addField("PROJECT_DESC",OP_STRING);//项目简介
		this.addField("PROJECT_ADDRESS",OP_STRING);//项目地址
		this.addField("BEGIN_DATE",OP_DATE);//开工日期
		this.addField("END_DATE",OP_DATE);//完工日期
		this.addField("ZHANDI_MIANJI",OP_STRING);//项目占地面积(平方米)
		this.addField("JIANZHU_MIANJI",OP_STRING);//建筑面积（平方米）
		this.addField("ZONG_TOUZI",OP_STRING);//总投资(元)
		this.addField("PROVINCE",OP_STRING);//省
		this.addField("CITY",OP_STRING);//市
		this.addField("DISTRICT",OP_STRING);//区
		this.addField("CREATED_USER",OP_STRING);//项目创建人
		this.addField("JK_DEEP",OP_STRING);//基坑深度（米）
		this.addField("JK_LEVEL",OP_STRING);//基坑等级
		this.addField("JKZH_STRUC_TYPE",OP_STRING);//基坑支护结构类型
		this.addField("CREATED_DATE",OP_DATE);//项目创建时间
		this.addField("UPDATED_DATE",OP_DATE);//最后更新时间
		this.setFieldDateFormat("BEGIN_DATE","yyyy-MM-dd");
		this.setFieldDateFormat("END_DATE","yyyy-MM-dd");
		this.setFieldDateFormat("CREATED_DATE","yyyy-MM-dd HH:mm:ss");
		this.setFieldDateFormat("UPDATED_DATE","yyyy-MM-dd HH:mm:ss");
		this.setVOTableName("BU_PROJECT");
	}

	public void setProject_uid(String project_uid){
		this.setInternal("PROJECT_UID",project_uid);
	}
	public String getProject_uid(){
		return (String)this.getInternal("PROJECT_UID");
	}
	public void setProject_name(String project_name){
		this.setInternal("PROJECT_NAME",project_name);
	}
	public String getProject_name(){
		return (String)this.getInternal("PROJECT_NAME");
	}
	public void setProject_desc(String project_desc){
		this.setInternal("PROJECT_DESC",project_desc);
	}
	public String getProject_desc(){
		return (String)this.getInternal("PROJECT_DESC");
	}
	public void setProject_address(String project_address){
		this.setInternal("PROJECT_ADDRESS",project_address);
	}
	public String getProject_address(){
		return (String)this.getInternal("PROJECT_ADDRESS");
	}
	public void setBegin_date(Date begin_date){
		this.setInternal("BEGIN_DATE",begin_date);
	}
	public Date getBegin_date(){
		return (Date)this.getInternal("BEGIN_DATE");
	}
	public void setEnd_date(Date end_date){
		this.setInternal("END_DATE",end_date);
	}
	public Date getEnd_date(){
		return (Date)this.getInternal("END_DATE");
	}
	public void setZhandi_mianji(String zhandi_mianji){
		this.setInternal("ZHANDI_MIANJI",zhandi_mianji);
	}
	public String getZhandi_mianji(){
		return (String)this.getInternal("ZHANDI_MIANJI");
	}
	public void setJianzhu_mianji(String jianzhu_mianji){
		this.setInternal("JIANZHU_MIANJI",jianzhu_mianji);
	}
	public String getJianzhu_mianji(){
		return (String)this.getInternal("JIANZHU_MIANJI");
	}
	public void setZong_touzi(String zong_touzi){
		this.setInternal("ZONG_TOUZI",zong_touzi);
	}
	public String getZong_touzi(){
		return (String)this.getInternal("ZONG_TOUZI");
	}
	public void setProvince(String province){
		this.setInternal("PROVINCE",province);
	}
	public String getProvince(){
		return (String)this.getInternal("PROVINCE");
	}
	public void setCity(String city){
		this.setInternal("CITY",city);
	}
	public String getCity(){
		return (String)this.getInternal("CITY");
	}
	public void setDistrict(String district){
		this.setInternal("DISTRICT",district);
	}
	public String getDistrict(){
		return (String)this.getInternal("DISTRICT");
	}
	public void setCreated_user(String created_user){
		this.setInternal("CREATED_USER",created_user);
	}
	public String getCreated_user(){
		return (String)this.getInternal("CREATED_USER");
	}
	public void setJk_deep(String jk_deep){
		this.setInternal("JK_DEEP",jk_deep);
	}
	public String getJk_deep(){
		return (String)this.getInternal("JK_DEEP");
	}
	public void setJk_level(String jk_level){
		this.setInternal("JK_LEVEL",jk_level);
	}
	public String getJk_level(){
		return (String)this.getInternal("JK_LEVEL");
	}
	public void setJkzh_struc_type(String jkzh_struc_type){
		this.setInternal("JKZH_STRUC_TYPE",jkzh_struc_type);
	}
	public String getJkzh_struc_type(){
		return (String)this.getInternal("JKZH_STRUC_TYPE");
	}
	public void setCreated_date(Date created_date){
		this.setInternal("CREATED_DATE",created_date);
	}
	public Date getCreated_date(){
		return (Date)this.getInternal("CREATED_DATE");
	}
	public void setUpdated_date(Date updated_date){
		this.setInternal("UPDATED_DATE",updated_date);
	}
	public Date getUpdated_date(){
		return (Date)this.getInternal("UPDATED_DATE");
	}
}