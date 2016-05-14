package com.ccthanking.business.project.vo;
import com.ccthanking.framework.base.BaseVO;

public class BuProjectRoleUserVO extends BaseVO{

	public BuProjectRoleUserVO(){
		this.addField("PROJECT_ROLE_USER_UID",OP_STRING|this.TP_PK);//
		this.addField("PROJECT_ROLE_UID",OP_STRING);//
		this.addField("PROJECT_USER_UID",OP_STRING);//
		this.setVOTableName("BU_PROJECT_ROLE_USER");
	}

	public void setProject_role_user_uid(String project_role_user_uid){
		this.setInternal("PROJECT_ROLE_USER_UID",project_role_user_uid);
	}
	public String getProject_role_user_uid(){
		return (String)this.getInternal("PROJECT_ROLE_USER_UID");
	}
	public void setProject_role_uid(String project_role_uid){
		this.setInternal("PROJECT_ROLE_UID",project_role_uid);
	}
	public String getProject_role_uid(){
		return (String)this.getInternal("PROJECT_ROLE_UID");
	}
	public void setProject_user_uid(String project_user_uid){
		this.setInternal("PROJECT_USER_UID",project_user_uid);
	}
	public String getProject_user_uid(){
		return (String)this.getInternal("PROJECT_USER_UID");
	}
}