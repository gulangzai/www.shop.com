/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.pmjiancha.SysBzgfDao.java
 * 创建日期： 2016-02-25 下午 02:46:15
 * 功能：   标准和规范
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-02-25 下午 02:46:15  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.pmjiancha.dao.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Component;

import com.ccthanking.business.pmjiancha.dao.SysBzgfDao;
import com.ccthanking.business.pmjiancha.vo.SysBzgfVO;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.util.RequestUtil;
import com.copj.modules.utils.exception.DaoException;


/**
 * <p> SysBzgfDao.java </p>
 * <p> 功能：标准和规范 </p>
 *
 * <p><a href="SysBzgfDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-02-25
 * 
 */

@Component
public class SysBzgfDaoImpl  extends BsBaseDaoTJdbc implements SysBzgfDao {

    public String queryCondition(String json, SysBzgfVO vo, Map map){
    
    	User user = ActionContext.getCurrentUserInThread();
    
        Connection conn = DBUtil.getConnection();
        String domresult = "";
        try {

            // 组织查询条件
            PageManager page = RequestUtil.getPageManager(json);
            String condition = RequestUtil.getConditionList(json).getConditionWhere();
            String orderFilter = RequestUtil.getOrderFilter(json);
            condition += orderFilter;
            if (page == null)
                page = new PageManager();
            page.setFilter(condition);

            //String sql = "SELECT * FROM " + "SYS_BZGF t";
            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT t.BZGF_UID,t.P_BZGF_UID,");
            sql.append(" CASE WHEN  t.NODE_TYPE='FL' THEN '分类' ");
            sql.append(" WHEN  t.NODE_TYPE='SJ' THEN '事件' ");
            sql.append(" WHEN  t.NODE_TYPE='GF' THEN '规范' ");
            sql.append(" END AS NODE_TYPE_NAME,t.NODE_TYPE,NODE_CONTENT, ");
            sql.append(" t.UPDATE_DATE ");
            sql.append(" FROM sys_bzgf t  ");

            BaseResultSet bs = DBUtil.query(conn, sql.toString(), page);
            // 合同表
            // bs.setFieldTranslater("HTID", "合同表", "ID", "NAME");
            // 项目下达库
            // bs.setFieldTranslater("XDKID", "GC_TCJH_XMXDK", "ID", "XMMC");
            // 标段表
            // bs.setFieldTranslater("BDID", "GC_XMBD", "GC_XMBD_ID", "BDMC");

            // 设置字典

            // 设置查询条件
            // bs.setFieldDateFormat("JLRQ", "yyyy-MM");// 计量月份
            // bs.setFieldThousand("DYJLSDZ");

            domresult = bs.getJson();
            
        } catch (Exception e) {
            DaoException.handleMessageException("*********查询出错!*********");
        } finally {
            DBUtil.closeConnetion(conn);
        }
        return domresult;

    }

	public String creatTree(Map<String, String> map) {
		Connection conn = DBUtil.getConnection();
		//String querySql ="SELECT t.NODE_CONTENT,t.P_BZGF_UID,t.BZGF_UID,t.NODE_TYPE  FROM sys_bzgf t where t.NODE_TYPE ='"+map.get("NODE_TYPE")+"'  ORDER BY t.BZGF_UID desc";		
		String querySql ="SELECT t.NODE_CONTENT,t.P_BZGF_UID,t.BZGF_UID,t.NODE_TYPE  FROM sys_bzgf t where t.NODE_TYPE in('FL','GF') ORDER BY t.BZGF_UID desc";
		JSONArray jsonArr = new JSONArray();
		JSONObject rootJson = new JSONObject();
		rootJson.put("id", "");
		rootJson.put("pId", "0");
		rootJson.put("name", "标准规范");

		
		rootJson.put("open", "true");
		rootJson.put("icon", "../../../assets/img/cailfl/dir.png");
		jsonArr.add(rootJson);
		try {
			List<Map<String, String>> rsList = DBUtil.queryReturnList(conn,querySql);
			for (int i = 0; i < rsList.size(); i++) {
				Map<String, String> rsMap = rsList.get(i);
				JSONObject json = new JSONObject();
				json.put("id", rsMap.get("BZGF_UID"));
				json.put("pId", rsMap.get("P_BZGF_UID"));
				if("GF".equals(rsMap.get("NODE_TYPE"))){
					json.put("icon", "../../../assets/img/cailfl/dir.png");
				}else{
					json.put("icon", "../../../assets/img/cailfl/doc.png");
				}
				json.put("icon", "../../../assets/img/cailfl/doc.png");
				json.put("name", rsMap.get("NODE_CONTENT"));
				json.put("NODE_TYPE", rsMap.get("NODE_TYPE"));
				json.put("open", "true");
				jsonArr.add(json);		
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return jsonArr.toString();
	}
    
    // 在此可加入其它方法

}
