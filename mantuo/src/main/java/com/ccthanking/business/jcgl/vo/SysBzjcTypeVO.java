package com.ccthanking.business.jcgl.vo;
import com.ccthanking.framework.base.BaseVO;
import java.util.Date;

public class SysBzjcTypeVO extends BaseVO{

	public SysBzjcTypeVO(){
		this.addField("BZJC_TYPE_UID",OP_STRING|this.TP_PK);//
		this.addField("TYPE_CODE",OP_STRING);//
		this.addField("TYPE_NAME",OP_STRING);//
		this.addField("SHIYONG",OP_STRING);//适用于
		this.addField("DESCRIPTION",OP_STRING);//备注
		this.addField("CREATE_DATE",OP_DATE);//创建时间
		this.addField("UPDATE_DATE",OP_DATE);//最后更新时间
		this.setFieldDateFormat("CREATE_DATE","yyyy-MM-dd HH:mm:ss");
		this.setFieldDateFormat("UPDATE_DATE","yyyy-MM-dd HH:mm:ss");
		this.setVOTableName("SYS_BZJC_TYPE");
		
	}

	public void setBzjc_type_uid(String bzjc_type_uid){
		this.setInternal("BZJC_TYPE_UID",bzjc_type_uid);
	}
	public String getBzjc_type_uid(){
		return (String)this.getInternal("BZJC_TYPE_UID");
	}
	public void setType_code(String type_code){
		this.setInternal("TYPE_CODE",type_code);
	}
	public String getType_code(){
		return (String)this.getInternal("TYPE_CODE");
	}
	public void setType_name(String type_name){
		this.setInternal("TYPE_NAME",type_name);
	}
	public String getType_name(){
		return (String)this.getInternal("TYPE_NAME");
	}
	public void setShiyong(String shiyong){
		this.setInternal("SHIYONG",shiyong);
	}
	public String getShiyong(){
		return (String)this.getInternal("SHIYONG");
	}
	public void setDescription(String description){
		this.setInternal("DESCRIPTION",description);
	}
	public String getDescription(){
		return (String)this.getInternal("DESCRIPTION");
	}
	public void setCreate_date(Date create_date){
		this.setInternal("CREATE_DATE",create_date);
	}
	public Date getCreate_date(){
		return (Date)this.getInternal("CREATE_DATE");
	}
	public void setUpdate_date(Date update_date){
		this.setInternal("UPDATE_DATE",update_date);
	}
	public Date getUpdate_date(){
		return (Date)this.getInternal("UPDATE_DATE");
	}
}