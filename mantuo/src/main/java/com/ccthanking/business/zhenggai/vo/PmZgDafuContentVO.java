package com.ccthanking.business.zhenggai.vo;
import com.ccthanking.framework.base.BaseVO;
import java.util.Date;

public class PmZgDafuContentVO extends BaseVO{

	public PmZgDafuContentVO(){
		this.addField("ZG_DAFU_CONTENT_UID",OP_STRING|this.TP_PK);//
		this.addField("ZG_DAFU_UID",OP_STRING);//
		this.addField("ZHENGGAI_CONTENT_UID",OP_STRING);//
		this.addField("DAFU_CONTENT",OP_STRING);//答复内容
		this.setVOTableName("PM_ZG_DAFU_CONTENT");
	}

	public void setZg_dafu_content_uid(String zg_dafu_content_uid){
		this.setInternal("ZG_DAFU_CONTENT_UID",zg_dafu_content_uid);
	}
	public String getZg_dafu_content_uid(){
		return (String)this.getInternal("ZG_DAFU_CONTENT_UID");
	}
	public void setZg_dafu_uid(String zg_dafu_uid){
		this.setInternal("ZG_DAFU_UID",zg_dafu_uid);
	}
	public String getZg_dafu_uid(){
		return (String)this.getInternal("ZG_DAFU_UID");
	}
	public void setZhenggai_content_uid(String zhenggai_content_uid){
		this.setInternal("ZHENGGAI_CONTENT_UID",zhenggai_content_uid);
	}
	public String getZhenggai_content_uid(){
		return (String)this.getInternal("ZHENGGAI_CONTENT_UID");
	}
	public void setDafu_content(String dafu_content){
		this.setInternal("DAFU_CONTENT",dafu_content);
	}
	public String getDafu_content(){
		return (String)this.getInternal("DAFU_CONTENT");
	}
}