<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ccthanking.framework.common.User"%>
<%@ page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext" %>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>

<div class=" width-90 height-auto"  >
<div>
  <div class="modal-body">
 	  <form method="post" role="form" class="form-horizontal"  id="executeJKXX" > 
      	 <input id="JKPROJECT_UID" type="hidden" name="PROJECT_UID" fieldname="PROJECT_UID" value="" />
      	 <input id="DISTRICT" type="hidden" name="DISTRICT" fieldname="DISTRICT"/>
      	 
	  		<div class="form-group" >
		  		<label class="col-sm-2 control-label no-padding-right">
					项目名称：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-6">
					 <input  datatype="*" nullmsg = "请输入项目名称！"  placeholder="请输入项目名称！" 
					 type="text" name="PROJECT_NAME" id="PROJECT_NAME" fieldname="PROJECT_NAME"  style="width: 94%;" />
				</div>
	  		
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label no-padding-right">
					基坑深度：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-6">
				<!-- 验证码 只能验证 保留2位小数的格式 -->
					 <input   datatype="/^[0-9]{0,3}\.(\d{1,2})$|^\d{0,3}$/" errormsg="输入的数字格式不对，小数最大值为999.99，小数点后只能保留2位小数" 
					  nullmsg="请输入基坑深度保留2位小数"  placeholder="请输入基坑深度，格式如：99.20" 
					 type="text" name="JK_DEEP" id="JK_DEEP" fieldname="JK_DEEP" class="col-xs-11 col-sm-11"  style="width: 94%;"/><span>m</span>
				</div>
			</div>
			
			<div class="form-group" >
				 <label class="col-sm-2 control-label no-padding-right">
					基坑等级：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-6">
					<select   class="form-control"  id="JK_LEVEL"  fieldname="JK_LEVEL" nullmsg="请选择基坑的等级" onchange="selectDENGJI(this.id)" >
						<option value="1" selected="selected">一级</option>
						<option value="2">二级</option>
						<option value="3">三级</option>
					</select>
				</div>
		    </div>
			
			
	  		<div class="form-group" >
				 <label class="col-sm-2 control-label no-padding-right">
					支护结构类型：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-6">
					<select  class="form-control"  id="JKZH_STRUC_TYPE" datatype="*" fieldname="JKZH_STRUC_TYPE" nullmsg="请选择支护结构类型" >
						<option value="放坡开挖" selected="selected">放坡开挖</option>
						<option value="围护墙深层搅拌水泥土">围护墙深层搅拌水泥土</option>
						<option value="高压旋喷桩">高压旋喷桩</option>
				        <option value="槽钢钢板桩">槽钢钢板桩</option>
					    <option value="转孔灌注 ">转孔灌注</option>
						<option value="地下连续墙">地下连续墙</option>
						<option value="土钉墙">土钉墙</option>
						<option value="SMW工法">SMW工法</option>
					</select>
				</div>
		   </div>
     </form>
   </div>
  <div class=" widget-footer">
   		<div class="" style="float:right;padding-right:60px;" >
		    <button class="btn btn-success btn-sm" id="addsaveProjectJKXX">保存</button>
		</div>
  </div>
</div>
</div>
<jsp:include page="/jsp/file_upload/fileupload_util.jsp" flush="true" />	
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/sys_resources/js/common/Form2Json.js"> </script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/common/convertJson.js"> </script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/common/combineQuery.js"> </script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/common/default.js?version=20131206"> </script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/common/bootstrap-validation.js"> </script>
<script type="text/javascript">
var validform;
var controlletname="${pageContext.request.contextPath }/project/buProjectController";
//点击保存按钮
$(function() {	
	var xmUid = $("#project_uid").val();
	$("#JKPROJECT_UID").val(xmUid);
    setFormValues();
	validform=FormValid.validbyformid(executeJKXX);
	$("#addsaveProjectJKXX").click(function() {
	  if(validform.check()){
		if($("#executeJKXX").validationButton()) {
			//生成json串
			var data = Form2Json.formToJSON(executeJKXX);
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
					
			      }
			});
		 }
	   }else {
	   	xAlert("信息提示","填写的数字格式不正确,请重新确认！",1);
		   return;
	   }
	});
});

function selectDENGJI(id){
  var dengji = ($("#"+id).val());
  $("#JK_LEVEL").val(dengji);  

}

function setFormValues(){
 	      $.ajax({
				url :controlletname+'?queryDetailById&PROJECT_UID='+$("#project_uid").val(),
				cache : false,
				async :	false,
				dataType : "json",  
				type : 'post',
				success : function(result) {
					if(result.obj!=null&&result.obj!=""){
							$.each(result.obj,function(){
							    $("#PROJECT_NAME").val(this.PROJECT_NAME);
							    $("#JK_DEEP").val(this.JK_DEEP);
							    $("#JK_LEVEL").val(this.JK_LEVEL);
							    $("#JKZH_STRUC_TYPE").val(this.JKZH_STRUC_TYPE);
							    $("#DISTRICT").val(this.DISTRICT);
							   
						});

			      }
			   }
			});

}

</script>