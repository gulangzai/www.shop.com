package com.ccthanking.business.commons.vo;
import com.ccthanking.framework.base.BaseVO;
import java.util.Date;

public class BuAttachmentVO extends BaseVO{

	public BuAttachmentVO(){
		this.addField("ATTACHMENT_UID",OP_STRING|this.TP_PK);//
		this.addField("FILE_NAME",OP_STRING);//文件名
		this.addField("FILE_EXT_NAME",OP_STRING);//文件扩展名
		this.addField("FILE_PATH",OP_STRING);//文件地址
		this.addField("MIME_TYPE",OP_STRING);//文件的MIME_TYPE
		this.addField("FILE_SIZE",OP_STRING);//文件大小（字节）
		this.addField("CREATED_DATE",OP_DATE);//
		this.addField("CREATED_USER",OP_STRING);//
		this.setFieldDateFormat("CREATED_DATE","yyyy-MM-dd HH:mm:ss");
		this.setVOTableName("BU_ATTACHMENT");
	}

	public void setAttachment_uid(String attachment_uid){
		this.setInternal("ATTACHMENT_UID",attachment_uid);
	}
	public String getAttachment_uid(){
		return (String)this.getInternal("ATTACHMENT_UID");
	}
	public void setFile_name(String file_name){
		this.setInternal("FILE_NAME",file_name);
	}
	public String getFile_name(){
		return (String)this.getInternal("FILE_NAME");
	}
	public void setFile_ext_name(String file_ext_name){
		this.setInternal("FILE_EXT_NAME",file_ext_name);
	}
	public String getFile_ext_name(){
		return (String)this.getInternal("FILE_EXT_NAME");
	}
	public void setFile_path(String file_path){
		this.setInternal("FILE_PATH",file_path);
	}
	public String getFile_path(){
		return (String)this.getInternal("FILE_PATH");
	}
	public void setMime_type(String mime_type){
		this.setInternal("MIME_TYPE",mime_type);
	}
	public String getMime_type(){
		return (String)this.getInternal("MIME_TYPE");
	}
	public void setFile_size(String file_size){
		this.setInternal("FILE_SIZE",file_size);
	}
	public String getFile_size(){
		return (String)this.getInternal("FILE_SIZE");
	}
	public void setCreated_date(Date created_date){
		this.setInternal("CREATED_DATE",created_date);
	}
	public Date getCreated_date(){
		return (Date)this.getInternal("CREATED_DATE");
	}
	public void setCreated_user(String created_user){
		this.setInternal("CREATED_USER",created_user);
	}
	public String getCreated_user(){
		return (String)this.getInternal("CREATED_USER");
	}
}