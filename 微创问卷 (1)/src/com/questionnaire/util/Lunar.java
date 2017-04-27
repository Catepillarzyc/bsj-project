package com.questionnaire.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

public class Lunar {
	private Calendar solar;
	private int lunarYear;
	private int solarYear;
	private int solarMonth;
	private int solarDay;

	@SuppressWarnings("deprecation")
	public static String jieqicode(Date date) {
		Calendar t = Calendar.getInstance();
		t.setTime(date);
		Date[] jieqi = jieqilist(1940);
		String jq = "";
		for (int i = 0; i < solarTerm.length; i++) {
			if (jieqi[i].getMonth() == date.getMonth() && jieqi[i].getDate() >= date.getDate()) {
				i = i - 1;
				jq = solarTerm[i];
				break;
			}
			if (jieqi[i].getMonth() > date.getMonth()) {
				i = i - 1;
				jq = solarTerm[i];
				break;
			}
		}
		return jq;
	}

	/**
	 * 获得某天前个节气日期差
	 * 
	 * @return 日期数
	 */
	public static long getbeforesolarTerm(int year, Date date) {
		List<Date> jieqi = Alljieqi(year);
		int[] jieqibeforeafter = getnearsolarTerm(year, date);
		return DateDays(date, jieqi.get(jieqibeforeafter[0]));
	}

	public static List<Date> Alljieqi(int year) {
		List<Date> jieqi = new ArrayList<Date>();
		Date[] temp;
		temp = jieqilist(year - 1);
		jieqi.addAll(Arrays.asList(temp));
		temp = jieqilist(year);
		jieqi.addAll(Arrays.asList(temp));
		temp = jieqilist(year + 1);
		jieqi.addAll(Arrays.asList(temp));
		return jieqi;
	}

	/**
	 * 获得某天前后两个节气序号
	 * 
	 * @return
	 */
	public static int[] getnearsolarTerm(int year, Date date) {
		List<Date> jieqi = Alljieqi(year);
		int[] returnValue = new int[2];
		for (int i = 0; i < jieqi.size(); i++) {
			if (date.getTime() > jieqi.get(i).getTime()) {
				continue;
			}
			if (i % 2 == 0) {// 只管气
				returnValue[0] = i - 2;
				returnValue[1] = i;
			} else {
				returnValue[0] = i - 1;
				returnValue[1] = i + 1;
			}
			break;
		}
		return returnValue;
	}

	/**
	 * 获得某天后个节气日期差
	 * 
	 * @return 日期数
	 */
	public static long getaftersolarTerm(int year, Date date) {
		List<Date> jieqi = Alljieqi(year);
		int[] jieqibeforeafter = getnearsolarTerm(year, date);
		return DateDays(date, jieqi.get(jieqibeforeafter[1]));
	}

	/**
	 * 获得某年中所有节气Date
	 * 
	 * @return
	 */
	public static Date[] jieqilist(int year) {
		Date[] returnvalue = new Date[solarTerm.length];
		for (int i = 0; i < solarTerm.length; i++) {
			Date t = getSolarTermCalendar(year, i);
			returnvalue[i] = t;
		}
		return returnvalue;
	}

	/**
	 * 通过 Date 对象构建农历信息
	 * 
	 * @param date
	 *            指定日期对象
	 */
	public Lunar(Date date) {
		if (date == null) {
			date = new Date();
		}
		this.init(date.getTime());
	}

	/**
	 * 通过 TimeInMillis 构建农历信息
	 * 
	 * @param TimeInMillis
	 */
	public Lunar(long TimeInMillis) {
		this.init(TimeInMillis);
	}

	private void init(long TimeInMillis) {
		this.solar = Calendar.getInstance();
		this.solar.setTimeInMillis(TimeInMillis);
		Calendar baseDate = new GregorianCalendar(1900, 0, 31);
		long offset = (TimeInMillis - baseDate.getTimeInMillis()) / 86400000;
		// 按农历年递减每年的农历天数，确定农历年份
		this.lunarYear = 1900;
		int daysInLunarYear = Lunar.getLunarYearDays(this.lunarYear);
		while (this.lunarYear < 2100 && offset >= daysInLunarYear) {
			offset -= daysInLunarYear;
			daysInLunarYear = Lunar.getLunarYearDays(++this.lunarYear);
		}
		// 农历年数字
		// 按农历月递减每月的农历天数，确定农历月份
		int lunarMonth = 1;
		// 所在农历年闰哪个月,若没有返回0
		int leapMonth = Lunar.getLunarLeapMonth(this.lunarYear);
		// 闰月是否递减
		boolean leapDec = false;
		boolean isLeap = false;
		int daysInLunarMonth = 0;
		while (lunarMonth < 13 && offset > 0) {
			if (isLeap && leapDec) { // 如果是闰年,并且是闰月
				// 所在农历年闰月的天数
				daysInLunarMonth = Lunar.getLunarLeapDays(this.lunarYear);
				leapDec = false;
			} else {
				// 所在农历年指定月的天数
				daysInLunarMonth = Lunar.getLunarMonthDays(this.lunarYear, lunarMonth);
			}
			if (offset < daysInLunarMonth) {
				break;
			}
			offset -= daysInLunarMonth;
			if (leapMonth == lunarMonth && isLeap == false) {
				// 下个月是闰月
				leapDec = true;
				isLeap = true;
			} else {
				// 月份递增
				lunarMonth++;
			}
		}
		// 取得干支历
		this.getCyclicalData();
	}

	/**
	 * 取干支历 不是历年，历月干支，而是中国的从立春节气开始的节月，是中国的太阳十二宫，阳历的。
	 * 
	 * @param cncaData
	 *            日历对象(Tcnca)
	 */
	private void getCyclicalData() {
		this.solarYear = this.solar.get(Calendar.YEAR);
		this.solarMonth = this.solar.get(Calendar.MONTH);
		this.solarDay = this.solar.get(Calendar.DAY_OF_MONTH);
		// 干支年 1900年立春後为庚子年(60进制36)
		int term2 = Lunar.getSolarTermDay(solarYear, 2); // 立春日期
		// 依节气调整二月分的年柱, 以立春为界
		if (solarMonth < 1 || (solarMonth == 1 && solarDay < term2)) {
		} else {
		}
		// 干支月 1900年1月小寒以前为 丙子月(60进制12)
		int firstNode = Lunar.getSolarTermDay(solarYear, solarMonth * 2); // 传回当月「节」为几日开始
		// 依节气月柱, 以「节」为界
		if (solarDay < firstNode) {
		} else {
		}
	}

	/**
	 * 返回农历年的总天数
	 * 
	 * @param lunarYear
	 *            指定农历年份(数字)
	 * @return 该农历年的总天数(数字)
	 */
	private static int getLunarYearDays(int lunarYear) {
		// 按小月计算,农历年最少有12 * 29 = 348天
		int daysInLunarYear = 348;
		// 数据表中,每个农历年用16bit来表示,
		// 前12bit分别表示12个月份的大小月,最后4bit表示闰月
		// 每个大月累加一天
		for (int i = 0x8000; i > 0x8; i >>= 1) {
			daysInLunarYear += ((Lunar.lunarInfo[lunarYear - 1900] & i) != 0) ? 1 : 0;
		}
		// 加上闰月天数
		daysInLunarYear += Lunar.getLunarLeapDays(lunarYear);
		return daysInLunarYear;
	}

	/**
	 * 返回农历年正常月份的总天数
	 * 
	 * @param lunarYear
	 *            指定农历年份(数字)
	 * @param lunarMonth
	 *            指定农历月份(数字)
	 * @return 该农历年闰月的月份(数字,没闰返回0)
	 */
	private static int getLunarMonthDays(int lunarYear, int lunarMonth) {
		// 数据表中,每个农历年用16bit来表示,
		// 前12bit分别表示12个月份的大小月,最后4bit表示闰月
		int daysInLunarMonth = ((Lunar.lunarInfo[lunarYear - 1900] & (0x10000 >> lunarMonth)) != 0) ? 30 : 29;
		return daysInLunarMonth;
	}

	/**
	 * 返回农历年闰月的天数
	 * 
	 * @param lunarYear
	 *            指定农历年份(数字)
	 * @return 该农历年闰月的天数(数字)
	 */
	private static int getLunarLeapDays(int lunarYear) {
		// 下一年最后4bit为1111,返回30(大月)
		// 下一年最后4bit不为1111,返回29(小月)
		// 若该年没有闰月,返回0
		return Lunar.getLunarLeapMonth(lunarYear) > 0 ? ((Lunar.lunarInfo[lunarYear - 1899] & 0xf) == 0xf ? 30 : 29)
				: 0;
	}

	/**
	 * 返回农历年闰月月份
	 * 
	 * @param lunarYear
	 *            指定农历年份(数字)
	 * @return 该农历年闰月的月份(数字,没闰返回0)
	 */
	private static int getLunarLeapMonth(int lunarYear) {
		// 数据表中,每个农历年用16bit来表示,
		// 前12bit分别表示12个月份的大小月,最后4bit表示闰月
		// 若4bit全为1或全为0,表示没闰, 否则4bit的值为闰月月份
		int leapMonth = Lunar.lunarInfo[lunarYear - 1900] & 0xf;
		leapMonth = (leapMonth == 0xf ? 0 : leapMonth);
		return leapMonth;
	}

	private final static int[] lunarInfo = { 0x4bd8, 0x4ae0, 0xa570, 0x54d5, 0xd260, 0xd950, 0x5554, 0x56af, 0x9ad0,
			0x55d2, 0x4ae0, 0xa5b6, 0xa4d0, 0xd250, 0xd295, 0xb54f, 0xd6a0, 0xada2, 0x95b0, 0x4977, 0x497f, 0xa4b0,
			0xb4b5, 0x6a50, 0x6d40, 0xab54, 0x2b6f, 0x9570, 0x52f2, 0x4970, 0x6566, 0xd4a0, 0xea50, 0x6a95, 0x5adf,
			0x2b60, 0x86e3, 0x92ef, 0xc8d7, 0xc95f, 0xd4a0, 0xd8a6, 0xb55f, 0x56a0, 0xa5b4, 0x25df, 0x92d0, 0xd2b2,
			0xa950, 0xb557, 0x6ca0, 0xb550, 0x5355, 0x4daf, 0xa5b0, 0x4573, 0x52bf, 0xa9a8, 0xe950, 0x6aa0, 0xaea6,
			0xab50, 0x4b60, 0xaae4, 0xa570, 0x5260, 0xf263, 0xd950, 0x5b57, 0x56a0, 0x96d0, 0x4dd5, 0x4ad0, 0xa4d0,
			0xd4d4, 0xd250, 0xd558, 0xb540, 0xb6a0, 0x95a6, 0x95bf, 0x49b0, 0xa974, 0xa4b0, 0xb27a, 0x6a50, 0x6d40,
			0xaf46, 0xab60, 0x9570, 0x4af5, 0x4970, 0x64b0, 0x74a3, 0xea50, 0x6b58, 0x5ac0, 0xab60, 0x96d5, 0x92e0,
			0xc960, 0xd954, 0xd4a0, 0xda50, 0x7552, 0x56a0, 0xabb7, 0x25d0, 0x92d0, 0xcab5, 0xa950, 0xb4a0, 0xbaa4,
			0xad50, 0x55d9, 0x4ba0, 0xa5b0, 0x5176, 0x52bf, 0xa930, 0x7954, 0x6aa0, 0xad50, 0x5b52, 0x4b60, 0xa6e6,
			0xa4e0, 0xd260, 0xea65, 0xd530, 0x5aa0, 0x76a3, 0x96d0, 0x4afb, 0x4ad0, 0xa4d0, 0xd0b6, 0xd25f, 0xd520,
			0xdd45, 0xb5a0, 0x56d0, 0x55b2, 0x49b0, 0xa577, 0xa4b0, 0xaa50, 0xb255, 0x6d2f, 0xada0, 0x4b63, 0x937f,
			0x49f8, 0x4970, 0x64b0, 0x68a6, 0xea5f, 0x6b20, 0xa6c4, 0xaaef, 0x92e0, 0xd2e3, 0xc960, 0xd557, 0xd4a0,
			0xda50, 0x5d55, 0x56a0, 0xa6d0, 0x55d4, 0x52d0, 0xa9b8, 0xa950, 0xb4a0, 0xb6a6, 0xad50, 0x55a0, 0xaba4,
			0xa5b0, 0x52b0, 0xb273, 0x6930, 0x7337, 0x6aa0, 0xad50, 0x4b55, 0x4b6f, 0xa570, 0x54e4, 0xd260, 0xe968,
			0xd520, 0xdaa0, 0x6aa6, 0x56df, 0x4ae0, 0xa9d4, 0xa4d0, 0xd150, 0xf252, 0xd520 };

	private final static int[] solarTermInfo = { 0, 21208, 42467, 63836, 85337, 107014, 128867, 150921, 173149, 195551,
			218072, 240693, 263343, 285989, 308563, 331033, 353350, 375494, 397447, 419210, 440795, 462224, 483532,
			504758 };
	public final static String[] solarTerm = { "10031021", "10031001", "10031002", "10031003", "10031004", "10031005",
			"10031006", "10031024", "10031007", "10031008", "10031009", "10031010", "10031011", "10031012", "10031013",
			"10031016", "10031014", "10031017", "10031015", "10031018", "10031019", "10031022", "10031020",
			"10031023" };

	public String getTermString() {
		// 二十四节气
		String termString = "";
		if (Lunar.getSolarTermDay(solarYear, solarMonth * 2) == solarDay) {
			termString = Lunar.solarTerm[solarMonth * 2];
		} else if (Lunar.getSolarTermDay(solarYear, solarMonth * 2 + 1) == solarDay) {
			termString = Lunar.solarTerm[solarMonth * 2 + 1];
		}
		return termString;
	}

	/**
	 * 返回公历年节气的日期
	 * 
	 * @param solarYear
	 *            指定公历年份(数字)
	 * @param index
	 *            指定节气序号(数字,0从小寒算起)
	 * @return 日期(数字,所在月份的第几天)
	 */
	private static int getSolarTermDay(int solarYear, int index) {
		return Lunar.getUTCDay(getSolarTermCalendar(solarYear, index));
	}

	/**
	 * 返回公历年节气的日期
	 * 
	 * @param solarYear
	 *            指定公历年份(数字)
	 * @param index
	 *            指定节气序号(数字,0从小寒算起)
	 * @return 日期(数字,所在月份的第几天)
	 */
	public static Date getSolarTermCalendar(int solarYear, int index) {
		long l = (long) 31556925974.7 * (solarYear - 1900) + solarTermInfo[index] * 60000L - 1000 * 60 * 60 * 24;
		l = l + Lunar.UTC(1900, 0, 6, 2, 5, 0);
		return new Date(l);
	}

	/**
	 * 取 Date 对象中用全球标准时间 (UTC) 表示的日期
	 * 
	 * @param date
	 *            指定日期
	 * @return UTC 全球标准时间 (UTC) 表示的日期
	 */
	public static synchronized int getUTCDay(Date date) {
		Lunar.makeUTCCalendar();
		synchronized (utcCal) {
			utcCal.clear();
			utcCal.setTimeInMillis(date.getTime());
			return utcCal.get(Calendar.DAY_OF_MONTH);
		}
	}

	private static GregorianCalendar utcCal = null;

	private static synchronized void makeUTCCalendar() {
		if (Lunar.utcCal == null) {
			Lunar.utcCal = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
		}
	}

	/**
	 * 返回全球标准时间 (UTC) (或 GMT) 的 1970 年 1 月 1 日到所指定日期之间所间隔的毫秒数。
	 * 
	 * @param y
	 *            指定年份
	 * @param m
	 *            指定月份
	 * @param d
	 *            指定日期
	 * @param h
	 *            指定小时
	 * @param min
	 *            指定分钟
	 * @param sec
	 *            指定秒数
	 * @return 全球标准时间 (UTC) (或 GMT) 的 1970 年 1 月 1 日到所指定日期之间所间隔的毫秒数
	 */
	public static synchronized long UTC(int y, int m, int d, int h, int min, int sec) {
		Lunar.makeUTCCalendar();
		synchronized (utcCal) {
			utcCal.clear();
			utcCal.set(y, m, d, h, min, sec);
			return utcCal.getTimeInMillis();
		}
	}

	public static long DateDays(Date aDate, Date aDate2) {
		long myTime;
		long myTime2;
		long days = 0;
		myTime = (aDate.getTime() / 1000);
		// SimpleDateFormat formatter =new SimpleDateFormat("yyyy-MM-dd");
		myTime2 = (aDate2.getTime() / 1000);
		if (myTime > myTime2) {
			days = (myTime - myTime2) / (1 * 60 * 60 * 24);
		} else {
			days = (myTime2 - myTime) / (1 * 60 * 60 * 24);
		}
		return days;
	}

}
