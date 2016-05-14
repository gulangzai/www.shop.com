package com.ccthanking.business.project.vo;
import com.ccthanking.framework.base.BaseVO;
import java.util.Date;

public class BuProjectStepsVO extends BaseVO{

	public BuProjectStepsVO(){
		this.addField("PROJECT_STEPS_UID",OP_STRING|this.TP_PK);//
		this.addField("PROJECT_FLOWS_UID",OP_STRING);//
		this.addField("STEP_CODE",OP_STRING);//步骤代码
		this.addField("STEP_NAME",OP_STRING);//步骤名称
		this.addField("STEP_NO",OP_STRING);//步骤顺序号
		this.addField("SUB_Y",OP_STRING);//是否为多处理人步骤，如会签
		this.addField("STEP_SUB_NO",OP_STRING);//子步骤顺序号
		this.addField("ROLE_UID",OP_STRING);//默认处理角色
		this.addField("DAYS",OP_STRING);//默认处理天数
		this.addField("DAYS_UNIT",OP_STRING);//默认处理天数单位，工作日：W；自然日：N
		this.addField("CAN_RETURN",OP_STRING);//步骤处理时，是否有权退回
		this.addField("RETURN_NO",OP_STRING);//如果处理结果是退回，退回到哪个步骤，空表示结束本次流转
		this.addField("MODIFY_Y",OP_STRING);//是否可以修改流转对象
		this.addField("DESCRIPTION",OP_STRING);//
		this.setVOTableName("BU_PROJECT_STEPS");
	}

	public void setProject_steps_uid(String project_steps_uid){
		this.setInternal("PROJECT_STEPS_UID",project_steps_uid);
	}
	public String getProject_steps_uid(){
		return (String)this.getInternal("PROJECT_STEPS_UID");
	}
	public void setProject_flows_uid(String project_flows_uid){
		this.setInternal("PROJECT_FLOWS_UID",project_flows_uid);
	}
	public String getProject_flows_uid(){
		return (String)this.getInternal("PROJECT_FLOWS_UID");
	}
	public void setStep_code(String step_code){
		this.setInternal("STEP_CODE",step_code);
	}
	public String getStep_code(){
		return (String)this.getInternal("STEP_CODE");
	}
	public void setStep_name(String step_name){
		this.setInternal("STEP_NAME",step_name);
	}
	public String getStep_name(){
		return (String)this.getInternal("STEP_NAME");
	}
	public void setStep_no(String step_no){
		this.setInternal("STEP_NO",step_no);
	}
	public String getStep_no(){
		return (String)this.getInternal("STEP_NO");
	}
	public void setSub_y(String sub_y){
		this.setInternal("SUB_Y",sub_y);
	}
	public String getSub_y(){
		return (String)this.getInternal("SUB_Y");
	}
	public void setStep_sub_no(String step_sub_no){
		this.setInternal("STEP_SUB_NO",step_sub_no);
	}
	public String getStep_sub_no(){
		return (String)this.getInternal("STEP_SUB_NO");
	}
	public void setRole_uid(String role_uid){
		this.setInternal("ROLE_UID",role_uid);
	}
	public String getRole_uid(){
		return (String)this.getInternal("ROLE_UID");
	}
	public void setDays(String days){
		this.setInternal("DAYS",days);
	}
	public String getDays(){
		return (String)this.getInternal("DAYS");
	}
	public void setDays_unit(String days_unit){
		this.setInternal("DAYS_UNIT",days_unit);
	}
	public String getDays_unit(){
		return (String)this.getInternal("DAYS_UNIT");
	}
	public void setCan_return(String can_return){
		this.setInternal("CAN_RETURN",can_return);
	}
	public String getCan_return(){
		return (String)this.getInternal("CAN_RETURN");
	}
	public void setReturn_no(String return_no){
		this.setInternal("RETURN_NO",return_no);
	}
	public String getReturn_no(){
		return (String)this.getInternal("RETURN_NO");
	}
	public void setModify_y(String modify_y){
		this.setInternal("MODIFY_Y",modify_y);
	}
	public String getModify_y(){
		return (String)this.getInternal("MODIFY_Y");
	}
	public void setDescription(String description){
		this.setInternal("DESCRIPTION",description);
	}
	public String getDescription(){
		return (String)this.getInternal("DESCRIPTION");
	}
}