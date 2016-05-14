package com.ccthanking.business.jcsb.vo;
import com.ccthanking.framework.base.BaseVO;
import java.util.Date;

public class JcEquipmentVO extends BaseVO{

	public JcEquipmentVO(){
		this.addField("EQUIPMENT_UID",OP_STRING|this.TP_PK);//
		this.addField("PROJECT_UID",OP_STRING);//
		this.addField("EQUIPMENT_TYPE",OP_STRING);//设备类型，基坑、降水
		this.addField("EQUIPMENT_CODE",OP_STRING);//
		this.addField("EQUIPMENT_NAME",OP_STRING);//
		this.addField("EQUIPMENT_MODEL",OP_STRING);//设备型号
		this.addField("EQUIPMENT_UNIT",OP_STRING);//设备单位，个、台等
		this.addField("WATER_YIELD",OP_STRING);//出水量（立方米/秒）
		this.addField("ELECTRICITY",OP_STRING);//耗电（KW）
		this.addField("CREATED_DATE",OP_DATE);//
		this.addField("CREATED_USER",OP_STRING);//
		this.setFieldDateFormat("CREATED_DATE","yyyy-MM-dd HH:mm:ss");
		this.setVOTableName("JC_EQUIPMENT");
	}

	public void setEquipment_uid(String equipment_uid){
		this.setInternal("EQUIPMENT_UID",equipment_uid);
	}
	public String getEquipment_uid(){
		return (String)this.getInternal("EQUIPMENT_UID");
	}
	public void setProject_uid(String project_uid){
		this.setInternal("PROJECT_UID",project_uid);
	}
	public String getProject_uid(){
		return (String)this.getInternal("PROJECT_UID");
	}
	public void setEquipment_type(String equipment_type){
		this.setInternal("EQUIPMENT_TYPE",equipment_type);
	}
	public String getEquipment_type(){
		return (String)this.getInternal("EQUIPMENT_TYPE");
	}
	public void setEquipment_code(String equipment_code){
		this.setInternal("EQUIPMENT_CODE",equipment_code);
	}
	public String getEquipment_code(){
		return (String)this.getInternal("EQUIPMENT_CODE");
	}
	public void setEquipment_name(String equipment_name){
		this.setInternal("EQUIPMENT_NAME",equipment_name);
	}
	public String getEquipment_name(){
		return (String)this.getInternal("EQUIPMENT_NAME");
	}
	public void setEquipment_model(String equipment_model){
		this.setInternal("EQUIPMENT_MODEL",equipment_model);
	}
	public String getEquipment_model(){
		return (String)this.getInternal("EQUIPMENT_MODEL");
	}
	public void setEquipment_unit(String equipment_unit){
		this.setInternal("EQUIPMENT_UNIT",equipment_unit);
	}
	public String getEquipment_unit(){
		return (String)this.getInternal("EQUIPMENT_UNIT");
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
	public void setCreated_date(Date created_date){
		this.setInternal("CREATED_DATE",created_date);
	}
	public Date getCreated_date(){
		return (Date)this.getInternal("CREATED_DATE");
	}
	public void setCreated_user(String created_user){
		this.setInternal("CREATED_USER",created_user);
	}
	public String getCreated_user(){
		return (String)this.getInternal("CREATED_USER");
	}
}