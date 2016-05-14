/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2016
 * 文件：   com.ccthanking.business.zhenggai.PmZhengGaiContentDao.java
 * 创建日期： 2016-03-30 上午 10:35:43
 * 功能：   工程整改内容
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-03-30 上午 10:35:43  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.zhenggai.dao;

import java.util.List;
import java.util.Map;

import com.ccthanking.business.zhenggai.vo.PmZhengGaiContentVO;
import com.ccthanking.business.zhenggai.vo.PmZhengGaiVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> PmZhengGaiContentDao.java </p>
 * <p> 功能接口：工程整改内容 </p>
 *
 * <p><a href="PmZhengGaiContentDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-03-30
 * 
 */

public interface PmZhengGaiContentDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, PmZhengGaiContentVO vo, Map map);

	public String queryConditionContent(String zHENGGAI_UID, Object object,
			Object object2);

	public List queryContentList(String zhenggai_uid, Object object,
			Object object2);

	public String queryNewContent(String zhenggai_uid, PmZhengGaiVO object,
			Object object2);

	// 可在此加入业务独特的服务接口
}
