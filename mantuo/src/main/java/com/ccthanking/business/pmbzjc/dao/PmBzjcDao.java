/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2016
 * 文件：   com.ccthanking.business.pmbzjc.PmBzjcDao.java
 * 创建日期： 2016-05-11 上午 09:12:48
 * 功能：   项目标准检查
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-05-11 上午 09:12:48  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.pmbzjc.dao;

import java.util.List;
import java.util.Map;

import com.ccthanking.business.pmbzjc.vo.PmBzjcVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> PmBzjcDao.java </p>
 * <p> 功能接口：项目标准检查 </p>
 *
 * <p><a href="PmBzjcDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-05-11
 * 
 */

public interface PmBzjcDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, PmBzjcVO vo, Map map);

	public String queryTreeCondition(String json,Map xmmap);

	public String queryNewContent(String bzjc_uid, PmBzjcVO vo, Object object);

	// 可在此加入业务独特的服务接口
}
