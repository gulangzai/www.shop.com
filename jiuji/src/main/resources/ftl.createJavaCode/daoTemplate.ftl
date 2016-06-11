package com.jiuji.cn.business.spcontact.dao;

import java.util.List;

import com.jiuji.cn.business.spcontact.model.SpContact;
import com.jiuji.cn.model.TCarousel;
import com.jiuji.cn.model.TClass;
import com.jiuji.cn.model.TProduct; 


public interface SpContactMapper {
	
	List<TProduct> select(TProduct tProduct);
	
	TProduct selectOnlyone(TProduct tProduct);

	void insert(SpContact spContact); 
	
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
