package com.questionnaire.web.filter;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;


public class InitSystem implements ApplicationListener<ContextRefreshedEvent> {

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		//CategoryService categoryService = (CategoryService) event.getApplicationContext().getBean("categoryService");
		//CategoryCached.init(categoryService.getCategoryList());
		//AreacodeService as =(AreacodeService)event.getApplicationContext().getBean("AreacodeService");
	 	//AreaCodeCached.init(as.queryAreaCodeByParentCode(null));
		//WardAreaService ws = (WardAreaService) event.getApplicationContext().getBean("wardAreaService");
		//WardAreaCached.init(ws.queryWardAreaByCondition(null, 0, null));
		//if (event.getApplicationContext().getParent() == null) {
		//}

	}

}
