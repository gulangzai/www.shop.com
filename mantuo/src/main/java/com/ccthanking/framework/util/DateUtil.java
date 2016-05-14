package com.ccthanking.framework.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	/**
	 * 判断邀请是否过期
	 * @param odate
	 * @return 过期返回ture  
	 */
	public static boolean isOverdue(String odate) {
		long quot = -1;
		// 获取当前时间，转换传过来的时间
		if (odate != null && !odate.equals("")) {
			try {
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				Date time1 = df.parse(odate);
				Date time2 = df.parse(df.format(new Date()));
				Date date1 = time1;
				Date date2 = time2;
				quot = date1.getTime() - date2.getTime();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
		return quot < 0;
	}
	
	/**
	 * 将字符串转换成日期
	 * @param dateStr
	 * @return
	 */
	public static Date formatDate(String dateStr){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	} 
	
	public static String getDateString(Date date){ 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		return sdf.format(date);
	}
 
	
	/**
	 * 将字符串转换成日期
	 * @param dateStr
	 * @return
	 */
	public static Date formatDate2(String dateStr){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 将字符串转换成日期
	 * @param dateStr  需要转换的字符串
	 * @param template 转换格式
	 * @return
	 */
	public static Date formatDate(String dateStr,String template){
		SimpleDateFormat sdf = new SimpleDateFormat(template);
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	} 
}
