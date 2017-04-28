package com.questionnaire.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang.StringUtils;

public class XssHttpServletRequestWraper extends HttpServletRequestWrapper {

	public XssHttpServletRequestWraper(HttpServletRequest request) {
		super(request);
	}

	/**
	 * 覆盖getParameter方法，将参数名和参数值都做xss过滤。<br/>
	 * 如果需要获得原始的值，则通过super.getParameterValues(name)来获取<br/>
	 * getParameterNames,getParameterValues和getParameterMap也可能需要覆盖
	 */
	@Override
	public String getParameter(String name) {
		String[] value1 = super.getParameterValues(name);
		String value = super.getParameter(xssEncode(name));
		if (value != null) {
			value = xssEncode(value);
		}
		return value;
	}

	/**
	 * 覆盖getHeader方法，将参数名和参数值都做xss过滤。<br/>
	 * 如果需要获得原始的值，则通过super.getHeaders(name)来获取<br/>
	 * getHeaderNames 也可能需要覆盖
	 */
	@Override
	public String getHeader(String name) {

		String value = super.getHeader(xssEncode(name));
		if (value != null) {
			value = xssEncode(value);
		}
		return value;
	}

	@Override
	public String[] getParameterValues(String name) {
		// Constants.MY_LOG.debug("getParameterValues----->转义处理");
		if (!StringUtils.isEmpty(name)) {
			String[] values = super.getParameterValues(name);
			if (values != null && values.length > 0) {
				String[] newValues = new String[values.length];

				for (int i = 0; i < values.length; i++) {
					newValues[i] = clearXss(values[i]); // 保留勿删
					newValues[i] = xssEncode(newValues[i]);
				}
				return newValues;
			}
		}
		return null;
	}

	private String clearXss(String value) {
		if (value == null || "".equals(value)) {
			return value;
		}
		value = value.replaceAll("eval\\((.*)\\)", "");
		value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
		value = value.replace("script", "");
		return value;
	}

	/*
	 * private String clearXss(String value) { if (value == null ||
	 * "".equals(value)) { return value; } value = value.replaceAll("<",
	 * "&lt;").replaceAll(">", "&gt;"); value = value.replaceAll("\\(",
	 * "&#40;").replace("\\)", "&#41;"); value = value.replaceAll("'", "&#39;");
	 * value = value.replaceAll("eval\\((.*)\\)", ""); value =
	 * value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
	 * value = value.replace("script", ""); return value; }
	 */

	/**
	 * 将特殊字符替换为实体编号
	 * 
	 * @param s
	 * @return
	 */
	private String xssEncode(String s) {
		if (s == null || s.isEmpty()) {
			return s;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			switch (c) {
			case '>':
				sb.append("&#62;");// 实体编号大于号
				break;
			case '<':
				sb.append("&#60;");// 实体编号小于号
				break;
			case '\'':
				sb.append("&#39;");// 实体编号单引号
				break;
			case '\"':
				sb.append("&#34;");// 实体编号双引号
				break;
			case '&':
				sb.append("&#38;");// 实体编号＆
				break;
			case '\\':
				sb.append("&#92;");// 实体编号斜线
				break;
			case '/':
				sb.append("&#47;");// 实体编号斜线
				break;
			case '#':
				sb.append("&#35;");// 实体编号井号
				break;
			case '(':
				sb.append("&#40;");// 实体编号(号
				break;
			case ')':
				sb.append("&#41;");// 实体编号)号
				break;
			default:
				sb.append(c);
				break;
			}
		}
		return sb.toString();
	}
}