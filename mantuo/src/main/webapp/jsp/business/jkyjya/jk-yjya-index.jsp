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
.btn-sm {
    padding: 4px 9px;
    border-width: 4px;
    font-size: 12px;
    line-height: 1.00;
}

.form-control {
    display: block;
    width: 100%;
    height: 25px;
    padding: 4px 12px;
</style>
</head>
<body class="no-skin">
	<div class="main-container" id="main-container">
				<div class="page-content">
					 <div class="col-sm-12">
					 <!--   tabs-below-->
					    <div class="tabbable">
							 <ul id="myTab" class="nav nav-tabs">
							   <li class="active">
							      <a href="#home"  onclick="getHref(this)" data-toggle="tab">&nbsp;&nbsp;设&nbsp;施&nbsp;设&nbsp;备&nbsp;&nbsp;</a>
							   </li>
							   <li>
							      <a href="#profile" onclick="getHref(this)" data-toggle="tab">施工应急预案</a>
							   </li>
							    <li>
							      <a href="#messages" onclick="getHref(this)" data-toggle="tab">负责人联络方式</a>
							   </li>
							   <li>
							      <a href="#house" onclick="getHref(this)" data-toggle="tab">&nbsp;&nbsp;仓&nbsp;库&nbsp;备&nbsp;品&nbsp;&nbsp;</a>
							   </li> 
							   <li>
				                  <a href="#company" onclick="getHref(this)" data-toggle="tab">抢修单位联系方式</a>
							   </li>
							   <li >
									<a id="fjsc" data-toggle="tab"  href="#dropdown1" onclick="getHref(this)">
										附件上传&nbsp;
										<i class="ace-icon fa fa-caret-down bigger-110 width-auto"></i>
									</a>
									<!-- <ul class="dropdown-menu dropdown-info">
										<li>
											<a data-toggle="tab" href="#dropdown1">应急预案</a>
										</li>

										<li>
											<a data-toggle="tab" href="#dropdown2">@2</a>
										</li>
									</ul> -->
							  </li>
							 </ul>
							<!-- class padding-24 -->
							 <div class="tab-content ">
							   <div class="tab-pane in active" id="home">
							    
							   </div>
							   <div class="tab-pane" id="profile">
							      
							   </div>
							   <div class="tab-pane" id="messages">
							    仓库备品
							    
							   </div>
							   <div class="tab-pane" id="house">
							    施工应急预案
							   
							   </div>
							   <div class="tab-pane" id="company">
							    抢修单位联系方式
							    
							   </div>
							  <div id="dropdown1" class="tab-pane fade ">
							 
						 </div>
						</div>
					</div>
				</div>
			 </div>
		</div>
	<div id="jldwUser-input" class="modal"></div>
	<jsp:include page="/jsp/file_upload/fileupload_util.jsp" flush="true" />
<%@ include file="/jsp/framework/common/basic_scripts.jsp"%>
    <script
		src="${pageContext.request.contextPath }/assets/js/jquery-ui.custom.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/assets/sys_resources/js/common/Form2Json.js"> </script>
<script type="text/javascript">
var scripts =[null];
var gridwidth;
var setHeight;
var gridHeight;
ace.load_ajax_scripts(scripts, function() {
      init();
     /**初始化 加载 数据页面*/
	$("#home").load("jsp/business/jkyjya/jkjc-sssb.jsp");
	//默认预选中 
    $("#firstTR").addClass("newClass");
	var xmUid = $("#project_uid").val();
	$("#PROJECT_UID").val(xmUid);

	gridwidth=$(".page-content").width();
	gridthHeight = $(".page-content").height();
	setHeight = $(window).height();
	
	if(navigator.userAgent.indexOf("Firefox") > -1){
	   setHeight = setHeight+50;
	}
	
	$('.ajax-loading-overlay').remove();
});

function init(){
$('#btnSure').click(function() {
		var data = Form2Json.formToJSON(executeForm);
		//alert("获取 的数据是"+data);
		var data1 = defaultJson.packSaveJson(data);
		defaultJson.doInsertJson(controllername + "?query", data1); 
		
	});

}

function getHref(obj){
 if(obj.href.indexOf("#home") !=-1){
         $("#profile").html("");
         $("#messages").html("");
         $("#company").html("");
         $("#house").html("");
         $("#dropdown1").html("");
	     $("#home").load("jsp/business/jkyjya/jkjc-sssb.jsp");
	    }else if(obj.href.indexOf("#profile") !=-1){
	     $("#home").html("");
         $("#messages").html("");
         $("#company").html("");
         $("#house").html("");
         $("#dropdown1").html("");
	     $("#profile").load("jsp/business/jkyjya/jkjc-sgyj.jsp");
	    }else if(obj.href.indexOf("#messages") !=-1){
	     $("#profile").html("");
         $("#home").html("");
         $("#company").html("");
         $("#house").html("");
         $("#dropdown1").html("");
	     $("#messages").load("jsp/business/jkyjya/jkjc-rylxfs.jsp");
	    }else if(obj.href.indexOf("#house") !=-1){
	     $("#profile").html("");
         $("#messages").html("");
         $("#home").html("");
         $("#company").html("");
         $("#dropdown1").html("");
	     $("#house").load("jsp/business/jkyjya/jkjc-ckbp.jsp");
	    }else if (obj.href.indexOf("#company") !=-1){
	     $("#profile").html("");
         $("#messages").html("");
         $("#home").html("");
         $("#house").html("");
         $("#dropdown1").html("");
	     $("#company").load("jsp/business/jkyjya/jkjc-qxdw.jsp");
	    }else if(obj.href.indexOf("#dropdown1") !=-1){
	     $("#profile").html("");
         $("#messages").html("");
         $("#home").html("");
         $("#house").html("");
         $("#company").html("");
	     $("#dropdown1").load("jsp/business/jkyjya/yjya-uploadFile.jsp");
	    
	    }
	
} 
</script>
	<form id="gdzxtformid" method="post" >
		<input type="hidden" name="gdzxt_gcid" id="gdzxt_gcid" >
	</form>
</body>
</html>