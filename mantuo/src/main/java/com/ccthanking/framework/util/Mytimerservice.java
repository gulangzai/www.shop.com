package com.ccthanking.framework.util;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;  

import net.sf.json.JSONObject;
import org.quartz.JobExecutionContext;  
import org.quartz.JobExecutionException;  
import org.springframework.scheduling.quartz.QuartzJobBean;  


/**该类 主要 是定时的执行某个固定的任务**/
public class Mytimerservice extends QuartzJobBean{	
@Override  
protected void executeInternal(JobExecutionContext arg0)  
             throws JobExecutionException { 
	     
	       Connection conn = null;
	   try {
		   /**
		    * 将所有的城市code查询出来 并获取其当前的天气状况
		    */
		   conn = DBUtil.getConnection();
		  // String sql = "SELECT DISTINCT WEATHER_CODE FROM sys_region  where WEATHER_CODE  is not null";
		   /**
		    * 查询出当前所有 的项目选择的城市的代码
		    */
		   String sqlTQ =" SELECT DISTINCT s.WEATHER_CODE FROM bu_project p left join  sys_region s "+
                         " on p.DISTRICT = s.REGION_CODE WHERE s.WEATHER_CODE IS NOT NULL ";
		   PreparedStatement ps=conn.prepareStatement(sqlTQ);
		   ResultSet rs =  ps.executeQuery();
		   while(rs.next()){
			String url = "http://apistore.baidu.com/microservice/weather?cityid="+rs.getInt("WEATHER_CODE"); 
			//System.out.println("===================="+rs.getInt("WEATHER_CODE")+"============"+request(url)+"===============================");
			getReportData(request(url));
			
		   }
		
	} catch (Exception e) {
		e.printStackTrace();
	}finally{
		DBUtil.closeConnection(conn);
	}
       
 }


public static  boolean getReportData(String jsonStr) {
	JSONObject obj = JSONObject.fromObject(jsonStr);
    JSONObject obj1 = obj.getJSONObject("retData");
    boolean bs= false;
    Connection conn = null;
    String city_name = "";
    String weather_desc="";
    String wind_direction = "";
    String wind ="";
    String report_time ="";
    String max_temperature = "";
    String min_temperature = "";
    String WEATHER_PIC = "";
    int weatherLength = 0;
    try {
   	 conn = DBUtil.getConnection();
	     city_name = URLDecoder.decode(obj1.getString("city"),"UTF-8");//城市的名称
	     weather_desc= URLDecoder.decode(obj1.getString("weather"),"UTF-8");//天气现象描述
	     wind_direction =  URLDecoder.decode(obj1.getString("WD"),"UTF-8");//风向
		 String ws = obj1.getString("WS");
		 int num =  ws.indexOf("(");//返回包含指定字符的 下标位置
		 wind = URLDecoder.decode(ws.substring(0,num),"UTF-8");//风力
		 String city_code = obj1.getString("citycode");
	     max_temperature = obj1.getString("h_tmp");
	     min_temperature = obj1.getString("l_tmp");
		 String date = obj1.getString("date");
		 report_time = obj1.getString("time");
		 String report_date_time = date+" "+report_time;
		 
		 weatherLength = weather_desc.length();
		//天气现象 判断
		 
		// if(weather_desc.indexOf("转") == -1){//说明不存在 转
		 if(weather_desc.indexOf("晴") != -1){//说明存在
			 WEATHER_PIC="assets/images/weather/icon/day/qing.png";
			 
		 } else if(weather_desc.indexOf("雨夹雪") != -1){
			 WEATHER_PIC="assets/images/weather/icon/day/yujiaxue.png";
		 } else if(weather_desc.indexOf("雨") != -1){
			 WEATHER_PIC="assets/images/weather/icon/day/yu.png";
			 
		 }else if(weather_desc.indexOf("雪") != -1){
			 WEATHER_PIC="assets/images/weather/icon/day/xue.png";
			 
		 }else if(weather_desc.indexOf("多云") != -1){
			 WEATHER_PIC="assets/images/weather/icon/day/duoyun.png";
			 
		 }else if(weather_desc.indexOf("霾") != -1){
			 WEATHER_PIC="assets/images/weather/icon/day/mai.png";
			 
		 }else if(weather_desc.indexOf("雾") != -1){
			 WEATHER_PIC="assets/images/weather/icon/day/wu.png";
			 
		 }else if(weather_desc.indexOf("浮尘") != -1){
			 WEATHER_PIC="assets/images/weather/icon/day/fuchen.png";
			 
		 }else if(weather_desc.indexOf("阴") != -1){
			 WEATHER_PIC="assets/images/weather/icon/day/yin.png";
			 
		 }else if(weather_desc.indexOf("沙尘暴") != -1){
			 WEATHER_PIC="assets/images/weather/icon/day/sanchengbao.png.png";
		 }
		 
		 /**查询 出 最低或最高气温 **/
		 if("".equals(min_temperature)){
			 min_temperature = String.valueOf(Integer.parseInt(max_temperature)-1);
		 }else if("".equals(max_temperature)){
			 max_temperature = String.valueOf(Integer.parseInt(min_temperature)+1);
		 }
		 
		
     String sql ="INSERT INTO sys_weather (REPORT_DATE,CITY_CODE,CITY_NAME,REPORT_TIME,WEATHER_PIC,WEATHER_DESC," +
	      		"MAX_TEMPERATURE,MIN_TEMPERATURE,RAINFALL,WIND,WIND_DIRECTION,WATER_LEVEL,GET_TIME) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
	     
	      PreparedStatement ps=conn.prepareStatement(sql);
				ps.setTimestamp(1, new java.sql.Timestamp(
						(new SimpleDateFormat("yy-MM-dd HH:mm").parse(report_date_time)).getTime()));//预报日期
				ps.setString(2, city_code);
				ps.setString(3,city_name);
				ps.setTimestamp(4, new java.sql.Timestamp(
						(new SimpleDateFormat("yy-MM-dd HH:mm").parse(report_date_time)).getTime()));//预报发布的时间 
				ps.setString(5, WEATHER_PIC);
				ps.setString(6, weather_desc);
				ps.setString(7, max_temperature);
				ps.setString(8, min_temperature);
				ps.setString(9, "");
				ps.setString(10, wind);
				ps.setString(11, wind_direction);
				ps.setString(12, "");
				ps.setDate(13,new java.sql.Date(
						new Date().getTime()));
				//ps.setString(14,city_code);
				int n = ps.executeUpdate();
			
				if(n>0){
					bs= true;
					System.out.println("插入天气 成功");
				}else{
					bs= false;
					System.out.println("插入天气失败");
				}
				
			ps.close(); 
	} catch (UnsupportedEncodingException e) {
		e.printStackTrace();
	} catch (ParseException e2) {
		e2.printStackTrace();
	} catch (Exception e3) {
		e3.printStackTrace();
	}finally{
		DBUtil.closeConnection(conn);
	}
    return bs;
		
	}



/**
 *  获取指定城市天气预报
 *  
 * @param urlAll
 *            :请求接口
 * @param httpArg
 *            :参数
 * @return 返回结果
 */
public static  String request(String httpUrl) {
	BufferedReader reader = null;
	String result = null;
	StringBuffer sbf = new StringBuffer();

	try {
		URL url = new URL(httpUrl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		InputStream is = connection.getInputStream();
		reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		String strRead = null;
		while ((strRead = reader.readLine()) != null) {
			sbf.append(strRead);
			sbf.append("\r\n");
		}
		reader.close();
		result = sbf.toString();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return result;
}
public static void main(String[] args) {
}
}
