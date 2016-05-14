<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
	<%@ include file="/jsp/framework/common/head.jsp"%>
<title><fmt:message key="ui.title" /></title>
<link rel="icon" href="${pageContext.request.contextPath }/favicon.ico" type="image/x-icon" />
<link rel="shortcut icon" href="${pageContext.request.contextPath }/favicon.ico" type="image/x-icon" />
<style>
/**设置页面 布局 */
.page-content {
    background-color: #fff;
    position: relative;
    margin: 0;
    padding:3px 0px 0px 3px;
}

.col-xs-6, .col-sm-10 {
    position: relative;
    min-height: 1px;
    padding-left: 3px;
    padding-right: 3px;
} 
.setposition{
position: absolute;
left:40%;
top:13%;
Z-index:10;
}

/**设置 表格的 滚动条**/
.ui-jqgrid .ui-jqgrid-bdiv{ 
overflow-x:hidden;
overflow-y:auto; }
</style>

</head>
<body >
<div class="setposition modal-dialog width-30 height-auto ">
  <div class="modal-content" >
   <div class="widget-header ">
   <%--class=" widget-toolbar "--%>
   		<div  style="cursor: pointer;">
			<a id="confirm" style="float:right;margin:6px 4px;" >
				<i class="ace-icon fa fa-times bigger-100"></i>
			</a>
		</div>
  </div>
  <!-- 设置 滚动条 自动显示  class="modal-body"-->
 <div  style="overflow-y:hidden;overflow-x:hidden;" >
		    <!-- form 开始 位置 -->
					<form class="form-inline"  id="queryForm02" width="100%"
					   role="form" style="border:solid 1px #ddd;height:50px;line-height:50px;vertical-align:middle;display:none;">
						 <input id="XMMC_PROJECT_UID" type="hidden" name="xm.PROJECT_UID" fieldname="xm.PROJECT_UID" operation="="/> 
					</form>
					    <table  rownumbers="false" sortname="xm.PRJ_STRUC_UID" multiselect=false  page="no" rownum="100000" sortorder="asc"  id="grid_table02" 
									action="${ctx}/pmxianchang/pmPrjStrucController?query" >
							<tr>
							    <th name="PRJ_STRUC_UID"  align="center" width="1" formatter="doOnly">选择</th>
								<th name="STRUC_NAME"   align="center" width="5">结构名称</th>
								<th name="STRUC_TYPE"  align="center" width="5">类型</th>
								<%--<th name="STRUC_CODE"  align="center" width="0">结构代码</th>--%>
							    
							</tr> 
						</table> 
						<script type="text/javascript">
							var $path_base = "/";
						</script>
	</div>
</div>
</div>
  <%@ include file="/jsp/framework/common/basic_scripts.jsp"%>
<script type="text/javascript">
var scripts =[null];
var gridwidth;
var setHeight;
ace.load_ajax_scripts(scripts, function() {
	init();
	//获取 当前的 项目的 主键 uid
	var xmUid = $("#project_uid").val();
	$("#XMMC_PROJECT_UID").val(xmUid);
	JqgridManage.initJqgrid01(grid_table02,queryForm02,600); 
	setHeight = $(window).height();
	
	if(navigator.userAgent.indexOf("Firefox") > -1){
	   setHeight = setHeight+50;
	}
	
	 $("#grid_table02").setGridHeight(100); 
	 
	$("#confirm").click(function(){
		var str = $("input[name='STRUCNAME']:checked").val();
	
		if(str==''||str==undefined){
		
		}else{
			var strs = new Array();
			strs = str.split(",");
			$("#XM_PRJ_STRUC_UID").val(strs[0]);
			$("#getXMJGMC").val(strs[1]);
			
		}
	
	        /** 修改当前页面框的div属性值**/
			$("#XMZK-XMMC").css("display","none");
			$("#XMZK-XMMC").attr("aria-hidden","true");
			$("#XMZK-XMMC").attr("class","modal");
			/**页面框的背景 样式去除**/
			$(".modal-backdrop").attr("class","");
	});
	
});

//这里是 单选 
function doOnly(cellvalue, opts, rowObject){
	var psid = rowObject.PRJ_STRUC_UID;
	var name = rowObject.STRUC_NAME;
	var pname = psid+","+name;
	var showHtml ="<div class='hidden-sm hidden-xs action-buttons' id='getValue'>";			        	
		showHtml +="<input id=\"psid"+psid+"\" type=\"radio\" name=\"STRUCNAME\" value =\""+pname+"\"";
		showHtml +="</div>";
	return 	showHtml;	
}


</script>

</body>
</html>