package javacommon.util;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SafeUtils {
	public static final String DATE_FORMAT = "yyyy-MM-dd";

	public static final String TIME_FORMAT = "HH:mm:ss";

	public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss.S";
	public static java.sql.Date getSqlDate(Object obj) {
		java.sql.Date tmpdate = null;
		if (obj != null) {
			try {
				tmpdate = java.sql.Date.valueOf(obj.toString());
			} catch (Exception ex) {
				// log.error("getDate :"+ex);
			}
		}
		return tmpdate;
	}

	/***
	 * 毫秒转换成时分秒
	 * 
	 * @param time
	 * @return
	 */
	public static String getTime(long time) {
		String str = "";
		time = time / 1000;
		int s = (int) (time % 60);
		int m = (int) (time / 60 % 60);
		int h = (int) (time / 3600);
		str = h + "时" + m + "分" + s + "秒";
		System.out.println(str);
		return str;
	}

	public static long getLongTime(String time) {
		// String time="1时20分11秒";
		long s = Integer.parseInt(time.substring(0, time.indexOf("时"))) * 3600 * 1000; // 小时
		s += Integer.parseInt(time.substring(time.indexOf("时") + 1, time
				.indexOf("分"))) * 60 * 1000; // 分钟
		s += Integer.parseInt(time.substring(time.indexOf("分") + 1, time
				.indexOf("秒"))) * 1000; // 秒
		//System.err.println(s);
		return s;
	}

	/**
	 * 获取当前毫秒时间
	 * 
	 * @return
	 */
	public static long getCurrentMillisecondTime() {
		long tmpCurrentTime = System.currentTimeMillis();
		return tmpCurrentTime;
	}

	public static java.sql.Date getSqlDate(String obj) {
		java.sql.Date tmpdate = null;
		if (obj != null) {
			try {
				tmpdate = java.sql.Date.valueOf(obj.toString());
			} catch (Exception ex) {
				// log.error("getDate :"+ex);
			}
		}
		return tmpdate;
	}

	public static String getUUID() {
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.replaceAll("-", "");
		return uuid;
	}


	private static AtomicInteger contentIdIntger = new AtomicInteger(1);
	 public static java.util.Date getCurrentTime(){
	        Calendar   ca           =   Calendar.getInstance();
	        java.util.Date   tmpnow     =   new java.util.Date(ca.getTimeInMillis());
	        return tmpnow;
	    }
	public synchronized static long getNextContentIdId() {
		if (contentIdIntger.get() >= 100) {
			contentIdIntger.set(1);
		}

		try {
			Thread.sleep(10);
		} catch (Exception e) {
			// TODO: handle exception
		}
		String str = "" + System.currentTimeMillis();
		str = str.substring(0, str.length() - 1);

		String str2 = "000" + contentIdIntger.getAndIncrement();
		str2 = str2.substring(str2.length() - 2, str2.length());

		return getLong(str + getLocalIPFlag() + str2);
	}


	public static int getCurrentUnixTime() {
		int tmpCurrentTime = (int) (System.currentTimeMillis() / 1000);
		return tmpCurrentTime;
	}

	private static Log log = LogFactory.getLog(SafeUtils.class.getName());

	public static int getInt(Object obj) {
		int tmpret = 0;
		if (obj != null) {
			try {
				tmpret = Integer.parseInt(obj.toString());
			} catch (Exception ex) {

			}
		}
		return tmpret;
	}

	public static byte[] readDate(InputStream in) {
		try {
			// int firstChar = in.read();
			// int length = in.available();
			// System.out.println(length+1);
			// byte[] array = new byte[length+1];
			// array[0] = (byte)firstChar;
			// in.read(array,1,length);

			int bufferSize = 1024 * 8;
			InputStream reader = in;
			byte[] buff = new byte[bufferSize];
			int i = 0;
			List list = new ArrayList(bufferSize);
			int read = reader.read();
			if (read != -1) {
				list.add((byte) read);
			}
			while (read != -1) {
				read = reader.read();
				if (read != -1) {
					list.add((byte) read);
				}
			}

			byte[] b3 = new byte[list.size()];

			for (int j = 0; j < b3.length; j++) {
				b3[j] = ((Byte) list.get(j)).byteValue();
			}
			reader.close();
			in.close();
			return b3;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getIp(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;

	}

	public static Long getLong(Object obj) {
		if (obj == null)
			return null;
		if (isDia(obj)) {
			Long tmpret = Long.valueOf(obj.toString().trim());
			return tmpret;
		}
		return null;
	}

	public static Long getLong(Object obj, Long defvalue) {
		Long tmpret = defvalue;
		if (obj != null) {
			try {
				tmpret = Long.parseLong(obj.toString());
			} catch (Exception ex) {
			}
		}
		return tmpret;
	}

	public static int getInt(Object obj, int defvalue) {
		int tmpret = defvalue;
		if (obj != null) {
			try {
				tmpret = Integer.parseInt(obj.toString());
			} catch (Exception ex) {

			}
		}
		return tmpret;
	}

	public static Integer getInteger(Object obj) {
		Integer tmpret = new Integer(0);
		if (obj != null) {
			try {
				tmpret = Integer.valueOf(obj.toString());
			} catch (Exception ex) {
				log.error("getInteger:" + ex);
			}
		}
		return tmpret;
	}

	public static Integer getInteger(Object obj, int defvalue) {
		Integer tmpret = new Integer(defvalue);
		if (obj != null) {
			try {
				tmpret = Integer.valueOf(obj.toString());
			} catch (Exception ex) {
				log.error("getInteger:" + ex);
			}
		}
		return tmpret;
	}

	/**
	 * 判断对象是否可安全转为为整数
	 * */
	public static boolean isDia(Object obj) {
		if (obj == null || ("" + obj).trim().equals("")) {
			return false;
		}
		String s = "" + obj;
		char[] c = s.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] > '9' || c[i] < '0') {
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断对象是否可安全转为为4位年份
	 * */
	public static boolean isYear(Object obj) {
		if (obj == null || ("" + obj).trim().equals("")) {
			return false;
		}
		String s = "" + obj;
		char[] c = s.toCharArray();
		if (c.length != 4) {
			return false;
		}
		if (c[0] == 0) {
			return false;
		}
		for (int i = 0; i < c.length; i++) {
			if (c[i] > '9' || c[i] < '0') {
				return false;
			}
		}
		return true;
	}

	public static boolean checkDate(String date, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		Date d = null;
		try {
			d = df.parse(date);
		} catch (Exception e) {
			// 如果不能转换,肯定是错误格式
			return false;
		}
		String s1 = df.format(d);
		// 转换后的日期再转换回String,如果不等,逻辑错误.如format为"yyyy-MM-dd",date为
		// "2006-02-31",转换为日期后再转换回字符串为"2006-03-03",说明格式虽然对,但日期
		// 逻辑上不对.
		return date.equals(s1);
	}

	public static double getDouble(Object obj) {
		double tmpvalue = 0;
		try {
			tmpvalue = Double.parseDouble(obj.toString());
		} catch (Exception ex) {
			log.error("getDouble :" + ex);
		}
		return tmpvalue;
	}

	public static double getDouble(Object obj, double defvalue) {
		double tmpvalue = defvalue;
		try {
			tmpvalue = Double.parseDouble(obj.toString());
		} catch (Exception ex) {
			log.error("getDouble :" + ex);
		}
		return tmpvalue;
	}

	public static String getString(Object obj) {
		String tmpret = "";
		if (obj != null)
			tmpret = obj.toString();
		return tmpret.trim();
	}

	public static String getStringWithUrlEncode(Object obj) {
		String tmpret = "";
		if (obj != null) {
			try {
				tmpret = URLDecoder.decode(obj.toString(), "utf-8");
			} catch (Exception ex) {
				log.error("getStringWithUrlEncode:" + ex);
			}
		}
		return tmpret.trim();
	}

	public static java.util.Date getDate(Object obj, String format) {
		java.util.Date tmpdate = null;
		if ((format == null) || (format.equals("")))
			format = "yyyy-MM-dd HH:mm:ss";
		java.text.SimpleDateFormat s = new java.text.SimpleDateFormat(format);
		if (obj != null) {
			try {
				if (obj.toString().startsWith("1-")) {
					return tmpdate;
				}
				tmpdate = new java.util.Date(s.parse(obj.toString().trim())
						.getTime());
			} catch (Exception ex) {
				log.error("getDate :" + ex);
			}
		}
		return tmpdate;
	}

	public static Date getSqlCurrentTime() {
		Calendar ca = Calendar.getInstance();
		Date tmpnow = new Date(ca.getTimeInMillis());
		return tmpnow;
	}

	public static String getSqlCurrentTimeStr(String format) {
		String tmpret = "";
		try {
			Calendar ca = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			tmpret = sdf.format(ca.getTime());
		} catch (Exception ex) {
			log.error("getCurrentTimeStr:" + ex);
		}
		return tmpret;
	}

	public static String getSqlCurrentTimeStr(int seconds, String format) {
		String tmpret = "";
		try {
			Calendar ca = Calendar.getInstance();
			ca.add(Calendar.SECOND, seconds);
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			tmpret = sdf.format(ca.getTime());
		} catch (Exception ex) {
			log.error("getCurrentTimeStr:" + ex);
		}
		return tmpret;
	}

	public static String formatDate(java.util.Date date, String format) {
		String tmpdatestr = "";
		try {
			SimpleDateFormat f = new SimpleDateFormat(format);
			tmpdatestr = f.format(date);
		} catch (Exception ex) {
			log.error("formatDate :" + ex);
		}
		return tmpdatestr;
	}

	public static String formatObjectDate(Object date, String format) {
		String tmpdatestr = "";
		try {
			SimpleDateFormat f = new SimpleDateFormat(format);
			tmpdatestr = f.format(date);
		} catch (Exception ex) {
			log.error("formatDate :" + ex);
		}
		return tmpdatestr;
	}

	public static java.util.Date getUtilDate(Date date) {
		java.util.Date tmpUtilDate = new java.util.Date(date.getTime());
		return tmpUtilDate;
	}

	public static Date getUtilDate(java.util.Date date, String format) {
		return (Date) date;
	}

	public static String formatDouble(double number, String format) {
		String tmpret = "";
		try {
			DecimalFormat df = new DecimalFormat(format);
			df.format(number);
		} catch (Exception ex) {
			log.error(" formatDobule:" + ex);
		}
		return tmpret;
	}

	public static String getCurrentTimeStr(String format) {
		String tmpret = "";
		try {
			Calendar ca = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			tmpret = sdf.format(ca.getTime());
		} catch (Exception ex) {
			log.error("getCurrentTimeStr:" + ex);
		}
		return tmpret;
	}

	public static String getCurrentTimeAddDayStr(String format, int day) {
		String tmpret = "";
		try {
			Calendar ca = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			ca.add(Calendar.DAY_OF_MONTH, day); // 目前時間加天数
			tmpret = sdf.format(ca.getTime());
		} catch (Exception ex) {
			log.error("getCurrentTimeStr:" + ex);
		}
		return tmpret;
	}

	public static String getCurrentTimeAddMinuteStr(String format, int minute) {
		String tmpret = "";
		try {
			Calendar ca = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			ca.add(Calendar.MINUTE, minute); // 目前時間加minute分钟
			tmpret = sdf.format(ca.getTime());
		} catch (Exception ex) {
			log.error("getCurrentTimeStr:" + ex);
		}
		return tmpret;
	}

	public static String getCurrentTimeAddHourStr(String format, int hour) {
		String tmpret = "";
		try {
			Calendar ca = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			ca.add(Calendar.HOUR, hour); // 目前時間加hour小時
			tmpret = sdf.format(ca.getTime());
		} catch (Exception ex) {
			log.error("getCurrentTimeStr:" + ex);
		}
		return tmpret;
	}

	public static String getCurrentTimeAddMonthStr(String format, int month) {
		String tmpret = "";
		try {
			Calendar ca = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			ca.add(Calendar.MONTH, month); // 目前時間加月份
			tmpret = sdf.format(ca.getTime());
		} catch (Exception ex) {
			log.error("getCurrentTimeStr:" + ex);
		}
		return tmpret;
	}

	private static AtomicInteger atomicInteger = new AtomicInteger();;

	public static int getAddInt() {
		if (atomicInteger.get() == 10000) {
			atomicInteger.set(10);
		}
		return atomicInteger.getAndIncrement();
	}

	// ftp文件是否存在
	public static boolean isFtpFileExist(String ftpUrl) {
		try {
			String[] values = getSingleMatchValue(ftpUrl);
			FtpUtil ftpUtil = new FtpUtil(values[2], getInt(values[3]),
					values[0], values[1]);
			ftpUtil.login();
			ftpUtil.fileNames(values[4]);
			ftpUtil.logout();
			return true;
		} catch (Exception e) {
			log.error(e.getMessage());
			return false;
		}
	}

	public static String[] getSingleMatchValue(String str) {
		String[] values = null;
		if (str == null) {
			return values;
		}

		String[] strs = str.split("://");
		if (strs == null || strs.length == 0) {
			return values;
		}
		if (strs == null || strs.length < 2 || !strs[0].equalsIgnoreCase("ftp")) {
			return values;
		}
		values = new String[5];
		String address = strs[1].replaceAll("//", "/");
		values[0] = address.substring(0, address.indexOf(':'));
		address = address.substring(address.indexOf(':'), address.length());
		values[1] = address.substring(1, address.indexOf('@'));
		address = address.substring(address.indexOf('@'), address.length());
		values[2] = address.substring(1, address.indexOf('/'));
		// if (values[2].contains(":")) {
		// String[] temp=values[2].split(":");
		// values[2]=temp[0];
		// values[3]=temp[1];
		// }else{
		// values[3]="21";
		// }
		if (values[2].split("\\.").length == 5) {
			String[] temp = values[2].split("\\.");
			values[2] = values[2].substring(0, values[2].lastIndexOf("."));
			values[3] = temp[4];
		} else {
			values[3] = "21";
		}
		address = address.substring(address.indexOf('/'), address.length());
		values[4] = address.substring(1, address.length());

		return values;
	}

	// 十六进制下数字到字符的映射数组
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	/**
	 * 转换字节数组为十六进制字符串
	 * 
	 * @param b
	 *            字节数组
	 * @return 十六进制字符串
	 */
	private static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	/** 将一个字节转化成十六进制形式的字符串 */
	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	/** 对字符串进行MD5加密 */
	public static String encodeByMD5(String originString) {
		if (originString != null) {
			try {
				// 创建具有指定算法名称的信息摘要
				MessageDigest md = MessageDigest.getInstance("MD5");
				// 使用指定的字节数组对摘要进行最后更新，然后完成摘要计算
				byte[] results = md.digest(originString.getBytes());
				// 将得到的字节数组变成字符串返回
				String resultString = byteArrayToHexString(results);
				return resultString.toUpperCase();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	public static String getDcmsidstring(String dcspCode, String dcmsid) {
		return "[" + dcspCode + "]" + dcmsid;
	}

	public static String setDcmsidString(String dcmsid) {
		return dcmsid.substring(dcmsid.indexOf("]") + 1);
	}

	public static void setFilePath(String filePath) {// 判断文件夹是否存在不存在则建立文件夹
		File file = new File(filePath);
		if (!file.exists())
			file.mkdirs();
	}

	/** 获取utc时间 */
	public static String getUTCTime(Date date) {
		String UTC = "";
		SimpleDateFormat foo = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		// 1、取得本地时间：
		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.setTime(date);
		// 2、取得时间偏移量：
		int zoneOffset = cal.get(java.util.Calendar.ZONE_OFFSET);

		// 3、取得夏令时差：
		int dstOffset = cal.get(java.util.Calendar.DST_OFFSET);

		// 4、从本地时间里扣除这些差量，即可以取得UTC时间：
		cal.add(java.util.Calendar.MILLISECOND, -(zoneOffset + dstOffset));

		// 之后调用cal.get(int x)或cal.getTimeInMillis()方法所取得的时间即是UTC标准时间。
		// System.out.println("UTC:"+new Date(cal.getTimeInMillis()));
		// System.out.println("UTC:"+foo.format(new
		// Date(cal.getTimeInMillis())));
		UTC = foo.format(new Date(cal.getTimeInMillis()));
		foo = new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy 'UTC'", Locale.US);
		System.out.println(foo.format(new Date(cal.getTimeInMillis())));
		return UTC;
	}

	/** 获取utc时间 */
	public static String getUTCFormatStr(Date date) {
		String UTC = "";
		SimpleDateFormat foo = new SimpleDateFormat(
				"EEE MMM dd HH:mm:ss yyyy 'UTC'", Locale.US);
		// 1、取得本地时间：
		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.setTime(date);
		// 2、取得时间偏移量：
		int zoneOffset = cal.get(java.util.Calendar.ZONE_OFFSET);

		// 3、取得夏令时差：
		int dstOffset = cal.get(java.util.Calendar.DST_OFFSET);

		// 4、从本地时间里扣除这些差量，即可以取得UTC时间：
		cal.add(java.util.Calendar.MILLISECOND, -(zoneOffset + dstOffset));

		// 之后调用cal.get(int x)或cal.getTimeInMillis()方法所取得的时间即是UTC标准时间。
		// System.out.println("UTC:"+new Date(cal.getTimeInMillis()));
		// System.out.println("UTC:"+foo.format(new
		// Date(cal.getTimeInMillis())));
		UTC = foo.format(new Date(cal.getTimeInMillis()));
		System.out.println(foo.format(new Date(cal.getTimeInMillis())));
		return UTC;
	}

	public static String getPPlivePlayDragUrl() {
		return PropertyUtil.getProperty().getProperty("Config_PlayDragUrl");
	}

	public static String getPPlivePlayDragUrl2() {
		return PropertyUtil.getProperty().getProperty("Config_PlayDragUrl2");
	}

	public static String getThreeKey() {
		return PropertyUtil.getProperty().getProperty(
				"Config_Transcodecdn_3DES_KEY");
	}

	public static String getThreeIV() {
		return PropertyUtil.getProperty().getProperty(
				"Config_Transcodecdn_3DES_IV");
	}

	public void s() {
		System.out.println(this.getClass().getSimpleName());
	}

	public static String getRandomNum(int num) {
		StringBuffer tmpret = new StringBuffer();
		int tmpint = 0;
		for (int i = 0; i < num; i++) {
			tmpint = new java.util.Random().nextInt(9);
			tmpret.append(tmpint);
		}
		return tmpret.toString();

	}

	// 获取星期几
	public static String getWeekOfDate(Date dt) {
		String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		return weekDays[w];
	}

	public static String getGenerateCode() {
		UUID uuid = UUID.randomUUID();
		String tmpid = uuid.toString();
		tmpid = tmpid.replace("-", "");
		return tmpid;
	}

	/** 获取总页数(根据总数和每页数量) */
	public static int GetPageNum(int totalCount, int pageSize) {
		int totalPages = 0;
		if ((totalCount % pageSize) == 0) {
			totalPages = totalCount / pageSize;
		} else {
			totalPages = totalCount / pageSize + 1;
		}
		return totalPages;
	}

	/** 获取文件后缀名 */
	public static String getSubfixFromFileName(String fileName) {
		String tmpret = "";
		fileName = getString(fileName);
		try {
			int lastIndexOfDot = fileName.lastIndexOf('.');
			if (lastIndexOfDot > 0)
				tmpret = fileName.substring(lastIndexOfDot + 1);

		} catch (Exception ex) {
			log.error("getSubfixFromFileName:" + ex.getMessage());
		}
		return tmpret;
	}

	/** 获取文件前缀名 */
	public static String getPrefixFromFileName(String fileName) {
		String tmpret = "";
		fileName = getString(fileName);
		try {
			int lastIndexOfDot = fileName.lastIndexOf('.');
			if (lastIndexOfDot != -1)
				tmpret = fileName.substring(0, lastIndexOfDot);
			else
				tmpret = fileName;

		} catch (Exception ex) {
			log.error("getSubfixFromFileName:" + ex.getMessage());
		}
		return tmpret;
	}

	/** 获取文件名根据路径 */
	public static String getFileNameFromFileUrl(String fileurl) {
		String tmpret = "";
		fileurl = getString(fileurl);
		try {
			fileurl = fileurl.replace("\\", "/");

			int lastIndexOfDot = fileurl.lastIndexOf('/');
			if (lastIndexOfDot != -1)
				tmpret = fileurl.substring(lastIndexOfDot + 1);
			else
				tmpret = fileurl;
		} catch (Exception ex) {
			log.error("getFileNameFromFileUrl:" + ex.getMessage());
		}
		return tmpret;
	}

	public static Date safeGetDate(String dateformat) {
		dateformat = dateformat.replaceAll("[^0-9]", "");
		if (dateformat.replaceAll("[0-9]*", "").length() == 0) {
			if (dateformat.length() == 8) {
				return getDate(dateformat, "yyyyMMdd");
			}
			if (dateformat.length() == 14) {
				return getDate(dateformat, "yyyyMMddHHmmss");
			}
			if (dateformat.length() == 6) {
				return getDate(dateformat, "HHmmss");
			}
		}

		return null;
	}

	// 分析枚举值 //{0:\'失效\',1:\'生效\'}
	public static String getEnumStrValue(Object key, String enumstr) {
		String re = "";
		if (key == null)
			return re;
		int len = enumstr.length();
		if (enumstr.indexOf("{") > -1)
			enumstr = enumstr.substring(1, len - 2);
		enumstr = enumstr.replaceAll("'", "");
		String[] strs = enumstr.split("[,;]");
		len = strs.length;
		if (len > 0) {
			String keyStr = key.toString();
			for (int i = 0; i < len; i++) {
				String[] strss = strs[i].split(":");
				if (strss[0].equals(keyStr)) {
					re = strss[1];
					break;
				}
			}
		}
		return re;
	}

	// 分析枚举值 //{0:\'失效\',1:\'生效\'}
	public static String getStrValueEnum(Object key, String enumstr) {
		String re = "";
		if (key == null)
			return re;
		int len = enumstr.length();
		if (enumstr.indexOf("{") > -1)
			enumstr = enumstr.substring(1, len - 2);
		enumstr = enumstr.replaceAll("'", "");
		String[] strs = enumstr.split("[,;]");
		len = strs.length;
		if (len > 0) {
			String keyStr = key.toString();
			for (int i = 0; i < len; i++) {
				String[] strss = strs[i].split(":");
				if (strss[1].equals(keyStr)) {
					re = strss[0];
					break;
				}
			}
		}
		return re;
	}

	public static String findOneString(String context, String pattern) {
		String result = "";
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(context);
		if (m.find() && m.toMatchResult().group() != null) {
			result = m.toMatchResult().group().trim();
		} else {
			result = "";
		}
		return result;
	}

	private static AtomicInteger idIntger = new AtomicInteger(1);

	public static long getNextId() {
		if (idIntger.get() >= 900) {
			idIntger.set(1);
		}
		String id = "000" + idIntger.getAndIncrement();
		id = id.substring(id.length() - 3, id.length());
		try {
			Thread.sleep(100);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return getLong((System.currentTimeMillis() / 1000) + ""
				+ getLocalIPFlag() + "" + id);
	}

	static String LocalIPFlag = "";

	private static String getLocalIPFlag() {
		if (LocalIPFlag.length() > 0) {
			return LocalIPFlag;
		}
		try {
			InetAddress[] inets = InetAddress.getAllByName(InetAddress
					.getLocalHost().getHostName());
			for (int i = 0; i < inets.length; i++) {
				int flag = inets[i].getAddress()[3] << 0 & 0xff;
				if (flag > 1) {
					LocalIPFlag = "000" + flag;
					LocalIPFlag = LocalIPFlag.substring(
							LocalIPFlag.length() - 2, LocalIPFlag.length());
				}
			}
		} catch (Exception e) {
			log.error(e, e);
		}
		return LocalIPFlag;
	}

	/**
	 * 从FTP下载一个文件保存到另一个FTP
	 * 
	 * @param orgFTPFileUrl
	 *            源FTP文件路径
	 * @param destFTPFileUrl
	 *            目标本地文件路径
	 * @return
	 */
	/*public static DaoReturnVO downFtpFileAndMD5(String orgFTPFileUrl,
			String destFTPFileUrl) {
		DaoReturnVO returnVO = new DaoReturnVO();
		returnVO.setResult(0);
		returnVO.setResultDesc("OK");
		try {
			String[] values = getSingleMatchValue(orgFTPFileUrl);
			FtpUtil ftpUtil = new FtpUtil(values[2], getInt(values[3]),
					values[0], values[1]);
			ftpUtil.login();
			byte[] bytes = ftpUtil.downFile("/" + values[4]);
			ftpUtil.logout();
			if (bytes != null && bytes.length > 0) {
				String[] destvalues = getSingleMatchValue(destFTPFileUrl);
				FtpUtil destftpUtil = new FtpUtil(destvalues[2],
						getInt(destvalues[3]), destvalues[0], destvalues[1]);
				destftpUtil.login();
				destftpUtil.upFile(bytes, destvalues[4]);
				destftpUtil.logout();
			} else {
				returnVO.setResult(-1);
				returnVO.setResultDesc("fail");
			}
		} catch (Exception e) {
			returnVO.setResult(-1);
			returnVO.setResultDesc(e.getMessage());
			log.error(e, e);
		}

		if (returnVO.getResult() == -1) {
			try {
				String[] values = getSingleMatchValue(orgFTPFileUrl);
				FtpUtil ftpUtil = new FtpUtil(values[2], getInt(values[3]),
						values[0], values[1]);
				ftpUtil.login();
				byte[] bytes = ftpUtil.downFile("/" + values[4]);
				ftpUtil.logout();
				if (bytes != null && bytes.length > 0) {
					String[] destvalues = getSingleMatchValue(destFTPFileUrl);
					FtpUtil destftpUtil = new FtpUtil(destvalues[2],
							getInt(destvalues[3]), destvalues[0], destvalues[1]);
					destftpUtil.login();
					destftpUtil.upFile(bytes, destvalues[4]);
					destftpUtil.logout();
				} else {
					returnVO.setResult(-1);
					returnVO.setResultDesc("fail");
				}
			} catch (Exception e) {
				returnVO.setResult(-1);
				returnVO.setResultDesc(e.getMessage());
				log.error(e, e);
			}
		}

		if (returnVO.getResult() == -1) {
			try {
				String[] values = getSingleMatchValue(orgFTPFileUrl);
				FtpUtil ftpUtil = new FtpUtil(values[2], getInt(values[3]),
						values[0], values[1]);
				ftpUtil.login();
				byte[] bytes = ftpUtil.downFile("/" + values[4]);
				ftpUtil.logout();
				if (bytes != null && bytes.length > 0) {
					String[] destvalues = getSingleMatchValue(destFTPFileUrl);
					FtpUtil destftpUtil = new FtpUtil(destvalues[2],
							getInt(destvalues[3]), destvalues[0], destvalues[1]);
					destftpUtil.login();
					destftpUtil.upFile(bytes, destvalues[4]);
					destftpUtil.logout();
				} else {
					returnVO.setResult(-1);
					returnVO.setResultDesc("fail");
				}
			} catch (Exception e) {
				returnVO.setResult(-1);
				returnVO.setResultDesc(e.getMessage());
				log.error(e, e);
			}
		}
		return returnVO;
	}
*/
	/*
	 * public static String getPicFtpUrl(String orgFTPFileUrl){ String
	 * destFilePath
	 * =SysConfigHelper.getConfigPath("PIC_FTPURL")+"managePic/"+SafeUtils
	 * .getCurrentTimeStr
	 * ("yyyy/MM/dd/")+SafeUtils.getNextId()+orgFTPFileUrl.substring
	 * (orgFTPFileUrl.lastIndexOf('.'), orgFTPFileUrl.length()); return
	 * destFilePath; }
	 */
	public static void main(String[] args) {
		/*
		 * String s="5aSn5a626YO95Zyo5pCc"; s=java.net.URLDecoder.decode(s);
		 */
		//System.out.println(getContentId("", "CmsSpecial"));

	}
	/**
	 * @Description:判断是否是数字
	 * @param str
	 * @return
	 * boolean
	 * @exception:
	 * @author: Mr.Ruan
	 * @time:2015年10月15日 下午3:30:44
	 */
	public static boolean isNumber(String str)
    {
        java.util.regex.Pattern pattern=java.util.regex.Pattern.compile("[0-9]*");
        java.util.regex.Matcher match=pattern.matcher(str);
        if(match.matches()==false)
        {
             return false;
        }
        else
        {
             return true;
        }
    }
	/*public static Base64 base64 = new Base64();

	public static String decodeBASE64(String str) {
		String result = "";
		try {
			str = str.replaceAll(" ", "+");
			result = new String(base64.decode(str.getBytes("utf-8")), "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}*/

	// 将newobj有值的属性更新到oldobj
	public static int megerObject(Object newobj, Object oldobj) {
		if (newobj == null || oldobj == null) {
			return -1;
		}
		if (!newobj.getClass().equals(oldobj.getClass())) {
			return megerTwoObject(newobj, oldobj);
		}
		Class c = newobj.getClass();
		Field[] fields = c.getDeclaredFields();
		try {
			for (int i = 0; i < fields.length; i++) {
				fields[i].setAccessible(true);
				if ((!Modifier.isStatic(fields[i].getModifiers()))
						&& Modifier.isPrivate(fields[i].getModifiers())
						&& fields[i].get(newobj) != null
						&& !getString(fields[i].get(newobj)).equals("")) {

					fields[i].set(oldobj, (fields[i].get(newobj)));
				}
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	// 将newobj有值的属性更新到oldobj
	public static int megerTwoObject(Object newobj, Object oldobj) {
		if (newobj == null || oldobj == null) {
			return -1;
		}
		Class nc = newobj.getClass();
		Field[] nfields = nc.getDeclaredFields();

		Class oc = oldobj.getClass();
		Field field = null;
		try {
			for (int i = 0; i < nfields.length; i++) {
				nfields[i].setAccessible(true);

				if ((!Modifier.isStatic(nfields[i].getModifiers()))
						&& Modifier.isPrivate(nfields[i].getModifiers())
						&& nfields[i].get(newobj) != null) {

					field = oc.getDeclaredField(nfields[i].getName());
					field.setAccessible(true);
					field.set(oldobj, (nfields[i].get(newobj)));
				}
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public static String getObject(Object[] objs) {
		if (objs == null) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		try {
			for (int i = 0; i < objs.length; i++) {
				sb.append(objs[i] + " ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		sb.append("\n");
		return sb.toString();
	}

	/*public static String getSpell(String chinese) {
		if (chinese == null) {
			return null;
		}
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);// 小写
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不标声调
		format.setVCharType(HanyuPinyinVCharType.WITH_V);// u:的声母替换为v
		try {
			StringBuilder sb = new StringBuilder();
			char[] cs = chinese.toCharArray();
			for (int i = 0; i < cs.length; i++) {
				String[] array = PinyinHelper.toHanyuPinyinStringArray(cs[i],
						format);
				if (array == null || array.length == 0) {
					sb.append(chinese.charAt(i));
					continue;
				}

				sb.append(String.valueOf(array[0].charAt(0)));
			}

			cs = sb.toString().toUpperCase().toCharArray();
			char[] result = new char[cs.length];
			int j = 0;
			for (int i = 0; i < cs.length; i++) {
				if (('A' <= cs[i]) && (cs[i] <= 'Z') || ('0' <= cs[i])
						&& (cs[i] <= '9')) {
					result[j] = cs[i];
					j++;
				}
			}
			return new String(result).trim();
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			e.printStackTrace();
		}
		return null;
	}*/
	/* @param beginDateStr
	* @param endDateStr
	* @return
	* long
	* @author Administrator
	*/
	public static long getDaySub(String beginDateStr,Date date)
	{
		long day=0;
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
		java.util.Date beginDate;
		java.util.Date endDate;
	try
	{
		beginDate = format.parse(beginDateStr);
		endDate= date;
		day=(int) ((endDate.getTime()-beginDate.getTime())/(24*60*60*1000));
	} catch (Exception e)
	{
	// TODO 自动生成 catch 块
		e.printStackTrace();
	}
		return day;
	}

	public static long getOtherDaySub(Date date,String endDateStr) {
		long day=0;
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
		java.util.Date beginDate;
		java.util.Date endDate;
		try
		{
			beginDate = date;
			endDate= format.parse(endDateStr);
			day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);
		} catch (ParseException e){
			e.printStackTrace();
		}
		return day;
	}  
}
