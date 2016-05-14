package com.ccthanking.business.pmscslweekplan.vo;
import com.ccthanking.framework.base.BaseVO;
import java.util.Date;

public class PmScslWeekPlanVO extends BaseVO{

	public PmScslWeekPlanVO(){
		this.addField("SCSL_WEEK_PLAN_UID",OP_STRING|this.TP_PK);//
		this.addField("PROJECT_UID",OP_STRING);//
		this.addField("PLAN_WEEK",OP_DATE);//计划检查周的周一
		this.addField("BUILDING_NUM",OP_STRING);//楼号
		this.addField("ROOM_NUM",OP_STRING);//户号
		this.addField("JC_NEIRONG",OP_STRING);//检查内容
		this.addField("JC_YAOQIU",OP_STRING);//检查要求
		this.addField("JC_BIAOZHUN",OP_STRING);//检查标准
		this.addField("JC_END_DATE",OP_DATE);//实际检查日期
		this.addField("JC_USER",OP_STRING);//检查人员
		this.addField("JC_RESULT",OP_STRING);//检查结果
		this.addField("JC_STATUS",OP_STRING);//检查状态，参见数据字典，默认为未检查
		this.addField("CREATE_DATE",OP_DATE);//
		this.addField("CREATE_USER",OP_STRING);//
		this.addField("UPDATE_DATE",OP_DATE);//
		this.addField("UPDATE_USER",OP_STRING);//
		this.setFieldDateFormat("PLAN_WEEK","yyyy-MM-dd");
		this.setFieldDateFormat("JC_END_DATE","yyyy-MM-dd");
		this.setFieldDateFormat("CREATE_DATE","yyyy-MM-dd HH:mm:ss");
		this.setFieldDateFormat("UPDATE_DATE","yyyy-MM-dd HH:mm:ss");
		this.setVOTableName("PM_SCSL_WEEK_PLAN");
	}

	public void setScsl_week_plan_uid(String scsl_week_plan_uid){
		this.setInternal("SCSL_WEEK_PLAN_UID",scsl_week_plan_uid);
	}
	public String getScsl_week_plan_uid(){
		return (String)this.getInternal("SCSL_WEEK_PLAN_UID");
	}
	public void setProject_uid(String project_uid){
		this.setInternal("PROJECT_UID",project_uid);
	}
	public String getProject_uid(){
		return (String)this.getInternal("PROJECT_UID");
	}
	public void setPlan_week(Date plan_week){
		this.setInternal("PLAN_WEEK",plan_week);
	}
	public Date getPlan_week(){
		return (Date)this.getInternal("PLAN_WEEK");
	}
	public void setBuilding_num(String building_num){
		this.setInternal("BUILDING_NUM",building_num);
	}
	public String getBuilding_num(){
		return (String)this.getInternal("BUILDING_NUM");
	}
	public void setRoom_num(String room_num){
		this.setInternal("ROOM_NUM",room_num);
	}
	public String getRoom_num(){
		return (String)this.getInternal("ROOM_NUM");
	}
	public void setJc_neirong(String jc_neirong){
		this.setInternal("JC_NEIRONG",jc_neirong);
	}
	public String getJc_neirong(){
		return (String)this.getInternal("JC_NEIRONG");
	}
	public void setJc_yaoqiu(String jc_yaoqiu){
		this.setInternal("JC_YAOQIU",jc_yaoqiu);
	}
	public String getJc_yaoqiu(){
		return (String)this.getInternal("JC_YAOQIU");
	}
	public void setJc_biaozhun(String jc_biaozhun){
		this.setInternal("JC_BIAOZHUN",jc_biaozhun);
	}
	public String getJc_biaozhun(){
		return (String)this.getInternal("JC_BIAOZHUN");
	}
	public void setJc_end_date(Date jc_end_date){
		this.setInternal("JC_END_DATE",jc_end_date);
	}
	public Date getJc_end_date(){
		return (Date)this.getInternal("JC_END_DATE");
	}
	public void setJc_user(String jc_user){
		this.setInternal("JC_USER",jc_user);
	}
	public String getJc_user(){
		return (String)this.getInternal("JC_USER");
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