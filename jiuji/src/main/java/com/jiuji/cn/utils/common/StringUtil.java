package cn.lanbao.com.utils.common;

public class StringUtil {
	
	public static boolean isEmpty(Object obj){
		
		if(obj==null||obj.toString().trim().equals("")){
			return true;
		}
		return false;
	}
}
