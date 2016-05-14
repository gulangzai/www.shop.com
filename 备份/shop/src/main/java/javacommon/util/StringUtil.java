package javacommon.util;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;





public class StringUtil {
	
	public static String isMobile="1[3458][0-9]{9,9}";
	
	public static String isUserName="[a-zA-Z0-9\u2E80-\uFE4F]+";
	
	public static String isEmail="([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+";
	
	public static void main(String[] args) {
		
		System.out.println(formatDate("6月19日"));
		
	}
	
public static String[] danans;
	
	public static String[] stringSplit(String souces){
		String[] anwsers= souces.split(",");
		return anwsers;
	}
	
	
	
	
	public static String getCategoryName(String pageTypes, String major){
		String categoryName="";
		if("1".equals(pageTypes)&&"2".equals(major))
			categoryName="建设工程法规及相关知识";
		if("2".equals(pageTypes)&&"2".equals(major))
			categoryName="建设工程施工管理";
		if("3".equals(pageTypes)&&"2".equals(major))
			categoryName="建筑工程管理实务";
		if("4".equals(pageTypes)&&"2".equals(major))
			categoryName="机电工程管理实务";
		if("5".equals(pageTypes)&&"2".equals(major))
			categoryName="市政公用工程管理实务";
		if("6".equals(pageTypes)&&"2".equals(major))
			categoryName="入学测试";
		if("7".equals(pageTypes)&&"1".equals(major))
			categoryName="建设工程经济";
		if("8".equals(pageTypes)&&"1".equals(major))
			categoryName="建设工程法规及相关知识";
		if("9".equals(pageTypes)&&"1".equals(major))
			categoryName="建设工程项目管理";
		if("10".equals(pageTypes)&&"1".equals(major))
			categoryName="市政公用工程管理实务";
		if("11".equals(pageTypes)&&"1".equals(major))
			categoryName="建筑工程管理实务";
		if("12".equals(pageTypes)&&"1".equals(major))
			categoryName="机电工程管理实务";

		
		
		return categoryName;
	}
	
	public static String imgName(){
		String rowdom="1234567890qwertyuiopasdfghjklzxcvbnm";
		Random r=new Random();
		int n=rowdom.length();
			String code="";
			for(int i=0;i<8;i++){
				code=code+rowdom.charAt(r.nextInt(n));
			}
			return code;
			
		}
	
	public static String getCurrentDate(){
		Date date=new Date();
	    
		SimpleDateFormat format=new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		
		return format.format(date).toString();
	
	}
	
	//获取当前时间 date类型
	public static String getCurrentDatetime(){
		Date date=new Date();
	    
		SimpleDateFormat format=new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		
		return format.format(date);
	
	}
	public static String formatDate(String currentDate){
		String myDate=currentDate.trim();
		
		String myMonth=myDate.substring(0,myDate.indexOf("月"));
		
		if(Integer.parseInt(myMonth)<10)
			 myMonth="0"+myMonth;
		
		String myDay=myDate.substring(myDate.indexOf("月")+1,myDate.indexOf("日"));
		
		if(Integer.parseInt(myDay)<10)
			myDay="0"+myDay;
		
		
		
		
		return "2015-"+myMonth+"-"+myDay;
		
		
	}


	public static String getCategoryName(String[] pageTypes) {
		String categoryName="";
		if("1".equals(pageTypes))
			categoryName="建设工程法规及相关知识";
		if("2".equals(pageTypes))
			categoryName="建设工程施工管理";
		if("3".equals(pageTypes))
			categoryName="建筑工程管理实务";
		if("4".equals(pageTypes))
			categoryName="机电工程管理实务";
		if("5".equals(pageTypes))
			categoryName="市政公用工程管理实务";
		if("6".equals(pageTypes))
			categoryName="入学测试";

		
		return categoryName;
	}
	

	public static String getCategoryNames(String pageTypes) {
		String categoryName="";
		if("1".equals(pageTypes))
			categoryName="建设工程法规及相关知识";
		if("2".equals(pageTypes))
			categoryName="建设工程施工管理";
		if("3".equals(pageTypes))
			categoryName="建筑工程管理实务";
		if("4".equals(pageTypes))
			categoryName="机电工程管理实务";
		if("5".equals(pageTypes))
			categoryName="市政公用工程管理实务";
		if("6".equals(pageTypes))
			categoryName="入学测试";

		
		return categoryName;
	}
	
	public static String getKey(){
		return UUID.randomUUID().toString();
	} 

}
