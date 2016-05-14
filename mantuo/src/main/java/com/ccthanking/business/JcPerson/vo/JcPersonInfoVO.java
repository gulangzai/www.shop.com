package com.ccthanking.business.JcPerson.vo;
import com.ccthanking.framework.base.BaseVO;
import java.util.Date;

public class JcPersonInfoVO extends BaseVO{

	public JcPersonInfoVO(){
		this.addField("PERSON_INFO_UID",OP_STRING|this.TP_PK);//
		this.addField("PROJECT_UID",OP_STRING);//
		this.addField("CONTACT_TYPE",OP_STRING);//联络类型，基坑－JK、降水－JS等
		this.addField("REPAIR_Y",OP_STRING);//是否为抢修单位联系人
		this.addField("PERSON_NAME",OP_STRING);//联系人姓名
		this.addField("PERSON_POST",OP_STRING);//联系人职务
		this.addField("COMPANY_NAME",OP_STRING);//联系人公司名称
		this.addField("PERSON_MOBILE",OP_STRING);//联系电话
		this.addField("PERSON_EMAIL",OP_STRING);//联系人EMAIL
		this.addField("CREATE_DATE",OP_DATE);//
		this.addField("CREATE_USER",OP_STRING);//
		this.addField("UPDATE_DATE",OP_DATE);//
		this.addField("UPDATE_USER",OP_STRING);//
		this.setFieldDateFormat("CREATE_DATE","yyyy-MM-dd HH:mm:ss");
		this.setFieldDateFormat("UPDATE_DATE","yyyy-MM-dd HH:mm:ss");
		this.setVOTableName("JC_PERSON_INFO");
	}

	public void setPerson_info_uid(String person_info_uid){
		this.setInternal("PERSON_INFO_UID",person_info_uid);
	}
	public String getPerson_info_uid(){
		return (String)this.getInternal("PERSON_INFO_UID");
	}
	public void setProject_uid(String project_uid){
		this.setInternal("PROJECT_UID",project_uid);
	}
	public String getProject_uid(){
		return (String)this.getInternal("PROJECT_UID");
	}
	public void setContact_type(String contact_type){
		this.setInternal("CONTACT_TYPE",contact_type);
	}
	public String getContact_type(){
		return (String)this.getInternal("CONTACT_TYPE");
	}
	public void setRepair_y(String repair_y){
		this.setInternal("REPAIR_Y",repair_y);
	}
	public String getRepair_y(){
		return (String)this.getInternal("REPAIR_Y");
	}
	public void setPerson_name(String person_name){
		this.setInternal("PERSON_NAME",person_name);
	}
	public String getPerson_name(){
		return (String)this.getInternal("PERSON_NAME");
	}
	public void setPerson_post(String person_post){
		this.setInternal("PERSON_POST",person_post);
	}
	public String getPerson_post(){
		return (String)this.getInternal("PERSON_POST");
	}
	public void setCompany_name(String company_name){
		this.setInternal("COMPANY_NAME",company_name);
	}
	public String getCompany_name(){
		return (String)this.getInternal("COMPANY_NAME");
	}
	public void setPerson_mobile(String person_mobile){
		this.setInternal("PERSON_MOBILE",person_mobile);
	}
	public String getPerson_mobile(){
		return (String)this.getInternal("PERSON_MOBILE");
	}
	public void setPerson_email(String person_email){
		this.setInternal("PERSON_EMAIL",person_email);
	}
	public String getPerson_email(){
		return (String)this.getInternal("PERSON_EMAIL");
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