<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<%@ include file="/WEB-INF/jsp/commons/taglib.jsp"%>
<%@ include file="/WEB-INF/jsp/commons/commons.jspf"%>
<script type="text/javascript"> 
</script>
<html lang="en">
	<head> 
	<link rel="stylesheet" href="${ctx}/plugins/zTree/3.5/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="${ctxStatic}/js/jquery-1.5.1.min.js"></script>
	<script type="text/javascript" src="${ctx}/plugins/zTree/3.5/jquery.ztree.core-3.5.js"></script>
	  
	</head>
<body>
 <div class="container-fluid" id="main-container">


<table style="width:100%;" border="0">
	<tr>
		<td style="width:15%;" valign="top" bgcolor="#F9F9F9">
			<div style="width:15%;">
				<ul id="leftTree" class="ztree"></ul>
			</div>
		</td>
		 <td style="width:85%;" valign="top" >
		     <iframe name="treeFrame" id="treeFrame" frameborder="0" src="${ctx}/goodClass/listClass.do" style="margin:0 auto;width:100%;height:100%;"></iframe>
         </td>
	</tr>
</table>





 
	
</div>

    <!-- 引入 -->
		<script type="text/javascript">window.jQuery || document.write("<script src='${ctxStatic}/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script src="${ctxStatic}/js/bootstrap.min.js"></script>
		<script src="${ctxStatic}/js/ace-elements.min.js"></script>
		<!-- 引入 -->
		<script type="text/javascript">
			$(top.hangge());
			 
			var setting = {
				view: {
					showIcon: showIconForTree
				},
				data: {
					simpleData: {
						enable: true
					}
				},
				callback: {
					onClick:zTreeOnClick
				}	
			};
			
			//单击 节点 获取数据显示
			function zTreeOnClick (event, treeId, treeNode){
				var fClsId = treeNode.id; 
				$("#treeFrame")[0].contentWindow.flush(fClsId);
			}
			

			var zNodes = ${classes};
			
            //console.info(zNodes);
			function showIconForTree(treeId, treeNode) {
				return !treeNode.isParent;
			};

			$(document).ready(function(){
				$.fn.zTree.init($("#leftTree"), setting, zNodes);
			});
		 
			
			function treeFrameT(){
				var hmainT = document.getElementById("treeFrame");
				var bheightT = document.documentElement.clientHeight;
				hmainT .style.width = '100%';
				hmainT .style.height = (bheightT-7) + 'px';
			}
			 treeFrameT();
			window.onresize=function(){  
				 treeFrameT();
			};
		</script>
</body>
</html>