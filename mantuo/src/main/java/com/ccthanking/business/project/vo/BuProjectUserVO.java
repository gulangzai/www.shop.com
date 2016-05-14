package com.ccthanking.business.project.vo;
import com.ccthanking.framework.base.BaseVO;
import java.util.Date;

public class BuProjectUserVO extends BaseVO{

	public BuProjectUserVO(){
		this.addField("PROJECT_USER_UID",OP_STRING|this.TP_PK);//
		this.addField("PROJECT_UID",OP_STRING);//
		this.addField("USER_UID",OP_STRING);//
		this.addField("CREATED_DATE",OP_DATE);//
		this.setFieldDateFormat("CREATED_DATE","yyyy-MM-dd HH:mm:ss");
		this.setVOTableName("BU_PROJECT_USER");
	}

	public void setProject_user_uid(String project_user_uid){
		this.setInternal("PROJECT_USER_UID",project_user_uid);
	}
	public String getProject_user_uid(){
		return (String)this.getInternal("PROJECT_USER_UID");
	}
	public void setProject_uid(String project_uid){
		this.setInternal("PROJECT_UID",project_uid);
	}
	public String getProject_uid(){
		return (String)this.getInternal("PROJECT_UID");
	}
	public void setUser_uid(String user_uid){
		this.setInternal("USER_UID",user_uid);
	}
	public String getUser_uid(){
		return (String)this.getInternal("USER_UID");
	}
	public void setCreated_date(Date created_date){
		this.setInternal("CREATED_DATE",created_date);
	}
	public Date getCreated_date(){
		return (Date)this.getInternal("CREATED_DATE");
	}
}