/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    pmcailiao.service.PmCailiaoService.java
 * 创建日期： 2016-03-24 上午 11:19:37
 * 功能：    接口：监测项目
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-03-24 上午 11:19:37     创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.pmcailiao.service;


import java.util.List;
import java.util.Map;

import com.ccthanking.business.pmcailiao.vo.PmCailiaoVO;

import com.ccthanking.framework.service.IBaseService;


/**
 * <p> PmCailiaoService.java </p>
 * <p> 功能：监测项目 </p>
 *
 * <p><a href="PmCailiaoService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com"></a>
 * @version 0.1
 * @since 2016-03-24
 * 
 */
public interface PmCailiaoService extends IBaseService<PmCailiaoVO, String> {

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
    List<Map<String, String>> queryFileList(Map<String, String> map);
    boolean deleteId(String cailiaoUid);
}
