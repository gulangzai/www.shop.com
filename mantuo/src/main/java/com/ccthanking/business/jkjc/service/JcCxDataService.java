/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    jkjc.service.JcCxDataService.java
 * 创建日期： 2015-12-01 上午 10:44:12
 * 功能：    接口：测斜数据
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-12-01 上午 10:44:12  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.jkjc.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ccthanking.business.jkjc.vo.JcCxDataVO;

import com.ccthanking.framework.service.IBaseService;
import com.github.abel533.echarts.Option;


/**
 * <p> JcCxDataService.java </p>
 * <p> 功能：测斜数据 </p>
 *
 * <p><a href="JcCxDataService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-12-01
 * 
 */
public interface JcCxDataService extends IBaseService<JcCxDataVO, String> {

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

    Map<String,Object>  queryTab(HashMap<String, String> map);

	Option queryLineChart(HashMap<String, Object> map);

	Option queryBarChart(HashMap<String, Object> map);

	Option queryLineChart2(HashMap<String, Object> map);

	Option queryBarChart2(HashMap<String, Object> map);

}
