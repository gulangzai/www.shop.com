<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ccthanking.framework.common.User"%>
<%@ page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext" %>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<!-- Modal -->
<div class="modal-dialog width-70 height-auto">
  <div class="modal-content" >
   		<div class="widget-header widget-header-large">
   		<div class="widget-toolbar">
			<a href="#" data-action="close" data-dismiss="modal">
				<i class="ace-icon fa fa-times"></i>
				</a>
		</div>
    	<h3 id="myModalLabel" class="blue bigger">添加项目信息</h3>
  </div>
  <div class="modal-body">

 	  <form method="post" role="form" class="form-horizontal"  id="executeFrm" >      	
      	
	  		<div class="form-group" >
		  		<label class="col-sm-2 control-label no-padding-right">
					项目名称：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-10">
					 <input datatype="*" nullmsg = "请输入项目名称"  placeholder="请输入项目名称" type="text" name="PROJECT_NAME" id="PROJECT_NAME" fieldname="PROJECT_NAME"  style="width: 97%;" />
				</div>
	  		
			</div>
	  		<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" for="MIMA">
					项目地址：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-10">
					 <input datatype="*" nullmsg="请输入项目地址"  placeholder="请输入项目地址" type="text" name="PROJECT_ADDRESS" id="PROJECT_ADDRESS" fieldname="PROJECT_ADDRESS" style="width: 97%;" />
				</div>
	  	
			</div>
	  		<div class="form-group" >
		  		<label class="col-sm-2 control-label no-padding-right">
					开工日期：
				</label>
				<div class="col-sm-4">
					 <input data-date-format="yyyy-mm-dd"  placeholder="请输入开工日期" type="text" name="BEGIN_DATE" id="BEGIN_DATE" fieldname="BEGIN_DATE" class="col-xs-11 col-sm-11"  />
				</div>
		  		<label class="col-sm-2 control-label no-padding-right">
					完工日期：
				</label>
				<div class="col-sm-4">
					 <input  data-date-format="yyyy-mm-dd" placeholder="请输入完工日期" type="text" name="END_DATE" id="END_DATE" fieldname="END_DATE" class="col-xs-11 col-sm-11"  />
				</div>
	  		
			</div>			
	  		<div class="form-group" >
		  		<label class="col-sm-2 control-label no-padding-right">
					占地面积(平方米)：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
					 <input datatype="*" nullmsg="请输入占地面积" placeholder="请输入占地面积" type="text" name="ZHANDI_MIANJI" id="ZHANDI_MIANJI" fieldname="ZHANDI_MIANJI" class="col-xs-11 col-sm-11"  />
				</div>
		  		<label class="col-sm-2 control-label no-padding-right">
					建筑面积(平方米)：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
					 <input datatype="*" nullmsg="请输入建筑面积"  placeholder="请输入建筑面积" type="text" name="JIANZHU_MIANJI" id="JIANZHU_MIANJI" fieldname="JIANZHU_MIANJI" class="col-xs-11 col-sm-11"  />
				</div>
	  		
			</div>			
	  		<div class="form-group" >
		  		<label class="col-sm-2 control-label no-padding-right">
					总投资(元)：<span class="required-indicator">*</span>
				</label>
				<div class="col-sm-4">
					 <input datatype="*" nullmsg="请输入总投资"  placeholder="请输入总投资" type="text" name="ZONG_TOUZI" id="ZONG_TOUZI" fieldname="ZONG_TOUZI" class="col-xs-11 col-sm-11"  />
				</div>
	  		
			</div>			
	  		<div class="form-group">
				<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
					项目简介：&nbsp;
				</label>
				<div class="col-sm-10">
					<textarea style="width: 97%;"  rows="5" id="PROJECT_DESC" fieldname="PROJECT_DESC"></textarea>
				</div>
			</div>
	  		
     </form>
     
	</div>

  <div class="modal-footer">
    <button class="btn btn-success btn-sm" id="addsaveUserInfo">保存</button>
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
//点击保存按钮
$(function() {	
	DatePicker.datepickerid("#BEGIN_DATE");
	DatePicker.datepickerid("#END_DATE");
	validform=FormValid.validbyformid(executeFrm);
	
	$("#addsaveUserInfo").click(function() {
	  if(validform.check()){
		if($("#executeFrm").validationButton()) {
			//生成json串
			var data = Form2Json.formToJSON(executeFrm);
			//组成保存json串格式
			var data1 = defaultJson.packSaveJson(data);
			
			$.ajax({
				url :'${ctx}/project/buProjectController?insert',
				data : data1,
				cache : false,
				async :	false,
				dataType : "json",  
				type : 'post',
				success : function(response) {
					xAlert("信息提示","新增成功",1);
					var a =window.parent;
					a.location.reload();
					window.close();
			      }
			});
			
		 }
	   }else {
		   return;
	   }
	});
});

</script>
		