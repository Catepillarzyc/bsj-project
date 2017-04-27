package com.questionnaire.util;

import java.security.MessageDigest;

public class MD5Utils {
	/**
	 * MD5加密生成32位MD5码
	 * 
	 * @param args
	 *            待加密字符串
	 * @return 返回32位MD5码
	 * @throws Exception
	 */
	public static String md5Encode(String args) throws Exception {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}

		byte[] byteArray = args.getBytes("UTF-8");
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}

	/**
	 * 主函数
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String args[]) throws Exception {
		String str = new String("123");
		System.out.println("原始：" + str);
		System.out.println("MD5后：" + md5Encode(str));
	}

}
