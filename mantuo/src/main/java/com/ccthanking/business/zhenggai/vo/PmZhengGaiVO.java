package com.ccthanking.business.zhenggai.vo;
import com.ccthanking.framework.base.BaseVO;
import java.util.Date;

public class PmZhengGaiVO extends BaseVO{

	public PmZhengGaiVO(){
		this.addField("ZHENGGAI_UID",OP_STRING|this.TP_PK);//
		this.addField("PROJECT_UID",OP_STRING);//
		this.addField("XUNJIAN_UID",OP_STRING);//巡查UID，空则表示直接开整改单
		this.addField("JIANCHA_LEVEL_UID",OP_STRING);//检查级别：抽查、日常巡检、上级部门检查等
		this.addField("ZG_CODE",OP_STRING);//整改单编号
		this.addField("ZG_TYPE",OP_STRING);//整改类型：1－限期整改；2－局部停工；3－全面停工
		this.addField("JINDU",OP_STRING);//现场进度
		this.addField("SGDW",OP_STRING);//施工单位
		this.addField("JLDW",OP_STRING);//监理单位
		this.addField("XMJL",OP_STRING);//项目经理
		this.addField("ZJ",OP_STRING);//总监
		this.addField("FAFANG_CORP",OP_STRING);//发放单位
		this.addField("FAFANG_USER",OP_STRING);//发放人
		this.addField("FAFANG_DATE",OP_DATE);//整改单发放日期
		this.addField("ZG_DATE",OP_DATE);//要求整改完成日期
		this.addField("CANJIAN",OP_STRING);//参检人员
		this.addField("STATUS",OP_STRING);//状态：-1－草稿；0－整改中；1－关闭；
		this.addField("ENABLED",OP_STRING);//是否有效，1－有效；0－无效
		this.addField("CREATE_DATE",OP_DATE);//
		this.addField("CREATE_USER",OP_STRING);//
		this.addField("UPDATE_DATE",OP_DATE);//
		this.addField("UPDATE_USER",OP_STRING);//
		this.setFieldDateFormat("FAFANG_DATE","yyyy-MM-dd HH:mm:ss");
		this.setFieldDateFormat("ZG_DATE","yyyy-MM-dd HH:mm:ss");
		this.setFieldDateFormat("CREATE_DATE","yyyy-MM-dd HH:mm:ss");
		this.setFieldDateFormat("UPDATE_DATE","yyyy-MM-dd HH:mm:ss");
		this.setVOTableName("PM_ZHENGGAI");
	}

	public void setZhenggai_uid(String zhenggai_uid){
		this.setInternal("ZHENGGAI_UID",zhenggai_uid);
	}
	public String getZhenggai_uid(){
		return (String)this.getInternal("ZHENGGAI_UID");
	}
	public void setProject_uid(String project_uid){
		this.setInternal("PROJECT_UID",project_uid);
	}
	public String getProject_uid(){
		return (String)this.getInternal("PROJECT_UID");
	}
	public void setXunjian_uid(String xunjian_uid){
		this.setInternal("XUNJIAN_UID",xunjian_uid);
	}
	public String getXunjian_uid(){
		return (String)this.getInternal("XUNJIAN_UID");
	}
	public void setJiancha_level_uid(String jiancha_level_uid){
		this.setInternal("JIANCHA_LEVEL_UID",jiancha_level_uid);
	}
	public String getJiancha_level_uid(){
		return (String)this.getInternal("JIANCHA_LEVEL_UID");
	}
	public void setZg_code(String zg_code){
		this.setInternal("ZG_CODE",zg_code);
	}
	public String getZg_code(){
		return (String)this.getInternal("ZG_CODE");
	}
	public void setZg_type(String zg_type){
		this.setInternal("ZG_TYPE",zg_type);
	}
	public String getZg_type(){
		return (String)this.getInternal("ZG_TYPE");
	}
	public void setJindu(String jindu){
		this.setInternal("JINDU",jindu);
	}
	public String getJindu(){
		return (String)this.getInternal("JINDU");
	}
	public void setSgdw(String sgdw){
		this.setInternal("SGDW",sgdw);
	}
	public String getSgdw(){
		return (String)this.getInternal("SGDW");
	}
	public void setJldw(String jldw){
		this.setInternal("JLDW",jldw);
	}
	public String getJldw(){
		return (String)this.getInternal("JLDW");
	}
	public void setXmjl(String xmjl){
		this.setInternal("XMJL",xmjl);
	}
	public String getXmjl(){
		return (String)this.getInternal("XMJL");
	}
	public void setZj(String zj){
		this.setInternal("ZJ",zj);
	}
	public String getZj(){
		return (String)this.getInternal("ZJ");
	}
	public void setFafang_corp(String fafang_corp){
		this.setInternal("FAFANG_CORP",fafang_corp);
	}
	public String getFafang_corp(){
		return (String)this.getInternal("FAFANG_CORP");
	}
	public void setFafang_user(String fafang_user){
		this.setInternal("FAFANG_USER",fafang_user);
	}
	public String getFafang_user(){
		return (String)this.getInternal("FAFANG_USER");
	}
	public void setFafang_date(Date fafang_date){
		this.setInternal("FAFANG_DATE",fafang_date);
	}
	public Date getFafang_date(){
		return (Date)this.getInternal("FAFANG_DATE");
	}
	public void setZg_date(Date zg_date){
		this.setInternal("ZG_DATE",zg_date);
	}
	public Date getZg_date(){
		return (Date)this.getInternal("ZG_DATE");
	}
	public void setCanjian(String canjian){
		this.setInternal("CANJIAN",canjian);
	}
	public String getCanjian(){
		return (String)this.getInternal("CANJIAN");
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