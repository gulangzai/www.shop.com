/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    pmxianchang.service.PmXianchangService.java
 * 创建日期： 2016-01-22 上午 10:45:08
 * 功能：    接口：进度质量安全
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-01-22 上午 10:45:08  卢红冈   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.pmxianchang.service;


import java.util.List;
import java.util.Map;

import com.ccthanking.business.pmxianchang.vo.PmXianchangVO;
import com.ccthanking.framework.service.IBaseService;


/**
 * <p> PmXianchangService.java </p>
 * <p> 功能：进度质量安全 </p>
 *
 * <p><a href="PmXianchangService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:luhonggang@kzcpm.com ">卢红冈</a>
 * @version 0.1
 * @since 2016-01-22
 * 
 */
public interface PmXianchangService extends IBaseService<PmXianchangVO, String> {

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
     * @param xsUid 
     * @param user
     * @return
     * @throws Exception
     * @since v1.00
     */
    String delete(String json, String xsUid) throws Exception;

	Map<String, List<?>> queryXcQK(String xcUid, String fileType) throws Exception;

}
