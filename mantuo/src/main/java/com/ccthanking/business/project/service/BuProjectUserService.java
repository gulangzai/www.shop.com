/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    project.service.BuProjectUserService.java
 * 创建日期： 2015-10-20 下午 05:09:42
 * 功能：    接口：项目用户
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-10-20 下午 05:09:42  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.project.service;


import java.util.List;
import java.util.Map;

import com.ccthanking.business.project.vo.BuProjectRoleVO;
import com.ccthanking.business.project.vo.BuProjectUserVO;

import com.ccthanking.framework.model.requestJson;
import com.ccthanking.framework.service.IBaseService;


/**
 * <p> BuProjectUserService.java </p>
 * <p> 功能：项目用户 </p>
 *
 * <p><a href="BuProjectUserService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-10-20
 * 
 */
public interface BuProjectUserService extends IBaseService<BuProjectUserVO, String> {

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
    
    
    Map<String,String> insertInvite(Map<String,String> map) throws Exception;

    Map<String,String> checkEmail(String email);

	Map<String,String> checkTime(String uuid);

	Map<String,String> userConfirm(String uuid);

	String queryList(String msg);

	requestJson save(String userId, String project_uid, String project_role_uid, String user_message_uid);

	List<Map<String, String>> getRoleByProject(String project_uid);

}
