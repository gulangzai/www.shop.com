package com.lanbao.cn.test;

public class Test {
	
	String str="";
	
	Test(){
		String[] s=str.split(",");
		System.out.println(s.length);
	}
	public static void main(String[] args) {
		new Test();
	}
}
