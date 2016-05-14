/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2016
 * 文件：   com.ccthanking.business.message.BuUserMessageDao.java
 * 创建日期： 2016-04-21 上午 11:18:28
 * 功能：   个人消息
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-04-21 上午 11:18:28  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.message.dao;

import java.util.Map;

import com.ccthanking.business.message.vo.BuUserMessageVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> BuUserMessageDao.java </p>
 * <p> 功能接口：个人消息 </p>
 *
 * <p><a href="BuUserMessageDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-04-21
 * 
 */

public interface BuUserMessageDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, BuUserMessageVO vo, Map map);

	public String queryByUserMessageId(String json, BuUserMessageVO vo, Map map);

	public int queryMessageCount(String idCard);

	// 可在此加入业务独特的服务接口
}
