// JavaScript Document

function switchTag(tag,content,k,n,stylea,styleb){	 
		for(i=1;i<=n;i++){
			if (i==k){
				document.getElementById(tag+i).className=stylea;
				document.getElementById(content+i).style.display="block";
			}else{
				document.getElementById(tag+i).className=styleb;
				document.getElementById(content+i).style.display="none";
			}
		}
	}
	
	
	
	
