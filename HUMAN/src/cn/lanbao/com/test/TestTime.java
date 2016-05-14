package cn.lanbao.com.test;

import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class TestTime {

	public TestTime() {
		// TODO Auto-generated constructor stub
		 Date date = new Date();
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	     String time = sdf.format(date);
	     System.out.println("time:"+time);
	     
	     String dateTime = "2014-04-23 12:22:33";
	     //long dateLong = Date.parse();
	     try {
			Date  my_date =  sdf.parse(dateTime);
			String my_time = sdf.format(my_date);
			System.out.println("my_time£∫"+my_time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	} 
	
	/*
	 * ≤‚ ‘ibatis
	 * */
	public void testIbatis(){
		try {
			Reader reader = Resources.getResourceAsReader("WEB-INF/sqlMapConfig.xml");
			SqlMapClient smc = SqlMapClientBuilder.buildSqlMapClient(reader);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
         new TestTime();
	}

}
