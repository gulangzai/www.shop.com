package com.ccthanking.business.project.vo;
import com.ccthanking.framework.base.BaseVO;
import java.util.Date;

public class BuProjectRoleVO extends BaseVO{

	public BuProjectRoleVO(){
		this.addField("PROJECT_ROLE_UID",OP_STRING|this.TP_PK);//
		this.addField("PROJECT_UID",OP_STRING);//
		this.addField("ROLE_UID",OP_STRING);//角色模板UID，自建项目角色为空
		this.addField("ROLE_NAME",OP_STRING);//项目角色名称
		this.addField("XUHAO",OP_STRING);//序号
		this.addField("DESCRIPTION",OP_STRING);//备注
		this.addField("CREATED_DATE",OP_DATE);//创建时间
		this.addField("UPDATED_DATE",OP_DATE);//最后更新时间
		this.setFieldDateFormat("CREATED_DATE","yyyy-MM-dd HH:mm:ss");
		this.setFieldDateFormat("UPDATED_DATE","yyyy-MM-dd HH:mm:ss");
		this.setVOTableName("BU_PROJECT_ROLE");
	}

	public void setProject_role_uid(String project_role_uid){
		this.setInternal("PROJECT_ROLE_UID",project_role_uid);
	}
	public String getProject_role_uid(){
		return (String)this.getInternal("PROJECT_ROLE_UID");
	}
	public void setProject_uid(String project_uid){
		this.setInternal("PROJECT_UID",project_uid);
	}
	public String getProject_uid(){
		return (String)this.getInternal("PROJECT_UID");
	}
	public void setRole_uid(String role_uid){
		this.setInternal("ROLE_UID",role_uid);
	}
	public String getRole_uid(){
		return (String)this.getInternal("ROLE_UID");
	}
	public void setRole_name(String role_name){
		this.setInternal("ROLE_NAME",role_name);
	}
	public String getRole_name(){
		return (String)this.getInternal("ROLE_NAME");
	}
	public void setXuhao(String xuhao){
		this.setInternal("XUHAO",xuhao);
	}
	public String getXuhao(){
		return (String)this.getInternal("XUHAO");
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
	public void setUpdated_date(Date updated_date){
		this.setInternal("UPDATED_DATE",updated_date);
	}
	public Date getUpdated_date(){
		return (Date)this.getInternal("UPDATED_DATE");
	}
}