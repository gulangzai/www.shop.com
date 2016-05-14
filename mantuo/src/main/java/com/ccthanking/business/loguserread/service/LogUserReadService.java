/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    loguserread.service.LogUserReadService.java
 * 创建日期： 2016-04-25 下午 05:34:16
 * 功能：    接口：监测项目
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-04-25 下午 05:34:16  hushujie   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.loguserread.service;


import java.util.List;
import java.util.Map;

import com.ccthanking.business.loguserread.vo.LogUserReadVO;

import com.ccthanking.framework.service.IBaseService;


/**
 * <p> LogUserReadService.java </p>
 * <p> 功能：监测项目 </p>
 *
 * <p><a href="LogUserReadService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:">hushujie</a>
 * @version 0.1
 * @since 2016-04-25
 * 
 */
public interface LogUserReadService extends IBaseService<LogUserReadVO, String> {

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
    String insert(String json,String session) throws Exception;

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
   
    String query(String project_uid,String user_uid,String target_uid,String target_code) throws Exception;
}
