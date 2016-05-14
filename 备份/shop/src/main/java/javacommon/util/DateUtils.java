package javacommon.util;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;


public class DateUtils {
	
	
	/**
	 * 获取日报当天的开始时间
	 * @param _date 时间如2010-12-2
	 * @param _hour 设置开始时间 如今天早上7：00， 则_hour=7
	 * @param style 日期的格式 yyyy-MM-dd
	 * @return
	 */
	public static final SimpleDateFormat fulSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	///获取一年当中的第一天
	public static String getOneYearDay(String date1) throws Exception{
		String date="";
		Calendar c = new GregorianCalendar();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date da=sdf.parse(date1);
		
		c.setTime(da);
		c.set(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH, 1);
		date=sdf.format(c.getTime());
		
		return date;
	}
	
     public static String getTodayTime(){  
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
	}
 	
	public static Date getStartDate(String _date, int _hour, String style){
		SimpleDateFormat sdf = new SimpleDateFormat(style);
		Calendar c = new GregorianCalendar();
		try {
			Date date = sdf.parse(_date);
			c.setTime(date);
			c.set(Calendar.HOUR_OF_DAY, _hour);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
			c.set(Calendar.MILLISECOND, 0);
		} catch (ParseException e) {
			return null;
		}
		return c.getTime();
	}
	/**
	 * 是否在两日期内
	 * @param startDate
	 * @param endDate
	 * @param date
	 * @return
	 */
	public static boolean isBetweenDay(Calendar startDate, Calendar endDate, Calendar date){
		boolean isBetween = false;
		if(date == null || startDate == null || endDate == null){
			return false;
		}
		if(date.after(startDate) && date.before(endDate)){
			isBetween = true;
		}
		return isBetween;
	}
	/**
	 * yyyy-MM-dd
	 * @param d
	 * @return
	 */
	public static String formatDate(Date d){
		return new SimpleDateFormat("yyyy-MM-dd").format(d);
	}
	/**
	 * 将日期的的一天开始时间
	 * yyyy-MM-dd
	 * 时，分，秒，微秒 设置成 00:00:00:000
	 * @param d
	 * @return
	 */
	public static Date getDateStart(String startDate){
		return getDateStart(DateUtils.getDate11(startDate));
	}
	/**
	 * 将日期的的一天开始时间
	 * 时，分，秒，微秒 设置成 00:00:00:000
	 * @param d
	 * @return
	 */
	public static Date getDateStart(Date d){
		Calendar c = new GregorianCalendar();
		if(d == null){
			return null;
		}
		c.setTime(d);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}
	/**
	 * 获取一个月的开始
	 * @param d
	 * @return
	 */
	public static Date getMonthStart(Date d){
		Calendar c = new GregorianCalendar();
		if(d == null){
			return null;
		}
		c.setTime(d);
		c.set(Calendar.DAY_OF_MONTH, 1);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}
	public static String getMonthStartStr(Date d){
		return formatDate(getMonthStart(d));
	}
	/**
	 * 将日期的的一天结束时间
	 * yyyy-MM-dd
	 * 时，分，秒，微秒 设置成 00:00:00:000
	 * @param d
	 * @return
	 */
	public static Date getDateEnd(String startDate){
		return getDateEnd(DateUtils.getDate11(startDate));
	}
	/**
	 * 将日期的的一天结束时间
	 * 时，分，秒，微秒 设置成 23:59:59:999 
	 * @param d
	 * @return
	 */
	public static Date getDateEnd(Date d){
		Calendar c = new GregorianCalendar();
		if(d == null){
			return null;
		}
		c.setTime(d);
		c.set(Calendar.HOUR_OF_DAY, 23); 
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 999);
		Date dt = new Date();
		if(dt.before(c.getTime())){
			return dt;
		}
		return c.getTime();
	}
	
	/**
	 * 将日期的的一天结束时间
	 * 时，分，秒，微秒 设置成 23:59:59:999 
	 * @param d
	 * @return
	 */
	public static Date getDateEnd(Date d1, Date d){
		Calendar c = new GregorianCalendar();
		if(d == null){
			return getMonthEnd(d1);
		}
		c.setTime(d);
		c.set(Calendar.HOUR_OF_DAY, 23); 
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 999);
		Date dt = new Date();
		if(dt.before(c.getTime())){
			return dt;
		}
		return c.getTime();
	}
	public static String getMonthEndStr(Date d){
		return formatDate(getMonthEnd(d));
	}
	/**
	 * 	系统时间
	 * 时，分，秒，微秒 设置成 当前时间 
	 * @param d
	 * @return
	 */
	public static Date getCurrent(Date d){
		Calendar c = new GregorianCalendar();
		if(d == null){
			return null;
		}
		c.setTime(d);
		c.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY)); 
		c.set(Calendar.MINUTE, c.get(Calendar.MINUTE));
		c.set(Calendar.SECOND, c.get(Calendar.SECOND));
		c.set(Calendar.MILLISECOND, c.get(Calendar.MILLISECOND));
		return c.getTime();
	}
	/**
	 * 获取一个月的结束
	 * @param d
	 * @return
	 */
	public static Date getMonthEnd(Date d){
		GregorianCalendar c = new GregorianCalendar();
		if(d == null){
			return null;
		}
		c.setTime(d);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		c.set(Calendar.HOUR_OF_DAY, 23); 
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 999);
		return c.getTime();
	}
	
	/**
	 * 获取日报当天的结束时间
	 * @param _date 时间如2010-12-2
	 * @param _hour 设置开始时间 如今天晚上723：00， 则_hour=23
	 * @param style 日期的格式 yyyy-MM-dd
	 * @return
	 */
	public static Date getEndDate(String _date, int _hour, String style){
		SimpleDateFormat sdf = new SimpleDateFormat(style);
		Calendar c = new GregorianCalendar();
		try {
			Date date = sdf.parse(_date);
			c.setTime(date);
			c.set(Calendar.HOUR_OF_DAY, _hour); 
			c.set(Calendar.MINUTE, 59);
			c.set(Calendar.SECOND, 59);
			c.set(Calendar.MILLISECOND, 999);
		} catch (ParseException e) {
			return null;
		}
		return c.getTime();
	}
	
	/**
	 * 获取日期表示 格式为MM月dd-MM月dd
	 * @param startC
	 * @param c
	 * @return
	 */
	public static String getDayShort(Calendar startC, Calendar c){
		String dayShort = "";
		  if(startC.get(Calendar.DAY_OF_YEAR) == c.get(Calendar.DAY_OF_YEAR) ){
			  dayShort = new SimpleDateFormat("M月d").format(startC.getTime());
		  }else{
			  dayShort = new SimpleDateFormat("M月d").format(startC.getTime()) + "-"
		  		+ new SimpleDateFormat("d").format(c.getTime());
		  }
		return dayShort;
	}

	/**
	 * 返回日历 日期格式为 yyyy-MM-dd
	 * @param date
	 * @return
	 */
	public static Calendar getDate(String date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = new GregorianCalendar();
		try{
			c.setTime(sdf.parse(date));
		}catch (Exception e) {
			return null;
		}
		return c;
	}
	/**
	 * 返回 日期格式为 yyyy-MM-dd
	 * @param date
	 * @return
	 */
	public static Date getDate11(String date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = null;
		try{
			d = sdf.parse(date);
		}catch (Exception e) {
		}
		return d;
	}
	
	/**
	 * 返回 日期格式为 yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return
	 */
	public static Date getDate12(String date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = null;
		try{
			d = sdf.parse(date);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return d;
	}
	
	/**
	 * 返回 日期格式为 yyyy-MM
	 * @param date
	 * @return
	 */
	public static Date getDate13(String date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Date d = null;
		try{
			d = sdf.parse(date);
		}catch (Exception e) {
		}
		return d;
	}
	/**
	 * 返回 日期格式为 yyyy-M-d
	 * @param date
	 * @return
	 */
	public static Date getDate2(String date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");
		Date d = null;
		try{
			d = sdf.parse(date);
		}catch (Exception e) {
		}
		return d;
	}
	/**
	 * 返回 pattern【7704#】格式的报表名
	 * @param riBaoName
	 * @param date
	 * @return
	 */
	public static String getDateName(String riBaoName, String date, String pattern){
		String ret = null;
		SimpleDateFormat sdf;
		Calendar c;
		try {
			sdf = new SimpleDateFormat("yyyy-MM-dd");
			c = new GregorianCalendar();
			c.setTime(sdf.parse(date));
			sdf = new SimpleDateFormat(pattern);
			ret = sdf.format(c.getTime()) + riBaoName;
		} catch (ParseException e) {

		}
		return ret;
	}
	/**
	 * 返回 pattern【7704#】格式的报表名
	 * @param riBaoName
	 * @param date
	 * @return
	 */
	public static String getDateName(String riBaoName, Date date, String pattern){
		String ret = null;
		SimpleDateFormat sdf;
		Calendar c;
		try {
			c = new GregorianCalendar();
			c.setTime(date);
			sdf = new SimpleDateFormat(pattern);
			ret = sdf.format(c.getTime()) + riBaoName;
		} catch(Exception e) {
			
		}
		return ret;
	}
	/**
	 * 获取小时数
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static double getHours(Date d1, Date d2){
		double re = 0d;
		if(d2 == null || d1 == null){
			return re;
		}
		try{	
		    long diff =   Math.abs(d2.getTime() - d1.getTime());
		    re = Math.rint((diff / (double)(1000 * 60 * 60)) * 100)/100;
		}
		catch (Exception e){
			
		}
		return re;
	}
	/**
	 * 返回日期
	 * @param date 日期字条串
	 * @param pattern 日期格式
	 * @return
	 * @throws Exception
	 */
	public static Date getDate(String date, String pattern) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.parse(date);
	}
	/**
	 * 比较时间
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static boolean compare(Date d1,Date d2){
		if(d1.getTime()>d2.getTime()){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 获得天数
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static double getDays(Date d1, Date d2){
		double re = 0d;
		if(d2 == null || d1 == null){
			return re;
		}
		try{	
		    long diff =   Math.abs(d2.getTime() - d1.getTime());
		    re = Math.rint((diff / (double)(1000 * 60 * 60 * 24)) * 100)/100;
		}
		catch (Exception e){
			
		}
		return re;
	}
	// 小时 * 100 整型
	public static int getHours100(Date d1, Date d2)
	{ return (int)(getHours(d1,d2)*100); }
	
	/**
	 * 取得tempDate月份的考核时间, 剔除23-7点数据
	 * @param d1 开始时间
	 * @param d2 结束时间
	 * @param tempDate 考核结束时间
	 * @return
	 */
	public static double getExamineHours(Date d1, Date d2, Date tempDate){
		if(d2 == null){
			if(tempDate == null){
				return 0;
			}else{
				Date tempStartDate = DateUtils.getMonthStart(tempDate); //月初				
				if(d1.before(tempStartDate)){ //如果开始时间在本月之前，就把开始时间设置在月初
					d1 = tempStartDate;
				}
				d2 = tempDate;
			}
		}else{
			if(d2.after(tempDate)){ //如果结束时间在考核时间之后，则把结束时间设置为tempDate
				d2 = tempDate;
			}
		}
		return getExamineHours(d1, d2, 23, 7);
	}
	/**
	 * 获取本月考核时间小时数
	 * 时间段 startHour-次日endHour 不计算如 23:00-7:00
	 * @param d1 开始时间
	 * @param d2 结束时间
	 * @param endHour 
	 * @param startHour 
	 * @return
	 */
	public static double getExamineHours(Date d1, Date d2, int startHour, int endHour){
		if(d1 == null){
			return 0;
		}
		if(d2 == null){
			d2 = new Date();
		}
		if(d1.after(d2)){
			return 0;
		}
		Calendar c1 = new GregorianCalendar();
		Calendar c2 = new GregorianCalendar();
		c1.setTime(d1);
		c2.setTime(d2);
		if(c1.get(Calendar.MONTH) != c2.get(Calendar.MONTH)){
			c1.setTime(getMonthStart(c2.getTime()));
		}
		int diff = getHours100(d1,d2);
		if ( diff == 0 ) return diff ;
		
		if ( d2.getTime() < d1.getTime() ) { Date tmp = d2; d2=d1;d1=tmp; }
		
		int hour = 0;

		hour = c1.get(Calendar.HOUR_OF_DAY);
		if ( hour >= startHour) {
			c1.set(Calendar.DAY_OF_YEAR, c1.get(Calendar.DAY_OF_YEAR) + 1); //加一天
			c1.set(Calendar.HOUR_OF_DAY, (endHour-1));
			c1.set(Calendar.MINUTE, 59);
			c1.set(Calendar.SECOND, 59);
			c1.set(Calendar.MILLISECOND, 999);
		}else if(hour < endHour){
			c1.set(Calendar.HOUR_OF_DAY, (endHour-1));
			c1.set(Calendar.MINUTE, 59);
			c1.set(Calendar.SECOND, 59);
			c1.set(Calendar.MILLISECOND, 999);
		}
		hour = c2.get(Calendar.HOUR_OF_DAY);
		if ( hour >= startHour) {
			c2.set(Calendar.HOUR_OF_DAY, (startHour-1));
			c2.set(Calendar.MINUTE, 59);
			c2.set(Calendar.SECOND, 59);
			c2.set(Calendar.MILLISECOND, 999);
		}else if(hour < endHour){
			c2.set(Calendar.DAY_OF_YEAR, c2.get(Calendar.DAY_OF_YEAR) - 1); //减一天
			c2.set(Calendar.HOUR_OF_DAY, (startHour-1));
			c2.set(Calendar.MINUTE, 59);
			c2.set(Calendar.SECOND, 59);
			c2.set(Calendar.MILLISECOND, 999);
		}
		if(c1.after(c2)){
			System.out.println(c1.getTime() + ", c2:" + c2.getTime());
			return 0;
		}
		diff = getHours100(c1.getTime(), c2.getTime());
		int days = betweenDays(c1, c2);
		diff -= days*(24 - startHour + endHour)*100;
		if(diff < 0){
			diff = 0;
		}
		if(diff/100 > 500){
			System.out.println(diff/100);
		}
		return (double)diff/100 ;
	}	
	
	/**
	 * 获取考核时间小时数
	 * 时间段 20：00-次日8：00 不计算
	 * @param d1 开始时间
	 * @param d2 结束时间
	 * @return
	 */
	public static double ExamineHours(Date d1, Date d2 ){
		return getExamineHours(d1, d2, 20, 8);
	}	
/**
	 * 获取天数 同一天为0， 隔一天为1
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static int betweenDays(Calendar beginDate, Calendar endDate) {
        if (beginDate.get(Calendar.YEAR) == endDate.get(Calendar.YEAR)) {
            return endDate.get(Calendar.DAY_OF_YEAR)
                    - beginDate.get(Calendar.DAY_OF_YEAR);
        } else {
            if (beginDate.getTimeInMillis() < endDate.getTimeInMillis()) {
                int days = beginDate.getActualMaximum(Calendar.DAY_OF_YEAR)
                        - beginDate.get(Calendar.DAY_OF_YEAR)
                        + endDate.get(Calendar.DAY_OF_YEAR);
                for (int i = beginDate.get(Calendar.YEAR) + 1; i < endDate
                        .get(Calendar.YEAR); i++) {
                    Calendar c = Calendar.getInstance();
                    c.set(Calendar.YEAR, i);
                    days += c.getActualMaximum(Calendar.DAY_OF_YEAR);
                }
                return days;
            } else {
                int days = endDate.getActualMaximum(Calendar.DAY_OF_YEAR)
                        - endDate.get(Calendar.DAY_OF_YEAR)
                        + beginDate.get(Calendar.DAY_OF_YEAR);
                for (int i = endDate.get(Calendar.YEAR) + 1; i < beginDate
                        .get(Calendar.YEAR); i++) {
                    Calendar c = Calendar.getInstance();
                    c.set(Calendar.YEAR, i);
                    days += c.getActualMaximum(Calendar.DAY_OF_YEAR);
                }
                return days;
            }
        }
    }
	
    public static String f(Date d)
    {
     return (new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")).format(d);	
    }
    
    public static String getDateString(Date d)
    {
     return (new java.text.SimpleDateFormat("yyyy-MM-dd")).format(d);	
    }

    
    /**
     * 日期格式转化字符串
     * @param stime
     * @return
     * @throws ParseException 
     */
    public static String dateToString(Date stime, String format){
    	if(stime==null){
    		return "";
    	}
    	SimpleDateFormat myFmt=new SimpleDateFormat(format);
    	
        String temp;
		try {
			temp = myFmt.format(stime);
			return temp;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "";
		}
    	
    }
    
    /**
     * 格式化时间 后时分秒都为0
     * @param stime
     * @return
     * @throws ParseException 
     */
    public static Date stringToDate(String time,String format){
    	if(time==null||"".equals(time.trim())){
    		return null;
    	}
    	SimpleDateFormat myFmt=new SimpleDateFormat(format);
    	Date stime = null;
		try {
			stime = (Date) myFmt.parseObject(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return stime;
    }
    
    
	public static void main(String args[]) throws Exception{
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date d1= sdf.parse("2011-5-2 14:18:20");
//		Date d2= sdf.parse("2011-5-31 23:59:59");
//		System.out.println("d1="+f(d1)+"\nd2="+f(d2));
//		System.out.println(getExamineHours(d1,d2, 23, 7));
		Date date = new Date();
//		System.out.print(dateToString(reduceDay(date,1),"yyyy-MM-dd HH:mm:ss"));
		System.out.print(dateToString(addDay(date,0),"yyyy-MM-dd HH:mm:ss"));
	
	}
	/**
	 * 是否在同一时间段时，按秒计算
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static boolean isTheSameTime(Date d1, Date d2){
		boolean re = false;
		if(d1 == null || d2 == null){
			return re;
		}
		try{
		    long diff =   Math.abs(d2.getTime() - d1.getTime());
		     if(diff < 2000){ //两时间差在同一秒内
		    	 Calendar c1 = new GregorianCalendar();
		    	 Calendar c2 = new GregorianCalendar();
		    	 
		    	 c1.setTime(d1);
		    	 c2.setTime(d2);
		    	 
		    	 if(c1.get(Calendar.SECOND) == c2.get(Calendar.SECOND)){ //且秒数相同
		    		 re = true;
		    	 }
		     }
		}
		catch (Exception e){
			
		}
		return re;
	}
	/**
	 * 获取时间long 精确到秒
	 * @param d
	 * @return
	 */
	public static long getTimeAtSecond(Date d){
		Calendar c = new GregorianCalendar();
		c.setTime(d);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTimeInMillis();
	}
	public static String reduceOneDay(String date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = new GregorianCalendar();
		try {
			c.setTime(sdf.parse(date));
			c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) -1);
		} catch (ParseException e) {
			return null;
		}
		return sdf.format(c.getTime());
	}
	public static Date reduceOneDay(Date date){
		Calendar c = new GregorianCalendar();
		if(date == null){
			return null;
		}
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) -1);
		return c.getTime();
	}
	public static Date reduceDay(Date date, int day){
		Calendar c = new GregorianCalendar();
		if(date == null){
			return null;
		}
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) -day);
		return c.getTime();
	}
	
	public static Date addDay(Date date, int day){
		Calendar c = new GregorianCalendar();
		if(date == null){
			return null;
		}
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) +day);
		return c.getTime();
	}
	
	public static String addOneDay(String date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = new GregorianCalendar();
		try {
			c.setTime(sdf.parse(date));
			c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + 1);
		} catch (ParseException e) {
			return null;
		}
		return sdf.format(c.getTime());
	}
	public static String addOneDay(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = new GregorianCalendar();
		try {
			c.setTime(date);
			c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + 1);
		} catch (Exception e) {
			return null;
		}
		return sdf.format(c.getTime());
	}
	public static Date addOneDay2(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = new GregorianCalendar();
		try {
			c.setTime(date);
			c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + 1);
		} catch (Exception e) {
			return null;
		}
		return c.getTime();
	}
	/**
	 *yyyyMMdd
	 * @param date
	 * @return
	 */
	public static String addOneDay2(String date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar c = new GregorianCalendar();
		try {
			c.setTime(sdf.parse(date));
			c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + 1);
		} catch (ParseException e) {
			return null;
		}
		return sdf.format(c.getTime());
	}
	public static String reduceOneMonth(String date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = new GregorianCalendar();
		try {
			c.setTime(sdf.parse(date));
			c.set(Calendar.DAY_OF_MONTH, 1);
			c.set(Calendar.MONTH, c.get(Calendar.MONTH) - 1);
		} catch (ParseException e) {
			return null;
		}
		return sdf.format(c.getTime());
	}
	public static Date reduceOneMonth(Date date){
		Calendar c = new GregorianCalendar();
		try {
			c.setTime(date);
			c.set(Calendar.DAY_OF_MONTH, 1);
			c.set(Calendar.MONTH, c.get(Calendar.MONTH) - 1);
		} catch (Exception e) {
			return null;
		}
		return c.getTime();
	}
	
	/**
//	 * 返回日期表示形式
	 * 	Wed, 9 Mar 2011 10:07:32 UTC 
	 * @return
	 */
	public static String getNowDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss", new DateFormatSymbols(Locale.US));
		return sdf.format(new Date()) + " UTC";
	}
	
	public static String addOneDays(String date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar c = new GregorianCalendar();
		try {
			c.setTime(sdf.parse(date));
			c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH));
		} catch (ParseException e) {
			return null;
		}
		System.out.println(sdf.format(c.getTime()));
		return sdf.format(c.getTime());
	}
	public static String getToday(){
		return new SimpleDateFormat("yyyyMMdd").format(new Date());
	}
	/**
	 * 格式 yyyy-MM-dd
	 * @return
	 */
	public static String getToday2(){
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	}
	public static String addTwoMonth(String date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = new GregorianCalendar();
		try {
			c.setTime(sdf.parse(date));
			c.set(Calendar.MONTH, c.get(Calendar.MONTH) - 2);
		} catch (ParseException e) {
			return null;
		}
		return sdf.format(c.getTime());
	}
	/**
	 * 文本转日期类型
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static Date parseDate(String date, String pattern){
		Date d = null;
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			d = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}
	
	public static String addOneMonth(String date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = new GregorianCalendar();
		try {
			c.setTime(sdf.parse(date));
			c.set(Calendar.MONTH, c.get(Calendar.MONTH) - 1);
		} catch (ParseException e) {
			return null;
		}
		return sdf.format(c.getTime());
	}
	     ///主方法进行测试
	/**
	 * 返回月份表示
	 * 格式 yyyyMMdd
	 * 
	 */
	public static String getMonth(String startDate, int months){
		String result = "";
		SimpleDateFormat sdf = new SimpleDateFormat("M月");
		try {
			Calendar c  = new GregorianCalendar();
			c.setTime(DateUtils.getDate(startDate, "yyyyMMdd"));
			c.set(Calendar.MONTH, c.get(Calendar.MONTH) + months);
			result = sdf.format(c.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 返回上个月份开始日期表示
	 * 格式 yyyyMMdd
	 * 
	 */
	public static String getLastStartDate(String startDate, String goToMonth) {
		String lastStartDate = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		try {
			Date d = DateUtils.getDate(startDate, "yyyyMMdd");
			d = DateUtils.reduceOneMonth(d);
			lastStartDate = sdf.format(d);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lastStartDate;
	}
	
	/**
	 * 返回上个月份结束日期表示
	 * 格式 yyyyMMdd
	 * 
	 */
	public static String getLastEndDate(String startDate, String goToMonth) {
		String lastEndDate = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		try {
			Date d = DateUtils.getDate(startDate, "yyyyMMdd");
			d = DateUtils.reduceOneMonth(d);
			d = DateUtils.getMonthEnd(d);
			lastEndDate = sdf.format(d);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lastEndDate;
	}
	public static String switchDateStyle(String date, String oldPattern, String newPattern) throws Exception{
		Date d = getDate(date, oldPattern);
		String result = "";
		if(d != null){
			result = new SimpleDateFormat(newPattern).format(d);
		}
		return result;
	}
	public static Date addDateMinut(int x)//返回的是字符串型的时间，输入的
	//是String day, int x
	 {   
	        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 24小时制  
        	//引号里面个格式也可以是 HH:mm:ss或者HH:mm等等，很随意的，不过在主函数调用时，要和输入的变
	        //量day格式一致
	        Date date = new Date();   
	        System.out.println("front:" + format.format(date)); //显示输入的日期  
	        Calendar cal = Calendar.getInstance();   
	        cal.setTime(date);   
	        cal.add(Calendar.MINUTE, x);// 24小时制   
	        date = cal.getTime();   
	        System.out.println("after:" + format.format(date));  //显示更新后的日期 
	        cal = null;   
	        String date1=format.format(date);
	        return getDate12(date1);   
	    }  
	
	public static Date getAddTime(String begin,int day){
		
		   SimpleDateFormat sf = new SimpleDateFormat(begin);
	        Calendar c = Calendar.getInstance();
	        c.add(Calendar.DAY_OF_MONTH, day);
	        String format = sf.format(c.getTime());
		return getDate11(format);
	}
		public static String getStudentTime(Date d1, Date d2){
			String time = null;
			try{
			  long diff = d1.getTime() - d2.getTime();//这样得到的差值是微秒级别
			  long days = diff / (1000 * 60 * 60 * 24);
			 
			  long hours = (diff-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60);
			  long minutes = (diff-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60))/(1000* 60);
			  time = hours+"小时"+minutes+"分";
			}
			catch (Exception e)
			{
			}
			return time;
		}
		
		public static long getTime(String lastLoginTime){
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	    	java.util.Date lastLoginTimeDate;
	    	long lastLoginTimeDatenum=0;
			try {
				lastLoginTimeDate = sdf.parse(lastLoginTime);
				lastLoginTimeDatenum = lastLoginTimeDate.getTime();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			return lastLoginTimeDatenum;
		}
}
