/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2016
 * 文件：   com.ccthanking.business.pmscslweekplan.PmScslWeekPlanDao.java
 * 创建日期： 2016-05-12 上午 10:02:05
 * 功能：   监测项目
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-05-12 上午 10:02:05  hushujie   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.pmscslweekplan.dao;

import java.util.Map;

import com.ccthanking.business.pmscslweekplan.vo.PmScslWeekPlanVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> PmScslWeekPlanDao.java </p>
 * <p> 功能接口：监测项目 </p>
 *
 * <p><a href="PmScslWeekPlanDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:">hushujie</a>
 * @version 0.1
 * @since 2016-05-12
 * 
 */

public interface PmScslWeekPlanDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, PmScslWeekPlanVO vo, Map map);
    public Boolean deleteById(String SCSLWEEKPLANUID);
    public String queryId(String SCSLWEEKPLANUID);
	// 可在此加入业务独特的服务接口
}
