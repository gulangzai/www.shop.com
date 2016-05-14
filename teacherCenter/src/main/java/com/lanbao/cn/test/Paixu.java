package com.lanbao.cn.test;

public class Paixu {
	
	int a[]={3,8,5,3,2};
	
	public Paixu(){
		int aa[]=new int[11];
		for(int i=0;i<a.length;i++){
			aa[a[i]]=aa[a[i]]+1;
		}
		  
		for (int i = 0; i < aa.length; i++) {
			if(aa[i]!=0)
				System.out.print(i+",");
		}
	}
	
	public static void main(String[] args) {
		new Paixu();
	}
}
