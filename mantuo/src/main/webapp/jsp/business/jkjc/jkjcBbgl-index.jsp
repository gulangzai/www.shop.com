<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>

<title><fmt:message key="ui.title" /></title>
<style>
/**设置页面 布局 */
.page-content {
    background-color: #fff;
    position: relative;
    margin: 0;
    padding:3px 0px 0px 3px;
}

.col-xs-6, .col-sm-2, .col-sm-10 {
    position: relative;
    min-height: 1px;
    padding-left: 3px;
    padding-right: 3px;
} 

#btnSure, #btnOutput {
margin:3px;
margin-top:1px;
margin-bottom:10px;
}

</style>
</head>
<body class="no-skin">
	<div class="main-container" id="main-container">
			<div class="page-content">
				<div class="col-xs-12">
					 <div class="col-xs-6 col-sm-2">
					    <div class="widget-box" id="leftHeight">
					      <form class="form-inline" role="form" id="executeForm"
							style="border:solid 1px #ddd;padding:4px;margin-bottom:5px;vertical-align:middle;">
				            <div class="form-group">
								<label style="font-size:14px;">报表类型</label>
							</div>
							<div class="form-group">
							    <input id="" type="text" style="width:90px;"name="" fieldname=""  operation="like" placeholder="查询"> 
							</div>
							<!-- <button id="searchBbxx" class="btn btn-link btn-sm" type="button">
									<i class="#ace-icon glyphicon glyphicon-search  bigger-110"></i>查询
							</button> -->
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<div class="">
								<span>沉降观测记录表</span>
							</div>
						</form>
						</div>
					  </div>
					 <div class="col-xs-6 col-sm-10">
						<div class="widget-box" id="tbDiv" >
						 <form class="form-inline"  id="queryForm" width="100%"
						 role="form" style="border:solid 1px #ddd;height:50px;line-height:50px;vertical-align:middle;">
							<!-- style="border:solid 1px #ddd;padding:3px;margin-bottom:5px;vertical-align:middle;>" -->
								&nbsp;&nbsp;
								<div class="form-group">
									 <label>由</label>
								<div class="form-group">
									 <label> 
									  	<input id="BDATE" class="form-control" style="width: 120px;" type="text"  fieldname="BDATE" operation="=" data-date-format="yyyy/mm/dd" />
									  	<!-- <input id="DATE01" class="form-control" style="width: 100px;" type="text"  fieldname="DATE01" operation="="/> -->
									 </label>
								</div>
								</div>
							    &nbsp;&nbsp;
								<div class="form-group">
									 <label>到</label>
								</div>
								<div class="form-group">
									 <label> 
									  	<input id="EDATE" class="form-control" style="width: 120px;" type="text" fieldname="EDATE" operation="="  data-date-format="yyyy/mm/dd" />
									  	<!-- <input id="DATE02" class="form-control" style="width: 100px;" type="text" fieldname="DATE02" operation="="/> -->
									 </label>
								</div>
							  &nbsp;&nbsp;
							  <div class="form-group" >
									<a data-target="" id="btnnew" data-toggle="modal">
										<button id="btnSure" class="btn btn-primary btn-sm" type="button"><i class="icon-edit bigger-110"></i>确定</button>
									</a>
							 </div>
							 &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
							 &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
							 <div class="form-group" >
								<a data-target="" id="new" data-toggle="modal">
									<button id="btnOutput" class="btn btn-primary btn-sm" type="button"><i class="icon-edit bigger-110"></i>报表输出</button>
								</a>
							 </div>
						</form>
		                 <!--  sortname="PROJECT_UID"-->
		               
						<table   sortname="PROJECT_UID" multiselect=false  rownum="1000000" sortorder="desc"  class="auto_startgrid"  id="grid_table" 
									action="${ctx}/project/buProjectController?query" >
							<tr>
								<th name="PROJECT_NAME"  width="2" formatter="jqgridactions">项目名称</th>
								<th name="PROJECT_ADDRESS"  width="2" >项目地址</th>
								<th name="BEGIN_DATE" width="2" align="center" >开工日期</th>
								<th name="END_DATE" width="2" align="center" >完工日期</th>
								<th name="ZHANDI_MIANJI" width="2" align="right" >项目占地面积(平方米)</th>
								<th name="JIANZHU_MIANJI" width="2" align="right" >建筑面积(平方米)</th>
								<th name="ZONG_TOUZI" width="2" align="right" >总投资(元)</th>
								
								
							</tr> 
						</table>
						
						<script type="text/javascript">
							var $path_base = "/";
						</script>
							<!-- PAGE CONTENT ENDS -->
					    </div>
					  </div>
					</div>
				</div>
			</div>
			
				<div id="jldwUser-input" class="modal"></div>

<%@ include file="/jsp/framework/common/basic_scripts.jsp"%>
    <script
		src="${pageContext.request.contextPath }/assets/js/jquery-ui.custom.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/assets/sys_resources/js/common/Form2Json.js"> </script>
<script type="text/javascript">

var scripts =[null];
ace.load_ajax_scripts(scripts, function() {
	init();
	DatePicker.datepicker("#BDATE");
	DatePicker.datepicker("#EDATE");
	//DatePicker.datepicker("#DATE01");
	//DatePicker.datepicker("#DATE02"); 
	//$("gview_grid_table").removeAttr("style");
	
var gridwidth=$("#tbDiv").width();
//alert(gridwidth+"获取的 宽度是 ");//1070
// gridwidth = gridwidth- 200;
//alert(gridwidth+"变化后的宽度是");
	JqgridManage.initJqgrid(grid_table,queryForm,gridwidth); 
	var setHeight = $(window).height();
	
	if(navigator.userAgent.indexOf("Firefox") > -1){
	   setHeight = setHeight+50;
	}
	
    $("#grid_table").setGridHeight(setHeight - 320); 
    var leftHeight= setHeight-170;
    $("#leftHeight").css("height",leftHeight);
   
   //设置  表格外围的div的宽度	
   // $("#tbDiv").css("width",gridwidth);
    //$("#leftHeight").setGridHeight($(window).height() - 320);  
    //alert("获取的宽度是"+$(window).height());//870
    
});

       //表格自适应宽度
		$(window).on(
				'resize.jqGrid',
				function() {
					$("#grid_table").jqGrid('setGridWidth',$("#tbDiv").width());
			}); 
	
function init(){
	
	/* $('#search').click(function() {
		jQuery("#grid_table").jqGrid().trigger("reloadGrid");
	});*/
	
    //查询按钮 (报表)
	$("#searchBbxx").click(function(){
	//jQuery("#grid_table").jqGrid().trigger("reloadGrid");
		var data = Form2Json.formToJSON(executeForm);
		//alert("获取 的数据是"+data);
		var data1 = defaultJson.packSaveJson(data);
		defaultJson.doInsertJson(controllername + "?query", data1); 
	}); 
	
	
	$('#btnSure').click(function() {
		var data = Form2Json.formToJSON(queryForm);
		//alert("获取 的数据是"+data);
		//组成保存json串格式
		var data1 = defaultJson.packSaveJson(data);
		defaultJson.doInsertJson(controllername + "?query", data1);
		//window.opener.reload();
		//window.close();
	});
	
	
	$("#btnOutput").click(function(){
	 $('#btnSure').click();
	
	
	});

}


function jqgridactions(cellvalue, opts, rowObject){
	var puid = rowObject.PROJECT_UID;
	var name = rowObject.PROJECT_NAME;
	var	showHtml ="<a class=\"blue\" title=\"查看\" data-target=\"content_view\"  href=\"javascript:void(0);\"" ;
		showHtml +=	" onclick=\"eidtRy('"+puid+"');\">"+name+"</a> &nbsp;";
	return 	showHtml;	        	
}

function eidtRy(puid){
	$("#gdzxt_gcid").val(puid);
    var f =document.getElementById('gdzxtformid');
    var url='${pageContext.request.contextPath }/jsp/framework/project/project_main.jsp';
	f.action=url;
	f.target="_blank"; 
	f.submit();
} 
</script>
	<form id="gdzxtformid" method="post" >
		<input type="hidden" name="" id="" >
	</form>
</body>
</html>