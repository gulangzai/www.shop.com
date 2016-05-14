<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>
<title><fmt:message key="ui.title" /></title>
<body>

		<div id="main" style="height:400px;"></div>
		<script src="${pageContext.request.contextPath }/assets/sys_resources/plugins/echarts/echarts-all.js"></script>
		<script type="text/javascript">
        // 基于准备好的dom，初始化echarts图表
        var myChart = echarts.init(document.getElementById('main')); 
		var option = "";
			
			$.ajax({ 
				url: "${pageContext.request.contextPath }/jkjc/jcJcDataController?queryLineChart", 
				async : false,
				type : 'get',
				dataType: 'json',
				success: function(data){
			    	
			    	option = data.obj;
			    	
				}
			});
		
				//console.log(option);
				
				//console.info(option);
				
		 //option = {title:{text:'未来一周气温变化',subtext:'纯属虚构'},tooltip:{trigger:'axis'},legend:{data:['最高气温','最低气温']},toolbox:{show:true,feature:{mark:{show:true},dataView:{show:true,readOnly:false},magicType:{show:true,type:['line','bar']},restore:{show:true},saveAsImage:{show:true}}},calculable:true,xAxis:[{type:'category',boundaryGap:false,data:['周一','周二','周三','周四','周五','周六','周日']}],yAxis:[{type:'value',axisLabel:{formatter:'{value} °C'}}],series:[{name:'最高气温',type:'line',data:[11,11,15,13,12,13,10],markPoint:{data:[{type:'max',name:'最大值'},{type:'min',name:'最小值'}]},markLine:{data:[{type:'average',name:'平均值'}]}},{name:'最低气温',type:'line',data:[1,-2,2,5,3,2,0],markPoint:{data:[{name:'周最低',value:-2,xAxis:1,yAxis:-1.5}]},markLine:{data:[{type:'average',name:'平均值'}]}}]};
         console.info(eval('(' + option + ')'));
		 myChart.setOption(eval('(' + option + ')')); 
        $('.ajax-loading-overlay').hide();
    </script>
</body>
</html>