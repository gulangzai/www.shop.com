package com.xue.service.impl;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xue.dao.UserMapper;
import com.xue.model.User;
import com.xue.service.UserService;
import com.xue.vo.query.StudentInfoQuery;
import com.xue.vo.query.UserQuery;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;
	
	@Override
	public User login(String userName, String password) {
		// TODO Auto-generated method stub
		UserQuery userQuery = new UserQuery();
		userQuery.setPassword(password);
		if (Pattern
				.compile("^((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$")
				.matcher(userName).matches()) {
			userQuery.setMobileNumber(userName);
			return userMapper.login(userQuery);
		} /*else if (Pattern
				.compile(
						"^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$")
				.matcher(userName).matches()) {
			studentInfoQuery.setEmail(userName);
			return studentInfoMapper.login(userQuery);
		}*/
		userQuery.setUserName(userName);
		return userMapper.login(userQuery); 
	}

	@Override
	public void updateByPrimaryKeySelective(User user) {
		// TODO Auto-generated method stub
		userMapper.updateByPrimaryKeySelective(user);
	}

}
