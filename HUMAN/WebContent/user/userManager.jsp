<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="../tools/tag.jsp"%>

<script type="text/javascript">
$(function(){
	
	var flag="";
	var userlist = "../web/getPageUserList.user";
	var saveurl = "../web/saveUser.user";
	var searchurl = "../web/searchUser.user";
	var deleteurl="../web/deleteUser.user";
	var updateurl="../web/updateUser.user";
	var url;
	
	
	$("#datagrid").datagrid({
    	url:userlist,    
    	striped:true, 
    	columns:[[
    		{field:"id",title:"id",hidden:false,checkbox:true},
    		{field:"name",title:"姓名",width:200},
    		{field:"age",title:"年龄",width:200},
    		{field:"sex",title:"性别",width:200},
         	{field:"birthday",title:"生日",width:200,
    			 formatter:function(birthday){  
    				 if(birthday){
    						var year = birthday.year+1900;
    	 		        	var month = birthday.month+1;
    	 		        	var day = birthday.date;
    	 		        	var hours = birthday.hours;
    	 		        	var men = birthday.minutes;
    	 		        	var seconds = birthday.seconds;
    	 		        	return year+"-"+(month<10?'0'+month:month)+"-"+day;
    				 } 
    				 return birthday;
 		        }
    		} ,
            {field:"createTime",title:"注册时间",width:200,
    			 formatter:function(birthday){ 
    				 if(birthday){
    					    var year = birthday.year+1900;
    	  		        	var month = birthday.month+1;
    	  		        	var day = birthday.date;
    	  		        	var hours = birthday.hours;
    	  		        	var men = birthday.minutes;
    	  		        	var seconds = birthday.seconds;
    	  		            return year+"-"+(month<10?'0'+month:month)+"-"+day+"  "+hours+":"+men+":"+seconds;
    				 }  
  		        }	
    		}  
    	]],   
    	rownumbers:true,
    	pagination:true,
    	pageSize:10
    }); 
	
	$("#win_form").window({
		/* title:flag=="save"?"保存用户":"更新用户", */
		closed:true,
		width:300,
		height:250,
		maximizable:false,
		minimizable:false,
		collapsible:false,
		closable:true,
		modal:true
	});
	
	$("#addBtn").click(function(){ 
		flag = "save";
		$("#win_form").window({title:"添加用户"});
		$("#win_form").window("open",true);
	});
	
	//修改
	$("#editBtn").click(function(){
		$("#win_form").window({title:"修改用户"});
		var data = $("#datagrid").datagrid("getSelections");  
		if(data.length<=0){
			$.messager.alert("提示","请选择一行");
		}else{
			//alert(data[0].name+data[0].sex);
			flag = "update";  
			$("#win_form").window("open");
			$("#saveff").form("clear");  
			$("#saveff input#id").val(data[0].id);
			$("#saveff input#name").val(data[0].name);
		    $("#saveff input#sex").val(data[0].sex);
			$("#saveff #age").val(data[0].age);  
		}
	});
	
    $("#deleteBtn").click(function(){
    	var data = $("#datagrid").datagrid("getSelections");
    	
    	if(data.length>0){
    		$.messager.confirm("提示","删除用户",function(ok){
    			if(ok){
    				$.ajax({
                		url:deleteurl,
                		data:{id:data[0].id},
                		type:"post",
                		success:function(r){
                			$.messager.alert("提示","删除成功");
                			$("#datagrid").datagrid("reload",{});
                		}
                	}); 
    			} 
        	}); 
    	} else{
    		$.messager.alert("提示","请选择一条记录");
    	}
    });
	  
	 
	$("#saveBtn").click(function(){
		if(flag == "save"){
			$.messager.confirm("提示","添加用户",function(ok){
				if(ok){   
					$("#saveff").submit();   
				} 
			}); 
		} else if(flag=="update"){
			$.messager.confirm("提示","更新用户",function(ok){
				if(ok){ 
					flag="update"; 
					url=updateurl;
					$.ajax({
						type:"post",
						url:url,
						data:$("#saveff").serialize(),
						success:function(r){
							 var result = eval("("+r+")");
							  $.messager.alert("提示",result.message);
				          	  $("#saveff").form("clear");
				              $("#win_form").window("close");
				              //刷新数据表格
				              $("#datagrid").datagrid("reload",{});
						}
					});  
				} 
			}); 
		}
	});
	
	 $('#saveff').form({   
		   url:saveurl,
		   success:function(data){  
            var result = eval("("+data+")");
           // alert(result.status) ;
            if(result.status=="ok"){
          	  $.messager.alert("提示",result.message);
          	  $("#ff").form("clear");
                $("#win_form").window("close");
            	//刷新数据表格
	            $("#datagrid").datagrid("reload",{});
            }else{
            	if(flag=="save")
          	       $.messager.alert("提示","添加失败");
            	else{
            		  $.messager.alert("提示","更新失败");
            	}
            } 
           }   
     });
	 
	 //条件检索
	 $("#search").click(function(){
	 	 $.ajax({
			 type:"post",
			 url:searchurl,
			 data:$("#searchForm").serialize(),
			 success:function(result){  
				 if(result){
					 var results = eval("{("+result+")}"); 
					   $("#datagrid").datagrid("loadData",results);  
				 }else{
					 $.messager.alert("提示","无记录");
				 } 
			 }
		 });    
	 });
	  
})
       
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>
</head>
<body class="easyui-layout">
 
		<div id="toobar" region="north" title="操作区"   closed="false" closable="false" style="height:60px">
			<a href="#" id="addBtn" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a> 
			<a href="#" id="editBtn" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a> 
			<a href="#" id="deleteBtn" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		</div>
		<div region="south" title="south1" style="height:0px"></div> 
  		    <div  id="query" region="center"  title="查询区" closed="false" collapsible="true"  closable="false" style="padding:5px;background:#eee;">  
			     <div region="north" closed="false" style="height:30px">  
				     <form name="searchForm" id="searchForm" action="" onsubmit="return false" onkeydown="if(event.keyCode==13){return false;}">
					姓名 <input type="text" name="name" size=10 value="李益"> 
					性别<input type="text" name="sex" size=10> 
					年龄 <input type="text" name="age" size=10 value="22">
					<button class="easyui-linkbutton" id="search">检索</button>
				   </form>
			     </div>   
			  <div region="center">
			      <table id="datagrid"></table>
		      </div>  
		    
		</div>  
           
 
<!-- 	<div id="win_form" align="center">
		<form id="saveff">
			<input type="text" hidden="true" name="id" id="id"><br>
			用户名：<input type="text" name="name" id="name" value="李二"><br>
			年龄: <input type="text" name="age" id="age" value="30"><br>
			性别 <input type="text" name="sex" id="sex" value="男" /><br> 生日<input
				type="text" class="easyui-datetimebox" name="birthday" /><br> <span></span>
			<a href="#" id="saveBtn" class="easyui-linkbutton"
				iconCls="icon-save">保存</a> <a href="#" id="resetBtn"
				class="easyui-linkbutton" iconCls="icon-save">重置</a>
		</form>
	</div> -->

</body>
</html>