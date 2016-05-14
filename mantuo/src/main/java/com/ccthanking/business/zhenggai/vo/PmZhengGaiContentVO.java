package com.ccthanking.business.zhenggai.vo;
import com.ccthanking.framework.base.BaseVO;
import java.util.Date;

public class PmZhengGaiContentVO extends BaseVO{

	public PmZhengGaiContentVO(){
		this.addField("ZHENGGAI_CONTENT_UID",OP_STRING|this.TP_PK);//
		this.addField("ZHENGGAI_UID",OP_STRING);//
		this.addField("BZGF_UID",OP_STRING);//对应的标准规范UID，空则表示手动添加的违规事件
		this.addField("XUHAO",OP_STRING);//序号
		this.addField("WEIGUI_LEVEL",OP_STRING);//违规等级：1－一般；2－较严重；3－严重
		this.addField("CONTENT",OP_STRING);//违规事件内容
		this.addField("DESCRIPTION",OP_STRING);//具体的违规内容描述
		this.setVOTableName("PM_ZHENGGAI_CONTENT");
	}

	public void setZhenggai_content_uid(String zhenggai_content_uid){
		this.setInternal("ZHENGGAI_CONTENT_UID",zhenggai_content_uid);
	}
	public String getZhenggai_content_uid(){
		return (String)this.getInternal("ZHENGGAI_CONTENT_UID");
	}
	public void setZhenggai_uid(String zhenggai_uid){
		this.setInternal("ZHENGGAI_UID",zhenggai_uid);
	}
	public String getZhenggai_uid(){
		return (String)this.getInternal("ZHENGGAI_UID");
	}
	public void setBzgf_uid(String bzgf_uid){
		this.setInternal("BZGF_UID",bzgf_uid);
	}
	public String getBzgf_uid(){
		return (String)this.getInternal("BZGF_UID");
	}
	public void setXuhao(String xuhao){
		this.setInternal("XUHAO",xuhao);
	}
	public String getXuhao(){
		return (String)this.getInternal("XUHAO");
	}
	public void setWeigui_level(String weigui_level){
		this.setInternal("WEIGUI_LEVEL",weigui_level);
	}
	public String getWeigui_level(){
		return (String)this.getInternal("WEIGUI_LEVEL");
	}
	public void setContent(String content){
		this.setInternal("CONTENT",content);
	}
	public String getContent(){
		return (String)this.getInternal("CONTENT");
	}
	public void setDescription(String description){
		this.setInternal("DESCRIPTION",description);
	}
	public String getDescription(){
		return (String)this.getInternal("DESCRIPTION");
	}
}