package com.ccthanking.business.projectaccept.vo;
import com.ccthanking.framework.base.BaseVO;
import java.util.Date;

public class PmYanShouDetailVO extends BaseVO{

	public PmYanShouDetailVO(){
		this.addField("YANSHOU_DETAIL_UID",OP_STRING|this.TP_PK);//
		this.addField("YANSHOU_UID",OP_STRING);//
		this.addField("XUHAO",OP_STRING);//序号
		this.addField("DETAIL_NAME",OP_STRING);//分项工程名称/检验批名称
		this.addField("DETAIL_NUMS",OP_STRING);//检验批数量/检验批容量
		this.addField("DETAIL_BUWEI",OP_STRING);//检验批部位、区段
		this.addField("DETAIL_JCJG",OP_STRING);//施工单位检查结果
		this.addField("DETAIL_YSJL",OP_STRING);//监理单位验收结论
		this.addField("CREATE_DATE",OP_DATE);//
		this.addField("CREATE_USER",OP_STRING);//
		this.addField("UPDATE_DATE",OP_DATE);//
		this.addField("UPDATE_USER",OP_STRING);//
		this.setFieldDateFormat("CREATE_DATE","yyyy-MM-dd HH:mm:ss");
		this.setFieldDateFormat("UPDATE_DATE","yyyy-MM-dd HH:mm:ss");
		this.setVOTableName("PM_YANSHOU_DETAIL");
	}

	public void setYanshou_detail_uid(String yanshou_detail_uid){
		this.setInternal("YANSHOU_DETAIL_UID",yanshou_detail_uid);
	}
	public String getYanshou_detail_uid(){
		return (String)this.getInternal("YANSHOU_DETAIL_UID");
	}
	public void setYanshou_uid(String yanshou_uid){
		this.setInternal("YANSHOU_UID",yanshou_uid);
	}
	public String getYanshou_uid(){
		return (String)this.getInternal("YANSHOU_UID");
	}
	public void setXuhao(String xuhao){
		this.setInternal("XUHAO",xuhao);
	}
	public String getXuhao(){
		return (String)this.getInternal("XUHAO");
	}
	public void setDetail_name(String detail_name){
		this.setInternal("DETAIL_NAME",detail_name);
	}
	public String getDetail_name(){
		return (String)this.getInternal("DETAIL_NAME");
	}
	public void setDetail_nums(String detail_nums){
		this.setInternal("DETAIL_NUMS",detail_nums);
	}
	public String getDetail_nums(){
		return (String)this.getInternal("DETAIL_NUMS");
	}
	public void setDetail_buwei(String detail_buwei){
		this.setInternal("DETAIL_BUWEI",detail_buwei);
	}
	public String getDetail_buwei(){
		return (String)this.getInternal("DETAIL_BUWEI");
	}
	public void setDetail_jcjg(String detail_jcjg){
		this.setInternal("DETAIL_JCJG",detail_jcjg);
	}
	public String getDetail_jcjg(){
		return (String)this.getInternal("DETAIL_JCJG");
	}
	public void setDetail_ysjl(String detail_ysjl){
		this.setInternal("DETAIL_YSJL",detail_ysjl);
	}
	public String getDetail_ysjl(){
		return (String)this.getInternal("DETAIL_YSJL");
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