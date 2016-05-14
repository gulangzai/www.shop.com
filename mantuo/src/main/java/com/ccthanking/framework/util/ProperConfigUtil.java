package com.ccthanking.framework.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

/**
 * 得到CDA properties 的配置文件
 * @author Administrator
 *
 */
public class ProperConfigUtil {
	private static Logger log = Logger.getLogger(ProperConfigUtil.class);
	private static Map<String, String> map = new Hashtable<String, String>();
	static Properties properties = new Properties();
	static {
		InputStream in = ProperConfigUtil.class.getClassLoader().getResourceAsStream(
				"CommConfig.properties");
		try {
			properties.load(in);
		} catch (IOException e) {
			log.error("初始化Config失败", e);
		}
		Set<Entry<Object, Object>> set = properties.entrySet();
		for (Entry<Object, Object> o : set) {
			String tempKey = o.getKey().toString();
			String value = o.getValue().toString();
			map.put(tempKey,value);
	    }
	}

	/**
	 * 得到key的配置文件
	 * @param key
	 * @param list 匹配$[0] $[1]$[2] 从0开始
	 * @return
	 */
	public static String getConfigByKey(String key,List<Object> list) {
		String value = properties.getProperty(key);
		if (list != null) {
			Map<String,String> map = new HashMap<String, String>();
			int list_length = list.size();
			for(int i=0;i<list_length;i++){
				map.put(String.valueOf(i), list.get(i).toString());
			}
			value = getConfigByKey(key,map);
		}
		log.debug(key+"取得值--->"+value);
		return value;
	}

	/**
	 * 得到key的配置文件
	 * @param key
	 * @param objs 匹配 $[1] $[2] 
	 * @return
	 */
	public static String getConfigByKey(String key,Object... objs) {
		String value = properties.getProperty(key);
		if (objs != null) {
			Map<String,String> map = new HashMap<String, String>();
			int i = 0;
			for(Object obj:objs){
				map.put(String.valueOf(i), obj.toString());
			}
			value = getConfigByKey(key,map);
		}
		log.debug(key+"取得值--->"+value);
		return value;
	}
	
	public static String getConfigByKey(String key)   {
		return getConfigByKey(key, new HashMap());
	}

	/**
	 * 返回以map形式的数据
	 * @return
	 */
	public static Map<String, String> getGlobalMap() {
		return map;
	}
	
	/**
	 * 获得配置文件属性
	 * @return
	 */
	public static String getParaValue(String sx){
		InputStream inputStream = ProperConfigUtil.class.getClassLoader().getResourceAsStream("CommConfig.properties");   
	    Properties p = new Properties();  
	    try {   
	    	p.load(inputStream);   
	    	return p.getProperty(sx);
	    } catch (IOException e1) {   
	    	e1.printStackTrace();   
	    } 
	    return "";
	}
	
	
}
