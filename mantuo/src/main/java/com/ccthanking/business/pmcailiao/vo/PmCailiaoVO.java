package com.ccthanking.business.pmcailiao.vo;
import com.ccthanking.framework.base.BaseVO;
import java.util.Date;

public class PmCailiaoVO extends BaseVO{

	public PmCailiaoVO(){
		this.addField("CAILIAO_UID",OP_STRING|this.TP_PK);//
		this.addField("PROJECT_UID",OP_STRING);//
		this.addField("CAILIAO_NAME",OP_STRING);//材料名称
		this.addField("CAILIAO_UNIT",OP_STRING);//材料单位
		this.addField("CAILIAO_PINPAI",OP_STRING);//材料品牌
		this.addField("CAILIAO_CHANGSHANG",OP_STRING);//材料生产商
		this.addField("CAILIAO_CHANDI",OP_STRING);//材料产地
		this.addField("CAILIAO_XINGHAO",OP_STRING);//材料规格型号
		this.addField("CAILIAO_PRICE",OP_STRING);//材料单价
		this.addField("CAILIAO_NUMS",OP_STRING);//材料数量
		this.addField("CAILIAO_JCRQ",OP_DATE);//材料进场日期
		this.addField("YANSHOU_REN",OP_STRING);//验收人
		this.addField("DESCRIPTION",OP_STRING);//
		this.addField("CREATE_DATE",OP_DATE);//
		this.addField("CREATE_USER",OP_STRING);//
		this.addField("UPDATE_DATE",OP_DATE);//
		this.addField("UPDATE_USER",OP_STRING);//
		this.setFieldDateFormat("CAILIAO_JCRQ","yyyy-MM-dd");
		this.setFieldDateFormat("CREATE_DATE","yyyy-MM-dd HH:mm:ss");
		this.setFieldDateFormat("UPDATE_DATE","yyyy-MM-dd HH:mm:ss");
		this.setVOTableName("PM_CAILIAO");
	}

	public void setCailiao_uid(String cailiao_uid){
		this.setInternal("CAILIAO_UID",cailiao_uid);
	}
	public String getCailiao_uid(){
		return (String)this.getInternal("CAILIAO_UID");
	}
	public void setProject_uid(String project_uid){
		this.setInternal("PROJECT_UID",project_uid);
	}
	public String getProject_uid(){
		return (String)this.getInternal("PROJECT_UID");
	}
	public void setCailiao_name(String cailiao_name){
		this.setInternal("CAILIAO_NAME",cailiao_name);
	}
	public String getCailiao_name(){
		return (String)this.getInternal("CAILIAO_NAME");
	}
	public void setCailiao_unit(String cailiao_unit){
		this.setInternal("CAILIAO_UNIT",cailiao_unit);
	}
	public String getCailiao_unit(){
		return (String)this.getInternal("CAILIAO_UNIT");
	}
	public void setCailiao_pinpai(String cailiao_pinpai){
		this.setInternal("CAILIAO_PINPAI",cailiao_pinpai);
	}
	public String getCailiao_pinpai(){
		return (String)this.getInternal("CAILIAO_PINPAI");
	}
	public void setCailiao_changshang(String cailiao_changshang){
		this.setInternal("CAILIAO_CHANGSHANG",cailiao_changshang);
	}
	public String getCailiao_changshang(){
		return (String)this.getInternal("CAILIAO_CHANGSHANG");
	}
	public void setCailiao_chandi(String cailiao_chandi){
		this.setInternal("CAILIAO_CHANDI",cailiao_chandi);
	}
	public String getCailiao_chandi(){
		return (String)this.getInternal("CAILIAO_CHANDI");
	}
	public void setCailiao_xinghao(String cailiao_xinghao){
		this.setInternal("CAILIAO_XINGHAO",cailiao_xinghao);
	}
	public String getCailiao_xinghao(){
		return (String)this.getInternal("CAILIAO_XINGHAO");
	}
	public void setCailiao_price(String cailiao_price){
		this.setInternal("CAILIAO_PRICE",cailiao_price);
	}
	public String getCailiao_price(){
		return (String)this.getInternal("CAILIAO_PRICE");
	}
	public void setCailiao_nums(String cailiao_nums){
		this.setInternal("CAILIAO_NUMS",cailiao_nums);
	}
	public String getCailiao_nums(){
		return (String)this.getInternal("CAILIAO_NUMS");
	}
	public void setCailiao_jcrq(Date cailiao_jcrq){
		this.setInternal("CAILIAO_JCRQ",cailiao_jcrq);
	}
	public Date getCailiao_jcrq(){
		return (Date)this.getInternal("CAILIAO_JCRQ");
	}
	public void setYanshou_ren(String yanshou_ren){
		this.setInternal("YANSHOU_REN",yanshou_ren);
	}
	public String getYanshou_ren(){
		return (String)this.getInternal("YANSHOU_REN");
	}
	public void setDescription(String description){
		this.setInternal("DESCRIPTION",description);
	}
	public String getDescription(){
		return (String)this.getInternal("DESCRIPTION");
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