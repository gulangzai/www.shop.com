/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    zhenggai.service.PmZhengGaiContentService.java
 * 创建日期： 2016-03-30 上午 10:35:43
 * 功能：    接口：工程整改内容
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-03-30 上午 10:35:43  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.zhenggai.service;


import java.util.List;

import com.ccthanking.business.zhenggai.vo.PmZhengGaiContentVO;
import com.ccthanking.business.zhenggai.vo.PmZhengGaiVO;

import com.ccthanking.framework.service.IBaseService;


/**
 * <p> PmZhengGaiContentService.java </p>
 * <p> 功能：工程整改内容 </p>
 *
 * <p><a href="PmZhengGaiContentService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-03-30
 * 
 */
public interface PmZhengGaiContentService extends IBaseService<PmZhengGaiContentVO, String> {

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

	String queryConditionContent(String msg);

	List queryContentList(String zhenggai_uid);

	String queryNewContent(String zhenggai_uid,PmZhengGaiVO vo);

}
