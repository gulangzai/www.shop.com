package com.ccthanking.business.projectlog.vo;
import com.ccthanking.framework.base.BaseVO;
import java.util.Date;

public class PmProjectTopicReplyVO extends BaseVO{

	public PmProjectTopicReplyVO(){
		this.addField("PROJECT_TOPIC_REPLY_UID",OP_STRING|this.TP_PK);//
		this.addField("PROJECT_TOPIC_UID",OP_STRING);//
		this.addField("XUHAO",OP_STRING);//序号
		this.addField("CONTENT",OP_STRING);//回复内容
		this.addField("CREATE_DATE",OP_DATE);//回复人
		this.addField("CREATE_USER",OP_STRING);//回复时间
		this.addField("UPDATE_DATE",OP_DATE);//修改时间
		this.setFieldDateFormat("CREATE_DATE","yyyy-MM-dd HH:mm:ss");
		this.setFieldDateFormat("UPDATE_DATE","yyyy-MM-dd HH:mm:ss");
		this.setVOTableName("PM_PROJECT_TOPIC_REPLY");
	}

	public void setProject_topic_reply_uid(String project_topic_reply_uid){
		this.setInternal("PROJECT_TOPIC_REPLY_UID",project_topic_reply_uid);
	}
	public String getProject_topic_reply_uid(){
		return (String)this.getInternal("PROJECT_TOPIC_REPLY_UID");
	}
	public void setProject_topic_uid(String project_topic_uid){
		this.setInternal("PROJECT_TOPIC_UID",project_topic_uid);
	}
	public String getProject_topic_uid(){
		return (String)this.getInternal("PROJECT_TOPIC_UID");
	}
	public void setXuhao(String xuhao){
		this.setInternal("XUHAO",xuhao);
	}
	public String getXuhao(){
		return (String)this.getInternal("XUHAO");
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
}