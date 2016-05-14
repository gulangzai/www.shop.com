<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/jsp/framework/common/taglibs.jsp" %>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>

<!DOCTYPE html>
<html>
  <head>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

	<base href="${ctx_site}/">
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
 	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
 	
    <title><fmt:message key="ui.title"/></title>
	<%@ include file="/jsp/framework/common/head.jsp"%>
  
  </head>
  
<body class="no-skin" onload="GetData();">
  	

		<!-- /section:basics/navbar.layout -->
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>
			<%@ include file="/jsp/framework/project/jik_left.jsp"%>

			<!-- /section:basics/sidebar -->
			<div class="main-content">

				<div class="page-content">
					
					<%@ include file="/jsp/framework/common/setting.jsp"%>					
					
					<div id = "page-content-area" class="page-content-area">
						<!-- ajax content goes here -->
					</div><!-- /.page-content-area -->

					<div id="u3dviewercontent" style="width: 100%; height: 500px; position:absolute;left:0;top:0;z-index:9;display:hidden;">
		
						<iframe src="<%=basePath%>jsp/business/Viewer/Viewer.jsp"
							id="mainFrame" name="mainFrame" scrolling="no" width="100%"
							height="100%" frameborder="0" >
						</iframe>

						<div id="pic" >
						
						</div>
						
					</div>

				</div><!-- /.page-content -->
			</div><!-- /.main-content -->

			<%@ include file="/jsp/framework/common/footer.jsp"%>		

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
			</a>
		</div><!-- /.main-container -->

  
  <!-- basic scripts -->

	<%@ include file="/jsp/framework/common/basic_scripts.jsp"%>
 	 	<script type="text/javascript"  src="${ctx}/assets/sys_resources/js/common/Form2Json.js"></script>
		<script type="text/javascript"  src="${ctx}/assets/sys_resources/js/common/convertJson.js"></script>
		<script type="text/javascript"  src="${ctx}/assets/sys_resources/js/common/combineQuery.js"></script>
		<script type="text/javascript"  src="${ctx}/assets/sys_resources/js/common/default.js?version=20131206"></script>
		<script type="text/javascript"  src="${ctx}/assets/sys_resources/js/common/bootstrap-validation.js"></script>
		<script type="text/javascript"  src="${ctx}/assets/sys_resources/js/common/loadFields.js?version=20131206"></script>
		<script type="text/javascript"  src="${ctx}/assets/sys_resources/plugins/validform/js/Validform_v5.3.2_min.js"></script>		
 		
  <script type="text/javascript">
     //If page has any inline scripts, it goes here
document.getElementById("u3dviewercontent").style.visibility="hidden";
var content = $("b.facility"); 
var content = $('#page-content-area');//the element I want to monitor 
content.bind('DOMNodeInserted', function(e) { 
//console.log('element now contains: ' + $(e.target).html());
//console.log(window.location.hash);
//console.log(document.getElementById("u3dviewercontent").style.visibility); 
if (window.location.hash.indexOf("jkjc-index") > 0 
|| window.location.hash.indexOf("jsjc-index") > 0)
{
	//if (document.getElementById("u3dviewercontent").style.visibility == "hidden")
	{
		document.getElementById("u3dviewercontent").style.visibility="visible";//显示
	}
}
else
{	
	document.getElementById("u3dviewercontent").style.visibility="hidden";//隐藏
}
}); 

     	//globe data storage
		var G_ProjectUID = null;
		var G_Points = null;
		var G_Items = null;
		var G_Item_Types = null
		var G_Points_Items = null;
		var G_PointProperties = null;
		var G_PointData_jc = null;
		var G_PointData_cx = null;
		var G_Points_Items_All = null;
		var G_User_Focus_Points = null;
				
     function getProjectUID(){
     	return G_ProjectUID;
     }

     function getPoints(){
     	return G_Points;
     }
     
     function getItems(){
     	return G_Items;
     }

     function getItemTypes(){
     	return G_Item_Types;
     }

     function getPointsItems(){
     	return G_Points_Items;
     }

     function getPointProperties(){
     	return G_PointProperties;
     }

     function getPointData_jc(){
     	return G_PointData_jc;
     }

     function getPointData_cx(){
     	return G_PointData_cx;
     }
     
     function getUser_Focus_Points(){
     	return G_User_Focus_Points;
     }

     function getPoints_Items_All(){
     	return G_Points_Items_All;
     }
     
     function addUser_Focus_Point(pointuid){
     	G_User_Focus_Points.PRJ_POINTS_UID.push(pointuid);
     	//console.log(IsFocused(pointuid));
     	//更新G_Points_Items_All
		for (var i = 0 ; i < G_Points_Items_All.length ; i++)
		{
			if(pointuid == G_Points_Items_All[i].PRJ_POINTS_UID)
			{
				G_Points_Items_All[i].IsFocused = true;
			}
		}
     }

     function delUser_Focus_Point(pointuid){
     	var new_User_Focus_Points = new Array();
		for (var i = 0 ; i < G_User_Focus_Points.PRJ_POINTS_UID.length ; i++)
		{
			if (pointuid != G_User_Focus_Points.PRJ_POINTS_UID[i])
			{
				new_User_Focus_Points.push(G_User_Focus_Points.PRJ_POINTS_UID[i]);
			}
		}
		
		G_User_Focus_Points.PRJ_POINTS_UID = new_User_Focus_Points;
     	//console.log(IsFocused(pointuid) == false);
     	//更新G_Points_Items_All
		for (var i = 0 ; i < G_Points_Items_All.length ; i++)
		{
			if(pointuid == G_Points_Items_All[i].PRJ_POINTS_UID)
			{
				G_Points_Items_All[i].IsFocused = false;
			}
		}
     }
     
    function updateWarnValue(pointitemid, singlewarn, totalwarn1, totalwarn2){
    	//update G_PointProperties
		for (var i = 0 ; i < G_PointProperties.PRJ_POINT_ITEM_UID.length ; i++)
		{
			if(pointitemid == G_PointProperties.PRJ_POINT_ITEM_UID[i])
			{
				G_PointProperties.SINGLE_WARN[i] = singlewarn;
				G_PointProperties.TOTAL_WARN1[i] = totalwarn1;
				G_PointProperties.TOTAL_WARN2[i] = totalwarn2;
				
				break;
			}
		}
    	
    	//update G_Points_Items_All
		for (var i = 0 ; i < G_Points_Items_All.length ; i++)
		{
			if(pointitemid == G_Points_Items_All[i].PRJ_POINT_ITEM_UID)
			{
				G_Points_Items_All[i].SINGLE_WARN = singlewarn;
				G_Points_Items_All[i].TOTAL_WARN1 = totalwarn1;
				G_Points_Items_All[i].TOTAL_WARN2 = totalwarn2;
				G_Points_Items_All[i].IsWarning = false;
				if(G_Points_Items_All[i].JC_NAME.indexOf("测斜") >= 0 || G_Points_Items_All[i].SHORT_NAME.indexOf("测斜") >= 0)
				{
					for (var j = 0 ; j < G_Points_Items_All[i].CX_DEEP.length ; j++)
					{		
						if (Math.abs(parseFloat(G_Points_Items_All[i].CX_VALUE_DIFF[j])) > parseFloat(singlewarn))
						{
							G_Points_Items_All[i].IsWarning = true;
							break;
						}
						var value = parseFloat(G_Points_Items_All[i].CX_VALUE[j]);
						if (value < parseFloat(totalwarn1) || value > parseFloat(totalwarn2))
						{
							G_Points_Items_All[i].IsWarning = true;
							break;
						}
					}
					
				}
				else
				{
					if(G_Points_Items_All[i].JC_NAME.indexOf("水平") >= 0 || G_Points_Items_All[i].SHORT_NAME.indexOf("水平") >= 0)
					{
						if (Math.abs(parseFloat(G_Points_Items_All[i].H_VALUE_DIFF)) > parseFloat(singlewarn))
						{
							G_Points_Items_All[i].IsWarning = true;
						}
						var value = parseFloat(G_Points_Items_All[i].H_VALUE);
						if (value < parseFloat(totalwarn1) || value > parseFloat(totalwarn2))
						{
							G_Points_Items_All[i].IsWarning = true;
						}

					}
					else
					{
						if (Math.abs(parseFloat(G_Points_Items_All[i].V_VALUE_DIFF)) > parseFloat(singlewarn))
						{
							G_Points_Items_All[i].IsWarning = true;
						}
						var value = parseFloat(G_Points_Items_All[i].V_VALUE);
						if (value < parseFloat(totalwarn1) || value > parseFloat(totalwarn2))
						{
							G_Points_Items_All[i].IsWarning = true;
						}
					}
				}				
				
				break;
			}
		}
	}     

	function IsContained(arr, obj) {  
	    var i = arr.length;  
	    while (i--) {  
	        if (arr[i] === obj) {  
	            return true;  
	        }  
	    }  
	    return false;  
	} 
	
	function IsFocused(pointuid)
	{
		return IsContained(G_User_Focus_Points.PRJ_POINTS_UID, pointuid);
	}

	function GetData(){
			var newprojectid = $('#project_uid').val();
			//console.log(newprojectid);
			
			//if (G_ProjectUID != newprojectid || G_Points == null)
			{
				$.ajax({
				url:"${pageContext.request.contextPath}/jkjc/jcPrjPointsController?queryPoints",
				data:{"project_uid":newprojectid},
				cache : false,
				async :	false,
				dataType : "json",  
				type : 'post',
				success : function(result){
	    			G_Points = result.obj;
	    			//console.log(G_Points.PRJ_POINTS_UID);
				}
				}); 
			}
			
			//if (G_ProjectUID != newprojectid || G_Items == null)
			{
				$.ajax({
				url:"${pageContext.request.contextPath}/jkjc/jcPrjPointsController?queryItems",
				data:{"project_uid":newprojectid},
				cache : false,
				async :	false,
				dataType : "json",  
				type : 'post',
				success : function(result){
	    			G_Items = result.obj;
	    			//console.log(G_Items.JC_PRJ_ITEM_UID);
	    			//子页面 调用 父页面 function
	    			//setTimeout(function (){
					//	window.parent.init(G_Items);
					//}, 3000);
	    			
				}
				}); 
			}
			
			//if (G_Item_Types == null)
			{
				$.ajax({
				url:"${pageContext.request.contextPath}/jkjc/jcPrjPointsController?queryItemTypes",
				cache : false,
				async :	false,
				dataType : "json",  
				type : 'post',
				success : function(result){
	    			G_Item_Types = result.obj;
	    			//console.log(G_Item_Types);
				}
				}); 
			}
	
			//if (G_ProjectUID != newprojectid || G_Points_Items == null)
			{
				$.ajax({
				url:"${pageContext.request.contextPath}/jkjc/jcPrjPointsController?queryPoint_Item",
				data:{"project_uid":newprojectid},
				cache : false,
				async :	false,
				dataType : "json",  
				type : 'post',
				success : function(result){
	    			G_Points_Items = result.obj;
	    			//console.log(G_Points_Items.JC_PRJ_ITEM_UID);
				}
				}); 
			}
			
			//if (G_ProjectUID != newprojectid || G_PointProperties == null)
			{
				$.ajax({
				url:"${pageContext.request.contextPath}/jkjc/jcPrjPointsController?queryPointProperty",
				data:{"project_uid":newprojectid},
				cache : false,
				async :	false,
				dataType : "json",  
				type : 'post',
				success : function(result){
	    			G_PointProperties = result.obj;
	    			//console.log(G_PointProperties.PRJ_POINT_ITEM_UID);
				}
				}); 
			}
	
			//if (G_ProjectUID != newprojectid || G_PointData_jc == null)
			{
				$.ajax({
				url:"${pageContext.request.contextPath}/jkjc/jcPrjPointsController?queryPointData_jc",
				data:{"project_uid":newprojectid},
				cache : false,
				async :	false,
				dataType : "json",  
				type : 'post',
				success : function(result){
	    			G_PointData_jc = result.obj;
	    			//console.log(G_PointData_jc.VERTICAL_VALUE);
				}
				}); 
			}
	
			//if (G_ProjectUID != newprojectid || G_PointData_cx == null)
			{
				$.ajax({
				url:"${pageContext.request.contextPath}/jkjc/jcPrjPointsController?queryPointData_cx",
				data:{"project_uid":newprojectid},
				cache : false,
				async :	false,
				dataType : "json",  
				type : 'post',
				success : function(result){
	    			G_PointData_cx = result.obj;
	    			//console.log(G_PointData_cx.HORIZONTAL_VALUE);
				}
				}); 
			}
			
			G_ProjectUID = newprojectid;

			//if (G_ProjectUID != newprojectid || G_User_Focus_Points == null)
			{
				$.ajax({
				url:"${pageContext.request.contextPath}/jkjc/jcPrjPointsController?queryUser_Focus_Point",
				data:{},
				cache : false,
				async :	false,
				dataType : "json",  
				type : 'post',
				success : function(result){
	    			G_User_Focus_Points = result.obj;
	    			//console.log(G_User_Focus_Points.PRJ_POINTS_UID);
				}
				}); 
			}

		//生成G_Points_Items_All
		G_Points_Items_All = new Array();
		for (var i = 0 ; i < G_Points_Items.PRJ_POINT_ITEM_UID.length ; i++)
		{
			var point_item_id=G_Points_Items.PRJ_POINT_ITEM_UID[i];
			//console.log("point_item_id:"+point_item_id);
			if (point_item_id == null || point_item_id == "")
			{
				console.log("错误：没有找到测点监测项对应的记录！");
				continue;
			}
			var itemid=G_Points_Items.JC_PRJ_ITEM_UID[i];
			///console.log("itemid:"+itemid);
			if (itemid == null || itemid == "")
			{
				console.log("错误：没有找到监测项对应的记录！");
				continue;
			}
			var pointuid = G_Points_Items.PRJ_POINTS_UID[i];
			//console.log("pointuid:"+pointuid);
			if (pointuid == null || pointuid == "")
			{
				console.log("错误：没有找到测点对应的记录！");
				continue;
			}
			//获取监测项信息
			var itemtypename=null;
			var itemname=null;
			var itemshortname=null;
			var itemunit=null;
			var itemprecode=null;
			var itemiconfile=null;
			var jctype = null;
			for (var j = 0 ; j < G_Items.JC_PRJ_ITEM_UID.length ; j++)
			{
				if (itemid == G_Items.JC_PRJ_ITEM_UID[j])
				{
					for (var k = 0 ; k < G_Item_Types.JC_TYPE_UID.length ; k++)
					{
						if (G_Items.JC_TYPE_UID[j] == G_Item_Types.JC_TYPE_UID[k])
						{
							itemtypename = G_Item_Types.JC_TYPE_NAME[k];
							break;
						}
					}
					itemname = G_Items.JC_NAME[j];
					itemshortname = G_Items.SHORT_NAME[j];
					itemunit = G_Items.UNIT[j];
					itemprecode = G_Items.PRE_CODE[j];
					itemiconfile = G_Items.ICON_FILE[j];
					jctype = G_Items.JC_TYPE_UID[j];
					break;
				}
			}
			//iconfile有空值跳过
			if(itemiconfile == null && itemiconfile == "")
			{
				console.log("错误：没有找到监测项对应的图标文件！");
				continue;
			}
			//获取测点信息
			var pointelementid = null;
			var pointcode = null;
			var pointinitheight = null;
			for (var j = 0 ; j < G_Points.PRJ_POINTS_UID.length ; j++)
			{
				if (pointuid == G_Points.PRJ_POINTS_UID[j])
				{
					pointelementid = G_Points.U3D_ELEMENT_ID[j];
					pointcode = G_Points.POINT_CODE[j];
					pointinitheight = G_Points.INIT_HEIGHT[j];
					break;
				}
			}
			
			if (pointcode == null || pointcode == "")
			{
				console.log("错误：没有找到测点对应的测点记录或测点记录有错误！");
				continue;
			}
			var IsFocused=false;
			for (var j = 0 ; j < G_User_Focus_Points.PRJ_POINTS_UID.length ; j++)
			{
				if (pointuid == G_User_Focus_Points.PRJ_POINTS_UID[j])
				{
					IsFocused = true;
					break;
				}
			}
			//取预警值
			var singlewarn=null;
			var totalwarn1=null;
			var totalwarn2=null;
			for (var j = 0 ; j < G_PointProperties.PRJ_POINT_ITEM_UID.length ; j++)
			{
				if (point_item_id == G_PointProperties.PRJ_POINT_ITEM_UID[j])
				{
					singlewarn = G_PointProperties.SINGLE_WARN[j];
					totalwarn1 = G_PointProperties.TOTAL_WARN1[j];
					totalwarn2 = G_PointProperties.TOTAL_WARN2[j];
				}
			}
			
			//预警值有空值也跳过
			if(singlewarn == null && singlewarn == "")
			{
				console.log("错误：SINGLE_WARN有空值！");
				continue;
			}
			if(totalwarn1 == null && totalwarn1 == "")
			{
				console.log("错误：TOTAL_WARN1有空值！");
				continue;
			}
			if(totalwarn2 == null && totalwarn2 == "")
			{
				console.log("错误：TOTAL_WARN2有空值！");
				continue;
			}
			//取测点最新数据并判断是否超过预警值
			var reportdate=null;
			var v_value = null;
			var v_value_diff = null;
			var h_value = null;
			var h_value_diff = null;
			var cx_deep = null;
			var cx_value = null;
			var cx_value_diff = null;
			var IsWarning=false;
			var datatype = "沉降";
			if(itemname.indexOf("测斜") >= 0 || itemshortname.indexOf("测斜") >= 0)
			{
				datatype = "测斜";
				cx_deep = new Array();
				cx_value = new Array();
				cx_value_diff = new Array();
				var datacount=0;
				for (var j = 0 ; j < G_PointData_cx.PRJ_POINTS_UID.length ; j++)
				{
					if (pointuid == G_PointData_cx.PRJ_POINTS_UID[j])
					{
						reportdate = G_PointData_cx.REPORT_DATE[j];
						cx_deep[datacount] = G_PointData_cx.DEEP[j];
						cx_value[datacount] = G_PointData_cx.HORIZONTAL_VALUE[j];
						cx_value_diff[datacount] = G_PointData_cx.HORIZONTAL_VALUE_DIFF[j];
						
						if (!IsWarning)
						{
							if (Math.abs(parseFloat(cx_value_diff[datacount])) > parseFloat(singlewarn))
							{
								IsWarning = true;
							}
							var value = parseFloat(cx_value[datacount]);
							if (value < parseFloat(totalwarn1) || value > parseFloat(totalwarn2))
							{
								IsWarning = true;
							}
						}
						datacount++;
					}
				}
				
			}
			else
			{
				for (var j = 0 ; j < G_PointData_jc.PRJ_POINTS_UID.length ; j++)
				{
					if (pointuid == G_PointData_jc.PRJ_POINTS_UID[j])
					{
						reportdate = G_PointData_jc.REPORT_DATE[j];
						if(itemname.indexOf("水平") >= 0 || itemshortname.indexOf("水平") >= 0)
						{
							datatype = "水平";
							h_value = G_PointData_jc.HORIZONTAL_VALUE[j];
							h_value_diff = G_PointData_jc.HORIZONTAL_VALUE_DIFF[j];

							if (Math.abs(parseFloat(h_value_diff)) > parseFloat(singlewarn))
							{
								IsWarning = true;
							}
							var value = parseFloat(h_value);
							if (value < parseFloat(totalwarn1) || value > parseFloat(totalwarn2))
							{
								IsWarning = true;
							}

						}
						else
						{
							v_value = G_PointData_jc.VERTICAL_VALUE[j];
							v_value_diff = G_PointData_jc.VERTICAL_VALUE_DIFF[j];

							if (Math.abs(parseFloat(v_value_diff)) > parseFloat(singlewarn))
							{
								IsWarning = true;
							}
							var value = parseFloat(v_value);
							if (value < parseFloat(totalwarn1) || value > parseFloat(totalwarn2))
							{
								IsWarning = true;
							}
						}
						break;
					}
				}
			}
			
			var point_item = new Object();
		    point_item.IsWarning = IsWarning;
		    point_item.IsFocused = IsFocused;
		    point_item.PRJ_POINT_ITEM_UID = point_item_id;
		    point_item.PRJ_POINTS_UID = pointuid;
		    point_item.JC_PRJ_ITEM_UID = itemid;
		    point_item.datatype = datatype;
		    point_item.POINT_CODE = pointcode;
		    point_item.U3D_ELEMENT_ID = pointelementid;
		    point_item.INIT_HEIGHT = pointinitheight;
		    point_item.JC_TYPE_UID = jctype;
		    point_item.JC_TYPE_NAME = itemtypename;
		    point_item.JC_NAME = itemname;
		    point_item.SHORT_NAME = itemshortname;
		    point_item.UNIT = itemunit;
		    point_item.PRE_CODE = itemprecode;
		    point_item.ICON_FILE = itemiconfile;
		    point_item.SINGLE_WARN = singlewarn;
		    point_item.TOTAL_WARN1 = totalwarn1;
		    point_item.TOTAL_WARN2 = totalwarn2;
		    point_item.REPORT_DATE = reportdate;
		    point_item.V_VALUE = v_value;
		    point_item.V_VALUE_DIFF = v_value_diff;
		    point_item.H_VALUE = h_value;
		    point_item.H_VALUE_DIFF = h_value_diff;
		    point_item.CX_DEEP = cx_deep;
		    point_item.CX_VALUE = cx_value;
		    point_item.CX_VALUE_DIFF = cx_value_diff;
		    point_item.REPORT_DATE = reportdate;
		    
		    G_Points_Items_All.push(point_item);
		}
	}

     function menutree_click(menuName,menuLocation,menuDesc,target){
    	 
    	 return true;
    	 
    	 if('enable_ajax_content' in ace) {
    		 alert('sd '+menuLocation);
    		 var options = {
    			  content_url:"'"+menuLocation+"'",
   	    		  default_url: 'homepage.html'//default url
   	    		  ,
   	    		  loading_icon: "fa-cog fa-2x blue"
    		 }
    		 ace.enable_ajax_content(jQuery, options);
    	 }
    	 
	 	
		//alert("menuName---"+menuName+"---menuLocation--"+menuLocation+"---menuDesc--"+menuDesc+"---target--"+target);
		if("#"!=menuLocation){
			window.location.href="${ctx}/"+menuLocation;
		}	
	 }
     
     jQuery(function($) {
    	 alert($('#app_code').val());
    	 	if('enable_ajax_content' in ace) {
				var options = {
				  content_url: function(url) {
					return url;
				  },
				  default_url:'page/framework/project/frame_jik_index'
				}
				//alert(options.content_url);
				ace.enable_ajax_content($, options);
			  }
    	 	
     });
     
  </script>
  </body>
</html>
