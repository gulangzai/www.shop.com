<%@ page language="java" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/assets/sys_resources/plugins/lightbox/themes/default/jquery.lightbox.css" />
<%@ page import="com.ccthanking.framework.common.User"%>
<%@ page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext" %>
<%@ page import="com.ccthanking.framework.Constants"%>
<%@ include file="/jsp/framework/common/taglibs.jsp"%> 
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<%
	User user = RestContext.getCurrentUser();
	String user_uid = "";
	String username = "";
	if(user!=null){
		user_uid = user.getIdCard();
		username = user.getName();
	}else{
		response.sendRedirect("/"+Constants.APP_NAME);
		return;
	}
	String PROJECT_UID = request.getParameter("PROJECT_UID");
	String YANSHOU_UID = request.getParameter("YANSHOU_UID"); 
	String YANSHOU_TYPE = request.getParameter("YANSHOU_TYPE");
%>
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
    	<h3 id="myModalLabel" class="blue bigger">修改</h3>
  </div>
  <div class="modal-body">
      <form id="yanshouSeeForm">
          <input  type="hidden" name="YANSHOU_UID" operation="=" fieldname="YANSHOU_UID" value="<%=YANSHOU_UID%>"/>    
          <input  type="hidden" name="PROJECT_UID" operation="=" fieldname="PROJECT_UID" value="<%=PROJECT_UID%>"/>    
      </form>
 	  <form method="post" role="form" class="form-horizontal"  id="yanshouseeFormResult" > 
 	   <!-- 获取 项目 的 uid -->    	
      	 <input id="XCROJECT_UID" type="hidden" name="ROJECT_UID" fieldname="PROJECT_UID"  />
      	 <%--  <input  id="XM_PRJ_STRUC_UID"  type="hidden"  fieldname="PRJ_STRUC_UID" value="" />--%>
	  	  <input  type="hidden" name="YANSHOU_UID"   fieldname="YANSHOU_UID"/>       
          <input type="hidden" id="target_uid" fieldname="target_uid"/>
	  		<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" > 
	  			验收类型：<%--<span class="required-indicator">*</span>--%>
				</label>
				<div class="col-sm-10">
				  <select class="selector" onchange="gradeChange()" datatype="*"   nullmsg="请选择验收类型" fieldname="YANSHOU_TYPE" class="col-xs-11 col-sm-11" >
			      </select>
				</div>
			</div>
	  		
			<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					分部分项工程名称：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-10">
				    <input  id="" nullmsg="请输入分部分项工程名称"  datatype="*"    placeholder="请输入分部分项工程名称" type="text"  fieldname="FBFXGC_NAME" value="" class="col-xs-11 col-sm-11"/>
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
				 <input  value="" id="" datatype="*" nullmsg="请输入单位工程结构类型"   placeholder="请输入单位工程结构类型" type="text"  fieldname="DWGC_JGLX" class="col-xs-11 col-sm-11"/>
				</div>
			</div>
			
			 <div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					地上层数：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
				 <input value="" datatype="/^[0-9]*[1-9][0-9]*$/" errormsg="只能输入正整数" id="" nullmsg="请输入地上层数"     placeholder="请输入地上层数" type="text"  fieldname="DWGC_DSCS" class="col-xs-11 col-sm-10"/>
				</div>
				<label class="col-sm-2 control-label no-padding-right" >
					地下层数：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
				 <input  value=""  datatype="/^[0-9]*[1-9][0-9]*$/" errormsg="只能输入正整数"  id="" nullmsg="请输入地下层数"   placeholder="请输入地下层数" type="text"  fieldname="DWGC_DXCS" class="col-xs-11 col-sm-10"/>
				</div>
			</div>
			 
			 <div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					施工单位：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-10">
				<input id="uid1" value="" type="hidden"/>
				 <input value="" id="SGDW" disabled="disabled" datatype="*" nullmsg="请选择施工单位"    placeholder="请选择施工单位" type="hidden"  fieldname="SGDW" class="col-xs-11 col-sm-11"/>
				 <input value="" id="SGDWS" disabled="disabled" datatype="*" nullmsg="请选择施工单位"    placeholder="请选择施工单位" type="text"  class="col-xs-11 col-sm-11"/>
				<i class="ace-icon glyphicon glyphicon-list bigger-110" onclick="selectGuifan1();" style="cursor:pointer" title="选择项目参建单位"></i>
				</div>
			</div>
			<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					施工单位负责人：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
				 <input value=""  id="SGDW_JSFZR" datatype="*" nullmsg="请输入施工单位负责人"      placeholder="请输入施工单位负责人" type="text"  fieldname="SGDW_JSFZR" class="col-xs-11 col-sm-10"/>
				</div>
				<label class="col-sm-2 control-label no-padding-right" >
					施工单位质量负责人：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
				 <input   value="" id="" datatype="*" nullmsg="请输入施工单位质量负责人"   placeholder="请输入施工单位质量负责人" type="text"  fieldname="SGDW_ZLFZR" class="col-xs-11 col-sm-10"/>
				</div>
			</div> 
			<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					分包单位：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-10">
				 <input value="" id="FBDW" datatype="*" disabled="disabled" nullmsg="请选择分包单位"    placeholder="请选择分包单位" type="hidden"  fieldname="FBDW" class="col-xs-11 col-sm-11"/>
				 <input value="" id="FBDWS" datatype="*" disabled="disabled" nullmsg="请选择分包单位"    placeholder="请选择分包单位" type="text"  class="col-xs-11 col-sm-11"/>
				<i class="ace-icon glyphicon glyphicon-list bigger-110" onclick="selectGuifan2();" style="cursor:pointer" title="选择项目参建单位"></i>
				</div>
			</div>
			<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					分包单位负责人：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
				 <input  value=""  id="FBDW_FZR" datatype="*" nullmsg="请输入分包单位负责人"     placeholder="请输入分包单位负责人" type="text"  fieldname="FBDW_FZR" class="col-xs-11 col-sm-10"/>
				</div>
				<label class="col-sm-2 control-label no-padding-right" >
					分包单位技术负责人：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
				 <input  value="" id="" datatype="*" nullmsg="请输入分包单位技术负责人"     placeholder="请输入分包单位技术负责人" type="text"  fieldname="FBDW_JSFZR" class="col-xs-11 col-sm-10"/>
				</div>
			</div> 
			 				
			 
			  <div class="form-group group_yanshouDetailtable" style="display:none" >
			  
		<!--    <a data-target="" id="newAddDetail" data-toggle="modal">
			       <button id="addYanshouDetail" class="btn btn-link btn-sm" type="button"><i class="ace-icon glyphicon glyphicon-plus"></i><span style="font-family:'Microsoft YaHei';font-size:15px;color:#4e4c4c;">添加验收详情</span></button>
			    </a> --> 
			   <div class="col-sm-12"> 
					<div class="page-content" id="inside1">  
				      <table class="table" id="yanshouDetailtable">
							<tr>
							    <th name="XUHAO"   align="center">序号</th>
								<th name="DETAIL_NAME"   align="center" formatter="doYanshouTypeStatus">分项工程名称</th>
								<th name="DETAIL_NUMS"   align="center">检验批数量</th>
								<th name="DETAIL_BUWEI"  align="center">检验批部位、区段</th>
								<th name="JCJG"  align="center">施工单位检查结果</th>
								<th name="YSJL"   align="center">监理单位验收结论</th> 
							</tr>
					   <tbody id="yanshou-see-table"></tbody> 
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
				 <input  value="" id="" nullmsg="请输入质量控制资料验收结论"     placeholder="请输入 质量控制资料验收结论" type="text"  fieldname="FB_ZLKZZL_YS" class="col-xs-11 col-sm-11"/>
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
				 <input  value="" id="" nullmsg="请输入安全和功能检测验收结论"    placeholder="请输入安全和功能检测验收结论" type="text"  fieldname="FB_AQGN_YS" class="col-xs-11 col-sm-11"/>
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
				 <input  value="" id="" nullmsg="请输入观感质量验收验收结论"     placeholder="请输入观感质量验收验收结论" type="text"  fieldname="FB_GGZL_YS" class="col-xs-11 col-sm-11" />
				</div>
			</div>
			
			<div class="form-group  group_YB_YSBW" style="display:none" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					隐蔽工程验收部位：<%--<span class="required-indicator">*</span>--%>
				</label>
				<div class="col-sm-10">
				 <input  value="" id="" nullmsg="请输入隐蔽工程验收部位"     placeholder="请输入隐蔽工程验收部位" type="text"  fieldname="YB_YSBW" class="col-xs-11 col-sm-11"/>
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
				 <input  class="REPORT_DATE1" type="text" id="ZX_KGRQ" data-date-format="yyyy-mm-dd" nullmsg="请输入专项工程开工日期"  style=" height: 25px;border-radius: 8px 8px 8px 8px; border: 1px solid #d1d2d9;"    placeholder="请输入专项工程开工日期"   fieldname="ZX_KGRQ" class="col-xs-11 col-sm-10"/>
				</div>
			</div>
			
			<div class="form-group group_ZX_WCRQ" style="display:none">
	  			<label class="col-sm-2 control-label no-padding-right" >
					专项工程完成日期：<span class="required-indicator">*</span> 
				</label>
				<div class="col-sm-10">
				 <input    class="REPORT_DATE1" type="text" id="ZX_WCRQ" data-date-format="yyyy-mm-dd" nullmsg="请输入专项工程完成日期" style="height: 25px;border-radius: 8px 8px 8px 8px; border: 1px solid #d1d2d9;"    placeholder="请输入专项工程完成日期"    fieldname="ZX_WCRQ" class="col-xs-11 col-sm-10"/>
				</div>
			</div>
			
			<div class="form-group  group_ZX_GCNR" style="display:none">
	  			<label class="col-sm-2 control-label no-padding-right" >
					专项工程工程内容：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-10">
				 <input   id="ZX_GCNR" nullmsg="请输入专项工程工程内容"    placeholder="请输入专项工程工程内容" type="text"  fieldname="ZX_GCNR" class="col-xs-11 col-sm-11"/>
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
				 <input  value="" datatype="*" id="JCJG" nullmsg="请输入检查结果"    placeholder="请输入检查结果" type="text"  fieldname="JCJG" class="col-xs-11 col-sm-11"/>
				</div>
			</div>
			
			<div class="form-group  group_YSJL" style="display:none" >
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
				 <input  class="REPORT_DATE1" id="YSRQ" type="text" datatype="*" data-date-format="yyyy-mm-dd" style="height: 25px;border-radius: 8px 8px 8px 8px; border: 1px solid #d1d2d9;"  fieldname="YSRQ" class="col-xs-11 col-sm-11"/>
				</div>
			</div>
		   <div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					 复查结论：  <span class="required-indicator">*</span> 
				</label>
				<div class="col-sm-10">
				 <input  value="" id="" datatype="*" nullmsg="请输入 复查结论"    placeholder="请输入 复查结论" type="text"  fieldname="FCJL" class="col-xs-11 col-sm-11"/>
				</div>
			</div>
            <div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					 复查日期 ：<span class="required-indicator">*</span>
				</label>
				 <div class="col-sm-10"> 
				 <input  class="REPORT_DATE_ONE" id="FCRQ" type="text" datatype="*" data-date-format="yyyy-mm-dd" nullmsg="请输入复查日期 " placeholder="请输入 复查日期" style="height: 25px;border-radius: 8px 8px 8px 8px; border: 1px solid #d1d2d9;"    fieldname="FCRQ" class="col-xs-11 col-sm-11"/>
				 </div>
			</div>
			
	  		<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					备注：
				</label>
				<div class="col-sm-10">
					 <textarea autofocus="autofocus"  nullmsg="备注"  placeholder="请输入备注" rows="4" type="text"  fieldname="DESCRIPTION" class="col-xs-11 col-sm-11" ></textarea>
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
						<tbody   showType="width:120px;height:120px;quanxian:true;heightss:160px;heights:40px;del:true;attr:multi;preview:false" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery"  file_type="10701"></tbody>
					</table>
				</div>
			</div>
     </form>
     
	</div>

  <div class="modal-footer"> 
    <button id="addYanshouEditSave" class="btn btn-1 btn-sms btn-round" type="button">&nbsp;保存&nbsp;</button>
    <button id="btnCloseXcJd"  class="btn btn-2 btn-sms pull-right btn-round" data-dismiss="modal" aria-hidden="true">关闭</button>
  </div>
</div>
</div>
<div id="XMZK-XMMC" class="modal"></div>

<jsp:include page="/jsp/file_upload/fileupload_util.jsp" flush="true" />
<%@ include file="/jsp/framework/common/basic_scripts.jsp"%>
<script src="${pageContext.request.contextPath }/assets/js/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/sys_resources/js/common/Form2Json.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/sys_resources/plugins/lightbox/jquery.min.js"></script>		
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/sys_resources/plugins/lightbox/jquery.lightbox.min.js"></script>
	
<script type="text/javascript"> 
var validform;
var controlletnameUrl="${pageContext.request.contextPath }/projectaccept/pmYanShouController";
var controllerSonUrl ="${pageContext.request.contextPath }/projectaccept/pmYanShouDetailController";
var scripts =[null]; 
function selectGuifan1(){
	var project_company_uid=$("#SGDW").val();
	window.open("${pageContext.request.contextPath }/jsp/business/danwei/xmdanwei-select.jsp?project_company_uid="+project_company_uid+"&uid=1");
}
function selectGuifan2(){
	var project_company_uid=$("#FBDW").val();
	window.open("${pageContext.request.contextPath }/jsp/business/danwei/xmdanwei-select.jsp?project_company_uid="+project_company_uid+"&uid=2");
}
gradeChange();

function gradeChange(){
	var sel = '<%=YANSHOU_TYPE%>';
	var fb = $("<option value='FB' >分部工程验收</option>");
    var fx = $("<option value='FX' >分项工程验收</option>"); 
    var zx = $("<option value='ZX' >专项工程验收</option>");
    var yb = $("<option value='YB' >隐蔽工程验收</option>");
    var jg = $("<option value='JG' >竣工总验收</option>"); 
    var $selector = $(".selector");
    $selector.empty();
	if(sel=='FB'){ 
		$selector.append(fb);
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
	
	if(sel=='FX'){
		$selector.append(fx);
	}
	
	if(sel=='JG'){
		$selector.append(jg);
	}
	
	if(sel=='YB'){
		$selector.append(yb);
		$(".group_YB_YSBW").show();
		$(".group_YB_YSYJ").show(); 
		$(".group_YB_YSNR").show();
	}else{
		$(".group_YB_YSBW").hide();
		$(".group_YB_YSYJ").hide(); 
		$(".group_YB_YSNR").hide();
	}
	
	if(sel=='ZX'){
		$selector.append(zx);
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
	
    
	if(sel=='FB'||sel=='FX'||sel=='YB'){
		$(".group_YSJL").show(); 
	}else{
		$(".group_YSJL").hide(); 
	} 
	//清空表格数据 
	$("#yanshouDetailtable tbody tr").eq(0).nextAll().remove(); 
	//form 下面的所有select禁用 
	$("#yanshouseeFormResult select").attr("disabled","disabled");
}
 
ace.load_ajax_scripts(scripts, function() { 
	DatePicker.datepicker(".REPORT_DATE1");
	DatePicker.datepicker(".REPORT_DATE_ONE");
});
function selectcompany1(){
	var project_company_uid = $("#SGDW").val();
	var company_name="";
	$.ajax({
		url : "${pageContext.request.contextPath }/buprojectcompany/buProjectCompanyController?queryid",
		data : {"project_company_uid":project_company_uid},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			 msg = eval("("+response.msg+")");
			 company_name = msg.response.data[0].COMPANY_NAME;
			 $("#SGDWS").val(company_name);
		}
	})
	
}
function selectcompany2(){
	var project_company_uid = $("#FBDW").val();
	var company_name="";
	$.ajax({
		url : "${pageContext.request.contextPath }/buprojectcompany/buProjectCompanyController?queryid",
		data : {"project_company_uid":project_company_uid},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			 msg = eval("("+response.msg+")");
			 company_name = msg.response.data[0].COMPANY_NAME;
			 $("#FBDWS").val(company_name);
		}
	})
	
}
$(function() {	  
    setFormValues();
    selectcompany1();
    selectcompany2();
  //删除多余的 div 避免弹出图片时出现多层
	var div1 = $(".jquery-lightbox-overlay").eq(0);
	var div2 = $(".jquery-lightbox-move").eq(0); 
	$(".jquery-lightbox-overlay").remove();
	$(".jquery-lightbox-move").remove();
	$("body").append(div1);
	$("body").append(div2);
	
	validform=FormValid.validbyformid(yanshouseeFormResult); 
	$("#addYanshouEditSave").click(function(){
		 var date1= $("#YSRQ").val();//验收日期
		  var date2= $("#FCRQ").val();//复查日期
		  var d1 = new Date(date1.replace(/\-/g, "\/")); 
			 var d2 = new Date(date2.replace(/\-/g, "\/"));
		  if(validform.check()&d2>d1){
			if($("#yanshouseeFormResult").validationButton()) {
				//生成json串
				var data = Form2Json.formToJSON(yanshouseeFormResult);
				//组成保存json串格式
				var data1 = defaultJson.packSaveJson(data); 
				$.ajax({
					url :controlletname+"?update",
					data : data1,
					cache : false,
					async :	false,
					dataType : "json",  
					type : 'post',
					success : function(response) { 
				      xAlert("信息提示","提交成功",1);
					  $("#btnCloseXcJd").click();
					  jQuery("#grid_table").jqGrid().trigger("reloadGrid"); 
					}
				});  
			}
		   }else {
			   xAlert("信息提示","验收日期小于复查日期",1);
			   return;
		   }
		 
    }); 
});

function setFormValues(){
	var data = combineQuery.getQueryCombineData(yanshouSeeForm,null,null);
	var data1={msg:data};
	$.ajax({
		url : "${pageContext.request.contextPath}/projectaccept/pmYanShouController?query",
		data : data1,
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			var resultobj = defaultJson.dealResultJson(response.msg); 
			$("#yanshouseeFormResult").setFormValues(resultobj);
			queryFileData('PM_YANSHOU',resultobj.YANSHOU_UID);
			$.ajax({
				url : "${pageContext.request.contextPath}/projectaccept/pmYanShouDetailController?querySon",
				data : {YANSHOU_UID:'<%=YANSHOU_UID%>'},
				cache : false,
				async :	false,
				dataType : "json",  
				type : 'post',
				success : function(response) {
					//var resultobj = defaultJson.dealResultJson(response.msg);
					//console.info(response.obj);
					var obj = response.obj; 
					obj.forEach(function(obj){ 
						var $tr = $("<tr style='height:auto'></tr>");
						$tr 
						.append($("<td style='display:none'></td>").append("<input type='hidden' name='YANSHOU_DETAIL_UID' fieldname='YANSHOU_DETAIL_UID' value="+obj.YANSHOU_DETAIL_UID+">"))
						.append($("<td class='col-sm-2' style='padding:26px;'></td>").append("<input  type='text' class='col-sm-12 col-xs-12' readOnly='readOnly' name='XUHAO' fieldname='XUHAO' value="+obj.XUHAO+">")) 
						.append($("<td class='col-sm-2' style='padding:26px;'></td>").append("<input  type='text' class='col-sm-12 col-xs-12' name='DETAIL_NAME' fieldname='DETAIL_NAME' value="+obj.DETAIL_NAME+">"))
						.append($("<td class='col-sm-2' style='padding:26px;'></td>").append("<input  type='text' class='col-sm-12 col-xs-12' name='DETAIL_NUMS'  datatype='/^[\\d]{1,8}(\\.[\\d]{1,2})?$/' errormsg='可以填最多8位整数加最多2位小数' fieldname='DETAIL_NUMS' value="+obj.DETAIL_NUMS+">"))
						.append($("<td class='col-sm-2' style='padding:26px;'></td>").append("<input  type='text' class='col-sm-12 col-xs-12' name='DETAIL_BUWEI' fieldname='DETAIL_BUWEI' value="+obj.DETAIL_BUWEI+">"))
						.append($("<td class='col-sm-2'></td>").append("<textarea rows='2'   class='col-sm-12 col-xs-12'   multiline='true'  name='DETAIL_JCJG' fieldname='DETAIL_JCJG'>"+obj.DETAIL_JCJG+"</textarea>"))
						.append($("<td class='col-sm-2'></td>").append("<textarea rows='2'   class='col-sm-12 col-xs-12'   multiline='true'  name='DETAIL_YSJL' fieldname='DETAIL_YSJL'>"+obj.DETAIL_YSJL+"</textarea>")); 
						$("#yanshou-see-table").append($tr);
					});  
				}
			});
		}
	});
}


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