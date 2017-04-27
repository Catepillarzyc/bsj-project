package com.questionnaire.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.questionnaire.pojo.User;



public interface UserDao {
	public User queryUserByUserName(@Param("userName") String userName);
	public Integer updatePswByUserName(@Param("userName") String userName, @Param("newPsw") String newPsw);
	public Integer insertUser(@Param("user")User user);
	public Integer updateIsBlock(@Param("isBlock")Integer isBlock);
	public List<User> queryAllUser(@Param("start") Integer start, @Param("size")Integer size);
	public Integer countAllUser();
}
