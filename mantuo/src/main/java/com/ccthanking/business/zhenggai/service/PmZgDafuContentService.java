/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    zhenggai.service.PmZgDafuContentService.java
 * 创建日期： 2016-03-30 上午 10:43:36
 * 功能：    接口：工程整改答复内容
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-03-30 上午 10:43:36  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.zhenggai.service;


import java.util.List;

import com.ccthanking.business.zhenggai.vo.PmZgDafuContentVO;

import com.ccthanking.framework.service.IBaseService;


/**
 * <p> PmZgDafuContentService.java </p>
 * <p> 功能：工程整改答复内容 </p>
 *
 * <p><a href="PmZgDafuContentService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-03-30
 * 
 */
public interface PmZgDafuContentService extends IBaseService<PmZgDafuContentVO, String> {

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

	List querySon(String msg,String ZG_DAFU_UID) throws Exception;

}
