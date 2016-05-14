package com.ccthanking.framework.util;

import java.io.InputStreamReader;
import java.util.Properties;


public class PropertyUtil {

	private static String propertiesFileName = "filetype.properties";
	
	private static Properties extraProperties = new Properties();

	private static Properties properties = new Properties();
	static{
		try {
			properties.load(new InputStreamReader(PropertyUtil.class.getClassLoader().getResourceAsStream("filetype/"+propertiesFileName),"UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * 获取属性
	 * 
	 * @param key
	 * @return	失败返回null
	 */
	public static String getProperty(String key){
		String result = null;
		if(result == null){
			result = properties.getProperty(key);
		}
		if(result == null){
			result = extraProperties.getProperty(key);
		}
		return result;
	}
	
	public static boolean addProperty(String key, String value){
		boolean result = false;
		if(extraProperties.get(key) == null){
			extraProperties.setProperty(key, value);
			result = true;
		}
		return result;
	}
	
}
