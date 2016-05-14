package com.ccthanking.business.pmbzjc.vo;
import com.ccthanking.framework.base.BaseVO;
import java.util.Date;

public class PmBzjcVO extends BaseVO{

	public PmBzjcVO(){
		this.addField("BZJC_UID",OP_STRING|this.TP_PK);//
		this.addField("P_BZJC_UID",OP_STRING);//
		this.addField("PROJECT_UID",OP_STRING);//
		this.addField("LEVEL",OP_STRING);//层次
		this.addField("XUHAO",OP_STRING);//同一层的序号
		this.addField("JC_TYPE",OP_STRING);//检查类型：BZ－标准检查；FX－飞行检查
		this.addField("BZJC_NAME",OP_STRING);//检查名称
		this.addField("PLAN_JC_DATE",OP_DATE);//计划检查日期
		this.addField("JC_ZHIBIAO",OP_STRING);//检查指标
		this.addField("JC_SSRY",OP_STRING);//实施人员
		this.addField("JC_BIAOZHUN",OP_STRING);//检查标准
		this.addField("JC_DZMS",OP_STRING);//检查动作描述
		this.addField("JC_CHENGGUO",OP_STRING);//检查成果
		this.addField("DESCRIPTION",OP_STRING);//备注
		this.addField("JC_END_DATE",OP_DATE);//实际检查完成日期
		this.addField("JC_RESULT",OP_STRING);//检查结果
		this.addField("JC_STATUS",OP_STRING);//检查状态，参见数据字典，默认为未检查
		this.addField("CREATE_DATE",OP_DATE);//
		this.addField("CREATE_USER",OP_STRING);//
		this.addField("UPDATE_DATE",OP_DATE);//
		this.addField("UPDATE_USER",OP_STRING);//
		this.setFieldDateFormat("PLAN_JC_DATE","yyyy-MM-dd");
		this.setFieldDateFormat("JC_END_DATE","yyyy-MM-dd");
		this.setFieldDateFormat("CREATE_DATE","yyyy-MM-dd HH:mm:ss");
		this.setFieldDateFormat("UPDATE_DATE","yyyy-MM-dd HH:mm:ss");
		this.setVOTableName("PM_BZJC");
	}

	public void setBzjc_uid(String bzjc_uid){
		this.setInternal("BZJC_UID",bzjc_uid);
	}
	public String getBzjc_uid(){
		return (String)this.getInternal("BZJC_UID");
	}
	public void setP_bzjc_uid(String p_bzjc_uid){
		this.setInternal("P_BZJC_UID",p_bzjc_uid);
	}
	public String getP_bzjc_uid(){
		return (String)this.getInternal("P_BZJC_UID");
	}
	public void setProject_uid(String project_uid){
		this.setInternal("PROJECT_UID",project_uid);
	}
	public String getProject_uid(){
		return (String)this.getInternal("PROJECT_UID");
	}
	public void setLevel(String level){
		this.setInternal("LEVEL",level);
	}
	public String getLevel(){
		return (String)this.getInternal("LEVEL");
	}
	public void setXuhao(String xuhao){
		this.setInternal("XUHAO",xuhao);
	}
	public String getXuhao(){
		return (String)this.getInternal("XUHAO");
	}
	public void setJc_type(String jc_type){
		this.setInternal("JC_TYPE",jc_type);
	}
	public String getJc_type(){
		return (String)this.getInternal("JC_TYPE");
	}
	public void setBzjc_name(String bzjc_name){
		this.setInternal("BZJC_NAME",bzjc_name);
	}
	public String getBzjc_name(){
		return (String)this.getInternal("BZJC_NAME");
	}
	public void setPlan_jc_date(Date plan_jc_date){
		this.setInternal("PLAN_JC_DATE",plan_jc_date);
	}
	public Date getPlan_jc_date(){
		return (Date)this.getInternal("PLAN_JC_DATE");
	}
	public void setJc_zhibiao(String jc_zhibiao){
		this.setInternal("JC_ZHIBIAO",jc_zhibiao);
	}
	public String getJc_zhibiao(){
		return (String)this.getInternal("JC_ZHIBIAO");
	}
	public void setJc_ssry(String jc_ssry){
		this.setInternal("JC_SSRY",jc_ssry);
	}
	public String getJc_ssry(){
		return (String)this.getInternal("JC_SSRY");
	}
	public void setJc_biaozhun(String jc_biaozhun){
		this.setInternal("JC_BIAOZHUN",jc_biaozhun);
	}
	public String getJc_biaozhun(){
		return (String)this.getInternal("JC_BIAOZHUN");
	}
	public void setJc_dzms(String jc_dzms){
		this.setInternal("JC_DZMS",jc_dzms);
	}
	public String getJc_dzms(){
		return (String)this.getInternal("JC_DZMS");
	}
	public void setJc_chengguo(String jc_chengguo){
		this.setInternal("JC_CHENGGUO",jc_chengguo);
	}
	public String getJc_chengguo(){
		return (String)this.getInternal("JC_CHENGGUO");
	}
	public void setDescription(String description){
		this.setInternal("DESCRIPTION",description);
	}
	public String getDescription(){
		return (String)this.getInternal("DESCRIPTION");
	}
	public void setJc_end_date(Date jc_end_date){
		this.setInternal("JC_END_DATE",jc_end_date);
	}
	public Date getJc_end_date(){
		return (Date)this.getInternal("JC_END_DATE");
	}
	public void setJc_result(String jc_result){
		this.setInternal("JC_RESULT",jc_result);
	}
	public String getJc_result(){
		return (String)this.getInternal("JC_RESULT");
	}
	public void setJc_status(String jc_status){
		this.setInternal("JC_STATUS",jc_status);
	}
	public String getJc_status(){
		return (String)this.getInternal("JC_STATUS");
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