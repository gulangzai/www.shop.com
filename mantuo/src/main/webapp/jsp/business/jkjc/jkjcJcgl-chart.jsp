<!DOCTYPE html>
<html>
<head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%>

<title><fmt:message key="ui.title" /></title>

</head>
<body class="no-skin">

	<div class="main-container" id="main-container">
	
		<div class="page-content">
			<div class="row" >
				<div class="col-xs-12" id="u3d-content">

				
				</div>
			</div>
		</div>
	</div>
</body>
<%@ include file="/jsp/framework/common/basic_scripts.jsp"%>
<script type="text/javascript">
	$(function(){
		$('#u3d-content').load('jsp/business/Viewer/Viewer.jsp');
	});

</script>
</html>