// JavaScript Document

function switchTag(tag,content,k,n,stylea,styleb){	 
		for(i=1;i<=n;i++){
			if (i==k){
				document.getElementById(tag+i).className=stylea;
				document.getElementById(content+i).style.display="block";
				if(k==2){//一度
					$("#paging1").hide();
					$("#paging3").hide();
					$("#paging2").show();
					$.ajax({type:"post",
				    	    url:rootPath+"/homePage/readNotice.do",
				    	    dataType: "json",
				    	    data:{
				    	    },
				    	    success:function(data){
				    	    	 if(data.iserror==true){
				    	    		 alert(data.message);
				    	    	 }else if(data.iserror==false){
				    	    		 $('#pagingWrap2').jqPaginator({
				    	 		  	    totalCounts : data.data,
				    	 		        currentPage : 1,
				    	 		        pageSize : pSize,
				    	 		  	    //first: '<li class="first"><a href="javascript:;">首页</a></li>',
				    	 		  	    prev: '<li class="prev paging_one"><a href="javascript:;">前一页</a></li>',
				    	 		        next: '<li class="next paging_one"><a href="javascript:;">后一页</a></li>',
				    	 		        page: '<li class="page paging_one"><a href="javascript:;">{{page}}</a></li>',
				    	 		        //last: '<li class="page"><a href="javascript:;">尾页</a></li>',
				    	 		        disableClass:'disabled',
				    	 		 	    onPageChange: function (num, type) {
				    	 			       	     $.ajax({
				    	 			                 url : rootPath+"/homePage/readPageNotice.do",
				    	 			                 dataType : 'json',
				    	 			                 data : {
				    	 			                     pageIndex : num,
				    	 			                     pageSize : pSize,
				    	 			                 },
				    	 			                 cache : false,
				    	 			                 success : function(data) {
				    	 			                	 var results = data.data;
				    	 			                	 //$("#thisKnowledge li:not(:first)").remove();
				    	 			                	 $('#thisKnowledge2').empty();
				    	 			                	 for (var i = 0; i < results.length; i++) {
				    	 			                		 var rowContent ='<tr><td width="768">'+results[i].annunciate+'</td>';
				    	 			                		 rowContent+='<td width="132" class="wxtz_time">'+format(new Date(results[i].createTime).getTime(), 'yyyy-MM-dd')+'</td></tr>';
				    	 		                			 $('#thisKnowledge2').prepend(rowContent);
				    	 			                	 }
				    	 			                 },
				    	 			                 error : function(html) {
				    	 			                     return;
				    	 			                 }
				    	 			       	   	 });
				    	 		 	    }
				    	 		  	});
				    	    	 }
				    	    }
				    }); 
				}else if(k==3){//未读
					$("#paging1").hide();
					$("#paging2").hide();
					$("#paging3").show();
					$.ajax({type:"post",
			    	    url:rootPath+"/homePage/readNoNotice.do",
			    	    dataType: "json",
			    	    data:{
			    	    },
			    	    success:function(data){
			    	    	if(data.iserror==true){
			    	    		 alert(data.message);
			    	    	 }else if(data.iserror==false){
			    	    		 $('#pagingWrap3').jqPaginator({
			    	 		  	    totalCounts : data.data,
			    	 		        currentPage : 1,
			    	 		        pageSize : pSize,
			    	 		  	    //first: '<li class="first"><a href="javascript:;">首页</a></li>',
			    	 		  	    prev: '<li class="prev paging_one"><a href="javascript:;">前一页</a></li>',
			    	 		        next: '<li class="next paging_one"><a href="javascript:;">后一页</a></li>',
			    	 		        page: '<li class="page paging_one"><a href="javascript:;">{{page}}</a></li>',
			    	 		        //last: '<li class="page"><a href="javascript:;">尾页</a></li>',
			    	 		        disableClass:'disabled',
			    	 		 	    onPageChange: function (num, type) {
			    	 			       	     $.ajax({
			    	 			                 url : rootPath+"/homePage/readNoPageNotice.do",
			    	 			                 dataType : 'json',
			    	 			                 data : {
			    	 			                     pageIndex : num,
			    	 			                     pageSize : pSize,
			    	 			                 },
			    	 			                 cache : false,
			    	 			                 success : function(data) {
			    	 			                	 var results = data.data;
			    	 			                	 //$("#thisKnowledge li:not(:first)").remove();
			    	 			                	 $('#thisKnowledge3').empty();
			    	 			                	 for (var i = 0; i < results.length; i++) {
			    	 			                		 var rowContent ='<tr><td width="768">'+results[i].annunciate+'</td>';
			    	 			                		 rowContent+='<td width="132" class="wxtz_time">'+format(new Date(results[i].createTime).getTime(), 'yyyy-MM-dd')+'</td></tr>';
			    	 		                			 $('#thisKnowledge3').prepend(rowContent);
			    	 			                	 }
			    	 			                 },
			    	 			                 error : function(html) {
			    	 			                     return;
			    	 			                 }
			    	 			       	   	 });
			    	 		 	    }
			    	 		  	});
			    	    	 }
			    	    }
			    }); 
				}else{
					$("#paging1").show();
					$("#paging2").hide();
					$("#paging3").hide();
				}
			}else{
				document.getElementById(tag+i).className=styleb;
				document.getElementById(content+i).style.display="none";
			}
		}
	}
	
	
	
	
