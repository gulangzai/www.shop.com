<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="./jquery-easyui-1.2.6/jquery-1.7.2.min.js"></script>
 <link rel="stylesheet" type="text/css" href="./jquery-easyui-1.2.6/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="./jquery-easyui-1.2.6/themes/icon.css">
<script type="text/javascript" src="./jquery-easyui-1.2.6/jquery.easyui.min.js"></script> 
<script type="text/javascript" src="./jquery-easyui-1.2.6/locale/easyui-lang-zh_CN.js"></script>
</head> 
<script type="text/javascript">
    $(function(){ 
    	$("a").click(function(){
    		var title = $(this).attr("title");
    		var url = $(this).attr("name");
    		
    		if($("#tt").tabs("exists",title)){ 
    		}else{
    			if(url){
        			url=url+'.jsp';
        			$("#tt").tabs("add",{
            			iconCls:"icon-add",
            			title:title,
            			closable:true,
            			content:craeteFrame(url)
            		}); 
        		}
    		}  
    	});
    	
    	function craeteFrame(url){
    		return '<iframe scolling="no" frameborder="0" src='+url+' style="width:100%;height:100%"></iframe>';
    	}
    })();
</script>
<body>
   <div id="main" class="easyui-layout"  style="width:100%;height:600px"> 
      <div region="north" title="工具栏" style="width:200px;height:100px"></div>
      
      
      <div region="west" title="左边" style="width:200px">
         <div class="easyui-accordion" title="导航栏">
             <div title="用户管理">
                <a id="userManager" class="easyui-linkbutton" href="#" title="用户管理" name="userManager" iconCls="icon-add">用户管理</a>
             </div>
             
             <div title="角色管理">
                <a id="charManager" class="easyui-linkbutton" href="#" title="角色管理" name="charManager">角色管理</a>
              </div>
             <div title="部门管理">部门管理</div>
         </div>
      </div>
      
       <div  region="center" id="tt" title="中部"  class="easyui-tabs" style="width:100%">
         
      </div>
      
   </div>
</body>
</html>