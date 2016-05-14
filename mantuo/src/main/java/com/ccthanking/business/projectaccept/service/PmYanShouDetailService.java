/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    projectaccept.service.PmYanShouDetailService.java
 * 创建日期： 2016-03-28 上午 11:52:17
 * 功能：    接口：工程验收
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-03-28 上午 11:52:17  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.projectaccept.service;


import java.util.List;

import com.ccthanking.business.projectaccept.vo.PmYanShouDetailVO;
import com.ccthanking.framework.service.IBaseService; 

/**
 * <p> PmYanShouDetailService.java </p>
 * <p> 功能：工程验收 </p>
 *
 * <p><a href="PmYanShouDetailService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-03-28
 * 
 */
public interface PmYanShouDetailService extends IBaseService<PmYanShouDetailVO, String> {

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

	List querySon(String msg, String yanshou_uid);

	String batchUpdate(String msg)throws Exception;

}
