/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    jcgl.service.SysBzjcService.java
 * 创建日期： 2016-05-11 上午 11:34:33
 * 功能：    接口：标准检查模板
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-05-11 上午 11:34:33  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.jcgl.service;


import com.ccthanking.business.jcgl.vo.SysBzjcVO;

import com.ccthanking.framework.service.IBaseService;


/**
 * <p> SysBzjcService.java </p>
 * <p> 功能：标准检查模板 </p>
 *
 * <p><a href="SysBzjcService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@qq.com">曹伟杰</a>
 * @version 0.1
 * @since 2016-05-11
 * 
 */
public interface SysBzjcService extends IBaseService<SysBzjcVO, String> {

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
    
    String queryTree(String json) throws Exception;
    
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
