/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    zhenggai.service.PmZhengGaiService.java
 * 创建日期： 2016-03-30 上午 10:31:40
 * 功能：    接口：工程整改
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-03-30 上午 10:31:40  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.zhenggai.service;


import com.ccthanking.business.zhenggai.vo.PmZhengGaiVO;

import com.ccthanking.framework.service.IBaseService;


/**
 * <p> PmZhengGaiService.java </p>
 * <p> 功能：工程整改 </p>
 *
 * <p><a href="PmZhengGaiService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-03-30
 * 
 */
public interface PmZhengGaiService extends IBaseService<PmZhengGaiVO, String> {

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
     * @param zHENGGAI_UID 
     * @param user
     * @return
     * @throws Exception
     * @since v1.00
     */
    String delete(String json, String zHENGGAI_UID) throws Exception;

}
