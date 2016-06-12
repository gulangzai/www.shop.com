package com.jiuji.cn.service;

import java.util.List;

import com.jiuji.cn.model.TProduct;
import com.jiuji.cn.model.TProductDto;

public interface ProductService {

	TProductDto queryById(String f_ProductId);

	List<TProductDto> queryByClsId(String f_clsId);

}
