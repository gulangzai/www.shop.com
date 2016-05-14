package com.ccthanking.business.commons.vo;
import com.ccthanking.framework.base.BaseVO;
import java.util.Date;

public class SysUserVO extends BaseVO{

	public SysUserVO(){
		this.addField("USER_UID",OP_STRING|this.TP_PK);//
		this.addField("COMPANY_UID",OP_STRING);//
		this.addField("USER_NAME",OP_STRING);//用户姓名
		this.addField("LOGON_NAME",OP_STRING);//登录名
		this.addField("EMAIL",OP_STRING);//用户邮箱
		this.addField("PWD",OP_STRING);//加密后的密码
		this.addField("MOBILE",OP_STRING);//手机
		this.addField("ENABLED",OP_STRING);//是否有效，1－有效；0－无效
		this.addField("CREATED_IP",OP_STRING);//注册时IP
		this.addField("CREATED_DATE",OP_DATE);//注册时间
		this.setFieldDateFormat("CREATED_DATE","yyyy-MM-dd");
		this.setVOTableName("SYS_USER");
	}

	public void setUser_uid(String user_uid){
		this.setInternal("USER_UID",user_uid);
	}
	public String getUser_uid(){
		return (String)this.getInternal("USER_UID");
	}
	public void setCompany_uid(String company_uid){
		this.setInternal("COMPANY_UID",company_uid);
	}
	public String getCompany_uid(){
		return (String)this.getInternal("COMPANY_UID");
	}
	public void setUser_name(String user_name){
		this.setInternal("USER_NAME",user_name);
	}
	public String getUser_name(){
		return (String)this.getInternal("USER_NAME");
	}
	public void setLogon_name(String logon_name){
		this.setInternal("LOGON_NAME",logon_name);
	}
	public String getLogon_name(){
		return (String)this.getInternal("LOGON_NAME");
	}
	public void setEmail(String email){
		this.setInternal("EMAIL",email);
	}
	public String getEmail(){
		return (String)this.getInternal("EMAIL");
	}
	public void setPwd(String pwd){
		this.setInternal("PWD",pwd);
	}
	public String getPwd(){
		return (String)this.getInternal("PWD");
	}
	public void setMobile(String mobile){
		this.setInternal("MOBILE",mobile);
	}
	public String getMobile(){
		return (String)this.getInternal("MOBILE");
	}
	public void setEnabled(String enabled){
		this.setInternal("ENABLED",enabled);
	}
	public String getEnabled(){
		return (String)this.getInternal("ENABLED");
	}
	public void setCreated_ip(String created_ip){
		this.setInternal("CREATED_IP",created_ip);
	}
	public String getCreated_ip(){
		return (String)this.getInternal("CREATED_IP");
	}
	public void setCreated_date(Date created_date){
		this.setInternal("CREATED_DATE",created_date);
	}
	public Date getCreated_date(){
		return (Date)this.getInternal("CREATED_DATE");
	}
}