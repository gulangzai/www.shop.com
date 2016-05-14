package com.ccthanking.business.message.vo;
import com.ccthanking.framework.base.BaseVO;
import java.util.Date;

public class BuUserMessageVO extends BaseVO{

	public BuUserMessageVO(){
		this.addField("USER_MESSAGE_UID",OP_STRING|this.TP_PK);//
		this.addField("USER_UID",OP_STRING);//
		this.addField("MESSAGE_CONTENT",OP_STRING);//消息内容
		this.addField("MESSAGE_TIME",OP_DATE);//消息接收时间
		this.addField("MESSAGE_URL",OP_STRING);//查看消息的URL
		this.addField("MESSAGE_PARA1",OP_STRING);//查看消息URL的参数1
		this.addField("MESSAGE_PARA2",OP_STRING);//查看消息URL的参数2
		this.addField("MESSAGE_PARA3",OP_STRING);//查看消息URL的参数3
		this.addField("MESSAGE_PARA4",OP_STRING);//查看消息URL的参数4
		this.addField("STATUS",OP_STRING);//查看状态，0-未看 1-已看 2-不同意 3-接受
		this.addField("SEETIME",OP_DATE);//查看时间
		this.addField("SEND_USERID",OP_STRING);//发起人ID
		this.addField("MESSAGE_TYPE",OP_STRING);//-1 邀请 0 回复
		this.setFieldDateFormat("MESSAGE_TIME","yyyy-MM-dd HH:mm:ss");
		this.setFieldDateFormat("SEETIME","yyyy-MM-dd HH:mm:ss");
		this.setVOTableName("BU_USER_MESSAGE");
	}

	public void setUser_message_uid(String user_message_uid){
		this.setInternal("USER_MESSAGE_UID",user_message_uid);
	}
	public String getUser_message_uid(){
		return (String)this.getInternal("USER_MESSAGE_UID");
	}
	public void setUser_uid(String user_uid){
		this.setInternal("USER_UID",user_uid);
	}
	public String getUser_uid(){
		return (String)this.getInternal("USER_UID");
	}
	public void setMessage_content(String message_content){
		this.setInternal("MESSAGE_CONTENT",message_content);
	}
	public String getMessage_content(){
		return (String)this.getInternal("MESSAGE_CONTENT");
	}
	public void setMessage_time(Date message_time){
		this.setInternal("MESSAGE_TIME",message_time);
	}
	public Date getMessage_time(){
		return (Date)this.getInternal("MESSAGE_TIME");
	}
	public void setMessage_url(String message_url){
		this.setInternal("MESSAGE_URL",message_url);
	}
	public String getMessage_url(){
		return (String)this.getInternal("MESSAGE_URL");
	}
	public void setMessage_para1(String message_para1){
		this.setInternal("MESSAGE_PARA1",message_para1);
	}
	public String getMessage_para1(){
		return (String)this.getInternal("MESSAGE_PARA1");
	}
	public void setMessage_para2(String message_para2){
		this.setInternal("MESSAGE_PARA2",message_para2);
	}
	public String getMessage_para2(){
		return (String)this.getInternal("MESSAGE_PARA2");
	}
	public void setMessage_para3(String message_para3){
		this.setInternal("MESSAGE_PARA3",message_para3);
	}
	public String getMessage_para3(){
		return (String)this.getInternal("MESSAGE_PARA3");
	}
	public void setMessage_para4(String message_para4){
		this.setInternal("MESSAGE_PARA4",message_para4);
	}
	public String getMessage_para4(){
		return (String)this.getInternal("MESSAGE_PARA4");
	}
	public void setStatus(String status){
		this.setInternal("STATUS",status);
	}
	public String getStatus(){
		return (String)this.getInternal("STATUS");
	}
	public void setSeetime(Date seetime){
		this.setInternal("SEETIME",seetime);
	}
	public Date getSeetime(){
		return (Date)this.getInternal("SEETIME");
	}
	public void setSend_userid(String send_userid){
		this.setInternal("SEND_USERID",send_userid);
	}
	public String getSend_userid(){
		return (String)this.getInternal("SEND_USERID");
	}
	public void setMessage_type(String message_type){
		this.setInternal("MESSAGE_TYPE",message_type);
	}
	public String getMessage_type(){
		return (String)this.getInternal("MESSAGE_TYPE");
	}
}