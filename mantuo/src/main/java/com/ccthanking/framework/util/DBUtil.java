package com.ccthanking.framework.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



	/**
	 * 该类负责数据库的连接管理工作
	 * @author Administrator
	 *
	 */
	public class DBUtil {
		
		public static final String  DRIVER ="com.mysql.jdbc.Driver";
		public static final String URL ="jdbc:mysql://192.168.1.117:3306/cpmi_test?useUnicode=true&characterEncoding=utf-8";
		public static final String NAME = "pmis";
		public static final String PWD = "111111";
		/**
		 * 获取一个数据库连接
		 * @return 若连接失败,抛出异常
		 * @throws Exception
		 */
		public static Connection getConnection() throws Exception{
				try{
					Class.forName(DRIVER);
					Connection conn=(Connection) DriverManager.getConnection(URL,NAME,PWD
						);
					return conn;
				}catch(Exception e){
					e.printStackTrace();
					throw e;
				}
			}
			/**
			 * 将给定的连接关闭
			 */
			public static void closeConnection (Connection conn){
				try{
					if(conn !=null){
						conn.close();
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			
/**
 * 测试 	
 * @param args
 */
			public static void main(String[] args) {
				Connection conn = null;
				try {
					conn = DBUtil.getConnection();
					System.out.println(conn+"sssssssssssssssssssssss");
					String sql = "select * from bu_project";
					PreparedStatement ps=(PreparedStatement) conn.prepareStatement(sql);
				   //  执行SQL语句,并返回结果集
			        ResultSet rs=ps.executeQuery();//这里不需要再传入SQL语句
					
			         if(rs.next()){
			        	 System.out.println("成功");
			         }else{
			        	 System.out.println("失败");
			         }
			         
			         while(rs.next()){
			 			int id = rs.getInt("PROJECT_UID");
			 			String name = rs.getString("PROJECT_NAME");
			 			String pwd = rs.getString("JIANZHU_MIANJI");
			 			System.out.println(id+"=="+name+"=="+pwd+"success");
			 			
			 		}
			         rs.close();
			         ps.close();
			         DBUtil.closeConnection(conn);
			         
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
			}
	     
	}



