<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>

<title><fmt:message key="ui.title" /></title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/jkjc-btnStyle.css" type="text/css"></link>
<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/chosen.css" type="text/css"></link>
<%@ include file="/jsp/framework/common/head.jsp"%>
<%
	String zhenggai_uid = request.getParameter("zhenggai_uid"); 
    String projectUserId = request.getParameter("projectUserId");
    String project_uid = request.getParameter("project_uid");
%>
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
.ui-state-hover{

}

/**设置 鼠标移动到 行上的 背景色**/
.table-hover>tbody>tr:hover>td,.table-hover>tbody>tr:hover>th{
background-color:#8A8E97;

}

.table-hover{
background-color:#F1F5FA;                                                                                                                                                                                    E4EEF7
}

/**设置 border*/
.ui-jqgrid tr.ui-row-ltr td {
    text-align: left;
    /* border-right-width: 1px; */
    border-right-color: inherit;
    border-right-style: none;
}

.ui-jqgrid .ui-jqgrid-labels th {
    border:none;
}
/** 横向 滚动条去除 */
.ui-jqgrid .ui-jqgrid-bdiv{ overflow-x: hidden; }

.ui-jqgrid .ui-jqgrid-htable thead {
    background-color:red;
}

/**设置 操作 的 样式**/
.colorshow{
color:#4E4C4C;
}

/**设置 标题内内容和 图标的 间隔**/
.divmargin{
margin:0px;
}

/**去掉 链接的下划线（鼠标点击添加按钮的时候) **/
.btn.btn-link:hover {
    background: 0 0!important;
    text-shadow: none!important;
    text-decoration:none;
}

.btn.btn-link:active {
    background: 0 0!important;
    text-shadow: none!important;
    text-decoration:none;
}
</style>
</head>
<body >
	<div class="main-container" id="main-container">
			<div class="page-content">
				<div class="col-sm-12"  id="inside">
		    <!-- form 开始 位置 -->
					<form class="form-inline"  id="queryForm" width="100%"
					   role="form" style="border:solid 1px #ddd;line-height:50px;vertical-align:middle;">
                          <input id="project_uid" type="hidden" name="project_uid" value="<%=project_uid%>"/>
                          <input id="projectUserId" type="hidden" name="projectUserId" value="<%=projectUserId%>"> 
						  <input type="hidden" id="NEW_Y" name="NEW_Y"  fieldname="zgdf.NEW_Y"  operation="="  value="Y"/>
						  <input id="ZHENGGAI_UID"  type="hidden"  fieldname="zgdf.ZHENGGAI_UID" operation="=" value="" fieldtype="text"/> 
				 	   <div class="form-group">   
						     <div id="btnCloseDiv"  class="form-group" >
								<a data-target="" id="newClose" data-toggle="modal">	
									
								<button class="btn btn-2 btn-sms pull-right" id="btn_Close" data-dismiss="modal" aria-hidden="true">关闭</button>
								</a>
							 </div>
						  <div style="float: right;">  
							 <div id="btnAddDiv"  class="form-group" >
								<a data-target="" id="newAdd" data-toggle="modal">
									<button id="btn_new" class="btn btn-link btn-sm" type="button"><i class="colorshow ace-icon glyphicon glyphicon-plus"></i>
									  <span style="font-family:'Microsoft YaHei';font-size:15px;color:#4e4c4c;">添加</span>
									</button>
								</a>
							 </div>
							 &nbsp; 
						  </div> 
					 </div>
					</form> 
					    <table  sortname="ZGDF.DAFU_ZG_DATE"   rownum="1000000"  sortorder="desc"  class="table-hover" id="grid_table" 
									action="${ctx}/zhenggai/pmZgDafuController?query" >
							<tr>
								<%--<th name="XIANCHANG_STRUC_UID"  align="center" width="0"></th>
								--%>
								<th name="STATUS"   align="center" width="2" formatter="showStatus">答复状态</th>
								<th name="NEW_Y"  align="center" width="3" formatter="newyStatus" >最新答复</th>
								<th name="DAFU_CODE"  align="center" width="3" formatter="" >答复编号</th>
								<th name="DAFU_DATE"  align="center" width="3" formatter="yyyy-mm-dd" >答复日期</th>
								<th name="DAFU_ZG_DATE"  align="center" width="3" formatter="yyyy-mm-dd" >整改完成日期</th>
								<th name="DAFU_USER"  align="center" width="3" formatter="" >答复人</th> 
								<th name="XIANCHANG_UID"  align="center" width="2" formatter="doUpdate">编辑</th> 
							    <th name="XIANCHANG_UID"  align="center" width="2" formatter="doOperator">操作</th> 
							</tr> 
						</table> 
						<script type="text/javascript">
							var $path_base = "/";
						</script>
				  </div>
				</div>
			</div> 
<div id="xmgk-input" class="modal"></div>
<%@ include file="/jsp/framework/common/basic_scripts.jsp"%>
<script src="${pageContext.request.contextPath }/assets/js/jquery-ui.custom.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/sys_resources/js/common/Form2Json.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/chosen.jquery.min.js"> </script>
	
<script type="text/javascript">
var controlletname="${pageContext.request.contextPath }/zhenggai/pmZgDafuController";
var scripts =[null];
var gridwidth;
var setHeight;
var _zhenggai_uid = "<%=zhenggai_uid%>";
ace.load_ajax_scripts(scripts, function() {
	init();
    $(".footer-content").remove();//去除页脚下的版本符号
     DatePicker.datepicker("#REPORT_DATE1");
     DatePicker.datepicker("#REPORT_DATE_ONE"); 
	//获取 当前的 项目的 主键 uid
	var xmUid = $("#project_uid").val(); 
	$("#PROJECT_UID").val(xmUid);
	$("#ZHENGGAI_UID").val(_zhenggai_uid);
	
	gridwidth=$("#inside").width();
	JqgridManage.initJqgrid(grid_table,queryForm,gridwidth-10); 
	setHeight = $(window).height();
	
	if(navigator.userAgent.indexOf("Firefox") > -1){
	   setHeight = setHeight+50;
	}
	
	 $("#grid_table").setGridHeight(setHeight - 220); 
});

function init(){	
	$(".chosen-select").chosen();
	$(".chosen-choices").css("height","10px");
	
    //查询按钮 (报表)
	$("#searchForPlan").click(function(){
		validform=FormValid.validbyformid(queryForm);
		//console.info(validform);
		if(validform.check()){
			var selectVar1 = $("#form-field-select-1").val();
			var selectVar2 = $("#form-field-select-2").val();
			if(selectVar1=="null"||selectVar1==""){
				$("#BIANGENG_STATUS_FORSELECT").val("");
			}else{
				$("#BIANGENG_STATUS_FORSELECT").val(selectVar1);
			}
			if(selectVar2=="null"||selectVar2==""){
				$("#BIANGENG_TYPE_FORSELECT").val("");
			}else{
				$("#BIANGENG_TYPE_FORSELECT").val(selectVar2);
			}
	        jQuery("#grid_table").jqGrid().trigger("reloadGrid");
		}
	}); 
	
	$('#newAdd').click(function() {
		$(".modal-backdrop").attr("class","");
	    $('#newAdd').attr("data-target","xmgk-input");
		$('#xmgk-input').removeData("bs.modal");
		$("#xmgk-input").empty();
		$('#xmgk-input').modal({
			backdrop: 'static'
		});
		
		$.get("${ctx}/jsp/business/zhenggai/zhenggai-dafu-add.jsp?zhenggai_uid="+_zhenggai_uid,function(data) {
			$("#xmgk-input").empty();
			$("#xmgk-input").html("");
			$("#xmgk-input").html(data);
		}); 
	 });
	
	 //关闭
	 $("#btn_Close").click(function(){ 
		   window.opener._reload();
		   window.close(); 
	 });
}

//newyStatus
function newyStatus(cellvalue,opts,rowObject){
	  var NEW_Y = rowObject.NEW_Y;
	  var MYSTATUS;
	  if(NEW_Y.indexOf("Y")!=-1){
		  MYSTATUS="是";
	  } 
		var thtml ="<div class=\"hidden-sm hidden-xs btn-group\" >";
	    thtml +="<span class=\"bigger-100\">"+MYSTATUS+"</span>";
	    thtml +="</div>"; 
	   return thtml; 
}
//编辑 标题
function showStatus(cellvalue, opts, rowObject){
	  var STATUS = rowObject.STATUS;
	  var MYSTATUS;
	  if(STATUS.indexOf("-1")!=-1){
		  MYSTATUS="草稿";
	  }else  if(STATUS.indexOf("0")!=-1){
		  MYSTATUS="已答复未处理";
	  }else if(STATUS.indexOf("1")!=-1){
		  MYSTATUS="已处理";
		  $("#btnAddDiv").hide();
	  }
		var thtml ="<div class=\"hidden-sm hidden-xs btn-group\" >";
	    thtml +="<span class=\"bigger-100\">"+MYSTATUS+"</span>";
	    thtml +="</div>"; 
	   return thtml; 
} 

function doType(cellvalue, opts, rowObject){
	var ZG_TYPE = rowObject.ZG_TYPE;
	var repStatus = "";
	if(ZG_TYPE.indexOf("1") != -1){//说明是已经完成
		repStatus="限期整改";
	}else if(ZG_TYPE.indexOf("2") != -1){//说明是已经完成
		repStatus="局部停工";
	}else if(ZG_TYPE.indexOf("3") != -1){
		repStatus="全面停工"; 
	} 
	var thtml ="<div class=\"hidden-sm hidden-xs btn-group\" >";
	    thtml +="<span class=\" bigger-100\">"+repStatus+"</span>";
	    thtml +="</div>";
	return thtml; 
}

//显示现场质量状况附件信息
function showFilesOrImgs(xc_uid){
	$(".modal-backdrop").attr("class","");
	$('#newAdd').attr("data-target","xmgk-input");
		$('#xmgk-input').removeData("bs.modal");
		$("#xmgk-input").empty();
		$('#xmgk-input').modal({
			backdrop: 'static'
		});
		
		$.get("${ctx}/jsp/business/xmzlaq/xczk-showfilesorimgs.jsp?XC_UID="+xc_uid+"&TYPE=10010",function(data) {
			$("#xmgk-input").empty();
			$("#xmgk-input").html("");
			$("#xmgk-input").html(data);
		});

}



// 查看按钮  打开整改答复详情页
function showFileList(project_uid,zhenggai_uid,zg_dafu_uid){
		$('#newAdd').attr("data-target","xmgk-input");
		$('#xmgk-input').removeData("bs.modal");
		$("#xmgk-input").empty();
		$('#xmgk-input').modal({
			backdrop: 'static'
		});
		
		$.get("${ctx}/jsp/business/zhenggai/zhenggai-dafu-detail.jsp?zhenggai_uid="+zhenggai_uid+"&zg_dafu_uid="+zg_dafu_uid,function(data) {
			$("#xmgk-input").empty();
			$("#xmgk-input").html("");
			$("#xmgk-input").html(data);
		});
	
}

//查看信息
function eidtXcZlxx(xc_uid,xs_uid){
		$('#newAdd').attr("data-target","xmgk-input");
		$('#xmgk-input').removeData("bs.modal");
		$("#xmgk-input").empty();
		$('#xmgk-input').modal({
			backdrop: 'static'
		});
		
		$.get("${ctx}/jsp/business/xmzlaq/xczk-zlorganize-update.jsp?XC_UID="+xc_uid+"&XS_UID="+xs_uid,function(data) {
			$("#xmgk-input").empty();
			$("#xmgk-input").html("");
			$("#xmgk-input").html(data);
		});
}

function removeData(PROJECT_UID,ZHENGGAI_UID){
		bootbox.confirm("确认删除吗？", function(result) {
		if (result) {						
			$.ajax({
				url :controlletname+"?delete",
				data : {"PROJECT_UID":PROJECT_UID,"ZHENGGAI_UID":ZHENGGAI_UID},
				cache : false,
				async :	false,
				dataType : "json",  
				type : 'post',
				success : function(response) {
					if(response){
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

function doAnswer(cellvalue, opts, rowObject){
	var sum = rowObject.SUMANSWER;
	var answer_name = rowObject.AN_PERSON;
	var answer_date = rowObject.AN_DATE;
	if(sum == "" || sum == null){
		sum ="0";
	}
	var thtml ="<div class=\"hidden-sm hidden-xs btn-group\" >";
	    thtml +="<span class=\"bigger-100\">回复数："+sum+"条</span>&nbsp;&nbsp;<br/><span class=\" bigger-100\">最近回复："+answer_name+"</span>&nbsp;&nbsp;<br/><span class=\" bigger-100\">最近回复时间："+answer_date+"</span>";
	    thtml +="</div>";
	return thtml;
}

function doReportInfo(cellvalue, opts, rowObject){
	var repname= rowObject.USER_NAME;
	var repdate = rowObject.CREATE_DATE;
	var thtml ="<div class=\"hidden-sm hidden-xs btn-group\" >";
	    thtml +="<span class=\"bigger-100\">发布人："+repname+"</span>&nbsp;&nbsp;<br/><span class=\" bigger-100\">发布时间："+repdate+"</span>";
	    thtml +="</div>";
	return thtml;
}

//编辑
function doUpdate(cellvalue, opts, rowObject){
	var project_uid = rowObject.PROJECT_UID;
	var zg_dafu_uid = rowObject.ZG_DAFU_UID;
	var zhenggai_uid = rowObject.ZHENGGAI_UID;
	var showHtml ='';
	
	showHtml ="<div class='hidden-sm hidden-xs action-buttons'>";	
	    showHtml +="<a class=\"blue\" title=\"查看答复详情\" data-target=\"content_view\"  href=\"javascript:void(0);\"" ;
		showHtml +="onclick=\"showFileList('"+project_uid+"','"+zhenggai_uid+"','"+zg_dafu_uid+"');\">" ;
		showHtml +="<span class=\"glyphicon glyphicon-file bigger-110\"></span>" ;
		showHtml +="</a>&nbsp;";  
	return 	showHtml;
}

function checkTabAuthority(projectId,projectUserId,authorityCode){
	var flag = false;
	$.ajax({
		url :"${ctx}/commons/SysUserController?queryAuthority",
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		data:{
			"projectId":projectId,
			"projectUserId":projectUserId,
			"authorityCode":authorityCode
		},
		success : function(response) {
			flag = response.success;
	    }
	});
	return flag;
}
 
//回复  复查 显示
function doOperator(cellvalue, opts, rowObject){ 
	var ZHENGGAI_UID = rowObject.ZHENGGAI_UID;
	var ZG_DAFU_UID = rowObject.ZG_DAFU_UID;
	var STATUS = rowObject.STATUS;
   <%-- var showHtml ="<div class='hidden-sm hidden-xs action-buttons' style='padding-top:15px;'>";			        	
		showHtml +="<a title=\"回复\" data-target=\"content_view\"  href=\"javascript:void(0);\"" ;
		showHtml +=	" onclick=\"answerXcZl('"+xc_uid+"');\">";
		showHtml +="<i class=\"colorshow ace-icon fa fa-comment  icon-only bigger-130\"></i>";
		showHtml +="</a></div> &nbsp;"; --%>
		var showHtml='';
   if(checkTabAuthority($("#project_uid").val(),$("#projectUserId").val(),"12080005")){
	  showHtml = '<div style="padding:15px;"><a href="javascript:void(0)" onclick="if('+STATUS+'==1){alert(\'关闭状态不能进行复查\');}else if('+STATUS+'==-1){alert(\'草稿状态不能进行复查\');}else{openDf(\''+ZHENGGAI_UID+'\',\''+ZG_DAFU_UID+'\');}">复查</a><div>';
   }
      return 	showHtml;
}

//打开答复列表
function openDf(ZHENGGAI_UID,ZG_DAFU_UID){   
	$('#xmgk-input').removeData("bs.modal");
	$("#xmgk-input").empty();
	$('#xmgk-input').modal({
		backdrop: 'static'
	});
	$.get("${ctx}/jsp/business/zhenggai/zhenggai-fucha.jsp?zhenggai_uid="+ZHENGGAI_UID+"&zg_dafu_uid="+ZG_DAFU_UID+"&FILE_TYPE=10010",function(data) {
		$("#xmgk-input").empty();
		$("#xmgk-input").html("");
		$("#xmgk-input").html(data);
	});  
	//window.open("${pageContext.request.contextPath }/jsp/business/zhenggai/dafu-index.jsp?zhenggai_uid="+ZHENGGAI_UID); 
}

function answerXcZl(xc_uid){
	$('#newAdd').attr("data-target","xmgk-input");
		$('#xmgk-input').removeData("bs.modal");
		$("#xmgk-input").empty();
		$('#xmgk-input').modal({
			backdrop: 'static'
		});
		
		$.get("${ctx}/jsp/business/xmzlaq/xczk-jdorganize-answer.jsp?XC_UID="+xc_uid+"&FILE_TYPE=10010",function(data) {
			$("#xmgk-input").empty();
			$("#xmgk-input").html("");
			$("#xmgk-input").html(data);
		}); 
}
function _reload(){
	jQuery("#grid_table").jqGrid().trigger("reloadGrid");
}

</script>

</body>
</html>