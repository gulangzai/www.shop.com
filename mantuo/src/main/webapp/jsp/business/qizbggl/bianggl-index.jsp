<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>

<title><fmt:message key="ui.title" /></title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/jkjc-btnStyle.css" type="text/css"></link>
<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/chosen.css" type="text/css"></link>
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

.chosen-select{
	height: 30px!important;
	line-height: 38px;
	overflow-y:auto;
}
.chosen-choices .search-field{
	height: 30px!important;
	line-height: 30px;
	overflow-y:auto;
}

</style>
<body >
	<div class="main-container" id="main-container">
			<div class="page-content" >
				<div class="col-sm-12" id="inside">
		    <!-- form 开始 位置 -->
					<form class="form-inline"  id="queryForm" width="100%"
					   role="form" style="border:solid 1px #ddd;line-height:50px;vertical-align:middle;">
						<input id="PROJECT_UID" type="hidden" name="PROJECT_UID" fieldname="PROJECT_UID" operation="="/> 
						<input  type="hidden"  fieldname="TAGS" operation="=" value="BG"/> 
						<input id="BIANGENG_TYPE_FORSELECT"  type="hidden"  fieldname="BIANGENG_TYPE" operation="in" value="" fieldtype="text"/> 
						
						<select multiple class="chosen-select" id="form-field-select-4" data-placeholder="请选择变更类型" style="width: 27%;">
							<option value="HTBCXY">合同补充协议</option>
							<option value="GCBGZL">工程变更指令</option>
							<option value="HTBWL">合同备忘录</option>
							<option value="SJBG">设计变更</option>
							<option value="HYJY">会议纪要</option>
							<option value="XH">信函</option>
							<option value="CLCJ">材料差价</option>
							<option value="JSBG">结算变更</option>
						</select>
						
						<div class="form-group">
						  变更日期：
						 <input class="form-control" data-date-format="yyyy-mm-dd"
										style="width: 100px; height: 25px;border-radius: 8px 8px 8px 8px; border: 1px solid #d1d2d9;"
										fieldname="BIANGENG_DATE" id="BIANGENG_DATE1"
										operation="&gt;=" logic="and" />
								~
						 <input class="form-control" data-date-format="yyyy-mm-dd"
								style="width: 100px; height: 25px;border-radius: 8px 8px 8px 8px; border: 1px solid #d1d2d9;"		
										fieldname="BIANGENG_DATE" id="BIANGENG_DATE2"
										operation="&lt;=" logic="and" />
						&nbsp;&nbsp; 
						  变更金额：
						 <input class="form-control" datatype="/^\d{0,}\.{0,1}(\d{0,})$/" errormsg="您输入的不是数字" ignore="ignore" 
										style="width: 100px; height: 25px;border-radius: 8px 8px 8px 8px; border: 1px solid #d1d2d9;"
										fieldname="AMOUNT" id="AMOUNT1"
										operation="&gt;=" logic="and" />
								~
						 <input class="form-control" datatype="/^\d{0,}\.{0,1}(\d{0,})$/" errormsg="您输入的不是数字" ignore="ignore" 
								style="width: 100px; height: 25px;border-radius: 8px 8px 8px 8px; border: 1px solid #d1d2d9;"		
										fieldname="AMOUNT" id="AMOUNT2"
										operation="&lt;=" logic="and" />
						&nbsp;&nbsp; 
						
						  <button id="searchForPlan" class="btn btn-link btn-sm" type="button">
								<i class="colorshow ace-icon glyphicon glyphicon-search"></i><span style="font-family:'Microsoft YaHei';font-size:15px;color:#4e4c4c;">查询</span>
						  </button>						 
						</div>
						<div id="btnAddDiv"  class="form-group" >
							<a data-target="" id="newAdd" data-toggle="modal">
								<button id="btn_new" style="display: none;" class="btn btn-link btn-sm" type="button"><i class="colorshow ace-icon glyphicon glyphicon-plus"></i><span style="font-family:'Microsoft YaHei';font-size:15px;color:#4e4c4c;">添加</span></button>
							</a>
						 </div>		
					</form>
			
					    <table  sortname="BIANGENG_TYPE" multiselect=false  rownum="1000000" sortorder="asc"  class=""  id="grid_table" 
									action="${ctx}/biangeng/biangengController?query" >
							<tr>
								
								<th name="BIANGENG_DATE" width="10" align="center">变更日期</th>
								<th name="BIANGENG_TYPE" width="10" align="center" formatter="formatType">变更类型</th>
								<th name="REASON" width="30" align="left" formatter="shiyou">变更事由</th>
								<th name="BIANGENG_COMPANY" width="20" align="center" formatter="fordanwei">变更发起单位</th>
								<th name="AMOUNT" width="10" align="right">变更金额</th>
								<th name="BIANGENG_UID" width="10" align="center" formatter="doUpdate">操作</th>
							</tr> 
						</table> 
						<script type="text/javascript">
							var $path_base = "/";
						</script>
				</div>
			</div>
		</div>
		<div id="jihbg-input" class="modal"></div>
		<div id="jihbg-input2" class="modal"></div>

<%@ include file="/jsp/framework/common/basic_scripts.jsp"%>
    <script src="${pageContext.request.contextPath }/assets/js/jquery-ui.custom.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/assets/sys_resources/js/common/Form2Json.js"> </script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/chosen.jquery.min.js"> </script>
	 
<script type="text/javascript">
var controlletname="${pageContext.request.contextPath }/biangeng/biangengController";
var scripts =[null];
var gridwidth;
var setHeight;
ace.load_ajax_scripts(scripts, function() {
	init();

    DatePicker.datepicker("#BIANGENG_DATE1");
    DatePicker.datepicker("#BIANGENG_DATE2"); 
	
    var xmUid = $("#project_uid").val();
	$("#PROJECT_UID").val(xmUid);
    
	gridwidth=$("#inside").width();
	JqgridManage.initJqgrid(grid_table,queryForm,gridwidth); 
	setHeight = $(window).height();
	
	if(navigator.userAgent.indexOf("Firefox") > -1){
	   setHeight = setHeight+50;
	}	
	 $("#grid_table").setGridHeight(setHeight - 320); 
	//改变浏览器大小自适应
		$(window).on(
				'resize.jqGrid',
				function() {
					$("#grid_table").jqGrid(    //jqGridtable 自适应width
							'setGridWidth',
							$("#inside").width());
				});
		$(window).triggerHandler('resize.jqGrid');
	//权限检查
		checkBtnAuthority($("#project_uid").val(),$("#projectUserId").val(),"12010001","btn_new");
	 
});
//编辑变更是由
function shiyou(cellvalue, opts, rowObject){
	  var reason = rowObject.REASON;

	  var  thtml = '<div class="pull-left">';
        if(reason.length<25){
	    	  
	    	  thtml += '<span class="bigger-100">'+reason+'&nbsp;&nbsp;</span>';  
	      }else{
	    	  
	    	  thtml += '<span class="bigger-100">'+reason.substring(0,22)+'...</span>';
	      }
	    
	      thtml += '</div>';
	      
	       
	   return thtml;
	
}
function init(){
	 $('.chosen-select').chosen(); 
	 
	 $(".chosen-choices .search-field input").css("padding","1px");
    //查询按钮 (报表)
	$("#searchForPlan").click(function(){
		validform=FormValid.validbyformid(queryForm);
		if(validform.check()){
			var selectVar = $("#form-field-select-4").val();
			if(selectVar=="null"||selectVar==""){
				$("#BIANGENG_TYPE_FORSELECT").val("");
			}else{
				$("#BIANGENG_TYPE_FORSELECT").val(selectVar);
			}
	    	jQuery("#grid_table").jqGrid().trigger("reloadGrid");
		}else {
		   return;
	   }
		
	}); 
	
	$('#newAdd').click(function() {
	$('#newAdd').attr("data-target","jihbg-input");
		$('#jihbg-input').removeData("bs.modal");
		$("#jihbg-input").empty();
		$('#jihbg-input').modal({
			backdrop: 'static'
		});
		
		$.get("${ctx}/jsp/business/qizbggl/bianggl-add.jsp",function(data) {
			$("#jihbg-input").empty();
			$("#jihbg-input").html("");
			$("#jihbg-input").html(data);
		});
	
	});	
	
}
//变更单位
function fordanwei(cellvalue, opts, rowObject){
	var project_company_uid= rowObject.BIANGENG_COMPANY;
	var thtml ="<div class=\"hidden-sm hidden-xs btn-group\" >";
	$.ajax({
		url : "${pageContext.request.contextPath }/buprojectcompany/buProjectCompanyController?queryid",
		data : {"project_company_uid":project_company_uid},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			 msg = eval("("+response.msg+")");
			 company_name = msg.response.data[0].COMPANY_NAME;
			 thtml +="<span class=\" bigger-100\">"+company_name+"</span>";
		}
	})
    thtml +="</div>";
	return thtml;
}
function formatType(cellvalue, opts, rowObject){
	var showHtml = "";
	var type = rowObject.BIANGENG_TYPE;
	if(type=="HTBCXY"){
		showHtml="合同补充协议";	
	}else if(type=="GCBGZL"){
		showHtml="工程变更指令";	
	}else if(type=="HTBWL"){
		showHtml="合同备忘录";	
	}else if(type=="XCQZ"){
		showHtml="现场签证";	
	}else if(type=="SJBG"){
		showHtml="设计变更";	
	}else if(type=="HYJY"){
		showHtml="会议纪要";	
	}else if(type=="XH"){
		showHtml="信函";	
	}else if(type=="CLCJ"){
		showHtml="材料差价";	
	}else if(type=="JSBG"){
		showHtml="结算变更";	
	}
	return showHtml;
}

function doUpdate(cellvalue, opts, rowObject){
	var bid = rowObject.BIANGENG_UID;
	var showHtml ="<div class='hidden-sm hidden-xs action-buttons'>";			        	
    	showHtml +="<a class=\"blue\" title=\"查看\" data-target=\"content_view\"  href=\"javascript:void(0);\"" ;
    	showHtml +=	" onclick=\"showeidtBiangeng('"+bid+"');\">";
    	showHtml +="<span class=\"glyphicon glyphicon-file bigger-110\"></i>";
     	showHtml +="</a> &nbsp;";  
     	 if(checkTabAuthority($("#project_uid").val(),$("#projectUserId").val(),"12060102")){
			    showHtml +="<a class=\"blue\" title=\"修改\" data-target=\"content_view\"  href=\"javascript:void(0);\"" ;
				showHtml +=	" onclick=\"eidtBiangeng('"+bid+"');\">";
				showHtml +="<span class=\"glyphicon glyphicon-edit bigger-110\"></i>";
				showHtml +="</a> &nbsp;";
     	 }
     	if(checkTabAuthority($("#project_uid").val(),$("#projectUserId").val(),"12060103")){
				showHtml +="<a class=\"\" title=\"删除\" data-target=\"content_view\"  href=\"javascript:void(0);\"" ;
				showHtml +=	" onclick=\"removeData('"+bid+"');\">";
				showHtml +="<span class=\"glyphicon colorshow1  glyphicon-trash bigger-110\"></i>";
				showHtml +="</a>&nbsp;";
        }  	
	return 	showHtml;
}

function eidtBiangeng(bid){
		$('#newAdd').attr("data-target","jihbg-input2");
		$('#jihbg-input2').removeData("bs.modal");
		$("#jihbg-input2").empty();
		$('#jihbg-input2').modal({
			backdrop: 'static'
		});
		
		$.get("${ctx}/jsp/business/qizbggl/bianggl-update.jsp?bid="+bid,function(data) {
			$("#jihbg-input2").empty();
			$("#jihbg-input2").html("");
			$("#jihbg-input2").html(data);
		});
}
function showeidtBiangeng(bid){
	$('#newAdd').attr("data-target","jihbg-input2");
	$('#jihbg-input2').removeData("bs.modal");
	$("#jihbg-input2").empty();
	$('#jihbg-input2').modal({
		backdrop: 'static'
	});
	
	$.get("${ctx}/jsp/business/qizbggl/bianggl-show.jsp?bid="+bid,function(data) {
		$("#jihbg-input2").empty();
		$("#jihbg-input2").html("");
		$("#jihbg-input2").html(data);
	});
}
function removeData(bid){
		bootbox.confirm("确认删除吗？", function(result) {
		if (result) {						
			$.ajax({
				url :controlletname+'?delete&bid='+bid,
				cache : false,
				async :	false,
				dataType : "json",  
				type : 'post',
				success : function(response) {
					if(response.success){
						xAlert("信息提示","删除成功",1);
					}else{
						xAlert("信息提示","删除失败,请联系管理员",1);
					}
					_reload();

			      }
			});
						
		} else {
			return;
		}
	});
}

function _reload(){
	jQuery("#grid_table").jqGrid().trigger("reloadGrid");
}



</script>

</body>
</html>