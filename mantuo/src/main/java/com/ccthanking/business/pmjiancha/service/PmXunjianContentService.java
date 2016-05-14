/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    pmjiancha.service.PmXunjianContentService.java
 * 创建日期： 2016-03-02 下午 02:21:23
 * 功能：    接口：巡检检查内容
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-03-02 下午 02:21:23  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.pmjiancha.service;


import java.util.List;
import java.util.Map;

import com.ccthanking.business.pmjiancha.vo.PmXunjianContentVO;

import com.ccthanking.framework.service.IBaseService;


/**
 * <p> PmXunjianContentService.java </p>
 * <p> 功能：巡检检查内容 </p>
 *
 * <p><a href="PmXunjianContentService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-03-02
 * 
 */
public interface PmXunjianContentService extends IBaseService<PmXunjianContentVO, String> {

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

	List<Map<String, String>> queryByXunjianUid(String xunjianUid);

}
