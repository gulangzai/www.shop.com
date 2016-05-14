package com.ccthanking.business.jkjc.vo;
import com.ccthanking.framework.base.BaseVO;
import java.util.Date;

public class JcPrjPointsVO extends BaseVO{

	public JcPrjPointsVO(){
		this.addField("PRJ_POINTS_UID",OP_STRING|this.TP_PK);//
		this.addField("PROJECT_UID",OP_STRING);//
		this.addField("U3D_POINT_ID",OP_STRING);//U3D中监测点ID
		this.addField("U3D_ELEMENT_ID",OP_STRING);//U3D中ELEMENT ID
		this.addField("POINT_CODE",OP_STRING);//监测点CODE
		this.addField("POINT_TYPE",OP_STRING);//监测点类型：基坑、降水、视频、门禁等
		this.addField("EQUIPMENT_UID",OP_STRING);//测点所用设备UID
		this.addField("INIT_HEIGHT",OP_STRING);//降水类测点初始高度
		this.addField("CREATED_DATE",OP_DATE);//
		this.setFieldDateFormat("CREATED_DATE","yyyy-MM-dd HH:mm:ss");
		this.setVOTableName("JC_PRJ_POINTS");
	}

	public void setPrj_points_uid(String prj_points_uid){
		this.setInternal("PRJ_POINTS_UID",prj_points_uid);
	}
	public String getPrj_points_uid(){
		return (String)this.getInternal("PRJ_POINTS_UID");
	}
	public void setProject_uid(String project_uid){
		this.setInternal("PROJECT_UID",project_uid);
	}
	public String getProject_uid(){
		return (String)this.getInternal("PROJECT_UID");
	}
	public void setU3d_point_id(String u3d_point_id){
		this.setInternal("U3D_POINT_ID",u3d_point_id);
	}
	public String getU3d_point_id(){
		return (String)this.getInternal("U3D_POINT_ID");
	}
	public void setU3d_element_id(String u3d_element_id){
		this.setInternal("U3D_ELEMENT_ID",u3d_element_id);
	}
	public String getU3d_element_id(){
		return (String)this.getInternal("U3D_ELEMENT_ID");
	}
	public void setPoint_code(String point_code){
		this.setInternal("POINT_CODE",point_code);
	}
	public String getPoint_code(){
		return (String)this.getInternal("POINT_CODE");
	}
	public void setPoint_type(String point_type){
		this.setInternal("POINT_TYPE",point_type);
	}
	public String getPoint_type(){
		return (String)this.getInternal("POINT_TYPE");
	}
	public void setEquipment_uid(String equipment_uid){
		this.setInternal("EQUIPMENT_UID",equipment_uid);
	}
	public String getEquipment_uid(){
		return (String)this.getInternal("EQUIPMENT_UID");
	}
	public void setInit_height(String init_height){
		this.setInternal("INIT_HEIGHT",init_height);
	}
	public String getInit_height(){
		return (String)this.getInternal("INIT_HEIGHT");
	}
	public void setCreated_date(Date created_date){
		this.setInternal("CREATED_DATE",created_date);
	}
	public Date getCreated_date(){
		return (Date)this.getInternal("CREATED_DATE");
	}
}