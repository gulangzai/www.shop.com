package com.ccthanking.business.projectdoc.vo;
import com.ccthanking.framework.base.BaseVO;
import java.util.Date;

public class PmProjectDocsVO extends BaseVO{

	public PmProjectDocsVO(){
		this.addField("PROJECT_DOCS_UID",OP_STRING|this.TP_PK);//
		this.addField("PROJECT_UID",OP_STRING);//
		this.addField("P_DOCS_UID",OP_STRING);//
		this.addField("NODE_TYPE",OP_STRING);//节点类型，DIR－文件夹；DOC－文档
		this.addField("NODE_NAME",OP_STRING);//节点名称
		this.addField("CREATE_DATE",OP_DATE);//
		this.addField("CREATE_USER",OP_STRING);//
		this.addField("UPDATE_DATE",OP_DATE);//
		this.addField("UPDATE_USER",OP_STRING);//
		this.setFieldDateFormat("CREATE_DATE","yyyy-MM-dd HH:mm:ss");
		this.setFieldDateFormat("UPDATE_DATE","yyyy-MM-dd HH:mm:ss");
		this.setVOTableName("PM_PROJECT_DOCS");
	}

	public void setProject_docs_uid(String project_docs_uid){
		this.setInternal("PROJECT_DOCS_UID",project_docs_uid);
	}
	public String getProject_docs_uid(){
		return (String)this.getInternal("PROJECT_DOCS_UID");
	}
	public void setProject_uid(String project_uid){
		this.setInternal("PROJECT_UID",project_uid);
	}
	public String getProject_uid(){
		return (String)this.getInternal("PROJECT_UID");
	}
	public void setP_docs_uid(String p_docs_uid){
		this.setInternal("P_DOCS_UID",p_docs_uid);
	}
	public String getP_docs_uid(){
		return (String)this.getInternal("P_DOCS_UID");
	}
	public void setNode_type(String node_type){
		this.setInternal("NODE_TYPE",node_type);
	}
	public String getNode_type(){
		return (String)this.getInternal("NODE_TYPE");
	}
	public void setNode_name(String node_name){
		this.setInternal("NODE_NAME",node_name);
	}
	public String getNode_name(){
		return (String)this.getInternal("NODE_NAME");
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