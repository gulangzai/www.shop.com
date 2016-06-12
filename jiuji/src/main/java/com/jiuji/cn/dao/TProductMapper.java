package com.jiuji.cn.dao;

import java.util.List;

import com.jiuji.cn.model.TCarousel;
import com.jiuji.cn.model.TClass;
import com.jiuji.cn.model.TProduct;
import com.jiuji.cn.model.TProductDto; 

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
	List<TProductDto> findNewGood();
	/**
	 * 获得热门的商品
	 * @return 
	 */
	List<TProductDto> findNewHot();
	/**
	 * 获得特卖商品
	 * @return 
	 */
	List<TProductDto> findNewSpecial();

	TProductDto queryById(TProduct tproduct);

	/**
	 * desc:根据类型获取商品
	 * @param tproduct
	 * @return
	 */
	List<TProductDto> queryByType(TProduct tproduct);
}
