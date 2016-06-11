package com.jiuji.cn.utils.log;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LyLog {
	public static Logger log = null;
	
	public static Logger getLog(){
		if(log==null){
			log = Logger.getLogger(com.jiuji.cn.utils.log.LyLog.class.getClass());
			System.out.println(System.getProperty("user.dir"));
			PropertyConfigurator.configure(com.jiuji.cn.utils.log.LyLog.class.getResource("log4j.properties"));
			System.out.println(com.jiuji.cn.utils.log.LyLog.class.getResource("log4j.properties"));
		}
		return log;	
	}
}
