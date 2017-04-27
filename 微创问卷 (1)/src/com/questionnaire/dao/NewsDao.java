package com.questionnaire.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.questionnaire.pojo.News;



public interface NewsDao {
	
	public List<News> queryNewsAll();
	public News queryNewsById(@Param("id")Integer id);
	public List<News> queryNewsByImport();//查询最新动态
	public List<News> queryNewsByKeyWordAndThemeId(@Param("themeId")Integer themeId,@Param("keyWord")String  keyWord,@Param("start")Integer start, @Param("size")Integer size);
	public Integer countNewsByKeyWordAndThemeId(@Param("themeId")Integer themeId,@Param("keyWord")String  keyWord);
	public List<News> queryNewsByThemeId(@Param("themeId")Integer themeId);
	public Integer updateNewsById(@Param("news")News news);
	public Integer insertNews(@Param("news")News news);
	public Integer deleteNewsById(@Param("id")Integer id);
}
