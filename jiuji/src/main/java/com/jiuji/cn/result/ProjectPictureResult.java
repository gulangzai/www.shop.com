package com.jiuji.cn.result;

import java.util.List;

import com.jiuji.cn.model.TPicture;
import com.jiuji.cn.model.TProduct;

public class ProjectPictureResult {

	TProduct tproduct = null;
	List<TPicture> tPictures = null;
	
	public ProjectPictureResult(TProduct tproduct, List<TPicture> tPictures) {
		// TODO Auto-generated constructor stub
		this.tproduct = tproduct;
		this.tPictures = tPictures;
	}

}
