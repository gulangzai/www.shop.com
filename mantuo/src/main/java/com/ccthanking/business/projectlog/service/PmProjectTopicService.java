/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    projectlog.service.PmProjectTopicService.java
 * 创建日期： 2016-01-19 下午 04:30:30
 * 功能：    接口：问题讨论
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-01-19 下午 04:30:30  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.projectlog.service;


import java.util.List;
import java.util.Map;

import com.ccthanking.business.projectlog.vo.PmProjectTopicVO;

import com.ccthanking.framework.service.IBaseService;


/**
 * <p> PmProjectTopicService.java </p>
 * <p> 功能：问题讨论 </p>
 *
 * <p><a href="PmProjectTopicService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-01-19
 * 
 */
public interface PmProjectTopicService extends IBaseService<PmProjectTopicVO, String> {

	/**
     * 根据条件查询记录.
     * 
     * @param json
     * @param user
     * @return
     * @throws Exception
     * @since v1.00
     */
    String queryCondition(String json,String project_uid) throws Exception;
    
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

	List<Map<String, String>> queryList();

	Map<String, List<?>> queryDetails(String topicUid);

	boolean deleteById(String topicUid);

	boolean shutDownById(String topic_uid);

}
