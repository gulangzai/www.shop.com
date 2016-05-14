<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/commons/taglib.jsp" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/base.css">
<title>我的学习</title>
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/my.css">
<script type="text/javascript" src="${ctxStatic}/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/highcharts.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/modules/exporting.js"></script>
<script type="text/javascript" src="${ctxStatic}/js/daytime.js"></script>
<style type="text/css">
          .black_overlay{
             display: none; 
             position: absolute;  
             top: 0%;  
             left: 0%;  
             width: 100%;  
             height: 100%;  
             z-index:1001;  
             -moz-opacity: 0.8;  
             opacity:.80;  
             filter: alpha(opacity=80);  
        } 
        .white_content {
            display: none;  
            position: absolute; 
            top: 42%; 
            left: 50%; 
            width: 20%; 
            height: 2%; 
            padding: 20px 0 16px 20px;
            border: 1px solid #ccc; 
            background-color:#FBFBFB; 
			border-radius: 4px;
            z-index:1002;
            overflow: auto;
        }  

    </style>
    <script language="javascript">
   	    var rootPath = '${ctx}';
        function openWindow(isDayTime){
            //document.getElementById('light').style.display='block';
            $("#light").append('<a href="javascript::" onClick="closeWindow()" style="float:right;margin-right: 7%;margin-top: 1%;"> 关闭</a><input name="updateTime" type="text" id="updateTime" style="border-radius: 4px;border: 1px solid #ccc;height: 25px;line-height: 25px;"><input onclick="updateStudentPlan('+isDayTime+')" name="提交" type="button" style="margin-left: 4%;width: 15%;line-height: 25px;border: 1px solid #33BDFD;color: #fff;border-radius: 2px;background: #33BDFD" value="提交">');
            $("#light").show();
            $("#fade").show();
        }
        function updateStudentPlan(isDayTime){
        	var planId = $("#planId").val();
        	var videoTotal= $("#videoTotal").val();
        	if(isDayTime==1){
        		var dayTime=$("#updateTime").val();
        		if(isNaN(dayTime)){
        			alert("不是数字,请重新输入");
        			return;
        		}
        		if(dayTime<0||dayTime>24){
        			alert("请输入正确的小时数,请重新输入");
        			return;
        		}
        		$.ajax({type:"post",
            	    url:rootPath+"/stuPersonalCenter/updateStudentPlan.do",
            	    dataType: "json",
            	    data:{dayTime:dayTime,
            	    	planId:planId,
            	    	videoTotal:videoTotal
            	    },
            	    success:function(data){
            	    	 if(data.iserror==true){
            	    		 alert(data.message);
            	    	 }else if(data.iserror==false){
            	    		 $("#planDayTime").html(data.data.studentPlan.dayTime+"");
            	    		 $("#learningTimeRate").html("达标率:"+data.data.studentPlan.learningTimeRate+"%");
            	    		 $("#updateTime").val("");
            	    		 collectUpdate(data.data.studentPlanLogs);
            	    		 /* $.ajax({type:"post",
            	            	    url:rootPath+"/stuPersonalCenter/selLearnLogs.do",
            	            	    dataType: "json",
            	            	    data:{dayTime:dayTime,
            	            	    	planId:planId,
            	            	    	videoTotal:videoTotal
            	            	    },
            	            	    success:function(data){
            	            	    	 if(data.iserror==true){
            	            	    		 alert(data.message);
            	            	    	 }else if(data.iserror==false){
            	            	    		 collectUpdate(data);
            	            	    	 }
            	            	    }
            	             });  */
            	    		 //closeWindow();
            	    	 }
            	    }
           		 }); 
        	}else{
        		var totalDay=$("#updateTime1").val();
        		if(isNaN(totalDay)){
        			alert("不是数字,请重新输入");
        			return;
        		}
        		$.ajax({type:"post",
            	    url:rootPath+"/stuPersonalCenter/updateStudentPlan.do",
            	    dataType: "json",
            	    data:{totalDay:totalDay,
            	    	planId:planId,
            	    	videoTotal:videoTotal
            	    },
            	    success:function(data){
            	    	 if(data.iserror==true){
            	    		 alert(data.message);
            	    	 }else if(data.iserror==false){
            	    		 $("#totalDayTime").html(data.data.studentPlan.totalDay+"");
            	    		 $("#classHour").html(parseInt(data.data.studentPlan.videoTotal/data.data.studentPlan.totalVideoTime));
            	    		 $("#endDate").html(data.data.studentPlan.endDate);
            	    		 $("#learningTimeRate").html("达标率:"+data.data.studentPlan.learningTimeRate+"%");
            	    		 $("#residueLearnDay").html("剩余天数:"+data.data.studentPlan.stageNumber+"天");
            	    		 $("#updateTime1").val("");
            	    		 collectUpdate(data.data.studentPlanLogs);
            	    		 /* $.ajax({type:"post",
	         	            	    url:rootPath+"/stuPersonalCenter/selLearnLogs.do",
	         	            	    dataType: "json",
	         	            	    data:{
	         	            	    	videoTotal:videoTotal
	         	            	    },
	         	            	    success:function(data){
	         	            	    	 if(data.iserror==true){
	         	            	    		 alert(data.message);
	         	            	    	 }else if(data.iserror==false){
	         	            	    		 collectUpdate(data);
	         	            	    	 }
	         	            	    }
	         	             });  */
            	    		 //closeWindow();
            	    		 //window.location.href = rootPath+"/stuPersonalCenter/enterLearning.do?planId="+data.data;
            	    	 }
            	    }
           		 }); 
        	}
        }
        
        
		function collectUpdate(data){
			$("#pi_3").empty();
			for(var i = 0 ;i <data.length; i++){
				//var nDate = new Date(data[i].createTime);
				$("#pi_3").append("<p>"+format(data[i].createTime,"yyyy-MM-dd")+data[i].actionName+"</p>");
			}
		}
		var format = function(time, format){
		    var t = new Date(time);
		    var tf = function(i){return (i < 10 ? '0' : '') + i};
		    return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function(a){
		        switch(a){
		            case 'yyyy':
		                return tf(t.getFullYear());
		                break;
		            case 'MM':
		                return tf(t.getMonth() + 1);
		                break;
		            case 'mm':
		                return tf(t.getMinutes());
		                break;
		            case 'dd':
		                return tf(t.getDate());
		                break;
		            case 'HH':
		                return tf(t.getHours());
		                break;
		            case 'ss':
		                return tf(t.getSeconds());
		                break;
		        }
		    })
		}
        
        function closeWindow(){
        	$("#light").hide();
        	$("#fade").hide();
        	$("#light").empty();
        }
        var mysqlQuestionsOptions;
        var chart;
		$(function () {
			var majorId= '${courseMajor.majorId}';
			var subjectId = '${courseMajor.subjectId}';
			var userdata; 
			var unfinished ='${studentPlanLearn.pie}'
			var unfinisheds;
			if(isNaN(unfinished)||!isFinite(unfinished)){
				 unfinisheds = 0;
			}else{
				 unfinisheds = parseFloat(unfinished);
			}
			$(document).ready(function () {
				pieChart = new Highcharts.Chart({
					chart: {
						renderTo: 'pieContainer'
					},
					title: {
						text: ''
					},
					plotArea: {
						shadow: null,
						borderWidth: null,
						backgroundColor: null
					},
					tooltip: {
						formatter: function() {
							return '<b>'+ this.point.name +'</b>: '+ this.y +' %';
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
						data: [
							['未完成',   100-unfinisheds*100],
							['完成',      unfinisheds*100],
						]
					}]
				});
				
				
				  mysqlQuestionsOptions = {
		        	chart: {
	 					renderTo: 'container',
	 					defaultSeriesType: 'line',
	 					marginRight: 130,
	 					marginBottom: 25
	 				},
	 				title: {
	 					text: '考试变化',
	 					x: -20 //center
	 				},
	 				exporting: {
	 		            enabled:false
	 				},
		            xAxis: {
		            	categories: ['1月', '2月', '3月', '4月', '5月', '6月', 
		    	 						'7月', '8月', '9月', '10月', '11月', '12月']/* categories: item */
		                //['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
		            },
		            yAxis : {
	                    min : 0,
	                    title : {
	                        text : '成绩 (分)'
	                    }
	               },
		            tooltip : {
	                    //当鼠标悬置数据点时的格式化提示
						formatter : function() {
							return '' +  this.series.name +''+this.x + '月份: ' + this.y + '分';
						}
					},
					 plotOptions : {
		                    column : {
		                        dataLabels : {
		                            enabled : true
		                        },
		                        pointPadding : 0.2,
		                        borderWidth : 0
		                    }
		            },
		            legend: {
						layout: 'vertical',
						align: 'right',
						verticalAlign: 'top',
						x: -10,
						y: 100,
						borderWidth: 0
					},
		            series: [{
						name: '月月考',
						data: []
					}, {
						name: '模拟考试',
						data: []
					}, {
						name: '真题考试',
						data: []
					}]
		        }
					$.ajax({
			            type: "post",
			            dataType: "json",
			            url:ctx+"/myStudent/getStudentExamInfo.do",
			            data:{majorId:'8Z5FqMVTQ4QZtJMQfbtp', //majorId,
			            	subjectId:'iV8huFgfA1nk7tq06qud', //subjectId,3940Qnt2KoUQY7S3XBI1
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
			    	    				 mysqlQuestionsOptions.series[0].data = resultdata[0].data;
			    	    			 }else{
			    	    				 mysqlQuestionsOptions.series[0].data = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
			    	    			 }
			    	    			 if(resultdata.length>1){
			    	    				 mysqlQuestionsOptions.series[1].data = resultdata[1].data;
			    	    			 }else{
			    	    				 mysqlQuestionsOptions.series[1].data = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
			    	    			 }
			    	    			 if(resultdata.length>2){
			    	    				 mysqlQuestionsOptions.series[2].data = resultdata[2].data;
			    	    			 }else{
			    	    				 mysqlQuestionsOptions.series[2].data = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]; 
			    	    			 }
				    	    		  chart = new Highcharts.Chart(mysqlQuestionsOptions);
			    	    		 }else{
			    	    			 mysqlQuestionsOptions.series[0].data = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
				    	    		 mysqlQuestionsOptions.series[1].data = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
				    	    		 mysqlQuestionsOptions.series[2].data = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]; 
				    	    		  chart = new Highcharts.Chart(mysqlQuestionsOptions);
			    	    		 }
			    	    		 //chart.series = [userdata]; 
			    	    	 }
			            }
			        }); 
			});
	    });
		function gotoStagePage(index){
			var planId= $("#planId").val();
			if(index==6||index==7){
				alert("还未开放");
				return;
			}/* else if(index==1){
				window.location.href = rootPath+"/studentLearn/toBaseStudyPlanStagePage.do?planId="+planId+"&phaseId="+index;
			} */else{
				window.location.href = rootPath+"/studentLearn/toStudyPlanStagePage.do?planId="+planId+"&phaseId="+index;
			}
			
		}
		function popupMsgBox(){
			alert("该阶段没有购买");
			return;
		}
		
		
		function changeSubject(majorId,subjectId){
			$.ajax({
	            type: "post",
	            dataType: "json",
	            url:rootPath+"/stuPersonalCenter/getStudentExamInfo.do",
	            data:{majorId:majorId,
	            	subjectId:subjectId,
	    	    },
	            success: function (data) {
	            	 //var newData = eval('(' + data + ')');
	            	 var resultdata = data.data;
	            	 if(data.iserror==true){
	    	    		 alert(data.message);
	    	    	 }else if(data.iserror==false){ 
	    	    		// chart.xAxis.categories = [item];
	    	    		 //chart.series;
	    	    		 if(resultdata.length>0){
	    	    			 if(resultdata.length>0){
	    	    				 mysqlQuestionsOptions.series[0].data = resultdata[0].data;
	    	    			 }else{
	    	    				 mysqlQuestionsOptions.series[0].data = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
	    	    			 }
	    	    			 if(resultdata.length>1){
	    	    				 mysqlQuestionsOptions.series[1].data = resultdata[1].data;
	    	    			 }else{
	    	    				 mysqlQuestionsOptions.series[1].data = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
	    	    			 }
	    	    			 if(resultdata.length>2){
	    	    				 mysqlQuestionsOptions.series[2].data = resultdata[2].data;
	    	    			 }else{
	    	    				 mysqlQuestionsOptions.series[2].data = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]; 
	    	    			 }
		    	    		 
		    	    		  chart = new Highcharts.Chart(mysqlQuestionsOptions);
	    	    		 }else{
	    	    			 mysqlQuestionsOptions.series[0].data = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
		    	    		 mysqlQuestionsOptions.series[1].data = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
		    	    		 mysqlQuestionsOptions.series[2].data = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]; 
		    	    		  chart = new Highcharts.Chart(mysqlQuestionsOptions);
	    	    		 }
	    	    		  //chart.series = [userdata]; 
	    	    	 }
	            }
	        });
		}
    </script>
	
<!--[if IE 6]>
        <script type="text/javascript" src="js/DD_belatedPNG.js"></script>
        <script>
          DD_belatedPNG.fix('.png_bg');
        </script>
    <![endif]-->
</head>

<body style="display:block;overflow-y:auto;overflow-x:hidden;">
<div class="page" style="position:relative;">
<div class="xuexi"><div class="xian"></div> </div> 

<div id="con"> 
<div class="right"> 
<h4 style="color:#3399ff;">学员的学习情况：</h4>
<c:choose>
<c:when  test="${studentPlan==null}">
    <div>该学员没有任何学习计划</div>
</c:when>
<c:otherwise>
      <div class="rigth_top">
<div id="pieContainer" style=" width:260px; height:260px; overflow:hidden; float:left;"></div>

<div class="right_one_right">
<ul class="right_content">
<h4 style="margin-bottom:10px;">学习时长：</h4>
<li class="content_one">目标学习时长：${studentPlanLearn.totalLearnTime}小时</li> 
<li class="content_one">总学习时长：${fn:substring(studentPlanLearn.oneLearnTime,0,4)+fn:substring(studentPlanLearn.twoLearnTime,0,4)}小时</li>
<li class="content_one">一轮学习时长：${studentPlanLearn.oneLearnTime}小时</li>
<li class="content_one">二轮及以上学习时长: ${studentPlanLearn.twoLearnTime}小时</li>
<li class="content_one">
	<%-- <c:choose>
	 	<c:when test="${studentPlanLearn.twoLearnTime>0&&studentPlanLearn.oneLearnTime>0}">
	 		复习率：${fn:substring(studentPlanLearn.twoLearnTime/studentPlanLearn.oneLearnTime*100,0,4)}%
	 	</c:when>
	 	<c:otherwise>
	 		复习率：0%
	 	</c:otherwise>
 	</c:choose> --%>
 	复习率：${studentPlanLearn.reviewRate}%
</li>
</ul>

<ul class="right_contentone">
<h4 style="margin-bottom:10px;">做题练习：</h4>
<li class="content_one">总答题量：${studentPlanLearn.alwaysATopicQuantity}道</li>

<li class="content_one">
	<c:choose>
	 	<c:when test="${studentPlanLearn.correct>0&&studentPlanLearn.alwaysATopicQuantity>0}">
	 		正确率：${fn:substring(studentPlanLearn.correct/studentPlanLearn.alwaysATopicQuantity*100,0,4)}%
	 	</c:when>
	 	<c:otherwise>
	 		正确率：0%
	 	</c:otherwise>
 	</c:choose>
</li>

<li class="content_one">
			知识点覆盖率：${fn:substring(studentPlanLearn.coverage,0,4)}%
	 <%-- <c:choose>
		 <c:when test="${studentPlanLearn.correct>0&&studentPlanLearn.knowledgePointCoverage>0}">
		 	知识点覆盖率：${fn:substring(studentPlanLearn.correct/studentPlanLearn.knowledgePointCoverage*100,0,4)}%
		 </c:when>
		 <c:otherwise>
			 知识点覆盖率：0%
		 </c:otherwise>
 	</c:choose>  --%>
</li>
<li class="content_one">二次以上练习率：${studentPlanLearn.twoPracticeRate}%</li>
</ul>
<div>
</div>
</div> 
</div>

 <div class="right_one" style="border-top:1px dashed #ccc;">
<h4 style="color:#3399ff; margin-top:20px;">我的【必胜】学习计划：</h4>
<div class="p_2">
 <p>每天学习时长：
 <span class="dayTime" id="dayTime"><span id="planDayTime">${studentPlan.dayTime}</span>小时<img src="${ctxStatic}/images/studentDetail/ico1.png"/></span>
 <span class="submit" id="submit" style="display:none;">
 <input type="text" value="" id="updateTime"/>
 <input type="button" value="提交" id="buttonone" onclick="updateStudentPlan('1')">
 </span>
 
<span class="ziti_3" id="learningTimeRate">  达标率：${fn:substring(learningTimeRate,0,4)} % </span><br>
计划学习天数：
<span class="dayTime" id="totalDay"><span id="totalDayTime">${studentPlan.totalDay }</span>天<img src="${ctxStatic}/images/studentDetail/ico1.png"/></span>
<span class="submit" id="submit1" style="display:none;">
 <input type="text" value="" id="updateTime1"/>
 <input type="button" value="提交" id="buttons" onclick="updateStudentPlan('2')">
</span>

<span class="ziti_1" id="residueLearnDay">剩余学习天数：${residueLearnDay}天 </span><br>
预计每天学习课时数：<span id="classHour"><fmt:formatNumber type="number" value="${studentPlan.videoTotal/studentPlan.totalVideoTime }" maxFractionDigits="0"/></span>个<span class="ziti_2"><!-- 达标率：51% --></span><br>
开始学习时间：${studentPlan.startDate}<br>
预计完成学习时间：<span id="endDate">${studentPlan.endDate}</span><br> 
<br>
<span class="ziti_4"><!-- 预计考试时间：10月13号(暂无) --></span><br> 
</p></div>

<div class="p_3" id = "p_3">
<h4>修改日志：</h4>
	<div class= "pi_3" id ="pi_3">
		<c:forEach var="studentPlanLog" items="${studentPlanLogs}">
			<p><spring:eval expression="studentPlanLog.createTime"></spring:eval>${studentPlanLog.actionName }</p>
		</c:forEach>
	</div>
</div>
</div> 
<div class="right_three">
<div class="class_contents">
 	<c:forEach var="subject" items="${courseMajors}" >
		<a href="javascript::;" onclick="changeSubject('${subject.majorId}','${subject.subjectId}')" class="class_botton">${subject.subjectName}</a>
	</c:forEach>
</div>
<h4 style="color:#3399ff;">我的考试变化：</h4>
<div id="container" style="width: 800px; height: 400px; margin: 0 auto"></div>
</div>
</c:otherwise>
</c:choose>

</div>   
</div>  
</div>
</body>
</html>