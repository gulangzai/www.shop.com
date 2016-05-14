package com.xue.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.xue.dto.OpenCourseQuery;
import com.xue.dto.OpenCourseRecodeQuery;
import com.xue.dto.OpenCourseRecodeResult;
import com.xue.dto.StudentAndShop;
import com.xue.model.MyCollect;
import com.xue.model.Shop; 
import com.xue.vo.query.ShopQuery;



public interface ShopService{
  
	/**
	 * @Description:根据专业ID,科目ID查询shop
	 * @param shopDetailId
	 * @return
	 * List<Shop>
	 * @exception:
	 * @author: Mr.Ruan
	 * @param subjectId 
	 * @time:2015年10月12日 下午2:03:07
	 */
	List<Shop> findSubClassByDetailId(String majorId, String subjectId);
	
	Shop selectByPrimaryKey(String id);

	List<Shop> findSubClassByDetailId(String majorId, String subjectId, String shopType);
	
	List<Shop> findByShopQuery(ShopQuery shopQuery);

	List<Shop> findShopByQuery(ShopQuery shopQuery);
  
	List<OpenCourseRecodeResult> getOpenCourseRecode(OpenCourseRecodeQuery openCourseRecode);

	int findShopByQueryCount(ShopQuery shopQuery);

	List<Shop> queryShopByStudentId(String studentId);
 
}
