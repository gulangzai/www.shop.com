<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>spring 表单</title> 
<script type="text/javascript" src="./jquery-easyui-1.2.6/jquery-1.7.2.min.js"></script>
 <link rel="stylesheet" type="text/css" href="./jquery-easyui-1.2.6/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="./jquery-easyui-1.2.6/themes/icon.css">
<script type="text/javascript" src="./jquery-easyui-1.2.6/jquery.easyui.min.js"></script> 
<script type="text/javascript" src="./jquery-easyui-1.2.6/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
$.extend($.fn.validatebox.defaults.rules, {   
    minLength: {   
        validator: function(value, param){   
            return value.length >= param[0]&&value.length<=param[1];   
        },   
        message: '请输入至少  {0} 位数字. 至多  {1} 位数字'  
    }   
});  

$(function(){
	   $("#dd").datebox({
		   disabled:false
		   ,panelWidth:200
		   ,editable:false
	   });
	   
	   $("#cc").combobox({
		   url:'web/getUserList.user'
		   ,valueField:'id'
		   ,textField:"name"
	   });
	   
	   /*  $("#subm").click(function(){
		    $.ajax({
			    type:'post'
				,dataType:'text'
				,data:$("#form").serialize()
				 ,url:"web/saveUser.user"
				 ,success:function(aresult){ 
					 var result = $.parseJSON(aresult); 
					 $.messager.show({
						 title:result.status
						 ,msg:result.message
					 });
				 }
		         ,error:function(){
		        	 
		         }
		   });  
		  /*  $("#form").form("submit",{ 
			   url:"web/saveUser.user",
			   onSubmit:function(){
				   if(!$("form").form("validate")){
					   $.messager.show({
						   title:"提示信息"
						   ,msg:"验证没有"
					   });
					   return false;
				   }
			   },
			   success:function(aresult){
				  var result  = $.parseJSON(aresult);
				  alert(result);
				  $.messager.show({
					  title:result.status,
					  msg:result.message
				  });
			   }
		   }); */
		   
		   $("#form").form({ 
			   url:"web/saveUser.user",
			   onSubmit:function(){
				   if(!$("form").form("validate")){
					   $.messager.show({
						   title:"提示信息"
						   ,msg:"验证没有通过"
					   });
					   return false;
				   }
			   },
			   success:function(aresult){
				  var result  = $.parseJSON(aresult);
				  //alert(result);
				  $.messager.show({
					  title:result.status,
					  msg:result.message
				  });
			   }
		   });
		   
		 
	       $("#subm").click(function(){
			   $("#form").submit();
		   });  
		   
			   $("#form").find("input").on("keyup",function(event){
				   if(event.keyCode==13){
					   $("#form").submit();
				   }
			   });
		  
		    
})();

</script>
</head>

<body>
     <div id="win" class="easyui-window" title="登陆" style="width:500px;height:350px">
         <form id="form" action="">
             <table>
                <tr>
                  <td>用户名</td>
                  <td><input type="text" name="name" value="" class="easyui-validatebox" validType=minLength[4,6] required="true" missingMessage="请输入用户名" /></td>
                </tr>
                <tr>
                   <td>密码</td>
                   <td><input type="password" value=""/></td>
                </tr>
                <tr>
                <td>日期：</td>
                <td><input type="text" id="dd" class="easyui-datebox"/></td>
                </tr>
                <tr>
                <td>年龄</td>
                <td><input type="text" id="ages" name="age"/></td>
                </tr>
                <tr>
                <td>城市</td>
                <td><select id="cc" name="user" style="width:200px;"></select> </td>
                </tr>
                
                <tr align="center"><td colspan="2">
                    <a id="subm" class="easyui-linkbutton">提交</a>
                    
                </td></tr>
             </table>
         </form>
     </div>
</body>
</html>