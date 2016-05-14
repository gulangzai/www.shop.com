package com.ccthanking.business.xmgk.vo;
import com.ccthanking.framework.base.BaseVO;
import java.util.Date;

public class PmGongkuangVO extends BaseVO{

	public PmGongkuangVO(){
		this.addField("GONGKUANG_UID",OP_STRING|this.TP_PK);//
		this.addField("PROJECT_UID",OP_STRING);//
		this.addField("XUHAO",OP_STRING);//序号
		this.addField("JINDU",OP_STRING);//工况名称
		this.addField("REPORT_DATE",OP_DATE);//计划完成日期
		this.addField("FINISH_DATE",OP_DATE);//实际完成日期
		this.addField("DESCRIPTION",OP_STRING);//工况描述
		this.addField("CURRENT_Y",OP_STRING);//是否为当前工况，Y－当前工况
		this.addField("CREATE_DATE",OP_DATE);//
		this.addField("CREATE_USER",OP_STRING);//
		this.setFieldDateFormat("REPORT_DATE","yyyy-MM-dd");
		this.setFieldDateFormat("FINISH_DATE","yyyy-MM-dd");
		this.setFieldDateFormat("CREATE_DATE","yyyy-MM-dd HH:mm:ss");
		this.setVOTableName("PM_GONGKUANG");
	}

	public void setGongkuang_uid(String gongkuang_uid){
		this.setInternal("GONGKUANG_UID",gongkuang_uid);
	}
	public String getGongkuang_uid(){
		return (String)this.getInternal("GONGKUANG_UID");
	}
	public void setProject_uid(String project_uid){
		this.setInternal("PROJECT_UID",project_uid);
	}
	public String getProject_uid(){
		return (String)this.getInternal("PROJECT_UID");
	}
	public void setXuhao(String xuhao){
		this.setInternal("XUHAO",xuhao);
	}
	public String getXuhao(){
		return (String)this.getInternal("XUHAO");
	}
	public void setJindu(String jindu){
		this.setInternal("JINDU",jindu);
	}
	public String getJindu(){
		return (String)this.getInternal("JINDU");
	}
	public void setReport_date(Date report_date){
		this.setInternal("REPORT_DATE",report_date);
	}
	public Date getReport_date(){
		return (Date)this.getInternal("REPORT_DATE");
	}
	public void setFinish_date(Date finish_date){
		this.setInternal("FINISH_DATE",finish_date);
	}
	public Date getFinish_date(){
		return (Date)this.getInternal("FINISH_DATE");
	}
	public void setDescription(String description){
		this.setInternal("DESCRIPTION",description);
	}
	public String getDescription(){
		return (String)this.getInternal("DESCRIPTION");
	}
	public void setCurrent_y(String current_y){
		this.setInternal("CURRENT_Y",current_y);
	}
	public String getCurrent_y(){
		return (String)this.getInternal("CURRENT_Y");
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