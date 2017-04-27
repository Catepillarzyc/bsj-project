package com.questionnaire.service.impl;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.questionnaire.dao.ConsumerDao;
import com.questionnaire.pojo.Consumer;
import com.questionnaire.pojo.dto.PageDto;
import com.questionnaire.service.UserService;
import com.questionnaire.util.MD5Utils;



@Component("UserService")
public class UserServiceImpl  implements UserService {
	@Autowired
	private ConsumerDao userDao;

	@Override
	public Consumer queryUserByUserName(String userName) {	
		return userDao.queryUserByUserName(userName);
	}

	@Override
	public Integer updatePswByUserName(String userName, String newPsw) {
		try {
			newPsw = MD5Utils.md5Encode(newPsw);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userDao.updatePswByUserName(userName, newPsw);
	}

	@Override
	public Integer insertUser(Consumer user) {
		return userDao.insertUser(user);
	}

	@Override
	public Integer updateIsBlock(Integer isBlock, Integer id) {
		return userDao.updateIsBlock(isBlock,id);
	}

	@Override
	public PageDto<Consumer> showPageUser(PageDto<Consumer> page) {
		List<Consumer> list =userDao.queryQuestionUser(page.getStartIndex(), page.getPageSize());
		page.setRecords(list);
		page.setTotalRecords(userDao.countQuestionUser());
		return page;
	}

	@Override
	public Integer deleteUser(Integer id) {
		return userDao.deleteUser(id);
	}



}
