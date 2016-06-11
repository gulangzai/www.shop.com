package com.jiuji.cn.utils.log;

import java.util.Calendar;

public class MyTime
{
	public MyTime(){

	}

    public String getTime(){
	    Calendar now = Calendar.getInstance();
	    String month = String.valueOf(now.get(Calendar.MONTH));
		String year = String.valueOf(now.get(Calendar.YEAR));
		String day = String.valueOf(now.get(Calendar.DAY_OF_MONTH));
	    return year+"-"+month+"-"+day;
	}
    
    public String getLastTime(){
    	 Calendar now = Calendar.getInstance();
 	    int month =  now.get(Calendar.MONTH);
 		String year = String.valueOf(now.get(Calendar.YEAR));
 		int day = now.get(Calendar.DAY_OF_MONTH);
 		Liyi.p(year+"|"+(month+1)+"|"+day);
 	    return year+"-"+(month+1)+"-"+(day-1);
    }
}
