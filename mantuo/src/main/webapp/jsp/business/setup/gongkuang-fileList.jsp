<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ccthanking.framework.common.User"%>
<%@ page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext" %>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>

<%
 String gongkuang_uid = request.getParameter("gongkuang_uid");
 String jindu = request.getParameter("jindu");
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
    	<h3 id="myModalLabel" class="blue bigger">模型文件上传历史</h3>
  </div>
  <div class="modal-body">
  	<table id="tab"  class="table table-striped table-bordered table-hover">
  	
  	</table>
  </div>
<!-- 
  <div class="modal-footer">
    <button class="btn btn-success btn-sm" id="addsaveUserInfo">保存</button>
    <button class="btn btn-danger btn-sm pull-right" id="btnClose" data-dismiss="modal" aria-hidden="true">关闭</button>
  </div>
 -->  
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
//点击保存按钮
$(function() {	
var gongkuang_uid = "<%=gongkuang_uid%>";
		$.ajax({
		url:"${pageContext.request.contextPath}/xmgk/pmGongkuangController?queryFileList",
		data:{'gongkuang_uid':gongkuang_uid}, 
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(result){
	    	var datalist = result.obj;
	    	var html = "";
	    	if(datalist!=""){
	    		html+="<thead><tr>";
	    		//html+="<th>工况</th>";
	    		html+="<th>文件名称</th>";
	    		html+="<th>上传人</th>";
	    		html+="<th>上传日期</th>";
	    		html+="</tr></thead>";
	    		html+="<tbody>";
	    		for(var i =0;i<datalist.length;i++){
	    			var dataObj = datalist[i];
	    			html+="<tr>";
	    			<%--html+="<td><%=jindu%></td>";--%>
	    			html+="<td>"+dataObj.FILE_NAME+"</td>";
	    			html+="<td>"+dataObj.USER_NAME+"</td>";
	    			html+="<td>"+dataObj.CREATE_DATE+"</td>";
	    			html+="<tr>";
	    			
	    		}
	    		html+="</tbody>";
	    		$('#tab').empty();
	    		$('#tab').html(html);
	    	}else{
	    		var html = "<tbody><tr><td><font color='red'>没有可显示的数据</font></td></tr></tbody>";
	    		$('#tab').empty();
	    		$('#tab').html(html);
	    	}
	    	
		}
	}); 
});

</script>
		