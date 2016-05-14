/* ==================================================================
 * 版权：    kcit 版权所有 (c) 2013
 * 文件：    message.service.BuUserMessageService.java
 * 创建日期： 2016-04-21 上午 11:18:28
 * 功能：    接口：个人消息
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-04-21 上午 11:18:28  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package com.ccthanking.business.message.service;


import com.ccthanking.business.message.vo.BuUserMessageVO;

import com.ccthanking.framework.service.IBaseService;


/**
 * <p> BuUserMessageService.java </p>
 * <p> 功能：个人消息 </p>
 *
 * <p><a href="BuUserMessageService.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-04-21
 * 
 */
public interface BuUserMessageService extends IBaseService<BuUserMessageVO, String> {

	/**
     * 根据条件查询记录.
     * 
     * @param json
	 * @param user_uid 
     * @param user
     * @return
     * @throws Exception
     * @since v1.00
     */
    String queryCondition(String json, String user_uid) throws Exception;
    
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

	String queryByUserMessageId(String msg, String user_message_uid) throws Exception;

	void updateById(String user_message_uid,String status)throws Exception;

	void save(BuUserMessageVO buUserMessageVo);

	boolean updateMessageVO(BuUserMessageVO buUserMessageVo);

	int queryMessageCount(String idCard);

	void insertVo(String invite_uid,String content);

}
