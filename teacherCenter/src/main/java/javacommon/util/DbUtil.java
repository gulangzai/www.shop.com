package javacommon.util;

import java.util.UUID;

public class DbUtil {
	
	public static String getKey(){
		return UUID.randomUUID().toString();
	} 
}
