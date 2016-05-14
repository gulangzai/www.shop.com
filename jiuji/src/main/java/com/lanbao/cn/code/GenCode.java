package com.lanbao.cn.code;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.logging.LogFactory; 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lanbao.cn.db.single.DBPool;
import com.lanbao.cn.db.single.DBPoolSource;
import com.lanbao.cn.db.single.Field;
import com.lanbao.cn.db.single.Table;

import freemarker.template.Template;

public class GenCode {
	
	/*
	 * 表名:tableName
	 * 字段名:fieldName
	 * 描述:descName
	 * */
	
	String tableName="pm_yanshou";
	private final String  outFile="add.jsp";  
	private final String controllerName = "projectaccept/pm_yanshouController";
	Logger logger = LoggerFactory.getLogger(GenCode.class);
	 
	GenCode(){  
		FreemarkerUtil fk = new FreemarkerUtil(); 
		HashMap<String,Object> rootMap = createData(tableName);
		fk.htmlOut("jsp-addTemplate.ftl", rootMap, tableName+"_"+outFile);
		logger.info("["+outFile+"]创建成功");
	}
	
	
	public HashMap createData(String tableName){
		HashMap map = new HashMap();
		DBPool  dbPool = DBPoolSource.getInstance("1");
		Connection conn = dbPool.getConnection();
		try {
			String sqlTable = "select *from information_schema.`TABLES` where TABLE_NAME='"+tableName+"';";
			System.out.println(sqlTable);
			String sql = "select *from information_schema.columns where table_name='"+tableName+"';";
			Statement stat = conn.createStatement();
			ResultSet tableRs = stat.executeQuery(sqlTable); 
			Table table = new Table();
			table.setName(tableName);
		 	while(tableRs.next())
			  table.setDesc(tableRs.getString("TABLE_COMMENT"));
			ResultSet rs = stat.executeQuery(sql);
			ArrayList<Field> fields = new ArrayList<Field>();
			while(rs.next()){
				 Field field = new Field();
				 field.setName(rs.getString("COLUMN_NAME"));
				 field.setIs_nullable(rs.getString("IS_NULLABLE"));
				 field.setComment(rs.getString("COLUMN_COMMENT"));
				 System.out.println("......"+field.getComment());
				 fields.add(field);
			}
			map.put("table", table);
			map.put("fields", fields);
			
			//controller
			map.put("controllerName",controllerName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return map;
	}
	public static void main(String[] args) {
		new GenCode();
	}
}
