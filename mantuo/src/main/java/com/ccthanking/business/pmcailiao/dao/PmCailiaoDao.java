/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2016
 * 文件：   com.ccthanking.business.pmcailiao.PmCailiaoDao.java
 * 创建日期： 2016-03-24 上午 11:19:37
 * 功能：   监测项目
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-03-24 上午 11:19:37     创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.pmcailiao.dao;

import java.util.List;
import java.util.Map;

import com.ccthanking.business.pmcailiao.vo.PmCailiaoVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> PmCailiaoDao.java </p>
 * <p> 功能接口：监测项目 </p>
 *
 * <p><a href="PmCailiaoDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com"></a>
 * @version 0.1
 * @since 2016-03-24
 * 
 */

public interface PmCailiaoDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, PmCailiaoVO vo, Map map);

	public  Boolean deleteId(String cailiaoUid);
	
	public List<Map<String, String>> queryFileList(Map<String, String> map);
	// 可在此加入业务独特的服务接口
}
