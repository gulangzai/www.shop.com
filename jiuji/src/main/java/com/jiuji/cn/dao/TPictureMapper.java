package com.jiuji.cn.dao;

import java.util.List;

import com.jiuji.cn.model.TCarousel;
import com.jiuji.cn.model.TClass;
import com.jiuji.cn.model.TPicture;
import com.jiuji.cn.model.TProduct;
import com.jiuji.cn.model.TProductDto; 

public interface TPictureMapper {
	
	List<TProduct> select(TProduct tProduct);
	
	TProduct selectOnlyone(TProduct tProduct);

	void insert(TProduct tProduct); 
	
	void update(TProduct tProduct);

	List<TProduct> queryAll();
 
	/**
	 * 获得特卖商品
	 * @return 
	 */
	List<TProduct> findNewSpecial();

	TProduct queryById(TProduct tproduct);

	List<TPicture> queryByProduct(TProductDto tproduct);
}
