/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
 * 文件：   com.ccthanking.business.pmjiancha.PmBzgfDao.java
 * 创建日期： 2016-02-23 上午 10:00:46
 * 功能：   项目标准规范
 * 所含类:   {包含的类}
 * 修改记录：

 * 日期                        作者                内容
 * ==================================================================
 * 2016-02-23 上午 10:00:46  龚伟雄   创建文件，实现基本功能
 *
 * ==================================================================
 */
package  com.ccthanking.business.pmjiancha.dao.impl;

import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Component;

import com.ccthanking.business.pmjiancha.dao.PmBzgfDao;
import com.ccthanking.business.pmjiancha.vo.PmBzgfVO;
import com.ccthanking.framework.base.BaseDAO;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.util.RequestUtil;
import com.copj.modules.utils.exception.DaoException;


/**
 * <p> PmBzgfDao.java </p>
 * <p> 功能：项目标准规范 </p>
 *
 * <p><a href="PmBzgfDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-02-23
 * 
 */

@Component
public class PmBzgfDaoImpl  extends BsBaseDaoTJdbc implements PmBzgfDao {

    public String queryCondition(String json, PmBzgfVO vo, Map map){
    
    	
    
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

            StringBuffer sql = new StringBuffer();
            sql.append(" SELECT t.BZGF_UID,t.P_BZGF_UID,t.PROJECT_UID,t.COMPONENT_TYPE_UID, ");
            sql.append(" t.BZ_LEVEL, t.WEIGUI_LEVEL,");
            sql.append(" CASE WHEN  t.NODE_TYPE='FL' THEN '分类' ");
            sql.append(" WHEN  t.NODE_TYPE='SJ' THEN '事件' ");
            sql.append(" WHEN  t.NODE_TYPE='GF' THEN '规范' ");
            sql.append(" END AS NODE_TYPE_NAME,t.NODE_TYPE,NODE_CONTENT, ");
            sql.append(" t.UPDATE_DATE,u.USER_NAME  ");
            sql.append(" FROM pm_bzgf t  ");
            sql.append(" LEFT JOIN sys_user u  ");
            sql.append(" ON t.UPDATE_USER = u.USER_UID  ");

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
		//String querySql ="SELECT t.NODE_CONTENT,t.P_BZGF_UID,t.BZGF_UID,t.NODE_TYPE  FROM pm_bzgf t where t.NODE_TYPE ='"+map.get("NODE_TYPE")+"'  ORDER BY t.BZGF_UID desc";		
		String querySql ="SELECT t.NODE_CONTENT,t.P_BZGF_UID,t.BZGF_UID,t.NODE_TYPE  FROM pm_bzgf t where t.NODE_TYPE in('FL','GF') and project_uid='"+map.get("project_uid")+"' ORDER BY t.BZGF_UID desc";		
		JSONArray jsonArr = new JSONArray();
		JSONObject rootJson = new JSONObject();
		rootJson.put("id", "");
		rootJson.put("pId", "0");
		//if("FL".equals(map.get("NODE_TYPE"))){
			rootJson.put("name", "标准规范");
		//}else if("SJ".equals(map.get("NODE_TYPE"))){
		//	rootJson.put("name", "事件");
		//}else if("GF".equals(map.get("NODE_TYPE"))){
		//	rootJson.put("name", "规范");
		//}
		
		rootJson.put("open", "true");
		rootJson.put("icon", "assets/img/cailfl/dir.png");
		jsonArr.add(rootJson);
		try {
			List<Map<String, String>> rsList = DBUtil.queryReturnList(conn,querySql);
			for (int i = 0; i < rsList.size(); i++) {
				Map<String, String> rsMap = rsList.get(i);
				JSONObject json = new JSONObject();
				json.put("id", rsMap.get("BZGF_UID"));
				json.put("pId", rsMap.get("P_BZGF_UID"));
				if("GF".equals(rsMap.get("NODE_TYPE"))){
					json.put("icon", "assets/img/cailfl/dir.png");
				}else{
					json.put("icon", "assets/img/cailfl/doc.png");
				}
				
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

	public boolean importGF(Map<String, String> map) {
		User user = ActionContext.getCurrentUserInThread();
		Connection conn = DBUtil.getConnection();
		boolean flag = false;
		
		try {
			String gfuid = map.get("BZGF_UID");
			String[] gfuids = gfuid.split(",");
			for (int i = 0; i < gfuids.length; i++) {
				StringBuffer sql = new StringBuffer();
				sql.append(" SELECT * FROM sys_bzgf  ");
				sql.append(" WHERE BZGF_UID = '"+gfuids[i]+"'  ");
				sql.append(" OR P_BZGF_UID  ");
				sql.append(" IN(SELECT BZGF_UID FROM sys_bzgf  ");
				sql.append(" WHERE BZGF_UID = '"+gfuids[i]+"'  ");
				sql.append(" OR P_BZGF_UID = '"+gfuids[i]+"') ");
				
				List<Map<String, String>> list = DBUtil.queryReturnList(conn, sql.toString());
				String bzgf_uid = "";
				String p_bzgf_uid = "";
				for (Map<String, String> map2 : list) {
					PmBzgfVO vo = new PmBzgfVO();

					if("GF".equals(map2.get("NODE_TYPE"))){
						vo.setProject_uid(map.get("PROJECT_UID"));
						vo.setNode_type(map2.get("NODE_TYPE"));
						vo.setNode_content(map2.get("NODE_CONTENT"));
						vo.setBz_level("GJ");
						vo.setCreate_date(new Date());
						vo.setCreate_user(user.getIdCard());
						vo.setUpdate_date(new Date());
						vo.setUpdate_user(user.getIdCard());
						p_bzgf_uid = String.valueOf(BaseDAO.insert(conn, vo));
					}else if("FL".equals(map2.get("NODE_TYPE"))){
						vo.setP_bzgf_uid(p_bzgf_uid);
						vo.setProject_uid(map.get("PROJECT_UID"));
						vo.setNode_type(map2.get("NODE_TYPE"));
						vo.setNode_content(map2.get("NODE_CONTENT"));
						vo.setBz_level("GJ");
						vo.setCreate_date(new Date());
						vo.setCreate_user(user.getIdCard());
						vo.setUpdate_date(new Date());
						vo.setUpdate_user(user.getIdCard());
						bzgf_uid = String.valueOf(BaseDAO.insert(conn, vo));
					}else{
						vo.setP_bzgf_uid(bzgf_uid);
						vo.setProject_uid(map.get("PROJECT_UID"));
						vo.setNode_type(map2.get("NODE_TYPE"));
						vo.setNode_content(map2.get("NODE_CONTENT"));
						vo.setBz_level("GJ");
						vo.setCreate_date(new Date());
						vo.setCreate_user(user.getIdCard());
						vo.setUpdate_date(new Date());
						vo.setUpdate_user(user.getIdCard());
						BaseDAO.insert(conn, vo);
					}
					

					
				}

			}
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return flag;
	}

	public String creatTree2(Map<String, String> map) {
		Connection conn = DBUtil.getConnection();
		//String querySql ="SELECT t.NODE_CONTENT,t.P_BZGF_UID,t.BZGF_UID,t.NODE_TYPE  FROM pm_bzgf t where t.NODE_TYPE ='"+map.get("NODE_TYPE")+"'  ORDER BY t.BZGF_UID desc";		
		String querySql ="SELECT t.NODE_CONTENT,t.P_BZGF_UID,t.BZGF_UID,t.NODE_TYPE  FROM pm_bzgf t where t.NODE_TYPE in('FL','GF') and project_uid = '"+map.get("project_uid")+"' ORDER BY t.BZGF_UID desc";
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
