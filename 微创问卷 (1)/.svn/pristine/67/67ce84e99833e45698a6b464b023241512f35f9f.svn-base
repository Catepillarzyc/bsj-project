package com.questionnaire.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.questionnaire.dao.ThemeDao;
import com.questionnaire.pojo.Theme;
import com.questionnaire.service.ThemeService;


@Component("ThemeService")
public class ThemeServiceImpl implements ThemeService {
	@Autowired
	public ThemeDao themeDao;
	
	@Override
	public List<Theme> showTheme(Integer parentId) { 
		List<Theme>	list =	themeDao.queryAllThemeByParentId(parentId);
		return list;
	}

	@Override
	public Map<Integer, String> getThemeByThemeId() {
		List<Theme>	list =	themeDao.queryAllThemeByParentId(null);
		 Map<Integer, String> map = new HashMap<Integer, String>() ;
		for(Theme tm :list){
			map.put(tm.getId(), tm.getTheme());
		}
		return map;
		
	}



}
