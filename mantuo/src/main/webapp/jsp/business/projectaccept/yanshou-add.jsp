<%@ page language="java" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/assets/sys_resources/plugins/lightbox/themes/default/jquery.lightbox.css" />
<%@ page import="com.ccthanking.framework.common.User"%>
<%@ page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext" %>
<%@ include file="/jsp/framework/common/taglibs.jsp"%> 
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<style>
.page-content {
    background-color: #fff;
    position: relative;
    margin: 0;
    padding:3px 0px 0px 3px;
}
</style>
<!-- Modal -->
<div class="modal-dialog width-60 height-auto">
  <div class="modal-content" >
   <div class="widget-header widget-header-large">
   		<div class="widget-toolbar">
			<a href="#" data-action="close" data-dismiss="modal">
				<i class="ace-icon fa fa-times"></i>
				</a>
		</div>
    	<h3 id="myModalLabel" class="blue bigger">添加</h3>
  </div>
  <div class="modal-body">
 	  <form method="post" role="form" class="form-horizontal"  id="executeFrmXcAddXcAdd" > 
 	   <!-- 获取 项目 的 uid -->    	
      	 <input id="XCROJECT_UID" type="hidden" name="ROJECT_UID" fieldname="PROJECT_UID"  />
      	 <%--  <input  id="XM_PRJ_STRUC_UID"  type="hidden"  fieldname="PRJ_STRUC_UID" value="" />--%>
	  	  <input type="hidden" id="target_uid" fieldname="target_uid"  />
	  		<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" > 
	  			验收类型：<%--<span class="required-indicator">*</span>--%>
				</label>
				<div class="col-sm-10">
				 <select class="selector" onchange="gradeChange()" datatype="*" style="width:220px;"  nullmsg="请选择验收类型" fieldname="YANSHOU_TYPE" class="col-xs-11 col-sm-11" >
			        <option value="FB" >分部工程验收</option>
			    <!--<option value="FX" >分项工程验收</option> --> 
			        <option value="ZX" >专项工程验收</option>
			        <option value="YB" >隐蔽工程验收</option>
			        <option value="JG" >竣工总验收</option> 
			      </select>
				</div>
			</div>
	  		
			<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					分部分项工程名称：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-10">
				    <input  id="" nullmsg="请输入分部分项工程名称"  datatype="*"    placeholder="请输入分部分项工程名称" type="text"  fieldname="FBFXGC_NAME" value="" class="col-"/>
				</div>
			</div>
				
			<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					单位工程名称：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-10">
				 <input value="" id="" datatype="*" nullmsg="请输入单位工程名称"    placeholder="请输入单位工程名称" type="text"  fieldname="DWGC_NAME" class="col-xs-11 col-sm-11"/>
				</div>
			</div>
			
		    <div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					单位工程结构类型： <span class="required-indicator">*</span> 
				</label>
				<div class="col-sm-10">
				 <input  value="" id="" datatype="*" nullmsg="请输入单位工程结构类型"     placeholder="请输入单位工程结构类型" type="text"  fieldname="DWGC_JGLX" class="col-xs-11 col-sm-11"/>
				</div>
			</div>
			
			 <div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					地上层数：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-3">
				 <input value="" datatype="/^[0-9]*[1-9][0-9]*$/" errormsg="只能输入正整数" id="" nullmsg="请输入地上层数"    placeholder="请输入地上层数" type="text"  fieldname="DWGC_DSCS" class="col-xs-11 col-sm-10"/>
				</div>
				<label class="col-sm-2 control-label no-padding-right" >
					地下层数：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-3">
				 <input  value=""  datatype="/^[0-9]*[1-9][0-9]*$/" errormsg="只能输入正整数"  id="" nullmsg="请输入地下层数"  placeholder="请输入地下层数" type="text"  fieldname="DWGC_DXCS" class="col-xs-11 col-sm-10" />
				</div>
			</div>
			 
			 <div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					施工单位：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-10">
				<input id="uid1" value="" type="hidden"/>
				 <input value="" id="SGDW" datatype="*" nullmsg="请选择施工单位" disabled="disabled"   placeholder="请选择施工单位" type="hidden"  fieldname="SGDW"  class="col-xs-11 col-sm-11"/>
				 <input value="" id="SGDWS" datatype="*" nullmsg="请选择施工单位"  disabled="disabled"  placeholder="请选择施工单位" type="text"    class="col-xs-11 col-sm-11"/>
				<i class="ace-icon glyphicon glyphicon-list bigger-110" onclick="selectGuifan1();" style="cursor:pointer" title="选择项目参建单位"></i>
				</div>
			</div>
			<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					施工单位负责人：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-3">
				 <input value=""  id="SGDW_JSFZR" datatype="*" nullmsg="请输入施工单位负责人"     placeholder="请输入施工单位负责人" type="text"  fieldname="SGDW_JSFZR" class="col-xs-11 col-sm-10"/>
				</div>
				<label class="col-sm-2 control-label no-padding-right" >
					施工单位质量负责人：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-3">
				 <input   value="" id="" datatype="*" nullmsg="请输入施工单位质量负责人"  placeholder="请输入施工单位质量负责人" type="text"  fieldname="SGDW_ZLFZR" class="col-xs-11 col-sm-10"/>
				</div>
			</div> 
			<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					分包单位：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-10">
				 <input value="" id="FBDW" datatype="*" nullmsg="请选择分包单位"    placeholder="请选择分包单位" type="hidden"  fieldname="FBDW" disabled="disabled" class="col-xs-11 col-sm-11"/>
				 <input value="" id="FBDWS" datatype="*" nullmsg="请选择分包单位"    placeholder="请选择分包单位" type="text" disabled="disabled"  class="col-xs-11 col-sm-11"/>
				<i class="ace-icon glyphicon glyphicon-list bigger-110" onclick="selectGuifan2();" style="cursor:pointer" title="选择项目参建单位"></i>
				</div>
			</div>
			<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					分包单位负责人：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-3">
				 <input  value=""  id="FBDW_FZR" datatype="*" nullmsg="请输入分包单位负责人"     placeholder="请输入分包单位负责人" type="text"  fieldname="FBDW_FZR" class="col-xs-11 col-sm-10"/>
				</div>
				<label class="col-sm-2 control-label no-padding-right" >
					分包单位技术负责人：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-3">
				 <input  value="" id="" datatype="*" nullmsg="请输入分包单位技术负责人"    placeholder="请输入分包单位技术负责人" type="text"  fieldname="FBDW_JSFZR" class="col-xs-11 col-sm-10"/>
				</div>
			</div> 
			 				
			 
			  <div class="form-group group_yanshouDetailtable" style="display:none" >
			  
			    <a data-target="" id="newAddDetail" data-toggle="modal">
			       <button id="addYanshouDetail" class="btn btn-link btn-sm" type="button"><i class="ace-icon glyphicon glyphicon-plus"></i><span style="font-family:'Microsoft YaHei';font-size:15px;color:#4e4c4c;">添加验收详情</span></button>
			    </a>
			 
			   <div class="col-sm-12">
				<div class="page-content" id="inside1"> 
			      <table class="table" id="yanshouDetailtable">
						<tr>
						    <th class="col-sm-2" name="XUHAO"   align="center">序号</th>
							<th class="col-sm-2" name="DETAIL_NAME"   align="center" formatter="doYanshouTypeStatus">分项工程名称</th>
							<th class="col-sm-2" name="DETAIL_NUMS"   align="center">检验批数量</th>
							<th class="col-sm-2" name="DETAIL_BUWEI"  align="center">检验批部位、区段</th>
							<th class="col-sm-2" name="JCJG"  align="center">施工单位检查结果</th>
							<th class="col-sm-2" name="YSJL"   align="center">监理单位验收结论</th> 
						</tr> 
						<tbody id="tt"></tbody>
		         </table> 
		        </div>
		      </div>
			</div>	
		   
	<!-- -------------------------------------------------------------- -->	  
		     <div class="form-group group_FB_ZLKZZL_JC" style="display:block">
	  			<label class="col-sm-2 control-label no-padding-right" >
					 质量控制资料检查结果：<%--<span class="required-indicator">*</span>--%>
				</label>
				<div class="col-sm-10"> 
				 <textarea autofocus="autofocus"   nullmsg="请输入质量控制资料检查结果"  placeholder="请输入质量控制资料检查结果" rows="4" type="text"  fieldname="FB_ZLKZZL_JC" class="col-xs-11 col-sm-11" ></textarea>
				</div>
			</div>
		    <div class="form-group  group_FB_ZLKZZL_YS" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					质量控制资料验收结论：<%--<span class="required-indicator">*</span>--%>
				</label>
				<div class="col-sm-10">
				 <input  value="" id="" nullmsg="请输入质量控制资料验收结论"     placeholder="请输入质量控制资料验收结论" type="text"  fieldname="FB_ZLKZZL_YS" class="col-xs-11 col-sm-11"/>
				</div>
			</div>
		  
		   <div class="form-group  group_FB_AQGN_JC" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					安全和功能检测检查结果：<%--<span class="required-indicator">*</span>--%>
				</label>
				<div class="col-sm-10">
				   <textarea autofocus="autofocus"  nullmsg="请输入安全和功能检测检查结果"  placeholder="请输入安全和功能检测检查结果" rows="4" type="text"  fieldname="FB_AQGN_JC" class="col-xs-11 col-sm-11" ></textarea>
				</div>
			</div>
			
			<div class="form-group  group_FB_AQGN_YS" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					安全和功能检测验收结论：<%--<span class="required-indicator">*</span>--%>
				</label>
				<div class="col-sm-10">
				  <input  value="" id="" nullmsg="请输入安全和功能检测验收结论"    placeholder="请输入 安全和功能检测验收结论" type="text"  fieldname="FB_AQGN_YS" class="col-xs-11 col-sm-11"/>
				</div>
			</div>
			
			<div class="form-group  group_FB_GGZL_JC" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					观感质量验位检查结果：<%--<span class="required-indicator">*</span>--%>
				</label>
				<div class="col-sm-10">
				  <textarea autofocus="autofocus"  nullmsg="请输入观感质量验位检查结果"  placeholder="请输入观感质量验位检查结果" rows="4" type="text"  fieldname="FB_GGZL_JC" class="col-xs-11 col-sm-11" ></textarea>
				 </div>
			</div>
		    <div class="form-group  group_FB_GGZL_YS" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					观感质量验收验收结论：<%--<span class="required-indicator">*</span>--%>
				</label>
				<div class="col-sm-10">
				 <input  value="" id="" nullmsg="请输入观感质量验收验收结论"     placeholder="请输入观感质量验收验收结论" type="text"  fieldname="FB_GGZL_YS" class="col-xs-11 col-sm-11"/>
				</div>
			</div>
			
			<div class="form-group  group_YB_YSBW" style="display:none" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					隐蔽工程验收部位：<%--<span class="required-indicator">*</span>--%>
				</label>
				<div class="col-sm-10">
				  <input  value="" id="" nullmsg="请输入隐蔽工程验收部位"    placeholder="请输入隐蔽工程验收部位" type="text"  fieldname="YB_YSBW" class="col-xs-11 col-sm-11"/>
				</div>
			</div>
			
			<div class="form-group group_YB_YSYJ" style="display:none">
	  			<label class="col-sm-2 control-label no-padding-right" >
					隐蔽工程验收依据：<%--<span class="required-indicator">*</span>--%>
				</label>
				<div class="col-sm-10">
				 <input  value="" id="" nullmsg="请输入隐蔽工程验收依据"   placeholder="请输入隐蔽工程验收依据" type="text"  fieldname="YB_YSYJ" class="col-xs-11 col-sm-11"/>
				</div>
			</div>
			
			<div class="form-group group_YB_YSNR" style="display:none">
	  			<label class="col-sm-2 control-label no-padding-right" >
					隐蔽工程验收内容：<%--<span class="required-indicator">*</span>--%>
				</label>
				<div class="col-sm-10">
				 <input  value="" id="" nullmsg="请输入隐蔽工程验收内容"    placeholder="请输入隐蔽工程验收内容" type="text"  fieldname="YB_YSNR" class="col-xs-11 col-sm-11"/>
				</div>
			</div>
			
			<div class="form-group  group_ZX_KGRQ" style="display:none">
	  			<label class="col-sm-2 control-label no-padding-right" >
					专项工程开工日期： <span class="required-indicator">*</span> 
				</label>
				<div class="col-sm-10">
				 <input  class="REPORT_DATE1"  id="ZX_KGRQ" data-date-format="yyyy-mm-dd" nullmsg="请输入专项工程开工日期"  style="height: 25px;border-radius: 8px 8px 8px 8px; border: 1px solid #d1d2d9;"    placeholder="请输入专项工程开工日期"   fieldname="ZX_KGRQ" class="col-xs-11 col-sm-10"/>
				</div>
			</div>
			
			<div class="form-group group_ZX_WCRQ" style="display:none">
	  			<label class="col-sm-2 control-label no-padding-right" >
					专项工程完成日期：<span class="required-indicator">*</span> 
				</label>
				<div class="col-sm-10">
				 <input    class="REPORT_DATE1" id="ZX_WCRQ" data-date-format="yyyy-mm-dd" nullmsg="请输入专项工程完成日期" style="height: 25px;border-radius: 8px 8px 8px 8px; border: 1px solid #d1d2d9;"    placeholder="请输入专项工程完成日期"    fieldname="ZX_WCRQ" />
				</div>
			</div>
			
			<div class="form-group  group_ZX_GCNR" style="display:none">
	  			<label class="col-sm-2 control-label no-padding-right" >
					专项工程工程内容：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-10">
				 <input   id="ZX_GCNR" nullmsg="请输入专项工程工程内容"   placeholder="请输入专项工程工程内容" type="text"  fieldname="ZX_GCNR" class="col-xs-11 col-sm-11" />
				</div>
			</div>
			
			<div class="form-group group_ZX_YSZL" style="display:none">
	  			<label class="col-sm-2 control-label no-padding-right" >
					专项工程验收资料：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-10">
				 <input  value="" id="ZX_YSZL" nullmsg="请输入专项工程验收资料"    placeholder="请输入专项工程验收资料" type="text"  fieldname="ZX_YSZL" class="col-xs-11 col-sm-11"/>
				</div>
			</div>
			
<!-- ------------------------------------------------------------------ -->				
			<div class="form-group  group_JCJG">
	  			<label class="col-sm-2 control-label no-padding-right" >
					 检查结果： <span class="required-indicator">*</span> 
				</label>
				<div class="col-sm-10">
				 <input  value="" datatype="*" id="JCJG" nullmsg="请输入检查结果"     placeholder="请输入检查结果" type="text"  fieldname="JCJG" class="col-xs-11 col-sm-11"/>
				</div>
			</div>
			
			<div class="form-group  group_YSJL">
	  			<label class="col-sm-2 control-label no-padding-right" >
					 验收结论： <span class="required-indicator">*</span> 
				</label>
				<div class="col-sm-10">
				 <input  value="" datatype="*" id="YSJL" nullmsg="请输入验收结论"     placeholder="请输入验收结论" type="text"  fieldname="YSJL" class="col-xs-11 col-sm-11"/>
				</div>
			</div>
			
			
	  		
		  <div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					 验收日期： <span class="required-indicator">*</span> 
				</label>
				<div class="col-sm-10">
				 <input  class="REPORT_DATE1" id="YSRQ"  datatype="*" nullmsg="请输入验收日期 " placeholder="请输入验收日期" data-date-format="yyyy-mm-dd" style="height: 25px;border-radius: 8px 8px 8px 8px; border: 1px solid #d1d2d9;"  fieldname="YSRQ" class="col-xs-11 col-sm-10"/>
				</div>
			</div>
		   <div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					 复查结论：  <span class="required-indicator">*</span> 
				</label>
				<div class="col-sm-10">
				 <input  value="" id="" datatype="*" nullmsg="请输入 复查结论"    placeholder="请输入复查结论" type="text"  fieldname="FCJL" class="col-xs-11 col-sm-11"/>
				</div>
			</div>
            <div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					 复查日期 ：<span class="required-indicator">*</span>
				</label>
				 <div class="col-sm-10"> 
				 <input  class="REPORT_DATE_ONE" id="FCRQ" datatype="*" data-date-format="yyyy-mm-dd" nullmsg="请输入复查日期 " placeholder="请输入复查日期"  style="height: 25px;border-radius: 8px 8px 8px 8px; border: 1px solid #d1d2d9;"    fieldname="FCRQ" class="col-xs-11 col-sm-11"/>
				 </div>
			</div>
			
	  		<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					备注：
				</label>
				<div class="col-sm-10">
					 <textarea autofocus="autofocus"  nullmsg="请输入备注"  placeholder="请输入备注" rows="4" type="text"  fieldname="DESCRIPTION" class="col-xs-11 col-sm-11" ></textarea>
				</div>
			</div>
	  
            
            <div class="form-group">
				<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
				        附件：&nbsp;
				</label> 
				<div class="col-sm-9">
					<span style="margin:4px auto;" class="btn  btn-white btn-default btn-round " onclick="selectFile(this);" file_type="10701" >
						<i class="ace-icon fa fa-cloud-upload bigger-100"></i>
							附件上传
					</span>
						<form class="form-inline"  id="queryForm1" width="100%"
					   role="form" style="border:solid 1px #ddd;height:50px;line-height:50px;vertical-align:middle;"></form>
					<table  role="presentation" class="table table-striped">
						<tbody  class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery"  file_type="10701"></tbody>
					</table>
				</div>
			</div>
     </form>
     
	</div>

  <div class="modal-footer">
    <button id="addXcZkData" class="btn btn-1 btn-sms btn-round" type="button">&nbsp;保存&nbsp;</button>
    <button id="btnCloseXcJd"  class="btn btn-2 btn-sms pull-right  btn-round" data-dismiss="modal" aria-hidden="true">关闭</button>
  </div>
</div>
</div>
<div id="XMZK-XMMC" class="modal"></div> 
<jsp:include page="/jsp/file_upload/fileupload_util.jsp" flush="true" />
<%@ include file="/jsp/framework/common/basic_scripts.jsp"%>
<script src="${pageContext.request.contextPath }/assets/js/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/sys_resources/js/common/Form2Json.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/sys_resources/plugins/lightbox/jquery.min.js"></script>		
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/common/bootstrap-validation.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/sys_resources/plugins/lightbox/jquery.lightbox.min.js"></script>	
<script type="text/javascript"> 
var validform;

var controlletnameUrl="${pageContext.request.contextPath }/projectaccept/pmYanShouController";
var scripts =[null];
function selectGuifan1(){
	
	window.open("${pageContext.request.contextPath }/jsp/business/danwei/xmdanwei-select.jsp?uid=1");
}
function selectGuifan2(){
	
	window.open("${pageContext.request.contextPath }/jsp/business/danwei/xmdanwei-select.jsp?uid=2");
}
ace.load_ajax_scripts(scripts, function() { 
	 DatePicker.datepicker(".REPORT_DATE1"); 
     DatePicker.datepicker(".REPORT_DATE_ONE");  
     DatePicker.datepicker("#ZX_KGRQ");
     DatePicker.datepicker("#ZX_WCRQ");
 	//gridwidth=$("#inside1").width(); 
 	//JqgridManage.initJqgrid(grid_table1,queryForm1,gridwidth); 
 	//setHeight = $(window).height(); 
 	//if(navigator.userAgent.indexOf("Firefox") > -1){
 	 //  setHeight = setHeight+50;
 	//} 
 	// $("#grid_table1").setGridHeight(setHeight - 320);  
});

function gradeChange(){
	var sel = $(".selector").val();
	if(sel=='FB'){ 
		$(".group_yanshouDetailtable").show();
		$(".group_FB_ZLKZZL_JC").show(); 
		$(".group_FB_ZLKZZL_YS").show();
		
		$(".group_FB_AQGN_JC").show(); 
		$(".group_FB_AQGN_YS").show();
		
		$(".group_FB_GGZL_JC").show(); 
		$(".group_FB_GGZL_YS").show();
	}else{
		$(".group_yanshouDetailtable").hide();
		$(".group_FB_ZLKZZL_JC").hide();
		$(".group_FB_ZLKZZL_YS").hide();
		
		$(".group_FB_AQGN_JC").hide(); 
		$(".group_FB_AQGN_YS").hide();
		
		$(".group_FB_GGZL_JC").hide(); 
		$(".group_FB_GGZL_YS").hide();
	}
	
	if(sel=='YB'){
		$(".group_YB_YSBW").show();
		$(".group_YB_YSYJ").show(); 
		$(".group_YB_YSNR").show();
	}else{
		$(".group_YB_YSBW").hide();
		$(".group_YB_YSYJ").hide(); 
		$(".group_YB_YSNR").hide();
	}
	
	if(sel=='ZX'){
		$(".group_ZX_KGRQ").show();
		$("#ZX_KGRQ").attr("datatype","*");
		$(".group_ZX_WCRQ").show();
		$("#ZX_WCRQ").attr("datatype","*"); 
		$(".group_ZX_GCNR").show();
		$("#ZX_GCNR").attr("datatype","*");
		$(".group_ZX_YSZL").show();
		$("#ZX_YSZL").attr("datatype","*");
	}else{
		$("#ZX_KGRQ").removeAttr("datatype"); 
		$("#ZX_WCRQ").removeAttr("datatype");  
		$("#ZX_GCNR").removeAttr("datatype"); 
		$("#ZX_YSZL").removeAttr("datatype");
		
		$(".group_ZX_KGRQ").hide();
		$(".group_ZX_WCRQ").hide(); 
		$(".group_ZX_GCNR").hide();
		$(".group_ZX_YSZL").hide();
	}
	
    
	//清空表格数据 
	$("#yanshouDetailtable tbody tr").eq(0).nextAll().remove();
}

gradeChange();
//点击保存按钮
$(function() {	 
	
	//删除多余的 div 避免弹出图片时出现多层
	var div1 = $(".jquery-lightbox-overlay").eq(0);
	var div2 = $(".jquery-lightbox-move").eq(0); 
	$(".jquery-lightbox-overlay").remove();
	$(".jquery-lightbox-move").remove();
	$("body").append(div1);
	$("body").append(div2);
	
	$(".modal-backdrop").attr("class","");  
	$('#XCROJECT_UID').val($('#project_uid').val());
	$("#addXcZkData").click(function() {
	  validform=FormValid.validbyformid(executeFrmXcAddXcAdd); 
	  
	  var date1= $("#YSRQ").val();//验收日期
	  var date2= $("#FCRQ").val();//复查日期
	  var d1 = new Date(date1.replace(/\-/g, "\/")); 
		 var d2 = new Date(date2.replace(/\-/g, "\/"));
	  if(validform.check()&d2>d1){
		if($("#executeFrmXcAddXcAdd").validationButton()) {
			//生成json串
			var data = Form2Json.formToJSON(executeFrmXcAddXcAdd);
			//组成保存json串格式
			var data1 = defaultJson.packSaveJson(data);
			$.ajax({
				url :controlletnameUrl+"?insert",
				data : data1,
				cache : false,
				async :	false,
				dataType : "json",  
				type : 'post',
				success : function(response) {
					xAlert("信息提示","工程验收信息添加成功",1);
					$("#btnCloseXcJd").click();
					_reload();
			      }
			});
			
		 }
	   }else {
		   xAlert("信息提示","验收日期小于复查日期",1);
		   return;
	   }
	});
	
	 $("#addYanshouDetail").click(function(){
		$('#newAddDetail').attr("data-target","XMZK-XMMC");
		$('#XMZK-XMMC').removeData("bs.modal");
		$("#XMZK-XMMC").empty();
		$('#XMZK-XMMC').modal({
			backdrop: 'static'
		});
		
		$.get("${ctx}/jsp/business/projectaccept/yanshoudetail-add.jsp",function(data) {
			console.info(data);
			$("#XMZK-XMMC").empty();
			$("#XMZK-XMMC").html("");
			$("#XMZK-XMMC").html(data);
		}); 
	}); 
});

function selectFile(obj){
	var targetUid = $('#target_uid').val();
	var file_type = $(obj).attr('file_type');
	setFileData("PM_YANSHOU","YANSHOU_UID",targetUid,file_type);
    document.getElementById("fileupload_btn").click();	 
}
function _reload(){
	jQuery("#grid_table").jqGrid().trigger("reloadGrid");
}

(function () {
    var v17 = $.noConflict(true);
    window.$.v17 = v17;
 })();

(function ($) {
 	$('.lightbox').lightbox();
 })(window.$.v17);
//回调
function doCallback(s){
	var strs= new Array(); //定义一数组 
	strs=s.split(","); //字符分割 
	$("#uid1").val(strs[3]);
	var uid=$("#uid1").val();
	if(uid=="1"){
	$("#SGDW").val(strs[0]);
	$("#SGDWS").val(strs[1]);
	$("#SGDW_JSFZR").val(strs[4]);
	}else if(uid=="2"){
	$("#FBDW").val(strs[0]);
	$("#FBDWS").val(strs[1]);
	$("#FBDW_FZR").val(strs[4]);
	}
	
}
</script>