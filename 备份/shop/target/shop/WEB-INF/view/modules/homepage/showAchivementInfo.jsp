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
<title>柱状图业绩年</title>
<script type="text/javascript" src="${ctxStatic}/js/jquery-1.11.3.min.js"></script>
<script src="${ctxStatic}/js/highcharts.js"></script>
<style>
a { text-decoration:none; display:block}
h3 { float: right; width:40px;  height:25px; line-height:25px; text-align:center; border:#2f8fff solid 1px; background:#FFF; color:#2f8fff; cursor:pointer; font:18px "microsoft Yahei";}
.left { background:#2f8fff; color:#FFF;}
.right { margin-right:20px}
.left a { color: #FFF}
.xuan { float:left; width:100%; margin:20px 0px 20px 0px;list-style: none;}
.xuan ul { float:left; width:800px;}
.xuan li { float:left;  width:220px; height:20px; margin:0 20px 20px; cursor:pointer}
.xuan li span { background: #88A4FF; width:10px; height:10px; margin-right:10px; display: inline-block; }
</style>
</head>

<body>
 	<div class="xuan">
        <ul>
        	 <c:forEach var="bsUserShopSet" items="${bsUserShopSets}" varStatus="bsUserShopSetIndex">
        	 	<li onclick="showBsUserShop('${bsUserShopSet.majorId}')"><span></span>${bsUserShopSet.majorName}</li>
        	 </c:forEach>
        </ul>
    </div>
    <input type="hidden" id="bsUserShop" name="bsUserShop" value="${bsUserShop.majorId}">
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <h3 class="right"><a href="#" onclick="showMouth()">月</a></h3>
   	<h3 class="left"><a href="#" onclick="showYear()">年</a></h3> 
    <div id="main" style="width: 800px;height:380px; margin-top:10px;"></div>
    
<script type="text/javascript">
			function showYear(){
				var marjorId =$("#bsUserShop").val();
				$(".right a").css({"color":"#2f8fff"})
				$(".left a").css({"color":"#FFF"})  
				$(".right a").css({"background":"#FFF"})
				$(".left a").css({"background":"#2f8fff"})  
				chart = new Highcharts.Chart({
					chart: {
						renderTo: 'main',
						defaultSeriesType: 'column'
					},
					title: {
						text: '业绩情况'
					},
					subtitle: {
						text: ''
					},
					xAxis: {
						categories:['1月', '2月', '3月', '4月', '5月', '6月', 
	    	 						'7月', '8月', '9月', '10月', '11月', '12月']
					},
					yAxis: {
						min: 0,
						title: {
							text: '元'
						}
					},
					legend: {
						layout: 'vertical',
						backgroundColor: '#FFFFFF',
						align: 'right',
						verticalAlign: 'top',
						x: 0,
						y: 70,
						floating: true,
						shadow: true
					},
					tooltip: {
						formatter: function() {
							return ''+
								this.x +': '+ this.y +' 元';
						}
					},
					plotOptions: {
						column: {
							pointPadding: 0.2,
							borderWidth: 0
						}
					},
				        series: []
				});
				$.ajax({
		            type: "post",
		            dataType: "json",
		            url:rootPath+"/homePage/getAchivementInfo.do",
		            data:{marjorId:marjorId
            	    },
		            success: function (data) {
		            	 var resultdata = data.data;
		            	 if(data.iserror==true){
		            		 alert("暂无业绩");
		    	    	 }else if(data.iserror==false){ 
		    	    		 //var series = mysqlQuestionsOptions.series[0];
		    	    		 var data = [];
		    	    		 for(var i=0;i<resultdata.length;i++){
		    	    			 chart.addSeries({name: resultdata[i].name, data: resultdata[i].data });
		    	    		 }
		    	    		 //chart.series[0].setData(data);
		    	    		/*  mysqlQuestionsOptions.series[0].setData(data);
		    	    		 chart = new Highcharts.Chart(mysqlQuestionsOptions); */
		    	    	 }
		            }
		        });
			}
			
			function showBsUserShop(marjorId){
				$("#bsUserShop").val(marjorId);
				showYear();
			}
			var rootPath = '${ctx}';
			var mysqlQuestionsOptions;
			var chart;
			$(document).ready(function() {
				showYear();
			});
			function date()
			{
				 var date=new Date;
				 var month=date.getMonth()+1;
				 month =(month<10 ? ""+month:month); 
				 //var mydate = (year.toString()+month.toString());
				 //var year=date.getFullYear(); 
				 return month;
			}
			function showMouth(){
				var marjorId =$("#bsUserShop").val();
			    $(".right a").css({"color":"#FFF"})
			    $(".left a").css({"color":"#2f8fff"}) 
			    $(".left a").css({"background":"#FFF"})
			    $(".right a").css({"background":"#2f8fff"})   
				var mouth = date();
				chart = new Highcharts.Chart({
					chart: {
						renderTo: 'main',
						defaultSeriesType: 'column'
					},
					title: {
						text: '业绩情况'
					},
					subtitle: {
						text: ''
					},
					xAxis: {
						categories:[''+mouth+'月']
					},
					yAxis: {
						min: 0,
						title: {
							text: '元'
						}
					},
					legend: {
						layout: 'vertical',
						backgroundColor: '#FFFFFF',
						align: 'right',
						verticalAlign: 'top',
						x: 0,
						y: 70,
						floating: true,
						shadow: true
					},
					tooltip: {
						formatter: function() {
							return ''+
								this.x +': '+ this.y +' 元';
						}
					},
					plotOptions: {
						column: {
							pointPadding: 0.2,
							borderWidth: 0
						}
					},
				        series: []
				});
				$.ajax({
		            type: "post",
		            dataType: "json",
		            url:rootPath+"/homePage/getMounthAchivementInfo.do",
		            data:{mouth:mouth,
		            	marjorId:marjorId
            	    },
		            success: function (data) {
		            	 var resultdata = data.data;
		            	 if(data.iserror==true){
		    	    		 alert("暂无业绩");
		    	    	 }else if(data.iserror==false){ 
		    	    		 //var series = mysqlQuestionsOptions.series[0];
		    	    		 var data = [];
		    	    		 for(var i=0;i<resultdata.length;i++){
		    	    			 chart.addSeries({name: resultdata[i].name, data: resultdata[i].data });
		    	    		 }
		    	    	 }
		            }
		        });
			}
		</script>
</body>
</html>
