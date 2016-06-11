package com.jiuji.cn.utils.common;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import com.jiuji.cn.utils.log.Liyi;
 

/*
 * @author:李益
 * */
public class CommonUtil {
	
	public CommonUtil(){
/*		 User user = (User)CommonUtil.objectFromReq(User.class,null);
		 System.out.println("---"+user.getName());*/
	}
   
	//构建访问的实例对象
	public static <T> Object objectFromReq(Class<T> myclass, HttpServletRequest req)throws Exception {
		// TODO Auto-generated method stub  
		String classname = myclass.getName();
		Object sourceObj = Class.forName(classname).newInstance();
		  	Field[] attributes = myclass.getDeclaredFields();
		  	String[] allValues = req.getParameter("args").split(","); 
		   	for(Field field : attributes){
		   		String fieldName = field.getName();
		   		Class<?> fieldType = field.getType();
		   		String fieldTypeName = field.getType().getName();  
		   		String methodName = "set"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
		   		Method method = myclass.getDeclaredMethod(methodName,fieldType); 
		   		//Liyi.p("fieldTypeName"+fieldTypeName);
		   		for(int i=0;i<allValues.length;i++){ 
		   				String[] myallValues  = allValues[i].split(":");
		   				if(myallValues.length==2){
		   				String reqFieldName = myallValues[0];
			   			String value = allValues[i].split(":")[1];
			   		//	 Liyi.p("reqFieldName"+reqFieldName+"value:"+value);
			   			if(fieldName.equals(reqFieldName)){ 
					   		Liyi.p(fieldName+":"+value+"Type"+reqFieldName);
					   		Object valueObj = value;
					   		//Class<?> myType = Class.forName(fieldTypeName); 
					   		if(!StringUtil.isEmpty(value)){
					   			System.out.println("value:"+value);
					   			if(fieldTypeName.equals("int")){
					   				valueObj=Integer.parseInt(value); 
					   			}else if(fieldTypeName.equals("java.util.Date")){
					   				valueObj = TranscodUtil.stringToDate(value);
					   			} else if(fieldTypeName.equals("java.lang.Long")){
					   				valueObj = Long.valueOf(value);
					   			}
					   			method.invoke(sourceObj,valueObj);  
					   		} 
			   			}
		   			} 
		   		} 
		   	} 
		   	System.out.println("-----from req to jopo"+classname);
 
		return sourceObj;
	}
	
	
	public static <E> void test(Class<E> obj){
 
	
	}
	
	//
	public static void getMyType(){
	//	User user = new User(); 
		//System.out.println(""+new Integer(ii).getClass().getName());
	}
	
	
	public static void main(String[] args) {
		//CommonUtil.test(User.class);
		//CommonUtil.getMyType();
		new CommonUtil();
	}

}
