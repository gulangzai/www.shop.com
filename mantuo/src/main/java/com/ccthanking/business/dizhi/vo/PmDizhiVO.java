package com.ccthanking.business.dizhi.vo;
import com.ccthanking.framework.base.BaseVO;
import java.util.Date;

public class PmDizhiVO extends BaseVO{

	public PmDizhiVO(){
		this.addField("DIZHI_UID",OP_STRING|this.TP_PK);//
		this.addField("PROJECT_UID",OP_STRING);//
		this.addField("XUHAO",OP_STRING);//序号
		this.addField("TUCENG_NUM",OP_STRING);//土层层号
		this.addField("TUCENG_NAME",OP_STRING);//土层名称
		this.addField("DEEP",OP_STRING);//层顶深
		this.addField("HEIGHT",OP_STRING);//层顶标高
		this.addField("WEIGHT",OP_STRING);//重度
		this.addField("CREATE_DATE",OP_DATE);//
		this.addField("CREATE_USER",OP_STRING);//
		this.setFieldDateFormat("CREATE_DATE","yyyy-MM-dd HH:mm:ss");
		this.setVOTableName("PM_DIZHI");
	}

	public void setDizhi_uid(String dizhi_uid){
		this.setInternal("DIZHI_UID",dizhi_uid);
	}
	public String getDizhi_uid(){
		return (String)this.getInternal("DIZHI_UID");
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
	public void setTuceng_num(String tuceng_num){
		this.setInternal("TUCENG_NUM",tuceng_num);
	}
	public String getTuceng_num(){
		return (String)this.getInternal("TUCENG_NUM");
	}
	public void setTuceng_name(String tuceng_name){
		this.setInternal("TUCENG_NAME",tuceng_name);
	}
	public String getTuceng_name(){
		return (String)this.getInternal("TUCENG_NAME");
	}
	public void setDeep(String deep){
		this.setInternal("DEEP",deep);
	}
	public String getDeep(){
		return (String)this.getInternal("DEEP");
	}
	public void setHeight(String height){
		this.setInternal("HEIGHT",height);
	}
	public String getHeight(){
		return (String)this.getInternal("HEIGHT");
	}
	public void setWeight(String weight){
		this.setInternal("WEIGHT",weight);
	}
	public String getWeight(){
		return (String)this.getInternal("WEIGHT");
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