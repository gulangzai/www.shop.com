package com.xue.service;

import com.xue.model.User;

public interface UserService {

	User login(String userId, String newpassword);

	void updateByPrimaryKeySelective(User user);

}
