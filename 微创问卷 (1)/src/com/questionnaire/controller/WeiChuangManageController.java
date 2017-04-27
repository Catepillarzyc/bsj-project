package com.questionnaire.controller;

import java.io.File;
import java.io.IOException;

import java.util.Iterator;
import java.util.List;
import java.util.Map;


import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSONObject;
import com.questionnaire.pojo.Consumer;
import com.questionnaire.pojo.News;
import com.questionnaire.pojo.Theme;
import com.questionnaire.pojo.dto.PageDto;
import com.questionnaire.service.NewsService;
import com.questionnaire.service.ThemeService;
import com.questionnaire.service.UserService;
import com.questionnaire.util.DeleteFileUtil;
import com.questionnaire.util.MD5Utils;
import com.questionnaire.util.StringUtils;

@RequestMapping("/weiChuangManage")
@Controller
public class WeiChuangManageController {

	@Resource
	private UserService userService;
	@Resource
	private NewsService newsService;
	@Resource
	private ThemeService themeService;
	
	@RequestMapping("login")
	public String login(Map<String, Object> out){
		return null;
	}
	
	@RequestMapping("index")
	public String index(Map<String, Object> out, HttpServletRequest request){
		return null;
	}
	
	//退出功能
	@RequestMapping("loginOut")
	public String loginOut(HttpServletRequest request, String pageFlag){
		HttpSession session = request.getSession();
		session.removeAttribute("us");
		return  "redirect:/weiChuangManage/login.html";
		
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
							session.setAttribute("us", user);
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
	
	//修改密码
		@RequestMapping("updatePassword")
		public String updatePassword(Map<String,Object> out,HttpServletRequest request, String newPsw ,String oldPsw){
			JSONObject json = new JSONObject();
			Integer flag =-9;
			if(StringUtils.isNotEmpty(oldPsw)){
				Consumer user  = (Consumer) request.getSession().getAttribute("us");
				try {
					oldPsw= MD5Utils.md5Encode(oldPsw);
					if(user.getPassword().equals(oldPsw)){
						flag=userService.updatePswByUserName(user.getUserName(),newPsw);
					}else{
						flag=2;//旧密码错误
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				flag=3;//旧密码为空
			}
			json.put("flag", flag);
			out.put("json", json);
			return "json";
			
		}
	
	//新闻列表页面
	@RequestMapping("newsList")
	public String newsList(Map<String, Object> out,Integer index, Integer themeId,String keyWord){
	    //获得筛选主题列表
	 	 List<Theme>  themeList = themeService.showTheme(0); 
	 	 out.put("themeList",themeList);
	     Map<Integer, String> map =	themeService.getThemeByThemeId();
	     out.put("map",map);
		if(index!=null && index>0){
		}else{
			index=1;
		}
		PageDto<News> page = new PageDto<News>();
		page.setCurrentPage(index);
		page.setPageSize(6);
	    page = newsService.showSearchPage(themeId, page, keyWord);
	    out.put("page", page);
	    out.put("keyWord", keyWord);
	    out.put("themeId", themeId);
		return null;
	}
	//用户列表页面
	@RequestMapping("userList")
	public String userList(Map<String, Object> out, HttpServletRequest request,Integer index){
		if(index!=null && index>0){
		}else{
			index=1;
		}
		PageDto<Consumer>  page = new PageDto<Consumer>();
		page.setCurrentPage(index);
		page.setPageSize(6);
		page = userService.showPageUser(page);
		out.put("page", page);
		return null;
	}
	//初始化密码
	@RequestMapping("initalPsw")
	public String initalPsw(Map<String, Object> out, String userName){
		JSONObject json = new JSONObject();
		Integer flag = -9;
		 if(StringUtils.isNotEmpty(userName)){
			 flag = userService.updatePswByUserName(userName, "123456");
		 }
		json.put("flag", flag);
		out.put("json", json);
		return "json";
	}
	
	//启用 和停用用户
	@RequestMapping("updateIsBlock")
	public String updateIsBlock(Map<String, Object> out, Integer isBlock, Integer userId){
		JSONObject json = new JSONObject();
		Integer flag = -9;
		flag=userService.updateIsBlock(isBlock,userId);
		json.put("flag", flag);
		out.put("json", json);
		return "json";
	}
	//删除用户
	@RequestMapping("delteUser")
	public  String delteUser(Map<String, Object> out, Integer userId){
		JSONObject json = new JSONObject();
		Integer flag = -9;
		flag =userService.deleteUser(userId);
		json.put("flag", flag);
		out.put("json", json);
		return "json";
	}
	
	//新建用户
	@RequestMapping("creatUser")
	public String creatUser(Map<String, Object> out,Integer flag){
	    out.put("flag", flag);
		return null;
	}
	@RequestMapping("modifyUser")
	public String modifyUser(Map<String, Object> out,Consumer user){
		Integer flag = 0;
		if(user.getId()!=null){
		}else{
			try {
				user.setPassword(MD5Utils.md5Encode(user.getPassword()));
			     flag =userService.insertUser(user);
				if(flag>0){
				}else{
					flag = -2;
			    }
			} catch (Exception e) {
				e.printStackTrace();
			}
			out.put("flag", flag);
		}
		return "redirect:/weiChuangManage/creatUser.html?flag="+flag;
	}
	
	//用户名唯一性验证
	@RequestMapping("userNameIsExist")
	public String userNameIsExist(Map<String, Object> out, String userName){
		JSONObject json = new JSONObject();
		Consumer user = userService.queryUserByUserName(userName);
		Integer flag = -9;
		if(user!=null){
			flag =1;
		}
		json.put("flag", flag);
		out.put("json", json);
		return "json";
		
	}
	//修改和新增新闻页面
	@RequestMapping("newArticle")
	public String newArticle(Map<String, Object> out, Integer newsId, Integer flag){
		 //获得筛选主题列表
	 	 List<Theme>  themeList = themeService.showTheme(0); 
	 	 out.put("themeList",themeList);
	 	 News news = newsService.queryNewsById(newsId);
	 	 out.put("news", news);
	 	 out.put("flag", flag);
		return "weiChuangManage/newArticle/newArticle";
	}
	//删除新闻
	@RequestMapping("deleteNewsById")
	public String deleteNewsById(Map<String, Object> out, Integer newsId){
		JSONObject json = new JSONObject();
		Integer flag = newsService.deleteNewsById(newsId);
		json.put("flag", flag);
		out.put("json", json);
		return "json";
	}
	
	//修改或新添加一条新闻
	@RequestMapping("modifyNews")
	public String modifyNews(Map<String, Object> out, News news){
		if(news.getId()!=null){//修改新闻
			newsService.updateNewsById(news);
		    return  "redirect:/weiChuangManage/newsList.html";
		}else{//新增新闻
			 Integer flag =newsService.insertNews(news);
			return "redirect:/weiChuangManage/newArticle.html?flag="+flag;
		}
	}
	//上传图片
	@RequestMapping("uploadImag")
	public String uploadImag(Map<String, Object> out,HttpServletResponse response, HttpServletRequest request) throws IOException, ServletException{
		String url = "";
		String fileName ="";
		// 创建一个通用的多部分解析器
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 判断 request 是否有文件上传,即多部分请求
		if (multipartResolver.isMultipart(request)) {
			// 转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 取得request中的所有文件名
			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				// 取得上传文件
				MultipartFile file = multiRequest.getFile(iter.next());
				if (file != null) {
					// 取得当前上传文件的文件名称
					String myFileName = file.getOriginalFilename();
					
					// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
					if (myFileName.trim() != "") {
						// 重命名上传后的文件名
						 fileName = System.currentTimeMillis() + "." + myFileName.split("\\.")[1];
						// 定义上传路径
						// String path = request.getRealPath("/") + "images/" +
						// fileName;
						@SuppressWarnings("deprecation")
						String path = request.getRealPath("/") + "upload/" + fileName;
						File localFile = new File(path);
						url = "upload/" + fileName;
						file.transferTo(localFile);
						 // 返回图片的URL地址
				        response.getWriter().write("../"+url);

					}
				}
			}
		}
		return null;
	}
	//删除上传的图片
	@RequestMapping("deletePic")
	public String deletePic(Map<String, Object> out,HttpServletResponse response, HttpServletRequest request){
		String path = request.getRealPath("/") ;
	//	DeleteFileUtil.DeleteFolder(path);
		return null;
	}
	
}
