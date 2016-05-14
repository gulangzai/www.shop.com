<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.text.*" %>
<!DOCTYPE html>
<html>
<head>
 <link rel="icon" href="${pageContext.request.contextPath }/favicon.ico" type="image/x-icon" /> 
<link rel="shortcut icon" href="${pageContext.request.contextPath }/favicon.ico" type="image/x-icon" /> 
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/sys_resources/plugins/echarts/echarts-all.js"></script>
<script type="text/javascript">
  /**获取 到农历的时间*/
   $("#nongliDate").html($("#getNlDate").html());
  
 /**时间显示格式的 处理**/
   var d = new Date();
   var getYear = d.getFullYear();
   var getMonth = d.getMonth()+1;
   var getDay = d.getDate();
   var month ="";
   var day = "";
   if(getMonth <10){
	  month ="0"+getMonth;
	   
   }else{
	   month = getMonth; 
	   
   }
   if(getDay < 10){
	  day = "0"+getDay; 
	   
   }else{
	  day = getDay;
   }
   $("#getDayOfY").text(day);
   $("#getDate").text(getYear+"年"+month+"月");
   $(".ajax-loading-overlay").remove();
  

</script>
<style>
/**页面 布局**/
.page-content { background-color: #fff; position: relative; margin: 0;padding:3px 0px 0px 3px;}
 #imgUpload{
width:200px;height:200px;margin:0px 0px 1px 0px ;
background-image:url(${pageContext.request.contextPath}/assets/images/logo/logo_01_03.png);background-position:center; background-repeat:no-repeat;
}
.col-xs-8, .col-sm-8{
padding-right:0px;
}
.widget-box {
    margin:0px;; 
    padding: 0;
    border: 1px solid #e0dede;
    -webkit-box-shadow: none;
    box-shadow: none;
}
</style>
</head>
<body>
<div class="main-container" id="main-container">
   <div class="page-content" id="inside"> 
	 <div class="widget-box col-xs-12">
		    <div class="widget-box col-xs-4 col-sm-4" style="width:35%;">
		       <div class="" >
		          <div id="mainCare" style="height:200px;"></div>
		       </div>
			</div>
			 <div class="widget-box col-xs-4 col-sm-4">
			    <div  class="">   
                  <div id="warning" style="height:200px;"></div> 
			    </div>
			 </div>
		  <div class=" col-xs-4 col-sm-4 " style="padding-right:0px;width:31%">
		    <div class="widget-box" style="height:200px;text-align:center;">
		         <h4 style="color:#353736;"><span id="getDate"></span></h4>
		         <h2 style="color:#383C3E;font-weight:bold;"><span id="getDayOfY"></span></h2>
		         <h4 style="color:#353736;"><span id="nongliDate"></span></h4><br/>
		         <h4 style="color:#353736;">共有<span id="allWaringPoint"></span>个测点报警</h4>
		    </div>
		  </div>
	</div>
	  <div class="widget-box col-xs-12" style="height:350px" >
		<div class="col-xs-4" style="height:300px;" id="Allwarning1"></div>
		<div class="col-xs-4" style="height:300px" id="Allwarning2"></div>
		<div class="col-xs-4" style="height:300px" id="Allwarning3"></div>
	  </div>
	  <div  class="widget-box col-xs-12" style="height:350px" id="appendPIE">
	    <div class="col-xs-4" style="height:300px" id="Allwarning4"></div>
		<div class="col-xs-4" style="height:300px" id="Allwarning5"></div>
	  </div>
   </div>
</div>
<script type="text/javascript">
var controlname="${pageContext.request.contextPath}/BuProjectController";
var scripts = [null];
ace.load_ajax_scripts(scripts, function() {
	
	var xmUid = $("#project_uid").val();
	//initPieData(xmUid);
	//initPieWarning(xmUid);
	//initPieAllWarning(xmUid);
	
	
	
});
function initPieData(xmUid){
	 var myChart = echarts.init(document.getElementById('mainCare')); 
	 var option = "";
			$.ajax({ 
				url: "${pageContext.request.contextPath }/project/buProjectController?queryMyCare",
				data:{'PROJECT_UID':xmUid},                                                                 
				async : false,
				type : 'post',
				dataType: 'json',
				success: function(data){
			    	var obj = eval('(' + data.obj+ ')');
					option = obj.option;

				}
			});
		 myChart.setOption( option); 
		
	
}

function initPieWarning(xmUid){
	 var myChartWarning = echarts.init(document.getElementById('warning')); 
	 var option = "";
			$.ajax({ 
				url: "${pageContext.request.contextPath }/project/buProjectController?queryWarning",
				data:{'PROJECT_UID':xmUid},                                                                 
				async : false,
				type : 'post',
				dataType: 'json',
				success: function(data){
			    	var obj = eval('(' + data.obj+ ')');
					option = obj.option;

				}
			});
			
	    myChartWarning.setOption( option); 
      	
}

//初始化 5个饼图 数据
function initPieAllWarning(xmUid){
	     var myChartWarning1= echarts.init(document.getElementById('Allwarning1')); 
	     var myChartWarning2 = echarts.init(document.getElementById('Allwarning2')); 
	     var myChartWarning3 = echarts.init(document.getElementById('Allwarning3')); 
	     var myChartWarning4 = echarts.init(document.getElementById('Allwarning4')); 
	     var myChartWarning5 = echarts.init(document.getElementById('Allwarning5')); 
	     var option1 = "";
	     var option2 = "";
	     var option3 = "";
	     var option4 = "";
	     var option5 = "";
			$.ajax({ 
				url: "${pageContext.request.contextPath }/project/buProjectController?queryAllPoint",
				data:{'PROJECT_UID':xmUid},                                                                 
				async : false,
				type : 'post',
				dataType: 'json',
				success: function(data){
			    	var obj = eval('(' + data.obj+ ')');
			    	//alert(data.obj)
					option1 = obj.option1;
			    	option2 = obj.option2;
			    	option3 = obj.option3;
			    	option4 = obj.option4;
			    	option5 = obj.option5;
					$("#allWaringPoint").html(obj.sumTotal);
				

				}
			});
		
		    myChartWarning1.setOption(option1); 
		    myChartWarning2.setOption(option2); 
		    myChartWarning3.setOption(option3); 
		    myChartWarning4.setOption(option4); 
		    myChartWarning5.setOption(option5); 
			
}
</script>
</body>
</html>

