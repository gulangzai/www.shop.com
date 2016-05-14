/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    project.service.BuProjectService.java
 * 创建日期： 2015-10-17 上午 08:22:57
 * 功能：    接口：项目
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-10-17 上午 08:22:57  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.project.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.ccthanking.business.project.vo.BuProjectVO;

import com.ccthanking.framework.service.IBaseService;


/**
 * <p> BuProjectService.java </p>
 * <p> 功能：项目 </p>
 *
 * <p><a href="BuProjectService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-10-17
 * 
 */
public interface BuProjectService extends IBaseService<BuProjectVO, String> {

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

	String queryProject(String projectUid);

	List<?> queryProjectList(String userUid,String projectName);

	List<?> queryProjectList(String projectUid);

	String deleteFile(String target_uid, String target_table,
			String file_type);

	List<?> queryDetailById(String projectUid);

	List<?> queryCity(String msg);

	List<?> queryCityorControyBycode(String code);

	List<?> queryWeather(String projectUid);

	String queryGKdetail(String projectUid);

	String queryDataJSON(String msg);

	Map<String, Object> queryMyCare(HashMap<String, Object> map);

	Map<String, Object> queryWarning(HashMap<String, Object> map);

	Map<String, Object> queryAllPoint(HashMap<String, Object> map);

	List<?> queryCityName(String code);
	
	String queryid(String projectuid);

}
