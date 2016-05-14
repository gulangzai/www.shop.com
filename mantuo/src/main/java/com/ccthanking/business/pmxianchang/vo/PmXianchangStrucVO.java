package com.ccthanking.business.pmxianchang.vo;
import com.ccthanking.framework.base.BaseVO;
import java.util.Date;

/**中间表  现场状况 对应的项目具体的结构表**/
public class PmXianchangStrucVO extends BaseVO{

	public PmXianchangStrucVO(){
		this.addField("XIANCHANG_STRUC_UID",OP_STRING|this.TP_PK);//本表主键id
		this.addField("XIANCHANG_UID",OP_STRING);//现场主键id
		this.addField("PRJ_STRUC_UID",OP_STRING);//项目主键id
		this.setVOTableName("PM_XIANCHANG_STRUC");
	}

	public void setXianchang_struc_uid(String xianchang_struc_uid){
		this.setInternal("XIANCHANG_STRUC_UID",xianchang_struc_uid);
	}
	public String getXianchang_struc_uid(){
		return (String)this.getInternal("XIANCHANG_STRUC_UID");
	}
	public void setXianchang_uid(String xianchang_uid){
		this.setInternal("XIANCHANG_UID",xianchang_uid);
	}
	public String getXianchang_uid(){
		return (String)this.getInternal("XIANCHANG_UID");
	}
	public void setPrj_struc_uid(String prj_struc_uid){
		this.setInternal("PRJ_STRUC_UID",prj_struc_uid);
	}
	public String getPrj_struc_uid(){
		return (String)this.getInternal("PRJ_STRUC_UID");
	}
}