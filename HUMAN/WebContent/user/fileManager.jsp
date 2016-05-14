<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../tools/tag.jsp"%>
<script type="text/javascript">
	$(function() {

		var url = "../web/upload.file";

		$("#saveBtn").click(function() {
			 
			$("#uploadForm").form("submit", {
				url : "../web/upload.file",
				success : function(result) { 
					var r = eval("(" + result + ")");
					$.messager.show({
						title : "提示",
						msg : r.message
					});
					//关闭
					$("#fileDialog").dialog("close");
				}
			}); 
		});

		$("#uploadBtn").click(function() {
			$("#fileDialog").dialog("open");
		});
		
		$("#cancelBtn").click(function(){
			$("#fileDialog").dialog("close");
		});

	})();
</script>
<title>文件管理</title>
</head>
<body>
<div> 
    <div id="mm"  style="width:160px"> 
       <a id="uploadBtn" class="easyui-linkbutton">上传文件</a>
    </div>
     
     <div id="fileDialog" title="上传文件" class="easyui-dialog" closed="true" style="width:350px;height:150px;">
       <form action="" method="post" id="uploadForm" enctype="multipart/form-data">  
           <table>
              <tr>
              <input type="text" name="userId" value="liyi"/>
                  <td>选择文件：</td>
                  <td><input type="file" name="fileName"/></td>
              </tr>  
              <tr align="center">
                  <td colspan="2"><a id="saveBtn" class="easyui-linkbutton" value="保存">上传</a>
                  <a id="cancelBtn" class="easyui-linkbutton" value="取消">取消</a></td>
              </tr>
           </table>
       </form>
    </div>
    
</div>
</body>
</html>