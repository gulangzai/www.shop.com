package com.ccthanking.business.project.vo;
import com.ccthanking.framework.base.BaseVO;
import java.util.Date;

public class PmBiangengVO extends BaseVO{

	public PmBiangengVO(){
		this.addField("BIANGENG_UID",OP_STRING|this.TP_PK);//
		this.addField("PROJECT_UID",OP_STRING);//
		this.addField("TAGS",OP_STRING);//变更或签证：BG－变更；QZ－签证
		this.addField("BIANGENG_TYPE",OP_STRING);//变更类型
		this.addField("BIANGENG_DATE",OP_DATE);//变更或签证日期
		this.addField("BIANGENG_COMPANY",OP_STRING);//变更或签证发起单位
		this.addField("REASON",OP_STRING);//变更或签证理由
		this.addField("AMOUNT",OP_STRING);//变更或签证金额
		this.addField("IMPORTANT",OP_STRING);//是否为重大变更，Y为是
		this.addField("STATUS",OP_STRING);//变更或签证状态，-1－草稿；0－流转中；1－流转完成
		this.addField("CREATE_DATE",OP_DATE);//
		this.addField("CREATE_USER",OP_STRING);//
		this.addField("UPDATE_DATE",OP_DATE);//
		this.addField("UPDATE_USER",OP_STRING);//
		this.setFieldDateFormat("BIANGENG_DATE","yyyy-MM-dd");
		this.setFieldDateFormat("CREATE_DATE","yyyy-MM-dd HH:mm:ss");
		this.setFieldDateFormat("UPDATE_DATE","yyyy-MM-dd HH:mm:ss");
		this.setVOTableName("PM_BIANGENG");
	}

	public void setBiangeng_uid(String biangeng_uid){
		this.setInternal("BIANGENG_UID",biangeng_uid);
	}
	public String getBiangeng_uid(){
		return (String)this.getInternal("BIANGENG_UID");
	}
	public void setProject_uid(String project_uid){
		this.setInternal("PROJECT_UID",project_uid);
	}
	public String getProject_uid(){
		return (String)this.getInternal("PROJECT_UID");
	}
	public void setTags(String tags){
		this.setInternal("TAGS",tags);
	}
	public String getTags(){
		return (String)this.getInternal("TAGS");
	}
	public void setBiangeng_type(String biangeng_type){
		this.setInternal("BIANGENG_TYPE",biangeng_type);
	}
	public String getBiangeng_type(){
		return (String)this.getInternal("BIANGENG_TYPE");
	}
	public void setBiangeng_date(Date biangeng_date){
		this.setInternal("BIANGENG_DATE",biangeng_date);
	}
	public Date getBiangeng_date(){
		return (Date)this.getInternal("BIANGENG_DATE");
	}
	public void setBiangeng_company(String biangeng_company){
		this.setInternal("BIANGENG_COMPANY",biangeng_company);
	}
	public String getBiangeng_company(){
		return (String)this.getInternal("BIANGENG_COMPANY");
	}
	public void setReason(String reason){
		this.setInternal("REASON",reason);
	}
	public String getReason(){
		return (String)this.getInternal("REASON");
	}
	public void setAmount(String amount){
		this.setInternal("AMOUNT",amount);
	}
	public String getAmount(){
		return (String)this.getInternal("AMOUNT");
	}
	public void setImportant(String important){
		this.setInternal("IMPORTANT",important);
	}
	public String getImportant(){
		return (String)this.getInternal("IMPORTANT");
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
	public void setUpdate_user(String update_user){
		this.setInternal("UPDATE_USER",update_user);
	}
	public String getUpdate_user(){
		return (String)this.getInternal("UPDATE_USER");
	}
}