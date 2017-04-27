package com.questionnaire.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.StringUtils;

/**
 * @ClassName: DateUtil
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Administer
 * @date 2014�?6�?17�? 下午4:25:37
 * 
 */
public class DateUtil {
	/**
	 * 日期格式:yyyyMM
	 */
	public static final String DATEFORMAT_SIX = "yyyyMM";
	/**
	 * 日期格式:yyyyMMdd
	 */
	public static final String DATEFORMAT_EIGHT = "yyyyMMdd";
	/**
	 * 日期格式:yyyy-MM-dd
	 */
	public static final String DATEFORMAT_TEN = "yyyy-MM-dd";
	/**
	 * 日期格式:yyyy-MM-dd HH:mm:ss
	 */
	public static final String DATEFORMAT_NINTEEN = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 字符串转时间(yyyy-MM-dd HH:mm:ss)
	 * 
	 * @return
	 */
	public static Date format(String dateTime) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMAT_NINTEEN);
		Date date = new Date();
		try {
			date = sdf.parse(dateTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 时间转字符串(yyyy-MM-dd)
	 * 
	 * @return
	 */
	public static String format(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * @param dateTime
	 *            字符串格式的时间
	 * @param pattern
	 *            时间样式
	 * @return
	 * @throws ParseException
	 */
	public static Date format(String dateTime, String pattern) throws ParseException {
		if (dateTime == null || "".equals(dateTime))
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.parse(dateTime);
	}

	public static String dsFormat(String dateTime, String format) throws ParseException {
		if (dateTime == null || "".equals(dateTime))
			return null;
		if (format == null) {
			format = DATEFORMAT_NINTEEN;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return String.valueOf(sdf.format(new Date(Long.valueOf(dateTime))));
	}

	/**
	 * 取得当前系统时间(yyyy-MM-dd HH:mm:ss)
	 * 
	 * @return
	 */
	public static String getCurrentDate() {
		SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMAT_NINTEEN);
		return sdf.format(new Date());
	}

	public static String dateToXmlString(Date date) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMAT_EIGHT);
		String dateString = sdf.format(date);
		return dateString;
	}

	/**
	 * 获取本月的第�?�?
	 * 
	 * @return
	 */
	public static Date getFirstDay(Long dateTime) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(new Date(dateTime));
		int day = cal.getMinimum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, day);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}

	/**
	 * 
	 * @param timestamp
	 *            毫秒�?
	 * @param format
	 *            yyyy-mm-DD hh:MM:ss
	 * @return
	 */
	public static String getStartTime(String timestamp) {
		if (StringUtils.isBlank(timestamp)) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(Long.valueOf(timestamp));
		StringBuffer sb = new StringBuffer();
		sb.append(cal.get(Calendar.YEAR) + "-");
		int month = cal.get(Calendar.MONTH) + 1;
		if (month < 10) {
			sb.append("0");
		}
		sb.append(month + "-");
		int dayOfM = cal.get(Calendar.DAY_OF_MONTH);
		if (dayOfM < 10) {
			sb.append("0");
		}
		sb.append(dayOfM + " ");
		int hourOfD = cal.get(Calendar.HOUR_OF_DAY);
		if (hourOfD < 10) {
			sb.append("0");
		}
		sb.append(hourOfD + ":");
		int min = cal.get(Calendar.MINUTE);
		if (min < 10) {
			sb.append("0");
		}
		sb.append(min + ":");
		int sec = cal.get(Calendar.SECOND);
		if (sec < 10) {
			sb.append("0");
		}
		sb.append(sec);
		String timeStr = sb.toString();
		return timeStr;
	}

	/**
	 * 获得日期时间对应�?,long�?
	 * 
	 * @param dateTime
	 * @param pattern
	 * @return long�?
	 * @throws ParseException
	 */
	public static long getTime(String dateTime, String pattern) throws ParseException {
		Date dateValue = format(dateTime, pattern);
		return dateValue.getTime();
	}

	/*
	 * 获取�?天开始时�?
	 */
	public static Long getStartTime(Long timestamp) {
		Date date = new Date(timestamp);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime().getTime();
	}

	/*
	 * 获取�?个月前的�?天开始时�?
	 */
	public static Long getMonthAgoTime(Long timestamp) {
		return getMonthLaterTime(timestamp, -1);
	}

	/*
	 * 获取上个�?1日的�?始时�?
	 */
	public static Long getPriorMonthStart(Long timestamp) {
		Date date = new Date(timestamp);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int month = calendar.get(Calendar.MONTH);
		int year = calendar.get(Calendar.YEAR);
		if (month == 0) {
			month = 11;
			year--;
		} else {
			month = month - 1;
		}
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime().getTime();
	}

	/*
	 * 获取本月1日的�?始时�?
	 */
	public static Long getMonthStart(Long timestamp) {
		return DateUtil.getFirstDay(timestamp).getTime();
	}

	/**
	 * @Title: getMonthLaterTime
	 * @Description: 获取n个月后的�?天开始时�?,n为负数时表示前面的时�?
	 * @param timestamp
	 * @param n
	 * @return
	 */
	public static Long getMonthLaterTime(Long timestamp, int n) {
		Date date = new Date(timestamp);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		month += n;
		if (month > 11) {
			year += month / 11;
			month = month % 11;
		} else {
			while (month < 0) {
				month += 12;
				year -= 1;
			}
		}
		if (day > 28) {
			if (month == 1) {
				if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
					day = 29;
				} else {
					day = 28;
				}
			} else if ((month == 3 || month == 5 || month == 8 || month == 10) && day == 31) {
				day = 30;
			}
		}
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH, day);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime().getTime();
	}

	/**
	 * @Title: getYearStart
	 * @Description: 获取n年后�?1�?1 日开始时�?,或n<0,表示n年前
	 * @param time
	 * @return
	 */
	public static Long getYearStart(Long time, int n) {

		Date date = new Date(time);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);

		calendar.set(Calendar.YEAR, year + n);
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return time;

	}

	/**
	 * 获取今天星期�? zhanfeilong
	 */
	public static int getWeekOfDate() {
		Date dt = new Date();
		int[] weekDays = { 7, 1, 2, 3, 4, 5, 6 };
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		return weekDays[w];
	}

	/**
	 * 日期转换成字符串
	 * 
	 * @param date
	 * @return str
	 */
	public static String DateToStr(Date date, String formatString) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatString);
		return sdf.format(date);
	}

	/**
	 * 聚date前day天的信息
	 * 
	 * @param date
	 * @param day
	 * @return
	 */
	public static String getDayFormatBeforeTime(Date date, Integer day) {
		Calendar ca = Calendar.getInstance();// 得到�?个Calendar的实�?
		ca.setTime(date); // 设置时间为当前时�?
		ca.add(Calendar.DATE, day);// 日期�?1
		Date resultDate = ca.getTime(); // 结果
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(resultDate);
	}

	/**
	 * 聚date前day天的信息
	 * 
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date getDayBeforeTime(Date date, Integer day) {
		Calendar ca = Calendar.getInstance();// 得到�?个Calendar的实�?
		ca.setTime(date); // 设置时间为当前时�?
		ca.add(Calendar.DATE, day);// 日期�?1
		return ca.getTime();
	}

	public static String formatDateToString(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String startTime = sdf.format(date);
		return startTime;
	}
	
	/**  
     * 计算两个日期之间相差的天数  
     * @param smdate 较小的时间 
     * @param bdate  较大的时间 
     * @return 相差天数 
     * @throws ParseException  
     */  
    public static int daysBetween(Date smdate,Date bdate) throws ParseException{    
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        smdate=sdf.parse(sdf.format(smdate));  
        bdate=sdf.parse(sdf.format(bdate));  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
       return Integer.parseInt(String.valueOf(between_days));           
    } 

	public static void main(String[] args) {

		Long time = format("2015-11-30 22:22:22").getTime();

		System.out.println(time);
	}
	
}
