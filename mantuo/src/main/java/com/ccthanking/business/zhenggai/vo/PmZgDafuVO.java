package com.ccthanking.business.zhenggai.vo;
import com.ccthanking.framework.base.BaseVO;
import java.util.Date;

public class PmZgDafuVO extends BaseVO{

	public PmZgDafuVO(){
		this.addField("ZG_DAFU_UID",OP_STRING|this.TP_PK);//
		this.addField("ZHENGGAI_UID",OP_STRING);//
		this.addField("DAFU_CODE",OP_STRING);//答复单编号
		this.addField("DAFU_DATE",OP_DATE);//整改答复日期
		this.addField("DAFU_ZG_DATE",OP_DATE);//完成整改的日期
		this.addField("DAFU_USER",OP_STRING);//答复人姓名
		this.addField("STATUS",OP_STRING);//状态：-1－草稿；0－已答复未处理；1－已处理；
		this.addField("FUCHA_USER",OP_STRING);//复查人员姓名
		this.addField("FUCHA_DATE",OP_DATE);//复查时间
		this.addField("FUCHA_DESC",OP_STRING);//复查情况
		this.addField("FUCHA_JIEGUO",OP_STRING);//复查结果：-0－重新整改；1－整改完成关闭
		this.addField("CXZG_DONE_DATE",OP_DATE);//重新整改应完成的日期
		this.addField("NEW_Y",OP_STRING);//是否为最新一次的答复，Y为最新
		this.addField("CREATE_DATE",OP_DATE);//答复创建日期
		this.addField("CREATE_USER",OP_STRING);//答复创建人
		this.addField("UPDATE_DATE",OP_DATE);//答复更新日期，复查时不更新
		this.addField("UPDATE_USER",OP_STRING);//答复更新人，复查时不更新
		this.setFieldDateFormat("DAFU_DATE","yyyy-MM-dd");
		this.setFieldDateFormat("DAFU_ZG_DATE","yyyy-MM-dd");
		this.setFieldDateFormat("FUCHA_DATE","yyyy-MM-dd HH:mm:ss");
		this.setFieldDateFormat("CXZG_DONE_DATE","yyyy-MM-dd");
		this.setFieldDateFormat("CREATE_DATE","yyyy-MM-dd HH:mm:ss");
		this.setFieldDateFormat("UPDATE_DATE","yyyy-MM-dd HH:mm:ss");
		this.setVOTableName("PM_ZG_DAFU");
	}

	public void setZg_dafu_uid(String zg_dafu_uid){
		this.setInternal("ZG_DAFU_UID",zg_dafu_uid);
	}
	public String getZg_dafu_uid(){
		return (String)this.getInternal("ZG_DAFU_UID");
	}
	public void setZhenggai_uid(String zhenggai_uid){
		this.setInternal("ZHENGGAI_UID",zhenggai_uid);
	}
	public String getZhenggai_uid(){
		return (String)this.getInternal("ZHENGGAI_UID");
	}
	public void setDafu_code(String dafu_code){
		this.setInternal("DAFU_CODE",dafu_code);
	}
	public String getDafu_code(){
		return (String)this.getInternal("DAFU_CODE");
	}
	public void setDafu_date(Date dafu_date){
		this.setInternal("DAFU_DATE",dafu_date);
	}
	public Date getDafu_date(){
		return (Date)this.getInternal("DAFU_DATE");
	}
	public void setDafu_zg_date(Date dafu_zg_date){
		this.setInternal("DAFU_ZG_DATE",dafu_zg_date);
	}
	public Date getDafu_zg_date(){
		return (Date)this.getInternal("DAFU_ZG_DATE");
	}
	public void setDafu_user(String dafu_user){
		this.setInternal("DAFU_USER",dafu_user);
	}
	public String getDafu_user(){
		return (String)this.getInternal("DAFU_USER");
	}
	public void setStatus(String status){
		this.setInternal("STATUS",status);
	}
	public String getStatus(){
		return (String)this.getInternal("STATUS");
	}
	public void setFucha_user(String fucha_user){
		this.setInternal("FUCHA_USER",fucha_user);
	}
	public String getFucha_user(){
		return (String)this.getInternal("FUCHA_USER");
	}
	public void setFucha_date(Date fucha_date){
		this.setInternal("FUCHA_DATE",fucha_date);
	}
	public Date getFucha_date(){
		return (Date)this.getInternal("FUCHA_DATE");
	}
	public void setFucha_desc(String fucha_desc){
		this.setInternal("FUCHA_DESC",fucha_desc);
	}
	public String getFucha_desc(){
		return (String)this.getInternal("FUCHA_DESC");
	}
	public void setFucha_jieguo(String fucha_jieguo){
		this.setInternal("FUCHA_JIEGUO",fucha_jieguo);
	}
	public String getFucha_jieguo(){
		return (String)this.getInternal("FUCHA_JIEGUO");
	}
	public void setCxzg_done_date(Date cxzg_done_date){
		this.setInternal("CXZG_DONE_DATE",cxzg_done_date);
	}
	public Date getCxzg_done_date(){
		return (Date)this.getInternal("CXZG_DONE_DATE");
	}
	public void setNew_y(String new_y){
		this.setInternal("NEW_Y",new_y);
	}
	public String getNew_y(){
		return (String)this.getInternal("NEW_Y");
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