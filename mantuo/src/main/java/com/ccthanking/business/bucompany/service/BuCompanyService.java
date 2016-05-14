/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    bucompany.service.BuCompanyService.java
 * 创建日期： 2016-04-28 上午 11:14:13
 * 功能：    接口：监测项目
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-04-28 上午 11:14:13  hushujie   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.bucompany.service;


import com.ccthanking.business.bucompany.vo.BuCompanyVO;

import com.ccthanking.framework.service.IBaseService;


/**
 * <p> BuCompanyService.java </p>
 * <p> 功能：监测项目 </p>
 *
 * <p><a href="BuCompanyService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:">hushujie</a>
 * @version 0.1
 * @since 2016-04-28
 * 
 */
public interface BuCompanyService extends IBaseService<BuCompanyVO, String> {

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
    boolean deleteId(String companyuid);
    String queryid(String companyuid);
}
