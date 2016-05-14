<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/commons/taglib.jsp" %>
<%@ include file="/WEB-INF/view/commons/jsp/main.jspf"%> 
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>饼图数据</title>
<script type="text/javascript" src="${ctxStatic}/js/myStudent/jquery-1.11.3.min.js"></script>
<script src="${ctxStatic}/js/myStudent/echarts.min.js"></script>
</head> 
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="main" style="width:340px;height:300px; "></div>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));
        var unfinished ='${pie}';
        //alert(unfinished);
        // alert('unfinished'+unfinished);
		var unfinisheds;
		if(typeof unfinished =='undefined'){
			 unfinisheds = 1;
		} 
		if(isNaN(unfinished)||!isFinite(unfinished)||unfinished==''){
			 unfinisheds = 1;
		}else{
			 unfinisheds = parseFloat(unfinished);
		}
		// alert(unfinisheds); 
        // 指定图表的配置项和数据
        var option = {
    title : {
     
    },
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    legend: {
        orient: 'vertical',
        bottom: 'bottom',
        data: ['完成','未完成']
    },
    series : [
        {
            name: '名称',
            type: 'pie',
            radius : '55%',
            center: ['50%', '40%'], 
            data:[
                {value: 100-unfinisheds*100, name:'完成'},
                {value: unfinisheds*100, name:'未完成'},

            ],
            itemStyle: {
                emphasis: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }
    ]
};


        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    </script>
</body>
</html>
