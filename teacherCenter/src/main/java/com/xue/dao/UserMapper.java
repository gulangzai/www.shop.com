package com.xue.dao;

import com.xue.model.User;
import com.xue.vo.query.UserQuery;

public interface UserMapper {

	User login(UserQuery userQuery);

	/*更新个人信息*/
	void updateByPrimaryKeySelective(User user);

}
