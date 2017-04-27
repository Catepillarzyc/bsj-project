package com.questionnaire.service;



import com.questionnaire.pojo.Consumer;
import com.questionnaire.pojo.dto.PageDto;

public interface UserService {
	public Consumer  queryUserByUserName(String userName);
	public Integer updatePswByUserName(String userName, String newPsw);
	public Integer  insertUser(Consumer user);
	public Integer updateIsBlock(Integer isBlock, Integer id);
	public PageDto<Consumer> showPageUser(PageDto<Consumer> page);
	public Integer deleteUser(Integer id);

}
