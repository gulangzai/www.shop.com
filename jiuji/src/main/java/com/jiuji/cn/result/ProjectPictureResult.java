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

	public TProduct getTproduct() {
		return tproduct;
	}

	public void setTproduct(TProduct tproduct) {
		this.tproduct = tproduct;
	}

	public List<TPicture> gettPictures() {
		return tPictures;
	}

	public void settPictures(List<TPicture> tPictures) {
		this.tPictures = tPictures;
	}
	
	

}
