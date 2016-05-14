<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<%@ include file="../tools/tag.jsp"%>

<title>人事管理系统</title>
<script type="text/javascript">
				$(function(){ 
					
					$("#layout").layout("collapse","north");
					
					$("a[title]").click(function(){
					
						var src = $(this).attr("title");
						var title = $(this).html(); 
						if(!$("#tt").tabs("exists",title)){
							$("#tt").tabs("add",{
								title:title
								,content:"<iframe frameborder=0 src="+src+" style='width:100%;height:100%'></iframe>"
								,closable:true
							});
						}else{
							 $("#tt").tabs("select",title);
						}
						 
					});
					
					 
				})();
   
</script>
</head>
<body id="layout" class="easyui-layout" style="width:100%;heigth:100%"> 
   
     <div id="north" region="north" title="导航栏" style="height:100px"></div> 
       
     <div region="west" title="主菜单"  collapsed=false style="width:200px">
     
		 <div id="menu" class="easyui-accordion" fit=true>
 
                <div title="系统管理" class="ul">
				   <div class="li"> <a title="../user/userManager.jsp" >工作流模型管理</a></div> 
				   <div class="li"> <a title="../user/userManager.jsp" >工作流管理</a></div>  
				   <div class="li"> <a title="../user/userManager.jsp" >报表管理</a></div> 
   				   <div class="li"> <a title="../user/userManager.jsp" >菜单信息</a></div>  
   				   <div class="li"> <a title="../user/userManager.jsp" >数据备份与恢复</a></div>   
   				   <div class="li"> <a title="../user/userManager.jsp" >外部链接</a></div> 
                   <div class="li"> <a title="../user/userManager.jsp" >日志信息</a></div>
				</div>
				
				<div title="部门管理" align="center">
				   <div class="li"> <a title="../user/userManager.jsp" >岗位信息</a>  </div> 
				   <div class="li"> <a title="../user/userManager.jsp" >部门信息</a>  </div> 
				</div>
				 
                <div title="人事管理" align="center">
				   <div class="li"> <a title="../user/userManager.jsp" >员工资料</a> </div> 
				</div>
				
				<div title="文档管理" align="center">
				   <div class="li"> <a title="../user/userManager.jsp" >文档信息</a>  </div> 
				   <div class="li"> <a title="../user/userManager.jsp" >文章管理</a>  </div> 
				</div>
				
				<div title="用户操作" align="center">
				   <div class="li"> <a title="../user/userManager.jsp" >电子邮件</a>  </div> 
 				</div>
				
				
				<div title="系统管理" align="center">
				   <div class="li"> <a title="../user/userManager.jsp" >工作流模型管理</a></div> 
				   <div class="li"> <a title="../user/userManager.jsp" >工作流管理</a></div>  
				   <div class="li"> <a title="../user/userManager.jsp" >报表管理</a></div> 
   				   <div class="li"> <a title="../user/userManager.jsp" >菜单信息</a></div>  
   				   <div class="li"> <a title="../user/userManager.jsp" >数据备份与恢复</a></div>   
   				   <div class="li"> <a title="../user/userManager.jsp" >外部链接</a></div> 
                   <div class="li"> <a title="../user/userManager.jsp" >日志信息</a></div>
				</div>
				
				
 
				<div title="用户管理" align="center"> 
				   <div class="li">
				      <a title="../user/userManager.jsp" >用户列表</a>
				   </div> 
				</div>
				
				<div title="权限管理">
				    <a title="../user/roleManager.jsp">权限列表</a>
				</div>
				
				<div title="文件管理">
				    <a title="../user/fileManager.jsp">文件上传</a>
				</div>
				 
			</div>  
     </div>
     
     <div region="center" id="tt" class="easyui-tabs"></div>   
     
</body>
</html>