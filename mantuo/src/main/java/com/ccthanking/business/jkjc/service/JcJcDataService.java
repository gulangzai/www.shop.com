/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    jkjc.service.JcJcDataService.java
 * 创建日期： 2015-10-30 上午 09:30:58
 * 功能：    接口：监测数据
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-10-30 上午 09:30:58  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.jkjc.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.ccthanking.business.jkjc.vo.JcJcDataVO;

import com.ccthanking.framework.service.IBaseService;
import com.github.abel533.echarts.Option;


/**
 * <p> JcJcDataService.java </p>
 * <p> 功能：监测数据 </p>
 *
 * <p><a href="JcJcDataService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-10-30
 * 
 */
public interface JcJcDataService extends IBaseService<JcJcDataVO, String> {

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

    Map<String,Object> queryLineChart(HashMap<String, Object> map);

	Map<String,Object> queryBarChart(HashMap<String, Object> map);

	Map<String,Object> queryTab(HashMap<String, Object> map);

}
