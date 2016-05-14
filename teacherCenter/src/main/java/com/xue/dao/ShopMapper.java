package com.xue.dao;

import java.util.List;

import com.xue.dto.OpenCourseRecodeQuery;
import com.xue.dto.StudentAndShop;
import com.xue.model.Shop; 
import com.xue.vo.query.ShopQuery;

public interface ShopMapper {
    int deleteByPrimaryKey(String shopId);

    int insert(Shop record);

    int insertSelective(Shop record);

    Shop selectByPrimaryKey(String shopId);

    int updateByPrimaryKeySelective(Shop record);

    int updateByPrimaryKey(Shop record);
  
	List<Shop> findSubClassByDetailId(ShopQuery shopQuery);
  
	List<Shop> findByShopQuery(ShopQuery shopQuery);

	List<Shop> findByFreeShopQuery(ShopQuery shopQuery);

	List<Shop> findShopByQuery(ShopQuery shopQuery);

	//List<StudentAndShop> queryAllStudentShop(OpenCourseRecodeQuery openCourseRecode);

	int findShopByQueryCount(ShopQuery shopQuery);

	List<Shop> queryShopByStudentId(String studentId);
}