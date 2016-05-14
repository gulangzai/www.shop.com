(function($){
	 
	var tree;
	var majorFactory;
	var subjectFactory;
	var classesFactory;
	var major;
	$.fn.tree=function(options){  
		tree = $(this);
		init();   
		tree.majorFactory=majorFactory;
		tree.subjectFactory = subjectFactory;
		tree.classesFactory=classesFactory;
		return tree; 
	};
	
	function init(){
		majorFactory = new MajorFactory();  
		subjectFactory = new SubjectFactory();
		classesFactory = new ClassesFactory();
		remote(majorFactory);
		var majorDiv = majorFactory.getContentDiv(); 
		tree.empty().append(majorDiv);
	}
	
	//发送请求
	function remote(obj){
		$.ajax({
     		type:'post',
     		url:ctx+'/shopManager/'+obj.url+'.do',
     		data:obj.paraData,
     		async:false,
     		success:function(sR){  
     			obj.setData(sR.data);
     		}
     	}); 
	}
	
	//var subjectFactory = new SubjectFactory();
	  
	function Major(majorId,majorName){
		this.url='getSubjectByMajorId';    //初始化子节点url
		this.id=majorId;
		this.paraData={'majorId':majorId};
		this.init = false;
		this.sonInit = false;
		this.closed = false;
		this.data=[];   //子节点
		this.setData = function(data){
			this.data = data;
		}; 
		this.li = $('<li><a id='+majorId+' href="javascript:majorFactory.click(\''+majorId+'\')">'+majorName+'</a></li>');   
		this.click =  function(){
    		var target = this.li;
    		//console.info(target.next()); 
    		if(!this.sonInit){ 
        		remote(this);
        		var subjectsDiv = $("<ul></ul>");
        		for(var i=0;i<this.data.length;i++){
        			var subject = new Subject(majorId,this.data[i].subjectId,this.data[i].subjectName);
        			subjectsDiv.append(subject.li);
        			subjectFactory.subjects[subjectFactory.subjects.length]=subject;
        		}   
        		 this.li.after(subjectsDiv);
        	/*	var subjectDiv = subjectFactory.getContentDiv();
    			if(target.next().length>0){
     			   if(target.next().get(0).tagName=='UL'){ 
     	    		    target.next().remove().after(subjectDiv);  
     	    	   }else{
     	    		    target.after(subjectDiv);  
     	    	   }
     			   this.closed=false;
     		    }else{
     			   target.after(subjectDiv); 
     			   this.closed=false;
     		    } */
    			this.sonInit=true;
    		}else{
    			   if(this.closed){
    				   target.next().show();
    				   this.closed=false;
    			   } else{
    				   target.next().hide();
    				   this.closed=true;
    			   }
    		} 
    	} 
		return this;
	}
	
    function MajorFactory(){ 
    	this.url="getMajor";
    	this.paraData;
    	this.data=[];
    	this.dataLiDiv=[];
    	this.majors =[];
    	this.setData=function(data){
    		this.data=data; 
    	},
    	this.getContentDiv = function(){ 
    		var majorDiv=$('<ul></ul>');
    		for(var i=0;i<this.data.length;i++){ 
    			var major= new Major(this.data[i].majorId,this.data[i].majorName);
    			this.majors[this.majors.length]=major;
    			this.dataLiDiv[i]=major;
    			console.info(major.li);
    			majorDiv.append(major.li);
    		} 
    		return majorDiv;
    	},
    	this.click =  function(majorId){  
    		//console.info(this.majors.length);
    		var target = this.getTarget(this.majors,majorId);    
    		target.click(majorId);
    	} ,
    	this.getTarget = function(data,target){
        	//console.info(data);
        	for(var i=0;i<data.length;i++){ 
        		if(data[i].id==target){
        			return data[i];
        		}
        	}
        	return null;
        }
    }
    
     function SubjectFactory(){
    	this.subjects = [];
    	this.click =  function(subjectId){   
    		//console.info(this.majors.length);
    		 var target = this.getTarget(subjectId);  
    		 target.click();
    	} ,
    	this.getTarget = function(target){  
        	for(var i=0;i<this.subjects.length;i++){ 
        		if(this.subjects[i].id==target){
        			return this.subjects[i];
        		}
        	}
        	return null;
        }
     }
    
    var classesFactory = new ClassesFactory();
    
    function Subject(majorId,subjectId,subjectName){
       this.id=subjectId;
       this.url="getClasses.do";
       this.init = false;      //自己是否已经初始化
       this.sonInit = false;   //子节点是否初始化 
       this.closed=false;
       this.paraData={'subjectId':subjectId};
       this.data=[];           //班级
       this.setData = function(data){
    	   this.data = data;
       }, 
   	   this.li=$('<li id='+subjectId+'><a href="javascript:subjectFactory.click(\''+subjectId+'\')">'+subjectName+'</a></li>');
   	   this.click = function(){
   		   var target = this.li;
   		   if(!this.sonInit){
   			     remote(this);
   				 var classesDivs=$('<ul></ul>');
   			     for(var i=0;i<this.data.length;i++){
   			    	 var classes = new Classes(majorId,subjectId,this.data[i].classesId,this.data[i].classesName);
   			    	 classesFactory.classess[classesFactory.classess.length]= classes;
   			    	 classesDivs.append(classes.li);
   			     } 
        		 this.li.after(classesDivs);
   			     this.sonInit=true; 
   		   } else{
			   if(this.closed){
				   target.next().show();
				   this.closed=false;
			   } else{
				   target.next().hide();
				   this.closed=true;
			   }
		   } 
   	   }
   	   return this;
    }
    
    
    function ClassesFactory(){ 
    	 this.classess=[];
    	 this.setData=function(data){
    		 this.data=data;
    	 },
    	 this.getTarget = function(target){  
         	for(var i=0;i<this.classess.length;i++){ 
         		if(this.classess[i].id==target){
         			return this.classess[i];
         		}
         	}
         	return null;
         }
    }
    
    //shopFactory
    function shopFactory(){
    	this.url="getShop";
    	this.shopes=[];
    	this.setData=function(data){
    		this.data = data;
    	},
    	this.getShop = function(){
    		
    	}
    }
    
    //班级
    function Classes(majorId,subjectId,classesId,classesName){
    	 this.url="getShop.do";
    	 this.paraData={'majorId':majorId,'subjectId':subjectId,'classesId':classesId};
    	 this.li=$('<li id='+classesId+'><a href="javascript:classesFactory.click(\''+classesId+'\')">'+classesName+'</a></li>');
    	 this.click = function(){
     		   var target = this.li;
     		   if(!this.sonInit){
     			     remote(this);
     				 var shopesDivs=$('<ul></ul>');
     			     for(var i=0;i<this.data.length;i++){
     			    	 var shop = new Shop(majorId,subjectId,this.data[i].classesId);
     			    	 shopFactory.shopes[shopFactory.shopes.length]= shop;
     			    	 shopesDivs.append(shop.li);
     			     } 
          	         this.li.after(shopesDivs);
     			     this.sonInit=true; 
     		   } else{
  			   if(this.closed){
  				   target.next().show();
  				   this.closed=false;
  			   } else{
  				   target.next().hide();
  				   this.closed=true;
  			   }
  		   } 
     	 }
    	 return this;
    }
    
    function Shop(majorId,subjectId,classesId){
    	this.majorId = majorId;
    	this.subjectId = subjectId;
    	this.classesId = classesId;
    }
    
})(jQuery);
