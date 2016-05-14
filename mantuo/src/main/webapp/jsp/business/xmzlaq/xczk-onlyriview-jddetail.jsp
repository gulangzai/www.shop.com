<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ccthanking.framework.common.User"%>
<%@ page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext" %>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/assets/sys_resources/plugins/lightbox/themes/default/jquery.lightbox.css" />

<%
 String XC_UID = request.getParameter("XC_UID");
 String XS_UID = request.getParameter("XS_UID");
%>
<!-- Modal -->
<div class="modal-dialog width-60 height-auto">
  <div class="modal-content" >
   <div class="widget-header widget-header-large">
   		<div class="widget-toolbar">
			<a href="#" data-action="close" data-dismiss="modal">
				<i class="ace-icon fa fa-times"></i>
				</a>
		</div>
    	<h3 id="myModalLabel" class="blue bigger">查看进度工况</h3>
  </div>
  <div class="modal-body">
 	  <form method="post" role="form" class="form-horizontal"  id="executeFrmXcJd" > 
 	   <!-- 获取 项目 的 uid -->    	
      	 <input id="XCJDROJECT_UID" type="hidden" name="ROJECT_UID" fieldname="PROJECT_UID"  />
      	  <input  id="XM_PRJ_STRUC_UID"  type="hidden"  fieldname="PRJ_STRUC_UID" value="" />
      	  <%-- 中间表的主键id --%>
      	   <input  id="XIANCHANG_STRUC_UID"  type="hidden"  fieldname="XIANCHANG_STRUC_UID" value="<%=XS_UID%>" />
      	   <input type="hidden"  fieldname="XIANCHANG_UID" value="<%=XC_UID%>" />
	  	  <input type="hidden" id="target_uid" fieldname="target_uid"  />
	  		<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					现场状况类型：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
				 <select  datatype="*" disabled="disabled" style="width: 80%;" nullmsg="请选择类型" fieldname="GONGKUANG_TYPE" class="col-xs-11 col-sm-11" >
			        <option value="JD" >进度</option>
			      </select>
				</div>
				<!-- 
				<label class="col-sm-2 control-label no-padding-right" >
					现场状态：
				</label>
				<div class="col-sm-4">
					&nbsp;<input id="jddoing" type="radio" disabled="disabled" name="STATUS" fieldname="STATUS"  value="0">&nbsp;进行中
					
					&nbsp;<input id="jdfinish" type="radio" disabled="disabled" name="STATUS" fieldname="STATUS"  value="1" >&nbsp;已完成
				</div>
				 -->
			</div>
<!-- 
			<div class="form-group" >
			 
	  			<label class="col-sm-2 control-label no-padding-right" >
					施工部位：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
				 <input  id="getXMJGMC" disabled="disabled" style="width: 80%;" class="col-xs-11 col-sm-11" datatype="*" nullmsg="请选择项目现场的施工部位"  nullmsg="请选择现场施工部位"  placeholder="请选择项目现场的施工部位" type="text"  fieldname="STRUC_NAME" />
				</div>
				
				<label class="col-sm-2 control-label no-padding-right" >
					要求完成日期：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
				 <input  id="FINISH_DATE" disabled="disabled" style="width: 80%;" class="col-xs-11 col-sm-11" data-date-format="yyyy-mm-dd"  datatype="*" nullmsg="请选择要求完成日期"   nullmsg="请选择要求完成日期"  placeholder="请选择要求完成日期" type="text"  fieldname="FINISH_DATE" />
				</div>
			</div>
			-->
			<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					标题：
				</label>
				<div class="col-sm-10">
					 <textarea id="xcjdContent" disabled="disabled" autofocus="autofocus" datatype="*" rows="4" type="text"  fieldname="JINDU" class="col-xs-11 col-sm-11" ></textarea>
				</div>
			</div>
			
	  		<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					状况描述：
				</label>
				<div class="col-sm-10">
					 <textarea id="xcjdms" disabled="disabled"  autofocus="autofocus" datatype="*"  rows="4" type="text"  fieldname="DESCRIPTION" class="col-xs-11 col-sm-11" ></textarea>
				</div>
			</div>

     <%--文件类型及显示 上传文件 --%>
            <div class="form-group">
				<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
					现场进度状况：&nbsp;
				</label>
				<div class="col-sm-9">
					<table   role="presentation" class="table table-striped">
						<tbody onlyView="true" showType="width:120px;height:120px;quanxian:false;heightss:160px;heights:40px;del:false;attr:multi;preview:false;" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery"  file_type="10009" fjlb="media"></tbody>
					</table>
				</div>
		</div>
     </form>
     
	</div>

  <div class="modal-footer">
    <button id="btnClose123"  class="btn btn-2 btn-sms" data-dismiss="modal" aria-hidden="true">关闭</button>
  </div>
</div>
</div>
<div id="XMZK-XMMC" class="modal"></div>
<jsp:include page="/jsp/file_upload/fileupload_util.jsp" flush="true" />
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/sys_resources/plugins/lightbox/jquery.min.js"></script>		
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/sys_resources/plugins/lightbox/jquery.lightbox.min.js"></script>

<script type="text/javascript">
var validform;
var controlletnameUrl="${pageContext.request.contextPath }/pmxianchang/pmXianchangController";
$(function() {	
	
	//删除多余的 div 避免弹出图片时出现多层
	var div1 = $(".jquery-lightbox-overlay").eq(0);
	var div2 = $(".jquery-lightbox-move").eq(0);

	$(".jquery-lightbox-overlay").remove();
	$(".jquery-lightbox-move").remove();
	$("body").append(div1);
	$("body").append(div2);
});
(function () {
    var v17 = $.noConflict(true);
    window.$.v17 = v17;
 })();

(function ($) {
 	$('.lightbox').lightbox();
 })(window.$.v17);
//点击保存按钮
$(function() {	
	  initFormValue();
	  queryFileData('PM_XIANCHANG','<%=XC_UID%>');
});

function initFormValue(){
	   var xc_uid = "<%=XC_UID%>";
	   var qystatus = "";
		$.ajax({
				url :controlletnameUrl+"?queryXcQK",
				data : {"XC_UID":xc_uid},
				cache : false,
				async :	false,
				dataType : "json",  
				type : 'post',
				success : function(result) {
					if(result.obj.listxx!=null && result.obj.listxx!=""){
							$(result.obj.listxx).each(function(index,ele){
								//qystatus = ele.STATUS;
								//施工部位（结构表中主键uid和 结构的名称）
								$("#XM_PRJ_STRUC_UID").val(ele.PRJ_STRUC_UID);
								$("#getXMJGMC").val(ele.STRUC_NAME);
								$("#xcjdContent").html(ele.JINDU);
								$("#xcjdms").html(ele.DESCRIPTION);
								$("#FINISH_DATE").val(ele.FINISH_DATE);
								
							});
						}
					/*
					if(qystatus.indexOf("0") != -1){
						$("#jddoing").attr("checked","checked");
					 }else{
						$("#jdfinish").attr("checked","checked");
					}
					*/
			      }
			});
	
}
//图片放大
/*
$(document).ready(function(){
	$(".template-download img").click(function(){
	    if($(this).hasClass("fd")){
	        $(this).removeClass("fd");
	    }else{
	        $(this).addClass("fd");
	    }
	})
})
*/
</script>