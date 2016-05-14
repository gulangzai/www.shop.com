/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    JcPerson.service.JcPersonInfoService.java
 * 创建日期： 2015-10-29 上午 10:54:59
 * 功能：    接口：负责人联络方式
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-10-29 上午 10:54:59  luhonggng   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.JcPerson.service;


import com.ccthanking.business.JcPerson.vo.JcPersonInfoVO;

import com.ccthanking.framework.service.IBaseService;


/**
 * <p> JcPersonInfoService.java </p>
 * <p> 功能：负责人联络方式 </p>
 *
 * <p><a href="JcPersonInfoService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:luhonggang@163.com">luhonggng</a>
 * @version 0.1
 * @since 2015-10-29
 * 
 */
public interface JcPersonInfoService extends IBaseService<JcPersonInfoVO, String> {

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

	String query(String msg) throws Exception;

}
