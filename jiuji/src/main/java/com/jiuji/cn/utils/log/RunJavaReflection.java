package com.jiuji.cn.utils.log;

import java.lang.reflect.InvocationTargetException;
 

public class RunJavaReflection {
	
	public Object init(String sClass,String sMethod,String args){
		String sReturn = "";
		try {
			Object obj = Class.forName(sClass).newInstance(); 
			sReturn = executeMethod(obj,sMethod);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return sReturn;
	}
	
	public String executeMethod(Object instance,String method){
		Class clazz = instance.getClass();
		try {
			java.lang.reflect.Method method1 = clazz.getDeclaredMethod(method);
			String sReturn = (String) method1.invoke(instance);
			return sReturn;
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
}
