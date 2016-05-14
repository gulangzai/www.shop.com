<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ccthanking.framework.common.User"%>
<%@ page import="com.ccthanking.framework.common.rest.handle.servlet.RestContext" %>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<head>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<style>
/**设置页面 布局 */
.page-content {
    background-color: #fff;
    position: relative;
    margin: 0;
    padding:3px 0px 0px 3px;
}
 .padding-header{
  margin:0px;
  padding-top:4px;
  padding-left:2px;
  padding-bottom:4px;
  padding-right:2px;
 }
 
  .main_table{
 width: 100%;
 border:1px #5B9BD5 solid;
 border-collapse:collapse;
 color: #FFFFFF;
 font-size:22px;
 }
 .main_table tr {
 height: 100px;
 }
 
 .main_table td {
 text-align: center;
 }
 
 .jcdss tr{
 height: 16px;
 }
 
 .jcdss td{
 height:16px;
 text-align:left;
 font-size:15px;
 color:#616161;
 }

/** border-color:背景*/
.addStyle{
background-color: #f6fbfb!important; border: 1px #f6fbfb solid;
position: absolute;
right:10px;
top:100px;
width: 116px; 
/* height: 180px;  */
border-right: 2px #f6fbfb solid;
z-index: 10;
}
.btn-yellow:active, .btn-yellow:hover, .open .btn-yellow.dropdown-toggle {
    border-color: #D6D9D9;
    background-color: #D6D9D9!important;
}
.btn-xs {
    border-width: 0px;
}

.detailed ul li{
	list-style-type: none;
}


.detailed{
	width: 210px;
	font-color:white;
	/**border-radius:15px;**/
	background-color:#F97A42;
	-webkit-box-shadow: 3px 3px 3px;  
	-moz-box-shadow: 3px 3px 3px;  
	box-shadow: 3px 3px 3px; 
	/**filter:alpha(opacity=90); 
    -moz-opacity:0.9;   
    -khtml-opacity:0.9;   
    opacity: 0.9;  **/
}

.detailed ul{
	margin-top:0px;
	padding-top:5px;
	padding-right:5px;
	margin-left: 8px;
	margin-right:5px;
	border-bottom:1px white solid;
}
	
.detailed ul li{
	list-style-type: none;
	color:white;
}

.detailed_head{
	margin-top:5px;	
	margin-left:5px;
	margin-right:5px;
	padding-left:5px;
	padding-right:5px;
	padding-bottom:5px;	
	height:20px;
	border-bottom:1px white solid;
}
.detailed_bottom{
	margin-left:5px;
	padding-left:5px;
	height:20px;
	color:white;
}

.detailed_head i{
	cursor:pointer;
}
.detailed_ul div{
	width:100%;
	height:30px;
	font-size:14px;
	text-align:left;
}

	
.detailed_head a{
	text-decoration: none;
	color: white;
}
</style> 

</head>
<body>
<!-- class modal-dialog -->
<div class="main-container" id="main-container">
    <div class="page-content">
	 <div class="col-sm-12">
		<div style="width: 100%;  position: relative;">
			<!-- DIV  height: 145px; -->
			    <div style="margin-top: 650px;">
					<div style="float: left;"><input type="text" id="keyWords"  placeholder="测点编号" ><a href="javascript:void(0);" onclick="pointClick();"><i class="ace-icon fa fa-search nav-search-icon"></i></a></div>
	 				<div style="float: right;">
	 					<table id="appendTr" style="width: 100% " class="jcdss" 	action="${ctx}/jkjc/jcPrjPointsController?queryPointForType" >
							<tr>
								<td >
								  <div onclick='DoCheck();' >
									<input id="selectAllCK"  onclick="pointClickAll();" type="checkbox" name="selectAllCK" fieldname="selectAllCK"  checked="checked" value="" style="cursor: pointer;" />
								  	<img src="${pageContext.request.contextPath }/assets/images/3dimages/quan_blue.png" title="全部"/>&nbsp;&nbsp;&nbsp;&nbsp;
								  </div>
								</td>
							
								<td>
								<div>
								 <input id="userFocused" onclick="userFocusedClick();" type="checkbox" name="userFocused" checked="checked" fieldname="userFocused" style="cursur:pointer;"/>
								 <img src="${pageContext.request.contextPath }/assets/images/3dimages/guanzhu_blue.png" title="我的关注"/>
								</div>
								</td>
							</tr>
						</table>
					</div>	
				 </div>
			    <div  style="position:absolute;z-index: 100;bottom:615px;right:11px;height:20px;background-color:#f6fbfb">
					<button onclick="showme()" class="btn btn-xs bigger btn-yellow dropdown-toggle" data-toggle="dropdown">
					   <i id="switchPicture" class="ace-icon fa fa-caret-down"></i>
					</button>
			    </div>
				<div id="detailed" style="position:absolute; top:-650px;z-index: 100;height:495px; width:80%; left:10%; background-color:#f6fbfb;display: none;">
					
					<button onclick="closeModel();" class="btn btn-xs bigger btn-yellow dropdown-toggle" style="float: right;" data-toggle="dropdown">
					   <i>X</i>
					</button>
					<div class="main-container" id="main-container">
						<div class="page-content">
							<div class="col-xs-12">
			
								  <div class="tabbable"id="bbDiv" style="overflow-y:scroll;height:480px;">
			
									 <ul id="myTab" class="nav nav-tabs">
									   <li class="active">
									      <a href="#home" data-toggle="tab">曲线</a>
									   </li>
									   <li>
									      <a href="#profile" data-toggle="tab">数据</a>
									   </li>
										  <a data-target="" id="btnStp" data-toggle="modal" style="float: right;">
											<button id="btnSet" class="btn btn-primary btn-sm" type="button" >设置</button>
								  		  </a>
									 </ul>
								
									 <div class="tab-content" id="tbDiv">
									 	<div class="tab-pane in active" id="home" >
									 		<form class="form-inline"  id="queryFormChart" width="100%"
											 role="form" style="line-height:40px;vertical-align:middle;">
											     	<div  id="BottomDiv">
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
			 
									       	<div id="linechart2" style="height:480px;width: 400px;" >
									       	
									       	</div>
									       	<div id="linechart" style="height:380px;width:880px;" >
									       	
									       	</div>
									       	<!--  -->
									       	<div id="barchart" style="height:380px; ">
									       	
									       	</div>
									     
									       
									   </div>
									   <div class="tab-pane" id="profile"  >
										  <form class="form-inline"  id="queryFormTab" width="100%"
											 role="form" style="line-height:40px;vertical-align:middle;">
											 <input type="hidden" id="code" fieldname="code">
											 <input type="hidden" id="type" fieldname="type">
											 <input type="hidden" id="iscx">
											 <input type="hidden" id="POINT_ITEM_UID" fieldname="POINT_ITEM_UID">
											 
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
						</div>
				    </div>
			    <input type="hidden" id="fromType" value="降水">
	      </div>
     </div>
   </div>
</div>



<div id="jkglSet_input" class="modal" ></div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/sys_resources/plugins/echarts/echarts-all.js"></script>

<script type="text/javascript">
var controllername="${pageContext.request.contextPath}/jkjc/jcPrjPointsController";
var showDIV =true;
var checkClick = true;
var scripts =[null];
var gridwidth;
var setHeight;
var detailzindex = 15;
ace.load_ajax_scripts(scripts, function(){
	var PROJECT = $("#project_uid").val();
	$("#PROJECT_UID").val(PROJECT);
	gridwidth=$(".page-content").width();
	setHeight = $(".page-content").height();

  	//$('#pic').width($('#navbar').width()-200);
    $('.ajax-loading-overlay').remove();
  	init();
    pointClickAll();
});

//实现勾上全选 未勾上 全不选功能
function DoCheck(){
	var ch = document.getElementsByName('JC_PRJ_ITEM_UID');
	for(var i=0;i<ch.length;i++){
		ch[i].checked=document.getElementById('selectAllCK').checked;
	}
}

//初始化 监测点 类型 页面显示 
function init(){

	var p_Items1 = null;
	var count =0;
	while (p_Items1 == null && count < 500)
	{
		setTimeout(function(){},100);
		p_Items1 = window.parent.getItems();
		count++;
	}
	
	if (p_Items1 == null)
	{
		console.log("错误：无法取到监测项信息！");
		return;
	}
	
	var thtml= "";
	for (var i = 0 ; i < p_Items1.JC_PRJ_ITEM_UID.length ; i++)
	{
		if ("降水" == p_Items1.ITEM_TYPE[i])
		{
			var itemuid = p_Items1.JC_PRJ_ITEM_UID[i];
			var iconfile = p_Items1.ICON_FILE[i]+".png";
			var shortname = p_Items1.SHORT_NAME[i];
			thtml += "<td id=\""+itemuid+"\"><div onclick=\"pointClick()\">"+
			        "<input id=\"Item"+itemuid+"\" type=\"checkbox\" checked=\"checked\" name=\"JC_PRJ_ITEM_UID\"  value =\""+itemuid+"\" /> "+
			        "<img src=\""+iconfile+"\" title=\""+shortname+"\"/>&nbsp;&nbsp;&nbsp;&nbsp;</div></td>";
		}
	}

	//在 table 的 第一个tr下 添加 tr
	$("#appendTr tr td:first").after(thtml);
	$('#barchart').css("width",$('#detailed').width());
	$('#linechart').css("width",$('#detailed').width());
	
	$('#btnStp').click(function(){
		$('#btnStp').attr("data-target","jkglSet_input");
		$('#jkglSet_input').removeData("bs.modal");
		$("#jkglSet_input").empty();
		$('#jkglSet_input').modal({
			backdrop: 'static'
		});

		$.get("${ctx}/jsp/business/jkjc/parameter-setup.jsp?POINT_ITEM_UID="+$("#POINT_ITEM_UID").val()+"&TYPEID="+$("#type").val(),function(data) {
			$("#jkglSet_input").empty();
			$("#jkglSet_input").html("");
			$("#jkglSet_input").html(data);
		});
	});

}

/**获取 子页面上的 值
function  getDate(data){
var datavalue = document.getElementById('getValue').value=data;
//alert (datavalue);
}**/

//点击我 的关注
function userFocusedClick(){
	var selecteditemids = new Array();
 	var ch = document.getElementsByName('JC_PRJ_ITEM_UID');
 	for(var i=0;i<ch.length;i++){
 		if(ch[i].checked){
    		selecteditemids.push(ch[i].value);
    	}
 	}

	//console.log(document.getElementById('userFocused').checked);
    refreshModel(selecteditemids, document.getElementById('userFocused').checked);

}
//全选、全部选
function pointClickAll(){
	var selecteditemids = new Array();
 	var ch = document.getElementsByName('JC_PRJ_ITEM_UID');
 	if(document.getElementById('selectAllCK').checked){
	 	for(var i=0;i<ch.length;i++){
			selecteditemids.push(ch[i].value);	
	 	}
 	}

    refreshModel(selecteditemids, document.getElementById('userFocused').checked);
    
}

//单独选择
function pointClick(){
	var selecteditemids = new Array();
 	var ch = document.getElementsByName('JC_PRJ_ITEM_UID');
 	for(var i=0;i<ch.length;i++){
 		if(ch[i].checked){
    		selecteditemids.push(ch[i].value);
    	}
 	}

    refreshModel(selecteditemids, document.getElementById('userFocused').checked);

}

//刷新模型
function refreshModel(selecteditemids, isuserfocused){

	detailzindex = 15;
  	//父页面 调用 子页面 function
    window.frames["mainFrame"].UpdatePointIcons(selecteditemids, isuserfocused);
}

function showme(){
 if(showDIV){
 //第一次点击 向下 展开小窗
        $("#switchPicture").removeClass("fa-caret-down");
        $("#switchPicture").addClass("fa-caret-up");
	    $("#TypeForPoint").slideDown(500);
	    
	    showDIV =false;
	   }else{
	    $("#TypeForPoint").slideUp(500);
	     $("#switchPicture").removeClass("fa-caret-up");
	     $("#switchPicture").addClass("fa-caret-down");
	     
	    showDIV =true;
	   }
    } 


function showDetailed(demo, zindex){
//console.log(zindex);
	demo.style.display= 'none';
	demo.nextSibling.style.zIndex = zindex;
	demo.nextSibling.style.display= '';
	//alert();
}
function showCd(demo){
	demo.parentNode.parentNode.parentNode.parentNode.style.display= 'none';
	demo.parentNode.parentNode.parentNode.parentNode.previousSibling.style.display= '';
}
function settop(demo, zindex){
	demo.style.zIndex= zindex;
}

function showTab(pointuid,pointcode,pointitemuid,pointtype,datatype){
	$('#code').val(pointuid);
	$('#type').val(pointtype);
	$('#datatype').val(datatype);
	$('#POINT_ITEM_UID').val(pointitemuid)
	if (datatype == "测斜") {
		showCx();
		hideCj();
		linechartLoad2();
		tabSearchCx();
	} else {
		showCj();
		hideCx();
		linechartLoad();
		barchartLoad();
		tabSearch();
	}
	
	$('#detailed').show();
}

function closeModel(){
	$('#detailed').hide();
}


//沉降类折线图
function linechartLoad(){
	        // 基于准备好的dom，初始化echarts图表
        var myChart = echarts.init(document.getElementById('linechart')); 
	    

		var option = "";
			
			$.ajax({ 
				url: "${pageContext.request.contextPath }/jkjc/jcJcDataController?queryLineChart",
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

		 myChart.setOption(option); 
		
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
		
		 myChart.setOption(option); 
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
	    			html+="<th>"+tobj[txt]+"</th>";
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
	$('#cxTab').hide();
}

	//点击我的关注★
function switchPicture(pointuid)
		{
		  //console.log("点击了测点"+pointuid);
		  if($("#tp"+pointuid).attr("src").indexOf("_kong") != -1){//未关注
		     $("#tp"+pointuid).attr("src","${pageContext.request.contextPath }/assets/images/guanzhu/guanzhu.png");
		     $.ajax({
		      url:"${pageContext.request.contextPath}/jkjc/jcPrjPointsController?insertFocus",
		      data:{"PRJ_POINTS_UID":pointuid},
		      type:"post",
		      cache : false,
		      async:false,
		      dataType : "json",
		      success:function(result){
		      if(result !=null && result != ""){
				//子页面 调用 父页面 function
    			window.parent.addUser_Focus_Point(pointuid);
			      console.log("关注成功！");		
		      }
                    
		      
		      }
		  });
		  
		  }else{
		 
			  $("#tp"+pointuid).attr("src","${pageContext.request.contextPath }/assets/images/guanzhu/guanzhu_kong.png");
		      $.ajax({
		      url:"${pageContext.request.contextPath}/jkjc/jcPrjPointsController?deleteFocus",
		      data:{"PRJ_POINTS_UID":pointuid},
		      type:"post",
		      cache : false,
		      async:false,
		      dataType : "json",
		      success:function(result){
		      if(result){
				//子页面 调用 父页面 function
    			window.parent.delUser_Focus_Point(pointuid);
		        console.log("成功取消关注！");
		      }
                    
		      
		      }
		  });
		  }
		  
		   
		 
		}
	
</script>
