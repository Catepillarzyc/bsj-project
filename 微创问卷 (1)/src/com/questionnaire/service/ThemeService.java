package com.questionnaire.service;

import java.util.List;
import java.util.Map;

import com.questionnaire.pojo.Theme;



public interface ThemeService {
	
	public List<Theme>  showTheme(Integer parentId);
	public Map<Integer, String> getThemeByThemeId();
}
