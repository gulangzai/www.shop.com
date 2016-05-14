package com.lanbao.cn.db.single;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Logger; 

public class ReadInfo {

	public InputStream in = null;
	public Properties p = null;
	
	public HashMap<String,DBNode> nodes = new HashMap<String,DBNode>();

	public ReadInfo() { 
		try { 
			String nowPath = System.getProperty("user.dir");  
			 nowPath=nowPath.replace("bin","webapps"); 
			//nowPath+="\\webapps";
			//nowPath+="\\GoodCenter"; 
			//in = new FileInputStream(new File(Configuration.fileName));
			in = new FileInputStream(new File(nowPath+"//db.properties"));
			//in = ReadInfo.class.getResource("pool.properties").openStream();
			p = new Properties();
			p.load(in);
		} catch (IOException e) {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public HashMap<String,DBNode> getNodes() {
         
		DBNode dbNode = new DBNode();
		
		try {
			dbNode.setDbNode(new String(p.getProperty("dbNode").getBytes("ISO8859-1"),"GBK"));
			
			dbNode.setDbName(new String(p.getProperty("dbName").getBytes("ISO8859-1"), "GBK"));

			dbNode.setDbIp(new String(p.getProperty("dbIp").getBytes("ISO8859-1"),"GBK"));

			dbNode.setDbPort(Integer.parseInt(p.getProperty("dbPort")));

			dbNode.setDbUser(new String(p.getProperty("dbUser").getBytes("ISO8859-1"), "GBK"));

			dbNode.setDbPass(new String(p.getProperty("dbPass").getBytes("ISO8859-1"), "GBK"));

			dbNode.setDbDriver(new String(p.getProperty("dbDriver").getBytes("ISO8859-1"), "GBK"));

			dbNode.setMaxCount(Integer.parseInt(p.getProperty("maxCount")));

			dbNode.setMinCount(Integer.parseInt(p.getProperty("minCount")));

		//	dbNode.setWaitTimer(Integer.parseInt(p.getProperty("waitTime")));
			
			dbNode.setInitCount(Integer.parseInt(p.getProperty("iniCount")));
			
			dbNode.setDistanceTime(Integer.parseInt(p.getProperty("distanceTime")));
			
			nodes.put(dbNode.getDbNode(), dbNode);
  
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nodes;
	}
}
