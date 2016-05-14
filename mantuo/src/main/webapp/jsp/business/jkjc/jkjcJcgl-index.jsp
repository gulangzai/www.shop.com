<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<%
	//String pointItemid = request.getParameter("pointItemid");
	//String hosttype = request.getParameter("hosttype");
%>
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
    border-color:#D6D9D9;
    background-color: #D6D9D9;
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
								<label style="font-size:16px; margin-right: 30px;">监测点</label>
						   	</div>
							<div class="form-group" id="zid" >
								<label>
									<input id="BTN_ALL" type="radio" name="jcd" value="all" checked="checked">全部
									<input id="BTN_WARN" type="radio" name="jcd" value="bj">仅报警
								</label>
							</div>
							<div class="form-group" >
								<input type="text" id="keyWords"  placeholder="测点编号" style="width: 100%;">
							</div>
							<div style="float: right; display: none; position: absolute; top: 35px; left:40px;  overflow: hidden;border: 1px solid #d1d2d9;background-color: #f3f3f3;"  id="TypeForPoint" >
								<table id="appendTr"  style=""  class="jcdss" 	 >
									<tr>
										<td>
											&nbsp;&nbsp;&nbsp;&nbsp;<input onclick="DoCheck();" id="selectAllCK" type="checkbox" name="selectAllCK"  value="" style="cursor: pointer;" checked="checked" />
											全部
										</td>
									</tr>
									<tr>
										<td>
											&nbsp;&nbsp;&nbsp;&nbsp;<input id="userFocused" type="checkbox" name="userFocused" value="1" checked="checked" fieldname=""/>
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
										    <th></th>
											<th>编号</th>
											<th>类型</th>
										</tr>
									</thead>
									<tbody id="jcd">
									</tbody>
								</table>
							</div>
							
							<div style="position:absolute;z-index: 100;top:34px;right:6px;background-color:#F6FBFB;">
								<button onclick="showme()" class="btn btn-xs bigger btn-yellow dropdown-toggle" data-toggle="dropdown">
								   <i id="switchPicture" class="ace-icon fa fa-caret-down"></i>
									<!-- <i class="ace-icon fa fa-chevron-down icon-on-right"></i> -->
								</button>
					    	</div>					 
						</form>
						</div>
					  </div>
					  
					  <div class="col-xs-6 col-sm-10">
					  <div class="tabbable"id="bbDiv" style="overflow-y:scroll;height: 508px;">

						 <ul id="myTab" class="nav nav-tabs">
						   <li class="active">
						      <a href="#home" data-toggle="tab">曲线</a>
						   </li>
						   <li>
						      <a href="#profile" data-toggle="tab">数据</a>
						   </li>
							  <a data-target="" id="btnStp" data-toggle="modal">
								<button id="btnSet" class="btn btn-primary btn-sm" type="button" >设置</button>
					  		  </a>
						 </ul>
					
						 <div class="tab-content" id="tbDiv">
						 	<div class="tab-pane in active" id="home" >
						 		<form class="form-inline"  id="queryFormChart" width="100%"
								 role="form" style="line-height:40px;vertical-align:middle;">
								     	<div >
										<div class="form-group">
											 <label>由</label>
										<div class="input-group form-group">
											<input id="date-timepicker1" type="text" class="form-control"    operation="=" data-date-format="yyyy-mm-dd" >
										</div>
										</div>
									    &nbsp;&nbsp;
										<div class="form-group">
											 <label>到</label>
										</div>
										<div class="input-group form-group">
											<input id="date-timepicker2" type="text" class="form-control"      data-date-format="yyyy-mm-dd" > 
										</div>
											<a data-target="" id="newToday" data-toggle="modal">
												<button id="btnToday" class="btn btn-primary btn-sm" type="button" onclick="charLoad();">查询</button>
									  		</a>
									  </div>
									  </form>
 								<!-- 沉降类  -->
								<div>
							       	<div id="linechart" style="height:380px;display: none;" >
							       	
							       	</div>
							       	<!--  -->
							       	<div id="barchart" style="height:380px;display: none;">
							       	
							       	</div>
						       	</div>
						       	<!-- 测斜类 -->
						       	<div  style="width: 100%">
							       	<div id="linechart2" style="height:480px;width: 50%;margin-top:25px; float: left;" >
							       	
							       	</div>
							       	<div id="linechart3" style="height:480px;width: 50%;margin-top:25px; float:left;" >
							       	
							       	</div>
						       	</div>
						       	<div style="width: 100%">
							       	<div id="barchar2" style="height:380px;width: 50%; float: left;" >
							       	
							       	</div>
	
							       	<div id="barchar3" style="height:380px;width: 50%;float: left;" >
							       	
							       	</div>
						     	</div>
						       
						   </div>
						   <div class="tab-pane" id="profile"  >
							  <form class="form-inline"  id="queryFormTab" width="100%"
								 role="form" style="line-height:40px;vertical-align:middle;">
								 <input type="hidden" id="code" fieldname="code">
								 <input type="hidden" id="type" fieldname="type">
								 <input type="hidden" id="typename" fieldname="typename">
								 <input type="hidden" id="datatype">
								 <input type="hidden" id="POINT_ITEM_UID" fieldname="POINT_ITEM_UID">
								 <input type="hidden" id="JC_PRJ_ITEM_UID" >
								 
								     	<div  id="BottomDiv">
										<div class="form-group">
											 <label>由</label>
										<div class="input-group form-group">
											<input id="date-timepicker11" type="text" class="form-control" fieldname="time1"  data-date-format="yyyy-mm-dd" >
										</div>
										</div>
									    &nbsp;&nbsp;
										<div class="form-group">
											 <label>到</label>
										</div>
										<div class="input-group form-group">
											<input id="date-timepicker22" type="text" class="form-control" fieldname="time2"  data-date-format="yyyy-mm-dd" > 
											
										</div>
											<a data-target="" id="newToday" data-toggle="modal">
												<button id="btnToday" class="btn btn-primary btn-sm" type="button" onclick="btnSearch();">查询</button>
									  		</a>
									  </div>
									  </form>
									<table id="grid_table"  class="table table-striped table-bordered table-hover">
								
									</table>
									<table id="cxTab"  class="table table-striped table-bordered table-hover">
									
									</table>
						
	
						</div>
					  </div>
					</div>
				</div>
			</div>
			
			<div id="jkglSet_input" class="modal"></div>

<%@ include file="/jsp/framework/common/basic_scripts.jsp"%>

    <script
		src="${pageContext.request.contextPath }/assets/js/jquery-ui.custom.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/assets/sys_resources/js/common/Form2Json.js"> </script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/date-time/moment.min.js"></script> 		
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/date-time/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/date-time/daterangepicker.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/date-time/locales/bootstrap-datepicker.zh-CN.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/sys_resources/plugins/echarts/echarts-all.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/sys_resources/plugins/echarts/macarons.js"></script>
		
<script type="text/javascript">
var controllername="${pageContext.request.contextPath}/jkjc/jcPrjPointsController";
var showDIV =true;
var scripts =[null];
var showDIV = true;
var isallshowing = true;
ace.load_ajax_scripts(scripts, function() {
	init();
	DatePicker.datepickerid("#date-timepicker1");
	DatePicker.datepickerid("#date-timepicker2");
	DatePicker.datepickerid("#date-timepicker11");
	DatePicker.datepickerid("#date-timepicker22");

	
 	queryJcd();
 	
 	initTab();

 	$("input[name='jcd']").each(function(){
 		$(this).click(function(){
 			queryJcd();
 		});
 	});
 	
   dateInit();	
   setUp();
  
   $('#keyWords').change(function(){
	    queryJcd();
       //显示表格颜色
       initTab();
      // tabCheck();
   });
   $('.ajax-loading-overlay').remove();
});

//表格自适应宽度
//$(window).on('resize.jqGrid',
//	function() {
//		$("#grid_table").jqGrid('setGridWidth',$("#tbDiv").width());
//}); 

//初始化时间
function dateInit(){
 	var date = new Date();
 	var year = date.getFullYear();
 	var month = date.getMonth()+1;
 	var day = date.getDay();
 	$('#date-timepicker1').val(year+"-"+month+"-"+day);
 	//$('#date-timepicker1').val("2010-10-10");
 	$('#date-timepicker11').val(year+"-"+month+"-"+day);
 	var day2 = day+7;
 	$('#date-timepicker2').val(year+"-"+month+"-"+day2);
 	$('#date-timepicker22').val(year+"-"+month+"-"+day2);
 	
}

//设置div属性
function setUp(){
		
    var setHeight = $(window).height();
	
	if(navigator.userAgent.indexOf("Firefox") > -1){
	   setHeight = setHeight+50;
	}
	
    $("#grid_table").setGridHeight(setHeight - 360); 
   // var leftHeight= setHeight-170;
    $("#leftHeight").css("height",$('#bbDiv').height()+33);  
    
    //设置 放置 表格的 div的宽度
	//$("#tbDiv").css("width",gridwidth);
    
    $('#pointTab').css("width",$('.widget-box').width()-20);
    $('#tabDiv').css("height",$('#bbDiv').height()-$('#tid').height()-$('#zid').height()-5);
    $('#appendTr').css("width",$('.widget-box').width()-40);
}

//function initTabCheck(pointItemid){
//	$('#jcd tr').each(function(){
//		if($(this).children("td").get(4).innerHTML == pointItemid){//
//			tabCheck($(this)[0]);
//		}
//	});
//}

function tabCheck(demo){
	 	//$('#jcd tr').each(function(){
 		//$(this).click(function(){
 			initTab();
			$(demo).css("background", "#5B9BD5");
    		$(demo).unbind("mouseout");
            $(demo).unbind("mouseover");
           
            var type = $(demo).children("td").get(3).innerHTML;
            var POINT_ITEM_UID = $(demo).children("td").get(4).innerHTML;
            var datatype = $(demo).children("td").get(5).innerHTML;
            var code = $(demo).children("td").get(6).innerHTML;
            var typename = $(demo).children("td").get(7).innerHTML;
            var JC_PRJ_ITEM_UID = $(demo).children("td").get(8).innerHTML;
			$('#code').val(code);
			$('#type').val(type);
			$('#datatype').val(datatype);
			$('#POINT_ITEM_UID').val(POINT_ITEM_UID);
			$('#typename').val(typename);
			$('#JC_PRJ_ITEM_UID').val(JC_PRJ_ITEM_UID);
			if(datatype=="测斜"){
				$('#date-timepicker1').val("2010-10-10");
				showCx();
				hideCj();
				linechartLoad2();
				linechartLoad3();
				barchar2();
				barchar3();
				tabSearchCx();
			}else{
				dateInit();
				showCj();
            	hideCx();
				linechartLoad();
            	barchartLoad();
            	tabSearch();
			}


		//})
 	//});
}

//沉降类折线图
function linechartLoad(){
	        // 基于准备好的dom，初始化echarts图表
        var myChart = echarts.init(document.getElementById('linechart')); 
		var option = "";
			
			$.ajax({ 
				url: "${pageContext.request.contextPath }/jkjc/jcJcDataController?queryLineChart",
				data:{'code':$('#code').val(),'type':$('#type').val(),'time1':$('#date-timepicker1').val(),'time2':$('#date-timepicker2').val(),'projectUid':$("#project_uid").val()},                                                                 
				async : false,
				type : 'post',
				dataType: 'json',
				success: function(data){
					var redata = eval('(' + data.obj + ')')
			    	option = redata.option;
	    			$("#date-timepicker1").val(redata.time1);
					$("#date-timepicker2").val(redata.time2);
				}
			});
		
         //console.info(eval('(' + option + ')'));
		 myChart.setOption(option); 
		
       // $('.ajax-loading-overlay').hide();
}

//沉降类柱状图
function barchartLoad(){
	        // 基于准备好的dom，初始化echarts图表
        var myChart = echarts.init(document.getElementById('barchart')); 
		var option = "";
			
			$.ajax({ 
				url: "${pageContext.request.contextPath }/jkjc/jcJcDataController?queryBarChart", 
				data:{'code':$('#code').val(),'type':$('#type').val(),'time1':$('#date-timepicker1').val(),'time2':$('#date-timepicker2').val()}, 
				async : false,
				type : 'post',
				dataType: 'json',
				success: function(data){
					var redata = eval('(' + data.obj + ')')
			    	option = redata.option;
	    			$("#date-timepicker1").val(redata.time1);
					$("#date-timepicker2").val(redata.time2);
				}
			});
		
         //console.info(eval('(' + option + ')'));
		 myChart.setOption(option); 
       // $('.ajax-loading-overlay').hide();
}

//测斜类 折线图
function linechartLoad2(){
	        // 基于准备好的dom，初始化echarts图表
        var myChart = echarts.init(document.getElementById('linechart2')); 
		var option = "";
			
			$.ajax({ 
				url: "${pageContext.request.contextPath }/jkjc/jcCxDataController?queryLineChart",
				data:{'code':$('#code').val(),'type':$('#type').val(),'time1':$('#date-timepicker1').val(),'time2':$('#date-timepicker2').val()},                                                                 
				async : false,
				type : 'post',
				dataType: 'json',
				success: function(data){
			    	option = data.obj;

				}
			});
		
		 myChart.setOption(eval('(' + option + ')')); 
       // $('.ajax-loading-overlay').hide();
}
//测斜类 折线图
function linechartLoad3(){
	        // 基于准备好的dom，初始化echarts图表
        var myChart = echarts.init(document.getElementById('linechart3')); 
		var option = "";
			
			$.ajax({ 
				url: "${pageContext.request.contextPath }/jkjc/jcCxDataController?queryLineChart2",
				data:{'code':$('#code').val(),'type':$('#type').val(),'time1':$('#date-timepicker1').val(),'time2':$('#date-timepicker2').val()},                                                                 
				async : false,
				type : 'post',
				dataType: 'json',
				success: function(data){
			    	option = data.obj;

				}
			});
		
		 myChart.setOption(eval('(' + option + ')')); 
       // $('.ajax-loading-overlay').hide();
}

//测斜类 柱状图
function barchar2(){
	        // 基于准备好的dom，初始化echarts图表
        var myChart = echarts.init(document.getElementById('barchar2')); 
		var option = "";
			
			$.ajax({ 
				url: "${pageContext.request.contextPath }/jkjc/jcCxDataController?queryBarChart",
				data:{'code':$('#code').val(),'type':$('#type').val(),'time1':$('#date-timepicker1').val(),'time2':$('#date-timepicker2').val()},                                                                 
				async : false,
				type : 'post',
				dataType: 'json',
				success: function(data){
			    	option = data.obj;

				}
			});
		
		 myChart.setOption(eval('(' + option + ')')); 
       // $('.ajax-loading-overlay').hide();
}
//测斜类 柱状图
function barchar3(){
	        // 基于准备好的dom，初始化echarts图表
        var myChart = echarts.init(document.getElementById('barchar3')); 
		var option = "";
			
			$.ajax({ 
				url: "${pageContext.request.contextPath }/jkjc/jcCxDataController?queryBarChart2",
				data:{'code':$('#code').val(),'type':$('#type').val(),'time1':$('#date-timepicker1').val(),'time2':$('#date-timepicker2').val()},                                                                 
				async : false,
				type : 'post',
				dataType: 'json',
				success: function(data){
			    	option = data.obj;

				}
			});
		
		 myChart.setOption(eval('(' + option + ')')); 
       // $('.ajax-loading-overlay').hide();
}



//第一次加载表格
function tabLoad(){
	var gridwidth=$("#tbDiv").width();
	JqgridManage.initJqgrid(grid_table,queryFormTab,gridwidth); 
}

//数据表格查询
function tabSearch(){
		$.ajax({
		url:"${pageContext.request.contextPath}/jkjc/jcJcDataController?queryTab",
		data:{'code':$('#code').val(),'type':$('#type').val(),'time1':$('#date-timepicker11').val(),'time2':$('#date-timepicker22').val()}, 
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(result){
	    	var datalist = result.obj.data;
	    	var html = "";
	    	if(datalist!=""){
	    		var tobj = datalist[datalist.length-1];
	    		html+="<thead><tr>";
	    		html+="<th>"+tobj.REPORT_DATE+"</th>";
	    		html+="<th>"+tobj.V+"</th>";
	    		html+="<th>"+tobj.B+"</th>";
	    		html+="<th>现象</th>";
	    		html+="</tr></thead>";
	    		html+="<tbody>";
	    		for(var i =0;i<datalist.length-1;i++){
	    			var dataObj = datalist[i];
	    			html+="<tr>";
	    			html+="<td>"+dataObj.REPORT_DATE+"</td>";
	    			html+="<td>"+dataObj.V+"</td>";
	    			html+="<td>"+dataObj.B+"</td>";
	    			html+="<td></td>";
	    			html+="<tr>";
	    			
	    		}
	    		html+="</tbody>";
	    		$('#grid_table').empty();
	    		$('#grid_table').html(html);
	    		$("#date-timepicker11").val(result.obj.time1);
				$("#date-timepicker22").val(result.obj.time2);
	    	}else{
	    		var html = "<tbody><tr><td><font color='red'>没有可显示的数据</font></td></tr></tbody>";
	    		$('#grid_table').empty();
	    		$('#grid_table').html(html);
	    		//console.log(html);
	    	}
	    	
		}
	}); 
}

function tabSearchCx(){
	$.ajax({
		url:"${pageContext.request.contextPath}/jkjc/jcCxDataController?queryTab",
		data:{'code':$('#code').val(),'type':$('#type').val(),'time1':$('#date-timepicker11').val(),'time2':$('#date-timepicker22').val()}, 
		cache : false,
		async :	false,
		dataType : "json",  
		type : 'post',
		success : function(result){
	    	var datalist = result.obj.data;
	    	var html = "";
	    	if(datalist!=""){
	    		var tobj = datalist[datalist.length-1];
	    		var i = 0;
	    		var txt = 'NUM'+i;
	    		html+="<thead><tr>";
	    		html+="<th>"+tobj.DEEP+"</th>"; 
	    		while (tobj[txt]!=undefined){
	    			html+="<th>"+tobj[txt]+"(mm)</th>";
	    			i++;
	    			txt = 'NUM'+i;
	    		}
	    		html+="</tr></thead>";
	    		html+="<tbody>";

	    		for(var i =0;i<datalist.length-1;i++){
	    			var dataObj = datalist[i];
	    			var n = 0;
	    			var txt1 = 'num'+n;
	    			html+="<tr>";
	    			html+="<td>"+dataObj.DEEP+"</td>";
	    			while(dataObj[txt1]!=undefined){
	    				html+="<td>"+dataObj[txt1]+"</td>";	
	    				n++;
	    				txt1 = 'num'+n;
	    			}
	    			html+="<tr>";
	    		}
	    		html+="</tbody>";
	    		$('#cxTab').empty();
	    		$('#cxTab').html(html);
	    		$("#date-timepicker11").val(result.obj.time1);
				$("#date-timepicker22").val(result.obj.time2);
	    		
	    	}else{
	    		var html = "<tbody><tr><td><font color='red'>没有可显示的数据</font></td></tr></tbody>";
	    		$('#cxTab').empty();
	    		$('#cxTab').html(html);
	    		//console.log(html);
	    	}
	    	
		}
	}); 
}

//点击数据查询按钮
function btnSearch(){
	var datatype = $('#datatype').val();
	if(datatype=="测斜"){
		tabSearchCx();
	}else{
	 	tabSearch();	
	}
}


//点击图表查询按钮
function charLoad(){
	var datatype = $('#datatype').val();
	if(datatype=="测斜"){
		showCx();
		hideCj();
		linechartLoad2();
		linechartLoad3();
		barchar2();
		barchar3();

	}else{
		showCj();
        hideCx();
		linechartLoad();
        barchartLoad();

	}

}

//显示沉降
function showCj(){
	$('#barchart').show();
	$('#linechart').show();
	$('#grid_table').show()
}
//显示测斜
function showCx(){
	$('#linechart2').show();
	$('#linechart3').show();
	$('#barchar2').show();
	$('#barchar3').show();
	$('#cxTab').show();
}
//影藏沉降
function hideCj(){
 	$('#barchart').hide();
	$('#linechart').hide();
	$('#grid_table').hide();
}
//影藏测斜
function hideCx(){
	$('#linechart2').hide();
	$('#linechart3').hide();
	$('#barchar2').hide();
	$('#barchar3').hide();
	$('#cxTab').hide();
}



function DoCheck(){
	var ch=document.getElementsByName('JKJC_TYPE');
	if(document.getElementsByName("selectAllCK")[0].checked==true){
		for(var i=0;i<ch.length;i++){
			ch[i].checked=true;
		}
	}else{
		for(var i=0;i<ch.length;i++){
			ch[i].checked=false;
		}
	}
}

function initTab(){
	 $('#jcd tr').each(function(){
		$(this).css("background", "");
		$(this).mouseover(function(){
			$(this).css("background", "#fbb908");
		}).mouseout(function(){
			$(this).css("background", "");
		});
	});
} 

//右侧类型筛选
function shaixuan(){

	var p_Items = window.parent.getItems();

	var thtml= "";
	for (var i = 0 ; i < p_Items.JC_PRJ_ITEM_UID.length ; i++)
	{
		var itemid = p_Items.JC_PRJ_ITEM_UID[i];
		var itemshortname = p_Items.SHORT_NAME[i];
		
		thtml += "<tr><td id=\"tdValue"+i+"\">"+
			"&nbsp;&nbsp;&nbsp;&nbsp;<input id=\"jkjc"+i+"\" type=\"checkbox\" name=\"JKJC_TYPE\"  value =\""+itemid+"\" checked=\"checked\" />"+
			"<span>&nbsp;"+itemshortname+"</span>"+"</td></tr>";
	}
	
	//console.log(thtml);
	//在 table 的 第一个tr下 添加 tr
	$("#appendTr tr:first").after(thtml);
}

//筛选框显示影藏
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
      // tabCheck();
     
	});
	
	$('#BTN_ALL').click(function() {
		isallshowing = true;
       queryJcd();
       if (!showDIV)
       {
	       //关闭窗口
	       showme();
       }
       //显示表格颜色
       initTab();
       tabCheck();
     
	});
	
	$('#BTN_WARN').click(function() {
		isallshowing = false;
       queryJcd();
       if (!showDIV)
       {
	       //关闭窗口
	       showme();
       }
       //显示表格颜色
       initTab();
       tabCheck();
     
	});
	
	$('#btnStp').click(function(){
		$('#btnStp').attr("data-target","jkglSet_input");
		$('#jkglSet_input').removeData("bs.modal");
		$("#jkglSet_input").empty();
		$('#jkglSet_input').modal({
			backdrop: 'static'
		});
//console.log($("#typename").val());
		$.get("${ctx}/jsp/business/jkjc/parameter-setup.jsp?POINT_ITEM_UID="+$("#POINT_ITEM_UID").val()+"&JC_PRJ_ITEM_UID="+$('#JC_PRJ_ITEM_UID').val(),function(data) {
			$("#jkglSet_input").empty();
			$("#jkglSet_input").html("");
			$("#jkglSet_input").html(data);
		});
	});
	

}

function updateJcdTable(pointitemid, singlewarn, totalwarn1, totalwarn2){
	window.parent.updateWarnValue(pointitemid, singlewarn, totalwarn1, totalwarn2);
	
	var p_Points_Items_All = window.parent.getPoints_Items_All();

	for (var i = 0 ; i < p_Points_Items_All.length ; i++)
	{
		if (pointitemid == p_Points_Items_All[i].PRJ_POINT_ITEM_UID)
		{
		 	$('#jcd tr').each(function(){
            	var POINT_ITEM_UID = $(this).children("td").get(4).innerHTML;
            	if (pointitemid == POINT_ITEM_UID)
            	{
					var html = "";
					if (p_Points_Items_All[i].IsWarning)
					{
						html = '<img src="${pageContext.request.contextPath}/assets/images/baojing/baojing2.png" width="15px" height="15px;"/>';
					}

           			$(this).children("td").get(0).innerHTML = html;
            		
            		return false;
            	}
			});
						
			break;
		}
	}
}

//获取选中值
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

//获取选中值
function getSelectedItems(lname) {
	var selectedItems = new Array();
	$("input:checkbox[name='" + lname + "']:checked").each(function() {
		selectedItems.push($(this).val());
	});
	return selectedItems;
}

//左侧测点列表
function queryJcd(){

		$('#jcd').empty();

		var selectedItems = getSelectedItems('JKJC_TYPE');
		//console.log(isallshowing);
		//console.log(selectedItems);
		var isuserfocuschecked = document.getElementById('userFocused').checked;
//console.log(isuserfocuschecked);
		
		if (selectedItems.length == 0 && !isuserfocuschecked)
		{
			return;	
		}
		
		var keyWords = $('#keyWords').val();
		
		
		var html = "";
		
		var p_Points_Items_All = window.parent.getPoints_Items_All();

		for (var i = 0 ; i < p_Points_Items_All.length ; i++)
		{
		//console.log("IsContained " + window.parent.IsContained(selectedItems, p_Points_Items_All[i].JC_PRJ_ITEM_UID));
		//console.log("IsFocused " + window.parent.IsFocused(p_Points_Items_All[i].PRJ_POINTS_UID));
			var flag = p_Points_Items_All[i].POINT_CODE.indexOf(keyWords);
			//alert(flag);
			if(flag==0){
				//过滤筛选
				if (window.parent.IsContained(selectedItems, p_Points_Items_All[i].JC_PRJ_ITEM_UID)
				|| (isuserfocuschecked && window.parent.IsFocused(p_Points_Items_All[i].PRJ_POINTS_UID)))
				{
					//添加测点列表记录
					if(isallshowing || p_Points_Items_All[i].IsWarning)
					{
						html +='<tr onclick="tabCheck(this);"';
						if (p_Points_Items_All[i].IsWarning)
						{
						    html +=' style="" ';//background-color:#FDA1A8 ISWARN
							html +='>';
							html +='<td title="" align="center"><img src="${pageContext.request.contextPath}/assets/images/baojing/baojing2.png" width="15px" height="15px;"/>';
							html +='</td>';
						}
						else
						{
							html +=' style="" ';//background-color:#FDA1A8 ISNOTWARN
							html +='>';
							html +='<td title="" >'
							html +='</td>';
						}
						
						html +='<td';
						if (p_Points_Items_All[i].IsWarning)
						{
							html +=' style="" ';//background-color:#FDA1A8 测点编号
						}
						else
						{
							html +=' style="" ';//background-color:#F3F3F3;
						}
						html +='>';
						html += p_Points_Items_All[i].POINT_CODE;
						html +='</td>';
						html +='<td';
						if (p_Points_Items_All[i].IsWarning)
						{
							html +=' style="" ';//background-color:#FDA1A8 检测类型
						}
						else
						{
							html +=' style="" ';//background-color:#F3F3F3;
						}
						html +='>';
						html += p_Points_Items_All[i].SHORT_NAME;
						html +='</td>';
						html +='<td title="JC_TYPE_UID"  style="display: none;">'+p_Points_Items_All[i].JC_TYPE_UID;
						html +='</td>';
						html +='<td title="PRJ_POINT_ITEM_UID"  style="display: none;">'+p_Points_Items_All[i].PRJ_POINT_ITEM_UID;
						html +='</td>';
						html +='<td title="datatype"  style="display: none;">'+p_Points_Items_All[i].datatype;
						html +='</td>';
						html +='<td title="PRJ_POINTS_UID"  style="display: none;">'+p_Points_Items_All[i].PRJ_POINTS_UID;
						html +='</td>';
						html +='<td title="JC_TYPE_NAME"  style="display: none;">'+p_Points_Items_All[i].JC_TYPE_NAME;
						html +='</td>';
						html +='<td title="JC_PRJ_ITEM_UID"  style="display: none;">'+p_Points_Items_All[i].JC_PRJ_ITEM_UID;
						html +='</td>';
						
						html +='</tr>';
					}
				}
			}

		}
		
		//console.log(html);
		$('#jcd').html(html);
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