<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ccthanking.framework.common.User"%>
<%@ page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext" %>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>

<%
 String PROJECT_UID = request.getParameter("PROJECT_UID");
 String NODE_TYPE = request.getParameter("NODE_TYPE");
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
    	<h3 id="myModalLabel" class="blue bigger"><p id="title"><p></h3>
  </div>
  <div class="modal-body">
		<form id="queryForm1">
		<input type="hidden"  fieldname="t.NODE_TYPE" operation="=" logic="and" value="<%=NODE_TYPE%>">
		<table   sortname="t.BZGF_UID" multiselect=false  rownum="10000" sortorder="asc"   id="bgrid_table" 
									action="${ctx}/pmjiancha/sysBzgfController?query" >
			<tr>
				<th name="NODE_TYPE"  width="6" maxlength="100" align="center">分类</th>
				<th name="NODE_CONTENT"  width="1" align="center">内容</th>
			</tr> 
		</table> 
     </form>
	</div>

  <div class="modal-footer">
    <button class="btn btn-1 btn-sms" id="addsaveUserInfo">保存</button>
    <button class="btn btn-2 btn-sms pull-right" id="btnClose" data-dismiss="modal" aria-hidden="true">关闭</button>
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
var controlletname="${pageContext.request.contextPath }/pmjiancha/pmBzgfController";
//点击保存按钮
$(function() {	
	var gridwidth = $(".modal-body").width();
	JqgridManage.initJqgrid(bgrid_table,queryForm1,gridwidth); 
});

</script>
		