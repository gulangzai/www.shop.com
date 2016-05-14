package com.ccthanking.business.pmyanshou.vo;
import com.ccthanking.framework.base.BaseVO;
import java.util.Date;

public class PmYanshouVO extends BaseVO{

	public PmYanshouVO(){
		this.addField("YANSHOU_UID",OP_STRING|this.TP_PK);//
		this.addField("PROJECT_UID",OP_STRING);//
		this.addField("YANSHOU_TYPE",OP_STRING);//验收类型：FB－分部工程验收；FX－分项工程验收；YB－隐蔽工程验收；JG－竣工总验收 等等
		this.addField("FBFXGC_NAME",OP_STRING);//分部工程或分项工程名称
		this.addField("DWGC_NAME",OP_STRING);//单位工程名称
		this.addField("DWGC_JGLX",OP_STRING);//单位工程结构类型
		this.addField("DWGC_DSCS",OP_STRING);//单位工程地上层数
		this.addField("DWGC_DXCS",OP_STRING);//单位工程地下层数
		this.addField("SGDW",OP_STRING);//施工单位
		this.addField("SGDW_JSFZR",OP_STRING);//施工单位技术负责人
		this.addField("SGDW_ZLFZR",OP_STRING);//施工单位质量负责人
		this.addField("FBDW",OP_STRING);//分包单位
		this.addField("FBDW_FZR",OP_STRING);//分包单位负责人
		this.addField("FBDW_JSFZR",OP_STRING);//分包单位技术负责人
		this.addField("FB_ZLKZZL_JC",OP_STRING);//质量控制资料施工单位检查结果
		this.addField("FB_ZLKZZL_YS",OP_STRING);//质量控制资料监理单位验收结论
		this.addField("FB_AQGN_JC",OP_STRING);//安全和功能检测施工单位检查结果
		this.addField("FB_AQGN_YS",OP_STRING);//安全和功能检测监理单位验收结论
		this.addField("FB_GGZL_JC",OP_STRING);//观感质量验收施工单位检查结果
		this.addField("FB_GGZL_YS",OP_STRING);//观感质量验收监理单位验收结论
		this.addField("YB_YSBW",OP_STRING);//隐蔽工程验收部位
		this.addField("YB_YSYJ",OP_STRING);//隐蔽工程验收依据
		this.addField("YB_YSNR",OP_STRING);//隐蔽工程验收内容
		this.addField("ZX_KGRQ",OP_DATE);//专项工程开工日期
		this.addField("ZX_WCRQ",OP_DATE);//专项工程完成日期
		this.addField("ZX_GCNR",OP_STRING);//专项工程工程内容
		this.addField("ZX_YSZL",OP_STRING);//专项工程验收资料
		this.addField("JCJG",OP_STRING);//分项工程施工单位检查结果/隐蔽工程检查意见
		this.addField("YSJL",OP_STRING);//分项工程监理单位验收结论/分部工程综合验收结论/隐蔽工程检查结论
		this.addField("YSRQ",OP_DATE);//验收日期
		this.addField("FCJL",OP_STRING);//复查结论
		this.addField("FCRQ",OP_DATE);//复查日期
		this.addField("DESCRIPTION",OP_STRING);//备注
		this.addField("CREATE_DATE",OP_DATE);//
		this.addField("CREATE_USER",OP_STRING);//
		this.addField("UPDATE_DATE",OP_DATE);//
		this.addField("UPDATE_USER",OP_STRING);//
		this.setFieldDateFormat("ZX_KGRQ","yyyy-MM-dd");
		this.setFieldDateFormat("ZX_WCRQ","yyyy-MM-dd");
		this.setFieldDateFormat("YSRQ","yyyy-MM-dd");
		this.setFieldDateFormat("FCRQ","yyyy-MM-dd");
		this.setFieldDateFormat("CREATE_DATE","yyyy-MM-dd HH:mm:ss");
		this.setFieldDateFormat("UPDATE_DATE","yyyy-MM-dd HH:mm:ss");
		this.setVOTableName("PM_YANSHOU");
	}

	public void setYanshou_uid(String yanshou_uid){
		this.setInternal("YANSHOU_UID",yanshou_uid);
	}
	public String getYanshou_uid(){
		return (String)this.getInternal("YANSHOU_UID");
	}
	public void setProject_uid(String project_uid){
		this.setInternal("PROJECT_UID",project_uid);
	}
	public String getProject_uid(){
		return (String)this.getInternal("PROJECT_UID");
	}
	public void setYanshou_type(String yanshou_type){
		this.setInternal("YANSHOU_TYPE",yanshou_type);
	}
	public String getYanshou_type(){
		return (String)this.getInternal("YANSHOU_TYPE");
	}
	public void setFbfxgc_name(String fbfxgc_name){
		this.setInternal("FBFXGC_NAME",fbfxgc_name);
	}
	public String getFbfxgc_name(){
		return (String)this.getInternal("FBFXGC_NAME");
	}
	public void setDwgc_name(String dwgc_name){
		this.setInternal("DWGC_NAME",dwgc_name);
	}
	public String getDwgc_name(){
		return (String)this.getInternal("DWGC_NAME");
	}
	public void setDwgc_jglx(String dwgc_jglx){
		this.setInternal("DWGC_JGLX",dwgc_jglx);
	}
	public String getDwgc_jglx(){
		return (String)this.getInternal("DWGC_JGLX");
	}
	public void setDwgc_dscs(String dwgc_dscs){
		this.setInternal("DWGC_DSCS",dwgc_dscs);
	}
	public String getDwgc_dscs(){
		return (String)this.getInternal("DWGC_DSCS");
	}
	public void setDwgc_dxcs(String dwgc_dxcs){
		this.setInternal("DWGC_DXCS",dwgc_dxcs);
	}
	public String getDwgc_dxcs(){
		return (String)this.getInternal("DWGC_DXCS");
	}
	public void setSgdw(String sgdw){
		this.setInternal("SGDW",sgdw);
	}
	public String getSgdw(){
		return (String)this.getInternal("SGDW");
	}
	public void setSgdw_jsfzr(String sgdw_jsfzr){
		this.setInternal("SGDW_JSFZR",sgdw_jsfzr);
	}
	public String getSgdw_jsfzr(){
		return (String)this.getInternal("SGDW_JSFZR");
	}
	public void setSgdw_zlfzr(String sgdw_zlfzr){
		this.setInternal("SGDW_ZLFZR",sgdw_zlfzr);
	}
	public String getSgdw_zlfzr(){
		return (String)this.getInternal("SGDW_ZLFZR");
	}
	public void setFbdw(String fbdw){
		this.setInternal("FBDW",fbdw);
	}
	public String getFbdw(){
		return (String)this.getInternal("FBDW");
	}
	public void setFbdw_fzr(String fbdw_fzr){
		this.setInternal("FBDW_FZR",fbdw_fzr);
	}
	public String getFbdw_fzr(){
		return (String)this.getInternal("FBDW_FZR");
	}
	public void setFbdw_jsfzr(String fbdw_jsfzr){
		this.setInternal("FBDW_JSFZR",fbdw_jsfzr);
	}
	public String getFbdw_jsfzr(){
		return (String)this.getInternal("FBDW_JSFZR");
	}
	public void setFb_zlkzzl_jc(String fb_zlkzzl_jc){
		this.setInternal("FB_ZLKZZL_JC",fb_zlkzzl_jc);
	}
	public String getFb_zlkzzl_jc(){
		return (String)this.getInternal("FB_ZLKZZL_JC");
	}
	public void setFb_zlkzzl_ys(String fb_zlkzzl_ys){
		this.setInternal("FB_ZLKZZL_YS",fb_zlkzzl_ys);
	}
	public String getFb_zlkzzl_ys(){
		return (String)this.getInternal("FB_ZLKZZL_YS");
	}
	public void setFb_aqgn_jc(String fb_aqgn_jc){
		this.setInternal("FB_AQGN_JC",fb_aqgn_jc);
	}
	public String getFb_aqgn_jc(){
		return (String)this.getInternal("FB_AQGN_JC");
	}
	public void setFb_aqgn_ys(String fb_aqgn_ys){
		this.setInternal("FB_AQGN_YS",fb_aqgn_ys);
	}
	public String getFb_aqgn_ys(){
		return (String)this.getInternal("FB_AQGN_YS");
	}
	public void setFb_ggzl_jc(String fb_ggzl_jc){
		this.setInternal("FB_GGZL_JC",fb_ggzl_jc);
	}
	public String getFb_ggzl_jc(){
		return (String)this.getInternal("FB_GGZL_JC");
	}
	public void setFb_ggzl_ys(String fb_ggzl_ys){
		this.setInternal("FB_GGZL_YS",fb_ggzl_ys);
	}
	public String getFb_ggzl_ys(){
		return (String)this.getInternal("FB_GGZL_YS");
	}
	public void setYb_ysbw(String yb_ysbw){
		this.setInternal("YB_YSBW",yb_ysbw);
	}
	public String getYb_ysbw(){
		return (String)this.getInternal("YB_YSBW");
	}
	public void setYb_ysyj(String yb_ysyj){
		this.setInternal("YB_YSYJ",yb_ysyj);
	}
	public String getYb_ysyj(){
		return (String)this.getInternal("YB_YSYJ");
	}
	public void setYb_ysnr(String yb_ysnr){
		this.setInternal("YB_YSNR",yb_ysnr);
	}
	public String getYb_ysnr(){
		return (String)this.getInternal("YB_YSNR");
	}
	public void setZx_kgrq(Date zx_kgrq){
		this.setInternal("ZX_KGRQ",zx_kgrq);
	}
	public Date getZx_kgrq(){
		return (Date)this.getInternal("ZX_KGRQ");
	}
	public void setZx_wcrq(Date zx_wcrq){
		this.setInternal("ZX_WCRQ",zx_wcrq);
	}
	public Date getZx_wcrq(){
		return (Date)this.getInternal("ZX_WCRQ");
	}
	public void setZx_gcnr(String zx_gcnr){
		this.setInternal("ZX_GCNR",zx_gcnr);
	}
	public String getZx_gcnr(){
		return (String)this.getInternal("ZX_GCNR");
	}
	public void setZx_yszl(String zx_yszl){
		this.setInternal("ZX_YSZL",zx_yszl);
	}
	public String getZx_yszl(){
		return (String)this.getInternal("ZX_YSZL");
	}
	public void setJcjg(String jcjg){
		this.setInternal("JCJG",jcjg);
	}
	public String getJcjg(){
		return (String)this.getInternal("JCJG");
	}
	public void setYsjl(String ysjl){
		this.setInternal("YSJL",ysjl);
	}
	public String getYsjl(){
		return (String)this.getInternal("YSJL");
	}
	public void setYsrq(Date ysrq){
		this.setInternal("YSRQ",ysrq);
	}
	public Date getYsrq(){
		return (Date)this.getInternal("YSRQ");
	}
	public void setFcjl(String fcjl){
		this.setInternal("FCJL",fcjl);
	}
	public String getFcjl(){
		return (String)this.getInternal("FCJL");
	}
	public void setFcrq(Date fcrq){
		this.setInternal("FCRQ",fcrq);
	}
	public Date getFcrq(){
		return (Date)this.getInternal("FCRQ");
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