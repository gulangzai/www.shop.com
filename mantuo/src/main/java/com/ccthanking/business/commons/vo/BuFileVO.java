package com.ccthanking.business.commons.vo;
import com.ccthanking.framework.base.BaseVO;
import java.util.Date;

public class BuFileVO extends BaseVO{

	public BuFileVO(){
		this.addField("FILE_UID",OP_STRING|this.TP_PK);//
		this.addField("TARGET_TABLE",OP_STRING);//附件所属对象表名
		this.addField("TARGET_COL",OP_STRING);//附件所属对象列名
		this.addField("TARGET_UID",OP_STRING);//附件所属对象ID
		this.addField("FILE_TYPE",OP_STRING);//附件类别，详见附件类别清单
		this.addField("FILE_TYPE_NAME",OP_STRING);//附件类别名称
		this.addField("DESCRIPTION",OP_STRING);//附件的描述
		this.addField("ATTACHMENT_UID",OP_STRING);//附件ID
		this.addField("ENABLED",OP_STRING);//是否有效，1－有效；0－无效
		this.addField("CREATE_DATE",OP_DATE);//
		this.addField("CREATE_USER",OP_STRING);//
		this.setFieldDateFormat("CREATE_DATE","yyyy-MM-dd HH:mm:ss");
		this.setVOTableName("BU_FILE");
	}

	public void setFile_uid(String file_uid){
		this.setInternal("FILE_UID",file_uid);
	}
	public String getFile_uid(){
		return (String)this.getInternal("FILE_UID");
	}
	public void setTarget_table(String target_table){
		this.setInternal("TARGET_TABLE",target_table);
	}
	public String getTarget_table(){
		return (String)this.getInternal("TARGET_TABLE");
	}
	public void setTarget_col(String target_col){
		this.setInternal("TARGET_COL",target_col);
	}
	public String getTarget_col(){
		return (String)this.getInternal("TARGET_COL");
	}
	public void setTarget_uid(String target_uid){
		this.setInternal("TARGET_UID",target_uid);
	}
	public String getTarget_uid(){
		return (String)this.getInternal("TARGET_UID");
	}
	public void setFile_type(String file_type){
		this.setInternal("FILE_TYPE",file_type);
	}
	public String getFile_type(){
		return (String)this.getInternal("FILE_TYPE");
	}
	public void setFile_type_name(String file_type_name){
		this.setInternal("FILE_TYPE_NAME",file_type_name);
	}
	public String getFile_type_name(){
		return (String)this.getInternal("FILE_TYPE_NAME");
	}
	public void setDescription(String description){
		this.setInternal("DESCRIPTION",description);
	}
	public String getDescription(){
		return (String)this.getInternal("DESCRIPTION");
	}
	public void setAttachment_uid(String attachment_uid){
		this.setInternal("ATTACHMENT_UID",attachment_uid);
	}
	public String getAttachment_uid(){
		return (String)this.getInternal("ATTACHMENT_UID");
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
}