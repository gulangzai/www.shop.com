(function($){
	
	var tableContent;
	var table;
	var shopTime;
	var shopType;
	var majorId;
	var subjectId;
	var classesId;
	 
	
	$(".change").change(function(){
		flush(1, getParameter());
	});
	
	$("#courseMajor").change(function(){ 
	 	   var majorId = $(this).children('option:selected').val();
	 		$.ajax({
	     		type:'post',
	     		url:ctx+'/courseOpen/getClassesByMajor.do',
	     		data:{
	     			majorId:majorId,   
	 		    },
	     		async:true,
	     		success:function(sR){ 
	     			 //console.info(sR.data);
	      			 $("#classes").empty();
	      			 $("#classes").append('<option value="请选择">请选择</option>');
	      			 for(var i=0;i<sR.data.length;i++){
	      				 var option=$('<option value="'+sR.data[i].classesId+'">'+sR.data[i].classesName+'</option>');
	      				 $("#classes").append(option);
	      			 } 
	     		}
	     	});  
	 	}); 
	
	/*	$("#courseMajor").change(function(){ 
 	   var majorId = $(this).children('option:selected').val();
 		$.ajax({
     		type:'post',
     		url:ctx+'/courseOpen/getSubjectByMajorId.do',
     		data:{
     			majorId:majorId,   
 		    },
     		async:true,
     		success:function(sR){ 
     			 $("#subject").empty();
     			 $("#subject").append('<option value="请选择">请选择</option>');
     			 for(var i=0;i<sR.data.length;i++){
     				 var option=$('<option value="'+sR.data[i].subjectId+'">'+sR.data[i].subjectName+'</option>');
     				 $("#subject").append(option);
     			 } 
     		}
     	});  
 	}); 
 	$("#subject").change(function(){ 
  	   var subjectId = $(this).children('option:selected').val();
  		$.ajax({
      		type:'post',
      		url:ctx+'/courseOpen/getClasses.do',
      		data:{ 
      			subjectId:subjectId
  		    },
      		async:true,
      		success:function(sR){ 
      			console.info(sR.data);
      			 $("#classes").empty();
      			 $("#classes").append('<option value="请选择">请选择</option>');
      			 for(var i=0;i<sR.data.length;i++){
      				 var option=$('<option value="'+sR.data[i].classesId+'">'+sR.data[i].classesName+'</option>');
      				 $("#classes").append(option);
      			 } 
      		}
      	}); 
  	}); */
 	
 	function getParameter(){
 		shopTime=$("#shopTime").val()=='请选择'?'':$("#shopTime").val();
		shopType=$("#shopType").val()=='请选择'?'':$("#shopType").val();
		majorId=$("#courseMajor").val()=='请选择'?'':$("#courseMajor").val();
		subjectId=$("#subject").val()=='请选择'?'':$("#subject").val();
		classesId=$("#classes").val()=='请选择'?'':$("#classes").val(); 
 	}
	//搜索
	$("#search").click(function(){ 
		//var keyword = $("input[name='search']").val();//alert(); 
		//table.keyword = keyword;  
		flush(1, getParameter());
	});
	
	//定时器 显示进度 
	function showProgress(){ 
		    var value = $('#p').progressbar('getValue');  
		    value += Math.floor(Math.random() * 10);
		    $('#p').progressbar('setValue', value); 
	}
	
	//导出excel
	var t1;
	$("#exportExcel").click(function(){
		$(".easyui-progressbar").show(); 
		t1 = window.setInterval(showProgress,1000); 
		$.ajax({
    		type:'post',
    		url:ctx+'/myStudent/exportExcel.do',
    		data:{
    			majorId:table.majorId,  
		        headColor:table.headColor
		    },
    		async:true,
    		success:function(sR){
    			if(sR.message=='FAIL'){
    				alert('生成失败');
    			}else{   
    				window.clearInterval(t1); 
    				$(".easyui-progressbar").hide();
    				window.location.href=ctx+"/myStudent/downloadExcel.do";
    			}
    		}
    	});   
	});
	
	var bb = [];
	 
	$(".colorOpe").click(function(){   
		 
	    $(".colorOpe").each(function(index){
	    	$(this).css("background-image",""); 
	    	   //console.info($(this));
	    });
	    $(this).css("background-image","url("+ctx+"/static/images/studentList/u840.png)");
	 
		
    	for(var k=0;k<table.columns.length;k++){ 
    		bb[k]=table.columns[k].title;
    	}
    	table.headColor=$(this).attr('data');
    	tableContent.empty();
    	table.addHeader(bb); 
    	flush(1);
	});
	
	
    $.fn.table = function(options){  
    	tableContent = this;
    	table = new Table(options); 
    	for(var k=0;k<table.columns.length;k++){ 
    		bb[k]=table.columns[k].title;
    	}
    	table.addHeader(bb); 
        flush(1, getParameter());
    	return table;
    };
    
    //表格对象
    function Table(options){
    	this.columns = options.columns; 
    	this.trNum;
    	this.header = [];   //表头数据
    	this.allData=[];    //
    	this.rowData=[]; 
    	this.contentData=[];//内容数据
    	this.$lis=[];       //分页标签
    	this.currentPage=1; //选中的当前页
    	this.allPageNum;    //总共的页数
    	this.allRecodeNum;  //总的数据量
    	this.keyword;       //搜索的关键词 
    }
    
    //获取数据
    function getRemoteData(currentPage,keyword){
    	$.ajax({
    		type:'post',
    		url:ctx+'/courseOpen/getShop.do',
    		async:false,
    		data:{  
    			currentPage:currentPage,
    			shopTime:shopTime,
    			shopType:shopType,
    			majorId:majorId,
    			subjectId:subjectId,
    			classesId:classesId
    		},  
    		success:function(sR){
    			if(sR.data!=null){
    				var dd=sR.data.obj;
        			for(var i=0;i<dd.length;i++){ 
        			    var aa = [];
        			    aa[0]=dd[i].shopId;
        			    aa[1]=(parseInt(currentPage)-1)*5+i+1;
        			    aa[2]=dd[i].majorName;
        			    aa[3]=dd[i].subjectName;
        			    aa[4]=dd[i].classesName;
        			    aa[5]=dd[i].shopType;
        			    aa[6]=dd[i].shopName;  
        			    aa[7]=dd[i].majorId;
        			    var shop = new Shop(dd[i].shopId,dd[i].majorName,dd[i].subjectName,dd[i].classesName,dd[i].shopType,dd[i].shopName,dd[i].majorId);
        			    addShopData(shop);
        			    table.addContentTr(aa);
        			}
        			table.allRecodeNum = sR.data.allRecodeNum; 
    			} 
    		}
    	});
    }
    
    function Shop(shopId,majorName,subjectName,classesName,shopType,shopName,majorId){
    	this.shopId=shopId;
    	this.majorName=majorName;
    	this.subjectName=subjectName;
    	this.classesName=classesName;
    	this.shopType=shopType;
    	this.shopName=shopName;
    	this.majorId=majorId;
    }
    
    //添加shop数据到集合里
    function addShopData(shop){
    	var flag=true;
    	for(var i=0;i<shops.length;i++){
    		if(shops[i].shopId==shop.shopId){
    			shops.splice(shops.indexOf(shops[i]),1);
    			shops.push(shop);
    			flag=false;
    			break;
    		}
    	}  
    	if(flag){
    		shops.push(shop);
    	}
    	console.info(shops+shops.length);
    }
    
    //添加表头
    function addHeader(){ 
    	table.addHeader(bb); 
    	for(var ii=0;ii<table.header.length;ii++){
    		tableContent.append(table.header[ii]); 
    	}
    }
    
    //添加数据
    function addData(){ 
    	for(var i=0;i<table.contentData.length;i++){ 
    		tableContent.append(table.contentData[i]); 
    	} 
    }
    
    Table.prototype.check = function(trThPageNum,shopId){
    	//console.info(studentId);
    	if($('input[name="studentIdChecked'+trThPageNum+shopId+'"]:checkbox').is(":checked")){
    		//没有就添加
    		/*if(choiceShops.indexOf(shopId)==-1)
    			choiceShops.push(shopId);*/
    		var flag = true;
    		for(var i=0;i<choiceShops.length;i++){
        		if(choiceShops[i].shopId==shopId){
        			flag=false;
        			break;
        		}
        	}
    		if(flag){
    			for(var j=0;j<shops.length;j++){
    				if(shops[j].shopId==shopId){
    					choiceShops.push(shops[j]);
    					break;
    				} 
    			} 
    		} 
    	}else{
    		//有就移除
    		for(var i=0;i<choiceShops.length;i++){
        		if(choiceShops[i].shopId==shopId){
        			choiceShops.splice(choiceShops.indexOf(choiceShops[i]),1);  
        		}
        	}
    		/*if(choiceShops.indexOf(shopId)!=-1)
    			choiceShops.splice(choiceShops.indexOf(shopId),1);*/
    	} 
    	//console.info('choiceShops'+choiceShops);
    }
    
 
    Tr.prototype.addTh = function(thData){
    	this.rowData[this.rowData.length] = new Th(thData);
    }
    
    Tr.prototype.addTd = function(tdData){
    	this.rowData[this.rowData.length] = new Td(tdData);
    } 
    
    Table.prototype.addOperate = function($tr,trData){
    	var $th = $('<td style="width:350px;"><a href="javascript:void(0)" onclick="studentDetail(\''+trData[2]+'\')">查看详情</a>'
    			+'<a href="javascript:void(0)" onclick="table.deleteStudentByPhone('+trData[3]+')">删除</a>'
    			+'<a href="javascript:void(0)" onclick="table.resetPassword('+trData[3]+')">重置密码</a></td>');
    	$tr.append($th);
    }
    
    
    Table.prototype.addTr = function(trData){
    	this.allData[this.allData.length] = new Tr(trData);
    }
    
    Table.prototype.addHeader = function(trData){
    	this.header=[];
    	this.header[this.header.length] = new TrTh(trData);
    }
    
    Table.prototype.addContentTr = function(trData){ 
    	var tr = new Tr(trData); 
    	//this.addOperate(tr,trData);
    	this.contentData[this.contentData.length] = tr;
    }
    
    //分页下标(当前页,总共的记录条数)
    function addPageTool(currentPage,allRecodeNum){ 
    	tableContent.next().remove();
    	var $ulDiv = $('<div style="magin-top:0px;"></div>'); 
    	$ulDiv.append(table.createUI(currentPage,allRecodeNum));
    	tableContent.after($ulDiv);  
    	return $ulDiv;
    }
    
    function RenderLi(btnLabel,currentPage){
    	var $li = $('<li onclick="table.myClick(\''+btnLabel+'\');">'+currentPage+'</li>'); 
    	return $li;
    }
    
    Table.prototype.createUI = function(currentPage,allRecodeNum){ 
    	this.currentPage=currentPage;
    	var allPageNum = (parseInt(allRecodeNum%5)==0)?parseInt(allRecodeNum/5):parseInt(allRecodeNum/5)+1;
    	//alert(allRecodeNum+'-'+allPageNum);
    	this.allPageNum=allPageNum;
    	 //alert(allRecodeNum+'总共页数'+allPageNum);
    	var $ul = $("<ul class='pages'></ul>"); 
    	var $li= new RenderLi('first',"首页");
    	$ul.append($li);
    	$li  = new RenderLi("last","上一页")
    	$ul.append($li);	
    	this.$lis[this.$lis.length]=$li;
    	var startLi = 1;
    	var endLi = 9;
    	
    	if(currentPage>4){
    		startLi = parseInt(currentPage)-4;
    		endLi = parseInt(currentPage)+4;
    	}
    	if(allPageNum<endLi){
    		endLi = allPageNum;
    	}
    	if(allPageNum<startLi){
    		startLi = 1;
    		endLi = 1;
    	}
    	//alert(currentPage+'-'+startLi+'-'+endLi);
    	for (var point = startLi; point < endLi; point++) {
    		var $li = new RenderLi(point,point); 
    		if(point==currentPage){
    			$li.addClass("pgCurrent");
    		}
    		this.$lis[this.$lis.length]=$li;
    		$ul.append($li);
    	}  
    	var num = 10;
    	if(allPageNum>endLi){
    		$li=new RenderLi("...","...");
    		num=+1;
        	this.$lis[this.$lis.length]=$li;
        	$ul.append($li);
    	} 
    	
    	//末页
    	$li = new RenderLi(allPageNum,allPageNum);
    	if(this.allPageNum==currentPage){
    		$li.addClass("pgCurrent");
    	}
    	$ul.append($li);
    	
    	$li=new RenderLi("next","下一页"); 
    	this.$lis[this.$lis.length]=$li; 
    	$ul.append($li);   
    	
    	//共多少页
    	$li = new RenderLi("all","共"+allPageNum+"页");
    	$ul.append($li);
    	return $ul;
    }
    
    //点击标签页
    Table.prototype.myClick = function(currentPage){  
    	if(currentPage=='first'){
    		this.currentPage = 1;
    		 currentPage = 1;
    	}
    	if(currentPage=='all') return;
    	if(currentPage=='...') return;
    	if(currentPage=='last'){
    	  	this.currentPage = this.currentPage -1;
    	}else if(currentPage=='next'){
    		this.currentPage = parseInt(this.currentPage) +1; 
    	}else{
    		this.currentPage = currentPage;
    	} 
    	currentPage = this.currentPage; 
    	if(currentPage<1) this.currentPage=1;
    	else if(currentPage>this.allPageNum) this.currentPage = this.allPageNum;  
    	//alert(currentPage);
    	//判断是否搜索
    	var keyword = $("input[name='search']").val();
    	if(keyword=='')
    	   flush(this.currentPage);
    	else{
    		//alert('搜索'+keyword);
    		 flush(this.currentPage,table.keyword); 
    	} 
    } 
    
    function flush(currentPage,fn){
    	fn();
    	//清空表格数据
    	table.contentData=[];
    	//表操作
    	getRemoteData(currentPage);
    	tableContent.empty(); 
    	addHeader();
    	//添加表格样式
    	addData();   
    	//alert('--ss-'+this.allRecodeNum);
    	addPageTool(currentPage,table.allRecodeNum); 
    }
    
    function flush(currentPage,keyword){
    	//清空表格数据
    	table.contentData=[];
    	//表操作
    	getRemoteData(currentPage,keyword);
    	tableContent.empty(); 
    	addHeader();
    	//添加表格样式
    	addData();    
    	addPageTool(currentPage,table.allRecodeNum); 
    }
    
    //初始化
    function init(){
    	tableContent.empty(); 
		addHeader();
		table.contentData=[]; 
    }
    
    Table.prototype.deleteStudentByPhone = function(phone){  
    	$.messager.confirm("确认", '你确认要删除该学员?', function (r) {  
		    	if(r){
		    		$.ajax({
			    		type:'post',
			    		url:ctx+'/myStudent/deleteStudentByPhone.do',
			    		async:false,
			    		data:{
			    			phone:phone
			    		},
			    		success:function(sR){
			    			if(sR.message=='DELETE_YES'){
			    				alert("删除成功"); 
			    				flush(table.currentPage,table.keyword);
			    			}else{
			    				alert("删除失败");
			    			} 
			    		}
			    	});
		    	} 
		    });
    }
    
    //重置密码
    Table.prototype.resetPassword = function(phone){   
        $.messager.confirm("确认", '你确认重置密码吗?', function (r) {  
    	        if (r) {  
    	        	$.ajax({
    	        		type:'post',
    	        		url:ctx+'/myStudent/resetPassword.do',
    	        		async:false,
    	        		data:{
    	        			phone:phone
    	        		},
    	        		success:function(sR){
    	        			if(sR.message=='RESET_SUCCESS'){
    	        				alert("密码重置成功,为初始密码123456");
    	        			} else{
    	        				alert("密码重置失败");
    	        			}
    	        		}
    	        	}); 
    	            return true;  
    	        }  
    	}); 
    }
    
    
    function TrTh(ths){
    	 var $tr = $('<tr></tr>'); 
    	 $tr.append(new Th('<input type="checkbox" name=""/>')); 
     	 for(var i=0;i<ths.length;i++)
   		 $tr.append(new Th(ths[i])); 
     	 return $tr;
    }
    
    function Tr(ths){    
    	 var $tr = $('<tr></tr>');  
    	 var flag=true;
    	 for(var i=0;i<choiceShops.length;i++){
    		 var shop = choiceShops[i];
    		 if(shop.shopId==ths[0]){
    	    	 $tr.append(new Td('<input type="checkbox" onclick="table.check(\''+table.currentPage+'\',\''+ths[0]+'\')"  name="studentIdChecked'+table.currentPage+ths[0]+'" class="'+table.currentPage+'" checked="checked" value="'+ths[0]+'"/>'));  
    	    	 flag=false;
        		 break;
    		 } 
    	 }
    	 
    	 if(flag){
    		 $tr.append(new Td('<input type="checkbox" onclick="table.check(\''+table.currentPage+'\',\''+ths[0]+'\')"  name="studentIdChecked'+table.currentPage+ths[0]+'" class="'+table.currentPage+'"  value="'+ths[0]+'"/>'));  
         }
    	 
    	//$tr.append(new Td('<input type="checkbox" name="" value="'+ths[0]+'"/>')); 
    	 for(var i=1;i<ths.length-1;i++)
    		 $tr.append(new Td(ths[i])); 
    	 $tr.append(new Td('<input type="hidden" name="" value="'+ths[7]+'"/>'));
    	 return $tr;
    } 
    function Th(data){
    	var $th = $('<th style="background-color:'+table.headColor+';color:black;">'+data+'</th>');
    	$th.addClass("table_th");
    	return $th;
    }
    
    function Td(data){
    	var $td = $('<td>'+data+'</td>');
    	return $td;
    }
})(jQuery);