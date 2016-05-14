package com.ccthanking.business.bucompany.vo;
import com.ccthanking.framework.base.BaseVO;
import java.util.Date;

public class BuCompanyVO extends BaseVO{

	public BuCompanyVO(){
		this.addField("COMPANY_UID",OP_STRING|this.TP_PK);//
		this.addField("COMPANY_NAME",OP_STRING);//企业名称
		this.addField("COMPANY_CODE",OP_STRING);//企业组织机构代码
		this.addField("ADDRESS",OP_STRING);//地址
		this.addField("URL",OP_STRING);//网站
		this.addField("CREATED_DATE",OP_DATE);//
		this.addField("LIANXI_REN",OP_STRING);//联系人
		this.addField("LIANXI_TEL",OP_STRING);//联系电话
		this.setFieldDateFormat("CREATED_DATE","yyyy-MM-dd HH:mm:ss");
		this.setVOTableName("BU_COMPANY");
	}

	public void setCompany_uid(String company_uid){
		this.setInternal("COMPANY_UID",company_uid);
	}
	public String getCompany_uid(){
		return (String)this.getInternal("COMPANY_UID");
	}
	public void setCompany_name(String company_name){
		this.setInternal("COMPANY_NAME",company_name);
	}
	public String getCompany_name(){
		return (String)this.getInternal("COMPANY_NAME");
	}
	public void setCompany_code(String company_code){
		this.setInternal("COMPANY_CODE",company_code);
	}
	public String getCompany_code(){
		return (String)this.getInternal("COMPANY_CODE");
	}
	public void setAddress(String address){
		this.setInternal("ADDRESS",address);
	}
	public String getAddress(){
		return (String)this.getInternal("ADDRESS");
	}
	public void setUrl(String url){
		this.setInternal("URL",url);
	}
	public String getUrl(){
		return (String)this.getInternal("URL");
	}
	public void setCreated_date(Date created_date){
		this.setInternal("CREATED_DATE",created_date);
	}
	public Date getCreated_date(){
		return (Date)this.getInternal("CREATED_DATE");
	}
	public void setLianxi_ren(String lianxi_ren){
		this.setInternal("LIANXI_REN",lianxi_ren);
	}
	public String getLianxi_ren(){
		return (String)this.getInternal("LIANXI_REN");
	}
	public void setLianxi_tel(String lianxi_tel){
		this.setInternal("LIANXI_TEL",lianxi_tel);
	}
	public String getLianxi_tel(){
		return (String)this.getInternal("LIANXI_TEL");
	}
}