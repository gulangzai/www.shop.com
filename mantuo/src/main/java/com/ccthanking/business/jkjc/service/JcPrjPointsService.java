/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    jkjc.service.JcPrjPointsService.java
 * 创建日期： 2015-10-30 上午 09:48:59
 * 功能：    接口：项目监测点位
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-10-30 上午 09:48:59  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.jkjc.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ccthanking.business.jkjc.vo.JcPrjPointsVO;

import com.ccthanking.framework.service.IBaseService;


/**
 * <p> JcPrjPointsService.java </p>
 * <p> 功能：项目监测点位 </p>
 *
 * <p><a href="JcPrjPointsService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-10-30
 * 
 */
public interface JcPrjPointsService extends IBaseService<JcPrjPointsVO, String> {

	/**
     * 根据条件查询记录.
     * 
     * @param json
     * @param user
     * @return
     * @throws Exception
     * @since v1.00
     */
    String queryCondition(String json) throws Exception;
    
    /**
     * 新增记录.
     * 
     * @param json
     * @param user
     * @return
     * @throws Exception
     * @since v1.00
     */
    String insert(String json) throws Exception;

    /**
     * 修改记录.
     * 
     * @param json
     * @param user
     * @return
     * @throws Exception
     * @since v1.00
     */
    String update(String json) throws Exception;

    /**
     * 删除记录.
     * 
     * @param json
     * @param user
     * @return
     * @throws Exception
     * @since v1.00
     */
    String delete(String json) throws Exception;

	String queryPointForType(String msg,HashMap<String, String> map) throws Exception;

	List<?> queryList(Map<String, String> map);

	List<Map<String, String>> queryU3DElementUids(HashMap<String, String> map);

	List<Map<String, String>> queryIconById(String eleId) throws Exception;;

	List<Map<String, String>> queryPoints(HashMap<String, String> map);

	List<Map<String, String>> queryItems(HashMap<String, String> map);
	
	List<Map<String, String>> queryItemTypes(HashMap<String, String> map);
	
	List<Map<String, String>> queryPoint_Item(HashMap<String, String> map);

	List<Map<String, String>> queryPointProperty(HashMap<String, String> map);
	
	List<Map<String, String>> queryPointData_jc(HashMap<String, String> map);
	
	List<Map<String, String>> queryPointData_cx(HashMap<String, String> map);

	List<Map<String, String>> queryUser_Focus_Point(HashMap<String, String> map);
	
	String queryById(String pRJPOINTSUID);
	
	List<Map<String, String>> queryNewestDataByPointId_jc(HashMap<String, String> map);
	
	List<Map<String, String>> queryNewestDataByPointId_cx(HashMap<String, String> map);

	String insertFocus(String msg);

	boolean deleteFocus(String priPointsUid);
	
}
