<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Unity Web Player | JiKeng</title>
		<script type='text/javascript' src='<%=basePath %>assets/js/jquery.min.js'></script>
		<!--[if IE]> -->
		<script type="text/javascript">
			window.jQuery || document.write("<script src='<%=basePath %>assets/sys_resources/js/jquery.js'>"+"<"+"/script>");
		</script>
		<!-- <![endif]-->
		<script type="text/javascript">
		<!--
		var unityObjectUrl = "<%=basePath %>assets/sys_resources/plugins/u3d/UnityObject2.js";
		if (document.location.protocol == 'https:')
			unityObjectUrl = unityObjectUrl.replace("http://", "https://ssl-");
		document.write('<script type="text\/javascript" src="' + unityObjectUrl + '"><\/script>');
		-->
		</script>
		<script type="text/javascript">
		<!--
			var config = {
				width: "100%", 
				height: "100%",
				params: { enableDebugging:"0" , disableContextMenu:true}
				
			};
			var u = new UnityObject2(config);

			jQuery(function() {

				var $missingScreen = jQuery("#unityPlayer").find(".missing");
				var $brokenScreen = jQuery("#unityPlayer").find(".broken");
				$missingScreen.hide();
				$brokenScreen.hide();
				
				u.observeProgress(function (progress) {
					switch(progress.pluginStatus) {
						case "broken":
							$brokenScreen.find("a").click(function (e) {
								e.stopPropagation();
								e.preventDefault();
								u.installPlugin();
								return false;
							});
							$brokenScreen.show();
						break;
						case "missing":
							$missingScreen.find("a").click(function (e) {
								e.stopPropagation();
								e.preventDefault();
								u.installPlugin();
								return false;
							});
							$missingScreen.show();
						break;
						case "installed":
							$missingScreen.remove();
						break;
						case "first":
						break;
					}
				});
				u.initPlugin(jQuery("#unityPlayer")[0], "<%=basePath %>assets/sys_resources/plugins/u3d/Viewer1.3.unity3d");
			});
		-->
		</script>
		<script type = "text/javascript"><!--
		var controllname="${pageContext.request.contextPath}/jkjc/jcPrjPointsController";
		
		var last_IconPoints = null;
		
		var p_SelectedItemIds = null;
		
		var p_isuserFocused = null;
		
		//
		function U_OnPlayerLoaded()
		{
			last_IconPoints = null;
			u.getUnity().SendMessage('UnityAPI','JS_GetIconPoints','');
		}
		
		function U_OnIconsTracking(){}

		function  U_OnSceneLoaded(){}
		
		function U_OnIconWasLoaded()
		{
			alert("Icon was loaded.");
		}
		
		function U_OnCameraSaved(s)
		{
			alert(s);
		}
		
        //当构件 被选中的  时候 触发 
        function U_OnElementsSelected(uid){
        
		} 
		
		//u.getunity().SendMessage("UnityAPI", "JS_SaveCamera", "");
		
		/* 隐藏指定构件 对应的图标
		function JS_UnloadIcon(uid){
		//alert("隐藏 指定的 图标");
		    u.getUnity().SendMessage('UnityAPI','JS_UnloadElementsIcon','');
		} */
		
		//影藏图标
		function U_OnCameraMoveBegan(){
			$('#pic', window.parent.document).empty();
		}
		
		//重新加载图标
		function U_OnCameraMoveEnded(){
			last_IconPoints = null;
			u.getUnity().SendMessage('UnityAPI','JS_GetIconPoints','');
		}
		
		function U_OnCameraMoving(){}
		
		/** 子页面向 父页面 上 传值 
		function getValue(obj,iframe){
			 var $obj = getId(obj);
			 var $iframe = getId(iframe);
			 var setobj = $iframe.contentWindow.document.getElementById("setValue");
			 //var setobj = window.frames[iframe].document.getElementById("setValue");
			 if(!setobj){return}
			 else{
			  $obj.value = setobj.value;
			  }
			  }
		*/
		
		function JS_GetIconPoints(IconPoints){
			//console.log(IconPoints);
			if (IconPoints == null)
			{
				return;
			}
			if (last_IconPoints == null)
			{
				last_IconPoints = IconPoints;
			}
			
			//console.log(p_SelectedItemIds);
			if (p_SelectedItemIds == null || p_isuserFocused == null)
			{
				return;
			}
			
			if (p_SelectedItemIds.length == 0 && !p_isuserFocused)
			{
				return;
			}
			
			var p_Points_Items_All = window.parent.getPoints_Items_All();
			
			var html = "";
			var kd = $('.content').width();
			var gd = $('#unityPlayer').height()-40; 
			var hosttype = $('#fromType', window.parent.document).val();
			$.each($.parseJSON(IconPoints), function(){
			
				//console.log(this);
			
				var count = 0;//测点图标计数，最多2
				for (var i = 0 ; i < p_Points_Items_All.length ; i++)
				{
					if (this.id == p_Points_Items_All[i].U3D_ELEMENT_ID)
					{
						//过滤筛选
						if (window.parent.IsContained(p_SelectedItemIds, p_Points_Items_All[i].JC_PRJ_ITEM_UID)
						|| (p_isuserFocused && window.parent.IsFocused(p_Points_Items_All[i].PRJ_POINTS_UID)))
						{
							//显示测点图标
							//根据测点距相机的距离，动态放缩测点图标的大小
							var px;
							var minpx = 5;
							var maxpx = 30;
							var dis = parseFloat(this.distance);
							//console.log(dis);
							var mindis = 100;
							var maxdis = 300;
							if (dis < mindis)
							{
								px = maxpx;
							}
							else if (dis > maxdis)
							{
								px = minpx;
							}
							else
							{
								px = parseInt(minpx + (1 - ((dis - mindis) / (maxdis - mindis))) * (maxpx - minpx));
							}
							var x;
							var y;
							if (count == 0)
							{
								x = this.screenX-(px/2);
								y = this.screenY-(px/2);
							}
							else if (count == 1)
							{
								x = this.screenX+(px/2);
								y = this.screenY-(px/2);
							}
							else
							{
								console.log("错误：测点有多于两个的图标！");
								continue;
							}
							
							var iconfile = p_Points_Items_All[i].ICON_FILE;
							if (p_Points_Items_All[i].IsWarning)
							{
								iconfile += "_red.png";	
							}
							else
							{
								iconfile += "_blue.png";	
							}
							
							html +="<div style='position:relative;'>";
							if(y>0&&y<gd&&x>0&&x<kd)
							{
		
							   	html += "<div onclick='showDetailed(this, detailzindex++);' style='position:absolute;z-index:10;background-color:#D3DEED;";
								html += "bottom:"+y+"px;left:"+x+"px;";
								html += "width:"+px+"px;height:"+px+"px;";
							   	html += "'";
							   	html += ">";
								html += "<img src=\""+iconfile+"\" title=\""+p_Points_Items_All[i].SHORT_NAME+"测点\"";
								html += " style='vertical-align:top;width:"+px+"px;height:"+px+"px;cursor:pointer;' ";
								html += "  /></div>";
								
								html += "<div class='detailed' onclick='settop(this, detailzindex++);' style='display:none;position:absolute;bottom:"+y+"px;left:"+x+"px;z-index:15;>";
								
								html += "<div style='color:white;'>";
								html +="<div class='detailed_head'>";
								html +="<span style='float:left;color:white;'>"+p_Points_Items_All[i].POINT_CODE+" "+ p_Points_Items_All[i].SHORT_NAME +"</span>";
								html +="<span style='float:right;font-size:20px;margin-top:-10px;'><i>  <a href='javascript:void(0);' onclick='showCd(this);'>×</a></i></span>";
								html +="</div>";
								html +="<ul class='detailed_ul'>";
								if(hosttype=='基坑'){
									if(p_Points_Items_All[i].datatype == "测斜"){
										html +="<li><div><span style='float:left;'>测斜类测点详细信息请看图表</span><span style='float:right;'></span></div></li>";
									}else{
										if(p_Points_Items_All[i].datatype == "水平")
										{
											html +="<li><div><span style='float:left;'>本次变化量</span><span style='float:right;'>"+(p_Points_Items_All[i].H_VALUE_DIFF==null||p_Points_Items_All[i].H_VALUE_DIFF==""?"无":(parseFloat(p_Points_Items_All[i].H_VALUE_DIFF).toFixed(2))+p_Points_Items_All[i].UNIT)+"</span></div></li>";
											html +="<li><div><span style='float:left;'>累计"+p_Points_Items_All[i].JC_TYPE_NAME+"</span><span style='float:right;'>"+(p_Points_Items_All[i].H_VALUE==null||p_Points_Items_All[i].H_VALUE==""?"无":(parseFloat(p_Points_Items_All[i].H_VALUE).toFixed(2))+p_Points_Items_All[i].UNIT)+"</span></div></li>";
										}
										else
										{
											html +="<li><div><span style='float:left;'>本次变化量</span><span style='float:right;'>"+(p_Points_Items_All[i].V_VALUE_DIFF==null||p_Points_Items_All[i].V_VALUE_DIFF==""?"无":(parseFloat(p_Points_Items_All[i].V_VALUE_DIFF).toFixed(2))+p_Points_Items_All[i].UNIT)+"</span></div></li>";
											html +="<li><div><span style='float:left;'>累计"+p_Points_Items_All[i].JC_TYPE_NAME+"</span><span style='float:right;'>"+(p_Points_Items_All[i].V_VALUE==null||p_Points_Items_All[i].V_VALUE==""?"无":(parseFloat(p_Points_Items_All[i].V_VALUE).toFixed(2))+p_Points_Items_All[i].UNIT)+"</span></div></li>";
										}
									}
		
								}else{
									html +="<li><div><span style='float:left;'>本次变化量</span><span style='float:right;'>"+(p_Points_Items_All[i].V_VALUE_DIFF==null||p_Points_Items_All[i].V_VALUE_DIFF==""?"无":parseFloat(p_Points_Items_All[i].V_VALUE_DIFF).toFixed(2))+p_Points_Items_All[i].UNIT+"</span></div></li>";
									html +="<li><div><span style='float:left;'>累计值</span><span style='float:right;'>"+(p_Points_Items_All[i].V_VALUE==null||p_Points_Items_All[i].V_VALUE==""?"无":parseFloat(p_Points_Items_All[i].V_VALUE).toFixed(2))+p_Points_Items_All[i].UNIT+"</span></div></li>";
									html +="<li><div><span style='float:left;'>孔口高程</span><span style='float:right;'>"+(p_Points_Items_All[i].INIT_HEIGHT==""?"未赋值":parseFloat(p_Points_Items_All[i].INIT_HEIGHT).toFixed(2))+p_Points_Items_All[i].UNIT+"</span></div></li>";
								}
		
								html +="</ul>";
								html +="<div  class='detailed_bottom'>";
								html +="<span style='float:left;font-size:20px;margin-top:-15px;margin-right:5px;'><i> <a  href='javascript:void(0);' onclick='switchPicture(\""+p_Points_Items_All[i].PRJ_POINTS_UID+"\");'><img id=\"tp"+p_Points_Items_All[i].PRJ_POINTS_UID+"\" width=\"18\" height=\"18\" src=\"${pageContext.request.contextPath }/assets/images/guanzhu/"+(p_Points_Items_All[i].IsFocused?"guanzhu":"guanzhu_kong")+".png\"></img></a> </i></span>&nbsp;&nbsp;";
								html +="<span style='float:left;font-size:20px;margin-top:-15px;'><i><a href='javascript:void(0);' onclick=\"showTab('"+p_Points_Items_All[i].PRJ_POINTS_UID+"','"+p_Points_Items_All[i].POINT_CODE+"','"+p_Points_Items_All[i].PRJ_POINT_ITEM_UID+"','"+p_Points_Items_All[i].JC_TYPE_UID+"','"+p_Points_Items_All[i].datatype+"');\"><img width=\"18\" height=\"18\" src=\"${pageContext.request.contextPath }/assets/images/guanzhu/pic.png\"></img></a></i></span>";
								html +="</div>";
								html +="</div>";

								html +="</div>";
								//html += "</iframe>";
							}
							html +="</div>";
							count++;
						}
					}
				}
											
			});  
			$('#pic', window.parent.document).html(html);
			
		}
		
	
			
		//获取到下拉框选择的 测点类型
		function UpdatePointIcons(selecteditemids, isuserfocused){
			p_SelectedItemIds = selecteditemids;
			p_isuserFocused = isuserfocused;
			$('#pic', window.parent.document).empty();
			JS_GetIconPoints(last_IconPoints);
		}
		
		$(function(){
		});
		
		--></script>
		<style type="text/css">
		<!--
		body {
			font-family: Helvetica, Verdana, Arial, sans-serif;
			background-color: white;
			color: black;
			text-align: center;
		}
		a:link, a:visited {
			color: #000;
		}
		a:active, a:hover {
			color: #666;
		}
		p.header {
			font-size: small;
		}
		p.header span {
			font-weight: bold;
		}
		p.footer {
			font-size: x-small;
		}
		div.content {
			margin: auto;
			width: 100%;
		}
		div.broken,
		div.missing {
			margin: auto;
			position: relative;
			top: 50%;
			width: 193px;
		}
		div.broken a,
		div.missing a {
			height: 63px;
			position: relative;
			top: -31px;
		}
		div.broken img,
		div.missing img {
			border-width: 0px;
		}
		div.broken {
			display: none;
		}
		div#unityPlayer {
			cursor: default;
			height: 500px;
			width: 100%;
		}
		-->
		</style>
	</head>
	<body>
		<div class="content">
			<div id="unityPlayer">
				<div class="missing">
					<a href="http://unity3d.com/webplayer/" title="Unity Web Player. Install now!">
						<img alt="Unity Web Player. Install now!" src="http://webplayer.unity3d.com/installation/getunity.png" width="193" height="63" />
					</a>
				</div>
				<div class="broken">
					<a href="http://unity3d.com/webplayer/" title="Unity Web Player. Install now! Restart your browser after install.">
						<img alt="Unity Web Player. Install now! Restart your browser after install." src="http://webplayer.unity3d.com/installation/getunityrestart.png" width="193" height="63" />
					</a>
				</div>
			</div>
		</div>
	</body>
</html>
