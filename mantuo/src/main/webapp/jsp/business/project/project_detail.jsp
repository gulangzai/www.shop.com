<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ccthanking.framework.common.User"%>
<%@ page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext" %>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<!-- Modal -->
<%
  String PROJECT_UID = request.getParameter("PROJECT_UID");
  String PROJECT_NAME = request.getParameter("PROJECT_NAME");
 %>
 
<style>
.form-group>label[class*=col-] {
    margin-bottom: 4px;
    padding-top: 1px;
}
</style>
</head>
<body>
<div class="modal-dialog width-60 height-auto">
  <div class="modal-content" >
   		<div class="widget-header widget-header-large">
   		<div class="widget-toolbar">
			<a href="#" data-action="close" data-dismiss="modal">
				<i class="ace-icon fa fa-times"></i>
				</a>
		</div>
		<!-- class center  -->
    	<h3 id="myModalLabel" class="blue bigger ">项目详细信息</h3>
  </div>
  <div class="modal-body">

 	  <form method="post" role="form" class="form-horizontal"  id="executeFrm" >      	
      	<input type="hidden" id="target_uid" fieldname="target_uid"  />
	  		<div class="form-group" >
		  		<label class="col-sm-2 control-label no-padding-right">
					项目名称：<span class="required-indicator"></span>
				</label>
				<div class="col-sm-3">
				   <span ><%=PROJECT_NAME%></span>
				</div>
			
		  		<label class="col-sm-1 control-label no-padding-right">
				</label>
				
				<div class="col-sm">
				<img width="300" height="120" src="" id="image" >
					
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label no-padding-right" for="form-field-1">
					项目简介：
				</label>
				<div class="col-sm-10">
				   <span  id="PROJECT_DESC_ONE"  readonly="readonly"></span>
				</div>
			</div>
			
	  		<div class="form-group" >
	  			<label class="col-sm-2 control-label no-padding-right" for="MIMA">
					项目地址：<span class="required-indicator"></span>
				</label>
				<div class="col-sm-10">
				  <span id="PROJECT_ADDRESS_ONE"></span>
				</div>
	  	
			</div>
			
	  		<div class="form-group" >
		  		<label class="col-sm-2 control-label no-padding-right">
					开工日期：
				</label>
				<div class="col-sm-4">
				 <span id="BEGIN_DATE_ONE"></span>
				</div>
		  		<label class="col-sm-2 control-label no-padding-right">
					完工日期：
				</label>
				<div class="col-sm-4">
				 <span id="END_DATE_ONE"></span>
				</div>
	  		
			</div>			
	  		<div class="form-group" >
		  		<label class="col-sm-2 control-label no-padding-right">
					占地面积：<span class="required-indicator"></span>
				</label>
				<div class="col-sm-4">
				  <span id="ZHANDI_MIANJI_ONE"></span>
				</div>
		  		<label class="col-sm-2 control-label no-padding-right">
					建筑面积：<span class="required-indicator"></span>
				</label>
				<div class="col-sm-4">
				  <span id="JIANZHU_MIANJI_ONE" ></span>
				</div>
	  		
			</div>			
	  		<div class="form-group" >
		  		<label class="col-sm-2 control-label no-padding-right">
					总投资：<span class="required-indicator"></span>
				</label>
				<div class="col-sm-4">
				  <span id="ZONG_TOUZI_ONE" ></span>
				</div>
			</div>			
	  		<div class="form-group" >
		  		<label class="col-sm-2 control-label no-padding-right">
					创建人：<span class="required-indicator"></span>
				</label>
				<div class="col-sm-4">
				  <span id="CREATED_USER_ONE" ></span>
				</div>
				<label class="col-sm-2 control-label no-padding-right">
					创建时间：<span class="required-indicator"></span>
				</label>
				<div class="col-sm-4">
				 <span id="CREATED_DATE_ONE"></span>
				</div>
			</div>	
     </form>
	</div>
  <div class="modal-footer">
    <button class="btn btn-danger btn-sm pull-right" id="btnClose" data-dismiss="modal" aria-hidden="true">关闭</button>
  </div>
</div>
</div>
</body>
<jsp:include page="/jsp/file_upload/fileupload_util.jsp" flush="true" />	
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/sys_resources/js/common/Form2Json.js"> </script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/common/convertJson.js"> </script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/common/combineQuery.js"> </script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/common/default.js?version=20131206"> </script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/common/bootstrap-validation.js"> </script>
<script type="text/javascript">
var validform;
//点击保存按钮
$(function() {	
	var pId="<%=PROJECT_UID%>";
	setFormValues(pId);
	
});

function setFormValues(pId){
 	      $.ajax({
				url :'${ctx}/project/buProjectController?queryDetailById&PROJECT_UID='+pId,
				cache : false,
				async :	false,
				dataType : "json",  
				type : 'post',
				success : function(result) {
					if(result.obj!=null&&result.obj!=""){
							$.each(result.obj,function(){
							    $("#PROJECT_DESC_ONE").html(this.PROJECT_DESC);
							    $("#PROJECT_ADDRESS_ONE").html(this.PROJECT_ADDRESS);
							    $("#BEGIN_DATE_ONE").html(this.BEGIN_DATE);
							    $("#END_DATE_ONE").html(this.END_DATE);
							    $("#ZHANDI_MIANJI_ONE").html(this.ZHANDI_MIANJI);
							    $("#JIANZHU_MIANJI_ONE").html(this.JIANZHU_MIANJI);
							    $("#ZONG_TOUZI_ONE").html(this.ZONG_TOUZI);
							    $("#CREATED_USER_ONE").html(this.USER_NAME);
							    var re = /[\u4000-\uFFFF]/g;
					            var periods = this.CREATED_DATE.replace(re, '');
					            var str = periods.substr(0,10);
							    $("#CREATED_DATE_ONE").html(str);
						});
							
			      }
					
			   }
			});
 	     $.ajax({
				url : "${pageContext.request.contextPath }/fileUploadUtilController?queryFileByType&targetUid="+pId+"&file_type=10001",
				cache : false,
				async :	false,
				dataType : "json",
				success : function(result) {
					if(result.obj!=null&&result.obj!=""){
						$.each(result.obj,function(){
							url =  this.url;
						});
						$("#image").attr("src",url);
					}

				}
			});
}



</script>
</html>
		