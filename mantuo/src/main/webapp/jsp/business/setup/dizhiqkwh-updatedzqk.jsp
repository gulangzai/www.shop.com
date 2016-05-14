<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ccthanking.framework.common.User"%>
<%@ page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext" %>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>

<%
 String DIZHI_UID =request.getParameter("DIZHI_UID");
 %>
<!-- Modal -->
<div class="modal-dialog width-50 height-auto">
  <div class="modal-content" >
   		<div class="widget-header widget-header-large">
   		<div class="widget-toolbar">
			<a href="#" data-action="close" data-dismiss="modal">
				<i class="ace-icon fa fa-times"></i>
				</a>
		</div>
    	<h3 id="myModalLabel" class="blue bigger">修改地质情况</h3>
  </div>
  <div class="modal-body">

 	  <form method="post" role="form" class="form-horizontal"  id="executeFrm" > 
      	 <input type="hidden" name="DIZHI_UID" fieldname="DIZHI_UID" value="<%=DIZHI_UID %>" />
	  		<div class="form-group" >
		  		<label class="col-sm-4 control-label no-padding-right">
				         土层层号：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-6">
					 <input datatype="*" 
					 nullmsg = "土层层号不可为空"  placeholder="请输入土层层号" type="text" name="TUCENG_NUM" id="TUCENG_NUM_ONE" fieldname="TUCENG_NUM"  class="col-xs-12 col-sm-12" />
				</div>
			  </div>
			  <div class ="form-group">
	  		    <label class="col-sm-4 control-label no-padding-right">
				         土层名称：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-6">
					 <input datatype="*"  nullmsg = "土层名称不可空"  placeholder="请输入土层名称" type="text" name="TUCENG_NAME" id="TUCENG_NAME_ONE" fieldname="TUCENG_NAME"  class="col-xs-12 col-sm-12" />
				</div>
			</div>
	  		
			<div class="form-group" >
		  		<label class="col-sm-4 control-label no-padding-right">
					层顶深（m）：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-6">
					 <input datatype="/^[\+-]{0,1}(\d{0,4})\.(\d{1,2})$|^[\+-]{0,1}(\d{0,4})$/" errormsg="输入的数字格式不对，小数最大值为9999.99，小数点后只能保留2位小数" 
					  nullmsg = "层顶深不可为空"  placeholder="请输入层顶深" type="text" name="DEEP" id="DEEP_ONE" fieldname="DEEP" class="col-xs-12 col-sm-12" />
				</div>
			</div>
			<div class ="form-group">
	  		   <label class="col-sm-4 control-label no-padding-right">
					层顶标高（m）：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-6">
					 <input datatype="/^[\+-]{0,1}(\d{0,4})\.(\d{1,2})$|^[\+-]{0,1}(\d{0,4})$/" errormsg="输入的数字格式不对，小数最大值为9999.99，小数点后只能保留2位小数" 
					  nullmsg = "层顶标高不可为空"  placeholder="请输入层顶标高" type="text" name="HEIGHT" id="HEIGHT_ONE" fieldname="HEIGHT" class="col-xs-12 col-sm-12" />
				</div>
			</div>
			
			<div class ="form-group">
			    <label class="col-sm-4 control-label no-padding-right">
				        重度（KN/m3）：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-6">
					 <input datatype="/^[\+-]{0,1}(\d{0,4})\.(\d{1,2})$|^[\+-]{0,1}(\d{0,4})$/" errormsg="输入的数字格式不对，小数最大值为9999.99，小数点后只能保留2位小数" 
					  nullmsg = "重度不可为空"  placeholder="请输入重度" type="text" name="WEIGHT" id="WEIGHT_ONE" fieldname="WEIGHT" class="col-xs-12 col-sm-12" />
				</div>
	       </div>
			
     </form>
     
	</div>

  <div class="modal-footer">
    <button class="btn btn-success btn-sm" id="adddizhiInfo">保存</button>
    <button class="btn btn-danger btn-sm pull-right" id="btnClose" data-dismiss="modal" aria-hidden="true">关闭</button>
  </div>
</div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath }/assets/sys_resources/js/common/Form2Json.js"> </script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/common/convertJson.js"> </script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/common/combineQuery.js"> </script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/common/default.js?version=20131206"> </script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/common/bootstrap-validation.js"> </script>
<script type="text/javascript">
var validform;
var controlletname="${pageContext.request.contextPath }/dizhi/pmDizhiController";
//点击保存按钮
$(function() {	
var Uid = "<%=DIZHI_UID%>";
	validform=FormValid.validbyformid(executeFrm);
	setFormValues();
	$("#adddizhiInfo").click(function() {
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
					xAlert("信息提示","新增成功",1);
					//关闭页面 刷新 数据
					$("#btnClose").click();
					jQuery("#grid_table_dz").jqGrid().trigger("reloadGrid");
			      }
			});
			
		 }
	   }else {
		   return;
	   }
	});
});


function  setFormValues(){
 	      $.ajax({
				url :controlletname+'?queryDzById&DIZHI_UID='+"<%=DIZHI_UID%>",
				cache : false,
				async :	false,
				dataType : "json",  
				type : 'post',
				success : function(result) {
					if(result.obj!=null&&result.obj!=""){
							$.each(result.obj,function(){
							    $("#TUCENG_NAME_ONE").val(this.TUCENG_NAME);
							    $("#HEIGHT_ONE").val(this.HEIGHT);
							    $("#DEEP_ONE").val(this.DEEP);
							    $("#TUCENG_NUM_ONE").val(this.TUCENG_NUM);
							    $("#WEIGHT_ONE").val(this.WEIGHT);
							  
						});

			      }
			   }
			});

}

</script>
		
		