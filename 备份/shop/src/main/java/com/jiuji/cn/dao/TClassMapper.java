package com.jiuji.cn.dao;

import java.util.List;
import java.util.Set;

import com.jiuji.cn.model.TCarousel;
import com.jiuji.cn.model.TClass; 

public interface TClassMapper {
	
	List<TClass> select(TClass tClass);
	
	TClass selectOnlyone(TClass tClass);

	void insert(TClass tClass); 
	
	void update(TClass tClass);

	List<TClass> queryAll();

	Set<TClass> queryAllson(TClass tClass);
}
