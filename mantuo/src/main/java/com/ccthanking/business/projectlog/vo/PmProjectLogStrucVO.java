package com.ccthanking.business.projectlog.vo;
import com.ccthanking.framework.base.BaseVO;

public class PmProjectLogStrucVO extends BaseVO{

	public PmProjectLogStrucVO(){
		this.addField("PROJECT_LOG_STRUC_UID",OP_STRING|this.TP_PK);//
		this.addField("PROJECT_LOG_UID",OP_STRING);//
		this.addField("PRJ_STRUC_UID",OP_STRING);//
		this.setVOTableName("PM_PROJECT_LOG_STRUC");
	}

	public void setProject_log_struc_uid(String project_log_struc_uid){
		this.setInternal("PROJECT_LOG_STRUC_UID",project_log_struc_uid);
	}
	public String getProject_log_struc_uid(){
		return (String)this.getInternal("PROJECT_LOG_STRUC_UID");
	}
	public void setProject_log_uid(String project_log_uid){
		this.setInternal("PROJECT_LOG_UID",project_log_uid);
	}
	public String getProject_log_uid(){
		return (String)this.getInternal("PROJECT_LOG_UID");
	}
	public void setPrj_struc_uid(String prj_struc_uid){
		this.setInternal("PRJ_STRUC_UID",prj_struc_uid);
	}
	public String getPrj_struc_uid(){
		return (String)this.getInternal("PRJ_STRUC_UID");
	}
}