<!DOCTYPE html>
<html>
<head>
<title>长春市政府投资建设项目管理中心——综合管控中心</title>
<%@ page language="java" pageEncoding="UTF-8"%>


<script type="text/javascript" src="${pageContext.request.contextPath }/jsp/char/charts/FusionCharts.js"></script>
<%@ page import="com.ccthanking.framework.common.DBUtil"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.util.*"%>
<%@ page import="java.lang.StringBuffer"%>
<%@ page import="com.ccthanking.framework.Globals"%>
<%@ page import="com.ccthanking.framework.common.*"%>
<%@ page import="com.ccthanking.framework.util.*"%>
<%@ page import="com.ccthanking.framework.plugin.AppInit"%>
<%@ page import="java.math.*"%>

<%@ taglib uri= "/tld/base.tld" prefix="app" %>
<app:base/>
<%

Connection conn = DBUtil.getConnection();

String getsql = "";
StringBuffer sbSql = null;//sql语句字符串
String sql = "";//查询参数字符串
String xmglgsIds = "";//项目管理公司ID

%>
<style type="text/css">
.table th,
.table td {
  padding: 6px;
  line-height: 20px;
  text-align: left;
  vertical-align: top;
  border-top: 1px solid #dddddd;
}
</style>
<script type="text/javascript" charset="utf-8">
var countXmzs = "0";//记录项目总数
var countWc = "0";//记录项目完成总数
var countWbz = "0";//记录项目为编制总数
var controllername= "${pageContext.request.contextPath }/ShowChart/ShowChartController.do"; 
var controllername_query = "${pageContext.request.contextPath }/jhbmjk/jhbmjkController.do";
//初始化页面
$(function() {
	generateNd($("#ND"));
	setDefaultOption($("#ND"),new Date().getFullYear());
	init();
	setDatas();
	//setTime();
	//queryAll();//监控日期
	//switchNd();//切换年度
	 //监听年度变化
    $("#ND").change(function() {
    	init();
    	setDatas();
    });
});
function setDatas(){
	setXmzxqk();
	Xmglgsxms();
	Ydzxqk();
	sxqk();
	sjqk1();
	sjqk2();
	sjqk3();
	zcqk();
	pqqk();
	kgqk();
	wgqk();
}
//项目执行情况(饼图)
function setXmzxqk(){
	var action1 = "${pageContext.request.contextPath }/XmzxqkServlet?&nd="+$("#ND").val();
	$.ajax({
		url : action1,
		success: function(xml){
			var myChart =  new FusionCharts("${pageContext.request.contextPath }/jsp/char/charts/Pie2D.swf", "myChartId", "370", "250");  
			myChart.setDataXML(xml);  
			myChart.render("chartdiv");  
		},
	    error : function(xml) {
			var myChart =  new FusionCharts("${pageContext.request.contextPath }/jsp/char/charts/Pie2D.swf", "myChartId", "370", "250");
			myChart.setDataXML("");  
			myChart.render("chartdiv");  
	    }
	});
}

function Xmglgsxms(){
	var action1 = "${pageContext.request.contextPath }/XmglgsxmslServlet?&nd="+$("#ND").val();
	$.ajax({
		url : action1,
		success: function(xml){
			var myChart =  new FusionCharts("${pageContext.request.contextPath }/jsp/char/charts/Column2D.swf", "myChartIdxmsl1", "200", "400"); 
			myChart.setDataXML(xml);  
			myChart.render("chartdivxmsl");  
		},
	    error : function(xml) {
			var myChart =  new FusionCharts("${pageContext.request.contextPath }/jsp/char/charts/Column2D.swf", "myChartIdxmsl1", "200", "400");
			myChart.setDataXML("");  
			myChart.render("chartdivxmsl");  
		}
	});
}


function Ydzxqk(){
	var action1 = "${pageContext.request.contextPath }/YdzxqkServlet?&nd="+$("#ND").val();
	$.ajax({
		url : action1,
		success: function(xml){
			var myChart =  new FusionCharts("${pageContext.request.contextPath }/jsp/char/charts/MSLine.swf", "myChartId6", "1100", "400");  
			myChart.setDataXML(xml);
			myChart.render("ydzxqktj");
		}
	});
}

//手续
function sxqk(){
	var action1 = controllername + "?single_chartData&zxqk=sxqk&nd="+$("#ND").val();
	$.ajax({
		url : action1,
		success: function(xml){
			var myChart =  new FusionCharts("${pageContext.request.contextPath }/jsp/char/charts/MSStackedColumn2D.swf", "myChartId8", "100%", "100%");  
			myChart.setDataXML(xml);  
			myChart.render("jdzxqk1");  
		}
	});
}
//设计
function sjqk1(){
	var action1 = controllername + "?single_chartData&zxqk=sjqk&nd="+$("#ND").val();
	$.ajax({
		url : action1,
		success: function(xml){
			var myChart =  new FusionCharts("${pageContext.request.contextPath }/jsp/char/charts/MSStackedColumn2D.swf", "myChartId9", "100%", "100%");  
			myChart.setDataXML(xml);  
			myChart.render("jdzxqk2");  
		}
	});
}
//设计
function sjqk2(){
	var action1 = controllername + "?single_chartData&zxqk=zjqk&nd="+$("#ND").val();
	$.ajax({
		url : action1,
		success: function(xml){
			var myChart =  new FusionCharts("${pageContext.request.contextPath }/jsp/char/charts/MSStackedColumn2D.swf", "myChartId10", "100%", "100%");  
			myChart.setDataXML(xml);  
			myChart.render("jdzxqk3");  
		}
	});
}
//设计
function sjqk3(){
	var action1 = controllername + "?single_chartData&zxqk=zbqk&nd="+$("#ND").val();
	$.ajax({
		url : action1,	    
		success: function(xml){
			var myChart =  new FusionCharts("${pageContext.request.contextPath }/jsp/char/charts/MSStackedColumn2D.swf", "myChartId11", "100%", "100%");  
			myChart.setDataXML(xml);  
			myChart.render("jdzxqk4");  
		}
	});
}
//征拆
function zcqk(){
	var action1 = controllername + "?single_chartData&zxqk=zcqk&nd="+$("#ND").val();
	$.ajax({
		url : action1,
		success: function(xml){
			var myChart =  new FusionCharts("${pageContext.request.contextPath }/jsp/char/charts/MSStackedColumn2D.swf", "myChartId12", "100%", "100%");  
			myChart.setDataXML(xml);  
			myChart.render("jdzxqk5");  
		}
	});
}
//排迁
function pqqk(){
	var action1 = controllername + "?single_chartData&zxqk=pqqk&nd="+$("#ND").val();
	$.ajax({
		url : action1,
		success: function(xml){
			var myChart =  new FusionCharts("${pageContext.request.contextPath }/jsp/char/charts/MSStackedColumn2D.swf", "myChartId13", "100%", "100%");  
			myChart.setDataXML(xml);  
			myChart.render("jdzxqk6");  
		}
	});
}
//开工
function kgqk() {
	var action1 = controllername + "?single_chartData&zxqk=kgqk&nd="+$("#ND").val();
	$.ajax({
		url : action1,
		success: function(xml){
			var myChart =  new FusionCharts("${pageContext.request.contextPath }/jsp/char/charts/MSStackedColumn2D.swf", "myChartId14", "100%", "100%");  
			myChart.setDataXML(xml);  
			myChart.render("jdzxqk7");  
		}
	});
}
//完工
function wgqk() {
	var action1 = controllername + "?single_chartData&zxqk=wgqk&nd="+$("#ND").val();
	$.ajax({
		url : action1,
		success: function(xml){
			var myChart =  new FusionCharts("${pageContext.request.contextPath }/jsp/char/charts/MSStackedColumn2D.swf", "myChartId15", "100%", "100%");  
			myChart.setDataXML(xml);  
			myChart.render("jdzxqk8");  
		}
	});
}

//初始化调用方法
function init(){
	queryJhbzzk_jhps();
	queryJhbzzk_lxxmps();
	queryJhbzzk_ybxms();
	queryJhbzzk_wc(countXmzs);
	queryJhbzzk_wbz(countXmzs);
	queryJhbzzk_bfwc(countWc,countWbz);
}
//年份查询
function generateNd(ndObj){
	ndObj.attr("src","T#GC_JH_SJ:distinct ND:ND as nnd:SFYX='1' order by nnd asc ");
	ndObj.attr("kind","dic");
	ndObj.html('');
	reloadSelectTableDic(ndObj);
	ndObj.val(new Date().getFullYear());
}
//计划编制状况-计划批数
function queryJhbzzk_jhps(){
	var data = null;
		$.ajax({
			url : controllername_query + "?query_jhps&nd="+$("#ND").val(),
			data : data,
			dataType : "json",  
			type : 'post',
			success : function(result) {
				var num=$("#jhps");
				num[0].innerHTML=result.msg;		
		}
	});
}
//计划编制状况-零星项目批数
function queryJhbzzk_lxxmps(){
	var data = null;
		$.ajax({
			url : controllername_query + "?query_lxxmps&nd="+$("#ND").val(),
			data : data,
			dataType : "json",  
			type : 'post',
			success : function(result) {
				var num=$("#lxxmps");
				num[0].innerHTML=result.msg;		
		}
	});
}
//计划编制状况-应编项目数
function queryJhbzzk_ybxms(){
	var data = null;
		$.ajax({
			url : controllername_query + "?query_ybxms&nd="+$("#ND").val(),
			data : data,
			dataType : "json",  
			type : 'post',
			async :	false,
			cache : false,
			success : function(result) {
				var num=$("#ybxms");
				countXmzs=result.msg;
				num[0].innerHTML=result.msg;		
		}
	});
}
//计划编制状况-完成
function queryJhbzzk_wc(countXmzs){
	var data = null;
		$.ajax({
			url : controllername_query + "?query_wc&nd="+$("#ND").val()+"&countXmzs="+countXmzs,
			data : data,
			dataType : "json",  
			type : 'post',
			async :	false,
			cache : false,
			success : function(result) {
				var num=$("#wc");
				countWc = result.msg;
				num[0].innerHTML=result.msg;		
		}
	});
}
//计划编制状况-为编制
function queryJhbzzk_wbz(countXmzs){
	var data = null;
		$.ajax({
			url : controllername_query + "?query_wbz&nd="+$("#ND").val()+"&countXmzs="+countXmzs,
			data : data,
			dataType : "json",  
			type : 'post',
			async :	false,
			cache : false,
			success : function(result) {
				var num=$("#wbz");
				countWbz = result.msg;
				num[0].innerHTML=result.msg;		
		}
	});
}
//计划编制状况-部分完成
function queryJhbzzk_bfwc(countWc,countWbz){
	var num=$("#bfwc");
	var bfwc = parseInt(countXmzs)-parseInt(countWc)-parseInt(countWbz);
	if(bfwc == 0){
		num[0].innerHTML="0[0%]";	
	}else if(countXmzs == 0){
		num[0].innerHTML="0[0%]";	
	}else{
		num[0].innerHTML=bfwc+"["+(bfwc/countXmzs*100).toFixed(1)+"%]";
	}
		
}
</script>
</head>
<body>
<div class="container-fluid">
    <p></p>
    <div class="B-small-from-table-auto" style="border:0px">
    	<h4 class="title" style="background:none; color:#333; border-bottom:#ccc solid 1px;font-size:15px">统筹计划管理运行状态监控&nbsp;&nbsp;
	    	<span class="pull">
	    		<select	id="ND" class="span12 year" style="width:6%;margin-top:5px;" noNullSelect ="true" name="ND" fieldname="ND" defaultMemo="全部" operation="=" ></select>
	    		<!-- <select	id="ND" style="width:5%;height:21px;padding:0px;padding-top:-1px;margin-top:5px;" noNullSelect ="true" name="ND" fieldname="ND" defaultMemo="全部" operation="=" ></select> -->
	        </span>
    	</h4>
        <div class="container-fluid">
            <div class="row-fluid" >
                <div class="span2">
                    <fieldset class="b_ddd" style="width:180px">
                        <legend>计划编制状况</legend>
                        <table class="table table-striped" style="height:266px">
                            <tr>
                                <th class="text-center" width="200px">计划批数</th>
                                <td align="right" id="jhps"></td>
                            </tr>
                            <tr>
                                <th class="text-center">零星项目批数</th>
                                <td align="right" id="lxxmps">
                                </td>
                            </tr>
                            <tr>
                                <th class="text-center">应编项目数</th>
                                <td align="right" id="ybxms">
                                </td>
                            </tr>
                            <tr>
                                <th class="text-center">完成</th>
                                <td align="right" id="wc">
                                </td>
                            </tr>
                            <tr>
                                <th class="text-center">部分完成</th>
                                <td align="right" id="bfwc"></td>
                            </tr>
                            <tr>
                                <th class="text-center">未编制</th>
                                <td align="right" id="wbz">
                                </td>
                            </tr>
                        </table>
                    </fieldset>
                </div>
                <div class="span4">
                    <fieldset class="b_ddd">
                        <legend>项目执行情况</legend>
                        <div class="row-fluid" style="height:275px">
                         	<!-- 使用fushionchar-->
                        	<div class="span6" style = "" id="chartdiv" align="center"></div>
                        </div>
                    </fieldset>
                </div>
                <div class="span6">
                    <fieldset class="b_ddd">
                        <legend>存在问题情况</legend>
                        <table class="table table-striped">
                            <tr>
                                <th class="text-center">手续办理</th>
                                <td>一般
                                <%
                                sbSql = new StringBuffer();
                                sbSql.append("SELECT COUNT(WTTB_INFO_ID) FROM WTTB_INFO WHERE WTLX = '18' AND WTXZ = '11' AND SFYX = '1' ");
                                sql = sbSql.toString();
					     		String[][] sxblArray_yb = DBUtil.query(conn, sql);
					     		if(Pub.emptyArray(sxblArray_yb)){
							      	 out.println(sxblArray_yb[0][0].toString());
							       }
                                %>
                                [已解决
                                <%
                                sbSql = new StringBuffer();
                                sbSql.append("SELECT COUNT(WTTB_INFO_ID) FROM WTTB_INFO WHERE WTLX = '18' AND WTXZ = '11' AND SFYX = '1' AND SJZT = '6'");
                                sql = sbSql.toString();
					     		String[][] sxblArray_yb_jj = DBUtil.query(conn, sql);
					     		if(Pub.emptyArray(sxblArray_yb_jj)){
							      	 out.println(sxblArray_yb_jj[0][0].toString());
							       }
                                %>]
                                </td>
                                <td>严重
                                <%
                                sbSql = new StringBuffer();
                                sbSql.append("SELECT COUNT(WTTB_INFO_ID) FROM WTTB_INFO WHERE WTLX = '18' AND WTXZ = '12' AND SFYX = '1' ");
                                sql = sbSql.toString();
					     		String[][] sxblArray_yz = DBUtil.query(conn, sql);
					     		if(Pub.emptyArray(sxblArray_yz)){
							      	 out.println(sxblArray_yz[0][0].toString());
							       }
                                %>
                                [已解决
                                <%
                                sbSql = new StringBuffer();
                                sbSql.append("SELECT COUNT(WTTB_INFO_ID) FROM WTTB_INFO WHERE WTLX = '18' AND WTXZ = '12' AND SFYX = '1' AND SJZT = '6'");
                                sql = sbSql.toString();
					     		String[][] sxblArray_yz_jj = DBUtil.query(conn, sql);
					     		if(Pub.emptyArray(sxblArray_yz_jj)){
							      	 out.println(sxblArray_yz_jj[0][0].toString());
							       }
                                %>]
                                </td>
                                <td>极其严重 
                                <%
                                sbSql = new StringBuffer();
                                sbSql.append("SELECT COUNT(WTTB_INFO_ID) FROM WTTB_INFO WHERE WTLX = '18' AND WTXZ = '13' AND SFYX = '1' ");
                                sql = sbSql.toString();
					     		String[][] sxblArray_jqyz = DBUtil.query(conn, sql);
					     		if(Pub.emptyArray(sxblArray_jqyz)){
							      	 out.println(sxblArray_jqyz[0][0].toString());
							       }
                                %>
                                [已解决
                                <%
                                sbSql = new StringBuffer();
                                sbSql.append("SELECT COUNT(WTTB_INFO_ID) FROM WTTB_INFO WHERE WTLX = '18' AND WTXZ = '13' AND SFYX = '1' AND SJZT = '6'");
                                sql = sbSql.toString();
					     		String[][] sxblArray_jqyz_jj = DBUtil.query(conn, sql);
					     		if(Pub.emptyArray(sxblArray_jqyz_jj)){
							      	 out.println(sxblArray_jqyz_jj[0][0].toString());
							       }
                                %>]
                                </td>
                            </tr>
                            <tr>
                                <th class="text-center">设计情况</th>
                                <td>一般
                                <%
                                sbSql = new StringBuffer();
                                sbSql.append("SELECT COUNT(WTTB_INFO_ID) FROM WTTB_INFO WHERE WTLX = '20' AND WTXZ = '11' AND SFYX = '1' ");
                                sql = sbSql.toString();
					     		String[][] sjqkArray_yb = DBUtil.query(conn, sql);
					     		if(Pub.emptyArray(sjqkArray_yb)){
							      	 out.println(sjqkArray_yb[0][0].toString());
							       }
                                %>
                                [已解决
                                 <%
                                sbSql = new StringBuffer();
                                sbSql.append("SELECT COUNT(WTTB_INFO_ID) FROM WTTB_INFO WHERE WTLX = '20' AND WTXZ = '11' AND SFYX = '1' AND SJZT = '6'");
                                sql = sbSql.toString();
					     		String[][] sjqkArray_yb_jj = DBUtil.query(conn, sql);
					     		if(Pub.emptyArray(sjqkArray_yb_jj)){
							      	 out.println(sjqkArray_yb_jj[0][0].toString());
							       }
                                %>]
                                </td>
                                <td>严重
                                <%
                                sbSql = new StringBuffer();
                                sbSql.append("SELECT COUNT(WTTB_INFO_ID) FROM WTTB_INFO WHERE WTLX = '20' AND WTXZ = '12' AND SFYX = '1' ");
                                sql = sbSql.toString();
					     		String[][] sjqkArray_yz = DBUtil.query(conn, sql);
					     		if(Pub.emptyArray(sjqkArray_yz)){
							      	 out.println(sjqkArray_yz[0][0].toString());
							       }
                                %>
                                [已解决
                                <%
                                sbSql = new StringBuffer();
                                sbSql.append("SELECT COUNT(WTTB_INFO_ID) FROM WTTB_INFO WHERE WTLX = '20' AND WTXZ = '12' AND SFYX = '1' AND SJZT = '6'");
                                sql = sbSql.toString();
					     		String[][] sjqkArray_yz_jj = DBUtil.query(conn, sql);
					     		if(Pub.emptyArray(sjqkArray_yz_jj)){
							      	 out.println(sjqkArray_yz_jj[0][0].toString());
							       }
                                %>]
                                </td>
                                <td>极其严重
                                <%
                                sbSql = new StringBuffer();
                                sbSql.append("SELECT COUNT(WTTB_INFO_ID) FROM WTTB_INFO WHERE WTLX = '20' AND WTXZ = '13' AND SFYX = '1' ");
                                sql = sbSql.toString();
					     		String[][] sjqkArray_jqyz = DBUtil.query(conn, sql);
					     		if(Pub.emptyArray(sjqkArray_jqyz)){
							      	 out.println(sjqkArray_jqyz[0][0].toString());
							       }
                                %>
                                [已解决
                                <%
                                sbSql = new StringBuffer();
                                sbSql.append("SELECT COUNT(WTTB_INFO_ID) FROM WTTB_INFO WHERE WTLX = '20' AND WTXZ = '13' AND SFYX = '1' AND SJZT = '6'");
                                sql = sbSql.toString();
					     		String[][] sjqkArray_jqyz_jj = DBUtil.query(conn, sql);
					     		if(Pub.emptyArray(sjqkArray_jqyz_jj)){
							      	 out.println(sjqkArray_jqyz_jj[0][0].toString());
							       }
                                %>]
                                </td>
                            </tr>
                            <tr>
                                <th class="text-center">造价情况</th>
                                <td>一般
                                <%
                                sbSql = new StringBuffer();
                                sbSql.append("SELECT COUNT(WTTB_INFO_ID) FROM WTTB_INFO WHERE WTLX = '12' AND WTXZ = '11' AND SFYX = '1' ");
                                sql = sbSql.toString();
					     		String[][] zjqkArray_yb = DBUtil.query(conn, sql);
					     		if(Pub.emptyArray(zjqkArray_yb)){
							      	 out.println(zjqkArray_yb[0][0].toString());
							       }
                                %>
                                [已解决
                                 <%
                                sbSql = new StringBuffer();
                                sbSql.append("SELECT COUNT(WTTB_INFO_ID) FROM WTTB_INFO WHERE WTLX = '12' AND WTXZ = '11' AND SFYX = '1' AND SJZT = '6'");
                                sql = sbSql.toString();
					     		String[][] zjqkArray_yb_jj = DBUtil.query(conn, sql);
					     		if(Pub.emptyArray(zjqkArray_yb_jj)){
							      	 out.println(zjqkArray_yb_jj[0][0].toString());
							       }
                                %>]
                                </td>
                                <td>严重
                                <%
                                sbSql = new StringBuffer();
                                sbSql.append("SELECT COUNT(WTTB_INFO_ID) FROM WTTB_INFO WHERE WTLX = '12' AND WTXZ = '12' AND SFYX = '1' ");
                                sql = sbSql.toString();
					     		String[][] zjqkArray_yz = DBUtil.query(conn, sql);
					     		if(Pub.emptyArray(zjqkArray_yz)){
							      	 out.println(zjqkArray_yz[0][0].toString());
							       }
                                %>
                                [已解决
                                <%
                                sbSql = new StringBuffer();
                                sbSql.append("SELECT COUNT(WTTB_INFO_ID) FROM WTTB_INFO WHERE WTLX = '12' AND WTXZ = '12' AND SFYX = '1' AND SJZT = '6'");
                                sql = sbSql.toString();
					     		String[][] zjqkArray_yz_jj = DBUtil.query(conn, sql);
					     		if(Pub.emptyArray(zjqkArray_yz_jj)){
							      	 out.println(zjqkArray_yz_jj[0][0].toString());
							       }
                                %>]
                                </td>
                                <td>极其严重
                                <%
                                sbSql = new StringBuffer();
                                sbSql.append("SELECT COUNT(WTTB_INFO_ID) FROM WTTB_INFO WHERE WTLX = '12' AND WTXZ = '13' AND SFYX = '1' ");
                                sql = sbSql.toString();
					     		String[][] zjqkArray_jqyz = DBUtil.query(conn, sql);
					     		if(Pub.emptyArray(zjqkArray_jqyz)){
							      	 out.println(zjqkArray_jqyz[0][0].toString());
							       }
                                %>
                                [已解决
                                <%
                                sbSql = new StringBuffer();
                                sbSql.append("SELECT COUNT(WTTB_INFO_ID) FROM WTTB_INFO WHERE WTLX = '12' AND WTXZ = '13' AND SFYX = '1' AND SJZT = '6'");
                                sql = sbSql.toString();
					     		String[][] zjqkArray_jqyz_jj = DBUtil.query(conn, sql);
					     		if(Pub.emptyArray(zjqkArray_jqyz_jj)){
							      	 out.println(zjqkArray_jqyz_jj[0][0].toString());
							       }
                                %>]
                                </td>
                            </tr>
                            <tr>
                                <th class="text-center">招标情况</th>
                                <td>一般
                                <%
                                sbSql = new StringBuffer();
                                sbSql.append("SELECT COUNT(WTTB_INFO_ID) FROM WTTB_INFO WHERE WTLX = '21' AND WTXZ = '11' AND SFYX = '1' ");
                                sql = sbSql.toString();
					     		String[][] zbqkArray_yb = DBUtil.query(conn, sql);
					     		if(Pub.emptyArray(zbqkArray_yb)){
							      	 out.println(zbqkArray_yb[0][0].toString());
							       }
                                %>
                                [已解决
                                 <%
                                sbSql = new StringBuffer();
                                sbSql.append("SELECT COUNT(WTTB_INFO_ID) FROM WTTB_INFO WHERE WTLX = '21' AND WTXZ = '11' AND SFYX = '1' AND SJZT = '6'");
                                sql = sbSql.toString();
					     		String[][] zbqkArray_yb_jj = DBUtil.query(conn, sql);
					     		if(Pub.emptyArray(zbqkArray_yb_jj)){
							      	 out.println(zbqkArray_yb_jj[0][0].toString());
							       }
                                %>]
                                </td>
                                <td>严重
                                <%
                                sbSql = new StringBuffer();
                                sbSql.append("SELECT COUNT(WTTB_INFO_ID) FROM WTTB_INFO WHERE WTLX = '21' AND WTXZ = '12' AND SFYX = '1' ");
                                sql = sbSql.toString();
					     		String[][] zbqkArray_yz = DBUtil.query(conn, sql);
					     		if(Pub.emptyArray(zbqkArray_yz)){
							      	 out.println(zbqkArray_yz[0][0].toString());
							       }
                                %>
                                [已解决
                                <%
                                sbSql = new StringBuffer();
                                sbSql.append("SELECT COUNT(WTTB_INFO_ID) FROM WTTB_INFO WHERE WTLX = '21' AND WTXZ = '12' AND SFYX = '1' AND SJZT = '6'");
                                sql = sbSql.toString();
					     		String[][] zbqkArray_yz_jj = DBUtil.query(conn, sql);
					     		if(Pub.emptyArray(zbqkArray_yz_jj)){
							      	 out.println(zbqkArray_yz_jj[0][0].toString());
							       }
                                %>]
                                </td>
                                <td>极其严重
                                <%
                                sbSql = new StringBuffer();
                                sbSql.append("SELECT COUNT(WTTB_INFO_ID) FROM WTTB_INFO WHERE WTLX = '21' AND WTXZ = '13' AND SFYX = '1' ");
                                sql = sbSql.toString();
					     		String[][] zbqkArray_jqyz = DBUtil.query(conn, sql);
					     		if(Pub.emptyArray(zbqkArray_jqyz)){
							      	 out.println(zbqkArray_jqyz[0][0].toString());
							       }
                                %>
                                [已解决
                                <%
                                sbSql = new StringBuffer();
                                sbSql.append("SELECT COUNT(WTTB_INFO_ID) FROM WTTB_INFO WHERE WTLX = '21' AND WTXZ = '13' AND SFYX = '1' AND SJZT = '6'");
                                sql = sbSql.toString();
					     		String[][] zbqkArray_jqyz_jj = DBUtil.query(conn, sql);
					     		if(Pub.emptyArray(zbqkArray_jqyz_jj)){
							      	 out.println(zbqkArray_jqyz_jj[0][0].toString());
							       }
                                %>]
                                </td>
                            </tr>
                            <tr>
                                <th class="text-center">征拆情况</th>
                                <td>一般
                                <%
                                sbSql = new StringBuffer();
                                sbSql.append("SELECT COUNT(WTTB_INFO_ID) FROM WTTB_INFO WHERE WTLX = '17' AND WTXZ = '11' AND SFYX = '1' ");
                                sql = sbSql.toString();
					     		String[][] zsqkArray_yb = DBUtil.query(conn, sql);
					     		if(Pub.emptyArray(zsqkArray_yb)){
							      	 out.println(zsqkArray_yb[0][0].toString());
							       }
                                %>
                                [已解决
                                 <%
                                sbSql = new StringBuffer();
                                sbSql.append("SELECT COUNT(WTTB_INFO_ID) FROM WTTB_INFO WHERE WTLX = '17' AND WTXZ = '11' AND SFYX = '1' AND SJZT = '6'");
                                sql = sbSql.toString();
					     		String[][] zsqkArray_yb_jj = DBUtil.query(conn, sql);
					     		if(Pub.emptyArray(zsqkArray_yb_jj)){
							      	 out.println(zsqkArray_yb_jj[0][0].toString());
							       }
                                %>]
                                </td>
                                <td>严重
                                <%
                                sbSql = new StringBuffer();
                                sbSql.append("SELECT COUNT(WTTB_INFO_ID) FROM WTTB_INFO WHERE WTLX = '17' AND WTXZ = '12' AND SFYX = '1' ");
                                sql = sbSql.toString();
					     		String[][] zsqkArray_yz = DBUtil.query(conn, sql);
					     		if(Pub.emptyArray(zsqkArray_yz)){
							      	 out.println(zsqkArray_yz[0][0].toString());
							       }
                                %>
                                [已解决
                                <%
                                sbSql = new StringBuffer();
                                sbSql.append("SELECT COUNT(WTTB_INFO_ID) FROM WTTB_INFO WHERE WTLX = '17' AND WTXZ = '12' AND SFYX = '1' AND SJZT = '6'");
                                sql = sbSql.toString();
					     		String[][] zsqkArray_yz_jj = DBUtil.query(conn, sql);
					     		if(Pub.emptyArray(zsqkArray_yz_jj)){
							      	 out.println(zsqkArray_yz_jj[0][0].toString());
							       }
                                %>]
                                </td>
                                <td>极其严重
                                <%
                                sbSql = new StringBuffer();
                                sbSql.append("SELECT COUNT(WTTB_INFO_ID) FROM WTTB_INFO WHERE WTLX = '17' AND WTXZ = '13' AND SFYX = '1' ");
                                sql = sbSql.toString();
					     		String[][] zsqkArray_jqyz = DBUtil.query(conn, sql);
					     		if(Pub.emptyArray(zsqkArray_jqyz)){
							      	 out.println(zsqkArray_jqyz[0][0].toString());
							       }
                                %>
                                [已解决
                                <%
                                sbSql = new StringBuffer();
                                sbSql.append("SELECT COUNT(WTTB_INFO_ID) FROM WTTB_INFO WHERE WTLX = '17' AND WTXZ = '13' AND SFYX = '1' AND SJZT = '6'");
                                sql = sbSql.toString();
					     		String[][] zsqkArray_jqyz_jj = DBUtil.query(conn, sql);
					     		if(Pub.emptyArray(zsqkArray_jqyz_jj)){
							      	 out.println(zsqkArray_jqyz_jj[0][0].toString());
							       }
                                %>]
                                </td>
                            </tr>
                            <tr>
                                <th class="text-center">排迁情况</th>
                                <td>一般
                                <%
                                sbSql = new StringBuffer();
                                sbSql.append("SELECT COUNT(WTTB_INFO_ID) FROM WTTB_INFO WHERE WTLX = '22' AND WTXZ = '11' AND SFYX = '1' ");
                                sql = sbSql.toString();
					     		String[][] pqqkArray_yb = DBUtil.query(conn, sql);
					     		if(Pub.emptyArray(pqqkArray_yb)){
							      	 out.println(pqqkArray_yb[0][0].toString());
							       }
                                %>
                                [已解决
                                 <%
                                sbSql = new StringBuffer();
                                sbSql.append("SELECT COUNT(WTTB_INFO_ID) FROM WTTB_INFO WHERE WTLX = '22' AND WTXZ = '11' AND SFYX = '1' AND SJZT = '6'");
                                sql = sbSql.toString();
					     		String[][] pqqkArray_yb_jj = DBUtil.query(conn, sql);
					     		if(Pub.emptyArray(pqqkArray_yb_jj)){
							      	 out.println(pqqkArray_yb_jj[0][0].toString());
							       }
                                %>]
                                </td>
                                <td>严重
                                <%
                                sbSql = new StringBuffer();
                                sbSql.append("SELECT COUNT(WTTB_INFO_ID) FROM WTTB_INFO WHERE WTLX = '22' AND WTXZ = '12' AND SFYX = '1' ");
                                sql = sbSql.toString();
					     		String[][] pqqkArray_yz = DBUtil.query(conn, sql);
					     		if(Pub.emptyArray(pqqkArray_yz)){
							      	 out.println(pqqkArray_yz[0][0].toString());
							       }
                                %>
                                [已解决
                                <%
                                sbSql = new StringBuffer();
                                sbSql.append("SELECT COUNT(WTTB_INFO_ID) FROM WTTB_INFO WHERE WTLX = '22' AND WTXZ = '12' AND SFYX = '1' AND SJZT = '6'");
                                sql = sbSql.toString();
					     		String[][] pqqkArray_yz_jj = DBUtil.query(conn, sql);
					     		if(Pub.emptyArray(pqqkArray_yz_jj)){
							      	 out.println(pqqkArray_yz_jj[0][0].toString());
							       }
                                %>]
                                </td>
                                <td>极其严重
                                <%
                                sbSql = new StringBuffer();
                                sbSql.append("SELECT COUNT(WTTB_INFO_ID) FROM WTTB_INFO WHERE WTLX = '22' AND WTXZ = '13' AND SFYX = '1' ");
                                sql = sbSql.toString();
					     		String[][] pqqkArray_jqyz = DBUtil.query(conn, sql);
					     		if(Pub.emptyArray(pqqkArray_jqyz)){
							      	 out.println(pqqkArray_jqyz[0][0].toString());
							       }
                                %>
                                [已解决
                                <%
                                sbSql = new StringBuffer();
                                sbSql.append("SELECT COUNT(WTTB_INFO_ID) FROM WTTB_INFO WHERE WTLX = '22' AND WTXZ = '13' AND SFYX = '1' AND SJZT = '6'");
                                sql = sbSql.toString();
					     		String[][] pqqkArray_jqyz_jj = DBUtil.query(conn, sql);
					     		if(Pub.emptyArray(pqqkArray_jqyz_jj)){
							      	 out.println(pqqkArray_jqyz_jj[0][0].toString());
							       }
                                %>]
                                </td>
                            </tr>
                            <tr>
                                <th class="text-center">施工情况</th>
                                <td>一般
                                <%
                                sbSql = new StringBuffer();
                                sbSql.append("SELECT COUNT(WTTB_INFO_ID) FROM WTTB_INFO WHERE WTLX = '23' AND WTXZ = '11' AND SFYX = '1' ");
                                sql = sbSql.toString();
					     		String[][] sgqkArray_yb = DBUtil.query(conn, sql);
					     		if(Pub.emptyArray(sgqkArray_yb)){
							      	 out.println(sgqkArray_yb[0][0].toString());
							       }
                                %>
                                [已解决
                                 <%
                                sbSql = new StringBuffer();
                                sbSql.append("SELECT COUNT(WTTB_INFO_ID) FROM WTTB_INFO WHERE WTLX = '23' AND WTXZ = '11' AND SFYX = '1' AND SJZT = '6'");
                                sql = sbSql.toString();
					     		String[][] sgqkArray_yb_jj = DBUtil.query(conn, sql);
					     		if(Pub.emptyArray(sgqkArray_yb_jj)){
							      	 out.println(sgqkArray_yb_jj[0][0].toString());
							       }
                                %>]
                                </td>
                                <td>严重
                                <%
                                sbSql = new StringBuffer();
                                sbSql.append("SELECT COUNT(WTTB_INFO_ID) FROM WTTB_INFO WHERE WTLX = '23' AND WTXZ = '12' AND SFYX = '1' ");
                                sql = sbSql.toString();
					     		String[][] sgqkArray_yz = DBUtil.query(conn, sql);
					     		if(Pub.emptyArray(sgqkArray_yz)){
							      	 out.println(sgqkArray_yz[0][0].toString());
							       }
                                %>
                                [已解决
                                <%
                                sbSql = new StringBuffer();
                                sbSql.append("SELECT COUNT(WTTB_INFO_ID) FROM WTTB_INFO WHERE WTLX = '23' AND WTXZ = '12' AND SFYX = '1' AND SJZT = '6'");
                                sql = sbSql.toString();
					     		String[][] sgqkArray_yz_jj = DBUtil.query(conn, sql);
					     		if(Pub.emptyArray(sgqkArray_yz_jj)){
							      	 out.println(sgqkArray_yz_jj[0][0].toString());
							       }
                                %>]
                                </td>
                                <td>极其严重
                                <%
                                sbSql = new StringBuffer();
                                sbSql.append("SELECT COUNT(WTTB_INFO_ID) FROM WTTB_INFO WHERE WTLX = '23' AND WTXZ = '13' AND SFYX = '1' ");
                                sql = sbSql.toString();
					     		String[][] sgqkArray_jqyz = DBUtil.query(conn, sql);
					     		if(Pub.emptyArray(sgqkArray_jqyz)){
							      	 out.println(sgqkArray_jqyz[0][0].toString());
							       }
                                %>
                                [已解决
                                <%
                                sbSql = new StringBuffer();
                                sbSql.append("SELECT COUNT(WTTB_INFO_ID) FROM WTTB_INFO WHERE WTLX = '23' AND WTXZ = '13' AND SFYX = '1' AND SJZT = '6'");
                                sql = sbSql.toString();
					     		String[][] sgqkArray_jqyz_jj = DBUtil.query(conn, sql);
					     		if(Pub.emptyArray(sgqkArray_jqyz_jj)){
							      	 out.println(sgqkArray_jqyz_jj[0][0].toString());
							       }
                                %>]
                                </td>
                            </tr>
                            <tr>
                                <th class="text-center">其他情况</th>
                                <td>一般
                                <%
                                sbSql = new StringBuffer();
                                sbSql.append("SELECT COUNT(WTTB_INFO_ID) FROM WTTB_INFO WHERE WTLX not in('18','20','12','21','17','22','23') AND WTXZ = '11' AND SFYX = '1' ");
                                sql = sbSql.toString();
					     		String[][] qtArray_yb = DBUtil.query(conn, sql);
					     		if(Pub.emptyArray(qtArray_yb)){
							      	 out.println(qtArray_yb[0][0].toString());
							       }
                                %>
                                [已解决
                                 <%
                                sbSql = new StringBuffer();
                                sbSql.append("SELECT COUNT(WTTB_INFO_ID) FROM WTTB_INFO WHERE WTLX not in('18','20','12','21','17','22','23') AND WTXZ = '11' AND SFYX = '1' AND SJZT = '6'");
                                sql = sbSql.toString();
					     		String[][] qtArray_yb_jj = DBUtil.query(conn, sql);
					     		if(Pub.emptyArray(qtArray_yb_jj)){
							      	 out.println(qtArray_yb_jj[0][0].toString());
							       }
                                %>]
                                </td>
                                <td>严重
                                <%
                                sbSql = new StringBuffer();
                                sbSql.append("SELECT COUNT(WTTB_INFO_ID) FROM WTTB_INFO WHERE WTLX not in('18','20','12','21','17','22','23') AND WTXZ = '12' AND SFYX = '1' ");
                                sql = sbSql.toString();
					     		String[][] qtArray_yz = DBUtil.query(conn, sql);
					     		if(Pub.emptyArray(qtArray_yz)){
							      	 out.println(qtArray_yz[0][0].toString());
							       }
                                %>
                                [已解决
                                <%
                                sbSql = new StringBuffer();
                                sbSql.append("SELECT COUNT(WTTB_INFO_ID) FROM WTTB_INFO WHERE WTLX not in('18','20','12','21','17','22','23') AND WTXZ = '12' AND SFYX = '1' AND SJZT = '6'");
                                sql = sbSql.toString();
					     		String[][] qtArray_yz_jj = DBUtil.query(conn, sql);
					     		if(Pub.emptyArray(qtArray_yz_jj)){
							      	 out.println(qtArray_yz_jj[0][0].toString());
							       }
                                %>]
                                </td>
                                <td>极其严重
                                <%
                                sbSql = new StringBuffer();
                                sbSql.append("SELECT COUNT(WTTB_INFO_ID) FROM WTTB_INFO WHERE WTLX not in('18','20','12','21','17','22','23') AND WTXZ = '13' AND SFYX = '1' ");
                                sql = sbSql.toString();
					     		String[][] qtArray_jqyz = DBUtil.query(conn, sql);
					     		if(Pub.emptyArray(qtArray_jqyz)){
							      	 out.println(qtArray_jqyz[0][0].toString());
							       }
                                %>
                                [已解决
                                <%
                                sbSql = new StringBuffer();
                                sbSql.append("SELECT COUNT(WTTB_INFO_ID) FROM WTTB_INFO WHERE WTLX not in('18','20','12','21','17','22','23') AND WTXZ = '13' AND SFYX = '1' AND SJZT = '6'");
                                sql = sbSql.toString();
					     		String[][] qtArray_jqyz_jj = DBUtil.query(conn, sql);
					     		if(Pub.emptyArray(qtArray_jqyz_jj)){
							      	 out.println(qtArray_jqyz_jj[0][0].toString());
							       }
                                %>]
                                </td>
                            </tr>
                        </table>
                    </fieldset>
                </div>
            </div>
        </div>
    </div>
    <div class="B-small-from-table-auto" style="border:0px">
        <h4 style="background:none; color:#333; border-bottom:#ccc solid 1px;">部门节点执行情况</h4>
        <p>&nbsp;</p>
        <div class="container-fluid">
            <div class="row-fluid">
                <div style = "width:310px;height:300px;display:inline-block;" class="text-center" id = "jdzxqk1" ></div>
                <div style = "width:360px;height:300px;display:inline-block;" class="text-center" id = "jdzxqk2"></div>
                <div style = "width:250px;height:300px;display:inline-block;" class="text-center" id = "jdzxqk3"></div>
                <div style = "width:250px;height:300px;display:inline-block;" class="text-center" id = "jdzxqk4"></div>
                <div style = "width:310px;height:260px;display:inline-block;" class="text-center" id = "jdzxqk5"></div>
                <div style = "width:360px;height:260px;display:inline-block;" class="text-center" id = "jdzxqk6"></div>
                <div style = "width:250px;height:260px;display:inline-block;" class="text-center" id = "jdzxqk7"></div>
                <div style = "width:250px;height:260px;display:inline-block;" class="text-center" id = "jdzxqk8"></div>
            </div>
        </div>
    </div>
    <div class="B-small-from-table-auto" style="border:0px">
        <h4 style="background:none; color:#333; border-bottom:#ccc solid 1px;">项目管理公司计划执行情况</h4>
        <p>&nbsp;</p>
        <div class="container-fluid">
            <div class="row-fluid">
            <div class="span12">
            	<div class="span2">
	               <div class="span12" style ="width:210px;">
	                   <blockquote><strong>项目数量</strong></blockquote>
	                   <div class="span12" id="chartdivxmsl" align="center"></div>
	               </div>
	            </div>
	            <div class="span10">
	               <div class="span2" style="width:15px;text-align:right">
						<div style="writing-mode:tb-rl;text-align:right">
							<br><br><br><br><br><font size="2" face="Arial" color="#A15E01">开工率</font>
							<br><br><br><br><br><br><br><br><font size="2" face="Arial" color="#A15E01">完工率</font>
						</div>
	               </div>
                
                <%
	              //项目管理公司总个数
	              sbSql = new StringBuffer();
	 	          sbSql.append("select count(*)as xmglgsCount from VIEW_YW_XMGLGS");
	 	          sql = sbSql.toString();
	 	          String[][] xmglgsCountResult = DBUtil.query(conn, sql);
	 	          int xmglgsCoount = 0;//总数
	 	          if(Pub.emptyArray(xmglgsCountResult)){
	 	        	 xmglgsCoount = Integer.parseInt(xmglgsCountResult[0][0].toString());
	 	          }
              	%>
                <%
                  sbSql = new StringBuffer();
  	          	  //sbSql.append("select t.row_id,t.bmjc from fs_org_dept t where EXTEND1='1' and ACTIVE_FLAG = '1' order by bmjc");
  	          	  sbSql.append("select t.row_id,t.bmjc from VIEW_YW_XMGLGS t order by bmjc");
	  	          sql = sbSql.toString();
	  	          String[][] xmglgsValue = DBUtil.query(conn, sql);
	  	          String xmglgsText = "";
	  	          String[] xmglgsIdsArray = new String[xmglgsCoount];
	  	          if(Pub.emptyArray(xmglgsValue)){
	  	        	  for(int i = 0; i < xmglgsCoount; i++){
	  	        		xmglgsIds += xmglgsValue[i][0].toString()+",";
	  	        		xmglgsText = xmglgsValue[i][1].toString();
	  	          
                %>
                 <div class="span2" style="width:170px;">
                    <blockquote><strong><%=xmglgsText %>计划执行情况</strong></blockquote>
                    <div class="row-fluid">
	                    <div>
			            	<div class="span11" id="hakg<%=i+1 %>"></div>
			            </div>
                    </div>
                    <div class="row-fluid" >
	                    <div>
	                    	<div class="span11" id="hawg<%=i+1 %>"></div>
	                    </div>
                    </div>
                 </div>
                <%
                } 
	  	        	  }
                %>
				</div>
            </div>
            </div>
        </div>
    </div>
    <div class="B-small-from-table-auto" style="border:0px;">
        <h4 style="background:none; color:#333; border-bottom:#ccc solid 1px;">月度执行情况</h4>
        <div class="container-fluid">
            <div class="row-fluid">
                <div id="ydzxqktj" align="center"></div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
$(function() {
	var ids= '<%=xmglgsIds%>';
	ids = ids.substring(0,ids.length-1);
	var xmglgsIds = new Array();
	xmglgsIds = ids.split(",");
	var count = 12;
	for(var i = 0;i<xmglgsIds.length;i++){
		var action1 = "${pageContext.request.contextPath }/DwjhzxqkServlet?dwmc="+xmglgsIds[i]+"&zxqk=kg&nd="+$("#ND").val();
		doSubmit(action1,i+1,count,"hakg");
		count++;
		action1 = "${pageContext.request.contextPath }/DwjhzxqkServlet?dwmc="+xmglgsIds[i]+"&zxqk=wg&nd="+$("#ND").val();
		doSubmit(action1,i+1,count,"hawg");
		count++;
	}
	
});
//方法提交
function doSubmit(url,i,count,type){
	$.ajax({
		url : url,
		success: function(xml){ 
	      var myChart =  new FusionCharts("${pageContext.request.contextPath }/jsp/char/charts/Pie2D.swf", "myChartIdxms"+count.toString(), "200", "200");  
	      myChart.setDataXML(xml); 
	      myChart.render(type+i);  
	       }
	});
}
</script>
</html>