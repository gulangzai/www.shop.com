package com.ccthanking.business.pmjiancha.vo;
import com.ccthanking.framework.base.BaseVO;
import java.util.Date;

public class PmXunjianStrucVO extends BaseVO{

	public PmXunjianStrucVO(){
		this.addField("XUNJIAN_STRUC_UID",OP_STRING|this.TP_PK);//
		this.addField("XUNJIAN_UID",OP_STRING);//
		this.addField("PRJ_STRUC_UID",OP_STRING);//
		this.setVOTableName("PM_XUNJIAN_STRUC");
	}

	public void setXunjian_struc_uid(String xunjian_struc_uid){
		this.setInternal("XUNJIAN_STRUC_UID",xunjian_struc_uid);
	}
	public String getXunjian_struc_uid(){
		return (String)this.getInternal("XUNJIAN_STRUC_UID");
	}
	public void setXunjian_uid(String xunjian_uid){
		this.setInternal("XUNJIAN_UID",xunjian_uid);
	}
	public String getXunjian_uid(){
		return (String)this.getInternal("XUNJIAN_UID");
	}
	public void setPrj_struc_uid(String prj_struc_uid){
		this.setInternal("PRJ_STRUC_UID",prj_struc_uid);
	}
	public String getPrj_struc_uid(){
		return (String)this.getInternal("PRJ_STRUC_UID");
	}
}