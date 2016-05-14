package com.ccthanking.business.projectlog.vo;
import com.ccthanking.framework.base.BaseVO;
import java.util.Date;

public class PmProjectTopicVO extends BaseVO{

	public PmProjectTopicVO(){
		this.addField("PROJECT_TOPIC_UID",OP_STRING|this.TP_PK);//
		this.addField("PROJECT_UID",OP_STRING);//
		this.addField("SUBJECT",OP_STRING);//
		this.addField("CONTENT",OP_STRING);//
		this.addField("STATUS",OP_STRING);//话题状态，0－草稿；1－已发布
		this.addField("CREATE_DATE",OP_DATE);//
		this.addField("CREATE_USER",OP_STRING);//
		this.addField("UPDATE_DATE",OP_DATE);//
		this.setFieldDateFormat("CREATE_DATE","yyyy-MM-dd HH:mm:ss");
		this.setFieldDateFormat("UPDATE_DATE","yyyy-MM-dd HH:mm:ss");
		this.setVOTableName("PM_PROJECT_TOPIC");
	}

	public void setProject_topic_uid(String project_topic_uid){
		this.setInternal("PROJECT_TOPIC_UID",project_topic_uid);
	}
	public String getProject_topic_uid(){
		return (String)this.getInternal("PROJECT_TOPIC_UID");
	}
	public void setProject_uid(String project_uid){
		this.setInternal("PROJECT_UID",project_uid);
	}
	public String getProject_uid(){
		return (String)this.getInternal("PROJECT_UID");
	}
	public void setSubject(String subject){
		this.setInternal("SUBJECT",subject);
	}
	public String getSubject(){
		return (String)this.getInternal("SUBJECT");
	}
	public void setContent(String content){
		this.setInternal("CONTENT",content);
	}
	public String getContent(){
		return (String)this.getInternal("CONTENT");
	}
	public void setStatus(String status){
		this.setInternal("STATUS",status);
	}
	public String getStatus(){
		return (String)this.getInternal("STATUS");
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