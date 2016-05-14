<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/jsp/framework/common/taglibs.jsp" %>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<%
  String  project_uid= (String)request.getAttribute("project_uid");
String  typeId= (String)request.getAttribute("typeId"); 
%>
<!DOCTYPE html>
<html>
	
<head>
	<base href="${ctx_site}/">
	<title>起重设备选择</title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
 	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="无锡建设环保局-建设单位信用管理系统" />
	<%@ include file="/jsp/framework/common/head.jsp"%>
 	<app:base />
</head>
<body class="no-skin">
<div class="main-container" id="main-container">
		<div class="main-content">
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12" id="contentdivid">
							<!-- PAGE CONTENT BEGINS -->
							<form class="form-inline" role="form" id="sbForm" style="border:solid 1px #ddd;height:50px;line-height:50px;vertical-align:middle;">
							<div class="col-xs-10">
							&nbsp;&nbsp;&nbsp;
					          <div class="form-group">
										<label>模板类型</label>
							   </div>
							   <div class="form-group">
									<input class="form-control" style="margin-bottom:8px;width: 180px" name="TYPE_NAME" fieldname="TYPE_NAME" id="TYPE_NAME"  operation="like" logic="and" >
								
								</div>
								
							 
							<button id="search" class="btn btn-link btn-sm" type="button"><i class="#ace-icon glyphicon glyphicon-search  bigger-110"></i>查询</button>
							<button id="clean" class="btn btn-link btn-sm"  type="button"><i class="#ace-icon glyphicon glyphicon-trash  bigger-110"></i>清空</button>
							</div>	
								<div class="col-xs-2 align-right" >
									<button id="savesb" onclick="selSb()" class="btn btn-primary btn-sm" type="button">确定</button>
									<button onclick="javascript:window.opener._reload();window.close();" class="btn btn-primary btn-sm" type="button">关闭</button>
							</div>
						 </form>
						 <div id="tableY">
							<table  sortname="BZJC_TYPE_UID" multiselect=false  rownum="10"
									sortorder="desc" 
									class="auto_startgrid" 
									id="content_grid_table" 
									action="${pageContext.request.contextPath }/jcgl/sysBzjcTypeController?query" >
								<tr> 
								    <th formatter="action" name="BZJC_TYPE_UID"  align=center key=true width="20">&nbsp;</th>
									<th formatter="open" name="TYPE_NAME"  width="70" align=center>模板类型</th>
									<th name="SHIYONG" width="120" align=center >适用于</th>
								</tr>
							</table>
							</div>
							
						</div>
					</div>

				</div>
			</div>		
		</div>
	<div id="view-input" class="modal"></div>
<%@ include file="/jsp/framework/common/basic_scripts.jsp"%>
 	<app:base />


<script type="text/javascript" charset="utf-8">


var scripts =[null];// ["jsp/business/project/JsProject.js"];
var type="y";
var bzjcTypeUid = <%=typeId %>; 
ace.load_ajax_scripts(scripts, function() {
		var gridwidth=$("#contentdivid").width();
		JqgridManage.initJqgrid(content_grid_table,sbForm,gridwidth);
		 
		 //改变浏览器大小自适应
		 /*
		$(window).on('resize.jqGrid',function() {
						$("#grid_table").jqGrid('content_grid_table',$("#contentdivid").width());
					});
		$(window).triggerHandler('resize.jqGrid');*/
		
	});
//页面初始化
$(function() {
		$('#search').click(function() {
			$('#content_grid_table').trigger("reloadGrid");
		});
		$('#clean').click(function(){
			$("#TYPE_NAME").val("");
			 $("#search").click();  
		});
});

function dock(obj){
	 var checks = document.getElementsByName("grid-checkbox");
	    if(obj.checked)
	    {
	        for(var i=0;i<checks.length;i++){
	            checks[i].checked = false;
	        }
	        obj.checked = true;    
	    }else
	    {
	        for(var i=0;i<checks.length;i++){
	            checks[i].checked = false;
	        }
	    }
}
function action(i,val,obj){
	var checkbox=" ";
	if(bzjcTypeUid == obj.BZJC_TYPE_UID){
		checkbox = "<label><input name=\"grid-checkbox\" onclick=\"dock(this)\" value=\""+ i+ "\"type=\"checkbox\" checked=true class=\"ace\"><span class=\"lbl\"></span></label>";
	}else{
		checkbox = "<label><input name=\"grid-checkbox\" onclick=\"dock(this)\" value=\""+ i+ "\"type=\"checkbox\" class=\"ace\"><span class=\"lbl\"></span></label>";
	}
	return checkbox;
}

function open(i,val,obj){
	var showHtml = "<a title=\"查看\" href=\"javascript:void(0)\" style=\"text-align: center;\" onclick=\"view("+ obj.BZJC_TYPE_UID +",'"+ obj.TYPE_NAME +"')\" data-toggle=\"modal\">"+ obj.TYPE_NAME +"</a>";
	return showHtml;
}

function view(id,name){
		$('#newAdd').attr("data-target","view-input");
		$('#view-input').removeData("bs.modal");
		$("#view-input").empty();
		$('#view-input').modal({
			backdrop: 'static'
		});
		
		$.get("${ctx}/jsp/business/jcgl/check-jcbz-tree.jsp?TYPE_UID="+id+"&TYPE="+name,function(data) {
			$("#view-input").empty();
			$("#view-input").html("");
			$("#view-input").html(data);
		});
}

function checkY(){
type="y";
$("#savesb").show();
$("#tableY").show();
$("#tableN").hide();
}
function checkN(){
type="n";
$("#savesb").hide();
$("#tableN").show();
$("#tableY").hide();
}

function selSb(){
	var id;
	var rowData;
	id=$("#content_grid_table").jqGrid("getGridParam","selrow");
	rowData = $("#content_grid_table").jqGrid('getRowData',id);
	var rowIds = CommUtils.getJqgridSelected("content_grid_table");
	if(rowIds==""){ 
	bootbox.alert("请选择一条记录!");
	}else{
		bootbox.confirm("确认选择改模板吗？", function(result) {
		if (result) {				
			$.ajax({
				url :'${pageContext.request.contextPath }/jcgl/sysBzjcTypeController?copyPm&TYPE_UID='+rowIds+"&PID="+<%=project_uid %>,
				cache : false,
				async :	false,
				dataType : "json",  
				type : 'post',
				success : function(response) {
					if( response == "1" ){
						xAlert("信息提示","导入成功",1);
					}else{
						xAlert("信息提示","导入失败,请联系管理员",2);
					}

			      }
			});
						
		} else {
			return;
		}
	});
	 window.opener.queryList(rowIds);
	  window.close();  
	}
}


function openSpan(id){
		$('#view-input').removeData("bs.modal");
			$("#view-input").empty();
			$('#view-input').modal({
				backdrop: 'static'
				});
			$.get(contextPath+ "/jsp/gdzxt/jxsbgl/sbzl-page-azgz-baoyang-view.jsp?id='"+id+"'",function(data) {$("#view-input").html(data);});
							
}
</script>		
</body>
</html>