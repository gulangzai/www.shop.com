/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2016
 * 文件：   com.ccthanking.business.loguserread.LogUserReadDao.java
 * 创建日期： 2016-04-25 下午 05:34:16
 * 功能：   监测项目
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-04-25 下午 05:34:16  hushujie   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.loguserread.dao;

import java.util.List;
import java.util.Map;

import com.ccthanking.business.loguserread.vo.LogUserReadVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> LogUserReadDao.java </p>
 * <p> 功能接口：监测项目 </p>
 *
 * <p><a href="LogUserReadDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:">hushujie</a>
 * @version 0.1
 * @since 2016-04-25
 * 
 */

public interface LogUserReadDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, LogUserReadVO vo, Map map);
    public String query(String project_uid,String user_uid,String target_uid,String target_code);
	// 可在此加入业务独特的服务接口
}
