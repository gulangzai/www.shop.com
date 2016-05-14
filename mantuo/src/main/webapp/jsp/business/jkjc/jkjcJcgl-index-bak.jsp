<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>

<title><fmt:message key="ui.title" /></title>
<style>
/**设置页面 布局 */
.page-content { background-color: #fff; position: relative; margin: 0;padding:3px 0px 0px 3px;}
/* #leftHeight{height:705px; background-color: #fff;margin:0px; margin-top:3px; position:relative;padding-left:1px;padding-right:1px;} */
.col-xs-6, .col-sm-2, .col-sm-10 {position: relative;min-height: 1px; padding-left: 3px; padding-right: 3px;}
#btnSure, #btnOutput {margin:3px;margin-top:1px;margin-bottom:10px;}
#BottomDiv{border:solid 1px #ddd;margin:0px 0px 8px 0px;}
#btnStp{float:right;}

.tab{width:100%;border-right:1px solid #d1d2d9; border-top:1px solid #d1d2d9;background-color:#f3f3f3; }
.tab tr{ height:25px;}
.tab tr td{border-bottom:1px solid #d1d2d9;border-left:1px solid #d1d2d9;color:#4e4c4c;  font-size:12px;}
.tab tr th{border-bottom:1px solid #d1d2d9;border-left:1px solid #d1d2d9;color:#4e4c4c;  font-size:12px;}

.btn-yellow, .btn-yellow:foucs {
    border-color:red;
    background-color: red;
}
.btn-xs {
    border-width: 0px;
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
							style="padding:1px;margin-bottom:5px;vertical-align:middle;">
							
				            <div class="form-group" id="tid">
								<label style="font-size:16px;">监测点</label>
						   	</div>
							<div class="form-group" id="zid">
								<label>
									<input type="radio" name="jcd" value="all" checked="checked">全部
									<input type="radio" name="jcd" value="bj">仅报警
								</label>
							</div>
							<div style="float: right; display: none; position: absolute; top: 35px; left:40px;  overflow: hidden;border: 1px solid #d1d2d9;background-color: #f3f3f3;"  id="TypeForPoint" >
								<table id="appendTr"  style=""  class="jcdss" 	action="${ctx}/jkjc/jcPrjPointsController?queryPointForType" >
									<tr>
										<td>
											&nbsp;&nbsp;&nbsp;&nbsp;<input onclick="DoCheck();" id="selectAllCK" type="checkbox" name="selectAllCK"  value="" style="cursor: pointer;" checked="checked" />
											全部
										</td>
									</tr>
									<tr>
										<td>
											&nbsp;&nbsp;&nbsp;&nbsp;<input id="" type="checkbox" name="wdgz" value="1" checked="checked" fieldname=""/>
											我的关注
										</td>
									</tr>
								</table>
								<div style="text-align: center; margin-top: 5px;">
									 <button id="btnSure"  type="button">确定</button>
								</div>
							</div>
							
							<div class="form-group" style="overflow-y:scroll;" id="tabDiv">
								<table id="pointTab" class="tab">
									<thead>
										<tr>
											<th>编号</th>
											<th>类型</th>
										</tr>
									</thead>
									<tbody id="jcd">

									</tbody>
								</table>
							</div>
							
							<div style="position:absolute;z-index: 100;top:7.5px;right:6px;background-color:#F6FBFB;">
								<button onclick="showme()"    class="btn btn-xs bigger btn-yellow dropdown-toggle" data-toggle="dropdown">
								   <i id="switchPicture" class="ace-icon fa fa-caret-down"></i>
									<!-- <i class="ace-icon fa fa-chevron-down icon-on-right"></i> -->
								</button>
					    	</div>					 
						</form>
						</div>
					  </div>
					  <div class="col-xs-6 col-sm-10">
					  <div class="tabbable"id="bbDiv">

						 <ul id="myTab" class="nav nav-tabs">
						   <li class="active">
						      <a href="#home" data-toggle="tab">曲线</a>
						   </li>
						   <li>
						      <a href="#profile" data-toggle="tab">数据</a>
						   </li>
						 </ul>
					
						 <div class="tab-content" id="tbDiv">
						 	<div class="tab-pane in active" id="home" >
						 		<form class="form-inline"  id="queryForm" width="100%"
								 role="form" style="line-height:40px;vertical-align:middle;">
								     	<div  id="BottomDiv">
										<div class="form-group">
											 <label>由</label>
										<div class="input-group form-group">
											<input id="date-timepicker1" type="text" class="form-control"  fieldname="Time" operation="=" data-date-format="YYYY/MM/DD hh:mm" >
										</div>
										</div>
									    &nbsp;&nbsp;
										<div class="form-group">
											 <label>到</label>
										</div>
										<div class="input-group form-group">
											<input id="date-timepicker2" type="text" class="form-control"  fieldname="Time01" operation="=" data-date-format="YYYY/MM/DD hh:MM" > 
											<!-- <input type="text" class="input-sm form-control" name="end"> -->
										</div>
									  &nbsp;
									  <!-- 
									  <a data-target="" id="newToday" data-toggle="modal">
											<button id="btnToday" class="btn btn-primary btn-sm" type="button"><i class="#ace-icon  glyphicon glyphicon-plus-sign bigger-110"></i>本日</button>
									  </a>
									  &nbsp;
									   
									  <a data-target="" id="newWeek" data-toggle="modal">
											<button id="btnWeek" class="btn btn-primary btn-sm" type="button"><i class="#ace-icon  glyphicon glyphicon-plus-sign bigger-110"></i>本周</button>
									  </a>
									  &nbsp;
									  <a data-target="" id="newMonth" data-toggle="modal">
											<button id="btnMonth" class="btn btn-primary btn-sm" type="button"><i class="#ace-icon  glyphicon glyphicon-plus-sign bigger-110"></i>本月</button>
									  </a> 
									  -->
									  </div>
									  </form>
 
						       	<div id="chartdiv1">
						       	
						       	</div>
						   </div>
						   <div class="tab-pane" id="profile">
							  <form class="form-inline"  id="queryForm" width="100%"
								 role="form" style="line-height:40px;vertical-align:middle;">
								     	<div  id="BottomDiv">
										<div class="form-group">
											 <label>由</label>
										<div class="input-group form-group">
											<input id="date-timepicker1" type="text" class="form-control"  fieldname="Time" operation="=" data-date-format="YYYY/MM/DD hh:mm" >
										</div>
										</div>
									    &nbsp;&nbsp;
										<div class="form-group">
											 <label>到</label>
										</div>
										<div class="input-group form-group">
											<input id="date-timepicker2" type="text" class="form-control"  fieldname="Time01" operation="=" data-date-format="YYYY/MM/DD hh:MM" > 
											<!-- <input type="text" class="input-sm form-control" name="end"> -->
										</div>
									  &nbsp;
									  <!-- 
									  <a data-target="" id="newToday" data-toggle="modal">
											<button id="btnToday" class="btn btn-primary btn-sm" type="button"><i class="#ace-icon  glyphicon glyphicon-plus-sign bigger-110"></i>本日</button>
									  </a>
									  &nbsp;
									   
									  <a data-target="" id="newWeek" data-toggle="modal">
											<button id="btnWeek" class="btn btn-primary btn-sm" type="button"><i class="#ace-icon  glyphicon glyphicon-plus-sign bigger-110"></i>本周</button>
									  </a>
									  &nbsp;
									  <a data-target="" id="newMonth" data-toggle="modal">
											<button id="btnMonth" class="btn btn-primary btn-sm" type="button"><i class="#ace-icon  glyphicon glyphicon-plus-sign bigger-110"></i>本月</button>
									  </a> 
									  -->
									  </div>
									  </form>
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
		
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/date-time/bootstrap-datetimepicker.min.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/date-time/moment.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/date-time/daterangepicker.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/sys_resources/plugins/FusionCharts/JSClass/FusionCharts.js"></script>

		
<script type="text/javascript">
var controllername="${pageContext.request.contextPath}/jkjc/jcPrjPointsController";
var showDIV =true;
var scripts =[null];
var showDIV = true;
ace.load_ajax_scripts(scripts, function() {
	init();
	//DatePicker.datepicker("#date-timepicker2");
	//DatePicker.datepicker("#EDATE");
	
	//$('#date-timepicker1').datetimepicker().next().on(ace.click_event, function(){
	//				$(this).prev().focus();
	//			}); 
	//$('#date-timepicker2').datetimepicker().next().on(ace.click_event, function(){
	//				$(this).prev().focus();
	//			}); 
	
	//设置时间控件的格式
	
    var gridwidth=$("#tbDiv").width();
    //gridwidth =gridwidth -220;
	JqgridManage.initJqgrid(grid_table,queryForm,gridwidth); 
	
    var setHeight = $(window).height();
	
	if(navigator.userAgent.indexOf("Firefox") > -1){
	   setHeight = setHeight+50;
	}
	
    $("#grid_table").setGridHeight(setHeight - 360); 
   // var leftHeight= setHeight-170;
    $("#leftHeight").css("height",leftHeight);  
    
    //设置 放置 表格的 div的宽度
	//$("#tbDiv").css("width",gridwidth);
    
    $('#pointTab').css("width",$('.widget-box').width()-20);
    //$('#tabDiv').css("height",$('#leftHeight').height()-$('#tid').height()-$('#zid').height());
    $('#appendTr').css("width",$('.widget-box').width()-40);
    
 	queryJcd();

 	initTab();
 	tabCheck();
 	$("input[name='jcd']").each(function(){
 		$(this).click(function(){
 			queryJcd();
 		});
 	});
 	
 	charLoad();

 
});

function tabCheck(){
	 	$('#jcd tr').each(function(){
 		$(this).click(function(){
 			initTab();
			$(this).css("background", "#5B9BD5");
    		$(this).siblings().css("background", "#F3F3F3");
    		$(this).unbind("mouseout");
            $(this).unbind("mouseover");
		})
 	});
}

function charLoad(){
	var gridwidth=$("#tbDiv").width();
	//var dataXml = "<chart caption='Monthly Sales Summary' subcaption='For the year 2006' xAxisName='Month' yAxisName='Sales' yAxisMinValue='15000' numberPrefix='$' showValues='0' alternateHGridColor='FCB541' alternateHGridAlpha='20' divLineColor='FCB541' divLineAlpha='50' canvasBorderColor='666666' baseFontColor='666666' lineColor='FCB541'><set label='Jan' value='17400'/><set label='Feb' value='19800'/><set label='Mar' value='21800'/><set label='Apr' value='23800'/><set label='May' value='29600'/><set label='Jun' value='27600'/><set label='Jul' value='31800'/><set label='Aug' value='39700'/><set label='Sep' value='37800'/><set label='Oct' value='21900'/><set label='Nov' value='32900'/><set label='Dec' value='39800'/><styles><definition><style name='Anim1' type='animation' param='_xscale' start='0' duration='1'/><style name='Anim2' type='animation' param='_alpha' start='0' duration='0.6'/><style name='DataShadow' type='Shadow' alpha='40'/></definition><application><apply toObject='DIVLINES' styles='Anim1'/><apply toObject='HGRID' styles='Anim2'/><apply toObject='DATALABELS' styles='DataShadow,Anim2'/></application></styles></chart>";
	var myChart2 = new FusionCharts("${pageContext.request.contextPath}/assets/sys_resources/plugins/FusionCharts/Charts/Line.swf","myChartId2", gridwidth,"370");　　　　
    　 myChart2.setDataURL("${pageContext.request.contextPath}/assets/sys_resources/plugins/FusionCharts/Data/Line2D.xml");　　
    　 myChart2.render("chartdiv1");　　
}


function DoCheck()
	{
	var ch=document.getElementsByName("JKJC_TYPE");
	if(document.getElementsByName("selectAllCK")[0].checked==true)
	{
	for(var i=0;i<ch.length;i++)
	{
	ch[i].checked=true;
	}
	}else{
	for(var i=0;i<ch.length;i++)
	{
	ch[i].checked=false;
	}
	}
	}


	     //表格自适应宽度
$(window).on('resize.jqGrid',
		function() {
			$("#grid_table").jqGrid('setGridWidth',$("#tbDiv").width());
	}); 
	

function initTab(){
	 $('#jcd tr').each(function(){
		$(this).mouseover(function(){
			$(this).css("background", "#fbb908");
		}).mouseout(function(){
			$(this).css("background", "#F3F3F3");
		});
	});
} 


function shaixuan(){
		var data = combineQuery.getQueryCombineData(queryFormById,null,null);
	var data1 = {
		msg : data
	};
	$.ajax({
	    url:controllername+"?queryPointForType",
		data : data1,
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response){
	            var resultmsgobj = convertJson.string2json1(response.msg);
					var thtml= "";
				   if(resultmsgobj!=null && resultmsgobj !=''){//说明有数据
				     for(var i=0;i<resultmsgobj.response.data.length;i++){
				        var datslist=resultmsgobj.response.data[i];
				        var getType = datslist.POINT_TYPE//JKJC_TYPE;
				        //获取  guid 
				        //var UPD = datslist.PRJ_POINTS_UID;
				        thtml += "<tr><td id=\"tdValue"+i+"\">"+
							        "&nbsp;&nbsp;&nbsp;&nbsp;<input id=\"jkjc"+i+"\" type=\"checkbox\" name=\"JKJC_TYPE\"  value =\""+getType+"\" checked=\"checked\" />"+
							        "<span>&nbsp;"+getType+"</span>"+"</td></tr>";
				        
				        var code = datslist.POINT_CODE;
				     }
					}else{
					     return;
					
					} 
					
					//在 table 的 第一个tr下 添加 tr
					$("#appendTr tr:first").after(thtml);
			
		}
	});
}


function showme(){
 if(showDIV){
	    $("#TypeForPoint").slideDown(1000);
	    showDIV =false;
	   }else{
	    $("#TypeForPoint").slideUp(1000);
	    showDIV =true;
	   }
    } 
	     
	     
function init(){
	shaixuan();
	//点击 数据 打开 页面
	$("#btnData").click(function(){
	
	
	
	});
	
	
	$('#search').click(function() {
	
	});
	
  
	$("#searchBbxx").click(function(){
	//jQuery("#grid_table").jqGrid().trigger("reloadGrid");
		var data = Form2Json.formToJSON(executeForm);
		var data1 = defaultJson.packSaveJson(data);
		defaultJson.doInsertJson(controllername + "?query", data1); 
	}); 
	
	
	$('#btnSure').click(function() {
       queryJcd();
       //关闭窗口
       showme();
       //显示表格颜色
       initTab();
       tabCheck();
	});
	
	
	$("#btnOutput").click(function(){
	
	});
	
	$('#btnStp').click(function(){
		$('#btnStp').attr("data-target","jldwUser-input");
		$('#jldwUser-input').removeData("bs.modal");
		$("#jldwUser-input").empty();
		$('#jldwUser-input').modal({
			backdrop: 'static'
		});

		$.get("${ctx}/jsp/business/jkjc/parameter-setup.jsp",function(data) {
			$("#jldwUser-input").empty();
			$("#jldwUser-input").html("");
			$("#jldwUser-input").html(data);
		});
	});
	

}

function getTypeV(lname) {
	var v = "";
	$("input:checkbox[name='" + lname + "']:checked").each(function() {
		v += "'"+$(this).val() + "',";
	});
	if (v.length != 0) {
		v = v.substring(0, v.length - 1);
	}
	return v;
}

function queryJcd(){
		var type = getRadioV("jcd");
		var lx = getTypeV('JKJC_TYPE');
		
		$.ajax({
		url :'${pageContext.request.contextPath }/jkjc/jcPrjPointsController?queryList',
		data:{"project_uid":$('#project_uid').val(),"type":type,"lx":lx,"wdgz":getCheckboxV('wdgz')},                                                                    
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(response) {
			var html = "";
			$(response.obj).each(function(index,element){
				
				html +='<tr>';
				html +='<td>';
				html += element.POINT_CODE;
				html +='</td>';
				html +='<td>';
				html += element.JKJC_TYPE;
				html +='</td>';
				html +='</tr>';
			});
			$('#jcd').empty();
			$('#jcd').html(html);
			
	    }
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

//展示类型按钮 设置
function showme(){
 if(showDIV){
 //第一次点击 向下 展开小窗
         $("#switchPicture").removeClass("fa-caret-down");
         $("#switchPicture").addClass("fa-caret-up");
	     $("#TypeForPoint").slideDown(1000);
	     showDIV =false;
	   }else{
	     $("#TypeForPoint").slideUp(1000);
	     $("#switchPicture").removeClass("fa-caret-up");
	     $("#switchPicture").addClass("fa-caret-down");
	     showDIV =true;
	   }
    } 

</script>
<form method="post" id="queryFormById"  >
   <table class="B-table" width="100%">
   <!--可以再此处加入hidden域作为过滤条件 -->
   	<TR  style="display:none;">
     	<TD class="right-border bottom-border">
      	<INPUT type="text"  kind="text" id="PROJECT"  fieldname="PROJECT" value="" operation="="/>
      
      	</TD>
		<TD class="right-border bottom-border">
		</TD>
     </TR>
   </table>
</form>		
</body>
</html>