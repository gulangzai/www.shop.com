package com.jiuji.cn.service;

import java.util.List;

import com.jiuji.cn.model.TProduct;

public interface ProductService {

	TProduct queryById(String f_ProductId);

	List<TProduct> queryByClsId(String f_clsId);

}
