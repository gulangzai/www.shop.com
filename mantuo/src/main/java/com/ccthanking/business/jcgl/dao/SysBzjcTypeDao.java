/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2016
 * 文件：   com.ccthanking.business.jcgl.SysBzjcTypeDao.java
 * 创建日期： 2016-05-11 上午 11:31:11
 * 功能：   标准检查模板类型
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-05-11 上午 11:31:11  曹伟杰   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.jcgl.dao;

import java.util.Map;

import com.ccthanking.business.jcgl.vo.SysBzjcTypeVO;
import com.ccthanking.framework.dao.BsBaseDaoable;


/**
 * <p> SysBzjcTypeDao.java </p>
 * <p> 功能接口：标准检查模板类型 </p>
 *
 * <p><a href="SysBzjcTypeDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:caochennihao1@qq.com">曹伟杰</a>
 * @version 0.1
 * @since 2016-05-11
 * 
 */

public interface SysBzjcTypeDao extends BsBaseDaoable {

	/**
	 * 条件查询.<br/>
	 * 
	 * @param json
	 * @param vo
	 * @param map
	 * @return string
	 * @since v1.00
	 */
	public String queryCondition(String json, SysBzjcTypeVO vo, Map map);

	// 可在此加入业务独特的服务接口
}
