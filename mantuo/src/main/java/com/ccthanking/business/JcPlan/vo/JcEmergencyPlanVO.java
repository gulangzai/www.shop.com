package com.ccthanking.business.JcPlan.vo;
import com.ccthanking.framework.base.BaseVO;
import java.util.Date;

public class JcEmergencyPlanVO extends BaseVO{

	public JcEmergencyPlanVO(){
		this.addField("EMERGENCY_PLAN_UID",OP_STRING|this.TP_PK);//
		this.addField("PROJECT_UID",OP_STRING);//
		this.addField("PLAN_TYPE",OP_STRING);//类型，基坑－JK、降水－JS等
		this.addField("PLAN_NAME",OP_STRING);//预案名称
		this.addField("PLAN_CONTENT",OP_STRING);//预案内容
		this.addField("CREATE_DATE",OP_DATE);//
		this.addField("CREATE_USER",OP_STRING);//
		this.setFieldDateFormat("CREATE_DATE","yyyy-MM-dd HH:mm:ss");
		this.setVOTableName("JC_EMERGENCY_PLAN");
	}

	public void setEmergency_plan_uid(String emergency_plan_uid){
		this.setInternal("EMERGENCY_PLAN_UID",emergency_plan_uid);
	}
	public String getEmergency_plan_uid(){
		return (String)this.getInternal("EMERGENCY_PLAN_UID");
	}
	public void setProject_uid(String project_uid){
		this.setInternal("PROJECT_UID",project_uid);
	}
	public String getProject_uid(){
		return (String)this.getInternal("PROJECT_UID");
	}
	public void setPlan_type(String plan_type){
		this.setInternal("PLAN_TYPE",plan_type);
	}
	public String getPlan_type(){
		return (String)this.getInternal("PLAN_TYPE");
	}
	public void setPlan_name(String plan_name){
		this.setInternal("PLAN_NAME",plan_name);
	}
	public String getPlan_name(){
		return (String)this.getInternal("PLAN_NAME");
	}
	public void setPlan_content(String plan_content){
		this.setInternal("PLAN_CONTENT",plan_content);
	}
	public String getPlan_content(){
		return (String)this.getInternal("PLAN_CONTENT");
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
}