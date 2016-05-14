package com.ccthanking.business.jcsb.vo;
import com.ccthanking.framework.base.BaseVO;
import java.util.Date;

public class JcJkEquipInfoVO extends BaseVO{

	public JcJkEquipInfoVO(){
		this.addField("JK_EQUIP_INFO_UID",OP_STRING|this.TP_PK);//
		this.addField("EQUIPMENT_UID",OP_STRING);//
		this.addField("PROJECT_UID",OP_STRING);//
		this.addField("STATUS",OP_STRING);//运行状态
		this.addField("PERSON",OP_STRING);//操作人员
		this.addField("BEGIN_TIME",OP_DATE);//进场时间
		this.addField("END_TIME",OP_DATE);//出场时间
		this.addField("CREATE_DATE",OP_DATE);//
		this.addField("CREATE_USER",OP_STRING);//
		this.addField("UPDATE_DATE",OP_DATE);//
		this.addField("UPDATE_USER",OP_STRING);//
		this.setFieldDateFormat("BEGIN_TIME","yyyy-MM-dd HH:mm:ss");
		this.setFieldDateFormat("END_TIME","yyyy-MM-dd HH:mm:ss");
		this.setFieldDateFormat("CREATE_DATE","yyyy-MM-dd HH:mm:ss");
		this.setFieldDateFormat("UPDATE_DATE","yyyy-MM-dd HH:mm:ss");
		this.setVOTableName("JC_JK_EQUIP_INFO");
	}

	public void setJk_equip_info_uid(String jk_equip_info_uid){
		this.setInternal("JK_EQUIP_INFO_UID",jk_equip_info_uid);
	}
	public String getJk_equip_info_uid(){
		return (String)this.getInternal("JK_EQUIP_INFO_UID");
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
	public void setStatus(String status){
		this.setInternal("STATUS",status);
	}
	public String getStatus(){
		return (String)this.getInternal("STATUS");
	}
	public void setPerson(String person){
		this.setInternal("PERSON",person);
	}
	public String getPerson(){
		return (String)this.getInternal("PERSON");
	}
	public void setBegin_time(Date begin_time){
		this.setInternal("BEGIN_TIME",begin_time);
	}
	public Date getBegin_time(){
		return (Date)this.getInternal("BEGIN_TIME");
	}
	public void setEnd_time(Date end_time){
		this.setInternal("END_TIME",end_time);
	}
	public Date getEnd_time(){
		return (Date)this.getInternal("END_TIME");
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