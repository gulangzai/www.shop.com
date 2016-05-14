/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    commons.service.SysUserService.java
 * 创建日期： 2015-10-26 下午 10:31:55
 * 功能：    接口：用户信息
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-10-26 下午 10:31:55  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.commons.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ccthanking.business.commons.vo.SysUserVO;

import com.ccthanking.framework.model.requestJson;
import com.ccthanking.framework.service.IBaseService;


/**
 * <p> SysUserService.java </p>
 * <p> 功能：用户信息 </p>
 *
 * <p><a href="SysUserService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-10-26
 * 
 */
public interface SysUserService extends IBaseService<SysUserVO, String> {

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

	boolean checkEmail(String param, String userId) throws Exception;

	boolean checkName(String name, String userId) throws Exception;

	boolean checkPwd(String pwd, String userId) throws Exception;

	boolean queryAuthority(HashMap<String, String> map);

	boolean checkDirAuthority(HashMap<String, String> map);

	List<Map<String, String>> getSysUserByLoginName(String loginName);

	//重置密码
	requestJson resetPassword(String msg);

}
