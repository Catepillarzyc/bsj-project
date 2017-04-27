package com.questionnaire.controller;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.questionnaire.pojo.Consumer;
import com.questionnaire.pojo.News;
import com.questionnaire.pojo.Theme;
import com.questionnaire.pojo.dto.NewsDto;
import com.questionnaire.pojo.dto.PageDto;
import com.questionnaire.service.NewsService;
import com.questionnaire.service.ThemeService;
import com.questionnaire.service.UserService;

import com.questionnaire.util.MD5Utils;

import com.questionnaire.util.VerifyCodeUtils;



@RequestMapping("/weiChuang")
@Controller
public class WeiChuangController {
	@Resource
	private NewsService newsService;
	@Resource
	private UserService userService;
	@Resource
	private ThemeService themeService;
	@RequestMapping("index")
	public String index(Map<String,Object> out,HttpServletRequest request, Integer loginFlag){
		Consumer user  = (Consumer) request.getSession().getAttribute("user");
		out.put("user", user);
		out.put("loginFlag", loginFlag);
		//存放最新动态
		List<News> importList = newsService.showImport();
 	    //存放中心动态
		List<News> trendList= newsService.showNews(5, 1);
 	    //存放regulation
		List<News> reList = newsService.showNews(5, 2);
 	    //存放health
		List<News> healthList = newsService.showNews(5, 3);
 	    //获得筛选主题列表
 	   List<Theme>  list = themeService.showTheme(0);  
 	   out.put("importList", importList);
 	    out.put("list", list);
 	    out.put("trendList", trendList);
 	    out.put("reList", reList);
 	    out.put("healthList", healthList); 	   
		return null;
	}
	//登录页面
	@RequestMapping("questionLogin")
	public String questionLogin(Map<String,Object> out, Integer loginFlag){
		String  verifyCode=VerifyCodeUtils.generateVerifyCode(4);
		out.put("verifyCode", verifyCode);
		out.put("loginFlag", loginFlag);
		return null;
	}
	//刷新校验码
	@RequestMapping("refreshVerifyCode")
	public String refreshVerifyCode(Map<String,Object> out){
		JSONObject json = new JSONObject();
	    String  verifyCode=VerifyCodeUtils.generateVerifyCode(4);
		json.put("verifyCode", verifyCode);
		out.put("json", json);
		return "json";	
		
	}
	
	
	//后台管理页面
	@RequestMapping("houTai") 
	public String houTai(Map<String,Object> out,HttpServletRequest request){
		Consumer user  = (Consumer) request.getSession().getAttribute("user");
			out.put("user", user);
		return null;
		
	}
	@RequestMapping("loginOut")
	public String loginOut(HttpServletRequest request, String pageFlag){
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		return  "redirect:/weiChuang/index.html";
		
	}
	
	
	
	
	@RequestMapping("checkLogin")
	public String  checkLogin(Map<String,Object> out,HttpServletRequest request, String password,String userName,
			HttpServletResponse response) {
		JSONObject json = new JSONObject();
		Integer loginFlag = 0;
		Consumer user = userService.queryUserByUserName(userName);
		if(user!=null){
			   try {
					   String s =MD5Utils.md5Encode(password);
					   if(user.getPassword().equals(s)){
						   HttpSession session = request.getSession();
						// 存储用户到session
							session.setAttribute("user", user);
						   loginFlag = 2;//登录成功
						   json.put("user", user);
					   }else{
						   loginFlag = 3;  //密码错误
					   }
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			loginFlag = 1; //用户名错误
		}
		json.put("loginFlag", loginFlag);
		out.put("json",json);
		return "json";
	}

	//中心简介
	@RequestMapping("centerIntroduce")
	public String centerIntroduce(Map<String,Object> out, Integer themeId, HttpServletRequest request){
		Consumer user  = (Consumer) request.getSession().getAttribute("user");
	     out.put("user", user);
		//查出中心动态的子主题
		List<Theme> list =themeService.showTheme(themeId);
		 if(list.size()>0){
			out.put("list", list);
		 }
		return null;
	}
	//中心动态
	@RequestMapping("centerTrends")
	public String centerTrends(Map<String,Object> out,Integer themeId, Integer index,String serchKey, HttpServletRequest request, Integer loginFlag){
		Consumer user  = (Consumer) request.getSession().getAttribute("user");
		out.put("user", user);
		out.put("loginFlag", loginFlag);
		if(index!=null && index>0){
		}else{
			index=1;
		}
		 PageDto<News>  page = new PageDto<News>();
		 page.setCurrentPage(index);
		 page.setPageSize(6);
		 page = newsService.showSearchPage(themeId, page,serchKey);
		out.put("page", page);
		out.put("serchKey", serchKey);
		return null;
	}
	//中心动态的详情
	@RequestMapping("centerTrendsDetail")
	public String centerTrendsDetail(Map<String,Object> out,Integer newsId,Integer loginFlag, HttpServletRequest request ){
		Consumer user  = (Consumer) request.getSession().getAttribute("user");
		out.put("user", user);
		out.put("loginFlag", loginFlag);
		News news = newsService.queryNewsById(newsId);
		out.put("news", news);
		return null;
	}

	//标准法规
	@RequestMapping("regulation")
	public String regulation(Map<String,Object> out, Integer themeId,HttpServletRequest request ){	
		Consumer user  = (Consumer) request.getSession().getAttribute("user");
		out.put("user", user);
		out.put("user", user);
		//根据法规的主题，查询子主题
		 List<Theme>  list=themeService.showTheme(themeId);
		 if(list.size()>0){
			 out.put("list", list);
			 List<NewsDto> newsList = new ArrayList<NewsDto>();
			 for(Theme theme:list ){		
				 NewsDto newsDto = new NewsDto();
			     List<News>  childreList = newsService.showNewsList(theme.getId());
			     newsDto.setList(childreList);
			     newsDto.setThemid(theme.getId());
			     newsList.add(newsDto);
			 }
			 out.put("newsList", newsList);
		 }
		return null;
	}
	//	//标准法规详情
	@RequestMapping("regulationDetail")
	public String regulationDetail(Map<String,Object> out,Integer newsId, Integer themeId,HttpServletRequest request ){
		Consumer user  = (Consumer) request.getSession().getAttribute("user");
		out.put("user", user);
		out.put("user", user);
		//根据法规的主题，查询子主题
		 List<Theme>  list=themeService.showTheme(themeId);
		 if(list.size()>0){
			 out.put("list", list);
			 List<NewsDto> newsList = new ArrayList<NewsDto>();
			 for(Theme theme:list ){		
				 NewsDto newsDto = new NewsDto();
			     List<News>  childreList = newsService.showNewsList(theme.getId());
			     newsDto.setList(childreList);
			     newsDto.setThemid(theme.getId());
			     newsList.add(newsDto);
			 }
			 out.put("newsList", newsList);
		 }
		News news = newsService.queryNewsById(newsId);
		out.put("news", news);
		return null;		
	}
	//通知公告
	@RequestMapping("notice")
	public String notice(Map<String,Object> out,Integer themeId, Integer index, String serchKey,HttpServletRequest request,  Integer loginFlag){
		Consumer user  = (Consumer) request.getSession().getAttribute("user");
		out.put("user", user);
		out.put("loginFlag", loginFlag);
		out.put("serchKey", serchKey);
		if(index!=null && index>0){
		}else{
			index=1;
		}
		 PageDto<News>  page = new PageDto<News>();
		 page.setCurrentPage(index);
		 page.setPageSize(6);
		 page = newsService.showSearchPage(themeId, page,serchKey);
		out.put("page", page);
		return null;
	}
	//通知公告详情页面
	@RequestMapping("noticeDetail")
	public String noticeDetail(Map<String,Object> out,Integer newsId,HttpServletRequest request,  Integer loginFlag){
		Consumer user  = (Consumer) request.getSession().getAttribute("user");
		out.put("user", user);
		out.put("user", user);
		out.put("loginFlag", loginFlag);
		News news = newsService.queryNewsById(newsId);
		out.put("news", news);
		return null;
	}
	
	//质控规范
	@RequestMapping("qualityStandard")
	public String qualityStandard(Map<String,Object> out, Integer themeId, Integer index, String serchKey,HttpServletRequest request , Integer loginFlag){
		Consumer user  = (Consumer) request.getSession().getAttribute("user");
		out.put("user", user);
		out.put("user", user);
		out.put("loginFlag", loginFlag);
		out.put("serchKey", serchKey);
		if(index!=null && index>0){
		}else{
			index=1;
		}
		 PageDto<News>  page = new PageDto<News>();
		 page.setCurrentPage(index);
		 page.setPageSize(6);
		 page = newsService.showSearchPage(themeId, page,serchKey);
		out.put("page", page);
		return null;
	}	
	//质控规范详情页面
	@RequestMapping("qualityStandardDetail")
	public String qualityStandardDetail(Map<String,Object> out,Integer newsId, HttpServletRequest request, Integer loginFlag){
		Consumer user  = (Consumer) request.getSession().getAttribute("user");
		out.put("user", user);
		out.put("user", user);
		out.put("loginFlag", loginFlag);
		News news = newsService.queryNewsById(newsId);
		out.put("news", news);
		return null;
		
	}
	// 健康主题
	@RequestMapping("healthTheme")
	public String healthTheme(Map<String,Object> out,Integer themeId, Integer index, String serchKey, HttpServletRequest request, Integer loginFlag){
		Consumer user  = (Consumer) request.getSession().getAttribute("user");
		out.put("user", user);
		out.put("user", user);
		out.put("loginFlag", loginFlag);
		out.put("serchKey", serchKey);
		if(index!=null && index>0){
		}else{
			index=1;
		}
		 PageDto<News>  page = new PageDto<News>();
		 page.setCurrentPage(index);
		 page.setPageSize(6);
		 page = newsService.showSearchPage(themeId, page,serchKey);
		out.put("page", page);
		return null;
	}
	
	//健康主题详情
	@RequestMapping("healthThemeDetail")
	public String healthThemeDetail(Map<String,Object> out,Integer newsId, HttpServletRequest request, Integer loginFlag){
		Consumer user  = (Consumer) request.getSession().getAttribute("user");
		out.put("user", user);
		out.put("user", user);
		out.put("loginFlag", loginFlag);
		News news = newsService.queryNewsById(newsId);
		out.put("news", news);
		return null;			
	}
	
	@RequestMapping("searchTheme")
	public String searchTheme(Map<String,Object> out,String serchKey, Integer themeId ){
		 JSONObject json = new JSONObject();
		 json.put("themeId", themeId);
		 json.put("serchKey", serchKey);
		 out.put("json", json);
		 return "json";
	}	
	
}
