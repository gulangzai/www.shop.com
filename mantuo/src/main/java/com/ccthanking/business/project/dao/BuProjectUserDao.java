/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2015
 * 文件：   com.ccthanking.business.project.BuProjectUserDao.java
 * 创建日期： 2015-10-20 下午 05:09:42
 * 功能：   项目用户
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-10-20 下午 05:09:42  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.project.dao;

import java.util.Map;

import com.ccthanking.business.project.vo.BuProjectUserVO;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> BuProjectUserDao.java </p>
 * <p> 功能接口：项目用户 </p>
 *
 * <p><a href="BuProjectUserDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2015-10-20
 * 
 */

public interface BuProjectUserDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, BuProjectUserVO vo, Map map);

	public Map<String,String> checkEmail(String email);

	public Map<String,String> checkTime(String uuid);

	public Map<String, String> userConfirm(String uuid);

	public String queryList(String msg);

	public boolean getByCondition(BuProjectUserVO vo);

	// 可在此加入业务独特的服务接口
}
