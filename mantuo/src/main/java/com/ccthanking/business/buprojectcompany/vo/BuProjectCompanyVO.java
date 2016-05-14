package com.ccthanking.business.buprojectcompany.vo;
import com.ccthanking.framework.base.BaseVO;
import java.util.Date;

public class BuProjectCompanyVO extends BaseVO{

	public BuProjectCompanyVO(){
		this.addField("PROJECT_COMPANY_UID",OP_STRING|this.TP_PK);//
		this.addField("PROJECT_UID",OP_STRING);//
		this.addField("COMPANY_UID",OP_STRING);//
		this.addField("COMPANY_TYPE",OP_STRING);//项目中的企业类型，JS、SG、JL等
		this.addField("COMPANY_NAME",OP_STRING);//企业名称
		this.addField("PROJECT_FUZEREN",OP_STRING);//项目负责人
		this.addField("PROJCET_FUZEREN_TEL",OP_STRING);//项目负责人联系方式
		this.setVOTableName("BU_PROJECT_COMPANY");
	}

	public void setProject_company_uid(String project_company_uid){
		this.setInternal("PROJECT_COMPANY_UID",project_company_uid);
	}
	public String getProject_company_uid(){
		return (String)this.getInternal("PROJECT_COMPANY_UID");
	}
	public void setProject_uid(String project_uid){
		this.setInternal("PROJECT_UID",project_uid);
	}
	public String getProject_uid(){
		return (String)this.getInternal("PROJECT_UID");
	}
	public void setCompany_uid(String company_uid){
		this.setInternal("COMPANY_UID",company_uid);
	}
	public String getCompany_uid(){
		return (String)this.getInternal("COMPANY_UID");
	}
	public void setCompany_type(String company_type){
		this.setInternal("COMPANY_TYPE",company_type);
	}
	public String getCompany_type(){
		return (String)this.getInternal("COMPANY_TYPE");
	}
	public void setCompany_name(String company_name){
		this.setInternal("COMPANY_NAME",company_name);
	}
	public String getCompany_name(){
		return (String)this.getInternal("COMPANY_NAME");
	}
	public void setProject_fuzeren(String project_fuzeren){
		this.setInternal("PROJECT_FUZEREN",project_fuzeren);
	}
	public String getProject_fuzeren(){
		return (String)this.getInternal("PROJECT_FUZEREN");
	}
	public void setProjcet_fuzeren_tel(String projcet_fuzeren_tel){
		this.setInternal("PROJCET_FUZEREN_TEL",projcet_fuzeren_tel);
	}
	public String getProjcet_fuzeren_tel(){
		return (String)this.getInternal("PROJCET_FUZEREN_TEL");
	}
}