package com.jiuji.cn.service;

import java.util.List;

import com.jiuji.cn.model.TPicture;
import com.jiuji.cn.model.TProduct;
import com.jiuji.cn.model.TProductDto;

public interface PictureService {

	List<TPicture> queryByProduct(TProductDto tproduct);

}
