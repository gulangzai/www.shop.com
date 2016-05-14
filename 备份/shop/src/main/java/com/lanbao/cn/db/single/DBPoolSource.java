package com.lanbao.cn.db.single;
 
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Set; 
 

public class DBPoolSource {
 
	private static HashMap<String,DBPool> dataSource = new HashMap<String,DBPool>();
	  
	static { 
		ReadInfo ri = new ReadInfo();
		HashMap<String,DBNode> nodes = ri.getNodes();
		Set<String> set = nodes.keySet();
		java.util.Iterator<String> iterator = set.iterator();
		while(iterator.hasNext()){
		    DBNode dbNode =nodes.get(iterator.next());
		   DBPool dbPool = new DBPool(dbNode);
		   dataSource.put(dbNode.getDbNode(),dbPool);
		}
	}
	
	
     
    /*������ӳض����ˣ��ñ�����ӳ�*/
	public final static DBPool getInstance(String name) { 
	     return  dataSource.get(name); 
	} 
}
