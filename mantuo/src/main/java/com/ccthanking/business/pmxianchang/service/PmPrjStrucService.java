/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    pmxianchang.service.PmPrjStrucService.java
 * 创建日期： 2016-01-25 下午 04:12:17
 * 功能：    接口：项目结构
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-01-25 下午 04:12:17  卢红冈   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.pmxianchang.service;


import com.ccthanking.business.pmxianchang.vo.PmPrjStrucVO;

import com.ccthanking.framework.service.IBaseService;


/**
 * <p> PmPrjStrucService.java </p>
 * <p> 功能：项目结构 </p>
 *
 * <p><a href="PmPrjStrucService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:luhonggang@kzcpm.com ">卢红冈</a>
 * @version 0.1
 * @since 2016-01-25
 * 
 */
public interface PmPrjStrucService extends IBaseService<PmPrjStrucVO, String> {

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

}
