/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    pmjiancha.service.PmJianchaBzService.java
 * 创建日期： 2016-02-23 上午 09:54:56
 * 功能：    接口：项目检查内容
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-02-23 上午 09:54:56  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.pmjiancha.service;


import java.util.List;
import java.util.Map;

import com.ccthanking.business.pmjiancha.vo.PmJianchaBzVO;

import com.ccthanking.framework.service.IBaseService;


/**
 * <p> PmJianchaBzService.java </p>
 * <p> 功能：项目检查内容 </p>
 *
 * <p><a href="PmJianchaBzService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-02-23
 * 
 */
public interface PmJianchaBzService extends IBaseService<PmJianchaBzVO, String> {

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

    List<Map<String, String>> queryJcjb();

	String queryBz(String msg);

	boolean deleteById(String uid);

	List<Map<String, String>> queryWgnr(String guifan_uid);

}
