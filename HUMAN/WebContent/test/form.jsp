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
		   
	   });
	
})();

</script>
</head>
<body>
     <div id="win" class="easyui-window" title="登陆" style="width:500px;height:150px">
         <form action="">
             <table>
                <tr>
                  <td>用户名</td>
                  <td><input type="text" value="" class="easyui-validatebox" validType=minLength[4,6] required="true" missingMessage="请输入用户名" /></td>
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
                <td>城市</td>
                <td><select id="cc" name="dept" style="width:200px;">  
                      <option value="aa">aitem1</option>  
                      <option>bitem2</option>  
                      <option>bitem3</option>  
                      <option>ditem4</option>  
                      <option>eitem5</option>  
                     </select> </td>
                </tr>
             </table>
         </form>
     </div>
</body>
</html>