package com.ccthanking.business.pmxianchang.vo;
import com.ccthanking.framework.base.BaseVO;
import java.util.Date;

/**对项目状况答复的表(包含2中类型的答复：发布人本身的答复0 其他人的答复1**/
public class PmXianchangDafuVO extends BaseVO{

	public PmXianchangDafuVO(){
		this.addField("XIANCHANG_DAFU_UID",OP_STRING|this.TP_PK);//
		this.addField("XIANCHANG_UID",OP_STRING);//
		this.addField("XUHAO",OP_STRING);//序号
		this.addField("DAFU_TYPE",OP_STRING);//类型：1－答复；0－发布人的回复
		this.addField("CONTENT",OP_STRING);//答复的内容
		this.addField("CLOSE_Y",OP_STRING);//是否关闭，Y为关闭
		this.addField("CREATE_USER",OP_STRING);//
		this.addField("CREATE_DATE",OP_DATE);//
		this.addField("UPDATE_DATE",OP_DATE);//
		this.setFieldDateFormat("CREATE_DATE","yyyy-MM-dd HH:mm:ss");
		this.setFieldDateFormat("UPDATE_DATE","yyyy-MM-dd HH:mm:ss");
		this.setVOTableName("PM_XIANCHANG_DAFU");
	}

	public void setXianchang_dafu_uid(String xianchang_dafu_uid){
		this.setInternal("XIANCHANG_DAFU_UID",xianchang_dafu_uid);
	}
	public String getXianchang_dafu_uid(){
		return (String)this.getInternal("XIANCHANG_DAFU_UID");
	}
	public void setXianchang_uid(String xianchang_uid){
		this.setInternal("XIANCHANG_UID",xianchang_uid);
	}
	public String getXianchang_uid(){
		return (String)this.getInternal("XIANCHANG_UID");
	}
	public void setXuhao(String xuhao){
		this.setInternal("XUHAO",xuhao);
	}
	public String getXuhao(){
		return (String)this.getInternal("XUHAO");
	}
	public void setDafu_type(String dafu_type){
		this.setInternal("DAFU_TYPE",dafu_type);
	}
	public String getDafu_type(){
		return (String)this.getInternal("DAFU_TYPE");
	}
	public void setContent(String content){
		this.setInternal("CONTENT",content);
	}
	public String getContent(){
		return (String)this.getInternal("CONTENT");
	}
	public void setClose_y(String close_y){
		this.setInternal("CLOSE_Y",close_y);
	}
	public String getClose_y(){
		return (String)this.getInternal("CLOSE_Y");
	}
	public void setCreate_user(String create_user){
		this.setInternal("CREATE_USER",create_user);
	}
	public String getCreate_user(){
		return (String)this.getInternal("CREATE_USER");
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