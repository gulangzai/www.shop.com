package com.ccthanking.business.jcgl.vo;
import com.ccthanking.framework.base.BaseVO;
import java.util.Date;

public class SysBzjcVO extends BaseVO{

	public SysBzjcVO(){
		this.addField("BZJC_UID",OP_STRING|this.TP_PK);//
		this.addField("BZJC_TYPE_UID",OP_STRING);//
		this.addField("P_BZJC_UID",OP_STRING);//
		this.addField("LEVEL",OP_STRING);//层次
		this.addField("XUHAO",OP_STRING);//同一层的序号
		this.addField("BZJC_NAME",OP_STRING);//标准检查名称
		this.addField("JC_ZHIBIAO",OP_STRING);//检查指标
		this.addField("JC_SSRY",OP_STRING);//实施人员
		this.addField("JC_BIAOZHUN",OP_STRING);//检查标准
		this.addField("JC_DZMS",OP_STRING);//检查动作描述
		this.addField("JC_CHENGGUO",OP_STRING);//检查成果
		this.addField("DESCRIPTION",OP_STRING);//备注
		this.addField("CREATE_DATE",OP_DATE);//创建时间
		this.addField("UPDATE_DATE",OP_DATE);//最后更新时间
		this.setFieldDateFormat("CREATE_DATE","yyyy-MM-dd HH:mm:ss");
		this.setFieldDateFormat("UPDATE_DATE","yyyy-MM-dd HH:mm:ss");
		this.setVOTableName("SYS_BZJC");
	}

	public void setBzjc_uid(String bzjc_uid){
		this.setInternal("BZJC_UID",bzjc_uid);
	}
	public String getBzjc_uid(){
		return (String)this.getInternal("BZJC_UID");
	}
	public void setBzjc_type_uid(String bzjc_type_uid){
		this.setInternal("BZJC_TYPE_UID",bzjc_type_uid);
	}
	public String getBzjc_type_uid(){
		return (String)this.getInternal("BZJC_TYPE_UID");
	}
	public void setP_bzjc_uid(String p_bzjc_uid){
		this.setInternal("P_BZJC_UID",p_bzjc_uid);
	}
	public String getP_bzjc_uid(){
		return (String)this.getInternal("P_BZJC_UID");
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
	public void setBzjc_name(String bzjc_name){
		this.setInternal("BZJC_NAME",bzjc_name);
	}
	public String getBzjc_name(){
		return (String)this.getInternal("BZJC_NAME");
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
	public void setCreate_date(Date create_date){
		this.setInternal("CREATE_DATE",create_date);
	}
	public Date getCreate_date(){
		return (Date)this.getInternal("CREATE_DATE");
	}
	public void setUpdate_date(Date update_date){
		this.setInternal("UPDATE_DATE",update_date);
	}
	public Date getUpdate_date(){
		return (Date)this.getInternal("UPDATE_DATE");
	}
}