/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    jkjc.service.JcPointPropertyService.java
 * 创建日期： 2015-10-30 上午 09:38:07
 * 功能：    接口：监测点属性值
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-10-30 上午 09:38:07  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.jkjc.service;


import java.util.HashMap;

import com.ccthanking.business.jkjc.vo.JcPointPropertyVO;

import com.ccthanking.framework.service.IBaseService;


/**
 * <p> JcPointPropertyService.java </p>
 * <p> 功能：监测点属性值 </p>
 *
 * <p><a href="JcPointPropertyService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-10-30
 * 
 */
public interface JcPointPropertyService extends IBaseService<JcPointPropertyVO, String> {

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
    String update(HashMap<String, String> map) throws Exception;

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

	String querybyTypeId(String itemId,String typeId) throws Exception;

	String querybyPoint_Item_Id(String pointitemId) throws Exception;
	
	String queryTypenameByTypeid(String typeId) throws Exception;
	
}
