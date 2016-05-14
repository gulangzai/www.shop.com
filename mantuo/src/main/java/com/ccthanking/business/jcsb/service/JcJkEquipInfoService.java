/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    jcsb.service.JcJkEquipInfoService.java
 * 创建日期： 2015-10-27 下午 03:52:34
 * 功能：    接口：项目
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-10-27 下午 03:52:34  luhonggang   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.jcsb.service;


import com.ccthanking.business.jcsb.vo.JcJkEquipInfoVO;

import com.ccthanking.framework.service.IBaseService;


/**
 * <p> JcJkEquipInfoService.java </p>
 * <p> 功能：项目 </p>
 *
 * <p><a href="JcJkEquipInfoService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:luhongang@163.com">luhonggang</a>
 * @version 0.1
 * @since 2015-10-27
 * 
 */
public interface JcJkEquipInfoService extends IBaseService<JcJkEquipInfoVO, String> {

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

	String query(String msg) throws Exception;

}
