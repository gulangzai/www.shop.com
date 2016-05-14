/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2016
 * 文件：   com.ccthanking.business.pmjiancha.PmXunjianDao.java
 * 创建日期： 2016-03-02 下午 02:20:13
 * 功能：   巡检
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-03-02 下午 02:20:13  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.pmjiancha.dao;

import java.util.Map;

import com.ccthanking.business.pmjiancha.vo.PmXunjianVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> PmXunjianDao.java </p>
 * <p> 功能接口：巡检 </p>
 *
 * <p><a href="PmXunjianDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-03-02
 * 
 */

public interface PmXunjianDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, PmXunjianVO vo, Map map);
	
	public String getMaxCode(String xunjianUid);

	// 可在此加入业务独特的服务接口
}
