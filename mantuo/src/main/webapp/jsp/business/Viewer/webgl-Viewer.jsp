<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="/jsp/framework/common/taglibs.jsp" %>
<%@ taglib uri= "/tld/base.tld" prefix="app" %>


<!-- PAGE CONTENT BEGINS -->
		<div class="row">
			<div class="col-sm-12" id="webgl_viewer">
				<iframe id="webgl_viewer2_iframe" src="" width="100%" height="100%"></iframe>
			</div>
		</div>



<script type="text/javascript">

var scripts =[null];// ["jsp/business/project/JsProject.js"];

ace.load_ajax_scripts(scripts, function() {
	$(".page-content").css("padding-bottom",0);
	$("#webgl_viewer").css("height",window.innerHeight-103);
	var gdzxt_xm_id=$("#GDZXT_XM_ID").val();
	//alert(gdzxt_xm_id+"-----------------------");

	var puid = $('#project_uid',window.parent.document).val();
	if (puid == 39)
	{
		$("#webgl_viewer2_iframe").attr("src", "${ctx_site}/jsp/webgl_viewer2/LmvDbg.htm?id=3")
	}
	else
	{
		$("#webgl_viewer2_iframe").attr("src", "${ctx_site}/jsp/webgl_viewer/viewer3d.html?id=1")
	}
});

	
</script>