package com.jiuji.cn.dao;

import java.util.List;

import com.jiuji.cn.model.TSendMessage;
import com.jiuji.cn.model.TUser;

public interface TSendMessageMapper {

	List<TSendMessage> select(TSendMessage tsendMessage);
	
	TSendMessage selectOnlyone(TSendMessage tsendMessage);

	void insert(TSendMessage tsendMessage); 
	
	void update(TSendMessage tsendMessage);
}
