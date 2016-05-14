/* ==================================================================
 * 版权：   kcit 版权所有 (c) 2013
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
package  com.ccthanking.business.projectdoc.dao.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Component;

import com.ccthanking.business.projectdoc.dao.PmProjectDocsDao;
import com.ccthanking.business.projectdoc.vo.PmProjectDocsVO;
import com.ccthanking.framework.common.BaseResultSet;
import com.ccthanking.framework.common.DBUtil;
import com.ccthanking.framework.common.PageManager;
import com.ccthanking.framework.common.User;
import com.ccthanking.framework.dao.impl.BsBaseDaoTJdbc;
import com.ccthanking.framework.handle.ActionContext;
import com.ccthanking.framework.util.RequestUtil;
import com.copj.modules.utils.exception.DaoException;


/**
 * <p> PmProjectDocsDao.java </p>
 * <p> 功能：项目工程文档 </p>
 *
 * <p><a href="PmProjectDocsDao.java.html"><i>查看源代码</i></a></p>  
 *
 * @author <a href="mailto:gongwx_java@163.com">龚伟雄</a>
 * @version 0.1
 * @since 2016-03-21
 * 
 */

@Component
public class PmProjectDocsDaoImpl  extends BsBaseDaoTJdbc implements PmProjectDocsDao {

    public String queryCondition(String json, PmProjectDocsVO vo, Map map){
    
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

            String sql = "SELECT * FROM " + "PM_PROJECT_DOCS t";
            BaseResultSet bs = DBUtil.query(conn, sql, page);
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

	public List<Map<String, String>> queryFileList(HashMap<String, String> map) {
		Connection conn = DBUtil.getConnection();
		StringBuffer sql = new StringBuffer();
		List<Map<String, String>> rsList = new ArrayList<Map<String,String>>();
		try {
/*			sql.append(" SELECT t.PROJECT_DOCS_UID,t.P_DOCS_UID,  ");
			sql.append(" t.NODE_NAME,t.NODE_TYPE,date_format(t.UPDATE_DATE,'%y-%m-%d %H:%i') as UPDATE_DATE ");
			sql.append(" FROM pm_project_docs t  ");
			sql.append(" WHERE t.PROJECT_UID = '"+map.get("project_uid")+"'  ");
			if("".equals(map.get("P_DOCS_UID"))){
				sql.append(" AND t.P_DOCS_UID is null");
			}else{
				sql.append(" AND t.P_DOCS_UID = '"+map.get("P_DOCS_UID")+"' ");
			}*/
			
			
			sql.append(" SELECT * FROM ( ");
			sql.append(" SELECT d.PROJECT_DOCS_UID,d.P_DOCS_UID,d.NODE_NAME,d.NODE_TYPE, ");
			sql.append(" date_format(d.UPDATE_DATE,'%y-%m-%d %H:%i') as UPDATE_DATE  ");
			sql.append(" FROM pm_project_docs d  ");
			sql.append(" WHERE d.PROJECT_UID = '"+map.get("project_uid")+"' ");
			if("".equals(map.get("P_DOCS_UID"))){
				sql.append(" AND d.P_DOCS_UID is null");
			}else{
				sql.append(" AND d.P_DOCS_UID = '"+map.get("P_DOCS_UID")+"' ");
			}
			sql.append(" ) a ");
			sql.append(" LEFT JOIN ");
			sql.append(" (SELECT f.TARGET_UID,a.FILE_EXT_NAME,a.FILE_PATH, ");
			sql.append(" a.FILE_SIZE ");
			sql.append("  FROM bu_file f ");
			sql.append(" LEFT JOIN bu_attachment a ");
			sql.append(" ON f.ATTACHMENT_UID = a.ATTACHMENT_UID ");
			sql.append(" WHERE f.FILE_TYPE = '10801' ");
			sql.append(" AND f.ENABLED = '1' ");
			sql.append(" ) b ");
			sql.append(" ON a.PROJECT_DOCS_UID = b.TARGET_UID ");
			
			rsList = DBUtil.queryReturnList(conn,sql.toString());
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return rsList;
	}

	public List<Map<String, String>> queryFiles(HashMap<String, String> map) {
		Connection conn = DBUtil.getConnection();
		StringBuffer sql = new StringBuffer();
		List<Map<String, String>> rsList = new ArrayList<Map<String,String>>();
		try {
/*			sql.append(" SELECT t.PROJECT_DOCS_UID,t.P_DOCS_UID,  ");
			sql.append(" t.NODE_NAME,t.NODE_TYPE,date_format(t.UPDATE_DATE,'%y-%m-%d %H:%i') as UPDATE_DATE ");
			sql.append(" FROM pm_project_docs t  ");
			sql.append(" WHERE t.PROJECT_UID = '"+map.get("project_uid")+"'  ");
			
			sql.append(" AND t.NODE_NAME like '%"+map.get("keyWords")+"%' ");*/
			
			sql.append(" SELECT * FROM ( ");
			sql.append(" SELECT d.PROJECT_DOCS_UID,d.P_DOCS_UID,d.NODE_NAME, ");
			sql.append(" date_format(d.UPDATE_DATE,'%y-%m-%d %H:%i') as UPDATE_DATE  ");
			sql.append(" FROM pm_project_docs d  ");
			sql.append(" WHERE d.PROJECT_UID = '1' ");
			sql.append(" AND d.NODE_NAME like '%"+map.get("keyWords")+"%'  ");
			sql.append(" ) a ");
			sql.append(" LEFT JOIN ");
			sql.append(" (SELECT f.TARGET_UID,a.FILE_EXT_NAME,a.FILE_PATH, ");
			sql.append(" a.FILE_SIZE ");
			sql.append("  FROM bu_file f ");
			sql.append(" LEFT JOIN bu_attachment a ");
			sql.append(" ON f.ATTACHMENT_UID = a.ATTACHMENT_UID ");
			sql.append(" WHERE f.FILE_TYPE = '10801' ");
			sql.append(" AND f.ENABLED = '1' ");
			sql.append(" ) b ");
			sql.append(" ON a.PROJECT_DOCS_UID = b.TARGET_UID ");
			
			rsList = DBUtil.queryReturnList(conn,sql.toString());
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return rsList;
	}

	public boolean deleteFiles(String doc_uids) {
		boolean flag = false;
		Connection conn = DBUtil.getConnection();
		try {
			String[] _doc_uids = doc_uids.split(",");
			for (int i = 0; i < _doc_uids.length; i++) {
				//删除子文件
				String sql = " DELETE FROM pm_project_docs WHERE P_DOCS_UID = '"+_doc_uids[i]+"'";
				DBUtil.exec(sql.toString());
				//删除当前文件
				String sql1 = " DELETE FROM pm_project_docs WHERE PROJECT_DOCS_UID = '"+_doc_uids[i]+"'";
				DBUtil.exec(sql1.toString());
			}

			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnetion(conn);
		}
		return flag;
	}
    
    // 在此可加入其它方法

}
