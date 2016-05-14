package com.jiuji.cn.dao;

import java.util.List;

import com.jiuji.cn.model.TCarousel; 

public interface TCarouselMapper {
	
	 List<TCarousel> select(TCarousel tCarousel);
	
	TCarousel selectOnlyone(TCarousel tCarousel);

	void insert(TCarousel tCarousel); 
	
	void update(TCarousel tCarousel);

	List<TCarousel> queryAll();
}
