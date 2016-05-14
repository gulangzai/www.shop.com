package com.ccthanking.business.pmxianchang.vo;

import com.ccthanking.framework.base.BaseVO;
import java.util.Date;

public class PmXianchangVO extends BaseVO{

	public PmXianchangVO(){
		this.addField("XIANCHANG_UID",OP_STRING|this.TP_PK);//
		this.addField("PROJECT_UID",OP_STRING);//
		this.addField("GONGKUANG_TYPE",OP_STRING);//类型：质量(ZL)、安全(AQ)、进度(JD)、文明(WM)等
		this.addField("JINDU",OP_STRING);//现场进度
		this.addField("DESCRIPTION",OP_STRING);//现场状况描述
		this.addField("FINISH_DATE",OP_DATE);//要求完成日期
		this.addField("STATUS",OP_STRING);//现场状况状态：-1－草稿；0－处理中；1－关闭
		this.addField("ENABLED",OP_STRING);//是否有效，1－有效；0－无效
		this.addField("CREATE_DATE",OP_DATE);//
		this.addField("CREATE_USER",OP_STRING);//
		this.addField("CLOSE_DATE",OP_DATE);//
		this.addField("SERIOUSNESS",OP_STRING);//严重性 ：1-一般；2-较严重；3-严重
		this.setFieldDateFormat("FINISH_DATE","yyyy-MM-dd");
		this.setFieldDateFormat("CREATE_DATE","yyyy-MM-dd HH:mm:ss");
		this.setFieldDateFormat("CLOSE_DATE","yyyy-MM-dd HH:mm:ss");
		this.setVOTableName("PM_XIANCHANG");
	}

	public void setXianchang_uid(String xianchang_uid){
		this.setInternal("XIANCHANG_UID",xianchang_uid);
	}
	public String getXianchang_uid(){
		return (String)this.getInternal("XIANCHANG_UID");
	}
	public void setProject_uid(String project_uid){
		this.setInternal("PROJECT_UID",project_uid);
	}
	public String getProject_uid(){
		return (String)this.getInternal("PROJECT_UID");
	}
	public void setGongkuang_type(String gongkuang_type){
		this.setInternal("GONGKUANG_TYPE",gongkuang_type);
	}
	public String getGongkuang_type(){
		return (String)this.getInternal("GONGKUANG_TYPE");
	}
	public void setJindu(String jindu){
		this.setInternal("JINDU",jindu);
	}
	public String getJindu(){
		return (String)this.getInternal("JINDU");
	}
	public void setDescription(String description){
		this.setInternal("DESCRIPTION",description);
	}
	public String getDescription(){
		return (String)this.getInternal("DESCRIPTION");
	}
	public void setFinish_date(Date finish_date){
		this.setInternal("FINISH_DATE",finish_date);
	}
	public Date getFinish_date(){
		return (Date)this.getInternal("FINISH_DATE");
	}
	public void setStatus(String status){
		this.setInternal("STATUS",status);
	}
	public String getStatus(){
		return (String)this.getInternal("STATUS");
	}
	public void setEnabled(String enabled){
		this.setInternal("ENABLED",enabled);
	}
	public String getEnabled(){
		return (String)this.getInternal("ENABLED");
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
	public void setClose_date(Date close_date){
		this.setInternal("CLOSE_DATE",close_date);
	}
	public Date getClose_date(){
		return (Date)this.getInternal("CLOSE_DATE");
	}
	public void setSeriousness(String seriousness){
		this.setInternal("SERIOUSNESS",seriousness);
	}
	public String getSeriousness(){
		return (String)this.getInternal("SERIOUSNESS");
	}
}