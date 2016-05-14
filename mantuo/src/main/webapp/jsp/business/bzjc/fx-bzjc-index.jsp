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
</head>
<body >
	<div class="main-container" id="main-container">
			<div class="page-content" >
				<div class="col-sm-12" id="inside">
		    <!-- form 开始 位置 -->
					<form class="form-inline"  id="queryForm" width="100%"
					   role="form" style="border:solid 1px #ddd;line-height:50px;vertical-align:middle;">
						 <input id="PROJECT_UID" type="hidden" name="PROJECT_UID" fieldname="ZG.PROJECT_UID" operation="="/> 
						  <input id="BIANGENG_STATUS_FORSELECT" type="hidden" fieldname="STATUS" operation="in" value="" fieldtype="text"/> 
						  <input id="BIANGENG_TYPE_FORSELECT"  type="hidden"  fieldname="JC_TYPE" operation="in" value="" fieldtype="text"/> 
						 <div class="form-group"> 
					   	<!-- <label class=" control-label no-padding-right" > 
					  			<%--整改状态：<span class="required-indicator">*</span>--%>
								</label>  -->
							<!--  <select  multiple class="chosen-select"    style="width:25%;"  nullmsg="请选择整改状态" data-placeholder="请选择整改状态" id="form-field-select-1">
							        <option value="-1" >草稿</option> 
							        <option value="0" >整理中</option> 
							        <option value="1" >关闭</option> 
							      </select>    --> 
				  			<!--<label class="control-label no-padding-right" > 
				  			<%--整改类型：<span class="required-indicator">*</span>--%>
							</label>  -->
						<!-- <select multiple="" class="chosen-select form-control"  style="width:30%;"     data-placeholder="请选择整改类型" id="form-field-select-2" >
						        <option value="1" >限期整改</option>
						        <option value="2" >局部停工</option> 
						        <option value="3" >全面停工</option> 
						      </select>  
							&nbsp;&nbsp;&nbsp;&nbsp;  -->	 
						 <%-- 发放单位：--%>				
					<!--<input id="selectId" placeholder="发放单位" class="form-control" onclick="select();" style="width: 150px; height: 25px;border-radius: 8px 8px 8px 8px; border: 1px solid #d1d2d9;
							  background-color: #ffffff; background-image: url(./assets/images/search_icon.png);background-position: right center; background-repeat: no-repeat;" name="ZG.FAFANG_CORP" fieldname="ZG.FAFANG_CORP"" operation="like" logic="and">
						 &nbsp;&nbsp;&nbsp;&nbsp;
						   发放日期：
						 <input class="form-control" data-date-format="yyyy-mm-dd"
										style="width: 130px; height: 25px;border-radius: 8px 8px 8px 8px; border: 1px solid #d1d2d9;"
										fieldname="ZG.FAFANG_DATE" id="REPORT_DATE_ONE"
										operation="&gt;" logic="and" />
								~
						 <input class="form-control" data-date-format="yyyy-mm-dd"
								style="width: 130px; height: 25px;border-radius: 8px 8px 8px 8px; border: 1px solid #d1d2d9;"		
										fieldname="ZG.FAFANG_DATE" id="REPORT_DATE1"
										operation="&lt;" logic="and" />
										&nbsp;&nbsp; 
						
						  <button id="searchForPlan" class="btn btn-link btn-sm" type="button">
								<i class="colorshow ace-icon glyphicon glyphicon-search"></i><span style="font-family:'Microsoft YaHei';font-size:15px;color:#4e4c4c;">查询</span>
						  </button>	 
						  -->	 
						<div style="float: right;">
							 <div id="btnAddDiv"  class="form-group" >
								<a data-target="" id="newAdd" data-toggle="modal">
									<button id="btn_new" style="" class="btn btn-link btn-sm" type="button"><i class="colorshow ace-icon glyphicon glyphicon-plus"></i>
									  <span style="font-family:'Microsoft YaHei';font-size:15px;color:#4e4c4c;">添加</span>
									</button>
								</a>
							 </div>
							 &nbsp;  
						 </div> 
						</div> 
					</form>
			
					    <table  sortname="ZG.ZG_DATE"   rownum="1000000"  sortorder="desc"  class="" id="grid_table"  ExpandColumn="BZJC_NAME"
									action="${ctx}/pmbzjc/pmBzjcController/queryTree?JC_TYPE=FX" >
							<tr>
								<%--<th name="XIANCHANG_STRUC_UID"  align="center" width="0"></th>
								--%> 
								<th name="BZJC_NAME"   align="center" width="5" formatter="">检查名称</th>
								<th name="JC_ZHIBIAO"  align="center" width="3" formatter="" >检查指标</th> 
								<th name="JC_SSRY"  align="center" width="3" formatter="" >实施人员</th>
								<th name="JC_BIAOZHUN"  align="center" width="3" formatter="" >检查标准</th>
								<th name="JC_DZMS"  align="center" width="3" formatter="" >动作描述</th> 
								<th name="PLAN_JC_DATE"  align="center" width="3" formatter="" >计划完成时间</th>
								<th name="JC_END_DATE"  align="center" width="3" formatter="" >实际完成时间</th>
								<th name="JC_CHENGGUO"  align="center" width="3" formatter="" >检查成果</th> 
								<th name="JC_STATUS"  align="center" width="3" formatter="" >检查状态</th>   
								<!--<th name="XIANCHANG_UID"  align="center" width="2" formatter="doUpdate">编辑</th>  -->
								<th name="XIANCHANG_UID" align="center" width="6" formatter="doUpdate">操作</th>
							</tr> 
						</table> 
						 <div id="pager11"></div>
						 
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
var controlletname="${pageContext.request.contextPath }/pmbzjc/pmBzjcController";
var scripts =[null];
var gridwidth;
var setHeight;
ace.load_ajax_scripts(scripts, function() {
	init();
    $(".footer-content").remove();//去除页脚下的版本符号
     DatePicker.datepicker("#REPORT_DATE1");
     DatePicker.datepicker("#REPORT_DATE_ONE"); 
	//获取 当前的 项目的 主键 uid
	var xmUid = $("#project_uid").val();
	$("#PROJECT_UID").val(xmUid);
	
	gridwidth=$("#inside").width();
	//JqgridManage.initJqgrid(grid_table,queryForm,gridwidth-10); 
	JqgridManage.initJqgridTree(grid_table,queryForm,gridwidth-10);
	
	setHeight = $(window).height();
	
	if(navigator.userAgent.indexOf("Firefox") > -1){
	   setHeight = setHeight+50;
	}
	
	 $("#grid_table").setGridHeight(setHeight - 220); 
	//改变浏览器大小自适应
		$(window).on(
				'resize.jqGrid',
				function() {
					$("#grid_table").jqGrid(    //jqGridtable 自适应width
							'setGridWidth',
							$("#inside").width());
				});
		$(window).triggerHandler('resize.jqGrid');
});

function init(){	
	$(".chosen-select").chosen();
	$(".chosen-choices .search-field input").css("padding","1px");
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
		
		$.get("${ctx}/jsp/business/bzjc/fx-bzjc-addparent.jsp",function(data) {
			$("#xmgk-input").empty();
			$("#xmgk-input").html("");
			$("#xmgk-input").html(data);
		}); 
	}); 
	
	checkBtnAuthority($("#project_uid").val(),$("#projectUserId").val(),"12080001","btn_new");
}
//施工单位
function showsgdw(cellvalue, opts, rowObject){
	var project_company_uid= rowObject.SGDW;
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
//发放单位
function showfafangcorp(cellvalue, opts, rowObject){
	var project_company_uid= rowObject.FAFANG_CORP;
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
//整改完成日期
function showZgDate(cellvalue,opts,rowObject){
	var ZG_DATE = rowObject.ZG_DATE;
	var arr = ZG_DATE.split("-");
	var zgDate = new Date(arr[0],arr[1],arr[2]); 
	var date = new Date().Format("yyyy-MM-dd");
	var t_arr = date.split("-");
	var t_date = new Date(t_arr[0],t_arr[1],t_arr[2]); 
	if(zgDate.getTime()<t_date.getTime()){
	    MYSTATUS="<span style='color:red'>"+ZG_DATE+"</span>";
	} else{
		MYSTATUS=ZG_DATE;
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
	  }else if(STATUS.indexOf("0")!=-1){
		  MYSTATUS="整改中";
	  }else if(STATUS.indexOf("1")!=-1){
		  MYSTATUS="关闭";
	  }
		var thtml ="<div class=\"hidden-sm hidden-xs btn-group\" >";
	    thtml +="<span class=\"bigger-100\">"+MYSTATUS+"</span>";
	    thtml +="</div>"; 
	   return thtml; 
}


function doType(cellvalue, opts, rowObject){
	var JC_TYPE = rowObject.JC_TYPE;
	var repStatus = "";
	if(JC_TYPE.indexOf("1") != -1){//说明是已经完成
		repStatus="限期整改";
	}else if(JC_TYPE.indexOf("2") != -1){//说明是已经完成
		repStatus="局部停工";
	}else if(JC_TYPE.indexOf("3") != -1){
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



// 查看按钮不可修改
function showFileList(project_uid,bzjc_uid,jc_type){
	//alert("暂时不开放");
    $('#newAdd').attr("data-target","xmgk-input");
		$('#xmgk-input').removeData("bs.modal");
		$("#xmgk-input").empty();
		$('#xmgk-input').modal({
			backdrop: 'static'
		});
		
		$.get("${ctx}/jsp/business/bzjc/fx-bzjc-detail.jsp?PROJECT_UID="+project_uid+"&BZJC_UID="+bzjc_uid+"&JC_TYPE="+jc_type,function(data) {
			$("#xmgk-input").empty();
			$("#xmgk-input").html("");
			$("#xmgk-input").html(data);
		});
	 
}

//修改信息
function eidtXcZlxx(project_uid,bzjc_uid,jc_type){
		$('#newAdd').attr("data-target","xmgk-input");
		$('#xmgk-input').removeData("bs.modal");
		$("#xmgk-input").empty();
		$('#xmgk-input').modal({
			backdrop: 'static'
		});
		
		$.get("${ctx}/jsp/business/bzjc/fx-bzjc-edit.jsp?PROJECT_UID="+project_uid+"&BZJC_UID="+bzjc_uid+"&JC_TYPE="+jc_type,function(data) {
			$("#xmgk-input").empty();
			$("#xmgk-input").html("");
			$("#xmgk-input").html(data);
		});
}

//反馈
function fankui(project_uid,bzjc_uid,jc_type){
	$('#newAdd').attr("data-target","xmgk-input");
	$('#xmgk-input').removeData("bs.modal");
	$("#xmgk-input").empty();
	$('#xmgk-input').modal({
		backdrop: 'static'
	});
	
	$.get("${ctx}/jsp/business/bzjc/fx-bzjc-fankui.jsp?PROJECT_UID="+project_uid+"&BZJC_UID="+bzjc_uid+"&JC_TYPE="+jc_type,function(data) {
		$("#xmgk-input").empty();
		$("#xmgk-input").html("");
		$("#xmgk-input").html(data);
	});
}

//添加子节点
function addson(project_uid,bzjc_uid,jc_type){
	$('#newAdd').attr("data-target","xmgk-input");
	$('#xmgk-input').removeData("bs.modal");
	$("#xmgk-input").empty();
	$('#xmgk-input').modal({
		backdrop: 'static'
	});
	
	$.get("${ctx}/jsp/business/bzjc/fx-bzjc-addson.jsp?PROJECT_UID="+project_uid+"&BZJC_UID="+bzjc_uid+"&JC_TYPE="+jc_type,function(data) {
		$("#xmgk-input").empty();
		$("#xmgk-input").html("");
		$("#xmgk-input").html(data);
	});
}


function removeData(PROJECT_UID,BZJC_UID){
		bootbox.confirm("确认删除吗？", function(result) {
		if (result) {						
			$.ajax({
				url :controlletname+"?delete",
				data : {"PROJECT_UID":PROJECT_UID,"BZJC_UID":BZJC_UID},
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
	var bzjc_uid = rowObject.BZJC_UID;
	var jc_type = rowObject.JC_TYPE;
	var isLeaf =  rowObject.isLeaf;
	var showHtml ="<div class='hidden-sm hidden-xs action-buttons' style='padding-top:15px;'>";	
	    showHtml +="<a class=\"blue\" title=\"查看飞行检查\" data-target=\"content_view\"  href=\"javascript:void(0);\"" ;
		showHtml +="onclick=\"showFileList('"+project_uid+"','"+bzjc_uid+"','"+jc_type+"');\">" ;
		showHtml +="<span class=\"glyphicon glyphicon-file bigger-110\"></span>" ;
		showHtml +="</a>&nbsp;";
		//if(checkTabAuthority($("#project_uid").val(),$("#projectUserId").val(),"12080002")){
		showHtml +="<a class=\"blue\" title=\"修改飞行检查\" data-target=\"content_view\"  href=\"javascript:void(0);\"" ;
		showHtml +=	" onclick=\"eidtXcZlxx('"+project_uid+"','"+bzjc_uid+"','"+jc_type+"');\">";
		showHtml +="<span class=\"glyphicon glyphicon-edit bigger-110\"></i>";
		showHtml +="</a> &nbsp;";
		//}
		
		showHtml +="<a class=\"blue\" title=\"反馈飞行检查\" data-target=\"content_view\"  href=\"javascript:void(0);\"" ;
		showHtml +=	" onclick=\"fankui('"+project_uid+"','"+bzjc_uid+"','"+jc_type+"');\">";
		showHtml +="<span class=\"normal-icon ace-icon fa fa-comments blue bigger-110\"></i>";
		showHtml +="</a> &nbsp;"; 
		if(!isLeaf){
			showHtml +="<a class=\"blue\" title=\"添加子节点\" data-target=\"content_view\"  href=\"javascript:void(0);\"" ;
			showHtml +=	" onclick=\"addson('"+project_uid+"','"+bzjc_uid+"','"+jc_type+"');\">";
			showHtml +="<span class=\"normal-icon ace-icon fa fa-plus blue bigger-110\"></i>";
			showHtml +="</a> &nbsp;"; 
		} 
		  
		//if(checkTabAuthority($("#project_uid").val(),$("#projectUserId").val(),"12080003")){ 
		showHtml +="<a class=\"\" title=\"删除飞行检查\" data-target=\"content_view\"  href=\"javascript:void(0);\"" ;
		showHtml +=	" onclick=\"removeData('"+project_uid+"','"+bzjc_uid+"');\">";
		showHtml +="<span class=\"colorshow1 glyphicon  glyphicon-trash bigger-110\"></i>";
		showHtml +="</a></div>&nbsp;"; 	
		//}
	return 	showHtml;
}

//回复  答复 复查 显示
function doAnswerAsk(cellvalue, opts, rowObject){
	var xc_uid = rowObject.XIANCHANG_UID;
	var BZJC_UID = rowObject.BZJC_UID;
	var STATUS = rowObject.STATUS;
	var showHtml=''; 
    if(checkTabAuthority($("#project_uid").val(),$("#projectUserId").val(),"12080004")){
		showHtml = '<div style="padding:15px;"><a href="javascript:if('+STATUS+'==-1){alert(\'草稿状态不能进行答复\');}else{openDf('+BZJC_UID+');}">答复</a>&nbsp<div>';
    }
	return 	showHtml;
}

//打开答复列表
function openDf(BZJC_UID){  
	 window.open("${pageContext.request.contextPath }/jsp/business/zhenggai/zhenggai-dafu-index.jsp?zhenggai_uid="+ZHENGGAI_UID+"&project_uid="+$('#project_uid').val()+"&projectUserId="+$('#projectUserId').val()); 
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

Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}
</script>

</body>
</html>