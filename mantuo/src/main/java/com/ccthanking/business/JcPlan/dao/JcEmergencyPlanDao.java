/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2015
 * 文件：   com.ccthanking.business.JcPlan.JcEmergencyPlanDao.java
 * 创建日期： 2015-10-29 上午 10:46:26
 * 功能：   施工应急预案
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-10-29 上午 10:46:26  luhonggng   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.JcPlan.dao;

import java.util.Map;

import com.ccthanking.business.JcPlan.vo.JcEmergencyPlanVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> JcEmergencyPlanDao.java </p>
 * <p> 功能接口：施工应急预案 </p>
 *
 * <p><a href="JcEmergencyPlanDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:luhonggang@163.com">luhonggng</a>
 * @version 0.1
 * @since 2015-10-29
 * 
 */

public interface JcEmergencyPlanDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, JcEmergencyPlanVO vo, Map map);

	public String query(String msg, Object object, Object object2);

	// 可在此加入业务独特的服务接口
}
