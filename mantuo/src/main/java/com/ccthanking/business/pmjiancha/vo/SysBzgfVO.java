package com.ccthanking.business.pmjiancha.vo;
import com.ccthanking.framework.base.BaseVO;
import java.util.Date;

public class SysBzgfVO extends BaseVO{

	public SysBzgfVO(){
		this.addField("BZGF_UID",OP_STRING|this.TP_PK);//
		this.addField("P_BZGF_UID",OP_STRING);//
		this.addField("NODE_TYPE",OP_STRING);//节点类型：GF－规范；LB－类别；TL－条例
		this.addField("NODE_CONTENT",OP_STRING);//节点内容
		this.addField("CREATE_DATE",OP_DATE);//创建时间
		this.addField("UPDATE_DATE",OP_DATE);//最后更新时间
		this.setFieldDateFormat("CREATE_DATE","yyyy-MM-dd HH:mm:ss");
		this.setFieldDateFormat("UPDATE_DATE","yyyy-MM-dd HH:mm:ss");
		this.setVOTableName("SYS_BZGF");
	}

	public void setBzgf_uid(String bzgf_uid){
		this.setInternal("BZGF_UID",bzgf_uid);
	}
	public String getBzgf_uid(){
		return (String)this.getInternal("BZGF_UID");
	}
	public void setP_bzgf_uid(String p_bzgf_uid){
		this.setInternal("P_BZGF_UID",p_bzgf_uid);
	}
	public String getP_bzgf_uid(){
		return (String)this.getInternal("P_BZGF_UID");
	}
	public void setNode_type(String node_type){
		this.setInternal("NODE_TYPE",node_type);
	}
	public String getNode_type(){
		return (String)this.getInternal("NODE_TYPE");
	}
	public void setNode_content(String node_content){
		this.setInternal("NODE_CONTENT",node_content);
	}
	public String getNode_content(){
		return (String)this.getInternal("NODE_CONTENT");
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