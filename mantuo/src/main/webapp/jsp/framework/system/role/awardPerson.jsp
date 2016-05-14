<!DOCTYPE html>
<%@page import="com.copj.modules.utils.spring.SpringContextHolder"%>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ccthanking.framework.service.UserServiceCust" %>
<%@ page import="com.ccthanking.framework.service.impl.UserServiceCustImpl" %>
<%@ page import="java.util.HashMap" %>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<%
	String role_uid = request.getParameter("role_uid");
	String project_uid = request.getParameter("project_uid");
	UserServiceCust userService = SpringContextHolder.getBean("userServiceCustImpl", UserServiceCust.class);
	
	HashMap<String,String> map = new HashMap<String,String>();
	map.put("role_uid", role_uid);
	map.put("project_uid", project_uid);
	String[][] roleInfoArray = userService.queryPersonRole(map);
	int spLen = 0;
	int menuLen = 0;
	String dept_name = "";
	int kk = 1;
%>
<title>实例页面-查询</title>

</head>
<body>
<div class="modal-dialog width-50 height-auto">
	<div class="modal-content">
		<div class="widget-header widget-header-large">
			<div class="widget-toolbar">
				<a href="#" data-action="close" data-dismiss="modal">
					<i class="ace-icon fa fa-times"></i>
				</a>
			</div>
			<h3 id="myModalLabel" class="blue bigger">人员信息<span id="title_sp"></span></h3>
		</div>
		<div class="modal-body">

			<div class="widget-main no-padding">
				<table class="table table-striped table-bordered table-hover">

					<tbody>

					<%
						// 每行显示的个数
						int num = 3;
						// 角色总数
						int line = 0;
						int cols = 1;
						for(int i = 0; i < roleInfoArray.length; i++) {
			
			
							{
								if(cols==1)
								{
							%>
								<tr>
								
							
							<%  }
			
									%>
										<td class="">
									<%
										if(roleInfoArray[i][0].equals(roleInfoArray[i][1])) {
									%>
											<input type="checkbox" id="name" name="name" value="<%=roleInfoArray[i][0] %>=<%=roleInfoArray[i][2] %>" checked="checked"/>&nbsp;&nbsp;<font color="red"><%=roleInfoArray[i][2] %></font>
									<%
										} else {
									%>
											<input type="checkbox" id="name" name="name" value="<%=roleInfoArray[i][0] %>=<%=roleInfoArray[i][2] %>"/>&nbsp;&nbsp;<font color="#000000"><%=roleInfoArray[i][2] %></font>
									<%		
										}
									%>
										
										</td>
									<%			
								if(cols==num)
								{ 
									
								%>
								</tr>
								<%
								}
								cols++;
								if(cols>num)
								{
									cols = 1;
									line++;
								}
							}
						}
						
				
						
					%>
		

					</tbody>
				</table>
			</div>

		</div>

		<div class="modal-footer">
			<button class="btn btn-success btn-sm" id="saveRoleInfo">保存</button>
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
  var controllername= "${pageContext.request.contextPath }/userController";
var id = "<%=role_uid %>";
var name  = "";
$(function(){
	$('#saveRoleInfo').click(function() {
		var checkObj = $(":checkbox");
		var checkValue = "";
		for(var i = 0; i < checkObj.length; i++) {
			if(checkObj[i].checked == true) {
				checkValue += checkObj[i].value + ",";
			}
		}
		$('#checkValue').val(checkValue);
		var data = "{\"roleid\":\""+id+"\",\"rolename\":\""+name+"\",\"checkValue\":\""+checkValue+"\"}";
		//组成保存json串格式frmPost9
		var data1 = defaultJson.packSaveJson(data);
		defaultJson.doInsertJson(controllername + "?awardRoleToPerson", data1);
		jQuery("#grid_table").jqGrid().trigger("reloadGrid");
		$('#btnClose').click();
	});
	
});


</script>
</body>
</html>