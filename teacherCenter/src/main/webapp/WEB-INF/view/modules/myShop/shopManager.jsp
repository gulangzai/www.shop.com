<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/commons/taglib.jsp" %>
<script type="text/javascript" src="${ctxStatic}/js/easyui/jquery-1.7.2.min.js"></script>  
<script type="text/javascript" src="${ctxStatic}/js/easyui/jquery.easyui.min.js"></script> 
<script type="text/javascript" src="${ctxStatic}/js/easyui/locale/easyui-lang-zh_CN.js"></script> 
<script type="text/javascript">
var ctx = '${ctx}'; 
</script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head> 
<title>商品管理</title>
</head>
<body>
<div>
      <div class="left">
           <div class="tree" style=""></div>
      </div> 
        
      <div class="right"></div>
</div>
</body>
<script type="text/javascript" src="${ctxStatic}/js/myShop/jquery.tree.js"></script>
<script type="text/javascript">
    var tree = $(".tree").tree({
    	
    });
    console.info(tree);
    var majorFactory = tree.majorFactory;
    var subjectFactory = tree.subjectFactory;
    console.info(majorFactory);
      
</script>
</html>