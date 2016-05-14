package com.ccthanking.business.loguserread.vo;
import com.ccthanking.framework.base.BaseVO;
import java.util.Date;

public class LogUserReadVO extends BaseVO{

	public LogUserReadVO(){
		this.addField("USER_READ_UID",OP_STRING|this.TP_PK);//
		this.addField("LOGIN_SESSION",OP_STRING);//会话
		this.addField("USER_UID",OP_STRING);//用户ID
		this.addField("USER_NAME",OP_STRING);//用户姓名
		this.addField("PROJECT_UID",OP_STRING);//项目UID
		this.addField("TARGET_CODE",OP_STRING);//查看目标代码，如XCGK－现场工况；HT－话题
		this.addField("TARGET_UID",OP_STRING);//查看目标UID
		this.addField("READ_TIME",OP_DATE);//查看时间
		this.setFieldDateFormat("READ_TIME","yyyy-MM-dd HH:mm:ss");
		this.setVOTableName("LOG_USER_READ");
	}

	public void setUser_read_uid(String user_read_uid){
		this.setInternal("USER_READ_UID",user_read_uid);
	}
	public String getUser_read_uid(){
		return (String)this.getInternal("USER_READ_UID");
	}
	public void setLogin_session(String login_session){
		this.setInternal("LOGIN_SESSION",login_session);
	}
	public String getLogin_session(){
		return (String)this.getInternal("LOGIN_SESSION");
	}
	public void setUser_uid(String user_uid){
		this.setInternal("USER_UID",user_uid);
	}
	public String getUser_uid(){
		return (String)this.getInternal("USER_UID");
	}
	public void setUser_name(String user_name){
		this.setInternal("USER_NAME",user_name);
	}
	public String getUser_name(){
		return (String)this.getInternal("USER_NAME");
	}
	public void setProject_uid(String project_uid){
		this.setInternal("PROJECT_UID",project_uid);
	}
	public String getProject_uid(){
		return (String)this.getInternal("PROJECT_UID");
	}
	public void setTarget_code(String target_code){
		this.setInternal("TARGET_CODE",target_code);
	}
	public String getTarget_code(){
		return (String)this.getInternal("TARGET_CODE");
	}
	public void setTarget_uid(String target_uid){
		this.setInternal("TARGET_UID",target_uid);
	}
	public String getTarget_uid(){
		return (String)this.getInternal("TARGET_UID");
	}
	public void setRead_time(Date read_time){
		this.setInternal("READ_TIME",read_time);
	}
	public Date getRead_time(){
		return (Date)this.getInternal("READ_TIME");
	}
}