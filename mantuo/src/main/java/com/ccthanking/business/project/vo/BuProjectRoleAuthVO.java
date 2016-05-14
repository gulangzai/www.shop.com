package com.ccthanking.business.project.vo;
import com.ccthanking.framework.base.BaseVO;

public class BuProjectRoleAuthVO extends BaseVO{

	public BuProjectRoleAuthVO(){
		this.addField("PROJECT_ROLE_AUTH_UID",OP_STRING|this.TP_PK);//
		this.addField("PROJECT_ROLE_UID",OP_STRING);//
		this.addField("AUTHORITY_UID",OP_STRING);//
		this.setVOTableName("BU_PROJECT_ROLE_AUTH");
	}

	public void setProject_role_auth_uid(String project_role_auth_uid){
		this.setInternal("PROJECT_ROLE_AUTH_UID",project_role_auth_uid);
	}
	public String getProject_role_auth_uid(){
		return (String)this.getInternal("PROJECT_ROLE_AUTH_UID");
	}
	public void setProject_role_uid(String project_role_uid){
		this.setInternal("PROJECT_ROLE_UID",project_role_uid);
	}
	public String getProject_role_uid(){
		return (String)this.getInternal("PROJECT_ROLE_UID");
	}
	public void setAuthority_uid(String authority_uid){
		this.setInternal("AUTHORITY_UID",authority_uid);
	}
	public String getAuthority_uid(){
		return (String)this.getInternal("AUTHORITY_UID");
	}
}