package com.ccthanking.business.pmxianchang.vo;
import com.ccthanking.framework.base.BaseVO;
import java.util.Date;

/** 项目结构表**/
public class PmPrjStrucVO extends BaseVO{

	public PmPrjStrucVO(){
		this.addField("PRJ_STRUC_UID",OP_STRING|this.TP_PK);//
		this.addField("PROJECT_UID",OP_STRING);//当前项目结构所在的项目(主键id)
		this.addField("P_STRUC_UID",OP_STRING);//?
		this.addField("STRUC_CODE",OP_STRING);//结构代码
		this.addField("STRUC_NAME",OP_STRING);//结构名称
		this.addField("STRUC_TYPE",OP_STRING);//节点类型。X－项目；B－标段(地块)；F－分部工程(地下室建筑、楼、绿化等)；C－楼层；Q－其他（区域、位置等）
		this.addField("CREATE_DATE",OP_DATE);//
		this.addField("CREATE_USER",OP_STRING);//
		this.setFieldDateFormat("CREATE_DATE","yyyy-MM-dd HH:mm:ss");
		this.setVOTableName("PM_PRJ_STRUC");
	}

	public void setPrj_struc_uid(String prj_struc_uid){
		this.setInternal("PRJ_STRUC_UID",prj_struc_uid);
	}
	public String getPrj_struc_uid(){
		return (String)this.getInternal("PRJ_STRUC_UID");
	}
	public void setProject_uid(String project_uid){
		this.setInternal("PROJECT_UID",project_uid);
	}
	public String getProject_uid(){
		return (String)this.getInternal("PROJECT_UID");
	}
	public void setP_struc_uid(String p_struc_uid){
		this.setInternal("P_STRUC_UID",p_struc_uid);
	}
	public String getP_struc_uid(){
		return (String)this.getInternal("P_STRUC_UID");
	}
	public void setStruc_code(String struc_code){
		this.setInternal("STRUC_CODE",struc_code);
	}
	public String getStruc_code(){
		return (String)this.getInternal("STRUC_CODE");
	}
	public void setStruc_name(String struc_name){
		this.setInternal("STRUC_NAME",struc_name);
	}
	public String getStruc_name(){
		return (String)this.getInternal("STRUC_NAME");
	}
	public void setStruc_type(String struc_type){
		this.setInternal("STRUC_TYPE",struc_type);
	}
	public String getStruc_type(){
		return (String)this.getInternal("STRUC_TYPE");
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
}