<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="./jquery-easyui-1.2.6/jquery-1.7.2.min.js"></script>
 <link rel="stylesheet" type="text/css" href="./jquery-easyui-1.2.6/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="./jquery-easyui-1.2.6/themes/icon.css">
<script type="text/javascript" src="./jquery-easyui-1.2.6/jquery.easyui.min.js"></script> 
<script type="text/javascript" src="./jquery-easyui-1.2.6/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
    $(function(){
    	  
    	$("#tb").datagrid({
    		title:"用戶管理",
    		url:"web/getPageUserList.user",
    		width:"100%", 
    		fitColumns:true,
    		remoteSort:false,
    		sortName:"age",
    		sortOrder:"asc",
    		loadMsg:"数据正在加载,请耐心等待",
    		rowStyler:function(index,data){
    			//console.info(index+data);
    			if(data.age>20)
    				return "background:red";
    		},
    		pageList:[5,10,15],
    		pagination:true,
    		columns:[[
    		  /*   {field:"id",title:"序",width:100}, */
    		    {field:"name",title:"姓名",width:100},
    		    {field:"age",title:"年齡",width:100},
    		    {field:"sex",title:"性别",width:100},
    		    {field:"birthday",title:"生日",width:200,
    		        formatter:function(date){
    		        	var year = date.year;
    		        	var month = date.month;
    		        	var day = date.date;
    		        	var hours = date.hours;
    		        	var men = date.minutes;
    		        	var seconds = date.seconds;
    		            return year+"-"+(month<10?'0'+month:month)+"-"+day+"  "+hours+":"+men+":"+seconds;
    		        }	
    		   }
    		]]
    	}); 
    });
</script>
</head>
<body>
     <table id="tb"></table>
</body>
</html>