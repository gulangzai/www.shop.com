package com.ccthanking.business.jcck.vo;
import com.ccthanking.framework.base.BaseVO;
import java.util.Date;

public class JcToolsVO extends BaseVO{

	public JcToolsVO(){
		this.addField("TOOLS_UID",OP_STRING|this.TP_PK);//
		this.addField("PROJECT_UID",OP_STRING);//
		this.addField("TOOLS_TYPE",OP_STRING);//类型，基坑－JK、降水－JS等
		this.addField("TOOLS_NAME",OP_STRING);//物品名称
		this.addField("TOOLS_MODEL",OP_STRING);//物品型号
		this.addField("TOOLS_UNIT",OP_STRING);//物品单位
		this.addField("TOOLS_NUMS",OP_STRING);//物品总数量
		this.addField("IN_USE_NUMS",OP_STRING);//正在使用的物品数量
		this.addField("SLIGHT_DAMAGE_NUMS",OP_STRING);//轻微损坏的物品数量
		this.addField("DAMAGE_NUMS",OP_STRING);//损坏的物品数量
		this.addField("CREATE_DATE",OP_DATE);//
		this.addField("CREATE_USER",OP_STRING);//
		this.addField("UPDATE_DATE",OP_DATE);//
		this.addField("UPDATE_USER",OP_STRING);//
		this.setFieldDateFormat("CREATE_DATE","yyyy-MM-dd HH:mm:ss");
		this.setFieldDateFormat("UPDATE_DATE","yyyy-MM-dd HH:mm:ss");
		this.setVOTableName("JC_TOOLS");
	}

	public void setTools_uid(String tools_uid){
		this.setInternal("TOOLS_UID",tools_uid);
	}
	public String getTools_uid(){
		return (String)this.getInternal("TOOLS_UID");
	}
	public void setProject_uid(String project_uid){
		this.setInternal("PROJECT_UID",project_uid);
	}
	public String getProject_uid(){
		return (String)this.getInternal("PROJECT_UID");
	}
	public void setTools_type(String tools_type){
		this.setInternal("TOOLS_TYPE",tools_type);
	}
	public String getTools_type(){
		return (String)this.getInternal("TOOLS_TYPE");
	}
	public void setTools_name(String tools_name){
		this.setInternal("TOOLS_NAME",tools_name);
	}
	public String getTools_name(){
		return (String)this.getInternal("TOOLS_NAME");
	}
	public void setTools_model(String tools_model){
		this.setInternal("TOOLS_MODEL",tools_model);
	}
	public String getTools_model(){
		return (String)this.getInternal("TOOLS_MODEL");
	}
	public void setTools_unit(String tools_unit){
		this.setInternal("TOOLS_UNIT",tools_unit);
	}
	public String getTools_unit(){
		return (String)this.getInternal("TOOLS_UNIT");
	}
	public void setTools_nums(String tools_nums){
		this.setInternal("TOOLS_NUMS",tools_nums);
	}
	public String getTools_nums(){
		return (String)this.getInternal("TOOLS_NUMS");
	}
	public void setIn_use_nums(String in_use_nums){
		this.setInternal("IN_USE_NUMS",in_use_nums);
	}
	public String getIn_use_nums(){
		return (String)this.getInternal("IN_USE_NUMS");
	}
	public void setSlight_damage_nums(String slight_damage_nums){
		this.setInternal("SLIGHT_DAMAGE_NUMS",slight_damage_nums);
	}
	public String getSlight_damage_nums(){
		return (String)this.getInternal("SLIGHT_DAMAGE_NUMS");
	}
	public void setDamage_nums(String damage_nums){
		this.setInternal("DAMAGE_NUMS",damage_nums);
	}
	public String getDamage_nums(){
		return (String)this.getInternal("DAMAGE_NUMS");
	}
	public void setCreate_date(Date create_date){
		this.setInternal("CREATE_DATE",create_date);
	}
	public Date getCreate_date(){
		return (Date)this.getInternal("CREATE_DATE");
	}
	public void setCreate_user(String create_user){
		this.setInternal("CREATE_USER",create_user);
	}
	public String getCreate_user(){
		return (String)this.getInternal("CREATE_USER");
	}
	public void setUpdate_date(Date update_date){
		this.setInternal("UPDATE_DATE",update_date);
	}
	public Date getUpdate_date(){
		return (Date)this.getInternal("UPDATE_DATE");
	}
	public void setUpdate_user(String update_user){
		this.setInternal("UPDATE_USER",update_user);
	}
	public String getUpdate_user(){
		return (String)this.getInternal("UPDATE_USER");
	}
}