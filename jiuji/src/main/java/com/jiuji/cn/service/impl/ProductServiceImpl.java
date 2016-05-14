package com.jiuji.cn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiuji.cn.dao.TProductMapper;
import com.jiuji.cn.model.TProduct;
import com.jiuji.cn.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	TProductMapper tproductMapper;
	
	@Override
	public TProduct queryById(String f_ProductId) {
		// TODO Auto-generated method stub
		TProduct tproduct = new TProduct(f_ProductId);
		return tproductMapper.queryById(tproduct);
	}

	@Override
	public List<TProduct> queryByClsId(String f_clsId) {
		// TODO Auto-generated method stub
		TProduct tproduct = new TProduct();
		tproduct.setFClsId(Integer.parseInt(f_clsId));
		return tproductMapper.queryByType(tproduct); 
	}

}
