<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/commons/taglib.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<script type="text/javascript" src="${ctxStatic}/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/highcharts.js"></script>
<title>饼图数据</title>
</head>

<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="main" style="width: 800px;height:400px; margin-top:10px;"></div>
    <script type="text/javascript">
    		var rootPath = '${ctx}';
			var data = [];
			var pieChart;
			function showChart(){
				pieChart = new Highcharts.Chart({
					chart: {
						renderTo: 'main'
					},
				    load: function() {
		                  // set up the updating of the chart each second
		                  var series = this.series[0];
		                  setInterval(function() {
		                      series.setData(data);
		                  }, 2000);
					},
					title: {
						text: '开课情况',
					},
					plotArea: {
						shadow: null,
						borderWidth: null,
						backgroundColor: null
					},
					tooltip: {
						formatter: function() {
							return '<b>'+ this.point.name +'</b>: '+ this.y +' /人';
						}
					},
					exporting: {
			            enabled:false
					},
					plotOptions: {
						pie: {
							allowPointSelect: true,
							cursor: 'pointer',
							dataLabels: {
								enabled: false
							},
							showInLegend: true
						}
					},
				    series: [{
						type: 'pie',
						name: 'Browser share',
					}]
					
				});
				$.ajax({
		            type: "post",
		            dataType: "json",
		            url:rootPath+"/homePage/getMajorCount.do",
		            data:{
	        	    },
		            success: function (data) {
		            	 var resultdata = data.data;
		            	 if(data.iserror==true){
		    	    		 alert("暂无开课情况");
		    	    	 }else if(data.iserror==false){ 
		    	    		 if(resultdata.length>0){
		    	    			  var data = [];
		    	    			  for(var i=0;i<resultdata.length;i++){
		    	    				  data.push([resultdata[i].name,resultdata[i].data]);
		    	    			  }
		    	    			  pieChart.series[0].setData(data);
		    	    		 }
		    	    		 
		    	    		 //showChart(xset,yset); 
		    	    	 }
		            }
		        }); 
			}
			$(document).ready(function() {
				showChart();
			});
				
		</script>
</body>
</html>
