package com.ccthanking.business.dizhi.vo;
import com.ccthanking.framework.base.BaseVO;
import java.util.Date;

public class PmDxsVO extends BaseVO{

	public PmDxsVO(){
		this.addField("DXS_UID",OP_STRING|this.TP_PK);//
		this.addField("PROJECT_UID",OP_STRING);//
		this.addField("XUHAO",OP_STRING);//序号
		this.addField("HEIGHT",OP_STRING);//水位标高
		this.addField("DEEP",OP_STRING);//水头埋深
		this.addField("CREATE_DATE",OP_DATE);//
		this.addField("CREATE_USER",OP_STRING);//
		this.setFieldDateFormat("CREATE_DATE","yyyy-MM-dd HH:mm:ss");
		this.setVOTableName("PM_DXS");
	}

	public void setDxs_uid(String dxs_uid){
		this.setInternal("DXS_UID",dxs_uid);
	}
	public String getDxs_uid(){
		return (String)this.getInternal("DXS_UID");
	}
	public void setProject_uid(String project_uid){
		this.setInternal("PROJECT_UID",project_uid);
	}
	public String getProject_uid(){
		return (String)this.getInternal("PROJECT_UID");
	}
	public void setXuhao(String xuhao){
		this.setInternal("XUHAO",xuhao);
	}
	public String getXuhao(){
		return (String)this.getInternal("XUHAO");
	}
	public void setHeight(String height){
		this.setInternal("HEIGHT",height);
	}
	public String getHeight(){
		return (String)this.getInternal("HEIGHT");
	}
	public void setDeep(String deep){
		this.setInternal("DEEP",deep);
	}
	public String getDeep(){
		return (String)this.getInternal("DEEP");
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
}