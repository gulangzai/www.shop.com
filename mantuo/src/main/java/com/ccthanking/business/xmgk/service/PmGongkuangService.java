/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    xmgk.service.PmGongkuangService.java
 * 创建日期： 2015-11-25 下午 01:16:52
 * 功能：    接口：项目工况
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-11-25 下午 01:16:52  luhonggng   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.xmgk.service;


import java.util.List;
import java.util.Map;

import com.ccthanking.business.xmgk.vo.PmGongkuangVO;

import com.ccthanking.framework.service.IBaseService;


/**
 * <p> PmGongkuangService.java </p>
 * <p> 功能：项目工况 </p>
 *
 * <p><a href="PmGongkuangService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:luhonggang@163.com">luhonggng</a>
 * @version 0.1
 * @since 2015-11-25
 * 
 */
public interface PmGongkuangService extends IBaseService<PmGongkuangVO, String> {

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

	boolean pmGongkuangSet(Map<String, String> map);

	List<Map<String, Object>> queryFileByType(Map<String, String> map);

	boolean deleteByid(String gongkuangUid);

}
