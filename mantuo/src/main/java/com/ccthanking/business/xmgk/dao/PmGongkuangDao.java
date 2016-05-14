/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2015
 * 文件：   com.ccthanking.business.xmgk.PmGongkuangDao.java
 * 创建日期： 2015-11-25 下午 01:16:52
 * 功能：   项目工况
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2015-11-25 下午 01:16:52  luhonggng   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.xmgk.dao;

import java.util.List;
import java.util.Map;

import com.ccthanking.business.xmgk.vo.PmGongkuangVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> PmGongkuangDao.java </p>
 * <p> 功能接口：项目工况 </p>
 *
 * <p><a href="PmGongkuangDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:luhonggang@163.com">luhonggng</a>
 * @version 0.1
 * @since 2015-11-25
 * 
 */

public interface PmGongkuangDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, PmGongkuangVO vo, Map map);

	public List<Map<String, String>> queryFileList(Map<String, String> map);

	public boolean pmGongkuangSet(Map<String, String> map);

	public List<Map<String, Object>> queryFileByType(Map<String, String> map);

	public boolean deleteByid(String gongkuangUid);

	// 可在此加入业务独特的服务接口
}
