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

.col-xs-6, .col-sm-10 {
    position: relative;
    min-height: 1px;
    padding-left: 3px;
    padding-right: 3px;
} 
/** width:600px;height:450px;**/
#imgUpload{
width:500px;height:430px;margin:0px 0px 1px 0px ;
background-image:url(${pageContext.request.contextPath}/assets/images/yjya-liucheng.png);background-position:center; background-repeat:no-repeat;
}

#Setpadding{
padding-left:20px;

}

#FABAOFANGAN{
 margin: 0;
    padding:0px 15px 2px 7px;
}
</style>
</head>
<body >
	<div class="main-container" id="main-container">
	<!-- 点击 附件可以查看  点击下载 可以下载  上传加在设置里面 id="setShouSuo" -->
	<!--  <form method="post"  role="form" class="form-inline"  id="executeForm" width="80%">   -->    	
	   <div class="col-xs-12 col-sm-12 widget-container-col ui-sortable" id="FABAOFANGAN">
		  <div class="widget-box ui-sortable-handle" >
			 <div class="widget-header" style="margin:0px;color:#979797;font-size:17px;">
				<h5 class="widget-title" >应急预案：</h5>
				<div class="widget-toolbar">
				<a href="#" data-action="collapse"> <i
					class="1 ace-icon fa fa-chevron-up bigger-125"></i> </a>
			    </div>
	        </div> 
           <div class="widget-body">
				<div class="widget-main">
					<div class="row" style="line-height:30px;vertical-align:middle;margin:0px 1px 2px -5px;">
					 <div id="contentYScroll" style="overflow-y:scroll">
					  <div class="col-xs-12" >
									<input type="hidden" id="target_uid" fieldname="target_uid" value=""  title="">
								<div class=""><!-- form-group -->
								  <div class="col-sm-6">
									<table id="wj08" role="presentation" class="table table-striped nomargin">
										<tbody  onlyView="true" style="cursor: pointer;" class="files showFileTab" data-toggle="modal-gallery" data-target="#modal-gallery" file_type="10004">
										</tbody>
									</table>
								  </div>
								</div> 
						<script type="text/javascript">
							var $path_base = "/";
						</script>
					 </div>
				  </div>
				</div>
			</div>
		 </div>
		 </div>
		</div> 
		<!-- </form> -->
			<div class="page-content" id="inside"> 
		    <!-- form 开始 位置 -->
		    	<div class="col-xs-12">
		    	 <div class="col-xs-6 col-sm-6">
		    	   <div class="widget-box" id="leftHeight">
		         <div id="qxdwDIV" >
					<form class="form-inline"  id="queryForm" width="100%"
					   role="form" style="border:solid 1px #ddd;height:50px;line-height:50px;vertical-align:middle;">
						 <input id="PROJECT_UID" type="hidden" name="t.PROJECT_UID" fieldname="t.PROJECT_UID" operation="="/> 
						  <input id="CONTACT_TYPE" type="hidden" name="t.CONTACT_TYPE" fieldname="t.CONTACT_TYPE" operation="=" value="JK"/> 
						 <!-- 是否为抢修单位  Y 是 N 否 -->
						  <input id="REPAIR_Y" type="hidden" name="t.REPAIR_Y" fieldname="t.REPAIR_Y" operation="=" value="Y"/> 
						<div class="form-group">
						  <span style="font-size:15px;line-height: 50px;">&nbsp;抢修单位联系方式</span>
						</div>
						<div style="float: right;">
							<div class="form-group">
							  <input class="form-control"  placeholder="请输入联系人姓名" style="width: 150px; height: 25px;border-radius: 8px 8px 8px 8px; border: 1px solid #d1d2d9;
							  background-color: #ffffff; background-image: url(./assets/images/search_icon.png);background-position: right center; background-repeat: no-repeat;" name="t.PERSON_NAME" fieldname=" t.PERSON_NAME" id="" operation="like" logic="and">
							  <button id="searchBox" class="btn btn-link btn-sm" onKeyDown="reSearch()" type="button">
									<i class="#ace-icon glyphicon  bigger-110"></i><span style="font-family:'Microsoft YaHei';font-size:15px;color:#4e4c4c;">查询</span>
							  </button>
							</div>
						</div>
					</form>
					 <div id="qxdwConnection" style="overflow-y:scroll; overflow-x: hidden;">
					    <table   sortname="PERSON_INFO_UID" multiselect=false  rownum="1000000" sortorder="asc"  page=""  id="grid_table" 
									action="${ctx}/JcPerson/jcPersonInfoController?queryCondition" >
							<tr>
							   <!--  <th name="CONTACT_TYPE"  width="2" align="center">联络类型</th> -->
								<th name="PERSON_NAME"  width="2" align="center">联系人</th>
								<th name="PERSON_POST"  width="2" align="center">职务</th>
								<th name="COMPANY_NAME" width="2" align="center" >公司</th>
								<th name="PERSON_MOBILE" width="2" align="center" >联系电话</th>
							</tr> 
						</table> 
					 </div>
				</div>
				      <!-- form 开始 位置 2-->
				<div id="ckbpDIV">
					<form class="form-inline"  id="queryForm02" width="100%"
					   role="form" style="border:solid 1px #ddd;height:50px;line-height:50px;vertical-align:middle;">
						 <input id="PROJECT_UID_CKBP" type="hidden" name="t.PROJECT_UID" fieldname="t.PROJECT_UID" operation="="/> 
						<div class="form-group">
						  <span id="getSelect" style="font-size:15px;line-height: 50px;">&nbsp;仓库备品</span>
						  <input id="ClickType" type="hidden" value=""/>
						</div>
						<div style="float: right;">
							<div class="form-group">
							  <input class="form-control"  placeholder="请输入物品名称" style="width: 150px; height: 25px;border-radius: 8px 8px 8px 8px; border: 1px solid #d1d2d9;
							  background-color: #ffffff; background-image: url(./assets/images/search_icon.png);background-position: right center; background-repeat: no-repeat;" name="t.TOOLS_NAME" fieldname="t.TOOLS_NAME" operation="like" logic="and">
							  <button id="searchCKBP" class="btn btn-link btn-sm" type="button">
									<i class="#ace-icon glyphicon  bigger-110"></i><span style="font-family:'Microsoft YaHei';font-size:15px;color:#4e4c4c;">查询</span>
							  </button>
							</div>
					    </div>	
					</form>
					<!--  height:300px;-->
					 <div id="ckbpTb" style="overflow-y:scroll; overflow-x: hidden;" >
					    <table   sortname="t.TOOLS_UID" multiselect=false  rownum="1000000" sortorder="asc" page="" id="grid_table02" 
									action="${ctx}/JcTools/jcckController?query" >
							<tr>
								<th name="TOOLS_UID"  width="2" align="center">物品ID</th>
								<th name="TOOLS_NAME"  width="3" align="center">物品名称</th>
								<th name="TOOLS_MODEL" width="3" align="center" >物品型号</th>
								<th name="TOOLS_UNIT" width="1" align="center" >单位</th>
								<th name="TOOLS_NUMS" width="2" align="center" >总数量</th>
								<th name="IN_USE_NUMS" width="2" align="center" >正常使用</th>
								<th name="SLIGHT_DAMAGE_NUMS" width="2" align="center" >轻微损耗</th>
								<th name="DAMAGE_NUMS" width="2" align="center" >损坏</th>
							</tr> 
						</table> 
						<script type="text/javascript">
							var $path_base = "/";
						</script>
					</div>
				  </div>
				</div>
			  </div>
			   <div class="col-xs-6 col-sm-6" id="Setpadding">
			     <div class="widget-box" id="tbDiv"  >
			       <div id="loadImg" >
					     <h5><span style="font-size:17px;color:#979797">&nbsp;应急组织架构和流程：</span></h5>
						 <div id="loadingImages">
					     </div>
				  </div> 
				 </div>
			   </div>
			</div>
			</div>
		</div>
        <div id="jldwUser-input" class="modal"></div>
		<jsp:include page="/jsp/file_upload/fileupload_util.jsp" flush="true" />
<%@ include file="/jsp/framework/common/basic_scripts.jsp"%>
 <%--    <script
		src="${pageContext.request.contextPath }/assets/js/jquery-ui.custom.min.js"></script> --%>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/assets/sys_resources/js/common/Form2Json.js"> </script>
<script type="text/javascript">
var controlletname="${pageContext.request.contextPath }/JcPerson/jcPersonInfoController";
var scripts =[null];
var gridwidth;
var setHeight;
ace.load_ajax_scripts(scripts, function() {
	//去除版本符号
	$(".footer-content").remove();
	
	init();
	/** 设置 应急预案 内容 区域 的 竖向 滚动条 显示 **/
	$("#contentYScroll").css("height",70);
	
	var xmUid = $("#project_uid").val();
	$("#PROJECT_UID_CKBP").val(xmUid);
	$("#PROJECT_UID").val(xmUid);
	queryURL(xmUid);
	
	queryFileData('BU_PROJECT',xmUid);
	setHeight = $(window).height();
	var jspHeight=$(window).height()-45-24-8-8-8;//页面高度
	
	if(navigator.userAgent.indexOf("Firefox") > -1){
	   setHeight = setHeight+250;
	   
	}
	/**应急预案的 宽度设置 */

	 var controlW=$("#qxdwDIV").width();
	 var jspHeight=$(window).height()-45-24-8-8-8;//页面高度
	 JqgridManage.initJqgrid(grid_table02,queryForm02,controlW);
	 JqgridManage.initJqgrid(grid_table,queryForm, controlW);
	 $("#queryForm02").css("width",controlW);
	 $("#queryForm").css("width",controlW);
	 
	 //设置 抢修单位联系人/仓库备品 滚动条的 高宽度  自适应
	 $("#qxdwConnection").css("width",controlW);
	 $("#ckbpTb").css("width",controlW);
     $("#qxdwConnection").css("height",jspHeight/2-160);
	 $("#ckbpTb").css("height",jspHeight/2-150);
	
	/**设置  抢修单位 联系人的div的 高度  显示出2个div的间隔 
	     此处 外围的 DIV高度要大于滚动条的 高度*/
	$("#qxdwDIV").css("height",jspHeight/2-80);
	$("#ckbpDIV").css("hight",jspHeight/2-50);
	
	  var gridwidth = $("#inside").width()-850;
	/**设置  缩放时 表格及滚动条以及form的尺度变化自适应**/
	 $(window).on(
					'resize.jqGrid',
					function() {
						$("#grid_table").jqGrid(    //表格1jqGridtable 自适应width
								'setGridWidth',
								$("#tbDiv").width());
						$("#grid_table02").jqGrid(    //表格2jqGridtable 自适应width
								'setGridWidth',
								$("#tbDiv").width());
						$("#qxdwConnection").css("width",$("#tbDiv").width());
                        $("#ckbpTb").css("width",$("#tbDiv").width());
                        
                        $("#queryForm02").css("width",$("#tbDiv").width());
	                    $("#queryForm").css("width",$("#tbDiv").width());
	                    $("#imgUpload").css("width",$("#tbDiv").width()-300);
	                    // $("#tbDiv").height(jspHeight/2); 
					});
					
	            /** 设置 table的高度 显示的内容由table的高度决定*/
			    $("#grid_table02").setGridHeight(jspHeight+500);   
			    $("#grid_table02").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
			    $("#grid_table02").closest(".ui-jqgrid-bdiv").css({ "overflow-y" : "hidden" });
			    
				$("#grid_table").jqGrid('setGridWidth',$("#tbDiv").width());
				$("#grid_table").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" });
			    $("#grid_table").setGridHeight(jspHeight+500);
			    
	 
});
function init(){	
    //查询按钮 (报表)
	$("#searchBox").click(function(){
	  jQuery("#grid_table").jqGrid().trigger("reloadGrid");
	});
	$("#searchCKBP").click(function(){
	  jQuery("#grid_table02").jqGrid().trigger("reloadGrid");
	});
}
	
//查询图片上传的 url并让图片显示在页面上
function queryURL(xmUid){
               $.ajax({
					url : "${pageContext.request.contextPath }/fileUploadUtilController?queryFileByType&targetUid="+xmUid+"&file_type=10002",
					cache : false,
					async :	false,
					dataType : "json",
					success : function(result) {
					    var html = "";
					    var pcurl = "";
						if(result.obj!=null&&result.obj!=""){
							$.each(result.obj,function(){
								pcurl =  this.url;
								if(pcurl.indexOf(".jpg") || pcurl.indexOf(".jpng") || pcurl.indexOf(".gif")){
								 html="<img height=\"340\" width=\"370\" src="+pcurl+" />";
								}else{
								 html="";
								}
							 
							});
						}
						$("#loadingImages").append(html);

					}
				});

}

</script>
</body>
</html>