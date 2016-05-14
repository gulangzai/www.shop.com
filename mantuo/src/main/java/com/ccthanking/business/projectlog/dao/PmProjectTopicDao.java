/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2016
 * 文件：   com.ccthanking.business.projectlog.PmProjectTopicDao.java
 * 创建日期： 2016-01-19 下午 04:30:30
 * 功能：   问题讨论
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-01-19 下午 04:30:30  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.projectlog.dao;

import java.util.List;
import java.util.Map;

import com.ccthanking.business.projectlog.vo.PmProjectTopicVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> PmProjectTopicDao.java </p>
 * <p> 功能接口：问题讨论 </p>
 *
 * <p><a href="PmProjectTopicDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-01-19
 * 
 */

public interface PmProjectTopicDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, PmProjectTopicVO vo, Map map);

	public List<Map<String, String>> queryList();

	public Map<String, List<?>> queryDetails(String topicUid);

	public boolean deleteById(String topicUid);

	public boolean shutDownById(String topic_uid);

	// 可在此加入业务独特的服务接口
}
