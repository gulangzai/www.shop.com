/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2016
 * 文件：   com.ccthanking.business.pmjiancha.PmJianchaBzDao.java
 * 创建日期： 2016-02-23 上午 09:54:56
 * 功能：   项目检查内容
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-02-23 上午 09:54:56  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.pmjiancha.dao;

import java.util.List;
import java.util.Map;

import com.ccthanking.business.pmjiancha.vo.PmJianchaBzVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> PmJianchaBzDao.java </p>
 * <p> 功能接口：项目检查内容 </p>
 *
 * <p><a href="PmJianchaBzDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-02-23
 * 
 */

public interface PmJianchaBzDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, PmJianchaBzVO vo, Map map);

	public List<Map<String, String>> queryJcjb();
	
	public String getMaxCode();

	public String queryBz(String msg);

	public List<Map<String, String>> queryWgnr(String guifan_uid);

	// 可在此加入业务独特的服务接口
}
