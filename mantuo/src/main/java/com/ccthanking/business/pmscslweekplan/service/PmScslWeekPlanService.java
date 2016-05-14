/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    pmscslweekplan.service.PmScslWeekPlanService.java
 * 创建日期： 2016-05-12 上午 10:02:05
 * 功能：    接口：监测项目
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-05-12 上午 10:02:05  hushujie   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.pmscslweekplan.service;


import com.ccthanking.business.pmscslweekplan.vo.PmScslWeekPlanVO;

import com.ccthanking.framework.service.IBaseService;


/**
 * <p> PmScslWeekPlanService.java </p>
 * <p> 功能：监测项目 </p>
 *
 * <p><a href="PmScslWeekPlanService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:">hushujie</a>
 * @version 0.1
 * @since 2016-05-12
 * 
 */
public interface PmScslWeekPlanService extends IBaseService<PmScslWeekPlanVO, String> {

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
   
    Boolean deleteById(String SCSLWEEKPLANUID);
  
    String queryId(String SCSLWEEKPLANUID);
}
