<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/commons/taglib.jsp" %>
<%@ include file="/WEB-INF/view/commons/jsp/main.jspf"%> 
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>柱状图业绩年</title>
<script type="text/javascript" src="${ctxStatic}/js/myStudent/jquery-1.11.3.min.js"></script>
<script src="${ctxStatic}/js/myStudent/echarts.min.js"></script> 
<style> 
</style>
</head>

<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->

    <div id="main" style="width: 900px;height:400px;"></div>
    <script type="text/javascript"> 
            // 基于准备好的dom，初始化echarts实例
		    var myChart = echarts.init(document.getElementById('main')); 
		    // 指定图表的配置项和数据
		    var option = {
		    tooltip : {
		        trigger: 'axis'
		    },
		    legend: {
		        data:['月月考','真题考','模拟考']
		    },
		    grid: {
		        left: '3%',
		        right: '4%',
		        bottom: '3%',
		        containLabel: true
		    },
		    toolbox: {
		        feature: {
		            saveAsImage: {}
		        }
		    },
		    xAxis : [
		        {
		            type : 'category',
		            boundaryGap : false,
		            data : ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月']
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value',
		            name : '分', 
		        },
		    ],
		    series : [
		        {
		            name:'月月考',
		            type:'line',
		            stack: '总量',
		            data:[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
		        },
		        {
		            name:'真题考',
		            type:'line',
		            stack: '总量',
		            data:[0,0,0,0,0,0,0,0,0,0,0,0]
		        },
		        {
		            name:'模拟考',
		            type:'line',
		            stack: '总量',
		            data:[0,0,0,0,0,0,0,0,0,0,0,0]
		        },
		      
		    ]
         }; 
		    myChart.setOption(option);
		    
		 $(".studentGrade li",window.parent.document).click(function(){ 
			 var majorId = $(this).children('input[name="majorId"]').val(); 
			 var subjectId = $(this).children('input[name="subjectId"]').val();
			  $.ajax({
		            type: "post",
		            dataType: "json",
		            async:false,
		            url:ctx+"/myStudent/getStudentExamInfo.do",
		            data:{majorId:majorId, //majorId,
		            	subjectId:subjectId, //subjectId,3940Qnt2KoUQY7S3XBI1
		                studentId:'${studentId}'
	        	    },
		            success: function (data) {
		            	 //var newData = eval('(' + data + ')');
		            	 var resultdata = data.data;
		            	 if(data.iserror==true){
		    	    		 console.info('获取考试成绩'+data.message);
		    	    	 }else if(data.iserror==false){ 
		    	    		 // chart.xAxis.categories = [item];
		    	    		 //chart.series;
		    	    		 if(resultdata.length>0){
		    	    			 if(resultdata.length>0){
		    	    				 option.series[0].data = resultdata[0].data; 
		    	    			 }else{
		    	    				 option.series[0].data = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
		    	    			 }
		    	    			 if(resultdata.length>1){
		    	    				 option.series[1].data = resultdata[1].data;
		    	    			 }else{
		    	    				 option.series[1].data = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
		    	    			 }
		    	    			 if(resultdata.length>2){
		    	    				 option.series[2].data = resultdata[2].data;
		    	    			 }else{
		    	    				 option.series[2].data = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]; 
		    	    			 }
			    	    		  //chart = new Highcharts.Chart(option);
		    	    		 }else{
		    	    			 option.series[0].data = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
		    	    			 option.series[1].data = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
		    	    			 option.series[2].data = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]; 
			    	    		 // chart = new Highcharts.Chart(mysqlQuestionsOptions);
		    	    		 }
		    	    		 //chart.series = [userdata]; 
		    	    	 }
		            }
		        }); 
	        // 使用刚指定的配置项和数据显示图表。
	        //console.info(option);
	        myChart.setOption(option); 
		 });
		
    </script>
</body>
</html>