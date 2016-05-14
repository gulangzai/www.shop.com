/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2016
 * 文件：   com.ccthanking.business.projectaccept.PmYanShouDetailDao.java
 * 创建日期： 2016-03-28 上午 11:52:17
 * 功能：   工程验收
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-03-28 上午 11:52:17  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.projectaccept.dao;

import java.util.List;
import java.util.Map;

import com.ccthanking.business.projectaccept.vo.PmYanShouDetailVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> PmYanShouDetailDao.java </p>
 * <p> 功能接口：工程验收 </p>
 *
 * <p><a href="PmYanShouDetailDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-03-28
 * 
 */

public interface PmYanShouDetailDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, PmYanShouDetailVO vo, Map map);

	public List querySon(String json, Object object, Object object2,
			String yanshou_uid);

	// 可在此加入业务独特的服务接口
}
