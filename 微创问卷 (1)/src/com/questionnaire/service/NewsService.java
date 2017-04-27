package com.questionnaire.service;


import java.util.List;



import com.questionnaire.pojo.News;
import com.questionnaire.pojo.dto.PageDto;




public interface NewsService {
	
	public List<News>  showNews(Integer size, Integer themeId);
	public News queryNewsById(Integer newsId);
	public List<News> showImport();
	public PageDto<News> showSearchPage(Integer themeId,PageDto<News>  page,String keyWord);
	//显示某主题下的新闻列表
	public List<News> showNewsList(Integer themeId);
	public Integer updateNewsById(News news);
	public Integer insertNews(News news);
	public Integer deleteNewsById(Integer id);
}
