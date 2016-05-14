<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ccthanking.framework.common.User"%>
<%@ page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext" %>
<%@ page import="com.ccthanking.framework.Constants"%>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>

<title><fmt:message key="ui.title" /></title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/jkjc-btnStyle.css" type="text/css"></link>
<%
User user = RestContext.getCurrentUser();
String user_uid = "";
String username = "";
if(user!=null){
	user_uid = user.getIdCard();
	username = user.getName();
}else{
	response.sendRedirect("/"+Constants.APP_NAME);
	return;
} 
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
.ui-state-hover{

}
/**设置 鼠标移动到行上 的 背景色**/
.table-hover>tbody>tr:hover>td,.table-hover>tbody>tr:hover>th{
background-color:#8A8E97;
}
.table-hover>tbody>tr,.table-hover>tbody>th{
border-right:1px solid red;
border-style:none;

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
<body >
	<div class="main-container" id="main-container">
			<div class="page-content" id="inside">
				<div class="col-sm-12">
		    <!-- form 开始 位置 -->
					<form class="form-inline"  id="queryForm" width="100%"
					   role="form" style="border:solid 1px #ddd;height:50px;line-height:50px;vertical-align:middle;">
						 <input id="PROJECT_UID" type="hidden" name="p.PROJECT_UID" fieldname="p.PROJECT_UID" operation="="/> 
						  <input id="" type="hidden" fieldname="xc.GONGKUANG_TYPE" operation="=" value="ZL"/> 
						
						<input id="TARGET_CODE" type="hidden" filedname="TARGET_CODE" value="XCGK"><div class="checkbox" >
							<label>
									<input name="form-field-checkbox" type="checkbox" fieldname="STATUS" class="ace" operation="=" checked value="0" >
									<span class="lbl"> 解决中</span>
								</label>
								<label>
									<input name="form-field-checkbox" type="checkbox"  class="ace" operation="=" checked value="1">
									<span class="lbl"> 已解决</span>
								</label>
							
						</div>
						 &nbsp;&nbsp;&nbsp;&nbsp;
						<div class="form-group"><!-- style="width: 130px; height: 25px;border-radius: 8px 8px 8px 8px; border: 1px solid #d1d2d9;" -->
						  发布时间：
						 <input class="form-control" data-date-format="yyyy-mm-dd"
										style="width: 130px; height: 25px;border-radius: 8px 8px 8px 8px; border: 1px solid #d1d2d9;"
										fieldname="xc.CREATE_DATE" id="REPORT_DATE_ONE"
										operation=">=" logic="and" />
								~
						 <input class="form-control" data-date-format="yyyy-mm-dd"
								style="width: 130px; height: 25px;border-radius: 8px 8px 8px 8px; border: 1px solid #d1d2d9;"		
										fieldname="xc.CREATE_DATE" id="REPORT_DATE11"
										operation="<=" logic="and" />
										&nbsp;&nbsp; 
						
						  <button id="searchForPlan" class="btn btn-link btn-sm" type="button">
								<i class="colorshow ace-icon glyphicon glyphicon-search"></i><span style="font-family:'Microsoft YaHei';font-size:15px;color:#4e4c4c;">查询</span>
						  </button>	
						</div>
						 
						<div style="float: right;">
							 <div id="btnAddDiv"  class="form-group" >
								<a data-target="" id="newAdd" data-toggle="modal">
									<button id="btn_new" style="display: none;"  class="btn btn-link btn-sm" type="button"><i class="colorshow ace-icon glyphicon glyphicon-plus"></i>
									  <span style="font-family:'Microsoft YaHei';font-size:15px;color:#4e4c4c;">添加</span>
									</button>
								</a>
							 </div>
							 &nbsp; 
						</div>
						
					</form>
			
					    <table  sortname="xc.XIANCHANG_UID"   rownum="1000000"  sortorder="desc"  class="table-hover" id="grid_table" 
									action="${ctx}/pmxianchang/pmXianchangController?query" >
							<tr>
								<%--<th name="XIANCHANG_STRUC_UID"  align="center" width="0"></th>
								--%>
								<th name="STATUS"  align="center" width="2" formatter="doStatus" >状态</th>
								<th name="FINISH_DATE"   align="center" width="4" formatter="riqi">要求解决日期</th>
								<th name="JINDU"   align="center" width="4" formatter="biaoti">标题</th>
								<th name="DESCRIPTION"   align="center" width="8" formatter="showStyle">状况描述</th>
								<th name="SERIOUSNESS"   align="center" width="2" formatter="showyanzheng" >严重性</th>
								<th name="USER_NAME"  align="left" width="8" formatter="doReportInfo" >发布信息</th>
								<th name="SUMANSWER"  align="left" width="8" formatter="doAnswer">回复信息</th>
								<th name="XIANCHANG_UID"  align="center" width="2" formatter="doAnswerAsk">回复</th>
								<th name="XIANCHANG_UID" align="center" width="4" formatter="doUpdate">编辑</th>
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
    <script
		src="${pageContext.request.contextPath }/assets/js/jquery-ui.custom.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/assets/sys_resources/js/common/Form2Json.js"> </script>
<script type="text/javascript">
var controlletname="${pageContext.request.contextPath }/pmxianchang/pmXianchangController";
var controll="${pageContext.request.contextPath }/loguserread/logUserReadController";
var scripts =[null];
var gridwidth;
var setHeight;
ace.load_ajax_scripts(scripts, function() {
	init();
    $(".footer-content").remove();//去除页脚下的版本符号
     DatePicker.datepicker("#REPORT_DATE11");
     DatePicker.datepicker("#REPORT_DATE_ONE"); 
	
     //获取 当前的 项目的 主键 uid
	var xmUid = $("#project_uid").val();
	$("#PROJECT_UID").val(xmUid);
	gridwidth=$("#inside").width();
	JqgridManage.initJqgrid(grid_table,queryForm,gridwidth-10); 
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
	//权限检查
		checkBtnAuthority($("#project_uid").val(),$("#projectUserId").val(),"12010001","btn_new");
	 
});

function init(){	
    //查询按钮 (报表)
	$("#searchForPlan").click(function(){
		var date= $("#REPORT_DATE11").val();
		if(date !=""){
			$("#REPORT_DATE11").val(date+" 23:59:59");
		}
	  jQuery("#grid_table").jqGrid().trigger("reloadGrid");
	  $("#REPORT_DATE11").val(date);
	}); 
	
	$('#newAdd').click(function() {
		$(".modal-backdrop").attr("class","");
	$('#newAdd').attr("data-target","xmgk-input");
		$('#xmgk-input').removeData("bs.modal");
		$("#xmgk-input").empty();
		$('#xmgk-input').modal({
			backdrop: 'static'
		});
		
		$.get("${ctx}/jsp/business/xmzlaq/xczk-zlorganize-add.jsp",function(data) {
			$("#xmgk-input").empty();
			$("#xmgk-input").html("");
			$("#xmgk-input").html(data);
		});
	
	});
	
	
}
//严重性
function showyanzheng(cellvalue, opts, rowObject){
	var seriousness = rowObject.SERIOUSNESS;
	var repseriousness = "";
	if(seriousness=="1"){
		repseriousness="一般";
	}else if(seriousness=="2"){
		repseriousness="较严重";
	}else if(seriousness=="3"){
		repseriousness="严重";
	}
	
	var thtml ="<div class=\"hidden-sm hidden-xs btn-group\" >";
	    thtml +="<span class=\" bigger-100\">"+repseriousness+"</span>";
	    thtml +="</div>";
	return thtml;
	
}
function doStatus(cellvalue, opts, rowObject){
	var status = rowObject.STATUS;
	var repStatus = "";
	if(status=="1"){//说明是已经完成
		repStatus="已解决";
	}else if(status=="0"){
		repStatus="解决中";
	}else if(status=="-1"){
		repStatus="草稿";
	}
	
	var thtml ="<div class=\"hidden-sm hidden-xs btn-group\" >";
	    thtml +="<span class=\" bigger-100\">"+repStatus+"</span>";
	    thtml +="</div>";
	return thtml;
	
}
//编辑日期
function riqi(cellvalue,opts,rowObject)
{
	var date = rowObject.FINISH_DATE;//期望完成日期
	var status = rowObject.STATUS;
	var tody = new Date(); 
	var nian = tody.getFullYear();
	var youe = tody.getMonth() + 1; 
	var day = tody.getDate(); 
	var dates=nian+"-"+youe+"-"+day;//当前日期
	 var d1 = new Date(date.replace(/\-/g, "\/")); 
	 var d2 = new Date(dates.replace(/\-/g, "\/"));  
	thtml="<div class='message-header clearfix'>";
	if(status.indexOf("1") != -1){
		thtml+="<span class='bigger-100'>"+date+"</span>";
	}else{
          if(date!=""&&dates!=""&&d1 <=d2){
			
			thtml+="<span class='bigger-100'><font color='red'>"+date+"</font></span>";
			}else{
				
			thtml+="<span class='bigger-100'>"+date+"</span>";
			}
		
	}
	
	thtml+="</div>";
	return thtml;  
}
//编辑 标题
/*function biaoti(cellvalue, opts, rowObject){
	  var JINDU = rowObject.JINDU;
      var thtml = '<div class="message-header clearfix">';
	      thtml += '<div class="pull-center">';
	      if(JINDU.length<5){
	    	  thtml += '<span class="bigger-100">'+JINDU+'&nbsp;&nbsp;</span>';  
	      }else{
	    	  thtml += '<span class="bigger-100">'+JINDU.substring(0,5)+'...</span>';
	      }
	      thtml += '</div>';
	      thtml += '</div>';
	       
	   return thtml;
	
}*/
function biaoti(cellvalue, opts, rowObject){
	  var JINDUS = rowObject.JINDU;
	  var status = rowObject.STATUS; 
	  var create_uid = rowObject.CREATE_USER;
	  var xian_uid=rowObject.XIANCHANG_UID;
	  var PROJECT_UID=$("#PROJECT_UID").val();
	  var TARGET_CODE=$("#TARGET_CODE").val();
	  var user_uid="<%=user_uid%>";
	  //var xians_uid="";
	  //var projects_uid="";
	  //var targets_code="";
	  //var users_uid="";
	  var thtml='';
	  $.ajax({
		url :controll+"?queryid",
		data : {"xian_uid":xian_uid,"PROJECT_UID":PROJECT_UID,"TARGET_CODE":TARGET_CODE,"user_uid":user_uid},
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			thtml = '<div class="message-header clearfix">';
		      thtml += '<div class="pull-left">';
			if(response.msg!="0"){
				 var msg = eval("("+response.msg+")");
				 var  users_uid = msg.response.data[0].USER_UID;
			     
				 if(status=="0"||status=="1"){
				    if(create_uid==user_uid){
				    		 if(JINDUS.length<5){
				        		  thtml += "<span class='bigger-100'>"+JINDUS+"...</span>";
				   	         }else{
				   	    	   thtml += "<span class='bigger-100'>"+JINDUS.substring(0,5)+"...</span>";
				   	          }
	                         
			         }else if(users_uid!=user_uid){
			    		  if(JINDUS.length<5){
			        		  thtml += "<img width='20px' height='20px;' src='/cpmi/assets/img/cricle.png'/><span class='bigger-100'>"+JINDUS+"...</span>";
			   	         }else{
			   	    	   thtml += "<img width='20px' height='20px;' src='/cpmi/assets/img/cricle.png'/><span class='bigger-100'>"+JINDUS.substring(0,5)+"...</span>";
			   	         }
			    	 }else{
			    		 if(JINDUS.length<5){
			        		  thtml += "<span class='bigger-100'>"+JINDUS+"...</span>";
			   	         }else{
			   	    	   thtml += "<span class='bigger-100'>"+JINDUS.substring(0,5)+"...</span>";
			   	          }
			    	 }
		          }else if(status=="-1"){
		        	  if(JINDUS.length<5){
		        		  thtml += "<span class='bigger-100'>"+JINDUS+"...</span>";
		   	          }else{
		   	    	      thtml += "<span class='bigger-100'>"+JINDUS.substring(0,5)+"...</span>";
		   	          }
		          }
			}else{
				 if(status=="0"||status=="1"){
			    	  if(create_uid==user_uid){
			    		  if(JINDUS.length<5){
			        		  thtml += "<span class='bigger-100'>"+JINDUS+"...</span>";
			   	         }else{
			   	    	   thtml += "<span class='bigger-100'>"+JINDUS.substring(0,5)+"...</span>";
			   	         }
                 
		         }else if(users_uid!=user_uid){
		    		  if(JINDUS.length<5){
		        		  thtml += "<img width='20px' height='20px;' src='/cpmi/assets/img/cricle.png'/><span class='bigger-100'>"+JINDUS+"...</span>";
		   	         }else{
		   	    	    thtml += "<img width='20px' height='20px;' src='/cpmi/assets/img/cricle.png'/><span class='bigger-100'>"+JINDUS.substring(0,5)+"...</span>";
		   	         }
		    	 }
	          }else if(status=="-1"){
	        	  if(JINDUS.length<5){
	        		  thtml += "<span class='bigger-100'>"+JINDUS+"...</span>";
	   	          }else{
	   	    	      thtml += "<span class='bigger-100'>"+JINDUS.substring(0,5)+"...</span>";
	   	          }
	          }
			}  
			thtml += "</div>";
		    thtml += "</div>";
		}
	});

   //console.info(thtml)
	       
	   return thtml;
	
}
//编辑 状况描述
function showStyle(cellvalue, opts, rowObject){
	  var title = rowObject.DESCRIPTION;
	  var xc_uid = rowObject.XIANCHANG_UID;
	  var target_uid = rowObject.TARGET_UID;
      var thtml = '<div class="message-header clearfix">';
	      thtml += '<div class="pull-left">';
	     
	      if(title.length<18){
	    	  
	    	  thtml += '<span class="bigger-100">'+title+'&nbsp;&nbsp;</span>';  
	      }else{
	    	  
	    	  thtml += '<span class="bigger-100">'+title.substring(0,15)+'...</span>';
	      }
	      
	      //if(target_uid !="" ){
	      //thtml += '<a onclick="showFilesOrImgs('+xc_uid+');" title="附件信息"><i class="colorshow ace-icon fa fa-paperclip"></i></a>';
	      //}
	      thtml += '</div>';
	      thtml += '</div>';
	       
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

//编辑
function doUpdate(cellvalue, opts, rowObject){
	var xc_uid = rowObject.XIANCHANG_UID;
	var xs_uid = rowObject.XIANCHANG_STRUC_UID;
	var status = rowObject.STATUS;
	var showHtml ="<div class='hidden-sm hidden-xs action-buttons' style='padding-top:15px;'>";	
	    showHtml +="<a class=\"blue\" title=\"查看现场信息\" data-target=\"content_view\"  href=\"javascript:void(0);\"" ;
		showHtml +="onclick=\"showFileList('"+xc_uid+"','"+xs_uid+"');\">" ;
		showHtml +="<span class=\" glyphicon glyphicon-file bigger-110\"></span>" ;
		showHtml +="</a>&nbsp;";
		
		if(checkTabAuthority($("#project_uid").val(),$("#projectUserId").val(),"12060102")){
			if(status=="0"||status=="-1"){
			    showHtml +="<a class=\"blue\" title=\"修改现场信息\" data-target=\"content_view\"  href=\"javascript:void(0);\"" ;
				showHtml +=	" onclick=\"eidtXcZlxx('"+xc_uid+"','"+xs_uid+"');\">";
				showHtml +="<span class=\" glyphicon glyphicon-edit bigger-110\"></i>";
				showHtml +="</a> &nbsp;";
		    }
		}
		if(checkTabAuthority($("#project_uid").val(),$("#projectUserId").val(),"12060103")){
				showHtml +="<a class=\"\" title=\"删除\" data-target=\"content_view\"  href=\"javascript:void(0);\"" ;
				showHtml +=	" onclick=\"removeData('"+xc_uid+"','"+xs_uid+"');\">";
				showHtml +="<span class=\"colorshow1 glyphicon  glyphicon-trash bigger-110\"></i>";
				showHtml +="</a></div>&nbsp;";
		}
	        	
	return 	showHtml;
}

// 查看按钮不可修改
function showFileList(xc_uid,xs_uid){
		$('#newAdd').attr("data-target","xmgk-input");
		$('#xmgk-input').removeData("bs.modal");
		$("#xmgk-input").empty();
		$('#xmgk-input').modal({
			backdrop: 'static'
		});
		
		$.get("${ctx}/jsp/business/xmzlaq/xczk-onlyriview-zldetail.jsp?XC_UID="+xc_uid+"&XS_UID="+xs_uid,function(data) {
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

function removeData(xc_uid,xs_uid){
		bootbox.confirm("确认删除吗？", function(result) {
		if (result) {						
			$.ajax({
				url :controlletname+"?delete",
				data : {"XC_UID":xc_uid,"XS_UID":xs_uid},
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

//回复 功能 实现
function doAnswerAsk(cellvalue, opts, rowObject){
	var xc_uid = rowObject.XIANCHANG_UID;
    var showHtml ="<div class='hidden-sm hidden-xs action-buttons' style='padding-top:15px;'>";			        	
		showHtml +="<a title=\"回复\" data-target=\"content_view\"  href=\"javascript:void(0);\"" ;
		showHtml +=	" onclick=\"answerXcZl('"+xc_uid+"');\">";
		showHtml +="<i class=\"colorshow ace-icon fa fa-comment  icon-only bigger-130\"></i>";
		showHtml +="</a></div> &nbsp;";
		
	        	
	return 	showHtml;
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