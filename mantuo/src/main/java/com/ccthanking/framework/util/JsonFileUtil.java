package com.ccthanking.framework.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import com.ccthanking.framework.common.DBUtil;

import net.sf.json.JSONObject;

/**
 * json 文件工具类
 * 
 * @author Admin
 * 
 */
public class JsonFileUtil {

	/**
	 * 读取json文件 并将其转成json对象
	 * 
	 * @param path
	 * @return
	 */
	public static ArrayList<JSONObject> ReadFile(String filepath) {
		File file = new File(filepath);
		BufferedReader reader = null;
		String laststr = "";
		JSONObject obj = null;
		ArrayList<JSONObject> list = new ArrayList<JSONObject>();
		try {
			if (!file.isDirectory()) {
				reader = new BufferedReader(new FileReader(file));
				String tempString = null;
				while ((tempString = reader.readLine()) != null) {
					laststr = laststr + tempString;
				}

				obj = JSONObject.fromObject(laststr);
				list.add(obj);
			} else if (file.isDirectory()) {
				String[] filelist = file.list();
				for (int i = 0; i < filelist.length; i++) {
					File readfile = new File(filepath + "\\" + filelist[i]);
					if (!readfile.isDirectory()) {
						reader = new BufferedReader(new FileReader(readfile));
						String tempString = null;
						while ((tempString = reader.readLine()) != null) {
							laststr = laststr + tempString;
						}

						obj = JSONObject.fromObject(laststr);
						list.add(obj);
					} else if (readfile.isDirectory()) {
						ReadFile(filepath + "\\" + filelist[i]);
					}
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {
		// System.out.println(ReadFile("D:\\675601.prop"));

		ArrayList<JSONObject> list = ReadFile("D:\\pmis\\t");
		Connection conn = DBUtil.getConnection();
		try {
			StringBuffer sql = null;
			for (int i = 0; i < list.size(); i++) {
				JSONObject obj = list.get(i);
				sql = new StringBuffer();
/*				sql.append(" INSERT INTO jc_prj_points ");
				sql.append(" (PROJECT_UID,U3D_POINT_ID,POINT_CODE,POINT_NAME,POINT_TYPE, ");
				sql.append(" JKJC_TYPE,ELEVATION,PLAN_BEGIN_TIME,PLAN_END_TIME,ACT_BEGIN_TIME, ");
				sql.append(" ACT_END_TIME,PHASE_CREATED,PHASE_DEMOLISHED, ");
				sql.append(" SCHEDULE_LEVEL,DEFAULT_ELEVATION,COST) ");
				sql.append(" VALUES ");
				sql.append(" (1,"+obj.getString("id")+","+obj.getString("Family Name")+","+obj.getString("Type Name")+","+obj.getString("Elevation")+","+obj.getString("当前时间")+") ");
				sql.append(" ,"+obj.getString("实际结束时间")+","+obj.getString("")+"");*/
				sql.append(" INSERT INTO jc_prj_points  ");
				sql.append(" (PROJECT_UID,U3D_POINT_ID,POINT_TYPE) values");
				sql.append(" (1,"+obj.getString("id")+","+obj.getString("Family Name"));
				System.out.println(sql);
				//DBUtil.exec(conn, sql.toString());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.closeConnetion(conn);
		}
		
		
	}
}
