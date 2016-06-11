<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/commons/taglib.jsp" %>  
<%@ include file="/WEB-INF/view/commons/jsp/basic_scripts.jsp"%>

<link rel="stylesheet" href="${ctx}/assets/css/bootstrap.css" type="text/css" /> 

  <div class="modal-dialog">  
    <div class="modal-content">  
      <div class="modal-header">  
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>  
        <h4 class="modal-title">请添加联系方式</h4>  
      </div>  
      <div class="modal-body">  
           
           <form class="form-horizontal" role="form" id="executeFrm">
           
           	    <input type="hidden" name="F_ProductId"   class="form-control" datatype="*" placeholder="请填写商品名"  nullmsg="请填写商品名"  id="form-field-1"  value="${requestScope.productId}"/>
           
				   <div class="form-group">
				      <label class="col-sm-3 control-label no-padding-right"  for="form-field-1">商品名</label>
				      <div class="col-sm-7">
				        <input type="text" name="F_ProductName"  readOnly="true" class="form-control" datatype="*" placeholder="请填写商品名"  nullmsg="请填写商品名"  id="form-field-1"  value="${requestScope.productName}"/>
				      </div>
				   </div>
				   <div class="form-group">
				      <label class="col-sm-3 control-label no-padding-right"  for="form-field-1">姓名</label>
				      <div class="col-sm-7">
				        <input type="text" name="CONTACT_NAME" class="form-control" datatype="*" placeholder="请填写姓名"  nullmsg="请填写姓名" id="form-field-1" />
				      </div>
				   </div>
				   <div class="form-group">
				      <label class="col-sm-3 control-label no-padding-right"  for="form-field-1">联系方式</label>
				      <div class="col-sm-7">
				        <input type="text" name="CONTACT_TELE" class="form-control"  datatype="m" placeholder="请填写联系方式"  nullmsg="请填写联系方式" errormsg="请正确填写联系方式"  id="form-field-1" />
				      </div>
				   </div>
				   <div class="form-group">
				      <label class="col-sm-3 control-label no-padding-right"  for="form-field-1">联系地址</label>
				      <div class="col-sm-7">
				        <input type="text" name="CONTACT_ADDRESS" class="form-control" datatype="*" placeholder="请填写联系地址" placeholder="请填写联系地址" id="form-field-1" />
				      </div>
				   </div>
		   </form
           
      </div>  
      <div class="modal-footer">  
        <button type="button" class="btn btn-default closeBtn" data-dismiss="modal">关闭</button>  
        <button type="button" class="btn btn-primary" id="submitInfo">提交</button>  
      </div>  
    </div><!-- /.modal-content -->  
  </div><!-- /.modal-dialog -->  
  

  
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/sys_resources/js/common/Form2Json.js"> </script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/common/convertJson.js"> </script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/common/combineQuery.js"> </script>
<!--<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/common/default.js?version=20131206"></script>-->
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/common/bootstrap-validation.js"> </script>
  
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/FormValidate.js"> </script>
<script type="text/javascript"	src="${pageContext.request.contextPath }/assets/sys_resources/js/xAlert.js"> </script>
 
<script>
var controllerName = "${ctx}/spContactController";
var validform;

    $(function(){
    	
  	  $("#submitInfo").click(function() { 
  		   validform=FormValid.validbyformid(executeFrm); 
  		    if(validform.check()){
  			  //if($("#executeFrm").validationButton()) { 
  				//生成json串
  				//var data = Form2Json.formToJSON(executeFrm);
  				//组成保存json串格式
  				//var data1 = defaultJson.packSaveJson(data); 
  				$.ajax({
  					url :controllerName+"/insert.do",
  					data : $('#executeFrm').serialize(),
  					cache : false,
  					async :	false,
  					dataType : "json",  
  					type : 'post',
  					success : function(response) {
  						//xAlert("信息提示","新增成功",1); 
  						$(".closeBtn").click(); 
  						alert("信息提交成功");
  				      }
  				});  
  		   }else {
  			   return;
  		   } 
  		});
    });
</script>