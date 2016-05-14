package com.ccthanking.business.pmjiancha.vo;
import com.ccthanking.framework.base.BaseVO;
import java.util.Date;

public class PmXunjianVO extends BaseVO{

	public PmXunjianVO(){
		this.addField("XUNJIAN_UID",OP_STRING|this.TP_PK);//
		this.addField("PROJECT_UID",OP_STRING);//
		this.addField("JIANCHA_LEVEL_UID",OP_STRING);//巡检级别：抽查、日常巡检、上级部门检查等
		this.addField("XUNJIAN_DATE",OP_DATE);//巡检日期
		this.addField("JIERUN",OP_STRING);//检查结论
		this.addField("CANJIAN",OP_STRING);//参检人员
		this.addField("STATUS",OP_STRING);//巡检状态：-1－草稿；0－处理中；1－关闭
		this.addField("XUNJIAN_TYPE",OP_STRING);//巡检类型 ZJ-质量检查 AJ-安全检查
		this.addField("ENABLED",OP_STRING);//是否有效，1－有效；0－无效
		this.addField("CREATE_DATE",OP_DATE);//
		this.addField("CREATE_USER",OP_STRING);//
		this.addField("UPDATE_DATE",OP_DATE);//
		this.addField("UPDATE_USER",OP_STRING);//
		this.setFieldDateFormat("XUNJIAN_DATE","yyyy-MM-dd HH:mm:ss");
		this.setFieldDateFormat("CREATE_DATE","yyyy-MM-dd HH:mm:ss");
		this.setFieldDateFormat("UPDATE_DATE","yyyy-MM-dd HH:mm:ss");
		this.setVOTableName("PM_XUNJIAN");
	}

	public void setXunjian_uid(String xunjian_uid){
		this.setInternal("XUNJIAN_UID",xunjian_uid);
	}
	public String getXunjian_uid(){
		return (String)this.getInternal("XUNJIAN_UID");
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
	public void setXunjian_date(Date xunjian_date){
		this.setInternal("XUNJIAN_DATE",xunjian_date);
	}
	public Date getXunjian_date(){
		return (Date)this.getInternal("XUNJIAN_DATE");
	}
	public void setJierun(String jierun){
		this.setInternal("JIERUN",jierun);
	}
	public String getJierun(){
		return (String)this.getInternal("JIERUN");
	}
	public void setCanjian(String canjian){
		this.setInternal("CANJIAN",canjian);
	}
	public String getCanjian(){
		return (String)this.getInternal("CANJIAN");
	}
	public void setStatus(String status){
		this.setInternal("STATUS",status);
	}
	public String getStatus(){
		return (String)this.getInternal("STATUS");
	}
	public void setXunjian_type(String xunjian_type){
		this.setInternal("XUNJIAN_TYPE",xunjian_type);
	}
	public String getXunjian_type(){
		return (String)this.getInternal("XUNJIAN_TYPE");
	}
	public void setEnabled(String enabled){
		this.setInternal("ENABLED",enabled);
	}
	public String getEnabled(){
		return (String)this.getInternal("ENABLED");
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