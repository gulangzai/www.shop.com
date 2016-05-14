<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div class="container">
<a data-target="#ratyService"  data-toggle="modal">ddd</a>
<div id="ratyService" class="modal hide fade">
           <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal">&times;</button>
              <h3>添加收藏: 我买过这个服务</h3>
            </div><!--Modal header-->
            <div class="modal-body">
             <div class="row">
            <div  class="span1"></div>
             <div class="span4 ">
              <h3>求评价(@^_^@) : </h3>
              </div>
              <div  class="span4" id="ratingstar"  ></div>
              <div  class="span1" id="target"></div>
           </div>
            </div><!--Modal body-->
            <div class="modal-footer">
              <a href="#" class="btn" data-dismiss="modal" >Close</a>
              <a href="#" class="btn btn-primary">Save changes</a>
            </div><!--Modal footer-->
          </div>
   <h1 class="page-header">对话框</h1>
   <a href="#login" data-toggle="modal" class="btn btn-primary">点击登录</a>
   <div class="modal hide" id="login">
      <div class="modal-header">
        <a href="#" class="close" data-dismiss="modal">*</a>
        <h4>用户登录</h4>
      </div>	
      <div class="modal-body">
        <form action="" class="form-horizontal">
          <div class="control-group">
            <label class="control-label">用户名</label>
            <div class="controls">
              <input type="text">
            </div>
          </div>
          
          <div class="control-group">
            <label class="control-label">密码</label>
            <div class="controls">
                 <input type="text">
            </div>
          </div>
        </form>
      </div>
       <div class="modal-footer">
         <button type="button" class="btn btn-primary">登录</button>
       </div>
   </div>
</div>
</body>
</html>