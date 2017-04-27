package com.questionnaire.service.impl;

import java.util.ArrayList;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.questionnaire.dao.NewsDao;
import com.questionnaire.pojo.News;
import com.questionnaire.pojo.dto.PageDto;
import com.questionnaire.service.NewsService;



@Component("NewsService")
public class NewsServiceImpl implements NewsService {
	@Autowired
	private NewsDao newsDao;

	@Override
	public List<News> showNews(Integer size, Integer themeId) {
		List<News> list =newsDao.queryNewsByThemeId(themeId);
		List<News> newsList = new ArrayList<News>();
		for(Integer i=0; i<=size; i++){
					newsList.add(list.get(i));
		}
	 return newsList;
	}

	@Override
	public News queryNewsById(Integer newsId) {
		return newsDao.queryNewsById(newsId);
	}



	@Override
	public List<News> showImport() {
		List<News> list = newsDao.queryNewsByImport();	
		List<News> nList = new ArrayList<News>();
		if(list.size()>0){
			int j =list.size()>5?5:list.size()-1;
			for(int i=0 ; i<j; i++){
				nList.add(list.get(i));
			}
		}
		return nList;
	}

	@Override
	public PageDto<News> showSearchPage(Integer themeId, PageDto<News> page, String keyWord) {
		List<News> list = newsDao.queryNewsByKeyWordAndThemeId(themeId, keyWord, page.getStartIndex(), page.getPageSize());
		page.setRecords(list);
		page.setTotalRecords(newsDao.countNewsByKeyWordAndThemeId(themeId, keyWord));
		return page;
	}

	@Override
	public List<News> showNewsList(Integer themeId) {
		List<News> list =newsDao.queryNewsByKeyWordAndThemeId(themeId,null, null, null);
		List<News> nList = new ArrayList<News>();
		if(list.size()>0){
			int j =list.size()>5?5:list.size()-1;
			for(int i=0 ; i<=j; i++){
				nList.add(list.get(i));
			}
		}
		return nList;
	}

	@Override
	public Integer updateNewsById(News news) {
		return newsDao.updateNewsById(news);
	}

	@Override
	public Integer insertNews(News news) {
		return newsDao.insertNews(news);
	}

	@Override
	public Integer deleteNewsById(Integer id) {
		return newsDao.deleteNewsById(id);
	}

}
