/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2016
 * 文件：   com.ccthanking.business.projectlog.PmProjectLogDao.java
 * 创建日期： 2016-01-14 下午 02:44:26
 * 功能：   项目日志
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-01-14 下午 02:44:26  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.projectlog.dao;

import java.util.Map;

import com.ccthanking.business.projectlog.vo.PmProjectLogVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> PmProjectLogDao.java </p>
 * <p> 功能接口：项目日志 </p>
 *
 * <p><a href="PmProjectLogDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-01-14
 * 
 */

public interface PmProjectLogDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, PmProjectLogVO vo, Map map);

	public boolean deleteById(String logUid);

	// 可在此加入业务独特的服务接口
}
