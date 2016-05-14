package com.ccthanking.business.pmjiancha.vo;
import com.ccthanking.framework.base.BaseVO;
import java.util.Date;

public class PmBzgfVO extends BaseVO{

	public PmBzgfVO(){
		this.addField("BZGF_UID",OP_STRING|this.TP_PK);//
		this.addField("P_BZGF_UID",OP_STRING);//
		this.addField("PROJECT_UID",OP_STRING);//
		this.addField("COMPONENT_TYPE_UID",OP_STRING);//
		this.addField("BZ_LEVEL",OP_STRING);//标准或规范级别：GJ－国家标准；HY－行业标准；QY－企业标准；XM－项目标准
		this.addField("NODE_TYPE",OP_STRING);//节点类型：GF－规范；FL－分类；SJ－事件
		this.addField("WEIGUI_LEVEL",OP_STRING);//
		this.addField("NODE_CONTENT",OP_STRING);//违规内容
		this.addField("CREATE_DATE",OP_DATE);//
		this.addField("CREATE_USER",OP_STRING);//
		this.addField("UPDATE_DATE",OP_DATE);//
		this.addField("UPDATE_USER",OP_STRING);//
		this.setFieldDateFormat("CREATE_DATE","yyyy-MM-dd HH:mm:ss");
		this.setFieldDateFormat("UPDATE_DATE","yyyy-MM-dd HH:mm:ss");
		this.setVOTableName("PM_BZGF");
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
	public void setProject_uid(String project_uid){
		this.setInternal("PROJECT_UID",project_uid);
	}
	public String getProject_uid(){
		return (String)this.getInternal("PROJECT_UID");
	}
	public void setComponent_type_uid(String component_type_uid){
		this.setInternal("COMPONENT_TYPE_UID",component_type_uid);
	}
	public String getComponent_type_uid(){
		return (String)this.getInternal("COMPONENT_TYPE_UID");
	}
	public void setBz_level(String bz_level){
		this.setInternal("BZ_LEVEL",bz_level);
	}
	public String getBz_level(){
		return (String)this.getInternal("BZ_LEVEL");
	}
	public void setNode_type(String node_type){
		this.setInternal("NODE_TYPE",node_type);
	}
	public String getNode_type(){
		return (String)this.getInternal("NODE_TYPE");
	}
	public void setWeigui_level(String weigui_level){
		this.setInternal("WEIGUI_LEVEL",weigui_level);
	}
	public String getWeigui_level(){
		return (String)this.getInternal("WEIGUI_LEVEL");
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