<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../tools/tag.jsp"%>
<script type="text/javascript">
     $(function(){
    	  var url = "../web/getRoleList.role";
    	  var saveUrl="../web/saveRole.role";
    	  
    	  $("#tt").tree({
    		  url:url,
    		   onSelect:function(node){
    			/*   alert(node.id);
    			  url=url+"?id="+node.id; 
    			  $("#tt").tree("getData",node.target); */
    		  },
    		  onContextMenu:function(e,node){
    			  e.preventDefault();
    		   $("#tt").tree("select",node.target); 
    			  $("#mm").menu("show",{
    				  left:e.pageX,
    				  top:e.pageY
    			  });
    		  },
    		  checkbox:true
    	  });  
    	  
    	  
    	  $("#cancelBtn").click(function(){
    		  $("#mydialog").dialog("close");
    	  });
    	  
    	 
    	  
    	  $("#saveBtn").click(function(){
    		  var node = $("#tt").tree("getSelected");
    		  $("#tt").tree("append",{
				  parent:node.target,
				  data:[{
					  text:$("#saveForm").find('input[name=roleName]').val(),
					  attributes:{
						  url:$("#saveForm").find('input[name=url]').val()
					  }
				  }]
			  });
    		  
    		  $.ajax({
    			  type:"post",
    			  url:saveUrl,
    			  dataType:"html",
    			 /*  data:$("#saveForm").serialize(), */
    			 data:{
    				roleName: $("#saveForm").find('input[name=roleName]').val(),
    				 url:$("#saveForm").find('input[name=url]').val(),
    				 pid:node.id
    			 },
    			  success:function(result){
    				  var r = eval("("+result+")");
    				  $.messager.show({
    					 title:"提示信息",
    					 msg:r.message
    				  });  
    				  //刷新父节点
    				  $("#tt").tree("reload",node.parent().target);
    			  }
    		  });
    		  /* 关闭弹框 */
    		  $("#mydialog").dialog("close");
    		  //刷新节点
    		 /*  $("#tt").tree("reload",node.target); */
    	  });
    	  
    	  $("#updateBtn").click(function(){
     		// alert("----"); 
    		  $.messager.confirm("提示","确定更新",function(r){
    			  var node = $("#tt").tree("getSelected");
    			  var roleId = $("#updateForm").find('input[name=roleId]'); 
    			  var pid = $("#updateForm").find('input[name=pid]'); 
    			  roleId.val(node.id); 
    			  var fatherNode = $("#tt").tree("getParent",node.target);
    			  pid.val(fatherNode.id);
    			 // alert(roleId.val());
    	   	    var nextNode = $(node.target.nextSbiling);
    	   	    var lastNode =$(node.target.previousSbiling);  
    			 // alert(nextNode.text);
    			  if(r){
    				  //前台刷新
    	    		  $("#tt").tree("remove",node.target);
    	    		 /*    $("#tt").tree("insert",{
    	    		    	before:nextNode,
    	    		    	after:lastNode,
    	    		    	data:{
    	    		    		 id:node.id,
    	    	    			 text:$("#updateForm").find('input[name=roleName]').val()
    	    		    	}});  */
    	    		  
    				  //后台刷新
    	    		  $("#updateForm").form("submit",{
    	    			  url:"../web/updateRole.role",
    	    			  success:function(result){
    	    				  $("#tt").tree("reload",fatherNode.target);
    	    				  var r = eval("("+result+")");
    	    			      $.messager.show({
    	    			    	  title:"提示",
    	    			    	  msg:r.message
    	    			      });
    	    			  }
    	    		  });  
    			  }
    		  }); 
    		  //关闭
			  $("#updatedialog").dialog("close");
    	  }); 
     })();
     
     
      function append(){
		   $("#mydialog").dialog("open",true); 
	  }
	  
	  function update(){ 
		  var node = $("#tt").tree("getSelected");
		  var roleName= $("#updateForm").find('input[name=roleName]'); 
		  roleName.val(node.text);
		  $("#updatedialog").dialog("open");
	  }
	  
	  function removeRole(){ 
		  var node = $("#tt").tree("getSelected");
		  $.messager.confirm("提示","确定删除"+node.text,function(r){
			  if(r){
				  $("#tt").tree("remove",node.target); 
				  //后台同步删除
				  $.ajax({
					  type:"post",
					  dataType:"html",
					  url:"../web/deleteRole.role",
					  data:{
						  roleId:node.id
					  },
					  success:function(result){
						  var r = eval("("+result+")");
						  $.messager.show({
							  title:"提示",
							  msg:r.message
						  });
					  }
				  });
				  
				  //刷新节点
				  $("#tt").tree("reload",node.parent.target);
				  
			
			  }
		  });
	  }
	  
</script>
<title>权限管理</title>
</head>
<body>
<div>
    <ul id="tt"></ul> 
    <div class="easyui-menu" id="mm"  style="width:160px">
       <div onclick="append()">添加</div>
       <div onclick="update()">修改</div>
       <div onclick="removeRole()">删除</div>
    </div>
    
    <div id="mydialog" class="easyui-dialog" closed="true" style="width:250px">
       <form action="" method="post" id="saveForm">
           <table>
              <tr>
                  <td>权限名称：</td>
                  <td><input type="text" name="roleName"/></td>
              </tr>
              <tr>
                  <td>url</td><td><input type="text" name="url" value="www.baidu.com"></td>
              </tr>
              <tr>    
                  <td>父节点</td><td><input type="hidden" name="pid"/></td> 
              </tr>
              <tr align="center">
                  <td colspan="2"><a id="saveBtn" class="easyui-linkbutton" value="保存">保存</a>
                  <a id="cancelBtn" class="easyui-linkbutton" value="取消">取消</a></td>
              </tr>
           </table>
       </form>
    </div>
    
     <div id="updatedialog" title="更新面板" class="easyui-dialog" closed="true" style="width:250px">
       <form action="" method="post" id="updateForm"> 
       <input name="roleId" type="hidden"  value="">
           <table>
              <tr>
                  <td>权限名称：</td>
                  <td><input type="text" name="roleName"/></td>
              </tr>
              <tr>
                  <td>url</td><td><input type="text" name="url" value="www.baidu.com"></td>
              </tr>
              <tr>    
                  <td>父节点</td><td><input type="hidden" name="pid"/></td> 
              </tr>
              <tr align="center">
                  <td colspan="2"><a id="updateBtn" class="easyui-linkbutton" value="保存">保存</a>
                  <a id="cancelBtn" class="easyui-linkbutton" value="取消">取消</a></td>
              </tr>
           </table>
       </form>
    </div>
    
</div>
</body>
</html>