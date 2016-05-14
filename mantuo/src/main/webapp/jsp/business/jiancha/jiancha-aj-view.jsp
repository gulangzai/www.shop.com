<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ccthanking.framework.common.User"%>
<%@ page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext" %>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>

<%
 String jc_uid = request.getParameter("jc_uid");
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
    	<h3 id="myModalLabel" class="blue bigger">修改安全检查标准</h3>
  </div>
  <div class="modal-body">

 	  <form method="post" role="form" class="form-horizontal"  id="executeFrm" > 
      	 <input  type="hidden" fieldname="JIANCHA_BZ_UID" />
	  		<div class="form-group" >
		  		<label class="col-sm-2 control-label no-padding-right">
					检查级别：
				</label>
				<div class="col-sm-10" id="jcjb">
					
				</div>	 
	  		
			</div>
	  		<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					对应标准规范：
				</label>
				<div class="col-sm-10">
					 <input id="gfname" type="text"  class="col-xs-11 col-sm-11" >
					 <input  type="hidden" id="BZGF_UID"  fieldname="BZGF_UID"  class="col-xs-11 col-sm-11" >
				</div>
			</div>
	  		<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" >
					检查内容：
				</label>
				<div class="col-sm-10">
					 <textarea id="CONTENT"  datatype="*" type="text"  fieldname="CONTENT" class="col-xs-11 col-sm-11" ></textarea>
					
				</div>
			</div>
	
     </form>
     
	</div>

  <div class="modal-footer">
    
    <button class="btn btn-2 btn-sms pull-right" id="btnClose" data-dismiss="modal" aria-hidden="true">关闭</button>
  </div>
</div>
</div>
<form method="post" id="queryFormById"  >
   <table class="B-table" width="100%">
   <!--可以再此处加入hidden域作为过滤条件 -->
   	<TR  style="display:none;">
     	<TD class="right-border bottom-border">
      	<INPUT type="text"  kind="text" id="JIANCHA_BZ_UID"  fieldname="JIANCHA_BZ_UID" value="<%=jc_uid%>" operation="="/>
      	</TD>
		<TD class="right-border bottom-border">
		</TD>
     </TR>
   </table>
</form>	
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/sys_resources/js/common/Form2Json.js"> </script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/common/convertJson.js"> </script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/common/combineQuery.js"> </script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/common/default.js?version=20131206"> </script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/common/bootstrap-validation.js"> </script>
<script type="text/javascript">
var validform;
var controlletname="${pageContext.request.contextPath }/pmjiancha/pmJianchaBzController";
//点击保存按钮
$(function() {	
	init();
	$(":input").attr("disabled","disabled");
	$("#btnClose").removeAttr("disabled");
	validform=FormValid.validbyformid(executeFrm);
	
	$("#addsaveUserInfo").click(function() {
	  if(validform.check()){
		if($("#executeFrm").validationButton()) {
			//生成json串
			var data = Form2Json.formToJSON(executeFrm);
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
					xAlert("信息提示","修改成功",1);
					$("#btnClose").click();
					 jQuery("#grid_table").jqGrid().trigger("reloadGrid");
			      }
			});
			
		 }
	   }else {
		   return;
	   }
	});
});

function init(){
    setFormValues();
}
function setFormValues(){
	var data = combineQuery.getQueryCombineData(queryFormById,null,null);
	var data1 = {
		msg : data
	};
	$.ajax({
		url : controllername+"?query",
		data : data1,
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			var resultobj = defaultJson.dealResultJson(response.msg);
			getJcjb(resultobj.JIANCHA_LEVEL_UID);
			$("#gfname").val(resultobj.NODE_CONTENT);
			$("#executeFrm").setFormValues(resultobj);	
		
		}
	});
}

function getJcjb(jcjbUid){
	$.ajax({
		url :controlletname+"?queryJcjb",
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			var html = "";
			$(response.obj).each(function(index,element){
				if(element.JIANCHA_LEVEL_UID==jcjbUid){
					html += "<input type='radio' style='margin-left:15px;' checked='checked' name='JIANCHA_LEVEL' fieldname='JIANCHA_LEVEL_UID' value='"+element.JIANCHA_LEVEL_UID+"'>"+element.LEVEL_NAME;
				}else{
					html += "<input type='radio' style='margin-left:15px;' name='JIANCHA_LEVEL' fieldname='JIANCHA_LEVEL_UID' value='"+element.JIANCHA_LEVEL_UID+"'>"+element.LEVEL_NAME;
				}
				
			});
			$('#jcjb').html(html);
	      }
	});
}

function selectGuifan(){
	var guifan_uid = $("#BZGF_UID").val();
	window.open("${pageContext.request.contextPath }/jsp/business/jiancha/guifan-select.jsp?guifan_uid="+guifan_uid);
}

//回调
function doCallback(str){
	var strs= new Array(); //定义一数组 
	strs=str.split(","); //字符分割 
	$("#BZGF_UID").val(strs[0]);
	$("#gfname").val(strs[1]);
	
}
</script>
		