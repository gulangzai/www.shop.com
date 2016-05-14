package com.ccthanking.business.userFocus.vo;
import com.ccthanking.framework.base.BaseVO;
import java.util.Date;

public class JcUserFocusPointVO extends BaseVO{

	public JcUserFocusPointVO(){
		this.addField("USER_FOCUS_POINT_UID",OP_STRING|this.TP_PK);//
		this.addField("USER_UID",OP_STRING);//
		this.addField("PRJ_POINTS_UID",OP_STRING);//
		this.setVOTableName("JC_USER_FOCUS_POINT");
	}

	public void setUser_focus_point_uid(String user_focus_point_uid){
		this.setInternal("USER_FOCUS_POINT_UID",user_focus_point_uid);
	}
	public String getUser_focus_point_uid(){
		return (String)this.getInternal("USER_FOCUS_POINT_UID");
	}
	public void setUser_uid(String user_uid){
		this.setInternal("USER_UID",user_uid);
	}
	public String getUser_uid(){
		return (String)this.getInternal("USER_UID");
	}
	public void setPrj_points_uid(String prj_points_uid){
		this.setInternal("PRJ_POINTS_UID",prj_points_uid);
	}
	public String getPrj_points_uid(){
		return (String)this.getInternal("PRJ_POINTS_UID");
	}
}