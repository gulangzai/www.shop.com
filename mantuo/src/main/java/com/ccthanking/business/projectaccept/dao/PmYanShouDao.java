/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2016
 * 文件：   com.ccthanking.business.projectaccept.PmYanShouDao.java
 * 创建日期： 2016-03-28 上午 11:50:46
 * 功能：   工程验收
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-03-28 上午 11:50:46  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.projectaccept.dao;

import java.util.Map;

import com.ccthanking.business.projectaccept.vo.PmYanShouVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> PmYanShouDao.java </p>
 * <p> 功能接口：工程验收 </p>
 *
 * <p><a href="PmYanShouDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-03-28
 * 
 */

public interface PmYanShouDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, PmYanShouVO vo, Map map);

	public boolean deleteById(String yanshou_uid);

	// 可在此加入业务独特的服务接口
}
