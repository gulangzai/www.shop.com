package com.ccthanking.business.projectlog.vo;
import com.ccthanking.framework.base.BaseVO;
import java.util.Date;

public class PmProjectLogVO extends BaseVO{

	public PmProjectLogVO(){
		this.addField("PROJECT_LOG_UID",OP_STRING|this.TP_PK);//
		this.addField("PROJECT_UID",OP_STRING);//
		this.addField("LOG_DATE",OP_DATE);//项目日志日期
		this.addField("TUFA",OP_STRING);//突发事件
		this.addField("CQQK_INFO",OP_STRING);//就有关问题与施工单位、监理单位处理澄清情况简述
		this.addField("GONGZUO_INFO",OP_STRING);//监理机构主要工作简述
		this.addField("SHENGCHAN_INFO",OP_STRING);//生产情况记录
		this.addField("ZLAQ_INFO",OP_STRING);//技术质量安全情况记录
		this.addField("LOG_TYPE",OP_STRING);//日志类型：SG－施工日志；JL－监理日志；YZ-业主日报
		this.addField("CREATE_DATE",OP_DATE);//
		this.addField("CREATE_USER",OP_STRING);//
		this.addField("UPDATE_DATE",OP_DATE);//
		this.addField("UPDATE_USER",OP_STRING);//
		this.addField("YZLOG_TITLE",OP_STRING);//业主日报标题
		this.addField("YZLOG_CONTENT",OP_STRING);//业主日报内容
		this.setFieldDateFormat("LOG_DATE","yyyy-MM-dd");
		this.setFieldDateFormat("CREATE_DATE","yyyy-MM-dd HH:mm:ss");
		this.setFieldDateFormat("UPDATE_DATE","yyyy-MM-dd HH:mm:ss");
		this.setVOTableName("PM_PROJECT_LOG");
	}

	public void setProject_log_uid(String project_log_uid){
		this.setInternal("PROJECT_LOG_UID",project_log_uid);
	}
	public String getProject_log_uid(){
		return (String)this.getInternal("PROJECT_LOG_UID");
	}
	public void setProject_uid(String project_uid){
		this.setInternal("PROJECT_UID",project_uid);
	}
	public String getProject_uid(){
		return (String)this.getInternal("PROJECT_UID");
	}
	public void setLog_date(Date log_date){
		this.setInternal("LOG_DATE",log_date);
	}
	public Date getLog_date(){
		return (Date)this.getInternal("LOG_DATE");
	}
	public void setTufa(String tufa){
		this.setInternal("TUFA",tufa);
	}
	public String getTufa(){
		return (String)this.getInternal("TUFA");
	}
	public void setCqqk_info(String cqqk_info){
		this.setInternal("CQQK_INFO",cqqk_info);
	}
	public String getCqqk_info(){
		return (String)this.getInternal("CQQK_INFO");
	}
	public void setGongzuo_info(String gongzuo_info){
		this.setInternal("GONGZUO_INFO",gongzuo_info);
	}
	public String getGongzuo_info(){
		return (String)this.getInternal("GONGZUO_INFO");
	}
	public void setShengchan_info(String shengchan_info){
		this.setInternal("SHENGCHAN_INFO",shengchan_info);
	}
	public String getShengchan_info(){
		return (String)this.getInternal("SHENGCHAN_INFO");
	}
	public void setZlaq_info(String zlaq_info){
		this.setInternal("ZLAQ_INFO",zlaq_info);
	}
	public String getZlaq_info(){
		return (String)this.getInternal("ZLAQ_INFO");
	}
	public void setLog_type(String log_type){
		this.setInternal("LOG_TYPE",log_type);
	}
	public String getLog_type(){
		return (String)this.getInternal("LOG_TYPE");
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
	public void setYzlog_title(String yzlog_title){
		this.setInternal("YZLOG_TITLE",yzlog_title);
	}
	public String getYzlog_title(){
		return (String)this.getInternal("YZLOG_TITLE");
	}
	public void setYzlog_content(String yzlog_content){
		this.setInternal("YZLOG_CONTENT",yzlog_content);
	}
	public String getYzlog_content(){
		return (String)this.getInternal("YZLOG_CONTENT");
	}
}