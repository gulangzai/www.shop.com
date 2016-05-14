<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/jsp/framework/common/taglibs.jsp" %>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>

<!DOCTYPE html>
<html>
  <head>

	<base href="${ctx_site}/">
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
 	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
 	
    <title><fmt:message key="ui.title"/></title>
	<%@ include file="/jsp/framework/common/head.jsp"%>
  
  </head>
  
<body class="no-skin">
  		

		<!-- /section:basics/navbar.layout -->
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>
			<%@ include file="/jsp/framework/project/side_left.jsp"%>

			<!-- /section:basics/sidebar -->
			<div class="main-content" id="main">
				<!-- #section:basics/content.breadcrumbs -->
				<div class="breadcrumbs" id="breadcrumbs">
					<script type="text/javascript">
						try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
					</script>

					<!-- /section:basics/content.searchbox -->
				</div>

				<!-- /section:basics/content.breadcrumbs -->
				<div class="page-content">
					
					<%@ include file="/jsp/framework/common/setting.jsp"%>					
					
					<div class="page-content-area">
						<!-- ajax content goes here -->
					</div><!-- /.page-content-area -->
				</div><!-- /.page-content -->
			</div><!-- /.main-content -->

			

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
			</a>
		</div><!-- /.main-container -->

  
  <!-- basic scripts -->

	<%@ include file="/jsp/framework/common/basic_scripts.jsp"%>
 	
 		
  <script type="text/javascript">
     //If page has any inline scripts, it goes here
 
     
     function menutree_click(menuName,menuLocation,menuDesc,target){
    	 
    	 return true;
    	 
    	 if('enable_ajax_content' in ace) {
    		 alert('sd '+menuLocation);
    		 var options = {
    			  content_url:"'"+menuLocation+"'",
   	    		  default_url: 'homepage.html'//default url
   	    		  ,
   	    		  loading_icon: "fa-cog fa-2x blue"
    		 }
    		 ace.enable_ajax_content(jQuery, options);
    	 }
    	 
	 	
		//alert("menuName---"+menuName+"---menuLocation--"+menuLocation+"---menuDesc--"+menuDesc+"---target--"+target);
		if("#"!=menuLocation){
			//window.location.href="${ctx}/userController/temp";
			//window.location.href="${ctx}/jsp/framework/portal/main_files.jsp";
			window.location.href="${ctx}/"+menuLocation;
		}	
	 }
     
     jQuery(function($) {
		$('#main').empty();
		$('#main').load("jsp/business/xtgl/user-index.jsp");
     });

     function replaceMain(url){
 		$('#main').empty();
		$('#main').load(url);
     }
  </script>
  </body>
</html>
