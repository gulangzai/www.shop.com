<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp"%>
<%@ include file="/WEB-INF/jsp/commons/commons.jspf"%> 
<!DOCTYPE html>
<html lang="en">
	<head> 
		<meta charset="utf-8" />
		<title></title>
		<meta name="description" content="overview & stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" /> 
		
<script type="text/javascript">
	top.hangge();
	//保存
	function save(){
		if($("#roleName").val()==""){
			$("#roleName").focus();
			return false;
		}
			$("#form1").submit();
			$("#zhongxin").hide();
			$("#zhongxin2").show();
	}
	
	
</script>
	</head>
<body>
		<form action="role/add.do" name="form1" id="form1"  method="post">
		<input name="PARENT_ID" id="parent_id" value="${pd.parent_id }" type="hidden">
			<div id="zhongxin">
			<table>
				<tr>
					<td><input type="text" name="ROLE_NAME" id="roleName" placeholder="这里输入名称" title="名称"/></td>
				</tr>
				<tr>
					<td style="text-align: center;">
						<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
						<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
					</td>
				</tr>
			</table>
			</div>
		</form>
	
	<div id="zhongxin2" class="center" style="display:none"><img src="${ctxStatic}/images/jzx.gif" style="width: 50px;" /><br/><h4 class="lighter block green"></h4></div>
		<!-- 引入 -->
 
</body>
</html>
