package com.jiuji.cn.utils.log;

import java.util.Random;

public class IDGenertor {
	
	public static String randomCode(){
		 Random r=new Random();
		String code = String.valueOf(r.nextInt(10))+
				        String.valueOf(r.nextInt(10)+
						String.valueOf(r.nextInt(10))+
						String.valueOf(r.nextInt(10))+
						String.valueOf(r.nextInt(10))); 
		return code;
	}
	
	public static void main(String[] args){
		System.out.println(IDGenertor.randomCode());
	}
}
