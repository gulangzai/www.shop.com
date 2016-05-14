package com.ccthanking.business.pmjiancha.vo;
import com.ccthanking.framework.base.BaseVO;
import java.util.Date;

public class PmJianchaBzVO extends BaseVO{

	public PmJianchaBzVO(){
		this.addField("JIANCHA_BZ_UID",OP_STRING|this.TP_PK);//
		this.addField("PROJECT_UID",OP_STRING);//
		this.addField("JIANCHA_LEVEL_UID",OP_STRING);//
		this.addField("XUHAO",OP_STRING);//序号
		this.addField("BZGF_UID",OP_STRING);//
		this.addField("JIANCHA_TYPE",OP_STRING);//检查类型 ZJ-质量检查 AJ-安全检查
		this.addField("CONTENT",OP_STRING);//检查内容
		this.addField("CREATE_DATE",OP_DATE);//
		this.addField("CREATE_USER",OP_STRING);//
		this.addField("UPDATE_DATE",OP_DATE);//
		this.addField("UPDATE_USER",OP_STRING);//
		this.setFieldDateFormat("CREATE_DATE","yyyy-MM-dd HH:mm:ss");
		this.setFieldDateFormat("UPDATE_DATE","yyyy-MM-dd HH:mm:ss");
		this.setVOTableName("PM_JIANCHA_BZ");
	}

	public void setJiancha_bz_uid(String jiancha_bz_uid){
		this.setInternal("JIANCHA_BZ_UID",jiancha_bz_uid);
	}
	public String getJiancha_bz_uid(){
		return (String)this.getInternal("JIANCHA_BZ_UID");
	}
	public void setProject_uid(String project_uid){
		this.setInternal("PROJECT_UID",project_uid);
	}
	public String getProject_uid(){
		return (String)this.getInternal("PROJECT_UID");
	}
	public void setJiancha_level_uid(String jiancha_level_uid){
		this.setInternal("JIANCHA_LEVEL_UID",jiancha_level_uid);
	}
	public String getJiancha_level_uid(){
		return (String)this.getInternal("JIANCHA_LEVEL_UID");
	}
	public void setXuhao(String xuhao){
		this.setInternal("XUHAO",xuhao);
	}
	public String getXuhao(){
		return (String)this.getInternal("XUHAO");
	}
	public void setBzgf_uid(String bzgf_uid){
		this.setInternal("BZGF_UID",bzgf_uid);
	}
	public String getBzgf_uid(){
		return (String)this.getInternal("BZGF_UID");
	}
	public void setJiancha_type(String jiancha_type){
		this.setInternal("JIANCHA_TYPE",jiancha_type);
	}
	public String getJiancha_type(){
		return (String)this.getInternal("JIANCHA_TYPE");
	}
	public void setContent(String content){
		this.setInternal("CONTENT",content);
	}
	public String getContent(){
		return (String)this.getInternal("CONTENT");
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