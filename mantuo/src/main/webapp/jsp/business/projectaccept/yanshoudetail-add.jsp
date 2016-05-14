<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/framework/common/taglibs.jsp"%>
<%@ taglib uri="/tld/base.tld" prefix="app"%> 
<title><fmt:message key="ui.title" /></title>
<style>

.setposition{
position: absolute;
left:40%;
top:13%;
Z-index:10;
}
</style>
<body>

<div class="modal-dialog setposition width-40 height-auto" style="background:rgb(239,243,248)">  
    <div clsss="container">  
	     <div class="modal-header">
	         <div  style="cursor: pointer;">
				<a id="confirm" style="float:right;margin:6px 4px;" >
					<i class="ace-icon fa fa-times bigger-100"></i>
				</a>
		     </div>
	          <h4>添加验收详情</h4>
	     </div>
	     <div class="modal-body">
		    <form action="" class="form-horizontal" id="yanshouFormAdd">
		        <div class="control-group">
			        <label class="control-label col-sm-4 no-padding-right">分项工程名称：<span class="required-indicator">*</span></label>
			        <div class="controls col-sm-8 ">
			          <input type="text" datatype="*" nullmsg="请填写分项工程名称" name="DETAIL_NAME1" fieldname="DETAIL_NAME" class="col-xs-11 col-sm-11"/>
			        </div>
		       </div>
		       &nbsp;
		        <div class="control-group">
			         <label class="control-label col-sm-4 no-padding-right">检验批数量：<span class="required-indicator">*</span></label>
			         <div class="controls col-sm-8">
			             <input type="text" datatype="/^[\d]{1,8}(\.[\d]{1,2})?$/" nullmsg="请填写检验批数量" errormsg="可以填最多8位整数加最多2位小数" name="DETAIL_NUMS1"  fieldname="DETAIL_NUMS" class="col-xs-11 col-sm-11"/>
			         </div>
                </div>
                &nbsp;
                 <div class="control-group">
			         <label class="control-label col-sm-4 no-padding-right">检验批部位、区段：<span class="required-indicator">*</span></label>
			         <div class="controls col-sm-8">
			             <input type="text" datatype="*"  nullmsg="请填写检验批部位、区段" name="DETAIL_BUWEI1" fieldname="DETAIL_BUWEI" class="col-xs-11 col-sm-11"/>
			         </div>
			      </div>
			      &nbsp;
			       <div class="control-group">
			         <label class="control-label col-sm-4 no-padding-right">施工单位检查结果：<span class="required-indicator">*</span></label>
			         <div class="controls col-sm-8">
			             <textarea id="DETAIL_JCJG1" rows="3"  datatype="*" nullmsg="请填写施工单位检查结果" name="DETAIL_JCJG1" fieldname="DETAIL_JCJG" class="col-xs-11 col-sm-11"></textarea>
			         </div>
			      </div>
			      &nbsp;
			         <div class="control-group">
			         <label class="control-label col-sm-4 no-padding-right">监理单位验收结论：<span class="required-indicator">*</span></label>
				         <div class="controls col-sm-8">
				             <textarea id="DETAIL_YSJL1" rows="3" datatype="*" nullmsg="请填写监理单位验收结论" name="DETAIL_YSJL1" fieldname="DETAIL_YSJL" class="col-xs-11 col-sm-11"></textarea>
				         </div>
			         </div>
		    </form> 
	     </div> 
	     <div class="modal-footer">
	        <button class="btn btn-1 btn-sms btn-round save" type="button">保存</button>
	     </div> 
	 </div>
</div>
  <%@ include file="/jsp/framework/common/basic_scripts.jsp"%>
  <script type="text/javascript">
     var scripts=[null];
     validform=FormValid.validbyformid(yanshouFormAdd);
     ace.load_ajax_scripts(scripts,function(){
    	 $(".save").click(function(){
    	 if(validform.check()){
    		 var i = $("#yanshouDetailtable tr").length;
    		 var str = $("input[name='DETAIL_NAME1']").val();
    		 var DETAIL_NAME = $("input[name='DETAIL_NAME1']").val();
    		 var DETAIL_NUMS =  $("input[name='DETAIL_NUMS1']").val(); 
    		 var DETAIL_BUWEI = $("input[name='DETAIL_BUWEI1']").val(); 
    		 var JCJG = $("#DETAIL_JCJG1").val(); 
    		 var YSJL = $("#DETAIL_YSJL1").val();
    		 
    		 var $tr = $("<tr style='height:auto'></tr>");  
    		 $tr
    		 .append($("<td class='col-sm-2' align='center' style='padding:26px;'></td>").append($("<input type='text' size='10' class='col-sm-12 col-xs-12' style='display:block' readOnly='readOnly' name='XUHAO' fieldname='XUHAO' value="+i+"></input>")))
    		 .append($("<td class='col-sm-2' style='padding:26px;'></td>").append($("<input type='text' size='20' maxlength='30'  class='col-sm-12 col-xs-12' name='DETAIL_NAME' fieldname='DETAIL_NAME' value="+DETAIL_NAME+"></input>")))
    		 .append($("<td class='col-sm-2' style='padding:26px;'></td>").append($("<input type='text' size='20' maxlength='30' class='col-sm-12 col-xs-12' datatype='/^[\\d]{1,8}(\\.[\\d]{1,2})?$/' errormsg='可以填最多8位整数加最多2位小数' name='DETAIL_NUMS' fieldname='DETAIL_NUMS' value="+DETAIL_NUMS+"></input>")))
    		 .append($("<td class='col-sm-2' style='padding:26px;'></td>").append($("<input type='text'  class='col-sm-12 col-xs-12'  name='DETAIL_BUWEI' fieldname='DETAIL_BUWEI' value="+DETAIL_BUWEI+"></input>")))
    		 .append($("<td class='col-sm-2'></td>").append($("<textarea rows='2'   class='col-sm-12 col-xs-12'   multiline='true'  name='DETAIL_JCJG' fieldname='DETAIL_JCJG'>"+JCJG+"</textarea>")))
    		 .append($("<td class='col-sm-2'></td>").append($("<textarea  rows='2'  class='col-sm-12 col-xs-12'  multiline='true'    name='DETAIL_YSJL' fieldname='DETAIL_YSJL'>"+YSJL+"</textarea>")));
    		 
    		 console.info($tr);	
    		 $("#tt").append($tr);
    	     m_close();
    	   }
    	 });
    	 $("#confirm").click(function(){
    		   /** 修改当前页面框的div属性值**/
    		 m_close();
    	 });
     }); 
     function m_close(){
    	 $("#XMZK-XMMC").css("display","none");
			$("#XMZK-XMMC").attr("aria-hidden","true");
			$("#XMZK-XMMC").attr("class","modal");
			/**页面框的背景 样式去除**/
			$(".modal-backdrop").attr("class",""); 
     };
  </script>
</body>
</html>