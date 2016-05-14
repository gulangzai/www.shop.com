/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2016
 * 文件：   com.ccthanking.business.projectdoc.PmProjectDocsDao.java
 * 创建日期： 2016-03-21 上午 09:32:52
 * 功能：   项目工程文档
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-03-21 上午 09:32:52  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.projectdoc.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ccthanking.business.projectdoc.vo.PmProjectDocsVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> PmProjectDocsDao.java </p>
 * <p> 功能接口：项目工程文档 </p>
 *
 * <p><a href="PmProjectDocsDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-03-21
 * 
 */

public interface PmProjectDocsDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, PmProjectDocsVO vo, Map map);

	public List<Map<String, String>> queryFileList(HashMap<String, String> map);

	public List<Map<String, String>> queryFiles(HashMap<String, String> map);

	public boolean deleteFiles(String doc_uids);

	// 可在此加入业务独特的服务接口
}
