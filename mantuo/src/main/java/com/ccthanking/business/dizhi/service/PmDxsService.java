/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    dizhi.service.PmDxsService.java
 * 创建日期： 2015-12-17 下午 02:19:59
 * 功能：    接口：项目
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-12-17 下午 02:19:59  卢红冈   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.dizhi.service;


import java.util.List;

import com.ccthanking.business.dizhi.vo.PmDxsVO;

import com.ccthanking.framework.service.IBaseService;


/**
 * <p> PmDxsService.java </p>
 * <p> 功能：项目 </p>
 *
 * <p><a href="PmDxsService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:luhonggnag@163.com">卢红冈</a>
 * @version 0.1
 * @since 2015-12-17
 * 
 */
public interface PmDxsService extends IBaseService<PmDxsVO, String> {

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

	List<?> queryDxsById(String dxsUid);

	String updateForSort(String dxsUid, String xuhao, String move);

}
