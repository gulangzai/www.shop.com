package com.ccthanking.business.invite.vo;
import com.ccthanking.framework.base.BaseVO;
import java.util.Date;

public class SysInviteVO extends BaseVO{

	public SysInviteVO(){
		this.addField("INVITE_UID",OP_STRING|this.TP_PK);//
		this.addField("INVITER_USER",OP_STRING);//邀请人UID
		this.addField("PROJECT_UID",OP_STRING);//邀请的项目
		this.addField("EMAIL",OP_STRING);//受邀人EMAIL
		this.addField("INVITE_DATE",OP_DATE);//邀请时间
		this.addField("EXPIRED_DATE",OP_DATE);//过期日期
		this.addField("JOIN_DATE",OP_DATE);//受邀加入项目时间
		this.addField("CREATE_USER",OP_STRING);//
		this.addField("INVITE_CODE",OP_STRING);//邀请码
		this.addField("STATUS",OP_STRING);//0 :同意 1 不同意
		this.setFieldDateFormat("INVITE_DATE","yyyy-MM-dd HH:mm:ss");
		this.setFieldDateFormat("EXPIRED_DATE","yyyy-MM-dd");
		this.setFieldDateFormat("JOIN_DATE","yyyy-MM-dd HH:mm:ss");
		this.setVOTableName("SYS_INVITE");
	}

	public void setInvite_uid(String invite_uid){
		this.setInternal("INVITE_UID",invite_uid);
	}
	public String getInvite_uid(){
		return (String)this.getInternal("INVITE_UID");
	}
	public void setInviter_user(String inviter_user){
		this.setInternal("INVITER_USER",inviter_user);
	}
	public String getInviter_user(){
		return (String)this.getInternal("INVITER_USER");
	}
	public void setProject_uid(String project_uid){
		this.setInternal("PROJECT_UID",project_uid);
	}
	public String getProject_uid(){
		return (String)this.getInternal("PROJECT_UID");
	}
	public void setEmail(String email){
		this.setInternal("EMAIL",email);
	}
	public String getEmail(){
		return (String)this.getInternal("EMAIL");
	}
	public void setInvite_date(Date invite_date){
		this.setInternal("INVITE_DATE",invite_date);
	}
	public Date getInvite_date(){
		return (Date)this.getInternal("INVITE_DATE");
	}
	public void setExpired_date(Date expired_date){
		this.setInternal("EXPIRED_DATE",expired_date);
	}
	public Date getExpired_date(){
		return (Date)this.getInternal("EXPIRED_DATE");
	}
	public void setJoin_date(Date join_date){
		this.setInternal("JOIN_DATE",join_date);
	}
	public Date getJoin_date(){
		return (Date)this.getInternal("JOIN_DATE");
	}
	public void setCreate_user(String create_user){
		this.setInternal("CREATE_USER",create_user);
	}
	public String getCreate_user(){
		return (String)this.getInternal("CREATE_USER");
	}
	public void setInvite_code(String invite_code){
		this.setInternal("INVITE_CODE",invite_code);
	}
	public String getInvite_code(){
		return (String)this.getInternal("INVITE_CODE");
	}
	public void setStatus(String status){
		this.setInternal("STATUS",status);
	}
	public String getStatus(){
		return (String)this.getInternal("STATUS");
	}
}