/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    invite.service.SysInviteService.java
 * 创建日期： 2016-04-25 下午 01:12:45
 * 功能：    接口：系统邀请
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-04-25 下午 01:12:45  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.invite.service;


import com.ccthanking.business.invite.vo.SysInviteVO;

import com.ccthanking.framework.service.IBaseService;


/**
 * <p> SysInviteService.java </p>
 * <p> 功能：系统邀请 </p>
 *
 * <p><a href="SysInviteService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-04-25
 * 
 */
public interface SysInviteService extends IBaseService<SysInviteVO, String> {

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

	boolean isOutDate(String userId);

	void updateByInviter_user(String userId);

	void updateVo(SysInviteVO sysInviteVo);

	SysInviteVO get(String invite_uid);
 
	void updateById(String invite_uid, String status);

	void updateStatusAndJoinDate(String invite_uid,String status);

	void updateStatus(String invite_uid, String status);

}
