/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2016
 * 文件：   com.ccthanking.business.zhenggai.PmZgDafuDao.java
 * 创建日期： 2016-03-30 上午 10:40:44
 * 功能：   工程整改答复
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-03-30 上午 10:40:44  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.zhenggai.dao;

import java.util.Map;

import com.ccthanking.business.zhenggai.vo.PmZgDafuVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> PmZgDafuDao.java </p>
 * <p> 功能接口：工程整改答复 </p>
 *
 * <p><a href="PmZgDafuDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-03-30
 * 
 */

public interface PmZgDafuDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, PmZgDafuVO vo, Map map);

	// 可在此加入业务独特的服务接口
}
