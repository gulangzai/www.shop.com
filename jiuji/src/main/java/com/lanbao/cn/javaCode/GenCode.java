package com.lanbao.cn.javaCode;

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

/**
 * 
 * @author liyi
 * @time  2016/5/20
 */
public class GenCode {
	
	/*
	 * 表名:tableName
	 * 字段名:fieldName
	 * 描述:descName
	 * */
	String database = "cpmi_test";
	String tableName="jc_prj_points_damage";
	String upperTableName="PRJ_POINTS_DAMAGE";
	String tableKey = "PRJ_POINTS_DAMAGE_UID";
	String file_type="12001";
	
	String packageName="jcprjpointsdamage";
	String classTableName="SpContact";
	/*--------------------------------------------------------*/
	
	
	
	private final String controller = classTableName+"Controller.java";
	private final String service = classTableName+"Service.java";  
	private final String serviceImpl =  classTableName+"ServiceImpl.java";  
	private final String  dao =  classTableName+"Mapper.java";  
	private final String  model =  classTableName+".java";   
	
	
	Logger logger = LoggerFactory.getLogger(GenCode.class);
	
	public GenCode(){   
		FreemarkerUtil fk = new FreemarkerUtil(); 
		HashMap<String,Object> rootMap = createData(tableName,database);
		initPara(rootMap);
		
		fk.htmlOut("controllerTemplate.ftl", rootMap, controller);
		logger.info("["+controller+"]创建成功");
		
		fk.htmlOut("serviceTemplate.ftl", rootMap,service);
		logger.info("["+service+"]创建成功"); 
		
		fk.htmlOut("serviceImplTemplate.ftl", rootMap, serviceImpl);
		logger.info("["+serviceImpl+"]创建成功");
		
		fk.htmlOut("daoTemplate.ftl", rootMap, dao);
		logger.info("["+dao+"]创建成功");
		
		fk.htmlOut("modelTemplate.ftl", rootMap,model);
		logger.info("["+model+"]创建成功"); 
		
	}
	
	public void initPara(HashMap<String,Object> rootMap){
		rootMap.put("controllerFile", controller);
		rootMap.put("serviceFile", service);
		rootMap.put("serviceImplFile", serviceImpl);
		rootMap.put("daoFile", dao);
		rootMap.put("modelFile", model);
		rootMap.put("file_type",file_type);
		rootMap.put("upperTableName", upperTableName);
		rootMap.put("tableKey", tableKey);
	}
	
	
	public HashMap createData(String tableName,String database){
		HashMap map = new HashMap();
		DBPool  dbPool = DBPoolSource.getInstance("1");
		Connection conn = dbPool.getConnection();
		try {
			String sqlTable = "select *from information_schema.`TABLES` where TABLE_NAME='"+tableName+"';";
			System.out.println(sqlTable);
			String sql = "select *from information_schema.columns where table_name='"+tableName+"' AND TABLE_SCHEMA='"+database+"';";
			System.out.println(sql);
			Statement stat = conn.createStatement();
			ResultSet tableRs = stat.executeQuery(sqlTable); 
			Table table = new Table();
			table.setName(tableName);
		 	while(tableRs.next()){
		 		 table.setDesc(tableRs.getString("TABLE_COMMENT"));
		 	}
			 
			ResultSet rs = stat.executeQuery(sql);
			ArrayList<Field> fields = new ArrayList<Field>();
			while(rs.next()){
				 Field field = new Field();
				 field.setName(rs.getString("COLUMN_NAME"));
				 field.setIs_nullable(rs.getString("IS_NULLABLE"));
				 field.setComment(rs.getString("COLUMN_COMMENT"));
				 field.setColumn_key(rs.getString("COLUMN_KEY"));
				 field.setData_type(rs.getString("DATA_TYPE"));
				 System.out.println("......"+field.getComment());
				 fields.add(field);
			}
			map.put("table", table);
			map.put("fields", fields);
			
			//controller
			//map.put("controllerName",controllerName);
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
