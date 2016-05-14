/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2016
 * 文件：   com.ccthanking.business.invite.SysInviteDao.java
 * 创建日期： 2016-04-25 下午 01:12:45
 * 功能：   系统邀请
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-04-25 下午 01:12:45  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.invite.dao;

import java.util.Map;

import com.ccthanking.business.invite.vo.SysInviteVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> SysInviteDao.java </p>
 * <p> 功能接口：系统邀请 </p>
 *
 * <p><a href="SysInviteDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-04-25
 * 
 */

public interface SysInviteDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, SysInviteVO vo, Map map);

	// 可在此加入业务独特的服务接口
}
