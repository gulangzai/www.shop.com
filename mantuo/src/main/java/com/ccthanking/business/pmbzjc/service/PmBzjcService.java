/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    pmbzjc.service.PmBzjcService.java
 * 创建日期： 2016-05-11 上午 09:12:48
 * 功能：    接口：项目标准检查
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-05-11 上午 09:12:48  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.pmbzjc.service;


import java.util.Map;

import com.ccthanking.business.pmbzjc.vo.PmBzjcVO;

import com.ccthanking.framework.service.IBaseService;


/**
 * <p> PmBzjcService.java </p>
 * <p> 功能：项目标准检查 </p>
 *
 * <p><a href="PmBzjcService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-05-11
 * 
 */
public interface PmBzjcService extends IBaseService<PmBzjcVO, String> {

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

	String queryTreeCondition(String msg, Map xmmap)throws Exception;

	String queryNewContent(String bzjc_uid, PmBzjcVO vo);

	String delete(String pROJECT_UID, String bZJC_UID)throws Exception ;

}
