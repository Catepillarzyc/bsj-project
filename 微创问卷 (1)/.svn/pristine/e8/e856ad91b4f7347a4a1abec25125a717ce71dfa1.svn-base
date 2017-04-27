package com.questionnaire.util;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: CommonUtils
 * @Description: 一些公用方法
 * @author lichao
 * @date 2014年6月17日 下午4:25:37
 * 
 */
public class CommonUtils {

	/**
	 * @Title: genRandomNum @Description: 返回指定位数的随机数字 @param @param
	 * pwd_len @param @return 设定文件 @return String 返回类型 @throws
	 */
	public static String genRandomNum(int pwd_len) {
		final int maxNum = 36;
		int i;
		int count = 0;
		char[] str = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		StringBuffer pwd = new StringBuffer("");
		Random r = new Random();
		while (count < pwd_len) {
			i = Math.abs(r.nextInt(maxNum));

			if (i >= 0 && i < str.length) {
				pwd.append(str[i]);
				count++;
			}
		}
		return pwd.toString();
	}

	/**
	 * @Title: genRandomNum @Description: 返回指定位数的随机字母 @param @param
	 * pwd_len @param @return 设定文件 @return String 返回类型 @throws
	 */
	public static String genRandomChar(int pwd_len) {
		final int maxNum = 36;
		int i;
		int count = 0;
		char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
				't', 'u', 'v', 'w', 'x', 'y', 'z' };
		StringBuffer pwd = new StringBuffer("");
		Random r = new Random();
		while (count < pwd_len) {
			i = Math.abs(r.nextInt(maxNum));

			if (i >= 0 && i < str.length) {
				pwd.append(str[i]);
				count++;
			}
		}
		return pwd.toString();
	}

	/**
	 * @Title: genRandomStr @Description: 返回指定位数的随机数字或字母 @param @param
	 * pwd_len @param @return 设定文件 @return String 返回类型 @throws
	 */
	public static String genRandomStr(int pwd_len) {
		final int maxNum = 36;
		int i;
		int count = 0;
		char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
				't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		StringBuffer pwd = new StringBuffer("");
		Random r = new Random();
		while (count < pwd_len) {
			i = Math.abs(r.nextInt(maxNum));

			if (i >= 0 && i < str.length) {
				pwd.append(str[i]);
				count++;
			}
		}
		return pwd.toString();
	}

	/**
	 * @Title: doubleFormat
	 * @Description: 数值格式化
	 * @param value
	 * @return
	 */
	public static String doubleFormat(String value) {
		if (isEmpty(value)) {
			return null;
		}
		Double d = Double.valueOf(value);
		DecimalFormat format = new DecimalFormat("0.00");
		return format.format(d);

	}

	public static String getSignatureSeed(Long userNum) {
		StringBuffer seed = new StringBuffer();
		seed.append(userNum);
		Date date = new Date();
		String dateStr = DateUtil.DateToStr(date, "yyyyMMddHHmmss");
		seed.append(dateStr);
		seed.append(genRandomStr(8));
		return seed.toString();
	}

	public static boolean isEmpty(String str) {
		if (str == null || "".equals(str.trim()) || "[]".equals(str) || str.equals("{}")) {
			return true;
		}
		return false;
	}

	public static boolean isEmpty(Object obj) {
		if (obj == null) {
			return true;
		}
		return false;
	}

	// 首字母转小写
	public static String toLowerCaseFirstOne(String s) {
		if (Character.isLowerCase(s.charAt(0)))
			return s;
		else
			return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
	}

	// 首字母转大写
	public static String toUpperCaseFirstOne(String s) {
		if (Character.isUpperCase(s.charAt(0)))
			return s;
		else
			return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
	}

	/**
	 * 地球经度或纬度转化为弧度(rad)
	 */
	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	/**
	 * 基于googleMap中的算法得到两经纬度之间的距离,计算精度与谷歌地图的距离精度差不多，相差范围在0.2米以下
	 * 
	 * @param lon1
	 *            第一点的精度
	 * @param lat1
	 *            第一点的纬度
	 * @param lon2
	 *            第二点的精度
	 * @param lat3
	 *            第二点的纬度
	 * @return 返回的距离，单位m
	 */
	public static double GetDistance(double lon1, double lat1, double lon2, double lat2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lon1) - rad(lon2);
		double s = 2 * Math.asin(Math.sqrt(
				Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
		// s = s * CommonConstants.EARTH_RADIUS;
		s = Math.round(s * 10000) / 10000;
		return s;
	}

	/**
	 * @Title: getStyleNumber
	 * @Description:编号自增
	 * @param old
	 * @return
	 */
	public static String getStyleNumber(String old) {

		if (isEmpty(old)) {
			return null;
		}
		String pre = old.substring(0, 2);
		int num = Integer.parseInt(old.substring(2)) + 1;

		String newNum = pre + getFomartString(old.length() - 2, num);

		return newNum;
	}

	/**
	 * @Title: getFomartString
	 * @Description: 数字转换成定长的字符串，前面加0
	 * @param length
	 * @param num
	 * @return
	 */
	public static String getFomartString(int length, int num) {
		StringBuffer result = new StringBuffer();
		String numStr = String.valueOf(num);
		for (int i = 0; i < length - numStr.length(); i++) {
			result.append("0");
		}
		result.append(numStr);

		return result.toString();

	}

	/**
	 * @Title: toLikeParam
	 * @Description: 模糊查询参数转换
	 * @param param
	 * @return
	 */
	public static String toLikeParam(String param) {
		if (param != null) {
			param = "%" + param + "%";
		}
		return param;
	}

	/**
	 * 判断给定的Integer是否正常 0/null:false | true
	 * 
	 * @param intp
	 * @return
	 */
	public static boolean isIntegerRight(Integer intp) {
		if (intp == null || intp.intValue() == 0) {
			return false;
		} else {
			return true;
		}
	}

	public static boolean isIntegerEq(Integer int1, Integer int2) {
		if (int1 == null || int2 == null) {
			return false;
		}

		if (int1.intValue() == int2.intValue()) {
			return true;
		} else {
			return false;
		}
	}

	public static String parseImgSize(String path, int size) {
		int dot = path.lastIndexOf(".");
		return path.substring(0, dot) + "_" + size + "_" + size + path.substring(dot);
	}

	/**
	 * 判断一个字符串是否为整数
	 * 
	 * @param val
	 * @return
	 */
	public static boolean isValidInt(String val) {
		try {
			Integer.parseInt(val);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	/**
	 * @Title: replace
	 * @Description: 不区分大小写替换字符串
	 * @param input
	 * @param regex
	 * @param replaceMent
	 * @return
	 */
	public static String replace(String input, String regex, String replaceMent) {
		Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(input);
		String result = m.replaceAll(replaceMent);
		return result;
	}

	public static String filterSpechars(String str) {
		String regEx = "[\\\\`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？\"\"]";
		str = str.replaceAll(regEx, " ");
		return str;
	}

	/**
	 * @Title: handlerFile
	 * @Description: 图片上传，非同步
	 * @param file
	 * @return
	 * @throws IOException
	 */
	/*
	 * public static String handlerFile(File file) { String suffixName =
	 * file.getName(); suffixName =
	 * suffixName.substring(suffixName.lastIndexOf(".")); String filePath =
	 * FileUtil.getFilePath(suffixName);
	 * 
	 * FileInputStream in = null; try { in = new FileInputStream(file);
	 * 
	 * FTSExecutor.send(in, filePath, false); //FileHandler.parse(filePath, in);
	 * file.deleteOnExit(); } catch (FileNotFoundException e) {
	 * e.printStackTrace(); }
	 * 
	 * return FileUtil.cleanPath(filePath); }
	 */

	public static void main(String args[]) {
		double a = 0.10;
		double b = 0.06;
		System.out.println(a - b);
	}

	public static Integer getWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		return w;
	}

}
