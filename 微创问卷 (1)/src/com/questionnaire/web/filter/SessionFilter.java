package com.questionnaire.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession();
//		if (request.getServletPath().indexOf("/index.html") > -1
//				|| request.getServletPath().indexOf("/check.html") > -1
//				|| request.getServletPath().indexOf("/question/questionList.html") > -1
//				|| request.getServletPath().indexOf("/question/questionDetail.html") > -1
//				|| request.getServletPath().indexOf("/cardReader.html") > -1
//				|| request.getServletPath().indexOf("/app/") > -1 || request.getServletPath().indexOf("/apk/") > -1
//				|| request.getServletPath().indexOf("/weiChuangManage/index.html") > -1 
//				|| request.getServletPath().indexOf("/weiChuangManage/login.html") > -1
//				){
//			chain.doFilter(request, response);
//		} else {
//			if (session.getAttribute("flag") == null) {
//				response.sendRedirect(request.getContextPath() + "/weiChuang/index.html");
//			} else {
//				chain.doFilter(request, response);
//			}
//		}

	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
