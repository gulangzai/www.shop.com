package com.jiuji.cn.dao;

import java.util.List;

import com.jiuji.cn.model.TCarousel;
import com.jiuji.cn.model.TClass;
import com.jiuji.cn.model.TProduct; 

public interface TProductMapper {
	
	List<TProduct> select(TProduct tProduct);
	
	TProduct selectOnlyone(TProduct tProduct);

	void insert(TProduct tProduct); 
	
	void update(TProduct tProduct);

	List<TProduct> queryAll();

	/**
	 * 获得最新的商品
	 * @return 
	 */
	List<TProduct> findNewGood();
	/**
	 * 获得热门的商品
	 * @return 
	 */
	List<TProduct> findNewHot();
	/**
	 * 获得特卖商品
	 * @return 
	 */
	List<TProduct> findNewSpecial();

	TProduct queryById(TProduct tproduct);

	/**
	 * desc:根据类型获取商品
	 * @param tproduct
	 * @return
	 */
	List<TProduct> queryByType(TProduct tproduct);
}
