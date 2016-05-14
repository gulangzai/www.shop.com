package com.jiuji.cn.dao;

import java.util.List;

import com.jiuji.cn.model.TUser;

public interface UserMapper {

	List<TUser> select(TUser t_user);
	
	TUser selectOnlyone(TUser tuser);

	void insert(TUser tuser); 
}
