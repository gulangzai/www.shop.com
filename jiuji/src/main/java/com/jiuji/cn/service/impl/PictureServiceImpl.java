package com.jiuji.cn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiuji.cn.dao.TPictureMapper;
import com.jiuji.cn.model.TPicture;
import com.jiuji.cn.model.TProduct;
import com.jiuji.cn.model.TProductDto;
import com.jiuji.cn.service.PictureService;
@Service
public class PictureServiceImpl implements PictureService {

	@Autowired
	TPictureMapper tPictureMapper;
	@Override
	public List<TPicture> queryByProduct(TProductDto tproduct) {
		// TODO Auto-generated method stub
		return tPictureMapper.queryByProduct(tproduct);
	}

}
